package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.network.ext.LoadBalancerV2;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2Update;

public class Loadbalancer {

	public static void main(String[] args) {

		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

		OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
			.credentials(user, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();

//Enhanced Loadbalancer Ops starts here!
			
//Create an Enhanced Loadbalancer
		String subnet_id = "5de13914-bd0c-4387-81a7-2d6618cd4824";//specify in which subnet you want to create an enhanced loadbalancer
		LoadBalancerV2 myelb = NeutronLoadBalancerV2.builder().adminStateUp(true).subnetId(subnet_id).name("l4_test_lb").build();
		osclient.networking().lbaasV2().loadbalancer().create(myelb);
		
//List all Enhanced Loadbalancer under tenant's project
		osclient.networking().lbaasV2().loadbalancer().list();
		
//Query info about a specific Loadbalancer
		String lb_id = "24255471-2bdd-4e31-9893-7df04b5c1db1";//specify your loadbalancer id here
		osclient.networking().lbaasV2().loadbalancer().get(lb_id);
		
//Update an Enhanced Loadbalancer
		LoadBalancerV2Update update_myelb = NeutronLoadBalancerV2Update.builder().name("l4_test_lb_mod").build();
		osclient.networking().lbaasV2().loadbalancer().update(lb_id, update_myelb);
		
//Delete an Enhanced Loadbalancer
		osclient.networking().lbaasV2().loadbalancer().delete(lb_id);

	}

}