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
import com.huawei.openstack4j.openstack.bss.v1.domain.periodOrder.*;

import java.util.HashMap;
import java.util.Map;

public class PeriodOrder
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

    private static QueryOrdersListRsp queryOrdersList(OSClient.OSClientV3 client, String domainID, Map<String, String> filteringParams)
    {
        try {
            QueryOrdersListRsp rsp = client.bssV1().periodOrderService().queryOrdersList(domainID, filteringParams);
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

    private static QueryOrderDetailRsp queryOrderDetail(OSClient.OSClientV3 client, String domainID, String orderId, Map<String, String> filteringParams)
    {
        try {
            QueryOrderDetailRsp rsp = client.bssV1().periodOrderService().queryOrderDetail(domainID, orderId, filteringParams);
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


    private static OrderPayRsp orderPay(OSClient.OSClientV3 client, String domainID, OrderPayReq req)
    {
        try {
            OrderPayRsp rsp = client.bssV1().periodOrderService().orderPay(domainID, req);
            return rsp;
        }
        catch (ClientResponseException e1)
        {

            System.out.println("errorMsg: " + e1.getBody());
        }
        catch (ServerResponseException e2)
        {

            System.out.println("errorMsg: " + e2.getBody());
        }

        return null;
    }

    private static UnsubscribeOrderRsp unsubscribeOrder(OSClient.OSClientV3 client, String domainID, String orderId, Map<String, String> filteringParams)
    {
        try {
            UnsubscribeOrderRsp rsp = client.bssV1().periodOrderService().unsubscribeOrder(domainID, orderId, filteringParams);
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

    private static OrderActionsRsp orderActions(OSClient.OSClientV3 client, String domainID, Map<String, String> filteringParams, OrderActionsReq req)
    {
        try {
            OrderActionsRsp rsp = client.bssV1().periodOrderService().orderActions(domainID, filteringParams, req);
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

    private static QueryRefundOrderRsp queryRefundOrder(OSClient.OSClientV3 client, String domainID, Map<String, String> filteringParams)
    {
        try {
            QueryRefundOrderRsp rsp = client.bssV1().periodOrderService().queryRefundOrder(domainID, filteringParams);
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

    private static QueryResourcesRsp queryResources(OSClient.OSClientV3 client, String domainID, String orderId, Map<String, String> filteringParams)
    {
        try {
            QueryResourcesRsp rsp = client.bssV1().periodOrderService().queryResources(domainID, orderId, filteringParams);
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

//Querying Orders
        Map<String, String> filteringParamsQueryOrdersList = new HashMap<>();
        filteringParamsQueryOrdersList.put("page_size", "10");
        filteringParamsQueryOrdersList.put("page_index", "1");
        //        filteringParamsQueryOrdersList.put("order_id", "xxx");
        //        filteringParamsQueryOrdersList.put("customer_id", "xxx");
        //        filteringParamsQueryOrdersList.put("create_time_begin", "2019-05-06T08:05:01Z");
        //        filteringParamsQueryOrdersList.put("create_time_end", "2019-06-06T08:05:01Z");
        //        filteringParamsQueryOrdersList.put("service_type", "xxx");
        //        filteringParamsQueryOrdersList.put("status", "xxx");
        //        filteringParamsQueryOrdersList.put("order_type", "xxx");
        //        filteringParamsQueryOrdersList.put("sort", "xxx");
        //        filteringParamsQueryOrdersList.put("payment_time_begin", "2019-05-06T08:05:01Z");
        //        filteringParamsQueryOrdersList.put("payment_time_end", "2019-05-06T08:05:01Z");
        QueryOrdersListRsp rsp = queryOrdersList(osclient, domainId, filteringParamsQueryOrdersList);

//Querying Order Details
        String orderIdForPay = "CS18033113409QLBB";
        Map<String, String> filteringParamsQueryOrderDetail = new HashMap<>();
        //        filteringParamsQueryOrderDetail.put("limit", "10");
        //        filteringParamsQueryOrderDetail.put("offset", "1");
        QueryOrderDetailRsp queryOrderDetailRsp = queryOrderDetail(osclient, domainId, orderIdForPay, filteringParamsQueryOrderDetail);

//Paying Yearly-Monthly Product Orders
        OrderPayReq orderPayReq = OrderPayReq.builder().orderId("CS1910091944Y0CQ1").build();
        OrderPayRsp orderPayRsp = orderPay(osclient, domainId, orderPayReq);

//Unsubscribing from Yearly/Monthly Products That Fail to Be Provisioned or Changed
        String orderIdForUnsubcribe = "CS1910091944Y0CQ1";
        Map<String, String> filteringParamsUnsubscribeOrder = new HashMap<>();
        filteringParamsUnsubscribeOrder.put("unsub_type", "3");
        //        filteringParamsUnsubscribeOrder.put("unsubscribe_reason_type", "xxx");
        //        filteringParamsUnsubscribeOrder.put("unsubscribe_reason", "xxx");
        UnsubscribeOrderRsp unsubscribeOrderRsp = unsubscribeOrder(osclient, domainId, orderIdForUnsubcribe, filteringParamsUnsubscribeOrder);

//Canceling Orders in the Pending Payment Status
        Map<String, String> filteringParamsOrderActions = new HashMap<>();
        filteringParamsOrderActions.put("action_id", "cancel");
        OrderActionsReq orderActionsReq = OrderActionsReq.builder().orderId("CS191011202178E9V").build();
        OrderActionsRsp orderActionsRsp = orderActions(osclient, domainId, filteringParamsOrderActions, orderActionsReq);

//Querying the Amount of Unsubscription Orders
        Map<String, String> filteringParamsQueryRefundOrder = new HashMap<>();
        filteringParamsQueryRefundOrder.put("order_id", "CS1910091944Y0CQ1");
        QueryRefundOrderRsp queryRefundOrderRsp = queryRefundOrder(osclient, domainId, filteringParamsQueryRefundOrder);

//Querying Resource Provisioning Details of an Order
        String orderId = "CS1910091944Y0CQ1";
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("offset", "1");
        filteringParams.put("limit", "10");
        QueryResourcesRsp queryResourcesRsp = queryResources(osclient, domainId, orderId, filteringParams);
    }
}
