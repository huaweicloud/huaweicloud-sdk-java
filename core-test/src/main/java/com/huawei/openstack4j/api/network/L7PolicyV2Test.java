package com.huawei.openstack4j.api.network;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronL7Policy;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronL7Policy.NeutronL7Policies;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronL7PolicyUpdate;

@Test(suiteName="Network/L7Policy", enabled = true)
public class L7PolicyV2Test extends AbstractTest {
	
	private static final String L7POLICY_CREATE_JSON = "/network/l7policy_create.json";
    private static final String L7POLICY_UPDATE_JSON = "/network/l7policy_update.json";
    private static final String L7POLICY_LIST_JSON = "/network/l7policy_list.json";
    private static final String L7POLICY_DETAIL_JSON = "/network/l7policy_detail.json";
    
    
    public void testCreateL7Policy() throws IOException{
    	respondWith(L7POLICY_CREATE_JSON);
    	String listenerId = "e1310063-00de-4867-ab55-ccac4d9db364";
    	String redirectPoolId = "59eebd7b-c68f-4f8a-aa7f-e062e84c0690";
    	NeutronL7Policy model = NeutronL7Policy.builder().listenerId(listenerId).redirectPoolId(redirectPoolId).build();
    	NeutronL7Policy res = osv3().networking().lbaasV2().lbPolicy().create(model);
    	assertNotNull(res);
    	assertEquals(res.getListenerId(), listenerId);
    }
    
	public void testUpdateL7Policy() throws IOException{
		respondWith(L7POLICY_UPDATE_JSON);
		String name = "test";
		String polciyId = "5ae0e1e7-5f0f-47a1-b39f-5d4c428a1586 ";
		NeutronL7PolicyUpdate model = NeutronL7PolicyUpdate.builder().name(name).build();
	    NeutronL7Policy res = osv3().networking().lbaasV2().lbPolicy().update(model, polciyId);
	    assertNotNull(res);
    	assertEquals(res.getName(), name);
	}
	public void testDetailL7Policy() throws IOException{
		respondWith(L7POLICY_DETAIL_JSON);
		String name = "l7policy-garry-1";
		String polciyId = "5ae0e1e7-5f0f-47a1-b39f-5d4c428a1586 ";
		NeutronL7Policy res = osv3().networking().lbaasV2().lbPolicy().get(polciyId);
		assertEquals(res.getName(),name);
    	
    }
    
	public void testListL7Policy() throws IOException{
		respondWith(L7POLICY_LIST_JSON);
		int size = 2;
		NeutronL7Policies res = osv3().networking().lbaasV2().lbPolicy().list();
		 assertTrue(res.getList().size() == size);
	    	
	}
	public void testDeleteL7Policy() throws IOException{
		int code = 200;
		respondWith(code);		
		String polciyId = "5ae0e1e7-5f0f-47a1-b39f-5d4c428a1586 ";
		 ActionResponse res = osv3().networking().lbaasV2().lbPolicy().delete(polciyId);
		 assertTrue(res.getCode() == code);
	}
	
	
	@Override
	protected Service service() {		
		return Service.NETWORK;
	}
}
