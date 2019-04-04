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

import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PublicIp;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PublicIp.Publicips;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PublicIpUpdate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualPublicIp;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualPublicIps;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VirtualPublicIpsResp;

 public class PublicIpService extends BaseVirtualPrivateCloudService{

	/**
	 * Querying Elastic IP Addresses
	 * @return
	 */
	public List<? extends PublicIp> list(){
		return list(null);
	}

	/**
	 * Querying Elastic IP Addresses with filter
	 * @param filteringParams
	 * @return
	 */
	public List<? extends PublicIp> list(Map<String, String> filteringParams) {
		Invocation<Publicips> flavorInvocation = get(Publicips.class, uri("/publicips"));
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				flavorInvocation = flavorInvocation.param(entry.getKey(), entry.getValue());
			}
		}

		return flavorInvocation.execute().getList();
	}

	 /**
	  * Apply for flexible public network IP
	  * @param virtualPublicIps
	  * @return
	  */
	 public VirtualPublicIpsResp apply(VirtualPublicIps virtualPublicIps){
		 Preconditions.checkNotNull(virtualPublicIps, "parameter `virtualPublicIps` should not be null");
		 Preconditions.checkNotNull(virtualPublicIps.getVirtualPublicIp(), "parameter `publicip` should not be null");
		 Preconditions.checkNotNull(virtualPublicIps.getVirtualBandwidth(), "parameter `bandwidth` should not be null");
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

	/**
	 * Updating(Binding/unbinding) Elastic IP Address Information
	 * @param publicIpId
	 * @return
	 */
	public PublicIp update(String publicIpId, PublicIpUpdate publicIpUpdate){
		Preconditions.checkNotNull(publicIpUpdate, "parameter `publicIpUpdate` should not be null");
		Preconditions.checkNotNull(publicIpId, "parameter `publicIpId` should not be null");
		return put(PublicIp.class, uri("/publicips/%s",publicIpId)).entity(publicIpUpdate).execute();
	}

	 /**
	  * Delete public IP
	  * @param publicIpId
	  * @return
	  */
	 public ActionResponse delete(String publicIpId) {
		 checkArgument(!Strings.isNullOrEmpty(publicIpId),"parameter `publicipId` should not be empty");
		 return deleteWithResponse(uri("/publicips/%s", publicIpId)).execute();
	 }
}
