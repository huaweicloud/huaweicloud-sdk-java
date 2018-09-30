package com.huawei.openstack.sample.classic;

import java.util.ArrayList;
import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.loadbalance.ServerCreate;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBServerCreate;
import com.huawei.openstack4j.openstack.loadbalance.options.ELBServerListOptions;

public class ClassicMember {

	public static void main(String[] args) {

		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

		OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();

		// Create a member
		String lsn_id = "******";
		String server_ip = "192.168.0.9";
		String server_id = "******";
		ELBServerCreate server = ELBServerCreate.builder().address(server_ip).serverId(server_id).build();
		List<ServerCreate> list = new ArrayList<ServerCreate>();
		list.add(server);
		osclient.loadBalancer().servers().create(lsn_id, list);

		// check a job
		String job_id = "******";
		osclient.loadBalancer().jobs().get(job_id);

		// List all members
		osclient.loadBalancer().servers().list(lsn_id);

		// Get info about a member
		String mem_id = "******";
		ELBServerListOptions options = ELBServerListOptions.create().id(mem_id);
		osclient.loadBalancer().servers().list(lsn_id, options);

		// Delete a member
		String server_id_rm = "******";
		List<String> list_rm = new ArrayList<String>();
		list_rm.add(server_id_rm);
		osclient.loadBalancer().servers().delete(lsn_id, list_rm);
	}
}