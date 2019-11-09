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
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.customerCredit.QueryCreditRsp;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.customerCredit.SetCreditReq;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "bssintl/v1/CustomerCreditService")
public class CustomerCreditServiceTest extends AbstractTest
{
    @Override
    protected Service service() {
        return Service.BSS_INTLV1;
    }

    private static final String account_mgr_credit_query= "/bssintl/customerCredit/queryCredit.json";

    @Test
    public void queryCreditTest() throws Exception{
        respondWith(account_mgr_credit_query);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("customer_id", "0685b4653c00264a0f30c00015c89920");
        QueryCreditRsp rsp = osv3().bssintlV1().customerCreditService().queryCredit(userDomainId, filteringParams);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/account-mgr/credit?customer_id=0685b4653c00264a0f30c00015c89920");
        assertEquals(request.getMethod(),"GET");
    }

    @Test
    public void setCreditTest() throws Exception{
        respondWith(204);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        SetCreditReq req = SetCreditReq.builder()
            .customerId("xxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            .adjustmentAmount(2000.19)
            .measureId(1)
            .build();
        ActionResponse rsp = osv3().bssintlV1().customerCreditService().setCredit(userDomainId, req);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/account-mgr/credit");
        assertEquals(request.getMethod(),"POST");
    }
}
