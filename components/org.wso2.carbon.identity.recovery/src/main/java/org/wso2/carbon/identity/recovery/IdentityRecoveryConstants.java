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
        ERROR_CODE_EXPIRED_CODE("18002", "过期码 '%s.'"),
        ERROR_CODE_INVALID_USER("18003", "无效的用户 '%s.'"),
        ERROR_CODE_UNEXPECTED("18013", "意外的错误"),
        ERROR_CODE_RECOVERY_NOTIFICATION_FAILURE("18015", "发送恢复通知时出错"),
        ERROR_CODE_INVALID_TENANT("18016", "无效的租户的 %s。'"),
        ERROR_CODE_CHALLENGE_QUESTION_NOT_FOUND("18017", "没有发现挑战问题。 %s"),
        ERROR_CODE_INVALID_CREDENTIALS("17002", "凭据无效"),
        ERROR_CODE_LOCKED_ACCOUNT("17003", "用户帐户已被锁定 - '%s.'"),
        ERROR_CODE_DISABLED_ACCOUNT("17004", "用户帐户已禁用 '%s.'"),
        ERROR_CODE_REGISTRY_EXCEPTION_GET_CHALLENGE_QUESTIONS("20001", "获得挑战问题时的注册表异常"),
        ERROR_CODE_REGISTRY_EXCEPTION_SET_CHALLENGE_QUESTIONS("20002", "设置挑战问题时的注册表异常"),
        ERROR_CODE_GETTING_CHALLENGE_URIS("20003", "获取挑战问题URI时出错 '%s。'"),
        ERROR_CODE_GETTING_CHALLENGE_QUESTIONS("20004", "获得挑战问题时出错 '%s.'"),
        ERROR_CODE_GETTING_CHALLENGE_QUESTION("20005", "获得挑战问题时出错 '%s.'"),
        ERROR_CODE_QUESTION_OF_USER("20006", "设置用户挑战问题时出错 '%s.'"),
        ERROR_CODE_NO_HASHING_ALGO("20007", "散列安全答案时出错"),
        ERROR_CODE_INVALID_ANSWER_FOR_SECURITY_QUESTION("20008", "答案无效"),
        ERROR_CODE_STORING_RECOVERY_DATA("20009", "安全问题的答案无效"),
        ERROR_CODE_NEED_TO_ANSWER_MORE_SECURITY_QUESTION("20010", "需要回答更多安全问题"),
        ERROR_CODE_TRIGGER_NOTIFICATION("20011", "用户触发通知时出错 '%s.'"),
        ERROR_CODE_NEED_TO_ANSWER_TO_REQUESTED_QUESTIONS("20012", "需要回答所有要求的安全问题"),
        ERROR_CODE_NO_VALID_USERNAME("20013", "找不到用于恢复的有效用户名"),
        ERROR_CODE_NO_FIELD_FOUND_FOR_USER_RECOVERY("20014", "找不到用于恢复用户名的字段"),
        ERROR_CODE_NO_USER_FOUND_FOR_RECOVERY("20015", "找不到有效的用户"),
        ERROR_CODE_ISSUE_IN_LOADING_RECOVERY_CONFIGS("20016", "加载恢复配置时出错"),
        ERROR_CODE_NOTIFICATION_BASED_PASSWORD_RECOVERY_NOT_ENABLE("20017", "未启用基于通知的密码恢复"),
        ERROR_CODE_QUESTION_BASED_RECOVERY_NOT_ENABLE("20018", "未启用基于安全问题的恢复"),
        ERROR_CODE_ADD_SELF_USER("20019", "添加自注册用户时出错"),
        ERROR_CODE_LOCK_USER_USER("20020", "锁定用户时出错"),
        ERROR_CODE_DISABLE_SELF_SIGN_UP("20021", "自注册功能已禁用"),
        ERROR_CODE_LOCK_USER_ACCOUNT("20022", "锁定用户帐户时出错"),
        ERROR_CODE_UNLOCK_USER_USER("20023", "解锁用户时出错"),
        ERROR_CODE_OLD_CODE_NOT_FOUND("20024", "找不到旧的确认码"),
        ERROR_CODE_FAILED_TO_LOAD_REALM_SERVICE("20025", "无法从租户ID: %s检索用户域"),
        ERROR_CODE_FAILED_TO_LOAD_USER_STORE_MANAGER("20026", "无法检索用户存储管理器。"),
        ERROR_CODE_FAILED_TO_LOAD_USER_CLAIMS("20027", "检索用户声明时出错"),
        ERROR_CODE_FAILED_TO_LOAD_GOV_CONFIGS("20028", "检索帐户锁连接器配置时出错"),
        ERROR_CODE_HISTORY_VIOLATE("22001", "此密码已在近期历史中使用。 请选择其他密码"),
        ERROR_CODE_MULTIPLE_QUESTION_NOT_ALLOWED("20029", "此操作不允许多个挑战问题"),
        ERROR_CODE_USER_ALREADY_EXISTS("20030", "用户%s已存在于系统中。 请使用其他用户名。"),
        ERROR_CODE_USERNAME_RECOVERY_NOT_ENABLE("20031", "未启用用户名恢复"),
        ERROR_CODE_MULTIPLE_USERS_MATCHING("20032", "找到了多个用户"),
        ERROR_CODE_ISSUE_IN_LOADING_SIGNUP_CONFIGS("20033", "加载注册配置时出错"),
        ERROR_CODE_FAILED_TO_UPDATE_USER_CLAIMS("20034", "更新用户声明时出错"),
        ERROR_CODE_POLICY_VIOLATION("20035", "密码政策违规"),
        ERROR_CODE_PROVIDED_CONFIRMATION_CODE_NOT_VALID("20036", "提供的确认码%s无效"),
        ERROR_CODE_CONFIRMATION_CODE_NOT_PROVIDED("20037", "没有为用户%s提供确认代码。"),
        ERROR_CODE_RECOVERY_SCENARIO_NOT_PROVIDED("20038", "没有为用户%s提供恢复方案。"),
        ERROR_CODE_RECOVERY_STEP_NOT_PROVIDED("20039", "用户%s未提供恢复步骤。"),
        ERROR_CODE_NOTIFICATION_TYPE_NOT_PROVIDED("20040", "用户%s未提供通知类型。"),
        ERROR_CODE_FAILED_TO_CHECK_ACCOUNT_LOCK_STATUS("20041", "验证用户：%s的帐户锁定状态时出错。"),
        ERROR_CODE_ADD_USER_CONSENT("20042", "添加用户%s的同意时出错。"),;


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
        public static final String[] SECRET_QUESTIONS_SET01 = new String[]{"City where you were born ?",
                "Father's middle name ?", "Favorite food ?", "Favorite vacation location ?"};

        // TODO remove this
        public static final String[] SECRET_QUESTIONS_SET02 = new String[]{"Model of your first car ?",
                "Name of the hospital where you were born ?", "Name of your first pet ?", "Favorite sport ?"};


    }

    public static class Consent {

        public static final String COLLECTION_METHOD_SELF_REGISTRATION = "Web Form - Self Registration";
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
