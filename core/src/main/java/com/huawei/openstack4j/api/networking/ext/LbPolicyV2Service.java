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
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronL7Policy;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronL7Policy.NeutronL7Policies;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronL7PolicyUpdate;

public interface LbPolicyV2Service extends RestService{

	/**
	 * 查询转发策略。
	 * @return
	 */	
	NeutronL7Policies 	list();

	/**
	 * 查询转发策略。
	 * @return
	 */
	List<? extends NeutronL7Policy> list(Map<String, String> filteringParams);

	/**
	 * 查询转发策略详情
	 * @param policyId
	 * @return
	 */
	NeutronL7Policy    get(String policyId);
	
	/**
	 * 创建转发策略。
	 * @param model
	 * @return
	 */
	NeutronL7Policy  create(NeutronL7Policy model);
	
	/**
	 * 更新转发策略
	 * @param l7PolicyUpdate
	 * @param l7policyId
	 * @return
	 */
	NeutronL7Policy	update(NeutronL7PolicyUpdate l7PolicyUpdate , String l7policyId);
	
	/**
	 * 删除转发策略
	 * @param l7policyId
	 * @return
	 */
	ActionResponse 	delete(String	l7policyId);
}
