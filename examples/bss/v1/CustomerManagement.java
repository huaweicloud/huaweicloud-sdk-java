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
package com.huawei.openstack.sample.bss.v1;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.exceptions.ClientResponseException;
import com.huawei.openstack4j.api.exceptions.ServerResponseException;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.bss.v1.domain.customerManagement.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerManagement
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

    private static CheckUserRsp checkUser(OSClient.OSClientV3 client, String domainID, CheckUserReq req)
    {
        try {
            CheckUserRsp rsp = client.bssV1().customerManagementService().checkUser(domainID, req);
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

    private static CreateCustomerRsp createCustomer(OSClient.OSClientV3 client, String domainID, CreateCustomerReq req)
    {
        try {
            CreateCustomerRsp rsp = client.bssV1().customerManagementService().createCustomer(domainID, req);
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

    private static QueryCustomerInfoListRsp queryCustomerInfoList(OSClient.OSClientV3 client, String domainID, QueryCustomerInfoListReq req)
    {
        try {
            QueryCustomerInfoListRsp rsp = client.bssV1().customerManagementService().queryCustomerInfoList(domainID, req);
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
        //create connection
        String domainId = "xxx";
        OSClient.OSClientV3 osclient = getOsClientV3("xxx", "xxxx", domainId);

        //Verifying Customer Registration Information
        CheckUserReq checkUserReq = CheckUserReq.builder().searchType("mobile").searchKey("0086-156xxxxxx97").build();
        CheckUserRsp checkUserRsp = checkUser(osclient, domainId, checkUserReq);

        //Creating a Customer
        CreateCustomerReq createCustomerReq = CreateCustomerReq.builder()
            .xAccountId("xxxxxxxx")
            .xAccountType("xxxxxxxxxxe_IDP")
            .domainName("xxxxxx")
            .password("xxxxxx")
            .build();
        CreateCustomerRsp createCustomerRsp = createCustomer(osclient, domainId, createCustomerReq);

        //Querying Customers
        QueryCustomerInfoListReq queryCustomerInfoListReq = QueryCustomerInfoListReq.builder()
            .cooperationTimeStart("2019-05-01T00:01:00Z")
            .cooperationTimeEnd("2019-12-01T00:01:00Z")
            .build();
        QueryCustomerInfoListRsp queryCustomerInfoListRsp =
            queryCustomerInfoList(osclient, domainId, queryCustomerInfoListReq);
    }
}
