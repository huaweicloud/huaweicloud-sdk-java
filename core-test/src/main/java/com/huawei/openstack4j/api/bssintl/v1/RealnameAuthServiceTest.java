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
import com.huawei.openstack4j.openstack.bssintl.v1.domain.realnameAuth.*;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "bssintl/v1/RealnameAuthService")
public class RealnameAuthServiceTest extends AbstractTest
{
    @Override
    protected Service service() {
        return Service.BSS_INTLV1;
    }

    private static final String realname_individual_realname_auth= "/bssintl/realnameAuth/individualRealnameAuth.json";
    private static final String realname_enterprise_realname_auth= "/bssintl/realnameAuth/enterpriseRealnameAuth.json";
    private static final String realname_enterprise_realname_change_auth= "/bssintl/realnameAuth/enterpriseRealnameAuth.json";
    private static final String realname_auth_review_result= "/bssintl/realnameAuth/enterpriseRealnameAuth.json";


    @Test
    public void individualRealnameAuthTest() throws Exception{
        respondWith(realname_individual_realname_auth);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        List<String> verifiedFileURL = new ArrayList<>();
        verifiedFileURL.add("zhengmian.jpg");
        verifiedFileURL.add("fanmian.jpg");
        verifiedFileURL.add("chizheng2.jpg");

        RealnameAuthReq req = RealnameAuthReq.builder()
            .customerId("xxxxxxxxxxxxxxxxxx")
            .identifyType(0)
            .verifiedType(0)
            .verifiedFileURL(verifiedFileURL)
            .name("xxx")
            .verifiedNumber("xxxxxxxxxxxxxxxxxx")
            .changeType(-1)
            .xaccountType("xxxxxxxxx_IDP")
            .build();

        RealnameAuthRsp rsp = osv3().bssintlV1().realnameAuthService().individualRealnameAuth(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/realname-auth/individual");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getIsReview().toString(),"1");
    }

    @Test
    public void enterpriseRealnameAuthTest() throws Exception{
        respondWith(realname_enterprise_realname_auth);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        List<String> verifiedFileURL = new ArrayList<>();
        verifiedFileURL.add("zhengmian.jpg");
        verifiedFileURL.add("fanmian.jpg");
        verifiedFileURL.add("chizheng2.jpg");

        EnterprisePerson enterprisePerson = new EnterprisePerson();
        enterprisePerson.setLegelName("xxxxxxx");
        enterprisePerson.setLegelIdNumber("xxxxxxxxxxxxxxxxxx");
        enterprisePerson.setCertifierRole("legalPerson");

        EnterpriseRealnameAuthReq req = EnterpriseRealnameAuthReq.builder()
            .customerId("xxxxxxxxxxxxxxxxxxx")
            .identifyType(1)
            .certificateType(0)
            .verifiedFileURL(verifiedFileURL)
            .corpName("xxxxxxx")
            .verifiedNumber("xxxxxx")
            .regCountry("CN")
            .regAddress("nanjing")
            .xaccountType("xxxxxxxxxx_IDP")
            .enterprisePerson(enterprisePerson)
            .build();

        RealnameAuthRsp rsp = osv3().bssintlV1().realnameAuthService().enterpriseRealnameAuth(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/realname-auth/enterprise");
        assertEquals(request.getMethod(),"POST");
        assertEquals(rsp.getIsReview().toString(),"1");
    }

    @Test
    public void enterpriseRealnameAuthChangeTest() throws Exception{
        respondWith(realname_enterprise_realname_change_auth);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        List<String> verifiedFileURL = new ArrayList<>();
        verifiedFileURL.add("zhengmian.jpg");
        verifiedFileURL.add("fanmian.jpg");
        verifiedFileURL.add("chizheng2.jpg");

        EnterprisePerson enterprisePerson = new EnterprisePerson();
        enterprisePerson.setLegelName("xxxxx");
        enterprisePerson.setLegelIdNumber("xxxxxxxxxxxxxxxxxx");
        enterprisePerson.setCertifierRole("legalPerson");

        EnterpriseRealnameAuthChangeReq req = EnterpriseRealnameAuthChangeReq.builder()
            .customerId("xxxxxxxxxxxxxxxxxxx")
            .identifyType(1)
            .certificateType(0)
            .verifiedFileURL(verifiedFileURL)
            .corpName("xxxxxxxxxxxx")
            .verifiedNumber("xxxxxx")
            .regCountry("CN")
            .regAddress("nanjing")
            .changeType(1)
            .xaccountType("xxxxxxxxxxxx_IDP")
            .enterprisePerson(enterprisePerson)
            .build();

        RealnameAuthRsp rsp = osv3().bssintlV1().realnameAuthService().enterpriseRealnameAuthChange(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/realname-auth/enterprise");
        assertEquals(request.getMethod(),"PUT");
        assertEquals(rsp.getIsReview().toString(),"1");
    }

    @Test
    public void queryRealnameAuthReviewResultTest() throws Exception{
        respondWith(realname_auth_review_result);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("customerId", "xxxxxxxxxxxxxxxxxxxxx");
        QueryRealnameAuthReviewResultRsp
            rsp = osv3().bssintlV1().realnameAuthService().queryRealnameAuthReviewResult(userDomainId, filteringParams);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/customer-mgr/realname-auth/result?customerId=xxxxxxxxxxxxxxxxxxxxx");
        assertEquals(request.getMethod(),"GET");
    }
}
