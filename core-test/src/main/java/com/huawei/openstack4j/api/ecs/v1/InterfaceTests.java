/*******************************************************************************
 * 	Copyright 2020 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.api.ecs.v1;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.ecs.v1.domain.InterfaceAttachment;
import com.huawei.openstack4j.openstack.ecs.v1.domain.InterfaceAttachment.InterfaceAttachments;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "ECS/Interface")
public class InterfaceTests extends AbstractTest {

    private static final String interface_list_result = "/ecs/interface_list_result.json";


    @Override
    protected Service service() {
        return Service.ECS;
    }

    @Test
    public void listTest() throws Exception {
        respondWith(interface_list_result);
        String serverId = "server-id";
        InterfaceAttachments interfaceAttachments = osv3().ecs().interfaces().list(serverId);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(), "/v1/project-id/cloudservers/server-id/os-interface");
        assertEquals(request.getMethod(), "GET");
        InterfaceAttachment interfaceAttachment = interfaceAttachments.getInterfaces().get(0);
        Assert.assertEquals("ACTIVE", interfaceAttachment.getPortState());
        Assert.assertEquals("b670eaf6-7e40-438a-868c-745ec8c9c7c6", interfaceAttachment.getNetworkId());
        Assert.assertEquals("392b4165-6f2f-4d87-9e91-6053d2b4fcb4", interfaceAttachment.getPortId());
        Assert.assertEquals("fa:16:3e:cb:c6:d5", interfaceAttachment.getMacAddr());
    }

}
