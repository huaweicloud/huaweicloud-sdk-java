/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * 	use this file except in compliance with the License. You may obtain a copy of
 * 	the License at
 *
 * 	    http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * 	License for the specific language governing permissions and limitations under
 * 	the License.
 *******************************************************************************/
package com.huawei.openstack4j.openstack.bssintl.v1.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.utilities.SendVerificationCodeReq;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.utilities.SendVerificationCodeRsp;

import static com.google.common.base.Preconditions.checkArgument;

public class UtilitiesService extends BaseBusinessSupportSystemIntlService
{
    /**
     * If customers enter email addresses for registration, this API is used to send a registration verification code to the email addresses to verify the registration information.
     * This API can be invoked only by the partner AK/SK or token.
     * @param req
     * @param domainID
     * @return
     */
    public SendVerificationCodeRsp sendVerificationCode(String domainID, SendVerificationCodeReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!(null == req.getReceiverType()), "parameter `ReceiverType` should not be empty");
        return post(SendVerificationCodeRsp.class, uri("/%s/partner/common-mgr/verificationcode", domainID)).entity(req)
            .execute();
    }
}
