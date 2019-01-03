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
//import com.huawei.openstack4j.model.common.ActionResponse;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.OperationDefinition;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.Policy;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.PolicyCreate;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.PolicyParam;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.PolicyUpdate;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.Resource;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.ScheduledOperationCreate;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.Trigger;
//import com.huawei.openstack4j.openstack.csbs.v1.domain.TriggerProperties;
//
//@Test
//public class BackupPoliciesServiceTest extends AbstractTest{
//
//	private static final String CSBS_POLICY = "/csbs/csbs_policy.json";
//	private static final String CSBS_POLICIES = "/csbs/csbs_policies.json";
//
//	private String policyId = "******";
//	private String resourceType = "OS::Nova::Server";
//	@Override
//	protected Service service() {
//		return Service.CSBS;
//	}
//
//	@Test
//	public void testGet() throws IOException{
//		respondWith(CSBS_POLICY);
//		Policy policy = osv3().csbs().policies().get(policyId);
//		assertNotNull(policy.getId());
//	}
//
//	@Test
//	public void testList() throws IOException{
//		respondWith(CSBS_POLICIES);
//		List<Policy> list = osv3().csbs().policies().list();
//		Assert.assertEquals(list.size(), 1);
//	}
//
//	@Test
//	public void testUpdate() throws IOException{
//		respondWith(CSBS_POLICY);
//		PolicyUpdate update = PolicyUpdate.builder().name("******").build();
//		Policy policy = osv3().csbs().policies().update(policyId, update);
//		assertNotNull(policy.getId());
//	}
//
//	@Test
//	public void testDelete() throws IOException{
//		respondWith(200);
//		ActionResponse delete = osv3().csbs().policies().delete(policyId);
//		Assert.assertEquals(delete.getCode(), 200);
//	}
//
//	@Test
//	public void testCreate() throws IOException{
//		respondWith(CSBS_POLICY);
//		String trigger = "******";
//		TriggerProperties triggerPro = TriggerProperties.builder().pattern(trigger).build();
//		List<ScheduledOperationCreate> scheduledOperations = new ArrayList<>();
//		ScheduledOperationCreate scheduledOperationCreate = ScheduledOperationCreate.builder().operationType("backup")
//				.operationDefinition(OperationDefinition.builder().build())
//				.trigger(Trigger.builder().properties(triggerPro).build()).build();
//		scheduledOperations.add(scheduledOperationCreate);
//		List<Resource> resources = new ArrayList<>();
//		Resource myResources = Resource.builder().name("myResources").id("******").type(resourceType).build();
//		resources.add(myResources);
//		PolicyCreate policyCreate = PolicyCreate.builder().name("my-test")
//				.parameters(PolicyParam.builder().build())
//				.providerId("******").scheduledOperations(scheduledOperations)
//				.resources(resources)
//				.build();
//		Policy policy =osv3().csbs().policies().create(policyCreate);
//		assertNotNull(policy.getId());
//	}
//
//}
