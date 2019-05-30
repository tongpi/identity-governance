/*
 *
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.carbon.identity.user.endpoint;

public final class Constants {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String SUCCESS = "SUCCESS";
    public static final String INVALID = "INVALID";
    public static final String FORBIDDEN = "FORBIDDEN";
    public static final String FAILED = "FAILED";
    public static final String SERVER_ERROR = "执行任务时服务器出错。";
    public static final String APPLICATION_JSON = "application/json";
    public static final String DEFAULT_RESPONSE_CONTENT_TYPE = APPLICATION_JSON;
    public static final String HEADER_CONTENT_TYPE = "Content-Type";



    //default error messages
    public static final String STATUS_FORBIDDEN_MESSAGE_DEFAULT = "Forbidden";
    public static final String STATUS_NOT_FOUND_MESSAGE_DEFAULT = "Not Found";
    public static final String STATUS_INTERNAL_SERVER_ERROR_MESSAGE_DEFAULT = "内部服务器错误";
    public static final String STATUS_METHOD_NOT_ALLOWED_MESSAGE_DEFAULT = "方法不允许";
    public static final String STATUS_BAD_REQUEST_MESSAGE_DEFAULT = "错误的请求";
    public static final String STATUS_CONFLICT_MESSAGE_RESOURCE_ALREADY_EXISTS = "资源已经存在";
    public static final String STATUS_CONFLICT_MESSAGE_DEFAULT = "Conflict";

    public static final String STATUS_INTERNAL_SERVER_ERROR_DESCRIPTION_DEFAULT = "服务器遇到了 "
            + "内部错误。请联系管理员.";
    public static final String TENANT_NAME_FROM_CONTEXT = "TenantNameFromContext";

}
