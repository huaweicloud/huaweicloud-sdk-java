/*******************************************************************************
 * 	Copyright 2017 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.*;

import java.util.HashMap;
import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import com.huawei.openstack4j.api.scaling.AutoScalingGroupService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.ScalingGroup;
import com.huawei.openstack4j.model.scaling.ScalingGroupCreate;
import com.huawei.openstack4j.model.scaling.ScalingGroupUpdate;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingGroup;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingGroup.ASAutoScalingGroups;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingGroupCreate;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingGroupUpdate;
import com.huawei.openstack4j.openstack.scaling.options.ScalingGroupListOptions;

/**
 *
 * @author QianBiao.NG
 * @date 2017-06-14 09:02:52
 */
public class AutoScalingGroupServiceImpl extends BaseAutoScalingServices implements AutoScalingGroupService {

	@Override
	public String create(ScalingGroupCreate group) {
		checkArgument(group != null, "group is required");
		checkArgument(group.getNetworks() != null && !group.getNetworks().isEmpty(), "networks is required");
		checkArgument(!Strings.isNullOrEmpty(group.getVpcId()), "vpcId is required");
		for (IdResourceEntity network : group.getNetworks()) {
			checkArgument(!Strings.isNullOrEmpty(network.getId()), "network id is required");
		}
		ASAutoScalingGroupCreate execute = post(ASAutoScalingGroupCreate.class, uri("/scaling_group")).entity(group)
				.execute();
		return execute.getGroupId();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends ScalingGroup> list(ScalingGroupListOptions options) {
		return get(ASAutoScalingGroups.class, uri("/scaling_group")).params(options.getOptions()).execute().getList();
	}

	@Override
	public List<? extends ScalingGroup> list() {
		return get(ASAutoScalingGroups.class, uri("/scaling_group")).execute().getList();
	}

	@Override
	public ScalingGroup get(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "group id is required");
		return get(ASAutoScalingGroup.class, uri("/scaling_group/%s", groupId)).execute();
	}

	@Override
	public String update(String groupId, ScalingGroupUpdate group) {
		checkArgument(group != null, "group is required");
		checkArgument(!Strings.isNullOrEmpty(groupId), "group id is required");
		if (group.getNetworks() != null) {
			for (IdResourceEntity network : group.getNetworks()) {
				checkArgument(!Strings.isNullOrEmpty(network.getId()), "network id is required");
			}
		}
		if (group.getSecurityGroups() != null) {
			for (IdResourceEntity securityGroup : group.getSecurityGroups()) {
				checkArgument(!Strings.isNullOrEmpty(securityGroup.getId()), "security group id is required");
			}
		}
		return put(ASAutoScalingGroupUpdate.class, uri("/scaling_group/%s", groupId)).entity(group).execute()
				.getGroupId();
	}

	@Override
	public ActionResponse delete(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "group id is required");
		return deleteWithResponse(uri("/scaling_group/%s", groupId)).execute();
	}

	public ActionResponse delete(String groupId, boolean forceDelete) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "group id is required");
		return deleteWithResponse(uri("/scaling_group/%s", groupId)).param("force_delete", forceDelete ? "yes" : "no").execute();
	}

	@Override
	public ActionResponse resume(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "group id is required");
		HashMap<Object, Object> entity = Maps.newHashMap();
		entity.put("action", "resume");
		return postWithResponse(uri("/scaling_group/%s/action", groupId)).entity(entity).execute();
	}

	@Override
	public ActionResponse pause(String groupId) {
		checkArgument(!Strings.isNullOrEmpty(groupId), "group id is required");
		HashMap<Object, Object> entity = Maps.newHashMap();
		entity.put("action", "pause");
		return postWithResponse(uri("/scaling_group/%s/action", groupId)).entity(entity).execute();
	}

}
