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
import com.huawei.openstack4j.openstack.nat.domain.SnatRule;
import com.huawei.openstack4j.openstack.nat.domain.SnatRuleCreate;

@Test
public class SnatRuleServiceTest extends AbstractTest{

	private static final String	SNAT_LISTS = "/nat/snat_lists.json";
    private static final String SNAT_DETAIL = "/nat/snat_detail.json";
    private static final String	SNAT_CREATE = "/nat/snat_create.json";
    
    
    public void testCreate() throws IOException{
    	respondWith(SNAT_CREATE);
    	String id = "5b95c675-69c2-4656-ba06-58ff72e1d338";
    	String floatingIpId ="3d12005c-5c45-4c0b-8f59-bf9b201c2626";
    	String natGatewayId ="4af3a862-328c-4b01-ae6e-0c6f4ef61631";
    	String networkid ="743f90fd-63bb-4e74-9a24-5c579cfa6d31";
    	SnatRuleCreate create = SnatRuleCreate.builder()
				.floatingIpId(floatingIpId)
				.natGatewayId(natGatewayId)
				.networkId(networkid)
				.build();
    	SnatRule res = osv3().nat().snatRules().create(create); 					
    	assertNotNull(id.equals(res.getId()));
    	
    }
    
	
	public void testLists() throws IOException{
		respondWith(SNAT_LISTS);	
		Map<String,String>  filteringParams = new HashMap<String,String>();
		List<SnatRule> list = osv3().nat().snatRules().list(filteringParams);
		assertTrue(list.size()==2);
    }
    
	public void testList() throws IOException{
		respondWith(SNAT_DETAIL);
		String id = "5b95c675-69c2-4656-ba06-58ff72e1d338";
		SnatRule res = osv3().nat().snatRules().get(id);
		 assertTrue(id.equals(res.getId()));
	    	
	}
	public void testDelete() throws IOException{
		int code = 200;
		respondWith(code);	
		String id = "a78fb3eb-1654-4710-8742-3fc49d5f04f8";
		 ActionResponse res = osv3().nat().snatRules().delete(id);
		 assertTrue(res.getCode() == code);
	}
	
	@Override
	protected Service service() {
		return Service.NAT;
	}
}
