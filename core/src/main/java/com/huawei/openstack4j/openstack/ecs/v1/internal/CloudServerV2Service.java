 /*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.                                         
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
package com.huawei.openstack4j.openstack.ecs.v1.internal;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.openstack.common.AsyncJobEntity;
import com.huawei.openstack4j.openstack.common.functions.ReplaceVersionOfURL;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ServerChangeOS;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

public class CloudServerV2Service extends BaseOpenStackService {

	public CloudServerV2Service(){
		super(ServiceType.ECS, ReplaceVersionOfURL.instance("/v2"));
	}
	
	/**
	 * change server OS
	 * 	
	 * @param change	
	 * @param serverId
	 * @return			job-id of the asynchronous server change os task
	 */
	public String changeOS(ServerChangeOS change, String serverId) {
		checkArgument(!Strings.isNullOrEmpty(change.getImageId()), "parameter `imageid` should not be empty");
		return post(AsyncJobEntity.class, "/cloudservers/"+serverId+"/changeos").entity(change).execute().getId();
	}

//	/**
//	 * Reinstall the elastic cloud server operating system
//	 * @param serverId
//	 * @param osReinstall
//	 * @return
//	 */
//	public AsyncJobEntity reinstallOS(OSReinstall osReinstall, String serverId){
//		checkArgument(!Strings.isNullOrEmpty(serverId), "parameter `serverId` should not be empty");
//		checkArgument(!(osReinstall == null), "parameter `osReinstall` should not be empty");
//		return post(AsyncJobEntity.class, uri("/cloudservers/"+serverId+"/reinstallos")).entity(osReinstall).execute();
//	}
}
