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
import com.huawei.openstack4j.api.networking.ext.VpnEndpointGroupService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.VpnEndpoint;
import com.huawei.openstack4j.openstack.networking.domain.VpnEndpoint.VpnEndpoints;

public class VpnEndpointGroupServiceImpl extends BaseNetworkingServices implements VpnEndpointGroupService{

	private static final String API_PATH = "/vpn/endpoint-groups";
	private static final String filter = "fields";
	
	
	@Override
	public VpnEndpoint create(VpnEndpoint model) {
		return post(VpnEndpoint.class, uri(API_PATH)).entity(model).execute();
	}

	@Override
	public List<VpnEndpoint> list(String field) {
		return get(VpnEndpoints.class, uri(API_PATH)).param(filter , field).execute().getList();
	}

	@Override
	public VpnEndpoint get(String id) {
		checkArgument(!Strings.isNullOrEmpty(id),"parameter `id` should not be empty");			
		return get(VpnEndpoint.class, uri(API_PATH+"/%s",id)).execute();
	}

	@Override
	public ActionResponse delete(String id) {
		checkArgument(!Strings.isNullOrEmpty(id),"parameter `id` should not be empty");
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, 
				uri(API_PATH+"/%s", id)).executeWithResponse());
	}

	@Override
	public VpnEndpoint update(String id, VpnEndpoint model) {
		checkArgument((null != model),"parameter `model` should not be null");
		checkArgument(!Strings.isNullOrEmpty(id),"parameter `connectionId` should not be empty");
		return put(VpnEndpoint.class, uri(API_PATH+"/%s",id)).entity(model).execute();
	}

}
