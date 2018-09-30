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
package com.huawei.openstack4j.api.network;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRule;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRule.NeutronRules;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRule.Type;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRuleUpdate;

@Test(suiteName="Network/Rule", enabled = true)
public class L7RuleV2Test extends AbstractTest {

	private static final String L7RULE_CREATE_JSON = "/network/l7rule_create.json";
    private static final String L7RULE_UPDATE_JSON = "/network/l7rule_update.json";
    private static final String	L7RULE_LIST_JSON = "/network/l7rule_list.json";
    private static final String L7RULE_DETAIL_JSON = "/network/l7rule_detail.json";
    
    
    public void testCreateL7Rule() throws IOException{
    	respondWith(L7RULE_CREATE_JSON);
    	String compareType = "EQUAL_TO";
    	String value = "/bbb.html";
    	String  policyId = "5ae0e1e7-5f0f-47a1-b39f-5d4c428a1586";
    	NeutronRule model = NeutronRule.builder().type(Type.PATH).compareType(compareType).value(value).build();
    	 NeutronRule res = osv3().networking().lbaasV2().lbRule().create(model, policyId);
    	assertNotNull(res);
    	
    }
    
	public void testUpdateL7Rule() throws IOException{
		respondWith(L7RULE_UPDATE_JSON);
		String compareType = "STARTS_WITH";
		String value = "/ccc.html";
		String polciyId = "5ae0e1e7-5f0f-47a1-b39f-5d4c428a1586";
		String ruleId = "c6f457b8-bf6f-45d7-be5c-a3226945b7b1";
		NeutronRuleUpdate model = NeutronRuleUpdate.builder().compareType(compareType).value(value).build();
	    NeutronRule res = osv3().networking().lbaasV2().lbRule().update(model, polciyId, ruleId);
	    assertNotNull(res);
    	
	}
	public void testDetailL7Rule() throws IOException{
		respondWith(L7RULE_DETAIL_JSON);
		String ruleId = "67d8a8fa-b0dd-4bd4-a85b-671db19b2ef3";
		String polciyId = "5ae0e1e7-5f0f-47a1-b39f-5d4c428a1586";
		NeutronRule res = osv3().networking().lbaasV2().lbRule().get(polciyId, ruleId);
		assertTrue(ruleId.equals(res.getId()));
    }
    
	public void testListL7Rule() throws IOException{
		respondWith(L7RULE_LIST_JSON);
		int size = 2;
		String polciyId = "5ae0e1e7-5f0f-47a1-b39f-5d4c428a1586";
		NeutronRules res = osv3().networking().lbaasV2().lbRule().list(polciyId);
		 assertTrue(res.getList().size() == size);
	    	
	}
	public void testDeleteL7Rule() throws IOException{
		int code = 200;
		respondWith(code);	
		String ruleId = "67d8a8fa-b0dd-4bd4-a85b-671db19b2ef3";
		String polciyId = "5ae0e1e7-5f0f-47a1-b39f-5d4c428a1586";		
		 ActionResponse res = osv3().networking().lbaasV2().lbRule().delete(polciyId, ruleId);
		 assertTrue(res.getCode() == code);
	}
	
	
	
	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}
