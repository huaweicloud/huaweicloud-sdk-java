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
import com.huawei.openstack4j.openstack.vpc.v1.domain.SecurityGroup;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SecurityGroup.SecurityGroups;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SecurityGroupCreate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SecurityGroupRule;
import com.huawei.openstack4j.openstack.vpc.v1.domain.SecurityGroupRule.SecurityGroupRules;

/**
 * The implementation of manipulation of Security Group
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
public class SecurityGroupService extends BaseVirtualPrivateCloudService {
	
	/**
	 * Querying Security Groups
	 * @return
	 */
	public List<? extends SecurityGroup> list(){
		return list(null);
	}
	
	/**
	 * Querying Security Groups with filter
	 * @param filteringParams
	 * @return
	 */
	public List<? extends SecurityGroup> list(Map<String, String> filteringParams) {
		Invocation<SecurityGroups> securityGroupsInvocation = get(SecurityGroups.class, uri("/security-groups"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	securityGroupsInvocation = securityGroupsInvocation.param(entry.getKey(), entry.getValue());
            }
        }
		return securityGroupsInvocation.execute().getList();
	}
	
	/**
	 * Creating a Security Group
	 * @param securityGroupCreate
	 * @return
	 */
	public SecurityGroup create(SecurityGroupCreate securityGroupCreate){
		Preconditions.checkNotNull(securityGroupCreate, "parameter `securityGroupCreate` should not be null");
		Preconditions.checkNotNull(securityGroupCreate.getName(), "parameter `securityGroup.name should not be empty");
		return post(SecurityGroup.class, uri("/security-groups")).entity(securityGroupCreate).execute();
	}
	
	/**
	 * Querying Security Group Details
	 * @param securityGroupId
	 * @return
	 */
	public SecurityGroup get(String securityGroupId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(securityGroupId), "parameter `securityGroupId` should not be empty");
		return get(SecurityGroup.class, uri("/security-groups/%s",securityGroupId)).execute();
	}
	
	/**
	 * Deleting Security Group
	 * @param securityGroupId
	 * @return
	 */
	public ActionResponse delete(String securityGroupId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(securityGroupId), "parameter `securityGroupId` should not be empty");
		return deleteWithResponse(uri("/security-groups/%s", securityGroupId)).execute();
	}
	
	/**
	 * Creating a Security Group Rule
	 * @param securityGroupRuleCreate
	 * @return
	 */
	public SecurityGroupRule createSecurityGroupRule(SecurityGroupRule securityGroupRuleCreate){
		Preconditions.checkNotNull(securityGroupRuleCreate, "parameter `securityGroupRuleCreate` should not be null");
		Preconditions.checkNotNull(securityGroupRuleCreate.getSecurityGroupId(), "parameter `SecurityGroupId` should not be null");
		Preconditions.checkNotNull(securityGroupRuleCreate.getDirection(), "parameter `Direction` should not be empty");
		return post(SecurityGroupRule.class, uri("/security-group-rules")).entity(securityGroupRuleCreate).execute();
	}
	
	/**
	 * Querying Security Group Rule Details
	 * @param securityGroupRuleId
	 * @return
	 */
	public SecurityGroupRule getSecurityGroupRule(String securityGroupRuleId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(securityGroupRuleId), "parameter `securityGroupRuleId` should not be empty");
		return get(SecurityGroupRule.class, uri("/security-group-rules/%s",securityGroupRuleId)).execute();
	}
	
	/**
	 * Querying Security Group Rules with filter
	 * @param filteringParams
	 * @return
	 */
	public List<? extends SecurityGroupRule> listSecurityGroupRules(Map<String, String> filteringParams) {
		Invocation<SecurityGroupRules> securityGroupRulesInvocation = get(SecurityGroupRules.class, uri("/security-group-rules"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	securityGroupRulesInvocation = securityGroupRulesInvocation.param(entry.getKey(), entry.getValue());
            }
        }
		return securityGroupRulesInvocation.execute().getList();
	}

	/**
	 * Querying Security Group Rules with filter
	 * @return
	 */
	public List<? extends SecurityGroupRule> listSecurityGroupRules(){
		return listSecurityGroupRules(null);
	}
	
	/**
	 * Deleting Security Group Rule
	 * @param securityGroupRuleId
	 * @return
	 */
	public ActionResponse deleteSecurityGroupRule(String securityGroupRuleId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(securityGroupRuleId), "parameter `securityGroupRuleId` should not be empty");
		return deleteWithResponse(uri("/security-group-rules/%s", securityGroupRuleId)).execute();
	}

}
