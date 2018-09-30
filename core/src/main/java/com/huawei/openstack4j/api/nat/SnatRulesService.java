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
package com.huawei.openstack4j.api.nat;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.nat.domain.SnatRule;
import com.huawei.openstack4j.openstack.nat.domain.SnatRuleCreate;

public interface SnatRulesService extends RestService{

	/**
	 * createa snat rule
	 * @param model
	 * @return
	 */
	SnatRule create(SnatRuleCreate model);
	
	/**
	 * list snat rules
	 * @param filteringParams
	 * @return
	 */
	List<SnatRule> list(Map<String, String> filteringParams);
	
	/**
	 * list snat rules
	 * @param filteringParams
	 * @return
	 */
	List<SnatRule> list();
	
	/**
	 * list snat rule
	 * @param snatRuleId
	 * @return
	 */
	SnatRule get(String snatRuleId);
	
	/**
	 * delete snat rule
	 * @param snatRuleId
	 * @return
	 */
	ActionResponse delete(String snatRuleId);
}
