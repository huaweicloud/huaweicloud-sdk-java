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
package com.huawei.openstack4j.api.compute.ext;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.compute.InterfaceAttachment;
import com.huawei.openstack4j.openstack.compute.domain.FixedIp;
import com.huawei.openstack4j.openstack.compute.domain.NovaInterfaceAttachmentCreate;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.annotations.Test;
import org.testng.collections.Lists;


import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Tests cases for the os-interface Compute API.
 */
@Test(suiteName = "Compute")
public class InterfaceTests extends AbstractTest {

    private static final String JSON_INTERFACEATTACHMENT_CREATE = "/compute/ext/interface_attachment_create.json";
    private static final String JSON_INTERFACEATTACHMENT_CREATE_REQUEST =
            "/compute/ext/interface_attachment_create_request.json";

    @Test
    public void createInterfaceAttachment() throws Exception {
        respondWith(JSON_INTERFACEATTACHMENT_CREATE);

        FixedIp fixedIp = FixedIp.builder().ipAddress("ip-address").build();
        List<FixedIp> fixedIps = Lists.newArrayList();
        fixedIps.add(fixedIp);
        NovaInterfaceAttachmentCreate build = NovaInterfaceAttachmentCreate.builder()
                .netId("net-id").fixedIps(fixedIps).build();
        InterfaceAttachment created = osv3().compute().servers().interfaces()
                .create("server-id", build);
        assertEquals("net-id", created.getNetId());
        assertEquals("ip-address", created.getFixedIps().get(0).getIpAddress());

        RecordedRequest request = takeRequest();
        String body = request.getBody().readUtf8();
        String expectBody = this.getResource(JSON_INTERFACEATTACHMENT_CREATE_REQUEST);
        assertEquals(body, expectBody);
    }

    @Override
    protected Service service() {
        return Service.COMPUTE;
    }
}
