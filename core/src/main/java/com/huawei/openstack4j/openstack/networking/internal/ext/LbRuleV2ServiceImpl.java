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
package com.huawei.openstack4j.openstack.networking.internal.ext;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.networking.ext.LbRuleV2Service;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRule;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRule.NeutronRules;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRuleUpdate;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

public class LbRuleV2ServiceImpl extends  BaseNetworkingServices implements LbRuleV2Service {

	private static final String API_PATH = "/lbaas/l7policies";
	
	@Override
	public NeutronRules list(String l7policyId) {
		return get(NeutronRules.class, uri(API_PATH+"/%s/rules",l7policyId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends NeutronRule> list(String l7policyId, Map<String, String> filteringParams) {
		Invocation<NeutronRules> serverInvocation = get(NeutronRules.class, uri(API_PATH+"/%s/rules",l7policyId));
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return serverInvocation.execute().getList();
	}

	@Override
	public NeutronRule get(String l7policyId , String ruleId) {
		checkArgument(!Strings.isNullOrEmpty(l7policyId),"parameter `policyId` should not be empty");	
		checkArgument(!Strings.isNullOrEmpty(ruleId),"parameter `ruleId` should not be empty");	
		
		return get(NeutronRule.class, uri(API_PATH+"/%s/rules/%s",l7policyId,ruleId)).execute();
	}

	@Override
	public NeutronRule create(NeutronRule ruleModel , String l7policyId) {
		checkArgument((ruleModel != null),"parameter `ruleModel` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(ruleModel.getCompareType()),"parameter `compare-type` should not be empty");
		checkArgument(ruleModel.getType() != null,"parameter `type` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(ruleModel.getValue()),"parameter `value` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(l7policyId),"parameter `l7policyId` should not be empty");
		//check(ruleModel,l7policyId);
		return post(NeutronRule.class, uri(API_PATH+"/%s/rules", l7policyId)).entity(ruleModel).execute();
	}

	@Override
	public NeutronRule update(NeutronRuleUpdate updateModel , String l7policyId , String ruleId) {
		checkArgument((updateModel != null),"parameter `ruleUpdateModel` should not be empty");
		return put(NeutronRule.class, uri(API_PATH+"/%s/rules/%s", l7policyId , ruleId)).entity(updateModel).execute();
	}

	@Override
	public ActionResponse delete(String l7policyId , String ruleId) {
		checkArgument(!Strings.isNullOrEmpty(l7policyId),"parameter `l7policyId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(ruleId),"parameter `l7policyId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(Void.class, uri(API_PATH+"/%s/rules/%s",l7policyId , ruleId)).executeWithResponse());		
	}
	
	/*private void check(Rule ruleModel , String l7policyId){
		//the parameter 'type' can not be repeated
		Rules rules = listLbRules(l7policyId);
		if(rules != null && !rules.getList().isEmpty()){
			Iterator<Rule> it = rules.getList().iterator();
			if(it.hasNext()){
				Rule next = it.next();
				if((ruleModel.getType()).equals(next.getType())){
					throw new IllegalArgumentException("the parameter 'type' can not be repeated ");
				}
			}
		}
	}*/

}
