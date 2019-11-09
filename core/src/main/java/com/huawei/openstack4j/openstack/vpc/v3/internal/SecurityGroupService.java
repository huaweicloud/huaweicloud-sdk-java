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
package com.huawei.openstack4j.openstack.vpc.v3.internal;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.vpc.v3.domain.*;

import java.util.Map;

public class SecurityGroupService extends BaseVirtualPrivateCloudService
{
    /**
     * Querying Security Groups
     * @return SecurityGroups
     */
    public SecurityGroups list(){
        return list(null);
    }

    /**
     * Querying Security Groups with filter
     * @param filteringParams 过滤参数
     * @return SecurityGroups
     */
    public SecurityGroups list(Map<String, String> filteringParams) {
        Invocation<SecurityGroups> securityGroupsInvocation = get(SecurityGroups.class, uri("/vpc/security-groups"));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                securityGroupsInvocation = securityGroupsInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return securityGroupsInvocation.execute();
    }

    /**
     * Creating a Security Group
     * @param securityGroupCreate 创建请求体
     * @return SecurityGroupResp
     */
    public SecurityGroupResp create(SecurityGroupCreate securityGroupCreate){
        Preconditions.checkNotNull(securityGroupCreate, "parameter `securityGroupCreate` should not be null");
        Preconditions.checkNotNull(securityGroupCreate.getName(), "parameter `securityGroup.name should not be empty");
        return post(SecurityGroupResp.class, uri("/vpc/security-groups")).entity(securityGroupCreate).execute();
    }

    /**
     * Querying Security Group Details
     * @param securityGroupId 安全组ID
     * @return SecurityGroupResp
     */
    public SecurityGroupResp get(String securityGroupId){
        Preconditions.checkArgument(!Strings.isNullOrEmpty(securityGroupId), "parameter `securityGroupId` should not be empty");
        return get(SecurityGroupResp.class, uri("/vpc/security-groups/%s", securityGroupId)).execute();
    }

    /**
     * Update Security Group
     * @param securityGroupId 安全组ID
     * @return SecurityGroupResp
     */
    public SecurityGroupResp update(String securityGroupId, SecurityGroupUpdate securityGroupUpdate){
        Preconditions.checkArgument(!Strings.isNullOrEmpty(securityGroupId), "parameter `securityGroupId` should not be empty");
        return put(SecurityGroupResp.class, uri("/vpc/security-groups/%s", securityGroupId)).entity(securityGroupUpdate).execute();
    }



    /**
     * Deleting Security Group
     * @param securityGroupId 安全组ID
     * @return ActionResponse
     */
    public ActionResponse delete(String securityGroupId){
        Preconditions.checkArgument(!Strings.isNullOrEmpty(securityGroupId), "parameter `securityGroupId` should not be empty");
        return deleteWithResponse(uri("/vpc/security-groups/%s", securityGroupId)).execute();
    }

    /**
     * Querying Security Group Rules with filter
     * @return List<? extends SecurityGroupRule>
     */
    public SecurityGroupRules listSecurityGroupRules(){
        return listSecurityGroupRules(null);
    }

    /**
     * Querying Security Group Rules with filter
     * @param filteringParams 过滤条件
     * @return List<? extends SecurityGroupRule>
     */
    public SecurityGroupRules listSecurityGroupRules(Map<String, String> filteringParams) {
        Invocation<SecurityGroupRules> securityGroupRulesInvocation = get(SecurityGroupRules.class, uri("/vpc/security-group-rules"));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                securityGroupRulesInvocation = securityGroupRulesInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return securityGroupRulesInvocation.execute();
    }

    /**
     * Creating a Security Group Rule
     * @param securityGroupRuleCreate 安全组规则创建请求体
     * @return SecurityGroupRule
     */
    public SecurityGroupRuleResp createSecurityGroupRule(SecurityGroupRuleCreate securityGroupRuleCreate){
        Preconditions.checkNotNull(securityGroupRuleCreate, "parameter `securityGroupRuleCreate` should not be null");
        Preconditions.checkNotNull(securityGroupRuleCreate.getSecurityGroupId(), "parameter `SecurityGroupId` should not be null");
        Preconditions.checkNotNull(securityGroupRuleCreate.getDirection(), "parameter `Direction` should not be empty");
        return post(SecurityGroupRuleResp.class, uri("/vpc/security-group-rules")).entity(securityGroupRuleCreate).execute();
    }

    /**
     * Querying Security Group Rule Details
     * @param securityGroupRuleId 安全组规则ID
     * @return SecurityGroupRule
     */
    public SecurityGroupRuleResp getSecurityGroupRule(String securityGroupRuleId){
        Preconditions.checkArgument(!Strings.isNullOrEmpty(securityGroupRuleId), "parameter `securityGroupRuleId` should not be empty");
        return get(SecurityGroupRuleResp.class, uri("/vpc/security-group-rules/%s",securityGroupRuleId)).execute();
    }

    /**
     * Deleting Security Group Rule
     * @param securityGroupRuleId 安全组规则ID
     * @return ActionResponse
     */
    public ActionResponse deleteSecurityGroupRule(String securityGroupRuleId){
        Preconditions.checkArgument(!Strings.isNullOrEmpty(securityGroupRuleId), "parameter `securityGroupRuleId` should not be empty");
        return deleteWithResponse(uri("/vpc/security-group-rules/%s", securityGroupRuleId)).execute();
    }

}
