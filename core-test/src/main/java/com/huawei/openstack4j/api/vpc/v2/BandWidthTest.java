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
package com.huawei.openstack4j.api.vpc.v2;

import okhttp3.mockwebserver.RecordedRequest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.vpc.v2.domain.AsyncBandWidthRespEntity;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualBandWidthUpdate;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualBandWidths;
import com.huawei.openstack4j.openstack.vpc.v2.domain.VirtualExtendParamUpdate;

@Test(suiteName = "VPC/BandWidth")
public class BandWidthTest extends AbstractTest{

	@Override
	protected Service service() {
		return Service.VPC2;
	}

	@Test
	public void updateTest() throws InterruptedException{
		respondWith(200, "{\"order_id\": \"this-is-a-order-id\"}");


		String bdId = "BandWidthTestId";
		VirtualBandWidths build = VirtualBandWidths.builder()
				.bandwidth(VirtualBandWidthUpdate.builder().size(20).name("bandwidth123").build())
				.extendParam(VirtualExtendParamUpdate.builder().isAutoPay(false).build()).build();
		AsyncBandWidthRespEntity respEntity =  osv3().vpcV2().bandwidths().update(build, bdId);

		RecordedRequest request = server.takeRequest();
		assertEquals(request.getPath(), "/v2.0/project-id/bandwidths/BandWidthTestId");
		assertEquals(request.getMethod(), "PUT");
		assertEquals(respEntity.getOrderId(), "this-is-a-order-id");
	}

//	@Test
//	public void createTest() throws Exception{
//		respondWith("/vpc/v2/bandwidth.json");
//		BandwidthCreate bandwidthCreate = BandwidthCreate.builder().name("test").size(5).build();
//		BandwidthResp bandwidthResp = osv3().vpcV2().bandwidths().create(bandwidthCreate);
//		assertEquals(bandwidthResp.getName(), "bandwidth123");
//		assertEquals(bandwidthResp.getShareType().toString(), "WHOLE");
//		assertEquals(bandwidthResp.getSize().toString(), "10");
//		assertEquals(bandwidthResp.getChargeMode().toString(), "BANDWIDTH");
//		assertEquals(bandwidthResp.getId(), "3fa5b383-5a73-4dcb-a314-c6128546d855");
//	}
//
//	@Test
//	public void batchCreateTest() throws Exception{
//		respondWith("/vpc/v2/bandwidth_batchcreate.json");
//		List<BandwidthResp> bandwidthResps = osv3().vpcV2().bandwidths().batchCreate(BandwidthBatchCreate.builder().count(5).name("test").size(10).build());
//		assertEquals(bandwidthResps.size(), 2);
//		assertEquals(bandwidthResps.get(0).getId(), "3fa5b383-5a73-4dcb-a314-c6128546d855");
//		assertEquals(bandwidthResps.get(0).getName(), "bandwidth123");
//		assertEquals(bandwidthResps.get(0).getShareType().toString(), "WHOLE");
//		assertEquals(bandwidthResps.get(0).getSize().toString(), "10");
//		assertEquals(bandwidthResps.get(0).getChargeMode().toString(), "BANDWIDTH");
//		assertEquals(bandwidthResps.get(1).getId(), "d91b0028-6f6b-4478-808a-297b75b6812a");
//		assertEquals(bandwidthResps.get(1).getName(), "bandwidth123");
//		assertEquals(bandwidthResps.get(1).getShareType().toString(), "WHOLE");
//		assertEquals(bandwidthResps.get(1).getSize().toString(), "10");
//		assertEquals(bandwidthResps.get(1).getChargeMode().toString(), "BANDWIDTH");
//	}
//
//	@Test
//	public void deleteTest() throws Exception{
//		respondWith(200);
//		ActionResponse delete = osv3().vpcV2().bandwidths().delete("bandwidth-id");
//		assertEquals(delete.getCode(), 200);
//	}
//
//	@Test
//	public void insertTest() throws Exception{
//		respondWith("/vpc/v2/bandwidth.json");
//		List<PublicIpInfo> publicIpInfos = new ArrayList<>();
//		publicIpInfos.add(PublicIpInfo.builder().publicipId("publicip-id").build());
//		BandwidthInsert bandwidthInsert = BandwidthInsert.builder().publicipInfo(publicIpInfos).build();
//		BandwidthResp bandwidthResp = osv3().vpcV2().bandwidths().insert("bandwidth-id",bandwidthInsert);
//		assertEquals(bandwidthResp.getName(), "bandwidth123");
//		assertEquals(bandwidthResp.getShareType().toString(), "WHOLE");
//		assertEquals(bandwidthResp.getSize().toString(), "10");
//		assertEquals(bandwidthResp.getChargeMode().toString(), "BANDWIDTH");
//		assertEquals(bandwidthResp.getId(), "3fa5b383-5a73-4dcb-a314-c6128546d855");
//	}
//
//	@Test
//	public void removeTest() throws Exception{
//		respondWith(200);
//		List<PublicIpInfo> publicIpInfos = new ArrayList<>();
//		publicIpInfos.add(PublicIpInfo.builder().publicipId("publicip-id").build());
//		BandwidthRemove bandwidthRemove = BandwidthRemove.builder().publicipInfo(publicIpInfos).size(10).chargeMode(VirtualChargingMode.BANDWIDTH).build();
//		ActionResponse remove = osv3().vpcV2().bandwidths().remove("bandwidth-id", bandwidthRemove);
//		assertEquals(remove.getCode(), 200);
//	}
}
