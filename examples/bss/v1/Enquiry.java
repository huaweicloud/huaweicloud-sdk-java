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
import com.huawei.openstack4j.openstack.bss.v1.domain.enquiry.ProductInfoForQueryRating;
import com.huawei.openstack4j.openstack.bss.v1.domain.enquiry.QueryRatingReq;
import com.huawei.openstack4j.openstack.bss.v1.domain.enquiry.QueryRatingRsp;

import java.util.ArrayList;
import java.util.List;

public class Enquiry
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

    private static QueryRatingRsp queryRating(OSClient.OSClientV3 client, String domainID, QueryRatingReq req)
    {
        try {
            QueryRatingRsp rsp = client.bssV1().enquiryService().queryRating(domainID, req);
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
        OSClient.OSClientV3 osclient = getOsClientV3("xxx","xxxx", domainId);

//Querying Product Prices Based on the Product Specifications
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
        QueryRatingRsp rsp = queryRating(osclient, domainId, req);
    }
}
