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
package com.huawei.openstack4j.openstack.nat.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.nat.SnatRulesService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.nat.domain.SnatRule;
import com.huawei.openstack4j.openstack.nat.domain.SnatRule.SnatRules;
import com.huawei.openstack4j.openstack.nat.domain.SnatRuleCreate;

public class SnatRulesServiceImpl extends BaseNatServices implements SnatRulesService {

	private static final String API_PATH = "/snat_rules";
	
	@Override
	public SnatRule create(SnatRuleCreate model) {
		return post(SnatRule.class, uri(API_PATH)).entity(model).execute();
	}

	@Override
	public List<SnatRule> list(Map<String, String> filteringParams) {
		Invocation<SnatRules> req = get(SnatRules.class, uri(API_PATH));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute().getList();
	}

	@Override
	public SnatRule get(String snatRuleId) {
		checkArgument(!Strings.isNullOrEmpty(snatRuleId),"parameter `snatRuleId` should not be empty");
		return get(SnatRule.class, uri(API_PATH+"/%s",snatRuleId)).execute();
	}

	@Override
	public ActionResponse delete(String snatRuleId) {
		checkArgument(!Strings.isNullOrEmpty(snatRuleId),"parameter `snatRuleId` should not be empty");
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, 
				uri(API_PATH+"/%s", snatRuleId)).executeWithResponse());
	}

	@Override
	public List<SnatRule> list() {
		return get(SnatRules.class, uri(API_PATH)).execute().getList();
	}

}
