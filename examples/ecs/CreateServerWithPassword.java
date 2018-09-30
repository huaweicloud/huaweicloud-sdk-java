package sample;

import java.util.List;
import sun.misc.BASE64Encoder; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.compute.Action;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.model.compute.Server.Status;
import com.huawei.openstack4j.model.compute.ServerCreate;

public class CreateServerWithPassword {
	public static void main(String[] args) {
		
		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxx"; //username
		String password = "xxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxx"; //projectID
		String userDomainId = "xxxxxxxxxxxxxxxxxxxx"; //domainID 
		
		// create connection
		OSFactory.enableHttpLoggingFilter(true);
		OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId))
				.scopeToProject(Identifier.byId(projectId)).authenticate();

		// Filte server list
		Map<String , String> filterName = new HashMap<String, String>();
		filterName.put("name", "ecs");
		filterName.put("status", "ACTIVE");
		List<? extends Server> servers = osclient.compute().servers().list(filterName);
		System.out.println(servers);
	
		String flavorId = "s2.xlarge.1";
		String imageId = "5d153261-c05c-456e-b38b-074eea7ce880";
		String networkId = "319944c8-baac-46da-a3a8-f07956105a4e";
		String imageIdWindows = "a1e6a557-e6c5-43a0-9d4e-a90fdf376afb";
		
		// use user-data to create linux server 
		String userDataOrg = "#!/bin/bash \r\n echo 'root:P@ssWr0d123' | chpasswd ;";
		
		//parse userData to Base64 format
		byte[] userDataByte = userDataOrg.getBytes();
		String userData = new BASE64Encoder().encode(userDataByte);
		
		// generate networkList
		ArrayList<String> networkList = new ArrayList<String>();
		networkList.add(networkId);
		
		// generate server body
		ServerCreate scLinux = Builders.server()
				.name("Linux-test-sdk")
				.flavor(flavorId)
				.image(imageId)
				.availabilityZone("eu-de-02")
				.userData(userData)
				.networks(networkList)
				.build();
		System.out.println("New Server body " + scLinux);
		
		//create linux server, wait for server status to Active
		Server newServer = osclient.compute().servers().boot(scLinux);
		osclient.compute().servers().waitForServerStatus(newServer.getId(), Status.ACTIVE, 10, TimeUnit.MINUTES);
		System.out.println("New Server " + newServer);            
				
		//use adminpass to create windows server
		ServerCreate scWindows = Builders.server()
				.name("Windows-test-sdk")
				.flavor(flavorId)
				.image(imageIdWindows)
				.availabilityZone("eu-de-02")
				.addMetadataItem("admin_pass", "P@ssWr0d123")
				.networks(networkList)
				.build();
		System.out.println("New Server body " + scWindows);
		
		//create windows server, wait for server status to Active
		Server newServerWindows = osclient.compute().servers().boot(scWindows);
		osclient.compute().servers().waitForServerStatus(newServerWindows.getId(), Status.ACTIVE, 10, TimeUnit.MINUTES);
		System.out.println("New Server " + newServerWindows);   
		
		// stop server 
		ActionResponse repStop = osclient.compute().servers().action(newServer.getId(), Action.STOP);
		if (repStop.isSuccess()) {
			System.out.println("Stop the server success, status = " + newServer.getId() + newServer.getVmState());
		} else {
			System.out.println("Stop the server failed " + newServer.getId());
		}
		osclient.compute().servers().waitForServerStatus(newServer.getId(), Status.SHUTOFF, 3, TimeUnit.MINUTES);
		
		// start server 
		ActionResponse repStart = osclient.compute().servers().action(newServer.getId(), Action.START);
		if (repStart.isSuccess()) {
			System.out.println("Start the server success, status = " + newServer.getId() + newServer.getVmState());
		} else {
			System.out.println("Start the server failed " + newServer.getId());
		}
		osclient.compute().servers().waitForServerStatus(newServer.getId(), Status.ACTIVE, 3, TimeUnit.MINUTES);
		
		// reboot server       
		ActionResponse repReboot = osclient.compute().servers().reboot(newServer.getId(), RebootType.SOFT);
		if (repReboot.isSuccess()) {
			System.out.println("Reboot the server success, status = " + newServer.getId() + newServer.getVmState());
		} else {
			System.out.println("Reboot the server failed " + newServer.getId());
		}
		osclient.compute().servers().waitForServerStatus(newServer.getId(), Status.ACTIVE, 3, TimeUnit.MINUTES);
		
		// delete server
		ActionResponse repDelete = osclient.compute().servers().delete(newServer.getId());
		if (repDelete.isSuccess()) {
			System.out.println("Delete the server success, status = " + newServer.getId() + newServer.getVmState());
		} else {
			System.out.println("Delete the server failed " + newServer.getId());
		}
		
		//delete windows server
		ActionResponse repDeleteWindows = osclient.compute().servers().delete(newServerWindows.getId());
		if (repDeleteWindows.isSuccess()) {
			System.out.println("Delete the server success, status = " + newServerWindows.getId() + newServer.getVmState());
		} else {
			System.out.println("Delete the server failed " + newServerWindows.getId());
		}
	}
}
