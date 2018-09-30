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
package com.huawei.openstack4j.openstack.ecs.v1_1.internal;

import static com.google.common.base.Preconditions.*;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.common.AsyncRespEntity;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.ResizeServer;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.ServerCreate;

public class CloudServerService extends BaseElasticComputeServices {

	/**
	 * create one or multiple server
	 * 
	 * @param creation
	 * @return			job-id of the asynchronous create server task
	 */
	public AsyncRespEntity create(ServerCreate creation) {
		checkArgument(!Strings.isNullOrEmpty(creation.getImageRef()), "parameter `imageRef` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getFlavorRef()), "parameter `flavorRef` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getName()), "parameter `name` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getVpcId()), "parameter `vpcid` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getAvailabilityZone()), "parameter `availability_zone` should not be empty");
		checkArgument(!(creation.getPersonality() != null && creation.getPersonality().size() > 5),
				"size of parameter `personality` should not greate than 5");
		checkArgument(creation.getNetworks() != null && creation.getNetworks().size() > 0,
				"parameter `networks` should not be empty");
		checkArgument(creation.getRootVolume() != null, "parameter `root_volume` should not be empty");
		return post(AsyncRespEntity.class, "/cloudservers").entity(creation).execute();
	}

	/**
	 * Change cloud server specifications
	 * @param resize
	 * @param serverId
	 * @return
	 */
	public AsyncRespEntity resize(ResizeServer resize,String serverId){
		checkArgument(!Strings.isNullOrEmpty(resize.getFlavorRef()), "parameter `flavorRef` should not be empty");
		return post(AsyncRespEntity.class, "/cloudservers/"+serverId+"/resize").entity(resize).execute();
	}
}
