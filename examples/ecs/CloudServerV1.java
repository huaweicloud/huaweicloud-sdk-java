package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.huawei.openstack4j.model.compute.StopType;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.compute.Action;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.model.compute.Server.Status;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.openstack.ecs.v1.contants.IpType;
import com.huawei.openstack4j.openstack.ecs.v1.contants.NetworkChargingMode;
import com.huawei.openstack4j.openstack.ecs.v1.contants.ShareType;
import com.huawei.openstack4j.openstack.ecs.v1.contants.VolumeType;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Bandwidth;
import com.huawei.openstack4j.openstack.ecs.v1.domain.CloudServer;
import com.huawei.openstack4j.openstack.ecs.v1.domain.CloudServer.CloudServers;
import com.huawei.openstack4j.openstack.ecs.v1.domain.DataVolume;
import com.huawei.openstack4j.openstack.ecs.v1.domain.FloatingIPCreate;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Personality;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ResizeServer;
import com.huawei.openstack4j.openstack.ecs.v1.domain.RootVolume;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ServerCreate;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ServerExtendParam;
import sun.misc.BASE64Encoder;

public class CloudServerV1 {
	public static void main(String[] args) throws InterruptedException {
		
		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxx"; //username
		String password = "xxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

		//create connection	
		OSClientV3 os = OSFactory.builderV3()
		.endpoint(authUrl)
		.credentials(user, password, Identifier.byId(userDomainId))
		.scopeToProject(Identifier.byId(projectId)).authenticate();
		
		int count = 1;
		String flavorId = "s2.xlarge.1";
		String imageId = "a1e6a557-e6c5-43a0-9d4e-a90fdf376afb";
		String vpcId = "0d85e49a-6aef-42a9-8583-c86e4317a7e2";
		String networkId = "319944c8-baac-46da-a3a8-f07956105a4e";
		String secGroup = "114f5982-ecdc-4297-ae23-e6aa17763c78";
		
		String userData_org = "#!/bin/bash \r\n echo 'root:Cloud.1234' | chpasswd ;";
	    byte[] userData_byte = userData_org.getBytes();
	    String userData = new BASE64Encoder().encode(userData_byte);
	    
		Bandwidth bandwidth = Bandwidth.builder().size(10).shareType(ShareType.PER).chargeMode(NetworkChargingMode.TRAFFIC).build();
		FloatingIPCreate FIPbuild = FloatingIPCreate.builder().ipType(IpType.BGP).bandwidth(bandwidth).build();
		
		ServerCreate creation = ServerCreate.builder()
				.name("test-name")
                .flavorRef(flavorId)
                .imageRef(imageId)
                .userData(userData)
                .vpcId(vpcId)
                .addNetwork(networkId)
                .availabilityZone("eu-de-02")
                .addSecurityGroup(secGroup)
                .addTag("key", "testvalue")
                .publicIP(FIPbuild)
                .keyName("KeyPair-a6c5")
                .addMetadata("Group", "testGroup")
                .addPersonality(Personality.builder().contents("some content").path("/etc/test.txt").build())
                .rootVolume(RootVolume.builder().type(VolumeType.SSD).build())
                .addDataVolume(DataVolume.builder().size(10).type(VolumeType.SATA).multiAttach(true).passthrough(true).build())
                .extendParam(ServerExtendParam.builder().autoRecovery(true).build())
                .count(count).build();
				
		//create server
		String jobId = os.ecs().servers().create(creation);
		if (null != jobId) {
			System.out.println("create server success, jobId = " + jobId);
		} else {
			System.out.println("create server failed");
		}
		
		//get list of server
		List<? extends Server> serverList = os.compute().servers().list();
		if (serverList.size() > 0) {
			System.out.println("get serverList success, size = " + serverList.size());
		} else {
			System.out.println("get serverList failed");
		}
		
		//find server, wait for server status to ACTIVE
		Map<String , String> filterName = new HashMap<String, String>();
		filterName.put("name", "test-name");
		List<? extends Server> servers = os.compute().servers().list(filterName);
		ArrayList<String> serverIds = new ArrayList<String>();
		servers = os.compute().servers().list(filterName);
		for (Server server : servers) {
			os.compute().servers().waitForServerStatus(server.getId(), Status.ACTIVE, 10, TimeUnit.MINUTES);
			//get server
			CloudServer serverInfo = os.ecs().servers().get(server.getId());
			if (null != serverInfo) {
				System.out.println("get serverInfo success, name = " + serverInfo.getName());
			} else {
				System.out.println("get serverInfo server failed");
			}
			serverIds.add(server.getId());
		}
		
		//reboot server
		String rebootJobId = os.ecs().servers().reboot(serverIds, RebootType.SOFT);
		if (null != rebootJobId) {
			System.out.println("batch reboot server success, jobId = " + rebootJobId);
		} else {
			System.out.println("batch reboot server failed");
		}
		
		//stop server
		String stopJobId = os.ecs().servers().stop(serverIds, StopType.SOFT);
		if (null != stopJobId) {
			System.out.println("batch stop server success, jobId = " + stopJobId);
		} else {
			System.out.println("batch stop server failed");
		}
		
		//start server
		String startJobId = os.ecs().servers().start(serverIds);
		if (null != startJobId) {
			System.out.println("batch start server success, jobId = " + startJobId);
		} else {
			System.out.println("batch start server failed");
		}
		
		//delete server
		String deleteJobId = os.ecs().servers().delete(serverIds, false, false);
		if (null != deleteJobId) {
			System.out.println("batch delete server success, jobId = " + deleteJobId);
		} else {
			System.out.println("batch delete server failed");
		}
		
		//resize server
		String newFlavorId = "s2.medium.2";
		String serverId = "ac91c721-9e8e-4147-83d9-b4f07ad607ed";
		ResizeServer resize = ResizeServer.builder().flavorRef(newFlavorId).build();
		os.compute().servers().action(serverId, Action.STOP);
		os.compute().servers().waitForServerStatus(serverId, Status.SHUTOFF, 3, TimeUnit.MINUTES);
		String resizeJobId = os.ecs().servers().resize(resize, serverId);
		if (null != resizeJobId) {
			System.out.println("Start to resize server, jobId = " + resizeJobId);
		} else {
			System.out.println("resize server failed");
		}

		//get count and list of server
		CloudServers cloudServer = os.ecs().servers().listWithCount();
		System.out.println("server count: " + cloudServer.getCount());
		System.out.println("server list: " + cloudServer.getServers());

		//get count and list of server with parameters
		Map<String, String> filter = new HashMap<String, String>();
		filter.put("offset", "0");
		filter.put("status", "ACTIVE");
		CloudServers serverObjects = os.ecs().servers().listWithCount(filter);
		System.out.println("server count: " + serverObjects.getCount());
		System.out.println("server list: " + serverObjects.getServers());
	}
}
