package com.huawei.openstack4j.api.network;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.networking.domain.NeutronIpsecConnection;

@Test
public class IpsecConnectionTest extends AbstractTest {

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

	private static final String CON_CREATE_JSON = "/network/con_create.json";
    private static final String CON_UPDATE_JSON = "/network/con_update.json";
    private static final String	CON_LIST_JSON = "/network/con_list.json";
    private static final String CON_DETAIL_JSON = "/network/con_detail.json";

    public void testCreate() throws IOException{
    	respondWith(CON_CREATE_JSON);
    	final String id = "851f280f-5639-4ea3-81aa-e298525ab74b";
    	final String ipseId = "e6e23d0c-9519-4d52-8ea4-5b1f96d857b1";
    	final String endpointId = "9ad5a7e0-6dac-41b4-b20d-a7b8645fddf1";
    	final String ikePolicyId = "9b00d6b0-6c93-4ca5-9747-b8ade7bb514f";
    	final String vpnId = "5c561d9d-eaea-45f6-ae3e-08d1a7080828";
    	final String locdalEndpointId = "3e1815dd-e212-43d0-8f13-b494fa553e68";    	
    	final String peerAddress = "172.24.4.233";
    	final String peerId = "172.24.4.233";
    	final String name = "vpnconnection1";
    	final String psk = "cidr";
    	final String initiator = "bi-directional";
    	ArrayList<String> list1 = new ArrayList<String>();
		list1.add(psk);
		NeutronIpsecConnection conCreate = NeutronIpsecConnection.builder()
				.psk(psk)
				.initiator(initiator)
				.ipsecpolicyId(ipseId)
				.adminStateUp(true)
				.mtu(1500)
				.peerEpGroupId(endpointId)
				.ikepolicyId(ikePolicyId)
				.vpnserviceId(vpnId)
				.localEpGroupId(locdalEndpointId)
				.peerAddress(peerAddress)
				.peerId(peerId)
				.name(name)
				.build();
		NeutronIpsecConnection res = osv3().networking().ipsecConections().create(conCreate);
    	assertNotNull(id.equals(res.getId()));
    	
    } 
    
    public void testUpdate() throws IOException{
    	respondWith(CON_UPDATE_JSON);    
    	final String  id = "851f280f-5639-4ea3-81aa-e298525ab74b";    	
    	NeutronIpsecConnection conUpdate = NeutronIpsecConnection.builder()
				.mtu(1200)
				.build();
    	NeutronIpsecConnection res = osv3().networking().ipsecConections().update(id, conUpdate);
    	assertNotNull(id.equals(res.getId()));
    	
    } 
    
    public void testList() throws IOException{
    	respondWith(CON_LIST_JSON);
    	List<NeutronIpsecConnection> res = osv3().networking().ipsecConections().list(null);
    	assertNotNull(res.size()==1);
    	
    } 
    
    public void testGet() throws IOException{
    	respondWith(CON_DETAIL_JSON);
    	final String id = "851f280f-5639-4ea3-81aa-e298525ab74b";
    	NeutronIpsecConnection res = osv3().networking().ipsecConections().get(id);
    	assertNotNull(res);
    	
    } 
    
    public void testDelete() throws IOException{
    	respondWith(200);
    	final String id = "851f280f-5639-4ea3-81aa-e298525ab74b";
    	 ActionResponse res = osv3().networking().vpnEndpointGroups().delete(id);
    	assertNotNull(200 == res.getCode());
    	
    } 
}
