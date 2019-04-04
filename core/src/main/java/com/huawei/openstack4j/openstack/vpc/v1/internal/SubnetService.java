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
import com.huawei.openstack4j.openstack.vpc.v1.domain.Subnet;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SubnetCreate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.Subnet.Subnets;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SubnetUpdate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SubnetUpdateResp;

/**
 * The implementation of manipulation of Subnet
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
public class SubnetService extends BaseVirtualPrivateCloudService{

	/**
	 * Querying Subnets
	 * @return
	 */
	public List<? extends Subnet> list(){
		return list(null);
	}
	
	/**
	 * Querying Subnets with filter
	 * @param filteringParams
	 * @return
	 */
	public List<? extends Subnet> list(Map<String, String> filteringParams) {
		Invocation<Subnets> flavorInvocation = get(Subnets.class, uri("/subnets"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	flavorInvocation = flavorInvocation.param(entry.getKey(), entry.getValue());
            }
        }
		
		return flavorInvocation.execute().getList();
	}
	
	/**
	 * Creating a Subnet
	 * @param subnetCreate
	 * @return
	 */
	public Subnet create(SubnetCreate subnetCreate){
		Preconditions.checkNotNull(subnetCreate, "parameter `subnetCreate` should not be null");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(subnetCreate.getName()),
				"parameter `subnetCreate.name` should not be empty");
		Preconditions.checkNotNull(subnetCreate.getCidr(), "parameter `subnetCreate.cidr` should not be empty");
		Preconditions.checkNotNull(subnetCreate.getGatewayIp(),"parameter `subnetCreate.gatewayIp` should not be empty");
		Preconditions.checkNotNull(subnetCreate.getVpcId(),"parameter `subnetCreate.vpcId` should not be empty");
		return post(Subnet.class, uri("/subnets")).entity(subnetCreate).execute();
	}

	/**
	 * Querying Subnet Details
	 * @param subnetId
	 * @return
	 */
	public Subnet get(String subnetId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(subnetId), "parameter `subnetId` should not be empty");
		return get(Subnet.class, uri("/subnets/%s",subnetId)).execute();
	}
	
	/**
	 * Updating Subnet Information
	 * @param subnetUpdate
	 * @return
	 */
	public SubnetUpdateResp update(String vpcId, String subnetId, SubnetUpdate subnetUpdate){
		Preconditions.checkNotNull(subnetUpdate, "parameter `subnetUpdate` should not be null");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(vpcId), "parameter `subnet.vpcId` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(subnetId), "parameter `subnet.id` should not be empty");
		Preconditions.checkNotNull(subnetUpdate.getName(), "parameter `subnet.name` should not be empty");
		return put(SubnetUpdateResp.class, uri("/vpcs/%s/subnets/%s",vpcId,subnetId)).entity(subnetUpdate).execute();
	}
	
	/**
	 * Deleting a Subnet
	 * @param vpcId
	 * @param subnetId
	 * @return
	 */
	public ActionResponse delete(String vpcId,String subnetId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(vpcId), "parameter `vpcId` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(subnetId), "parameter `subnetId` should not be empty");
		return deleteWithResponse(uri("/vpcs/%s/subnets/%s",vpcId,subnetId)).execute();
	}

}
