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
import com.huawei.openstack4j.openstack.bss.v1.domain.enquiry.ProductInfoForQueryRating;
import com.huawei.openstack4j.openstack.bss.v1.domain.enquiry.QueryRatingReq;
import com.huawei.openstack4j.openstack.bss.v1.domain.enquiry.QueryRatingRsp;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "bss/v1/EnquiryService")
public class EnquiryServiceTest extends AbstractTest
{
    @Override
    protected Service service() {
        return Service.BSSV1;
    }

    private static final String product_mgr_query_rating= "/bss/enquiry/queryRating.json";

    @Test
    public void queryRatingTest() throws Exception{
        respondWith(product_mgr_query_rating);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        List<ProductInfoForQueryRating> productInfos = new ArrayList<ProductInfoForQueryRating>();
        ProductInfoForQueryRating productInfo = new ProductInfoForQueryRating();
        productInfo.setId("1234");
        productInfo.setCloudServiceType("hws.service.type.ec2");
        productInfo.setResourceType("hws.resource.type.vm");
        productInfo.setResourceSpecCode("s2.small.1.linux");
        productInfos.add(productInfo);

        QueryRatingReq req = QueryRatingReq.builder()
            .tenantId("631c087e85264a78bd85d7a8b5975945")
            .regionId("cn-north-1")
            .chargingMode(0)
            .periodType(2)
            .periodNum(1)
            .subscriptionNum(1)
            .productInfos(productInfos)
            .build();
        QueryRatingRsp rsp = osv3().bssV1().enquiryService().queryRating(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/customer/product-mgr/query-rating");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getErrorCode(), "CBC.0000");
    }
}
