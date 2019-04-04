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
import com.huawei.openstack4j.openstack.vpc.v1.domain.Vpc;
import com.huawei.openstack4j.openstack.vpc.v1.domain.Vpc.Vpcs;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VpcCreate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VpcUpdate;

/**
 * The implementation of manipulation of Vpc
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
public class VpcService extends BaseVirtualPrivateCloudService {
	
	/**
	 * Querying VPCs
	 * @return
	 */
	public List<? extends Vpc> list(){
		return list(null);
	}
	
	/**
	 * Querying VPCs with filter
	 * @param filteringParams
	 * @return
	 */
	public List<? extends Vpc> list(Map<String, String> filteringParams) {
		Invocation<Vpcs> flavorInvocation = get(Vpcs.class, uri("/vpcs"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	flavorInvocation = flavorInvocation.param(entry.getKey(), entry.getValue());
            }
        }
		
		return flavorInvocation.execute().getList();
	}
	
	/**
	 * Creating a VPC
	 * @param vpcCreate
	 * @return
	 */
	public Vpc create(VpcCreate vpcCreate){
		Preconditions.checkNotNull(vpcCreate, "parameter `vpcCreate` should not be null");
		return post(Vpc.class, uri("/vpcs")).entity(vpcCreate).execute();
	}
	
	/**
	 * Querying VPC Details
	 * @param vpcId
	 * @return
	 */
	public Vpc get(String vpcId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(vpcId), "parameter `vpcId` should not be empty");
		return get(Vpc.class, uri("/vpcs/%s",vpcId)).execute();
	}
	
	/**
	 * Updating VPC Information
	 * @param vpcId
	 * @param vpcUpdate
	 * @return
	 */
	public Vpc update(String vpcId, VpcUpdate vpcUpdate){
		Preconditions.checkNotNull(vpcUpdate, "parameter `vpcUpdate` should not be null");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(vpcId), "parameter `vpcId` should not be empty");
		return put(Vpc.class, uri("/vpcs/%s",vpcId)).entity(vpcUpdate).execute();
	}
	
	/**
	 * Deleting a VPC
	 * @param vpcId
	 * @return
	 */
	public ActionResponse delete(String vpcId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(vpcId), "parameter `vpcId` should not be empty");
		return deleteWithResponse(uri("/vpcs/%s", vpcId)).execute();
	}

}
