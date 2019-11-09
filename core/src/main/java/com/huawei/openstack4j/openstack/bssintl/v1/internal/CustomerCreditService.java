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
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.customerCredit.QueryCreditRsp;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.customerCredit.SetCreditReq;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class CustomerCreditService extends BaseBusinessSupportSystemIntlService
{

    /**
     * This API can be used to query the budget of a customer for the partner to determine whether to adjust the budget.
     * This API can be invoked only by the partner account AK/SK or token.
     * @param
     * @param domainID
     * @return
     */
    public QueryCreditRsp queryCredit(String domainID, Map<String, String> filteringParams)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");

        BaseOpenStackService.Invocation<QueryCreditRsp>
            queryMonthlySumInvocation = get(QueryCreditRsp.class, uri("/%s/partner/account-mgr/credit", domainID));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                queryMonthlySumInvocation = queryMonthlySumInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return queryMonthlySumInvocation.execute();
    }

    /**
     * This API is used to set or adjust a customer's budget.
     * The api is only allowed to be called with the partner's AK/SK or Token.
     * @param req
     * @param domainID
     * @return
     */
    public ActionResponse setCredit(String domainID, SetCreditReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getCustomerId()), "parameter `customerId` should not be empty");
        checkArgument(!(null == req.getAdjustmentAmount()), "parameter `adjustmentAmount` should not be empty");
        checkArgument(!(null == req.getMeasureId()), "parameter `measureId` should not be empty");
        return postWithResponse(uri("/%s/partner/account-mgr/credit", domainID)).entity(req).execute();
    }
}
