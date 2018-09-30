package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRule;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRule.Type;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronRuleUpdate;

public class L7rule {

	public static void main(String[] args) {
        
		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

	OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
			.credentials(user, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();
	
			
//Create a Layer 7 Redirect Rule
		String l7p_id = "******";
		NeutronRule l7rule = NeutronRule.builder().type(Type.PATH).value("/api").compareType("EQUAL_TO").build();
		osclient.networking().lbaasV2().lbRule().create(l7rule, l7p_id);
//List all Layer 7 Redirect Rules
		osclient.networking().lbaasV2().lbRule().list(l7p_id);
//Query a Layer 7 Redirect Rule
		String l7r_id = "******";
		osclient.networking().lbaasV2().lbRule().get(l7p_id, l7r_id);
//Update a Layer 7 Redirect Rule
		NeutronRuleUpdate l7rule_mod = NeutronRuleUpdate.builder().value("/api_update").build();
		osclient.networking().lbaasV2().lbRule().update(l7rule_mod, l7p_id, l7r_id);
//Delete a Layer 7 Redierct Rule
		osclient.networking().lbaasV2().lbRule().delete(l7p_id, l7r_id);
	}

}