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

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.networking.domain.NeutronVpn;

@Test
public class VpnServiceTest extends AbstractTest{

	@Override
	protected Service service() {
		return Service.NETWORK;
	}
	
	private static final String VPN_CREATE_JSON = "/network/vpn_create.json";
    private static final String VPN_UPDATE_JSON = "/network/vpn_update.json";
    private static final String	VPN_LIST_JSON = "/network/vpn_list.json";
    private static final String VPN_DETAIL_JSON = "/network/vpn_detail.json";

    public void testCreate() throws IOException{
    	respondWith(VPN_CREATE_JSON);
    	final String name = "vpn_test";
    	final String routerId = "47bb383c-4b93-4d96-978a-ca88d770c421";
    	final String description = "test the vpn service";
    	final String  vpnId = "66e3b16c-8ce5-40fb-bb49-ab6d8dc3f2aa";    	
    	NeutronVpn create = NeutronVpn.builder()
				.name(name)
				.routerId(routerId)			
				.adminStateUp(true)
				.description(description)
				.build();
    	NeutronVpn res = osv3().networking().vpns().create(create);
    	assertNotNull(vpnId.equals(res.getId()));
    	
    } 
    
    public void testUpdate() throws IOException{
    	respondWith(VPN_UPDATE_JSON);
    	final String name = "vpn_test";
    	final String  vpnId = "881b7b30-4efb-407e-a162-5630a7af3595"; 
    	final String routerId = "47bb383c-4b93-4d96-978a-ca88d770c421";
    	final String description = "test the vpn service";
    	NeutronVpn update = NeutronVpn.builder()
				.name(name)
				.routerId(routerId)			
				.adminStateUp(true)
				.description(description)
				.build();
    	NeutronVpn res = osv3().networking().vpns().update(update, vpnId);
    	assertNotNull(vpnId.equals(res.getId()));
    	
    } 
    
    public void testList() throws IOException{
    	respondWith(VPN_LIST_JSON);
    	final Integer size =  1;
    	List<NeutronVpn> res = osv3().networking().vpns().list(null);
    	assertNotNull(res.size() == size);
    	
    } 
    
    public void testGet() throws IOException{
    	respondWith(VPN_DETAIL_JSON);
    	final String vpnId = "66e3b16c-8ce5-40fb-bb49-ab6d8dc3f2aa";
    	NeutronVpn res = osv3().networking().vpns().get(vpnId);
    	assertNotNull(res);
    	
    } 
    
    public void testDelete() throws IOException{
    	respondWith(200);
    	final String vpnId = "66e3b16c-8ce5-40fb-bb49-ab6d8dc3f2aa";
    	 ActionResponse res = osv3().networking().vpns().delete(vpnId);
    	assertNotNull(200 == res.getCode());
    	
    } 
}
