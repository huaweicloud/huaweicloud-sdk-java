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
import com.huawei.openstack4j.openstack.bss.v1.domain.resource.QueryCustomerResourceReq;
import com.huawei.openstack4j.openstack.bss.v1.domain.resource.QueryCustomerResourceRsp;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "bss/v1/PayPerUseResourceService")
public class PayPerUseResourceServiceTest extends AbstractTest
{
    @Override
    protected Service service() {
        return Service.BSSV1;
    }

    private static final String customer_resource_query_resources= "/bss/resource/queryCustomerResource.json";


    @Test
    public void queryCustomerResourceTest() throws Exception{
        respondWith(customer_resource_query_resources);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        QueryCustomerResourceReq req =
            QueryCustomerResourceReq.builder().customerId("xxxxxxxxxxxxxxxxxxxxxxx").build();
        QueryCustomerResourceRsp rsp =
            osv3().bssV1().payPerUseResourceService().queryCustomerResource(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/customer-resource/query-resources");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }
}
