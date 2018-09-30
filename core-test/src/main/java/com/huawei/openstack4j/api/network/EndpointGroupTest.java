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
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.networking.domain.VpnEndpoint;
import com.huawei.openstack4j.openstack.networking.domain.VpnEndpoint.EndpointType;

@Test
public class EndpointGroupTest extends AbstractTest{

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

	private static final String ENDPOINT_CREATE_JSON = "/network/endpoint_create.json";
    private static final String ENDPOINT_UPDATE_JSON = "/network/endpoint_update.json";
    private static final String	ENDPOINT_LIST_JSON = "/network/endpoint_list.json";
    private static final String ENDPOINT_DETAIL_JSON = "/network/endpoint_detail.json";

    public void testCreate() throws IOException{
    	respondWith(ENDPOINT_CREATE_JSON);
    
    	final String  id = "5291b189-fd84-46e5-84bd-78f40c05d69c";   	
    	ArrayList<String> list = new ArrayList<String>();
    	final String net_one = "10.2.0.1/24";
    	final String net_two = "10.3.0.1/24";
		list.add(net_one);
		list.add(net_two);
		final String name = "peers";
    	VpnEndpoint endpointModel = VpnEndpoint.builder().endpoints(list)
				.type(EndpointType.cidr)
				.name(name)
				.build();
    	 VpnEndpoint res = osv3().networking().vpnEndpointGroups().create(endpointModel);
    	assertNotNull(id.equals(res.getId()));
    	
    } 
    
    public void testUpdate() throws IOException{
    	respondWith(ENDPOINT_UPDATE_JSON);
    	final String description = "endpoint description";
    	final String  id = "6ecd9cf3-ca64-46c7-863f-f2eb1b9e838a";    	
    	VpnEndpoint endpointUpdate = VpnEndpoint.builder().description(description).build();
    	VpnEndpoint res = osv3().networking().vpnEndpointGroups().update(id, endpointUpdate);
    	assertNotNull(id.equals(res.getId()));
    	
    } 
    
    public void testList() throws IOException{
    	respondWith(ENDPOINT_LIST_JSON);
    	List<VpnEndpoint> res = osv3().networking().vpnEndpointGroups().list(null);
    	assertNotNull(res.size()==2);
    	
    } 
    
    public void testGet() throws IOException{
    	respondWith(ENDPOINT_DETAIL_JSON);
    	final String id = "6ecd9cf3-ca64-46c7-863f-f2eb1b9e838a";
    	VpnEndpoint res = osv3().networking().vpnEndpointGroups().get(id);
    	assertNotNull(res);
    	
    } 
    
    public void testDelete() throws IOException{
    	respondWith(200);
    	final String id = "66e3b16c-8ce5-40fb-bb49-ab6d8dc3f2aa";
    	 ActionResponse res = osv3().networking().vpnEndpointGroups().delete(id);
    	assertNotNull(200 == res.getCode());
    	
    } 
}
