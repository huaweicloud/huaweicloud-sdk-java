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
package com.huawei.openstack4j.api.ecs.v1;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.ecs.v1.domain.CloudServerTag;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ProjectTag;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ServerTags;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "ECS/Tag")
public class TagTests extends AbstractTest {

    private static final String cloud_server_tag_result = "/ecs/cloud_server_tag_result.json";
    private static final String project_tag_result = "/ecs/project_tag_result.json";

    @Override
    protected Service service() {
        return Service.ECS;
    }

    @Test
    public void addTest() throws Exception {
        respondWith(200);
        List<ServerTags> serverTags = new ArrayList<>();
        serverTags.add(ServerTags.builder().key("key1").value("value1").build());
        String serverId = "server-id";
        ActionResponse actionResponse = osv3().ecs().tags().add(serverId, serverTags);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(), "/v1/project-id/cloudservers/server-id/tags/action");
        assertEquals(request.getMethod(), "POST");
        assertEquals(actionResponse.toString(), "ActionResponse{success=true, code=200}");
        String requestBody = request.getBody().readUtf8();
        String expectBody = this.getResource("/ecs/cloud_server_add_tag_request.json");
        Assert.assertEquals(requestBody, expectBody);
    }

    @Test
    public void deleteTest() throws Exception {
        respondWith(200);
        List<ServerTags> serverTags = new ArrayList<>();
        serverTags.add(ServerTags.builder().key("key1").value("value1").build());
        String serverId = "server-id";
        ActionResponse actionResponse = osv3().ecs().tags().delete(serverId, serverTags);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(), "/v1/project-id/cloudservers/server-id/tags/action");
        assertEquals(request.getMethod(), "POST");
        assertEquals(actionResponse.toString(), "ActionResponse{success=true, code=200}");
        String requestBody = request.getBody().readUtf8();
        String expectBody = this.getResource("/ecs/cloud_server_delete_tag_request.json");
        Assert.assertEquals(requestBody, expectBody);
    }

    @Test
    public void listTest() throws Exception {
        respondWith(cloud_server_tag_result);
        String serverId = "server-id";
        CloudServerTag cloudServerTag = osv3().ecs().tags().list(serverId);
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(), "/v1/project-id/cloudservers/server-id/tags");
        assertEquals(request.getMethod(), "GET");
        Assert.assertEquals("key1", cloudServerTag.getServerTags().get(0).getKey());
        Assert.assertEquals("value1", cloudServerTag.getServerTags().get(0).getValue());
    }

    @Test
    public void listProjectTagsTest() throws Exception {
        respondWith(project_tag_result);
        ProjectTag projectTag = osv3().ecs().tags().listProjectTags();
        RecordedRequest request = server.takeRequest();
        assertEquals(request.getPath(), "/v1/project-id/cloudservers/tags");
        assertEquals(request.getMethod(), "GET");
        Assert.assertEquals("key1", projectTag.getTags().get(0).getKey());
        Assert.assertEquals("value1", projectTag.getTags().get(0).getValues().get(0));
        Assert.assertEquals("value2", projectTag.getTags().get(0).getValues().get(1));
    }

}
