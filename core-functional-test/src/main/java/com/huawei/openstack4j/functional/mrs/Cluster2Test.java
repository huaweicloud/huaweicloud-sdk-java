/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package com.huawei.openstack4j.functional.mrs;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.map.reduce.constants.ClusterType;
import com.huawei.openstack4j.openstack.map.reduce.constants.ClusterVersion;
import com.huawei.openstack4j.openstack.map.reduce.constants.JobType;
import com.huawei.openstack4j.openstack.map.reduce.constants.VolumeType;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterInfo;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterCreate;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterCreateResult;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceComponent;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExeCreate;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-05 15:36:39
 */
@Test
public class Cluster2Test extends AbstractTest {

	public void testGetCluster() {
		MapReduceClusterInfo cluster = osclient.mrs().clusters().get("0f4ab6b7-a723-4b6c-b326-f8a5711d365a");
		Assert.assertEquals(cluster.getId(), "0f4ab6b7-a723-4b6c-b326-f8a5711d365a");
	}
}
