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
package com.huawei.openstack4j.api.csbs;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckPoint;
import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckPointItem;
import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckPointParam;
import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckPointResp;

@Test
public class BackupManagementServiceTest extends AbstractTest {
	@Override
	protected Service service() {
		return Service.CSBS;
	}

	private static final String CSBS_JSON = "/csbs/csbs_create.json";
	private static final String CSBS_LIST_JSON = "/csbs/csbs_list.json";
	private static final String CSBS_REPLICATION_JSON = "/csbs/csbs_replication.json";
	@Test
	public void testCreate() throws IOException {
		respondWith(CSBS_JSON);
		String providerId = "******";
		String planId = "******";
		CheckPoint checkPoint = CheckPoint
				.builder()
				.planId(planId)
				.parameters(CheckPointParam.builder().autoTrigger(true).build())
				.build();
		CheckPointResp res = osv3().csbs().backups()
				.create(checkPoint, providerId);
		assertNotNull(res.getId());
	}
	@Test(enabled=false)
	public void testGet() throws IOException {
		respondWith(1);
		Integer res = osv3().csbs().backups().get();
		assertNotNull(res);
	}
	@Test
	public void testList() throws IOException {
		respondWith(CSBS_LIST_JSON);
		List<CheckPointItem> res = osv3().csbs().backups().list();
		assertNotNull(res);
	}
	
//	@Test
//	public void testReplication() throws IOException {
//		respondWith(CSBS_REPLICATION_JSON);
//		String providerId = "******";
//		String checkpointItemId = "******";
//		Replication replication = Replication.builder()
//			.destinationProjectId("******")
//			.destinationRegion("******")
//			.build();
//		Replication create = osv3().csbs().backups().create(replication, providerId, checkpointItemId);
//		assertNotNull(create.getDestinationProjectId());
//	}
	
//	@Test
//	public void testCreatePolicy() throws IOException {
//		respondWith(CSBS_REPLICATION_JSON);
//		String providerId = "******";
//		Replication replication = Replication.builder()
//			.destinationProjectId("******")
//			.destinationRegion("******")
//			.policyId("******")
//			.build();
//		Replication create = osv3().csbs().backups().create(replication, providerId);
//		assertNotNull(create.getDestinationProjectId());
//	}
}
