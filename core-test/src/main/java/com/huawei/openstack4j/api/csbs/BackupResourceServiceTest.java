// /*******************************************************************************
// * 	Copyright 2018 Huawei Technologies Co.,Ltd.
// *
// * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not
// * 	use this file except in compliance with the License. You may obtain a copy of
// * 	the License at
// *
// * 	    http://www.apache.org/licenses/LICENSE-2.0
// *
// * 	Unless required by applicable law or agreed to in writing, software
// * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// * 	License for the specific language governing permissions and limitations under
// * 	the License.
// *******************************************************************************/
//package com.huawei.openstack4j.api.csbs;
//
//import static org.testng.Assert.assertNotNull;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Assert;
//import org.testng.annotations.Test;
//
//import com.huawei.openstack4j.api.AbstractTest;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckProtectable;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckProtectableResp;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckRestorable;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckRestorableResp;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.Protect;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.ProtectResp;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.Protectable;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.Restorable;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.RestoreVolume;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.Target;
//
//@Test
//public class BackupResourceServiceTest extends AbstractTest{
//
//	private static final String CSBS_RESOURCE = "/csbs/csbs_checkpoint.json";
//	private static final String CSBS_PROTECTABLE = "/csbs/csbs_protectable.json";
//	private static final String CSBS_RESTORABLE = "/csbs/csbs_restorable.json";
//	private String providerId = "******";
//	private String resourceId = "******";
//	private String resourceType = "OS::Nova::Server";
//	@Override
//	protected Service service() {
//		return Service.CSBS;
//	}
//
//	@Test
//	public void testCreate() throws IOException{
//		respondWith(CSBS_RESOURCE);
//		ProtectResp protectResp = osv3().csbs().resources().create(providerId, resourceId, Protect.builder().backupName("mybackuptest").build());
//		assertNotNull(protectResp.getId());
//	}
//
//	@Test
//	public void testGetProtectable() throws IOException{
//		respondWith(CSBS_PROTECTABLE);
//		List<Protectable> list = new ArrayList<Protectable>();
//		Protectable protectable = Protectable.builder().resourceId(resourceId).resourceType(resourceType).build();
//		list.add(protectable);
//		CheckProtectable checkProtectable = CheckProtectable.builder().checkProtectable(list).build();
//		CheckProtectableResp protectableResp = osv3().csbs().resources().getProtectable(providerId, checkProtectable);
//		Assert.assertEquals(protectableResp.getProtectable().size(), 1);
//	}
//
//	@Test
//	public void testGetRestorable() throws IOException{
//		respondWith(CSBS_RESTORABLE);
//		List<Restorable> list = new ArrayList<Restorable>();
//		List<RestoreVolume> volumeList = new ArrayList<RestoreVolume>();
//		volumeList.add(RestoreVolume.builder().backupId("******").volumeId("******").build());
//		String checkPointId = "******";
//		Restorable restorable = Restorable.builder()
//				.target(Target.builder().resourceId(resourceId).resourceType(resourceType)
//				.volumes(volumeList).build())
//				.checkpointItemId(checkPointId).build();
//		list.add(restorable);
//		CheckRestorableResp restorableResp = osv3().csbs().resources().getRestorable(providerId, CheckRestorable.builder().checkRestorable(list).build());
//		Assert.assertEquals(restorableResp.getRestorable().size(), 1);
//	}
//
//}
