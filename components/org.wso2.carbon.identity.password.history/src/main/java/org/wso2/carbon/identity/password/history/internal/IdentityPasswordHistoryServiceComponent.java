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

package org.wso2.carbon.identity.password.history.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;
import org.wso2.carbon.identity.governance.IdentityGovernanceService;
import org.wso2.carbon.identity.password.history.handler.PasswordHistoryValidationHandler;

/**
 * @scr.component name="org.wso2.carbon.identity.recovery.internal.IdentityRecoveryServiceComponent"
 *                immediate="true"
 * @scr.reference name="IdentityGovernanceService"
 *                interface="org.wso2.carbon.identity.governance.IdentityGovernanceService"
 *                cardinality="1..1" policy="dynamic"
 *                bind="setIdentityGovernanceService"
 *                unbind="unsetIdentityGovernanceService"
 */
public class IdentityPasswordHistoryServiceComponent {

    private static Log log = LogFactory.getLog(IdentityPasswordHistoryServiceComponent.class);

    protected void activate(ComponentContext context) {

        try {

            if (log.isDebugEnabled()) {
                log.debug("身份管理监听器已启用");
            }
            BundleContext bundleContext = context.getBundleContext();
            IdentityPasswordHistoryServiceDataHolder.getInstance().setBundleContext(bundleContext);

            PasswordHistoryValidationHandler handler = new PasswordHistoryValidationHandler();
            context.getBundleContext().registerService(AbstractEventHandler.class.getName(), handler, null);

        } catch (Exception e) {
            log.error("激活身份管理组件时出错。", e);
        }
    }

    protected void deactivate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("身份管理包已取消激活");
        }
    }

    protected void unsetIdentityGovernanceService(IdentityGovernanceService idpManager) {
        IdentityPasswordHistoryServiceDataHolder.getInstance().setIdentityGovernanceService(null);
    }

    protected void setIdentityGovernanceService(IdentityGovernanceService idpManager) {
        IdentityPasswordHistoryServiceDataHolder.getInstance().setIdentityGovernanceService(idpManager);
    }

}
