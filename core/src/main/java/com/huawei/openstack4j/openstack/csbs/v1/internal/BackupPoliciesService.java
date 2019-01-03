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
package com.huawei.openstack4j.openstack.csbs.v1.internal;

import static com.google.common.base.Preconditions.checkArgument;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.csbs.v1.domain.Policy;
import com.huawei.openstack4j.openstack.csbs.v1.domain.PolicyUpdate;
import com.huawei.openstack4j.openstack.csbs.v1.domain.Policy.Policies;
import com.huawei.openstack4j.openstack.csbs.v1.domain.PolicyCreate;

public class BackupPoliciesService extends BaseCloudServerBackupService {

	/**
	 * 创建备份策略
	 * @param policyCreate
	 * @return
	 */
	public Policy create(PolicyCreate policyCreate){
		checkArgument(!Strings.isNullOrEmpty(policyCreate.getName()),"parameter `name` should not be empty");
		checkArgument(policyCreate.getParameters() != null,"parameter `parameters` should not be empty");
		checkArgument(policyCreate.getResources()!= null && policyCreate.getResources().size() > 0,"parameter `resources` should not be empty");
		checkArgument(policyCreate.getScheduledOperations()!= null && policyCreate.getScheduledOperations().size() > 0,"parameter `scheduled_operations` should not be empty");
		return post(Policy.class, uri("/policies")).entity(policyCreate).execute();
	}
	
	/**
	 * 查询备份策略列表
	 * @return
	 */
	public List<Policy> list(){
		return get(Policies.class, uri("/policies")).execute().getList();
	}

	/**
	 * 查询备份策略列表
	 * @return
	 */
	public List<Policy> list(Map<String,String> filteringParams){
		Invocation<Policies> policyInvocation = get(Policies.class, "/policies");
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				policyInvocation = policyInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return policyInvocation.execute().getList();
	}
	/**
	 * 查询单个备份策略
	 * @param policyId
	 * @return
	 */
	public Policy get(String policyId){
		checkArgument(!Strings.isNullOrEmpty(policyId),"parameter `policyId` should not be empty");
		return get(Policy.class, uri("/policies/%s", policyId)).execute();
	}
	
	/**
	 * 更新备份策略
	 * @param policyId
	 * @param policyUpdate
	 * @return
	 */
	public Policy update(String policyId,PolicyUpdate policyUpdate){
		checkArgument(!Strings.isNullOrEmpty(policyId),"parameter `policyId` should not be empty");
		checkArgument(policyUpdate != null,"parameter `policyUpdate` should not be empty");
		return put(Policy.class, uri("/policies/%s", policyId)).entity(policyUpdate).execute();
	}
	
	/**
	 * 删除备份策略
	 * @param policyId
	 * @return
	 */
	public ActionResponse delete(String policyId) {
		checkArgument(!Strings.isNullOrEmpty(policyId),"parameter `policyId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(Void.class, uri("/policies/%s", policyId)).executeWithResponse());
	}
	
}
