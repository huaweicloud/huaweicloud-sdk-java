package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronL7Policy;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronL7PolicyUpdate;

public class L7policy {

	public static void main(String[] args) {
        
		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

	OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
			.credentials(user, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();
	
			
		String lsnr_id = "******";
		String pool_id = "******";
//Create a Layer 7 Redirect Policy 
		NeutronL7Policy l7p = NeutronL7Policy.builder().adminStateUp(true).name("l7p").redirectPoolId(pool_id).listenerId(lsnr_id).build();
		osclient.networking().lbaasV2().lbPolicy().create(l7p);
//List all Layer 7 Redirect Policy
		osclient.networking().lbaasV2().lbPolicy().list();
//Query info about a Layer 7 Redirect Policy
		String l7p_id = "";
		osclient.networking().lbaasV2().lbPolicy().get(l7p_id);
//Update a Layer 7 Redirect Policy
		NeutronL7PolicyUpdate l7p_mod = NeutronL7PolicyUpdate.builder().name("l7p_mod").build();
		osclient.networking().lbaasV2().lbPolicy().update(l7p_mod, l7p_id);
//Delete a Layer 7 Redirect Policy
		osclient.networking().lbaasV2().lbPolicy().delete(l7p_id);
	}

}