package sample;

import java.util.List;
import sun.misc.BASE64Encoder; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.openstack.ecs.v1.domain.Job;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.model.compute.ServerCreate;
import com.huawei.openstack4j.model.compute.StopType;

public class CreateOneOrMoreServers {
    /**
	 * wait until job status become SUCCESS or FAIL
	 **/
	public void waitTime(int times,int interval,OSClientV3 osclient,String jobId){
		for(int i=1;i<=times;i++) {
			try {
				Thread.sleep(interval*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Job job = osclient.ecs().jobs().get(jobId);
			if("SUCCESS".equals(job.getStatus())) {
				System.out.println("job success after " + i + " tries");
				break;
			}else if("FAIL".equals(job.getStatus())){
				System.out.println("job failed after " + i + " tries");
				break;
			}
		}
	}

	public static void main(String[] args){
		
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
				
		String flavorId = "s2.xlarge.1";
		String imageId = "5d153261-c05c-456e-b38b-074eea7ce880";
		String networkId = "319944c8-baac-46da-a3a8-f07956105a4e";
		
		CreateOneOrMoreServers demo = new CreateOneOrMoreServers();
		int times = 60;
		int interval = 10;
		
		//use userData to create linux server
		String userDataOrg = "#!/bin/bash \r\n echo 'root:P@ssWr0d123' | chpasswd ;";
		
		//parse userData to Base64 format
		byte[] userDataByte = userDataOrg.getBytes();
		String userData = new BASE64Encoder().encode(userDataByte);
		
		// generate networkList
		ArrayList<String> networkList = new ArrayList<String>();
		networkList.add(networkId);

		// generate server body
		ServerCreate scLinux = Builders.server()
				.name("test-name")
				.flavor(flavorId)
				.min(5)
				.max(5)
				.image(imageId)
				.availabilityZone("az1.dc1")
				.userData(userData)
				.networks(networkList)
				.build();
		System.out.println("New Server body " + scLinux);
		
		//create server
		Server newServer = osclient.compute().servers().boot(scLinux);
		osclient.compute().servers().waitForServerStatus(newServer.getId(), Status.ACTIVE, 10, TimeUnit.MINUTES);
			
		//Filter server list, wait for server status to Active
		Map<String , String> filterName = new HashMap<String, String>();
		filterName.put("name", "test-name");
		List<? extends Server> servers = osclient.compute().servers().list(filterName);
		ArrayList<String> serverIds = new ArrayList<String>();
		for (Server server : servers) {
			osclient.compute().servers().waitForServerStatus(server.getId(), Status.ACTIVE, 3, TimeUnit.MINUTES);
			serverIds.add(server.getId());
		}
		
		// batch reboot server
		String rebootJobId = osclient.ecs().servers().reboot(serverIds, RebootType.SOFT);
		if (null != rebootJobId) {
			System.out.println("batch reboot server success, jobId = " + rebootJobId);
		} else {
			System.out.println("batch reboot server failed");
		}
		demo.waitTime(times,interval,osclient,rebootJobId);
		
		// batch stop server
		String stopJobId = osclient.ecs().servers().stop(serverIds, StopType.SOFT);
		if (null != stopJobId) {
			System.out.println("batch stop server success, jobId = " + stopJobId);
		} else {
			System.out.println("batch stop server failed");
		}
		demo.waitTime(times,interval,osclient,stopJobId);

		// batch start server
		String startJobId = osclient.ecs().servers().start(serverIds);
		if (null != startJobId) {
			System.out.println("batch start server success, jobId = " + startJobId);
		} else {
			System.out.println("batch start server failed");
		}
		demo.waitTime(times,interval,osclient,startJobId);
				
		// batch delete server
		String deleteJobId = osclient.ecs().servers().delete(serverIds, false, false);
		if (null != deleteJobId) {
			System.out.println("batch delete server success, jobId = " + deleteJobId);
		} else {
			System.out.println("batch delete server failed");
		}
		demo.waitTime(times,interval,osclient,deleteJobId);
	}
}
