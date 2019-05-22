/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.captcha.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.captcha.connector.CaptchaConnector;
import org.wso2.carbon.identity.captcha.connector.CaptchaPostValidationResponse;
import org.wso2.carbon.identity.captcha.connector.CaptchaPreValidationResponse;
import org.wso2.carbon.identity.captcha.exception.CaptchaClientException;
import org.wso2.carbon.identity.captcha.exception.CaptchaException;
import org.wso2.carbon.identity.captcha.internal.CaptchaDataHolder;
import org.wso2.carbon.identity.captcha.util.CaptchaHttpServletResponseWrapper;
import org.wso2.carbon.identity.captcha.util.CaptchaUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Captcha filter.
 */
public class CaptchaFilter implements Filter {

    private static final Log log = LogFactory.getLog(CaptchaFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        if (log.isDebugEnabled()) {
            log.debug("验证码过滤器已激活。");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        try {

            List<CaptchaConnector> captchaConnectors = CaptchaDataHolder.getInstance().getCaptchaConnectors();

            CaptchaConnector selectedCaptchaConnector = null;
            for (CaptchaConnector captchaConnector : captchaConnectors) {
                if (captchaConnector.canHandle(servletRequest, servletResponse) && (selectedCaptchaConnector == null
                        || captchaConnector.getPriority() > selectedCaptchaConnector.getPriority())) {
                    selectedCaptchaConnector = captchaConnector;
                }
            }

            if (selectedCaptchaConnector == null) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            // Check whether captcha is required or will reach to the max failed attempts
            // with the current attempt.
            CaptchaPreValidationResponse captchaPreValidationResponse = selectedCaptchaConnector
                    .preValidate(servletRequest, servletResponse);

            if (captchaPreValidationResponse == null) {
                // Captcha connector failed to response. Default is success.
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

            if (captchaPreValidationResponse.isCaptchaValidationRequired()) {
                try {
                    boolean validCaptcha = selectedCaptchaConnector.verifyCaptcha(servletRequest, servletResponse);
                    if (!validCaptcha) {
                        log.warn("验证码验证失败了。");
                        httpResponse.sendRedirect(CaptchaUtil.getOnFailRedirectUrl(httpRequest.getHeader("referer"),
                                captchaPreValidationResponse.getOnCaptchaFailRedirectUrls(),
                                captchaPreValidationResponse.getCaptchaAttributes()));
                        return;
                    }
                } catch (CaptchaClientException e) {
                    log.warn("验证码验证失败了。 原因： " + e.getMessage());
                    httpResponse.sendRedirect(CaptchaUtil.getOnFailRedirectUrl(httpRequest.getHeader("referer"),
                            captchaPreValidationResponse.getOnCaptchaFailRedirectUrls(),
                            captchaPreValidationResponse.getCaptchaAttributes()));
                    return;
                }
            }

            // Enable reCaptcha for the destination.
            if (captchaPreValidationResponse.isEnableCaptchaForRequestPath()) {
                if (captchaPreValidationResponse.getCaptchaAttributes() != null) {
                    for (Map.Entry<String, String> parameter : captchaPreValidationResponse.getCaptchaAttributes()
                            .entrySet()) {
                        servletRequest.setAttribute(parameter.getKey(), parameter.getValue());
                    }
                }
                doFilter(captchaPreValidationResponse, servletRequest, servletResponse, filterChain);
                return;
            }

            // Below the no. of max failed attempts, including the current attempt
            if (!captchaPreValidationResponse.isPostValidationRequired()
                    || (!captchaPreValidationResponse.isCaptchaValidationRequired()
                            && !captchaPreValidationResponse.isMaxFailedLimitReached())) {
                doFilter(captchaPreValidationResponse, servletRequest, servletResponse, filterChain);
                return;
            }

            CaptchaHttpServletResponseWrapper responseWrapper = new CaptchaHttpServletResponseWrapper(httpResponse);
            doFilter(captchaPreValidationResponse, servletRequest, responseWrapper, filterChain);

            CaptchaPostValidationResponse postValidationResponse = selectedCaptchaConnector.postValidate(servletRequest,
                    responseWrapper);

            // Check whether this attempt is failed
            if (postValidationResponse == null || postValidationResponse.isSuccessfulAttempt()) {
                if (responseWrapper.isRedirect()) {
                    httpResponse.sendRedirect(responseWrapper.getRedirectURL());
                }
                return;
            }

            if (postValidationResponse.isEnableCaptchaResponsePath() && responseWrapper.isRedirect()) {
                httpResponse.sendRedirect(CaptchaUtil.getUpdatedUrl(responseWrapper.getRedirectURL(),
                        postValidationResponse.getCaptchaAttributes()));
            }
        } catch (CaptchaException e) {
            log.error("处理验证码时出错。", e);
            ((HttpServletResponse) servletResponse).sendRedirect(CaptchaUtil.getErrorPage("服务器错误", "出了点问题。 请再试一次"));
        }
    }

    @Override
    public void destroy() {

    }

    private void doFilter(CaptchaPreValidationResponse preValidationResponse, ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (preValidationResponse.getWrappedHttpServletRequest() != null) {
            filterChain.doFilter(preValidationResponse.getWrappedHttpServletRequest(), servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
