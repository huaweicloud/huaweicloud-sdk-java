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
//import org.testng.annotations.Test;
//
//import com.huawei.openstack4j.api.AbstractTest;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.Restore;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.RestoreParam;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.RestoreResp;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.RestoreTarget;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.RestoreVolumeMapping;
//
//public class RestoreManagementServiceTest extends AbstractTest {
//	@Override
//	protected Service service() {
//		return Service.CSBS;
//	}
//
//	private static final String RESTORE_JSON = "/csbs/restore.json";
//
//	@Test
//	public void testCreate() throws IOException {
//		respondWith(RESTORE_JSON);
//
//		RestoreVolumeMapping volumeMapping = RestoreVolumeMapping.builder()
//				.backupId("******")
//				.volumeId("******")
//				.build();
//
//		List<RestoreVolumeMapping> list = new ArrayList<RestoreVolumeMapping>();
//		list.add(volumeMapping);
//		RestoreTarget target = RestoreTarget.builder().volumes(list).build();
//
//		RestoreParam param = RestoreParam.builder()
//		.targets(target)
//		.build();
//		Restore restore = Restore.builder()
//				.checkpointId("******")
//				.providerId("******")
//				.parameters(param)
//				.build();
//		RestoreResp res = osv3().csbs().restores().create(restore);
//		assertNotNull(res);
//	}
//
//}
