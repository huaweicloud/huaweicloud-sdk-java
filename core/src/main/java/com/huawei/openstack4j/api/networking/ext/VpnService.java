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

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.networking.domain.NeutronVpn;

public interface VpnService  extends RestService{

	/**
	 * create the vpn Servcie
	 * @param model
	 * @return
	 */
	NeutronVpn create(NeutronVpn model);
	
	/**
	 * 
	 * @param fields
	 * @return
	 */
	List<NeutronVpn> list(String fields);
	
	/**
	 * delete vpn service
	 * @param serviceId
	 * @return
	 */
	ActionResponse delete(String serviceId);
	
	/**
	 * list vpn service by id
	 * @param serviceId
	 * @return
	 */
	NeutronVpn	get(String	serviceId);
	
	/**
	 * update  vpn servcie
	 * @param model
	 * @param serviceId
	 * @return
	 */
	NeutronVpn update(NeutronVpn model , String serviceId);
}
