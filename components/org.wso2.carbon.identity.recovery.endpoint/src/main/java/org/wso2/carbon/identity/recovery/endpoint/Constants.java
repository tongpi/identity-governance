package org.wso2.carbon.identity.recovery.endpoint;

public final class Constants {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String SUCCESS = "SUCCESS";
    public static final String INVALID = "INVALID";
    public static final String FORBIDDEN = "FORBIDDEN";
    public static final String FAILED = "FAILED";
    public static final String SERVER_ERROR = "Error occurred in the server while performing the task.";
    public static final String APPLICATION_JSON = "application/json";
    public static final String DEFAULT_RESPONSE_CONTENT_TYPE = APPLICATION_JSON;
    public static final String HEADER_CONTENT_TYPE = "Content-Type";



    //default error messages
    public static final String STATUS_FORBIDDEN_MESSAGE_DEFAULT = "被禁止";
    public static final String STATUS_NOT_FOUND_MESSAGE_DEFAULT = "未发现";
    public static final String STATUS_INTERNAL_SERVER_ERROR_MESSAGE_DEFAULT = "内部服务器错误";
    public static final String STATUS_METHOD_NOT_ALLOWED_MESSAGE_DEFAULT = "方法不允许";
    public static final String STATUS_BAD_REQUEST_MESSAGE_DEFAULT = "错误请求";
    public static final String STATUS_CONFLICT_MESSAGE_RESOURCE_ALREADY_EXISTS = "资源已经存在";
    public static final String STATUS_CONFLICT_MESSAGE_DEFAULT = "冲突";
    public static final String TENANT_NAME_FROM_CONTEXT = "TenantNameFromContext";

    public static final String STATUS_INTERNAL_SERVER_ERROR_DESCRIPTION_DEFAULT = "服务器遇到内部错误。请与管理员联系。";

    public static final String ERROR_CODE_NO_USER_FOUND_FOR_RECOVERY = "20015";
    public static final String ERROR_CODE_MULTIPLE_USERS_MATCHING = "20015";

}
