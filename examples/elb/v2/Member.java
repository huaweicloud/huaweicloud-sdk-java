package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.network.ext.MemberV2;
import com.huawei.openstack4j.model.network.ext.MemberV2Update;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronMemberV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronMemberV2Update;

public class Member {

	public static void main(String[] args) {

		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

		OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
			.credentials(user, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();
		
		String subnet_id = "******";			
//Create a member
		String pool_id = "******";
		String mem_ip = "******";
		MemberV2 member = NeutronMemberV2.builder().address(mem_ip).subnetId(subnet_id).protocolPort(80).build();
		osclient.networking().lbaasV2().lbPool().createMember(pool_id, member);
//List all members
		osclient.networking().lbaasV2().lbPool().listMembers(pool_id);
//Get info about a member
		String mem_id = "******";
		osclient.networking().lbaasV2().lbPool().getMember(pool_id, mem_id);
//Update a member
		MemberV2Update member_mod = NeutronMemberV2Update.builder().weight(2).build();
		osclient.networking().lbaasV2().lbPool().updateMember(pool_id, mem_id, member_mod);
//Delete a member
		osclient.networking().lbaasV2().lbPool().deleteMember(pool_id, mem_id);
	}

}