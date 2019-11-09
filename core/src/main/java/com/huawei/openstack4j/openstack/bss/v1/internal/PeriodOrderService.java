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
import com.huawei.openstack4j.openstack.bss.v1.domain.periodOrder.*;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class PeriodOrderService extends BaseBusinessSupportSystemService
{

    /**
     * After a customer purchases yearly/monthly resources, it can query the orders in different statuses, such as in the pending approval, processing, canceled, completed, and pending payment statuses.
     * This API can be invoked using the customer AK/SK or token.
     * @param
     * @param domainID
     * @return
     */
    public QueryOrdersListRsp queryOrdersList(String domainID, Map<String, String> filteringParams)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");

        Invocation<QueryOrdersListRsp>
            queryOrdersListInvocation = get(QueryOrdersListRsp.class, uri("/%s/common/order-mgr/orders/detail", domainID));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                queryOrdersListInvocation = queryOrdersListInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return queryOrdersListInvocation.execute();
    }

    /**
     * A customer can invoke this API to query order bills.
     * This API can be invoked using the customer AK/SK or token.
     * @param
     * @param domainID
     * @return
     */
    public QueryOrderDetailRsp queryOrderDetail(String domainID, String orderId, Map<String, String> filteringParams)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(orderId), "parameter `orderId` should not be empty");
        Invocation<QueryOrderDetailRsp> queryOrderDetailInvocation = get(QueryOrderDetailRsp.class, uri("/%s/common/order-mgr/orders/%s", domainID, orderId));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                queryOrderDetailInvocation = queryOrderDetailInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return queryOrderDetailInvocation.execute();
    }

    /**
     * A customer can invoke this API to pay yearly-monthly product orders in the pending payment status.
     * This API can be invoked using the customer token only.
     * @param req
     * @param domainID
     * @return
     */
    public OrderPayRsp orderPay(String domainID, OrderPayReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getOrderId()), "parameter `orderId` should not be empty");
        return post(OrderPayRsp.class, uri("/%s/customer/order-mgr/order/pay", domainID)).entity(req).execute();
    }

    /**
     * A customer can invoke this API to unsubscribe from early-monthly product orders in the subscribed, changing, or failed to be provisioned status.
     * This API can be invoked using the customer token only.
     * @param
     * @param
     * @return
     */
    public UnsubscribeOrderRsp unsubscribeOrder(String domainID, String orderId, Map<String, String> filteringParams)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(orderId), "parameter `orderId` should not be empty");

        Invocation<UnsubscribeOrderRsp> unsubscribeOrderInvocation = delete(UnsubscribeOrderRsp.class, uri("/%s/customer/order-mgr/orders/%s", domainID, orderId));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                unsubscribeOrderInvocation = unsubscribeOrderInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return unsubscribeOrderInvocation.execute();
    }

    /**
     * A customer can invoke this API to cancel orders in the pending payment status.
     * This API can be invoked using the customer token only.
     * @param req
     * @param domainID
     * @return
     */
    public OrderActionsRsp orderActions(String domainID, Map<String, String> filteringParams, OrderActionsReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");

        Invocation<OrderActionsRsp>
            orderActionsInvocation = put(OrderActionsRsp.class, uri("/%s/customer/order-mgr/orders/actions", domainID));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                orderActionsInvocation = orderActionsInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return orderActionsInvocation.entity(req).execute();
    }

    /**
     * A customer can query the resources and original orders of the unsubscription amount for an unsubscription order or degrade order.
     * This API can be invoked using the AK/SK or token of the partner or the token of the partner's customer.
     * @param
     * @param domainID
     * @return
     */
    public QueryRefundOrderRsp queryRefundOrder(String domainID, Map<String, String> filteringParams)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");

        Invocation<QueryRefundOrderRsp> queryRefundOrderInvocation = get(QueryRefundOrderRsp.class, uri("/%s/common/order-mgr/orders/refund-order", domainID));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                queryRefundOrderInvocation = queryRefundOrderInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return queryRefundOrderInvocation.execute();
    }

    /**
     * A customer can query resource details and provisioning status of an order on the partner sales platform.
     * This API can be invoked using the customer token only.
     * @param
     * @param domainID
     * @return
     */
    public QueryResourcesRsp queryResources(String domainID, String orderId,  Map<String, String> filteringParams)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(orderId), "parameter `orderId` should not be empty");

        Invocation<QueryResourcesRsp> queryResourcesInvocation = get(QueryResourcesRsp.class, uri("/%s/common/order-mgr/orders-resource/%s", domainID, orderId));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                queryResourcesInvocation = queryResourcesInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return queryResourcesInvocation.execute();
    }
}
