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
//
//import org.testng.annotations.Test;
//
//import com.huawei.openstack4j.api.AbstractTest;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.ProtectConfig;
//@Test
//public class ProjectManagementServiceTest extends AbstractTest{
//	@Override
//	protected Service service() {
//		return Service.CSBS;
//	}
//
//	private static final String PROJECT_JSON = "/csbs/project_config.json";
//	@Test
//	public void testGet() throws IOException{
//		respondWith(PROJECT_JSON);
//		ProtectConfig res = osv3().csbs().protects().get();
//		assertNotNull(res.getId());
//	}
//}
