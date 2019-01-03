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
import com.huawei.openstack4j.openstack.nat.domain.NatGateWay;
import com.huawei.openstack4j.openstack.nat.domain.NatGateWayCreate;
import com.huawei.openstack4j.openstack.nat.domain.NatGateWayUpdate;

@Test
public class NatGateWayServiceTest extends AbstractTest{
	private static final String	NAT_LISTS = "/nat/nat_lists.json";
    private static final String NAT_DETAIL = "/nat/nat_detail.json";
    private static final String	NAT_CREATE = "/nat/nat_create.json";
    private static final String NAT_UPDATE = "/nat/nat_update.json";
    
    
    public void testCreate() throws IOException{
    	respondWith(NAT_CREATE);
    	String id = "a78fb3eb-1654-4710-8742-3fc49d5f04f8";
    	String name ="testnat";
    	String description = "description";
    	String spec = "1";
    	String routerId = "12a5ead9-9b93-4ac9-b28e-ba0992ac8a85";
    	String internalNetworkId = "743f90fd-63bb-4e74-9a24-5c579cfa6d31";
    	NatGateWayCreate create = NatGateWayCreate.builder()
    			.name(name)
    			.description(description)
    			.spec(spec)
    			.routerId(routerId)
    			.internalNetworkId(internalNetworkId)
    			.build();
    	NatGateWay res = osv3().nat().natGateWays().create(create);    					
    	assertNotNull(id.equals(res.getId()));
    	
    }
    
	public void testUpdate() throws IOException{
		respondWith(NAT_UPDATE);
		String id = "d84f345c-80a1-4fa2-a39c-d0d397c3f09a";
		String name = "updatetest";
		NatGateWayUpdate update = NatGateWayUpdate.builder()
				.name(name)
				.build();	
	    NatGateWay res = osv3().nat().natGateWays().update(update, id);
	    assertNotNull(id.equals(res.getId()));
    	
	}
	public void testLists() throws IOException{
		respondWith(NAT_LISTS);	
		Map<String,String>  filteringParams = new HashMap<String,String>();
		List<NatGateWay> list = osv3().nat().natGateWays().list(filteringParams);
		assertTrue(list.size()==2);
    }
    
	public void testList() throws IOException{
		respondWith(NAT_DETAIL);
		String id = "a78fb3eb-1654-4710-8742-3fc49d5f04f8";
		NatGateWay res = osv3().nat().natGateWays().get(id);
		 assertTrue(id.equals(res.getId()));
	    	
	}
	public void testDelete() throws IOException{
		int code = 200;
		respondWith(code);	
		String id = "35cb8516-1173-4035-8dae-0dae3453f37f";
		 ActionResponse res = osv3().nat().natGateWays().delete(id);
		 assertTrue(res.getCode() == code);
	}
	
	@Override
	protected Service service() {
		return Service.NAT;
	}
}
