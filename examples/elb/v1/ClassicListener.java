package com.huawei.openstack.sample.classic;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.loadbalance.Listener.BackendProtocol;
import com.huawei.openstack4j.model.loadbalance.Listener.LbAlgorithm;
import com.huawei.openstack4j.model.loadbalance.Listener.Protocol;
import com.huawei.openstack4j.model.loadbalance.ListenerCreate;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBListenerCreate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBListenerUpdate;
import com.huawei.openstack4j.openstack.loadbalance.options.ELBListenerListOptions;

public class ClassicListener {

	public static void main(String[] args) {

		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

		OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();

		// Create a listener
		String lb_id = "******";
		String name = "my_listener";
		ListenerCreate create = ELBListenerCreate.builder().name(name).loadBalancerId(lb_id)
				.lbAlgorithm(LbAlgorithm.ROUND_ROBIN).protocol(Protocol.TCP).port(80)
				.backendProtocol(BackendProtocol.TCP).backendPort(80).build();
		ELBListenerCreate.builder().name(name).loadBalancerId(lb_id).build();
		osclient.loadBalancer().listeners().create(create);

		// List all listeners
		osclient.loadBalancer().listeners().list();

		// Query info about a listener
		ELBListenerListOptions options = ELBListenerListOptions.create().name(name);
		osclient.loadBalancer().listeners().list(options);

		// Update a listener
		String lsn_id = "******";
		ELBListenerUpdate update = ELBListenerUpdate.builder().description("my_tcp_listener").build();
		osclient.loadBalancer().listeners().update(lsn_id, update);

		// Delete a listener
		osclient.loadBalancer().listeners().delete(lsn_id);
	}

}