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
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.customerCredit.QueryCreditRsp;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.customerCredit.SetCreditReq;

import java.util.HashMap;
import java.util.Map;

public class CustomerCredit
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

    private static QueryCreditRsp queryCredit(OSClient.OSClientV3 client, String domainID, Map<String, String> filteringParams)
    {
        try {
            QueryCreditRsp rsp = client.bssintlV1().customerCreditService().queryCredit(domainID, filteringParams);
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

    private static ActionResponse setCredit(OSClient.OSClientV3 client, String domainID, SetCreditReq req)
    {
        try {
            ActionResponse rsp = client.bssintlV1().customerCreditService().setCredit(domainID, req);
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
        OSClient.OSClientV3 osclient = getOsClientV3("xxx","xxxx", domainId);

//Querying Customers' Budgets
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("customer_id", "xxx");
        QueryCreditRsp queryCreditRsp = queryCredit(osclient, domainId, filteringParams);

//Setting Customers' Budgets
        SetCreditReq req = SetCreditReq.builder()
            .customerId("xxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            .adjustmentAmount(2019.19)
            .measureId(1)
            .build();
        ActionResponse setCreditRsp = setCredit(osclient, domainId, req);
    }
}
