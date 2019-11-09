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
package com.huawei.openstack4j.api.bssintl.v1;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.periodOrder.*;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "bssintl/v1/PeriodOrderService")
public class PeriodOrderServiceTest extends AbstractTest
{
    @Override
    protected Service service() {
        return Service.BSS_INTLV1;
    }

    private static final String order_mgr_orders_List= "/bssintl/periodOrder/queryOrdersList.json";
    private static final String order_mgr_orders_detail= "/bssintl/periodOrder/queryOrdersDetail.json";
    private static final String order_mgr__order_pay= "/bssintl/periodOrder/orderPay.json";
    private static final String order_mgr__order_unsubscribe= "/bssintl/periodOrder/unsubscribeOrder.json";
    private static final String order_mgr__order_cancel= "/bssintl/periodOrder/orderActions.json";
    private static final String order_mgr__order_refund_order= "/bssintl/periodOrder/queryRefundOrder.json";
    private static final String order_mgr__order_refund_resource= "/bssintl/periodOrder/queryRefundOrder.json";

    @Test
    public void queryOrdersListTest() throws Exception{
        respondWith(order_mgr_orders_List);
        String userDomainId = "userDomainId" ;

        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("page_size", "10");
        filteringParams.put("page_index", "1");
        QueryOrdersListRsp rsp =
            osv3().bssintlV1().periodOrderService().queryOrdersList(userDomainId, filteringParams);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/common/order-mgr/orders/detail?page_index=1&page_size=10");
        assertEquals(request.getMethod(),"GET");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }

    @Test
    public void queryOrderDetailTest() throws Exception{
        respondWith(order_mgr_orders_detail);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("limit", "10");
        filteringParams.put("offset", "1");
        String orderId = "CS18033113409QLBB";
        QueryOrderDetailRsp rsp =
            osv3().bssintlV1().periodOrderService().queryOrderDetail(userDomainId, orderId, filteringParams);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/common/order-mgr/orders/CS18033113409QLBB?offset=1&limit=10");
        assertEquals(request.getMethod(),"GET");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }

    @Test
    public void orderPayTest() throws Exception{
        respondWith(order_mgr__order_pay);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        OrderPayReq req = OrderPayReq.builder().orderId("CS1910091944Y0CQ1")
            .build();
        OrderPayRsp rsp = osv3().bssintlV1().periodOrderService().orderPay(userDomainId, req);

        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/customer/order-mgr/order/pay");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }

    @Test
    public void unsubscribeOrderTest() throws Exception{
        respondWith(order_mgr__order_unsubscribe);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        String orderId = "CS1910091944Y0CQ1";
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("unsub_type", "3");
        UnsubscribeOrderRsp rsp =
            osv3().bssintlV1().periodOrderService().unsubscribeOrder(userDomainId, orderId, filteringParams);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/customer/order-mgr/orders/CS1910091944Y0CQ1?unsub_type=3");
        assertEquals(request.getMethod(),"DELETE");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }

    @Test
    public void orderActionsTest() throws Exception{
        respondWith(order_mgr__order_cancel);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("action_id", "cancel");
        OrderActionsReq req = OrderActionsReq.builder().orderId("CS191011202178E9V").build();

        OrderActionsRsp rsp = osv3().bssintlV1().periodOrderService().orderActions(userDomainId, filteringParams, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/customer/order-mgr/orders/actions?action_id=cancel");
        assertEquals(request.getMethod(),"PUT");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }

    @Test
    public void queryRefundOrderTest() throws Exception{
        respondWith(order_mgr__order_refund_order);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("order_id", "CS1910091944Y0CQ1");

        QueryRefundOrderRsp rsp = osv3().bssintlV1().periodOrderService().queryRefundOrder(userDomainId, filteringParams);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/common/order-mgr/orders/refund-order?order_id=CS1910091944Y0CQ1");
        assertEquals(request.getMethod(),"GET");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }

    @Test
    public void queryResourcesTest() throws Exception{
        respondWith(order_mgr__order_refund_resource);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        String orderId = "CS1910091944Y0CQ1";
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("offset", "1");
        filteringParams.put("limit", "10");
        QueryResourcesRsp
            rsp = osv3().bssintlV1().periodOrderService().queryResources(userDomainId, orderId, filteringParams);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/common/order-mgr/orders-resource/CS1910091944Y0CQ1?offset=1&limit=10");
        assertEquals(request.getMethod(),"GET");
    }
}
