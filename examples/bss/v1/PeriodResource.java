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
import com.huawei.openstack4j.openstack.bss.v1.domain.periodResource.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeriodResource
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

    private static QueryResourcesListRsp queryResourcesList(OSClient.OSClientV3 client, String domainID, Map<String, String> filteringParams)
    {
        try {
            QueryResourcesListRsp rsp = client.bssV1().periodResourceService().queryResourcesList(domainID, filteringParams);
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

    private static OrderRenewByResourceIdRsp orderRenewByResourceId(OSClient.OSClientV3 client, String domainID,  OrderRenewByResourceIdReq req)
    {
        try {
            OrderRenewByResourceIdRsp rsp = client.bssV1().periodResourceService().orderRenewByResourceId(domainID, req);
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

    private static OrderDeleteByResourceIdRsp orderDeleteByResourceId(OSClient.OSClientV3 client, String domainID,  OrderDeleteByResourceIdReq req)
    {
        try {
            OrderDeleteByResourceIdRsp rsp = client.bssV1().periodResourceService().orderDeleteByResourceId(domainID, req);
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

    private static AutoRenewRsp autoRenew(OSClient.OSClientV3 client, String domainID, String resourceId,
        Map<String, String> filteringParams)
    {
        try
        {
            AutoRenewRsp rsp =
                client.bssV1().periodResourceService().autoRenew(domainID, resourceId, filteringParams);
            return rsp;
        }
        catch (ClientResponseException e1)
        {

            System.out.println("errorMsg: " + e1.getBody());
        }
        catch (ServerResponseException e2)
        {
            System.out.println("errorMsg: " + e2.getBody());;
        }

        return null;
    }

    private static CancelAutoRenewRsp cancelAutoRenew(OSClient.OSClientV3 client, String domainID, String resourceId, Map<String, String> filteringParams)
    {
        try {
            CancelAutoRenewRsp rsp = client.bssV1().periodResourceService().cancelAutoRenew(domainID, resourceId, filteringParams);
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

//Querying Customer's Yearly/Monthly Resources
        String orderId = "CS1910091944Y0CQ1";
        Map<String, String> filteringParams = new HashMap<>();
        //        filteringParams.put("resource_ids", "xxx");
        //        filteringParams.put("order_id", "xxx");
        //        filteringParams.put("only_main_resource", "xxx");
        //        filteringParams.put("status_list", "xxx");
        //        filteringParams.put("page_no", "xxx");
        //        filteringParams.put("page_size", "xxx");
        QueryResourcesListRsp queryResourcesListRsp = queryResourcesList(osclient, domainId, filteringParams);

//Renewing Subscription to Yearly/Monthly Resources
        List<String> resource_ids = new ArrayList<>();
        resource_ids.add("6a6a6a4c-df84-4521-8e41-2929ebf0bbce");
        OrderRenewByResourceIdReq
            orderRenewByResourceIdReq = OrderRenewByResourceIdReq.builder()
            .resourceIds(resource_ids)
            .periodType(2)
            .periodNum(1)
            .expireMode(3)
            .build();
        OrderRenewByResourceIdRsp orderRenewByResourceIdRsp = orderRenewByResourceId(osclient, domainId, orderRenewByResourceIdReq);

//Unsubscribing from Yearly/Monthly Resources
        List<String> resourceIds = new ArrayList<>();
        resourceIds.add("d89d6c4d-43cd-4765-b2cb-4264558a2336");
        OrderDeleteByResourceIdReq orderDeleteByResourceIdReq = OrderDeleteByResourceIdReq.builder()
            .resourceIds(resourceIds)
            .unSubType(2)
            .unsubscribeReasonType(1)
            .unsubscribeReason("xxxxxx")
            .build();
        OrderDeleteByResourceIdRsp orderDeleteByResourceIdRsp = orderDeleteByResourceId(osclient, domainId, orderDeleteByResourceIdReq);

//Enabling Automatic Subscription Renewal for Yearly/Monthly Resources
        String resourceIdForAutoRenew = "d89d6c4d-43cd-4765-b2cb-4264558a2336";
        Map<String, String> filteringParamsAutoRenew = new HashMap<>();
        filteringParamsAutoRenew.put("action_id", "autorenew");
        AutoRenewRsp autoRenewRsp = autoRenew(osclient, domainId, resourceIdForAutoRenew, filteringParamsAutoRenew);

//Disabling Automatic Subscription Renewal for Yearly/Monthly Resources
        String resourceId = "d89d6c4d-43cd-4765-b2cb-4264558a2336";
        Map<String, String> filteringParamsCancelAutoRenew = new HashMap<>();
        filteringParams.put("action_id", "autorenew");
        CancelAutoRenewRsp rsp = cancelAutoRenew(osclient, domainId, resourceId, filteringParamsCancelAutoRenew);
    }
}
