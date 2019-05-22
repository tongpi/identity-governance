/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations und
 */

package org.wso2.carbon.identity.recovery.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.User;
import org.wso2.carbon.identity.base.IdentityRuntimeException;
import org.wso2.carbon.identity.core.bean.context.MessageContext;
import org.wso2.carbon.identity.core.handler.InitConfig;
import org.wso2.carbon.identity.core.model.IdentityErrorMsgContext;
import org.wso2.carbon.identity.core.util.IdentityCoreConstants;
import org.wso2.carbon.identity.core.util.IdentityUtil;
import org.wso2.carbon.identity.event.IdentityEventConstants;
import org.wso2.carbon.identity.event.IdentityEventException;
import org.wso2.carbon.identity.event.event.Event;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;
import org.wso2.carbon.identity.recovery.IdentityRecoveryException;
import org.wso2.carbon.identity.recovery.signup.UserSelfRegistrationManager;
import org.wso2.carbon.user.core.UserCoreConstants;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.UserStoreManager;
import org.wso2.carbon.user.core.util.UserCoreUtil;

import java.util.Map;

public class AccountConfirmationValidationHandler extends AbstractEventHandler {

    private static final Log log = LogFactory.getLog(AccountConfirmationValidationHandler.class);

    public static final String ACCOUNT_LOCKED_CLAIM = "http://is.cd.mtn:9443/claims/identity/accountLocked";

    public String getName() {
        return "accountConfirmationValidation";
    }

    public String getFriendlyName() {
        return "帐户确认验证";
    }

    @Override
    public void handleEvent(Event event) throws IdentityEventException {

        Map<String, Object> eventProperties = event.getEventProperties();
        String userName = (String) eventProperties.get(IdentityEventConstants.EventProperty.USER_NAME);
        UserStoreManager userStoreManager = (UserStoreManager) eventProperties
                .get(IdentityEventConstants.EventProperty.USER_STORE_MANAGER);

        String tenantDomain = (String) eventProperties.get(IdentityEventConstants.EventProperty.TENANT_DOMAIN);
        String domainName = userStoreManager.getRealmConfiguration()
                .getUserStoreProperty(UserCoreConstants.RealmConfig.PROPERTY_DOMAIN_NAME);

        User user = new User();
        user.setUserName(userName);
        user.setTenantDomain(tenantDomain);
        user.setUserStoreDomain(domainName);

        if (IdentityEventConstants.Event.PRE_AUTHENTICATION.equals(event.getEventName())) {
            if (log.isDebugEnabled()) {
                log.debug("PreAuthenticate");
            }
            boolean isAccountLocked = true;
            try {
                if (isAuthPolicyAccountExistCheck() && !isUserExistsInDomain(userStoreManager, userName)) {
                    IdentityErrorMsgContext customErrorMessageContext = new IdentityErrorMsgContext(
                            UserCoreConstants.ErrorCode.USER_DOES_NOT_EXIST);
                    IdentityUtil.setIdentityErrorMsg(customErrorMessageContext);
                    return;
                }
                Map<String, String> values = userStoreManager.getUserClaimValues(userName,
                        new String[] { ACCOUNT_LOCKED_CLAIM }, UserCoreConstants.DEFAULT_PROFILE);
                isAccountLocked = Boolean.parseBoolean(values.get(ACCOUNT_LOCKED_CLAIM));
            } catch (UserStoreException e) {
                throw new IdentityEventException("检索帐户锁定声明值时出错", e);
            }
            if (isAccountLocked && !isUserAccountConfirmed(user)) {
                IdentityErrorMsgContext customErrorMessageContext = new IdentityErrorMsgContext(
                        IdentityCoreConstants.USER_ACCOUNT_NOT_CONFIRMED_ERROR_CODE);
                IdentityUtil.setIdentityErrorMsg(customErrorMessageContext);
                throw new IdentityEventException(IdentityCoreConstants.USER_ACCOUNT_NOT_CONFIRMED_ERROR_CODE,
                        "用户 : " + userName + " 尚未确认。");
            }
        }
    }

    @Override
    public void init(InitConfig configuration) throws IdentityRuntimeException {
        super.init(configuration);
    }

    @Override
    public int getPriority(MessageContext messageContext) {
        return 50;
    }

    /**
     * Check whether user is already confirmed or not.
     *
     * @param user
     * @return
     * @throws IdentityEventException
     */
    private boolean isUserAccountConfirmed(User user) throws IdentityEventException {
        boolean userConfirmed = false;
        try {
            userConfirmed = UserSelfRegistrationManager.getInstance().isUserConfirmed(user);
        } catch (IdentityRecoveryException e) {
            throw new IdentityEventException(
                    "检查此用户是否已确认时发生错误, " + e.getMessage(), e);
        }
        return userConfirmed;
    }

    private boolean isUserExistsInDomain(UserStoreManager userStoreManager, String userName) throws UserStoreException {

        boolean isExists = false;
        if (userStoreManager.isExistingUser(userName)) {
            isExists = true;
        }
        return isExists;
    }

    private boolean isAuthPolicyAccountExistCheck() {
        String authPolicyAccountExistCheck = IdentityUtil.getProperty("AuthenticationPolicy.CheckAccountExist");
        return authPolicyAccountExistCheck == null || Boolean.parseBoolean(authPolicyAccountExistCheck);
    }

}
