///*******************************************************************************
// *  Copyright 2017 Huawei TLD
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
///*******************************************************************************
// *******************************************************************************/
//package com.huawei.openstack4j.functional.compute.v1;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.huawei.openstack4j.functional.AbstractTestV3;
//import com.huawei.openstack4j.functional.Retry;
//import com.huawei.openstack4j.model.compute.Server;
//import com.huawei.openstack4j.model.network.Network;
//import com.huawei.openstack4j.model.network.Router;
//import com.huawei.openstack4j.openstack.compute.v1.contants.VolumeType;
//import com.huawei.openstack4j.openstack.compute.v1.domain.DataVolume;
//import com.huawei.openstack4j.openstack.compute.v1.domain.RootVolume;
//import com.huawei.openstack4j.openstack.compute.v1.domain.ServerCreate;
//import com.huawei.openstack4j.openstack.message.notification.domain.Topic;
//
//@Test(suiteName = "Compute/v1/Server/Test")
//public class ServerTest extends AbstractTestV3 {
//
//	String name = randomName();
//	Topic topic = null;
//	private List<? extends Server> created;
//
//	@BeforeClass
//	@SuppressWarnings("unchecked")
//	public void prepare() {
//		Router router = this.getFirstRouter();
//		List<? extends Network> networks = this.getNetwork(router.getId());
//		ServerCreate creation = ServerCreate.builder().name(name).flavorRef(this.getFirstFlavor().getId())
//				.imageRef(this.getFirstImage().getId()).vpcId(router.getId()).addNetwork(networks.get(0).getId())
//				.availabilityZone("eu-de-01").rootVolume(RootVolume.builder().size(100).type(VolumeType.SSD).build())
//				 .addDataVolume(DataVolume.builder().size(100).type(VolumeType.SATA).multiAttach(true).passthrough(true).build())
//				.count(2).build();
//		String jobId = osclient.compute().serversV1().create(creation);
//		
//		created = (List<? extends Server>) this.retry(new Retry() {
//			@Override
//			public Integer maxRetryTimes() {
//				return 20;
//			}
//
//			@Override
//			public Integer delay() {
//				return 20;
//			}
//
//			@Override
//			public Object run() {
//				Map<String , String> filter = new HashMap<String, String>();
//				filter.put("name", name);
//				List<? extends Server> list = osclient.compute().servers().list(filter);
//				if (list.size() == 2) {
//					return list;
//				}
//				return null;
//			}
//		});
//	}
//
//	@AfterClass
//	public void cleanup() {
//		List<String> collect = created.stream().map(s -> {
//			return s.getId();
//		}).collect(Collectors.toList());
//		String jobId = osclient.compute().serversV1().delete(collect, true, true);
//		Assert.assertNotNull(jobId);
//	}
//
//	@Test(priority = 1)
//	public void testSomething() {
//		// donothing
//		
//	}
//
//}
