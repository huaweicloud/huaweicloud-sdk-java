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
package com.huawei.openstack4j.api.bss.v1;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.bss.v1.domain.periodResource.*;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "bss/v1/periodResourceService")
public class PeriodResourceServiceTest extends AbstractTest
{
    @Override
    protected Service service() {
        return Service.BSSV1;
    }

    private static final String order_mgr_resources_detail= "/bss/periodResource/queryResourcesList.json";
    private static final String order_mgr_resources_renew= "/bss/periodResource/orderRenewByResourceId.json";
    private static final String order_mgr_resources_delete= "/bss/periodResource/orderDeleteByResourceId.json";
    private static final String order_mgr_resources_auto_renew= "/bss/periodResource/autoRenew.json";
    private static final String order_mgr_resources_cancel_auto_renew= "/bss/periodResource/cancelAutoRenew.json";

    @Test
    public void queryResourcesListTest() throws Exception{
        respondWith(order_mgr_resources_detail);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        String orderId = "CS1910091944Y0CQ1";
        Map<String, String> filteringParams = new HashMap<>();
        QueryResourcesListRsp rsp = osv3().bssV1()
            .periodResourceService()
            .queryResourcesList(userDomainId, filteringParams);

        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/common/order-mgr/resources/detail");
        assertEquals(request.getMethod(),"GET");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }

    @Test
    public void orderRenewByResourceIdTest() throws Exception{
        respondWith(order_mgr_resources_renew);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        List<String> resource_ids = new ArrayList<>();
        resource_ids.add("6a6a6a4c-df84-4521-8e41-2929ebf0bbce");
        OrderRenewByResourceIdReq
            req = OrderRenewByResourceIdReq.builder()
            .resourceIds(resource_ids)
            .periodType(2)
            .periodNum(1)
            .expireMode(3)
            .build();
        OrderRenewByResourceIdRsp rsp = osv3().bssV1().periodResourceService().orderRenewByResourceId(userDomainId, req);

        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/common/order-mgr/resources/renew");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }

    @Test
    public void orderDeleteByResourceIdTest() throws Exception{
        respondWith(order_mgr_resources_delete);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        List<String> resourceIds = new ArrayList<>();
        resourceIds.add("d89d6c4d-43cd-4765-b2cb-4264558a2336");
        OrderDeleteByResourceIdReq req = OrderDeleteByResourceIdReq.builder()
            .resourceIds(resourceIds)
            .unSubType(2)
            .unsubscribeReasonType(1)
            .unsubscribeReason("xxxxxx")
            .build();
        OrderDeleteByResourceIdRsp rsp =
            osv3().bssV1().periodResourceService().orderDeleteByResourceId(userDomainId, req);

        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/common/order-mgr/resources/delete");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }

    @Test
    public void autoRenewTest() throws Exception{
        respondWith(order_mgr_resources_auto_renew);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        String resourceId = "d89d6c4d-43cd-4765-b2cb-4264558a2336";
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("action_id", "autorenew");
        AutoRenewRsp rsp = osv3().bssV1().periodResourceService().autoRenew(userDomainId, resourceId, filteringParams);

        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/common/order-mgr/resources/d89d6c4d-43cd-4765-b2cb-4264558a2336/actions?action_id=autorenew");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }

    @Test
    public void cancelAutoRenewTest() throws Exception{
        respondWith(order_mgr_resources_cancel_auto_renew);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        String resourceId = "d89d6c4d-43cd-4765-b2cb-4264558a2336";
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("action_id", "autorenew");
        CancelAutoRenewRsp rsp =
            osv3().bssV1().periodResourceService().cancelAutoRenew(userDomainId, resourceId, filteringParams);

        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/common/order-mgr/resources/d89d6c4d-43cd-4765-b2cb-4264558a2336/actions?action_id=autorenew");
        assertEquals(request.getMethod(),"DELETE");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }
}
