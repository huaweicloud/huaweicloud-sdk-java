package com.huawei.openstack.sample.classic;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer.Type;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerCreate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBLoadBalancerUpdate;
import com.huawei.openstack4j.openstack.loadbalance.options.ELBLoadBalancerListOptions;

public class ClassicLoadbalancer {

	public static void main(String[] args) {

		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

		OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();

		// Create a classic Loadbalancer
		String vpc_id = "cc45c85e-51ee-421a-a50b-e102695982a0";// specify in which vpc in which you want to create
		String name = "my_lb";
		Integer bandwidth = 10;
		ELBLoadBalancerCreate create = ELBLoadBalancerCreate.builder().name(name).vpcId(vpc_id).bandwidth(bandwidth)
				.type(Type.EXTERNAL).adminStateUp(1).build();
		//osclient.loadBalancer().loadBalancers().create(create);

		// Check a job
		String job_id = "ff80808265ed06200166155854251d66";
		osclient.loadBalancer().jobs().get(job_id);

		// List all classic Loadbalancer under tenant's project
		//osclient.loadBalancer().loadBalancers().list();

		// Query info about a specific Loadbalancer
		String lb_id = "******";// specify your loadbalancer id here
		ELBLoadBalancerListOptions options = ELBLoadBalancerListOptions.create().id(lb_id);
		//osclient.loadBalancer().loadBalancers().list(options);

		// Update classic Loadbalancer
		ELBLoadBalancerUpdate update = ELBLoadBalancerUpdate.builder().bandwidth(3).build();
		//osclient.loadBalancer().loadBalancers().update(lb_id, update);

		String job_id_2 = "******";
		//osclient.loadBalancer().jobs().get(job_id_2);

		// Delete a classic Loadbalancer
		//osclient.loadBalancer().loadBalancers().delete(lb_id);

	}

}