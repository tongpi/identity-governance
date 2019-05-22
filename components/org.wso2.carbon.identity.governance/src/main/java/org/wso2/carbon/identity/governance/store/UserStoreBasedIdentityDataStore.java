/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.governance.store;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.CarbonContext;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.core.util.IdentityCoreConstants;
import org.wso2.carbon.identity.governance.model.UserIdentityClaim;
import org.wso2.carbon.user.api.UserStoreException;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.UserCoreConstants;
import org.wso2.carbon.user.core.claim.Claim;
import org.wso2.carbon.user.core.common.AbstractUserStoreManager;
import org.wso2.carbon.user.core.util.UserCoreUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * This module persists data in to user store as user's attribute //TODO remove
 * method when user is deleted
 */
public class UserStoreBasedIdentityDataStore extends InMemoryIdentityDataStore {

    private static Log log = LogFactory.getLog(UserStoreBasedIdentityDataStore.class);
    private static final String TRUE_STRING = "TRUE";
    private static final String FALSE_STRING = "FALSE";
    private static ThreadLocal<String> userStoreInvoked = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return FALSE_STRING;
        }
    };

    /**
     * This method stores data in the read write user stores.
     */
    @Override
    public void store(UserIdentityClaim userIdentityDTO, UserStoreManager userStoreManager) throws IdentityException {

        UserIdentityClaim newIdentityClaimDO = new UserIdentityClaim(userIdentityDTO.getUserName(),
                userIdentityDTO.getUserIdentityDataMap());
        super.store(newIdentityClaimDO, userStoreManager);

        if (userIdentityDTO.getUserName() == null) {
            log.error("持久保存用户数据时出错。 提供空用户名。");
            return;
        }
        String username = UserCoreUtil.removeDomainFromName(userIdentityDTO.getUserName());

        try {
            // Check if the user store is read only. If it is read only and still uses user
            // store based data
            // store then log a warn.
            if (!userStoreManager.isReadOnly()) {
                // Need to clone the map. If not iterative calls will refer the same map
                userStoreManager.setUserClaimValues(username,
                        new HashMap<String, String>(userIdentityDTO.getUserIdentityDataMap()), null);
            } else {
                // If the user store is read only and still uses
                // UserStoreBasedIdentityDataStore, then log a warn
                log.warn("用户存储是只读的。 对身份的更改仅存储在内存中，不会在用户存储中更新。");
                return;
            }
        } catch (UserStoreException e) {
            if (!e.getMessage().startsWith(IdentityCoreConstants.USER_NOT_FOUND)) {
                throw IdentityException.error("将身份用户数据持久化到用户存储时出错", e);
            } else if (log.isDebugEnabled()) {
                String message = null;
                if (userStoreManager instanceof AbstractUserStoreManager) {
                    String domain = ((AbstractUserStoreManager) userStoreManager).getRealmConfiguration()
                            .getUserStoreProperty(UserCoreConstants.RealmConfig.PROPERTY_DOMAIN_NAME);
                    if (domain != null) {
                        message = "在" + domain + "用户: " + username + " 不存在";
                    }
                }
                if (message == null) {
                    message = "用户: " + username + " 不存在";
                }
                log.debug(message);
                return;
            }
        }
    }

    /**
     * This method loads identity and security questions from the user stores
     */
    @Override
    public UserIdentityClaim load(String userName, UserStoreManager userStoreManager) {
        UserIdentityClaim userIdentityDTO = super.load(userName, userStoreManager);
        if (userIdentityDTO != null) {
            return userIdentityDTO;
        }
        // check for thread local variable to avoid infinite recursive on this method (
        // load() )
        // which happen calling getUserClaimValues()
        if (TRUE_STRING.equals(userStoreInvoked.get())) {
            if (log.isDebugEnabled()) {
                log.debug("已经在堆栈中调用了UserStoreBasedIdentityDataStore.load()。 因此返回时不再处理load()。");
            }
            return null;
        } else {
            if (log.isDebugEnabled()) {
                log.debug("设置标志以指示方法UserStoreBasedIdentityDataStore.load()已被调用");
            }
            userStoreInvoked.set(TRUE_STRING);
        }

        Map<String, String> userDataMap = new HashMap<String, String>();
        try {
            // reading all claims of the user
            Claim[] claims = ((AbstractUserStoreManager) userStoreManager).getUserClaimValues(userName, null);
            // select the security questions and identity claims
            if (claims != null) {
                for (Claim claim : claims) {
                    String claimUri = claim.getClaimUri();
                    if (claimUri.contains(UserCoreConstants.ClaimTypeURIs.IDENTITY_CLAIM_URI)
                            || claimUri.contains(UserCoreConstants.ClaimTypeURIs.CHALLENGE_QUESTION_URI)) {
                        if (log.isDebugEnabled()) {
                            log.debug("添加 UserIdentityClaim : " + claimUri + " 值为 : " + claim.getValue());
                        }
                        userDataMap.put(claimUri, claim.getValue());
                    }
                }
            } else {
                // null is returned when the user doesn't exist
                return null;
            }
        } catch (UserStoreException e) {
            if (!e.getMessage().startsWith(IdentityCoreConstants.USER_NOT_FOUND)) {
                log.error("从用户存储中读取身份用户数据时出错", e);
            } else if (log.isDebugEnabled()) {
                String message = null;
                if (userStoreManager instanceof AbstractUserStoreManager) {
                    String domain = ((AbstractUserStoreManager) userStoreManager).getRealmConfiguration()
                            .getUserStoreProperty(UserCoreConstants.RealmConfig.PROPERTY_DOMAIN_NAME);
                    if (domain != null) {
                        message = "在" + domain + "用户: " + userName + " 不存在";
                    }
                }
                if (message == null) {
                    message = "用户: " + userName + " 不存在";
                }
                log.debug(message);
            }
            return null;
        } finally {
            // reset to initial value
            if (log.isDebugEnabled()) {
                log.debug("重置标志以指示正在完成的方法UserStoreBasedIdentityDataStore.load()");
            }
            userStoreInvoked.set(FALSE_STRING);
        }

        userIdentityDTO = new UserIdentityClaim(userName, userDataMap);
        int tenantId = CarbonContext.getThreadLocalCarbonContext().getTenantId();
        userIdentityDTO.setTenantId(tenantId);
        org.wso2.carbon.user.core.UserStoreManager store = (org.wso2.carbon.user.core.UserStoreManager) userStoreManager;
        String domainName = store.getRealmConfiguration()
                .getUserStoreProperty(UserCoreConstants.RealmConfig.PROPERTY_DOMAIN_NAME);

        try {
            super.store(userIdentityDTO, userStoreManager);
        } catch (IdentityException e) {
            log.error("读取用户身份数据时出错", e);
        }
        return userIdentityDTO;
    }

}
