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
package com.huawei.openstack4j.openstack.networking.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.networking.ext.VpnService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.NeutronVpn;
import com.huawei.openstack4j.openstack.networking.domain.NeutronVpn.NeutronVpns;

public class VpnServiceImpl	extends BaseNetworkingServices implements VpnService{
	
	private static final String API_PATH = "/vpn/vpnservices";
	private static final String filter = "fields";
	
	
	@Override
	public NeutronVpn create(NeutronVpn model) { 
		return post(NeutronVpn.class, uri(API_PATH)).entity(model).execute();
	}
	@Override
	public List<NeutronVpn> list(String fields) {
		return get(NeutronVpns.class, uri(API_PATH)).param(filter , fields).execute().getList();
	}
	@Override
	public ActionResponse delete(String serviceId) {
		checkArgument(!Strings.isNullOrEmpty(serviceId),"parameter `serviceId` should not be empty");
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, 
				uri(API_PATH+"/%s", serviceId)).executeWithResponse());
	}
	@Override
	public NeutronVpn get(String serviceId) {
		checkArgument(!Strings.isNullOrEmpty(serviceId),"parameter `serviceId` should not be empty");			
		return get(NeutronVpn.class, uri(API_PATH+"/%s",serviceId)).execute();
	}
	@Override
	public NeutronVpn update(NeutronVpn model ,String serviceId) {
		checkArgument((null != model),"parameter `updateModel` should not be null");
		checkArgument(!Strings.isNullOrEmpty(serviceId),"parameter `serviceId` should not be empty");
		return put(NeutronVpn.class, uri(API_PATH+"/%s",serviceId)).entity(model).execute();
	}

}
