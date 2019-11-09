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
package com.huawei.openstack4j.openstack.bss.v1.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.bss.v1.domain.bill.QueryMonthlySumRsp;
import com.huawei.openstack4j.openstack.bss.v1.domain.bill.QueryResFeeRecordRsp;
import com.huawei.openstack4j.openstack.bss.v1.domain.bill.QueryResRecordRsp;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class BillService extends BaseBusinessSupportSystemService
{

    /**
     * This API can be used to query the expenditure summary bills of a customer on the customer platform. The bills summarize the summary data by month. The data of the previous day is updated once a day.
     * This API can be invoked using the customer AK/SK or token only.
     * @param
     * @param domainID
     * @return
     */
    public QueryMonthlySumRsp queryMonthlySum(String domainID, Map<String, String> filteringParams)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");

        Invocation<QueryMonthlySumRsp> queryMonthlySumInvocation = get(QueryMonthlySumRsp.class, uri("/%s/customer/account-mgr/bill/monthly-sum", domainID));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                queryMonthlySumInvocation = queryMonthlySumInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return queryMonthlySumInvocation.execute();
    }

    /**
     * This API can be used to query usage details of each resource for a customer on the customer platform. The resource details have a latency (a maximum of 24 hours).
     * This API can be invoked using the customer AK/SK or token only.
     * @param
     * @param domainID
     * @return
     */
    public QueryResRecordRsp queryResRecord(String domainID, Map<String, String> filteringParams)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");

        Invocation<QueryResRecordRsp> queryResRecordInvocation = get(QueryResRecordRsp.class, uri("/%s/customer/account-mgr/bill/res-records", domainID));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                queryResRecordInvocation = queryResRecordInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return queryResRecordInvocation.execute();
    }

    /**
     * This API can be used to query the usage details of each resource for a customer on the customer platform.
     * This API can be invoked using the customer AK/SK or token only.
     * @param
     * @param domainID
     * @return
     */
    public QueryResFeeRecordRsp queryResFeeRecord(String domainID, Map<String, String> filteringParams)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");

        Invocation<QueryResFeeRecordRsp> queryResFeeRecordInvocation = get(
            QueryResFeeRecordRsp.class, uri("/%s/customer/account-mgr/bill/res-fee-records", domainID));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                queryResFeeRecordInvocation = queryResFeeRecordInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return queryResFeeRecordInvocation.execute();
    }
}
