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
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelist;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelist.NeutronWhitelists;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelistUpdate;

public interface LbWhitelistV2Service extends RestService{

	/**
	 * 查询whitelist详情。
	 * @param whitelistId
	 * @return
	 */
	NeutronWhitelist get(String whitelistId);
	
	/**
	 * 查询whitelist
	 * @return
	 */
	NeutronWhitelists	list();

	/**
	 * 查询whitelist
	 * @return
	 */
	List<? extends NeutronWhitelist> list(Map<String, String> filteringParams);

	/**
	 * 创建whitelist
	 * @param model
	 * @return
	 */
	NeutronWhitelist create(NeutronWhitelist model);
	
	/**
	 * 更新whitelist
	 * @param model
	 * @param whitelistId
	 * @return
	 */
	NeutronWhitelist update(NeutronWhitelistUpdate model,String whitelistId);
	
	/**
	 * 删除whitelist
	 * @param whitelistId
	 * @return
	 */
	ActionResponse delete(String whitelistId);
}
