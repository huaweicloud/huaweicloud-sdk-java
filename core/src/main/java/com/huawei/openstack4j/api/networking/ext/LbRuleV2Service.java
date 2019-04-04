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
package com.huawei.openstack4j.api.networking.ext;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRule;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRule.NeutronRules;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRuleUpdate;

public interface LbRuleV2Service extends RestService {
	
	/**
	 * 查询转发规则
	 * @return
	 */
	NeutronRules list(String l7policyId);

	/**
	 * 查询转发规则
	 * @return
	 */
	List<? extends NeutronRule> list(String l7policyId, Map<String, String> filteringParams);

	/**
	 * 查询转发规则详情。
	 * @param ruleId
	 * @return
	 */
	NeutronRule get(String l7policyId , String ruleId);
	
	/**
	 * 创建转发规则
	 * @param ruleModel
	 * @return
	 */
	NeutronRule create(NeutronRule ruleModel , String l7policyId);
	
	/**
	 * 更新转发规则。
	 * @param updateModel
	 * @param ruleId
	 * @return
	 */
	NeutronRule update(NeutronRuleUpdate updateModel , String l7policyId ,	String ruleId);
	
	/**
	 * 删除转发规则。
	 * @param ruleId
	 * @return
	 */
	ActionResponse delete(String l7policyId , String ruleId);
}
