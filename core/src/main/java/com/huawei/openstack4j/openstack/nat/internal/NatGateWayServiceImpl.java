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
package com.huawei.openstack4j.openstack.nat.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.nat.NatGateWayService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.nat.domain.NatGateWay;
import com.huawei.openstack4j.openstack.nat.domain.NatGateWay.NatGateWays;
import com.huawei.openstack4j.openstack.nat.domain.NatGateWayCreate;
import com.huawei.openstack4j.openstack.nat.domain.NatGateWayUpdate;

public class NatGateWayServiceImpl extends BaseNatServices implements NatGateWayService {
	
	private static final String API_PATH = "/nat_gateways";
	@Override
	public NatGateWay get(String natGateWayId) {
		checkArgument(!Strings.isNullOrEmpty(natGateWayId),"parameter `natGateWayId` should not be empty");
		return get(NatGateWay.class, uri(API_PATH+"/%s",natGateWayId)).execute();
	}
	@Override
	public List<NatGateWay> list(Map<String, String> filteringParams) {
		Invocation<NatGateWays> req = get(NatGateWays.class, uri(API_PATH));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute().getList();
	}
	
	@Override
	public NatGateWay create(NatGateWayCreate natGateWayModel) {
		return post(NatGateWay.class, uri(API_PATH)).entity(natGateWayModel).execute();
	}
	@Override
	public NatGateWay update(NatGateWayUpdate updateModel, String natGateWayId) {
		checkArgument((null != updateModel),"parameter `updateModel` should not be null");
		checkArgument(!Strings.isNullOrEmpty(natGateWayId),"parameter `natGateWayId` should not be empty");
		return put(NatGateWay.class, uri(API_PATH+"/%s",natGateWayId)).entity(updateModel).execute();
	}
	@Override
	public ActionResponse delete(String natGateWayId) {
		checkArgument(!Strings.isNullOrEmpty(natGateWayId),"parameter `natGateWayId` should not be empty");
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, 
				uri(API_PATH+"/%s", natGateWayId)).executeWithResponse());
	}
	@Override
	public List<NatGateWay> list() {
		return get(NatGateWays.class, uri(API_PATH)).execute().getList();
	}

}
