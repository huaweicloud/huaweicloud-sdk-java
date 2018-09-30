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
import com.huawei.openstack4j.api.networking.ext.IpsecPolicyService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.NeutronIpsecPolicy;
import com.huawei.openstack4j.openstack.networking.domain.NeutronIpsecPolicy.NeutronIpsecPolicies;

public class IpsecPolicyServiceImpl extends BaseNetworkingServices implements IpsecPolicyService{

	private static final String API_PATH = "/vpn/ipsecpolicies";
	private static final String filter = "fields";
	
	@Override
	public NeutronIpsecPolicy create(NeutronIpsecPolicy model) {
		return post(NeutronIpsecPolicy.class, uri(API_PATH)).entity(model).execute();
	}

	@Override
	public List<NeutronIpsecPolicy> list(String field) {
		return get(NeutronIpsecPolicies.class, uri(API_PATH)).param(filter , field).execute().getList();
	}

	@Override
	public NeutronIpsecPolicy get(String policyId) {
		checkArgument(!Strings.isNullOrEmpty(policyId),"parameter `policyId` should not be empty");			
		return get(NeutronIpsecPolicy.class, uri(API_PATH+"/%s",policyId)).execute();
	}

	@Override
	public ActionResponse delete(String policyId) {
		checkArgument(!Strings.isNullOrEmpty(policyId),"parameter `policyId` should not be empty");
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, 
				uri(API_PATH+"/%s", policyId)).executeWithResponse());
	}

	@Override
	public NeutronIpsecPolicy update(String policyId, NeutronIpsecPolicy model) {
		checkArgument((null != model),"parameter `model` should not be null");
		checkArgument(!Strings.isNullOrEmpty(policyId),"parameter `policyId` should not be empty");
		return put(NeutronIpsecPolicy.class, uri(API_PATH+"/%s",policyId)).entity(model).execute();
	}

}
