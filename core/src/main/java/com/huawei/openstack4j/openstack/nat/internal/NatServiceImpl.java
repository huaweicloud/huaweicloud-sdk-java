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

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.nat.DnatRuleService;
import com.huawei.openstack4j.api.nat.NatGateWayService;
import com.huawei.openstack4j.api.nat.NatService;
import com.huawei.openstack4j.api.nat.SnatRulesService;

public class NatServiceImpl implements NatService {

	@Override
	public NatGateWayService natGateWays() {
		return Apis.get(NatGateWayService.class);
	}
	
	@Override
	public DnatRuleService dnatRules() {
		return Apis.get(DnatRuleService.class);
	}

	@Override
	public SnatRulesService snatRules() {
		return Apis.get(SnatRulesService.class);
	}

}
