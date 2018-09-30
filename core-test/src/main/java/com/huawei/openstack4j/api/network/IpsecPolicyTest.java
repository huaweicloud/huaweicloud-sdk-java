package com.huawei.openstack4j.api.network;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.networking.domain.IKEPolicy.LifeTime;
import com.huawei.openstack4j.openstack.networking.domain.IKEPolicy.Pfs;
import com.huawei.openstack4j.openstack.networking.domain.NeutronIpsecPolicy;
import com.huawei.openstack4j.openstack.networking.domain.NeutronIpsecPolicy.NeutronIpsecPolicies;

@Test
public class IpsecPolicyTest extends AbstractTest{

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

	private static final String IPSEC_POLICY_CREATE_JSON = "/network/ipsec_policy_create.json";
    private static final String IPSEC_POLICY_UPDATE_JSON = "/network/ipsec_policy_update.json";
    private static final String	IPSEC_POLICY_LIST_JSON = "/network/ipsec_policy_list.json";
    private static final String IPSEC_POLICY_DETAIL_JSON = "/network/ipsec_policy_detail.json";

    public void testCreate() throws IOException{
    	respondWith(IPSEC_POLICY_CREATE_JSON);
    	final String id = "5291b189-fd84-46e5-84bd-78f40c05d69c";
    	final String name = "policy1";
    	final String transformProtocol = "esp";
    	final String authAlgorithm = "sha1";
    	final String encapsulationMode = "tunnel";
    	final String encryptionAlgorithm = "aes-128";
    	final String units = "seconds";
    	final Integer value = 7200;
    	
		NeutronIpsecPolicy ipePolicyCreate = NeutronIpsecPolicy.builder()
				.name(name)
				.transformProtocol(transformProtocol)
				.authAlgorithm(authAlgorithm)
				.encapsulationMode(encapsulationMode)
				.encryptionAlgorithm(encryptionAlgorithm)
				.pfs(Pfs.group5)
				.lifetime(new LifeTime(units,value ))
				.build();
		NeutronIpsecPolicy res = osv3().networking().ipsecPolicies().create(ipePolicyCreate);
    	assertNotNull(id.equals(res.getId()));
    	
    } 
    
    public void testUpdate() throws IOException{
    	respondWith(IPSEC_POLICY_UPDATE_JSON);    
    	final String  id = "5291b189-fd84-46e5-84bd-78f40c05d69c";    	
    	NeutronIpsecPolicy ipseUpdate = NeutronIpsecPolicy.builder().pfs(Pfs.group14).build();
    	NeutronIpsecPolicy res = osv3().networking().ipsecPolicies().update(id, ipseUpdate);
    	assertNotNull(id.equals(res.getId()));
    	
    } 
    
    public void testList() throws IOException{
    	respondWith(IPSEC_POLICY_LIST_JSON);
    	final Integer size = 1;
    	List<NeutronIpsecPolicy> res = osv3().networking().ipsecPolicies().list(null);
    	assertNotNull(res.size() == size);
    	
    } 
    
    public void testGet() throws IOException{
    	respondWith(IPSEC_POLICY_DETAIL_JSON);
    	final String id = "5291b189-fd84-46e5-84bd-78f40c05d69c";
    	NeutronIpsecPolicy res = osv3().networking().ipsecPolicies().get(id);
    	assertNotNull(res);
    	
    } 
    
    public void testDelete() throws IOException{
    	respondWith(200);
    	final String id = "5291b189-fd84-46e5-84bd-78f40c05d69c";
    	 ActionResponse res = osv3().networking().ipsecPolicies().delete(id);
    	assertNotNull(200 == res.getCode());
    	
    } 
}
