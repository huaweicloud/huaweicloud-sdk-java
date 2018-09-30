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
package com.huawei.openstack4j.openstack.deh.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.deh.DedicatedHostService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.openstack.compute.domain.NovaServer.Servers;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.deh.domain.*;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHost.DedicatedHosts;

public class DedicatedHostServiceImpl extends BaseDehServices implements DedicatedHostService{

	private static final String API_PATH = "/dedicated-hosts";
	private static final String AZ_PATH = "/availability-zone";
	private static final String HOSTTYPE_PATH = "/dedicated-host-types";

	@Override
	public List<String> create(DedicatedHostCreate host) {
		return post(DedicatedHostIds.class, uri(API_PATH)).entity(host).execute().getList();
	}

	@Override
	public DedicatedHosts list() {
		return get(DedicatedHosts.class, uri(API_PATH)).execute();
	}

	@Override
	public DedicatedHosts list(Map<String, String> filteringParams) {
		Invocation<DedicatedHosts> req = get(DedicatedHosts.class, uri(API_PATH));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute();
	}

	@Override
	public DedicatedHost get(String hostId) {
		checkArgument(!Strings.isNullOrEmpty(hostId),"parameter `hostId` should not be empty");
		return get(DedicatedHost.class, uri(API_PATH+"/%s",hostId)).execute();
	}

	@Override
	public List <? extends Server> list(String hostId , String limit, String marker) {
	
		checkArgument(!Strings.isNullOrEmpty(hostId),"parameter `hostId` should not be empty");
		Map<String,String> filteringParams = new HashMap<String,String>();
		filteringParams.put("limit", limit);
		filteringParams.put("marker", marker);
		return get(Servers.class, uri(API_PATH+"/%s"+"/servers",hostId)).params(filteringParams).execute().getList();
	}
	@Override
	public ActionResponse update(String hostId , DedicatedHostUpdate dedicatedHostUpdate){
		checkArgument(!Strings.isNullOrEmpty(hostId),"parameter `hostId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(put(Void.class, uri(API_PATH+"/%s", hostId)).entity(dedicatedHostUpdate)
						.executeWithResponse());
	}

	@Override
	public ActionResponse delete(String hostId){
		checkArgument(!Strings.isNullOrEmpty(hostId),"parameter `hostId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(Void.class, uri(API_PATH+"/%s", hostId)).executeWithResponse());
	}

	@Override
	public List<DedicatedHostType> listHostType(String availabilityZone){
		checkArgument(!Strings.isNullOrEmpty(availabilityZone),"parameter `availabilityZone` should not be empty");
		return (List<DedicatedHostType>) get(DedicatedHostType.DedicatedHostTypes.class, uri(AZ_PATH+"/%s"+HOSTTYPE_PATH,availabilityZone)).execute().getList();
	}

}
