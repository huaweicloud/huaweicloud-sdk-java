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
import com.huawei.openstack4j.openstack.bssintl.v1.domain.utilities.SendVerificationCodeReq;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.utilities.SendVerificationCodeRsp;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "bssintl/v1/CustomerManagementService")
public class UtilitiesService extends AbstractTest
{
    @Override
    protected Service service() {
        return Service.BSS_INTLV1;
    }


    @Test
    public void sendVerificationCodeTest() throws Exception{
        respondWith(204);
        String userDomainId = "userDomainId" ;

        OSFactory.enableHttpLoggingFilter(true);

        SendVerificationCodeReq req =
            SendVerificationCodeReq.builder().receiverType(1).email("xxx@163.com").build();
        SendVerificationCodeRsp rsp = osv3().bssintlV1().utilitiesService().sendVerificationCode(userDomainId, req);

        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(),"/v1.0/userDomainId/partner/common-mgr/verificationcode");
        assertEquals(request.getMethod(),"POST");
    }
}
