package com.huawei.openstack4j.functional.evs.v2_1;


import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.evs.v2.contants.ChargingType;
import com.huawei.openstack4j.openstack.evs.v2.domain.CloudVolumes;
import com.huawei.openstack4j.openstack.evs.v2.domain.Metadata;
import com.huawei.openstack4j.openstack.evs.v2_1.domain.BssParam;
import com.huawei.openstack4j.openstack.evs.v2_1.domain.Volumes;

public class VolumeTests extends AbstractTest {

//	@Test
//	public void createVolumeTest() {
//		Metadata buildMetadata = Metadata.builder().systemEncrypted("0").hwPassthrough(true).build();
//		CloudVolumes vo = CloudVolumes.builder().name("name").volumeType("volumetype").metadata(buildMetadata)
//				.size(120).availabilityZone("az").multiattach(true).count(1).build();
//		BssParam bssParam = BssParam.builder().chargingMode(ChargingType.prePaid).periodType("year").periodNum(1).isAutoPay(true).isAutoRenew(true).build();
//		Volumes volumes = Volumes.builder().cloudVolumes(vo).bssParam(bssParam).build();
//		String orderId =osclient.evsV2_1().volumes().create(volumes);
//		assertNotNull((Object)orderId);
//	}
}
