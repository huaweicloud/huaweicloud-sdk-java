package com.huawei.openstack.sample.classic;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.loadbalance.HealthCheck.HealthCheckProtocol;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBHealthCheckCreate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBHealthCheckUpdate;

public class ClassicHealthcheck{

	public static void main(String[] args) {

		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

		OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();

		// Create a healthcheck
		String lsn_id = "******";

		ELBHealthCheckCreate create = ELBHealthCheckCreate.builder().listenerId(lsn_id)
				.healthCheckProtocol(HealthCheckProtocol.TCP).healthCheckInterval(3).healthCheckConnectPort(80).build();
		osclient.loadBalancer().healthchecks().create(create);

		// Query info about a healthcheck
		String hc_id = "******";
		osclient.loadBalancer().healthchecks().get(hc_id);

		// Update a healthmonitor
		ELBHealthCheckUpdate update = ELBHealthCheckUpdate.builder().healthCheckInterval(5).build();
		osclient.loadBalancer().healthchecks().update(hc_id, update);

		// Delete a healthmonitor
		osclient.loadBalancer().healthchecks().delete(lsn_id);
	}

}