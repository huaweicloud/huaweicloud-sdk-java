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
package com.huawei.openstack4j.api.deh;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.deh.domain.*;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHost.DedicatedHosts;

@Test
public class DedicatedHostServiceTest extends AbstractTest{
	
	private static final String	DEH_LISTS = "/deh/deh_lists.json";
    private static final String DEH_DETAIL = "/deh/deh_detail.json";
    private static final String	DEH_CREATE = "/deh/deh_create.json";
    private static final String DEH_LIST_SERVER = "/deh/deh_list_server.json";
    private static final String DEH_LIST_TYPE = "/deh/deh_list_type.json";
    private static final String DEH_QUOTA_SET = "/deh/deh_quota_set.json";

    @Test
    public void testCreate() throws IOException{
    	respondWith(DEH_CREATE);
    	DedicatedHostCreate create = DedicatedHostCreate.builder()
    			.availabilityZone("az1.dc1")
    			.name("test_create_host")
    			.autoPlacement(AutoPlacement.off)
    			.hostType("h1")
    			.quantity(2)			
    			.build();
    	List<String> res = osv3().deh().dedicatedHosts().create(create);   					
    	assertNotNull(2 == res.size());
    	
    }
    
    @Test
	public void testList() throws IOException{
		respondWith(DEH_LISTS);
		DedicatedHosts res = osv3().deh().dedicatedHosts().list();
	    assertNotNull(25 == res.getTotal());
    	
	}
    @Test
	public void testget() throws IOException{
		respondWith(DEH_DETAIL);
		String id = "d84f345c-80a1-4fa2-a39c-d0d397c3f09a";
		 DedicatedHost res = osv3().deh().dedicatedHosts().get(id);
		assertTrue(res != null);
    }
    @Test
	public void testListServer() throws IOException{
		respondWith(DEH_LIST_SERVER);
		String id = "a78fb3eb-1654-4710-8742-3fc49d5f04f8";
		 List<? extends Server> res = osv3().deh().dedicatedHosts().list(id, "3", "222");
		 assertTrue(res != null);
	    	
	}

	@Test
	public void testUpdate(){
		respondWith(200);
		DedicatedHostUpdate hostUpdate = DedicatedHostUpdate.builder().name("test1").build();
		ActionResponse ac = osv3().deh().dedicatedHosts().update("test", hostUpdate);
		assertEquals(200, ac.getCode());
	}

	@Test
	public void testDelete(){
		respondWith(200);
		ActionResponse ac = osv3().deh().dedicatedHosts().delete("test");
		assertEquals(200, ac.getCode());
	}

	@Test
	public void testTypeList() throws IOException{
		respondWith(DEH_LIST_TYPE);
		List<DedicatedHostType> dedicatedHostTypes = osv3().deh().dedicatedHosts().listHostType("kvmxen.dc1");
		assertTrue(4 ==dedicatedHostTypes.size() );
	}

	@Test
	public  void testGetQuotaSet()throws IOException{
		respondWith(DEH_QUOTA_SET);
		QuotaSet quotaSet = osv3().deh().quotaSets().get("test");
		assertTrue(null !=quotaSet);
	}

	@Override
	protected Service service() {
		return Service.DEH;
	}
}
