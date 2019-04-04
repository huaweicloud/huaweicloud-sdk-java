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
import com.huawei.openstack4j.api.networking.ext.LbWhitelistV2Service;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelist;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelist.NeutronWhitelists;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelistUpdate;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

public class LbWhiteV2listServiceImpl extends  BaseNetworkingServices implements LbWhitelistV2Service {

	private static final String API_PATH = "/lbaas/whitelists";
	
	@Override
	public NeutronWhitelist get(String whitelistId) {
		checkArgument(!Strings.isNullOrEmpty(whitelistId),"parameter `whitelistId` should not be empty");
		return get(NeutronWhitelist.class, uri(API_PATH+"/%s",whitelistId)).execute();
	}

	@Override
	public NeutronWhitelists list() {
		return get(NeutronWhitelists.class, uri(API_PATH)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends NeutronWhitelist> list(Map<String, String> filteringParams) {
		Invocation<NeutronWhitelists> serverInvocation = get(NeutronWhitelists.class, uri(API_PATH));
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return serverInvocation.execute().getList();
	}

	@Override
	public NeutronWhitelist create(NeutronWhitelist model) {
		checkArgument(!Strings.isNullOrEmpty(model.getListenerId()),"parameter `listenerId` should not be empty");
		return post(NeutronWhitelist.class, uri(API_PATH)).entity(model).execute();
	}

	@Override
	public NeutronWhitelist update(NeutronWhitelistUpdate model, String whitelistId) {
		checkArgument(!Strings.isNullOrEmpty(whitelistId),"parameter `whitelistId` should not be empty");
		checkArgument(null != model ,"parameter `updateModel` should not be null");
		return put(NeutronWhitelist.class, uri(API_PATH+"/%s",whitelistId)).entity(model).execute();
	}

	@Override
	public ActionResponse delete(String whitelistId) {
		checkArgument(!Strings.isNullOrEmpty(whitelistId),"parameter `whitelistId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(Void.class, uri(API_PATH+"/%s",whitelistId)).executeWithResponse());	
	}

}
