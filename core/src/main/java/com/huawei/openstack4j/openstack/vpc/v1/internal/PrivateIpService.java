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

import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PrivateIp;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PrivateIp.PrivateIps;

/**
 * The implementation of manipulation of Private IP
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
public class PrivateIpService extends BaseVirtualPrivateCloudService {
	
	/**
	 * Querying Private IP Addresses
	 * @param subnetId
	 * @return
	 */
	public List<? extends PrivateIp> list(String subnetId){
		return list(subnetId,null);
	}
	
	/**
	 * Querying Private IP Addresses with filter
	 * @param subnetId
	 * @param filteringParams
	 * @return
	 */
	public List<? extends PrivateIp> list(String subnetId, Map<String, String> filteringParams) {
		Invocation<PrivateIps> flavorInvocation = get(PrivateIps.class, uri("/subnets/%s/privateips",subnetId));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	flavorInvocation = flavorInvocation.param(entry.getKey(), entry.getValue());
            }
        }
		
		return flavorInvocation.execute().getList();
	}

	/**
	 * Assigning a Private IP Address
	 * @param privateIps
	 * @return
	 */
	public List<? extends PrivateIp> apply(PrivateIps privateIps){

		Preconditions.checkNotNull(privateIps, "parameter `privateips` should not be null");
		for (PrivateIp privateIp:privateIps.getList())
		{
			Preconditions.checkNotNull(privateIp.getSubnetId(), "parameter `subnetId` should not be null");
		}
		return post(PrivateIps.class, uri("/privateips")).entity(privateIps).execute().getList();
	}
	
	/**
	 * Querying Private IP Address Details
	 * @param privateIpId
	 * @return
	 */
	public PrivateIp get(String privateIpId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(privateIpId), "parameter `privateIpId` should not be empty");
		return get(PrivateIp.class, uri("/privateips/%s",privateIpId)).execute();
	}

	/**
	 * Deleting a Private IP Address
	 * @param privateIpId
	 * @return
	 */
	public ActionResponse delete(String privateIpId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(privateIpId), "parameter `privateipId` should not be empty");
		return deleteWithResponse(uri("/privateips/%s", privateIpId)).execute();
	}

}
