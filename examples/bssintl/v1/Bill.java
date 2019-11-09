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
package com.huawei.openstack.sample.bssintl.v1;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.exceptions.ClientResponseException;
import com.huawei.openstack4j.api.exceptions.ServerResponseException;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.bill.QueryMonthlySumRsp;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.bill.QueryPostpaidBillRsp;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.bill.QueryResFeeRecordRsp;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.bill.QueryResRecordRsp;

import java.util.HashMap;
import java.util.Map;

public class Bill
{
    private static OSClient.OSClientV3 getOsClientV3(String username, String password, String userDomainId) {
        String authUrl = "https://iam.xxx.com/v3";
        OSFactory.enableHttpLoggingFilter(true);
        // create connection
        return OSFactory.builderV3()
            .endpoint(authUrl)
            .credentials(username, password, Identifier.byId(userDomainId))
            .scopeToDomain(Identifier.byId(userDomainId))
            .authenticate();
    }

    private static QueryPostpaidBillRsp queryPostpaidBill(OSClient.OSClientV3 client, String domainID, Map<String, String> filteringParams)
    {
        try {
            QueryPostpaidBillRsp rsp = client.bssintlV1().billService().queryPostpaidBill(domainID, filteringParams);
            return rsp;
        }
        catch (ClientResponseException e1)
        {
            System.out.println("errorMsg: " + e1.getBody());;
        }
        catch (ServerResponseException e2)
        {
            System.out.println("errorMsg: " + e2.getBody());;
        }

        return null;
    }

    private static QueryMonthlySumRsp queryMonthlySum(OSClient.OSClientV3 client, String domainID, Map<String, String> filteringParams)
    {
        try {
            QueryMonthlySumRsp rsp = client.bssintlV1().billService().queryMonthlySum(domainID, filteringParams);
            return rsp;
        }
        catch (ClientResponseException e1)
        {
            System.out.println("errorMsg: " + e1.getBody());;
        }
        catch (ServerResponseException e2)
        {
            System.out.println("errorMsg: " + e2.getBody());;
        }

        return null;
    }

    private static QueryResRecordRsp queryResRecord(OSClient.OSClientV3 client, String domainID, Map<String, String> filteringParams)
    {
        try {
            QueryResRecordRsp rsp = client.bssintlV1().billService().queryResRecord(domainID, filteringParams);
            return rsp;
        }
        catch (ClientResponseException e1)
        {
            System.out.println("errorMsg: " + e1.getBody());;
        }
        catch (ServerResponseException e2)
        {
            System.out.println("errorMsg: " + e2.getBody());;
        }

        return null;
    }


    private static QueryResFeeRecordRsp queryResFeeRecord(OSClient.OSClientV3 client, String domainID, Map<String, String> filteringParams)
    {
        try {
            QueryResFeeRecordRsp rsp = client.bssintlV1().billService().queryResFeeRecord(domainID, filteringParams);
            return rsp;
        }
        catch (ClientResponseException e1)
        {
            System.out.println("errorMsg: " + e1.getBody());;
        }
        catch (ServerResponseException e2)
        {
            System.out.println("errorMsg: " + e2.getBody());;
        }

        return null;
    }

    public static void main(String[] args)
    {
// create connection
        String domainId = "xxx";
        OSClient.OSClientV3 osclient = getOsClientV3("xxx","xxxxx", domainId);

//Querying Monthly Bills
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("consume_month","2019-09");
        QueryPostpaidBillRsp rsp = queryPostpaidBill(osclient, domainId, filteringParams);
        System.out.println(rsp);

//Viewing Expenditure Summary
        Map<String, String> filteringParamsQueryMonthlySum = new HashMap<>();
        filteringParamsQueryMonthlySum.put("cycle", "2019-09");
        //        filteringParamsQueryMonthlySum.put("cloud_service_type_code", "cloud_service_type_code");
        //        filteringParamsQueryMonthlySum.put("type", "type");
        //        filteringParamsQueryMonthlySum.put("enterpriseProjectId", "enterpriseProjectId");
        QueryMonthlySumRsp queryMonthlySumRsp = queryMonthlySum(osclient, domainId, filteringParamsQueryMonthlySum);

//Viewing Resource Usage Details
        Map<String, String> filteringParamsQueryResRecord = new HashMap<>();
        filteringParamsQueryResRecord.put("cycle", "2019-03");
        filteringParamsQueryResRecord.put("payMethod", "0");
        filteringParamsQueryResRecord.put("offset", "1");
        filteringParamsQueryResRecord.put("limit", "10");
        //        filteringParamsQueryResRecord.put("cloudServiceTypeCode", "xxx");
        //        filteringParamsQueryResRecord.put("resourceTypeCode", "xxx");
        //        filteringParamsQueryResRecord.put("regionCode", "xxx");
        //        filteringParamsQueryResRecord.put("resInstanceId", "xxx");
        //        filteringParamsQueryResRecord.put("enterpriseProjectId", "xxx");
        QueryResRecordRsp queryResRecordRsp = queryResRecord(osclient, domainId, filteringParamsQueryResRecord);

//Viewing Resource Expenditures
        Map<String, String> filteringParamsQueryResFeeRecord = new HashMap<>();
        filteringParamsQueryResFeeRecord.put("startTime", "2019-08-01");
        filteringParamsQueryResFeeRecord.put("endTime", "2019-08-31");
        filteringParamsQueryResFeeRecord.put("payMethod", "0");
        //        filteringParamsQueryResFeeRecord.put("cloudServiceTypeCode", cloudServiceTypeCode);
        //        filteringParamsQueryResFeeRecord.put("regionCode", regionCode);
        //        filteringParamsQueryResFeeRecord.put("orderId", orderId);
        //        filteringParamsQueryResFeeRecord.put("offset", offset);
        //        filteringParamsQueryResFeeRecord.put("limit", limit);
        //        filteringParamsQueryResFeeRecord.put("resourceId", resourceId);
        //        filteringParamsQueryResFeeRecord.put("enterpriseProjectId", enterpriseProjectId);
        QueryResFeeRecordRsp queryResFeeRecordRsp = queryResFeeRecord(osclient, domainId, filteringParamsQueryResFeeRecord);
    }
}
