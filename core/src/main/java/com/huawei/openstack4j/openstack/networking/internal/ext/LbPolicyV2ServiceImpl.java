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
import com.huawei.openstack4j.api.networking.ext.LbPolicyV2Service;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronL7Policy;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronL7Policy.NeutronL7Policies;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronL7PolicyUpdate;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

public class LbPolicyV2ServiceImpl extends  BaseNetworkingServices implements LbPolicyV2Service {

	private static final String API_PATH = "/lbaas/l7policies";
	@Override
	public NeutronL7Policies list() {		
		return get(NeutronL7Policies.class, uri(API_PATH)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends NeutronL7Policy> list(Map<String, String> filteringParams) {
		Invocation<NeutronL7Policies> serverInvocation = get(NeutronL7Policies.class, API_PATH);
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return serverInvocation.execute().getList();
	}

	@Override
	public NeutronL7Policy get(String policyId) {
		checkArgument(!Strings.isNullOrEmpty(policyId),"parameter `policyId` should not be empty");			
		return get(NeutronL7Policy.class, uri(API_PATH+"/%s",policyId)).execute();
	}

	@Override
	public NeutronL7Policy create(NeutronL7Policy model) {
		checkArgument((model != null),"parameter `L7Policy` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(model.getListenerId()),"parameter `listernerId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(model.getAction()),"parameter `action` should not be empty");
//		checkArgument(!Strings.isNullOrEmpty(model.getRedirectPoolId()),"parameter `redirectPoolId` should not be empty");
		//check(model);
		return post(NeutronL7Policy.class, uri(API_PATH)).entity(model).execute();
	}


	

	@Override
	public NeutronL7Policy update(NeutronL7PolicyUpdate l7PolicyUpdate , String l7policyId) {
		checkArgument((l7PolicyUpdate != null),"parameter `l7PolicyUpdate` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(l7policyId),"parameter `l7policyId` should not be empty");
		return put(NeutronL7Policy.class, uri(API_PATH+"/%s",l7policyId)).entity(l7PolicyUpdate).execute();
	}

	@Override
	public ActionResponse delete(String l7policyId) {		
		checkArgument(!Strings.isNullOrEmpty(l7policyId),"parameter `l7policyId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(Void.class, uri(API_PATH+"/%s",l7policyId)).executeWithResponse());			
		
	}
	
	/*private void check(L7Policy model){		
		String listenerId =  model.getListenerId();
		NeutronListenerV2 listerner = get(NeutronListenerV2.class, uri("/lbaas/listeners/%s",listenerId)).execute();
		L7Policies policies = listL7Policies();
		ListenerProtocol protocol = listerner.getProtocol();
		if(ListenerProtocol.TERMINATED_HTTPS.equals(protocol) || ListenerProtocol.HTTP.equals(protocol)){
			throw new IllegalArgumentException("parameter protocol  can only be used 'TERMINATED_HTTPS' or 'HTTP' ");
		}
		if(model.getRedirectPoolId().equals(listerner.getDefaultPoolId())){
			throw new IllegalArgumentException("parameter redirect_pool can not be the listener's default_pool");
		}
		if(policies !=null && !policies.getList().isEmpty()){
			List<L7Policy> list = policies.getList();
			Iterator<L7Policy> it = list.iterator();
			if(it.hasNext()){
				if(!model.getListenerId().equals(it.next().getListenerId())){
					String redirectPoolId = it.next().getRedirectPoolId();
					if(model.getRedirectPoolId().equals(redirectPoolId)){
						throw new IllegalArgumentException("parameter redirect_pool can not be userd by another listener's l7policy");
					}
				}								
			}
		}
	}*/

}
