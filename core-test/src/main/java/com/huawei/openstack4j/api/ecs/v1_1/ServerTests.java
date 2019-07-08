/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co.,Ltd.                                         
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
package com.huawei.openstack4j.api.ecs.v1_1;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

import okhttp3.mockwebserver.RecordedRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.ecs.v1.contants.NetworkChargingMode;
import com.huawei.openstack4j.openstack.ecs.v1_1.contants.ShareType;
import com.huawei.openstack4j.openstack.ecs.v1.contants.VolumeType;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Personality;
import com.huawei.openstack4j.openstack.ecs.v1.contants.IpType;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Ipv6BandWidth;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Network;
import com.huawei.openstack4j.openstack.ecs.v1_1.contants.ServerChargingMode;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.AsyncServerRespEntity;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.Bandwidth;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.DataVolume;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.FloatingIPCreate;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.FloatingIPExtendParam;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.PublicIP;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.RootVolume;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.SchedulerHints;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.ServerCreate;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.ServerExtendParam;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.ServerTags;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.VolumeExtendParam;


@Test(suiteName = "ECS1_1/Servers")
public class ServerTests extends AbstractTest{

	@Override
	protected Service service() {
		return Service.ECS1_1;
	}

	@Test
	public void createServerTest() throws Exception {
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\",\"serverIds\": [\"this-is-serverIds\"]}");

		Network network = Network.builder().subnetId("network-id").ipv6Enable(true)
				.ipv6Bandwidth(Ipv6BandWidth.builder().id("ipv6_bandwidth_id").build()).build();

		Bandwidth bandwidth = Bandwidth.builder().size(10).shareType(ShareType.PER).chargeMode(NetworkChargingMode.TRAFFIC).id("bandwidthId").build();
		FloatingIPExtendParam fIPExtParam = FloatingIPExtendParam.builder().chargingMode(ServerChargingMode.postPaid).build();
		FloatingIPCreate build = FloatingIPCreate.builder().ipType(IpType.BGP).bandwidth(bandwidth )
				.extendparam(fIPExtParam).build();
		PublicIP FIPbuild = PublicIP.builder().eip(build).build();

		List<ServerTags> serverTags = new ArrayList<>();
		serverTags.add(ServerTags.builder().key("key1").value("value1").build());
		serverTags.add(ServerTags.builder().key("key2").value("value2").build());

		ServerCreate creation = ServerCreate.builder().name("name").flavorRef("flavor-id").imageRef("image-id")
				.vpcId("vpc-id").addNetwork(network).availabilityZone("eu-de-01")
				.addSecurityGroup("securityGroupId")
				.addTag("key", "value")
				.serverTags(serverTags)
				.description("description")
				.publicIP(FIPbuild)
				.addPersonality(Personality.builder().contents("some content").path("/etc/xxx").build())
				.rootVolume(RootVolume.builder().size(10).type(VolumeType.SSD).passthrough(true).build())
				.addDataVolume(
						DataVolume.builder().size(100).type(VolumeType.SAS).multiAttach(true).passthrough(true)
								.extendParam(VolumeExtendParam.builder().snapshotId("snapshotId").build())
								.dataImageId("data_image_id").build())
				.extendParam(ServerExtendParam.builder().enterpriseProjectId("enterpriseProjectId").build())
				.schedulerHints(SchedulerHints.builder().group("groupId").build())
				.addMetadata("mkey", "mvalue")
				.build();
				

		//create server with return serverIds in responese
		AsyncServerRespEntity espEntity = osv3().ecsV1_1().servers().create(creation);
		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v1.1/project-id/cloudservers");
		assertEquals(request.getMethod(), "POST");
		assertEquals(espEntity.getJobId(), "this-is-a-job-id");
		assertEquals(espEntity.getServerIds().get(0), "this-is-serverIds");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/compute/v1/servers_create_request.json");
		Assert.assertEquals(requestBody, expectBody);
	}
}