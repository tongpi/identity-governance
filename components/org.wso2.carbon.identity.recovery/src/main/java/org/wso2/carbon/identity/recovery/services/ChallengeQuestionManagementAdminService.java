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
 * limitations under the License.
 */

package org.wso2.carbon.identity.recovery.services;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.CarbonConstants;
import org.wso2.carbon.context.CarbonContext;
import org.wso2.carbon.identity.application.common.model.User;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.core.util.IdentityTenantUtil;
import org.wso2.carbon.identity.recovery.ChallengeQuestionManager;
import org.wso2.carbon.identity.recovery.IdentityRecoveryClientException;
import org.wso2.carbon.identity.recovery.IdentityRecoveryConstants;
import org.wso2.carbon.identity.recovery.IdentityRecoveryException;
import org.wso2.carbon.identity.recovery.IdentityRecoveryServerException;
import org.wso2.carbon.identity.recovery.internal.IdentityRecoveryServiceDataHolder;
import org.wso2.carbon.identity.recovery.model.ChallengeQuestion;
import org.wso2.carbon.identity.recovery.model.UserChallengeAnswer;
import org.wso2.carbon.identity.recovery.util.Utils;
import org.wso2.carbon.user.api.AuthorizationManager;
import org.wso2.carbon.user.api.UserStoreException;
import org.wso2.carbon.utils.multitenancy.MultitenantUtils;

import java.util.List;

/**
 * Admin Service class to carry out operations related to challenge questions
 * management.
 */
public class ChallengeQuestionManagementAdminService {

    private static Log log = LogFactory.getLog(ChallengeQuestionManagementAdminService.class);
    private ChallengeQuestionManager questionManager = ChallengeQuestionManager.getInstance();

    /**
     * Get all challenge questions registered for a tenant.
     *
     * @param tenantDomain
     * @return
     * @throws IdentityRecoveryException
     */
    public ChallengeQuestion[] getChallengeQuestionsOfTenant(String tenantDomain) throws IdentityRecoveryException {

        List<ChallengeQuestion> challengeQuestionList;
        checkCrossTenantAccess(tenantDomain);
        try {
            challengeQuestionList = questionManager.getAllChallengeQuestions(tenantDomain);
            return challengeQuestionList.toArray(new ChallengeQuestion[challengeQuestionList.size()]);
        } catch (IdentityRecoveryException e) {
            String errorMgs = "在为租户: %s加载挑战问题时出错。";
            log.error(String.format(errorMgs, tenantDomain), e);
            throw new IdentityRecoveryException(String.format(errorMgs, tenantDomain), e);
        }
    }

    /**
     * Get all challenge questions applicable for a user based on his locale. If we
     * can't find any question in his locale we return challenge questions from the
     * default en_US locale.
     *
     * @param user
     * @return
     * @throws IdentityRecoveryServerException
     */
    public ChallengeQuestion[] getChallengeQuestionsForUser(User user) throws IdentityRecoveryException {
        if (user == null) {
            log.error("提供的用户对象为null。");
            throw new IdentityRecoveryClientException("提供的用户对象为null。");
        }

        String tenantDomain = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();
        List<ChallengeQuestion> challengeQuestionList;
        try {
            challengeQuestionList = questionManager.getAllChallengeQuestionsForUser(tenantDomain, user);
            return challengeQuestionList.toArray(new ChallengeQuestion[challengeQuestionList.size()]);
        } catch (IdentityRecoveryException e) {
            String errorMgs = "用户 : %s@%s 加载挑战问题时出错";
            log.error(String.format(errorMgs, user.getUserName(), tenantDomain), e);
            throw new IdentityRecoveryException(String.format(errorMgs, user.getUserName(), tenantDomain), e);
        }
    }

    /**
     * Get all tenant questions of a locale in a tenant domain
     *
     * @param tenantDomain
     * @param locale
     * @return
     * @throws IdentityRecoveryServerException
     */
    public ChallengeQuestion[] getChallengeQuestionsForLocale(String tenantDomain, String locale)
            throws IdentityRecoveryException {
        // check for cross tenant access
        checkCrossTenantAccess(tenantDomain);
        List<ChallengeQuestion> challengeQuestionList;
        try {
            challengeQuestionList = questionManager.getAllChallengeQuestions(tenantDomain, locale);
            return challengeQuestionList.toArray(new ChallengeQuestion[challengeQuestionList.size()]);
        } catch (IdentityRecoveryException e) {
            String errorMgs = String.format("在%s区域设置中为租户%s加载质询问题时出错。",
                    locale, tenantDomain);
            log.error(errorMgs, e);
            throw new IdentityRecoveryException(errorMgs, e);
        }
    }

    /**
     * Set challenge questions for a tenant domain
     *
     * @param challengeQuestions
     * @param tenantDomain
     * @throws IdentityRecoveryException
     */
    public void setChallengeQuestionsOfTenant(ChallengeQuestion[] challengeQuestions, String tenantDomain)
            throws IdentityRecoveryException {
        checkCrossTenantAccess(tenantDomain);
        try {
            questionManager.addChallengeQuestions(challengeQuestions, tenantDomain);
        } catch (IdentityRecoveryException e) {
            String errorMsg = "租户域 %s 设置挑战问题时出错。";
            log.error(String.format(errorMsg, tenantDomain), e);
            throw new IdentityRecoveryException(String.format(errorMsg, tenantDomain), e);
        }
    }

    /**
     * Set challenge questions for a tenant domain
     *
     * @param challengeQuestions
     * @param tenantDomain
     * @throws IdentityRecoveryException
     */
    public void deleteChallengeQuestionsOfTenant(ChallengeQuestion[] challengeQuestions, String tenantDomain)
            throws IdentityRecoveryException {
        checkCrossTenantAccess(tenantDomain);
        try {
            questionManager.deleteChallengeQuestions(challengeQuestions, tenantDomain);
        } catch (IdentityRecoveryException e) {
            String errorMsg = "租户域 %s 删除挑战问题时出错。";
            log.error(String.format(errorMsg, tenantDomain), e);
            throw new IdentityRecoveryException(String.format(errorMsg, tenantDomain), e);
        }
    }

    /**
     * Set challenge question answers for a user
     *
     * @param user
     * @param userChallengeAnswers
     * @throws IdentityRecoveryException
     */
    public void setUserChallengeAnswers(User user, UserChallengeAnswer[] userChallengeAnswers)
            throws IdentityRecoveryException {
        if (user == null) {
            log.error("User object provided is null.");
            throw new IdentityRecoveryClientException("提供的用户对象为null。");
        }

        String tenantAwareUserName = MultitenantUtils.getTenantAwareUsername(user.getUserName());

        if (ArrayUtils.isEmpty(userChallengeAnswers)) {
            String errorMsg = "用户" + tenantAwareUserName + "没有提供挑战问题答案";
            log.error(errorMsg);
            throw new IdentityRecoveryClientException(errorMsg);
        }

        String tenantDomain = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();
        String loggedInName = CarbonContext.getThreadLocalCarbonContext().getUsername();

        // TODO externalize the authorization logic
        if (tenantAwareUserName != null && !tenantAwareUserName.equals(loggedInName)) {
            boolean isAuthorized = isUserAuthorized(tenantAwareUserName, tenantDomain);
            if (!isAuthorized) {
                throw new IdentityRecoveryClientException(
                        "未经授权的访问！！可能的特权提升攻击。用户" + loggedInName
                                + " 尝试更改用户" + tenantAwareUserName + "的挑战问题");
            }
        } else if (tenantAwareUserName == null) {
            tenantAwareUserName = loggedInName;
        }

        try {
            questionManager.setChallengesOfUser(user, userChallengeAnswers);

        } catch (IdentityException e) {
            String errorMessage = "在为用户" + tenantAwareUserName + "持续存在用户质询时出错";
            log.error(errorMessage, e);
            throw new IdentityRecoveryException(errorMessage, e);
        }
    }

    /**
     * Get Challenge question answers along with their encrypted answers of a user
     *
     * @param user
     * @return
     * @throws IdentityRecoveryException
     */
    public UserChallengeAnswer[] getUserChallengeAnswers(User user) throws IdentityRecoveryException {
        if (user == null) {
            log.error("User object provided is null.");
            throw new IdentityRecoveryClientException("提供的用户对象为null。");
        }

        String tenantAwareUserName = MultitenantUtils.getTenantAwareUsername(user.getUserName());
        String tenantDomain = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();
        String loggedInName = CarbonContext.getThreadLocalCarbonContext().getUsername();

        // TODO externalize authorization
        if (tenantAwareUserName != null && !tenantAwareUserName.equals(loggedInName)) {
            boolean isAuthorized = isUserAuthorized(tenantAwareUserName, tenantDomain);
            if (!isAuthorized) {
                throw new IdentityRecoveryClientException(
                        "未经授权的访问!! 可能违反保密规定。 " + "用户 " + loggedInName
                                + " 尝试获取用户" + tenantAwareUserName + "的挑战问题");
            }
        } else if (tenantAwareUserName == null) {
            tenantAwareUserName = loggedInName;
        }

        try {
            return questionManager.getChallengeAnswersOfUser(user);
        } catch (IdentityRecoveryException e) {
            String msg = "为" + tenantAwareUserName + "检索用户质询答案时出错 ";
            log.error(msg, e);
            throw new IdentityRecoveryException(msg, e);
        }
    }

    private boolean isUserAuthorized(String tenantAwareUserName, String tenantDomain) throws IdentityRecoveryException {

        int tenantId = IdentityTenantUtil.getTenantId(tenantDomain);
        AuthorizationManager authzManager = null;
        boolean isAuthorized;

        try {
            authzManager = IdentityRecoveryServiceDataHolder.getInstance().getRealmService()
                    .getTenantUserRealm(tenantId).getAuthorizationManager();

            isAuthorized = authzManager.isUserAuthorized(tenantAwareUserName, "/permission/admin/manage/identity",
                    CarbonConstants.UI_PERMISSION_ACTION);

        } catch (UserStoreException e) {
            throw new IdentityRecoveryServerException("检查租户" + tenantDomain + "的用户" + tenantAwareUserName + "的访问级别时出错", e);
        }

        return isAuthorized;
    }

    private void checkCrossTenantAccess(String tenantDomain) throws IdentityRecoveryClientException {
        String loggedInUser = CarbonContext.getThreadLocalCarbonContext().getUsername();
        String loggedInTenant = CarbonContext.getThreadLocalCarbonContext().getTenantDomain();

        if (!StringUtils.equals(loggedInTenant, tenantDomain)) {
            String errorMsg = String.format(
                    "未经授权的访问。用户 %s@%s 尝试检索 %s 租户的挑战问题",
                    loggedInUser, loggedInTenant, tenantDomain);
            throw new IdentityRecoveryClientException(errorMsg);
        }

    }

}
