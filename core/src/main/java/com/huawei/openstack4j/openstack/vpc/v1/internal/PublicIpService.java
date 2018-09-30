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
package com.huawei.openstack4j.openstack.vpc.v1.internal;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualPublicIpsResp;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualPublicIps;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualPublicIp;

public class PublicIpService extends BaseVirtualPrivateCloudService{

	/**
	 * Apply for flexible public network IP
	 * @param virtualPublicIps
	 * @return
	 */
	public VirtualPublicIpsResp apply(VirtualPublicIps virtualPublicIps){
		checkArgument(!(null == (virtualPublicIps.getVirtualPublicIp().getType())), "parameter `type` should not be empty");
		checkArgument(!(null == (virtualPublicIps.getVirtualBandwidth().getShareType())), "parameter `share_type` should not be empty");
		return post(VirtualPublicIpsResp.class, "/publicips").entity(virtualPublicIps).execute();
	}
	
	/**
	 * list publicip
	 * @param publicipId
	 * @return
	 */
	public VirtualPublicIp get(String publicipId){
		checkArgument(!Strings.isNullOrEmpty(publicipId), "parameter `publicipId` should not be empty");
		return get(VirtualPublicIp.class,"/publicips/"+publicipId).execute();
	}
	
	
}
