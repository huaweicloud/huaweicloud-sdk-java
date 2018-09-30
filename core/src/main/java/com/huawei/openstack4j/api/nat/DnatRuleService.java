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
import com.huawei.openstack4j.openstack.nat.domain.DnatRule;
import com.huawei.openstack4j.openstack.nat.domain.DnatRuleCreate;

public interface DnatRuleService extends RestService{

	/**
	 * 创建DNAT规则
	 * @param dNatRuleCreate
	 * @return
	 */
	DnatRule create(DnatRuleCreate dNatRuleCreate);
	
	/**
	 * 查询DNAT规则列表
	 * @param filteringParams
	 * @return
	 */
	List<DnatRule> list(Map<String, String> filteringParams);
	
	/**
	 * 查询DNAT规则列表
	 * @return
	 */
	List<DnatRule> list();
	
	/**
	 * 查询指定的DNAT规则详情
	 * @param dnatRuleId
	 * @return
	 */
	DnatRule get(String dnatRuleId);
	
	/**
	 * 删除DNAT规则
	 * @param dnatRuleId
	 * @return
	 */
	ActionResponse delete(String dnatRuleId);
}
