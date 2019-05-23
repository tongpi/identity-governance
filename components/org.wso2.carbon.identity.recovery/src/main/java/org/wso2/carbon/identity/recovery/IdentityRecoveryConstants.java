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

package org.wso2.carbon.identity.recovery;

/**
 * Identity management related constants
 */
public class IdentityRecoveryConstants {


    public static final String IDENTITY_MANAGEMENT_PATH = "/identity";
    public static final String IDENTITY_MANAGEMENT_QUESTIONS = IDENTITY_MANAGEMENT_PATH + "/questionCollection";
    public static final String IDENTITY_MANAGEMENT_I18N_PATH = "/repository/components/identity";
    public static final String IDENTITY_I18N_QUESTIONS =
            IDENTITY_MANAGEMENT_I18N_PATH + "/questionCollection";
    public static final String LINE_SEPARATOR = "!";
    public static final String CHALLENGE_QUESTION_URI = "http://wso2.org/claims/challengeQuestionUris";
    public static final String NOTIFICATION_TYPE_PASSWORD_RESET = "passwordreset";
    public static final String NOTIFICATION_TYPE_ADMIN_FORCED_PASSWORD_RESET = "adminforcedpasswordreset";
    public static final String NOTIFICATION_TYPE_ADMIN_FORCED_PASSWORD_RESET_WITH_OTP = "adminforcedpasswordresetwithotp";
    public static final String NOTIFICATION_TYPE_ACCOUNT_CONFIRM = "accountconfirmation";
    public static final String NOTIFICATION_TYPE_RESEND_ACCOUNT_CONFIRM = "resendaccountconfirmation";
    public static final String NOTIFICATION_TYPE_EMAIL_CONFIRM = "emailconfirm";
    public static final String NOTIFICATION_TYPE_ASK_PASSWORD = "askPassword";
    public static final String NOTIFICATION_TYPE_PASSWORD_RESET_SUCCESS = "passwordresetsucess";
    public static final String NOTIFICATION_TYPE_PASSWORD_RESET_INITIATE = "initiaterecovery";
    public static final String NOTIFICATION_ACCOUNT_ID_RECOVERY = "accountidrecovery";
    public static final String RECOVERY_STATUS_INCOMPLETE = "INCOMPLETE";
    public static final String RECOVERY_STATUS_COMPLETE = "COMPLETE";
    public static final String TEMPLATE_TYPE = "TEMPLATE_TYPE";
    public static final String CONFIRMATION_CODE = "confirmation-code";
    public static final String WSO2CARBON_CLAIM_DIALECT = "http://wso2.org/claims";
    public static final String ACCOUNT_LOCKED_CLAIM = "http://wso2.org/claims/identity/accountLocked";
    public static final String ACCOUNT_UNLOCK_TIME_CLAIM = "http://wso2.org/claims/identity/unlockTime";
    public static final String ACCOUNT_DISABLED_CLAIM = "http://wso2.org/claims/identity/accountDisabled";
    public static final String VERIFY_EMAIL_CLIAM = "http://wso2.org/claims/identity/verifyEmail";
    public static final String EMAIL_VERIFIED_CLAIM = "http://wso2.org/claims/identity/emailVerified";
    public static final String ASK_PASSWORD_CLAIM = "http://wso2.org/claims/identity/askPassword";
    public static final String ADMIN_FORCED_PASSWORD_RESET_CLAIM = "http://wso2.org/claims/identity/adminForcedPasswordReset";
    public static final String OTP_PASSWORD_CLAIM = "http://wso2.org/claims/oneTimePassword";
    public static final String DEFAULT_CHALLENGE_QUESTION_SEPARATOR = "!";


    public static final String PASSWORD_RESET_FAIL_ATTEMPTS_CLAIM = "http://wso2" +
            ".org/claims/identity/failedPasswordRecoveryAttempts";
    public static final String SIGN_UP_ROLE_SEPARATOR = ",";


    public static final String LOCALE_EN_US = "en_US";
    public static final String LOCALE_LK_LK = "lk_lk";
    public static final String SELF_SIGNUP_ROLE = "Internal/selfsignup";
    public static final String EXECUTE_ACTION = "ui.execute";


    private IdentityRecoveryConstants() {
    }

    public enum ErrorMessages {

        ERROR_CODE_INVALID_CODE("18001", "无效码 '%s.'"),
        ERROR_CODE_EXPIRED_CODE("18002", "码已过期 '%s.'"),
        ERROR_CODE_INVALID_USER("18003", "非法用户 '%s.'"),
        ERROR_CODE_UNEXPECTED("18013", "不可预知的错误"),
        ERROR_CODE_RECOVERY_NOTIFICATION_FAILURE("18015", "发送恢复通知时出错"),
        ERROR_CODE_INVALID_TENANT("18016", "非法的租户 '%s.'"),
        ERROR_CODE_CHALLENGE_QUESTION_NOT_FOUND("18017", "找不到挑战问题. %s"),
        ERROR_CODE_INVALID_CREDENTIALS("17002", "无效的凭据"),
        ERROR_CODE_LOCKED_ACCOUNT("17003", "用户账号被锁定 - '%s.'"),
        ERROR_CODE_DISABLED_ACCOUNT("17004", "用户账号被禁用 '%s.'"),
        ERROR_CODE_REGISTRY_EXCEPTION_GET_CHALLENGE_QUESTIONS("20001", "获取挑战问题时出现注册表异常"),
        ERROR_CODE_REGISTRY_EXCEPTION_SET_CHALLENGE_QUESTIONS("20002", "设置挑战问题时出现注册表异常"),
        ERROR_CODE_GETTING_CHALLENGE_URIS("20003", "获取挑战问题时出错， URI： '%s.'"),
        ERROR_CODE_GETTING_CHALLENGE_QUESTIONS("20004", "获取挑战问题时出错 '%s.'"),
        ERROR_CODE_GETTING_CHALLENGE_QUESTION("20005", "获取挑战问题时出错 '%s.'"),
        ERROR_CODE_QUESTION_OF_USER("20006", "获取挑战问题时出错，用户为： '%s.'"),
        ERROR_CODE_NO_HASHING_ALGO("20007", "哈希安全答案时出错"),
        ERROR_CODE_INVALID_ANSWER_FOR_SECURITY_QUESTION("20008", "答案无效"),
        ERROR_CODE_STORING_RECOVERY_DATA("20009", "安全问题答案无效"),
        ERROR_CODE_NEED_TO_ANSWER_MORE_SECURITY_QUESTION("20010", "需要回答更多的安全问题"),
        ERROR_CODE_TRIGGER_NOTIFICATION("20011", "为用户触发通知时出错。用户为 '%s.'"),
        ERROR_CODE_NEED_TO_ANSWER_TO_REQUESTED_QUESTIONS("20012", "需要回答所有要求的安全问题"),
        ERROR_CODE_NO_VALID_USERNAME("20013", "找不到用于恢复的有效用户名"),
        ERROR_CODE_NO_FIELD_FOUND_FOR_USER_RECOVERY("20014", "找不到用于用户名恢复的文件"),
        ERROR_CODE_NO_USER_FOUND_FOR_RECOVERY("20015", "找不到有效用户"),
        ERROR_CODE_ISSUE_IN_LOADING_RECOVERY_CONFIGS("20016", "加载恢复配置时出错"),
        ERROR_CODE_NOTIFICATION_BASED_PASSWORD_RECOVERY_NOT_ENABLE("20017", "未启用基于通知的密码恢复"),
        ERROR_CODE_QUESTION_BASED_RECOVERY_NOT_ENABLE("20018", "未启用基于安全问题的恢复"),
        ERROR_CODE_ADD_SELF_USER("20019", "添加自注册用户时出错"),
        ERROR_CODE_LOCK_USER_USER("20020", "锁定用户时出错"),
        ERROR_CODE_DISABLE_SELF_SIGN_UP("20021", "已禁用自注册功能"),
        ERROR_CODE_LOCK_USER_ACCOUNT("20022", "锁定用户账号时出错"),
        ERROR_CODE_UNLOCK_USER_USER("20023", "解锁用户时出错"),
        ERROR_CODE_OLD_CODE_NOT_FOUND("20024", "未找到旧确认码"),
        ERROR_CODE_FAILED_TO_LOAD_REALM_SERVICE("20025", "无法从租户 id : %s检索用户领域"),
        ERROR_CODE_FAILED_TO_LOAD_USER_STORE_MANAGER("20026", "检索用户存储管理器失败."),
        ERROR_CODE_FAILED_TO_LOAD_USER_CLAIMS("20027", "检索用户声明时出错"),
        ERROR_CODE_FAILED_TO_LOAD_GOV_CONFIGS("20028", "检索帐户锁定连接器配置时出错"),

        ERROR_CODE_HISTORY_VIOLATE("22001", "此密码已在最近的历史记录中使用。请选择其他密码"),

        ERROR_CODE_MULTIPLE_QUESTION_NOT_ALLOWED("20029", "此操作不允许有多个挑战问题"),
        ERROR_CODE_USER_ALREADY_EXISTS("20030", "用户 %s 在系统中已存在。请使用其他用户名."),
        ERROR_CODE_USERNAME_RECOVERY_NOT_ENABLE("20031", "未启用用户名恢复"),
        ERROR_CODE_MULTIPLE_USERS_MATCHING("20032", "找到多个用户"),
        ERROR_CODE_ISSUE_IN_LOADING_SIGNUP_CONFIGS("20033", "加载注册配置时出错"),
        ERROR_CODE_FAILED_TO_UPDATE_USER_CLAIMS("20034", "更新用户声明时出错"),
        ERROR_CODE_POLICY_VIOLATION("20035", "违反了密码策略"),
        ERROR_CODE_PROVIDED_CONFIRMATION_CODE_NOT_VALID("20036", "提供的确认码 %s 无效"),
        ERROR_CODE_CONFIRMATION_CODE_NOT_PROVIDED("20037", "没有为用户 %s 提供确认码."),
        ERROR_CODE_RECOVERY_SCENARIO_NOT_PROVIDED("20038", "没有为用户提供恢复方案 ."),
        ERROR_CODE_RECOVERY_STEP_NOT_PROVIDED("20039", "没有为用户 %s 提供恢复步骤."),
        ERROR_CODE_NOTIFICATION_TYPE_NOT_PROVIDED("20040", "没有为用户 %s 提供通知类型."),
        ERROR_CODE_FAILED_TO_CHECK_ACCOUNT_LOCK_STATUS("20041", "验证用户的帐户锁定状态时出错: " +
                "%s."),
        ERROR_CODE_ADD_USER_CONSENT("20042", "为用户 %s 添加同意时出错."),;


        private final String code;
        private final String message;

        ErrorMessages(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return code + " - " + message;
        }

    }

    public static class ConnectorConfig {
        public static final String NOTIFICATION_INTERNALLY_MANAGE = "Recovery.Notification.InternallyManage";
        public static final String NOTIFY_USER_EXISTENCE = "Recovery.NotifyUserExistence";
        public static final String NOTIFICATION_SEND_RECOVERY_NOTIFICATION_SUCCESS = "Recovery.NotifySuccess";
        public static final String NOTIFICATION_SEND_RECOVERY_SECURITY_START = "Recovery.Question.Password.NotifyStart";
        public static final String NOTIFICATION_BASED_PW_RECOVERY = "Recovery.Notification.Password.Enable";
        public static final String QUESTION_BASED_PW_RECOVERY = "Recovery.Question.Password.Enable";
        public static final String FORCE_ADD_PW_RECOVERY_QUESTION = "Recovery.Question.Password.Forced.Enable";
        public static final String USERNAME_RECOVERY_ENABLE = "Recovery.Notification.Username.Enable";
        public static final String QUESTION_CHALLENGE_SEPARATOR = "Recovery.Question.Password.Separator";
        public static final String QUESTION_MIN_NO_ANSWER = "Recovery.Question.Password.MinAnswers";
        public static final String EXPIRY_TIME = "Recovery.ExpiryTime";
        public static final String RECOVERY_QUESTION_PASSWORD_RECAPTCHA_ENABLE = "Recovery.Question.Password" +
                ".ReCaptcha.Enable";
        public static final String RECOVERY_QUESTION_PASSWORD_RECAPTCHA_MAX_FAILED_ATTEMPTS = "Recovery.Question" +
                ".Password.ReCaptcha.MaxFailedAttempts";
        public static final String ENABLE_SELF_SIGNUP = "SelfRegistration.Enable";
        public static final String ACCOUNT_LOCK_ON_CREATION = "SelfRegistration.LockOnCreation";
        public static final String SIGN_UP_NOTIFICATION_INTERNALLY_MANAGE = "SelfRegistration.Notification" +
                ".InternallyManage";
        public static final String SELF_REGISTRATION_RE_CAPTCHA = "SelfRegistration.ReCaptcha";
        public static final String SELF_REGISTRATION_VERIFICATION_CODE_EXPIRY_TIME = "SelfRegistration" +
                ".VerificationCode.ExpiryTime";

        public static final String ENABLE_EMIL_VERIFICATION = "EmailVerification.Enable";
        public static final String EMAIL_VERIFICATION_EXPIRY_TIME = "EmailVerification.ExpiryTime";
        public static final String ASK_PASSWORD_EXPIRY_TIME = "EmailVerification.AskPassword.ExpiryTime";
        public static final String ASK_PASSWORD_TEMP_PASSWORD_GENERATOR = "EmailVerification.AskPassword.PasswordGenerator";
        public static final String EMAIL_ACCOUNT_LOCK_ON_CREATION = "EmailVerification.LockOnCreation";
        public static final String EMAIL_VERIFICATION_NOTIFICATION_INTERNALLY_MANAGE = "EmailVerification.Notification.InternallyManage";

        public static final String ENABLE_ADMIN_PASSWORD_RESET_OFFLINE = "Recovery.AdminPasswordReset.Offline";
        public static final String ENABLE_ADMIN_PASSWORD_RESET_WITH_OTP = "Recovery.AdminPasswordReset.OTP";
        public static final String ENABLE_ADMIN_PASSWORD_RESET_WITH_RECOVERY_LINK = "Recovery.AdminPasswordReset.RecoveryLink";
    }

    public static class SQLQueries {

        public static final String STORE_RECOVERY_DATA = "INSERT INTO IDN_RECOVERY_DATA "
                + "(USER_NAME, USER_DOMAIN, TENANT_ID, CODE, SCENARIO,STEP, TIME_CREATED, REMAINING_SETS)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        public static final String LOAD_RECOVERY_DATA = "SELECT "
                + "* FROM IDN_RECOVERY_DATA WHERE USER_NAME = ? AND USER_DOMAIN = ? AND TENANT_ID = ? AND CODE = ? AND " +
                "SCENARIO = ? AND STEP = ?";

        public static final String LOAD_RECOVERY_DATA_CASE_INSENSITIVE = "SELECT * FROM IDN_RECOVERY_DATA WHERE" +
                " LOWER(USER_NAME)=LOWER(?) AND USER_DOMAIN = ? AND TENANT_ID = ? AND CODE= ? AND SCENARIO = ? AND " +
                "STEP = ?";

        public static final String LOAD_RECOVERY_DATA_FROM_CODE = "SELECT * FROM IDN_RECOVERY_DATA WHERE CODE = ?";


        public static final String INVALIDATE_CODE = "DELETE FROM IDN_RECOVERY_DATA WHERE CODE = ?";

        public static final String INVALIDATE_USER_CODES = "DELETE FROM IDN_RECOVERY_DATA WHERE USER_NAME = ? AND " +
                "USER_DOMAIN = ? AND TENANT_ID =?";

        public static final String INVALIDATE_USER_CODES_CASE_INSENSITIVE = "DELETE FROM IDN_RECOVERY_DATA WHERE " +
                "LOWER(USER_NAME)=LOWER(?) AND USER_DOMAIN = ? AND TENANT_ID =?";

        public static final String LOAD_RECOVERY_DATA_OF_USER = "SELECT "
                + "* FROM IDN_RECOVERY_DATA WHERE USER_NAME = ? AND USER_DOMAIN = ? AND TENANT_ID = ?";

        public static final String LOAD_RECOVERY_DATA_OF_USER_CASE_INSENSITIVE = "SELECT "
                + "* FROM IDN_RECOVERY_DATA WHERE LOWER(USER_NAME)=LOWER(?) AND USER_DOMAIN = ? AND TENANT_ID = ?";

    }

    public static class Questions {

        public static final String LOCALE_CLAIM = "http://wso2.org/claims/locality";
        public static final String BLACKLIST_REGEX = ".*[/\\\\].*";

        public static final String CHALLENGE_QUESTION_SET_ID = "questionSetId";
        public static final String CHALLENGE_QUESTION_ID = "questionId";
        public static final String CHALLENGE_QUESTION_LOCALE = "locale";

        // TODO remove this
        public static final String[] SECRET_QUESTIONS_SET01 = new String[]{"你出生的城市是什么?",
                "您父亲的名字是什么?", "您最爱吃的美食是什么?", "最喜欢的度假地点是什么?"};

        // TODO remove this
        public static final String[] SECRET_QUESTIONS_SET02 = new String[]{"你的第一辆汽车的品牌是什么?",
                "出生的医院的名字是什么?", "第一只宠物的名字?", "最喜欢的运动是什么?"};


    }

    public static class Consent {

        public static final String COLLECTION_METHOD_SELF_REGISTRATION = "Web 表单 - 自注册";
        public static final String DEFAULT_JURISDICTION = "Global";
        public static final String LANGUAGE_ENGLISH = "en";
        public static final String CONSENT = "consent";
        public static final String SERVICES = "services";
        public static final String PURPOSES = "purposes";
        public static final String PII_CATEGORY = "piiCategory";
        public static final String PII_CATEGORY_ID = "piiCategoryId";
        public static final String EXPLICIT_CONSENT_TYPE = "EXPLICIT";
        public static final String PURPOSE_ID = "purposeId";
        public static final String INFINITE_TERMINATION = "DATE_UNTIL:INDEFINITE";
        public static final String RESIDENT_IDP = "Resident IDP";
    }
}
