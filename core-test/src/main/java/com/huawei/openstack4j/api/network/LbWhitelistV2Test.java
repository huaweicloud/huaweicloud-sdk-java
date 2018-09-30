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
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelist;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelist.NeutronWhitelists;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelistUpdate;

@Test
public class LbWhitelistV2Test extends AbstractTest {

	private static final String	WHITELIST_CREATE_JSON = "/network/whitelist_create.json";
    private static final String WHITELIST_UPDATE_JSON = "/network/whitelist_update.json";
    private static final String	WHITELIST_LIST_JSON = "/network/whitelist_list.json";
    private static final String WHITELIST_DETAIL_JSON = "/network/whitelist_detail.json";
    private static final String listenerId = "eabfefa3fd1740a88a47ad98e132d238";
    
    public void testCreateWhitelist() throws IOException{
    	respondWith(WHITELIST_CREATE_JSON);
    	String listenerId = "eabfefa3fd1740a88a47ad98e132d238";
    	NeutronWhitelist model = NeutronWhitelist.builder().listenerId(listenerId).enableWhitelist(true).build();
    	 NeutronWhitelist res = osv3().networking().lbaasV2().lbWhitelist().create(model);
    	assertNotNull(listenerId.equals(res.getListenerId()));
    	
    }
    
	public void testUpdateWhitelist() throws IOException{
		respondWith(WHITELIST_UPDATE_JSON);
		String whitelistId = "dcaf46f1-037c-4f63-a31f-e0c4c18032c7";
		String whiteList = "192.168.0.2";
		NeutronWhitelistUpdate model = NeutronWhitelistUpdate.builder().whitelist(whiteList).build();		
	    NeutronWhitelist res = osv3().networking().lbaasV2().lbWhitelist().update(model, whitelistId);
	    assertNotNull(listenerId.equals(res.getListenerId()));
    	
	}
	public void testDetailWhitelist() throws IOException{
		respondWith(WHITELIST_DETAIL_JSON);
		String whitelistId = "dcaf46f1-037c-4f63-a31f-e0c4c18032c7";
		 NeutronWhitelist res = osv3().networking().lbaasV2().lbWhitelist().get(whitelistId);
		assertTrue(listenerId.equals(res.getListenerId()));
    }
    
	public void testListWhitelist() throws IOException{
		respondWith(WHITELIST_LIST_JSON);
		NeutronWhitelists res = osv3().networking().lbaasV2().lbWhitelist().list();
		 assertTrue(res.getList().size() == 2);
	    	
	}
	public void testDeleteWhitelist() throws IOException{
		int code = 200;
		respondWith(code);	
		String whitelistId = "35cb8516-1173-4035-8dae-0dae3453f37f";
		 ActionResponse res = osv3().networking().lbaasV2().lbWhitelist().delete(whitelistId);
		 assertTrue(res.getCode() == code);
	}
	
	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}
