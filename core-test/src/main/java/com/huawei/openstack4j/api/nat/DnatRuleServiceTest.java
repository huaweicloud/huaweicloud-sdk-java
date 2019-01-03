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
package com.huawei.openstack4j.api.nat;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.nat.domain.DnatRule;
import com.huawei.openstack4j.openstack.nat.domain.DnatRuleCreate;

@Test
public class DnatRuleServiceTest extends AbstractTest{

	private static final String	DNAT_LISTS = "/nat/dnat_lists.json";
    private static final String DNAT_DETAIL = "/nat/dnat_detail.json";
    private static final String	DNAT_CREATE = "/nat/dnat_create.json";
    
    
    public void testCreate() throws IOException{
    	respondWith(DNAT_CREATE);
    	String id = "79195d50-0271-41f1-bded-4c089b2502ff";
    	String floatingIpId ="Cf99c679-9f41-4dac-8513-9c9228e713e1";
    	String natGatewayId ="Dda3a125-2406-456c-a11f-598e10578541";
    	String portId ="Aa469561-daac-4c94-88f5-39366e5ea193";
    	String protocol = "TCP";
    	DnatRuleCreate create = DnatRuleCreate.builder()
    			.floatingIpId(floatingIpId).portId(portId).internalServicePort(993)
    			.natGatewayId(natGatewayId).externalServicePort(242).protocol(protocol).build();
				
    	DnatRule res = osv3().nat().dnatRules().create(create); 					
    	assertNotNull(id.equals(res.getId()));
    	
    }
    
	
	public void testLists() throws IOException{
		respondWith(DNAT_LISTS);	
		Map<String,String>  filteringParams = new HashMap<String,String>();
		List<DnatRule> res = osv3().nat().dnatRules().list(filteringParams);
		assertTrue(res.size()==2);
    }
    
	public void testList() throws IOException{
		respondWith(DNAT_DETAIL);
		String id = "Cf99c679-9f41-4dac-8513-9c9228e713e1";
		DnatRule res = osv3().nat().dnatRules().get(id);
		assertTrue(id.equals(res.getId()));
	    	
	}
	public void testDelete() throws IOException{
		int code = 200;
		respondWith(code);	
		String id = "79195d50-0271-41f1-bded-4c089b2502ff";
		 ActionResponse res = osv3().nat().dnatRules().delete(id);
		 assertTrue(res.getCode() == code);
	}
	
	@Override
	protected Service service() {
		return Service.NAT;
	}
}
