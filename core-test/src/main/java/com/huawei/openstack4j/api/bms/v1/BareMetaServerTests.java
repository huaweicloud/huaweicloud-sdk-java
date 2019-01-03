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
package com.huawei.openstack4j.api.bms.v1;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.bms.v1.contants.DecBaremetal;
import com.huawei.openstack4j.openstack.bms.v1.contants.VolumeType;
import com.huawei.openstack4j.openstack.bms.v1.domain.*;
import com.huawei.openstack4j.openstack.common.AsyncRespEntity;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Job;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Network;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Test
public class BareMetaServerTests extends AbstractTest{
	private static final String	BMS_GET = "/bms/v1/bms_get.json";
	private static final String	JOB_GET = "/bms/v1/job_get.json";


	@Override
	protected Service service() {
		return Service.BMS;
	}

	public void createTest() throws IOException{
		respondWith(200, "{\"job_id\": \"this-is-a-job-id\"}");
		String imageRef = "766a545a-02e1-433e-b1d1-733b5dc95e94";
		String flavorRef = "physical.s1.medium";
		String vpcId = "3bdd1973-fa1b-4fbe-980c-d5b8e238d345";
		String subnetId = "1ffc0d2e-8aa8-4da2-ae45-8e58c7d0f251";
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("op_svc_userid", "59781460e9e54886a7d03df7d3f3fc81");
		ServerCreate creation = ServerCreate.builder().availabilityZone("kvmxen.dc1").name("bms-75c7").imageRef(imageRef).flavorRef(flavorRef)
		.dataVolumes(Arrays.asList(DataVolume.builder().type(VolumeType.SATA).shareable(false).build())).vpcId(vpcId)
		.networks(Arrays.asList(Network.builder().subnetId(subnetId).ipAddress("").build())).count(1)
		.schedulerHints(SchedulerHints.builder().decBaremetal(DecBaremetal.share).build()).extendParam(ServerExtendParam.builder().isAutoPay(false).build())
		.metadata(map).build();
		AsyncRespEntity create = osv3().bms().servers().create(creation);
		assertEquals(create.getJobId(), "this-is-a-job-id");
	}

	public void getTest() throws IOException{
		respondWith(BMS_GET);
		String serverId = "b8f2083c-cfda-4ee1-b114-ffd5040f454a";
		BareMetaServer bareMetaServer = osv3().bms().servers().get(serverId);
		assertEquals("bms-8863_", bareMetaServer.getName());
	}

	public void attachVolumeTest() throws IOException{
		respondWith(200);
		String serverId = "b8f2083c-cfda-4ee1-b114-ffd5040f454a";
		VolumeAttachment volume = VolumeAttachment.builder().id("test").build();
		ActionResponse response = osv3().bms().servers().attachVolume(serverId, volume);
		assertEquals(200, response.getCode());
	}

	public void detachVolumeTest() throws IOException{
		respondWith(200);
		String serverId = "b8f2083c-cfda-4ee1-b114-ffd5040f454a";
		ActionResponse response = osv3().bms().servers().detachVolume(serverId, "test");
		assertEquals(200, response.getCode());
	}

	public void getJobTest() throws IOException{
		respondWith(JOB_GET);
		Job job = osv3().bms().jobs().get("test");
		assertEquals("2c9eb2c5544cbf6101544f0602af2b4f", job.getJobId());
	}
}
