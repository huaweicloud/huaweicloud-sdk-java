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

import static org.testng.Assert.assertEquals;
import okhttp3.mockwebserver.RecordedRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.common.AsyncRespEntity;
import com.huawei.openstack4j.openstack.ecs.v1.contants.NetworkChargingMode;
import com.huawei.openstack4j.openstack.ecs.v1_1.contants.ShareType;
import com.huawei.openstack4j.openstack.ecs.v1.contants.VolumeType;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.Bandwidth;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.DataVolume;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.FloatingIPCreate;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Personality;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.RootVolume;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.ServerCreate;
import com.huawei.openstack4j.openstack.ecs.v1.contants.IpType;

@Test(suiteName = "ECS/Servers")
public class ServerTests extends AbstractTest{

	@Override
	protected Service service() {
		return Service.ECS1_1;
	}

	@Test
	public void createServerTest() throws Exception {
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");

		
		Bandwidth bandwidth = Bandwidth.builder().size(10).shareType(ShareType.PER).chargeMode(NetworkChargingMode.BANDWIDTH).build();
		FloatingIPCreate build = FloatingIPCreate.builder().ipType(IpType.BGP).bandwidth(bandwidth).build();
		
		ServerCreate creation = ServerCreate.builder().name("name").flavorRef("flavor-id").imageRef("image-id")
				.vpcId("vpc-id").addNetwork("network-id").availabilityZone("eu-de-01")
				.addSecurityGroup("securityGroupId")
				.addTag("key", "value")
				.publicIP(build)
				.addPersonality(Personality.builder().contents("some content").path("/etc/xxx").build())
				.rootVolume(RootVolume.builder().size(10).type(VolumeType.SSD).build())
				.addDataVolume(
						DataVolume.builder().size(100).type(VolumeType.SAS).multiAttach(true).passthrough(true).build())
				.addMetadata("mkey", "mvalue")
				.count(2).build();
		AsyncRespEntity espEntity = osv3().ecsV1_1().servers().create(creation);
		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v1.1/project-id/cloudservers");
		assertEquals(request.getMethod(), "POST");
		assertEquals(espEntity.getJobId(), "this-is-a-job-id");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/compute/v1/servers_create_request.json");
		Assert.assertEquals(requestBody, expectBody);
	}
}
