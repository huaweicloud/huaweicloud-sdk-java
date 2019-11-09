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
import com.huawei.openstack4j.openstack.bssintl.v1.domain.realnameAuth.*;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class RealnameAuthService extends BaseBusinessSupportSystemIntlService
{
    /**
     * This API can be used to submit an individual real-name authentication application.
     * This API can be invoked only by the partner account AK/SK or token.
     * @param req
     * @param domainID
     * @return
     */
    public RealnameAuthRsp individualRealnameAuth(String domainID, RealnameAuthReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getCustomerId()), "parameter `customerId` should not be empty");
        checkArgument(!(null == req.getIdentifyType()), "parameter `identifyType` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getName()), "parameter `name` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getVerifiedNumber()), "parameter `verifiedNumber` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getXaccountType()), "parameter `xaccountType` should not be empty");
        checkArgument(!(null == req.getVerifiedFileURL()), "parameter `verifiedFileURL` should not be empty");
        return post(RealnameAuthRsp.class, uri("/%s/partner/customer-mgr/realname-auth/individual", domainID)).entity(req).execute();
    }

    /**
     * This API can be used to submit an enterprise real-name authentication application.
     * This API can be invoked only by the partner account AK/SK or token.
     * @param req
     * @param domainID
     * @return
     */
    public RealnameAuthRsp enterpriseRealnameAuth(String domainID, EnterpriseRealnameAuthReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getCustomerId()), "parameter `customerId` should not be empty");
        checkArgument(!(null == req.getIdentifyType()), "parameter `identifyType` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getCorpName()), "parameter `corpName` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getVerifiedNumber()), "parameter `verifiedNumber` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getXaccountType()), "parameter `xaccountType` should not be empty");
        checkArgument(!(null == req.getVerifiedFileURL()), "parameter `verifiedFileURL` should not be empty");
        return post(RealnameAuthRsp.class, uri("/%s/partner/customer-mgr/realname-auth/enterprise", domainID)).entity(req).execute();
    }

    /**
     * This API can be used to submit a real-name authentication change application.
     * This API can be invoked only by the partner account AK/SK or token.
     * @param req
     * @param domainID
     * @return
     */
    public RealnameAuthRsp enterpriseRealnameAuthChange(String domainID, EnterpriseRealnameAuthChangeReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getCustomerId()), "parameter `customerId` should not be empty");
        checkArgument(!(null == req.getIdentifyType()), "parameter `identifyType` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getCorpName()), "parameter `corpName` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getVerifiedNumber()), "parameter `verifiedNumber` should not be empty");
        checkArgument(!(null == req.getChangeType()), "parameter `changeType` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getXaccountType()), "parameter `xaccountType` should not be empty");
        checkArgument(!(null == req.getVerifiedFileURL()), "parameter `verifiedFileURL` should not be empty");
        return put(RealnameAuthRsp.class, uri("/%s/partner/customer-mgr/realname-auth/enterprise", domainID)).entity(req).execute();
    }

    /**
     * If the response to a real-name authentication application or real-name authentication change application indicates that manual review is required, this API can be used to query the review result.
     * This API can be invoked only by the partner account AK/SK or token.
     * @param
     * @param domainID
     * @return
     */
    public QueryRealnameAuthReviewResultRsp queryRealnameAuthReviewResult(String domainID, Map<String, String> filteringParams)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        BaseOpenStackService.Invocation<QueryRealnameAuthReviewResultRsp>
            queryRealnameAuthReviewResultInvocation = get(QueryRealnameAuthReviewResultRsp.class, uri("/%s/partner/customer-mgr/realname-auth/result", domainID));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                queryRealnameAuthReviewResultInvocation = queryRealnameAuthReviewResultInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return queryRealnameAuthReviewResultInvocation.execute();
    }
}
