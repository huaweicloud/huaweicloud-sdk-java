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
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.Server.Status;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.openstack.ecs.v1.contants.IpType;
import com.huawei.openstack4j.openstack.ecs.v1.contants.NetworkChargingMode;
import com.huawei.openstack4j.openstack.ecs.v1.contants.ShareType;
import com.huawei.openstack4j.openstack.ecs.v1.contants.VolumeType;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Bandwidth;
import com.huawei.openstack4j.openstack.ecs.v1.domain.CloudServer;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Job;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ResetPassword;
import com.huawei.openstack4j.openstack.ecs.v1.domain.SubJob;
import com.huawei.openstack4j.openstack.ecs.v1.domain.CloudServer.CloudServers;
import com.huawei.openstack4j.openstack.ecs.v1.domain.DataVolume;
import com.huawei.openstack4j.openstack.ecs.v1.domain.FloatingIPCreate;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Personality;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ResizeServer;
import com.huawei.openstack4j.openstack.ecs.v1.domain.RootVolume;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ServerCreate;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ServerExtendParam;
import com.huawei.openstack4j.openstack.ecs.v1.domain.SupportAutoRecovery;
import com.huawei.openstack4j.openstack.ecs.v1.domain.AsyncServerRespEntity;

import sun.misc.BASE64Encoder;

public class CloudServerV1 {
	public static void main(String[] args) throws InterruptedException {

		// Using credentials for authentication
		String authUrl = "https://iam.XXX.YYY.com/v3"; //endpoint Url
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
		String flavorId = "flavor_id";
		String imageId = "image_id";
		String vpcId = "vpc_id";
		String networkId = "network_id";
		String secGroup = "security_groups";

		String userData_org = "#!/bin/bash \r\n echo 'root:xxxxx' | chpasswd ;";
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

		//create server ,return jobId
		String jobId = os.ecs().servers().create(creation);
		if (null != jobId) {
			Job job = os.ecs().jobs().get(jobId);
			while (!"SUCCESS".equals(job.getStatus()) && !"FAIL".equals(job.getStatus())) {
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("jobStatus"+job.getStatus());
				job = os.ecs().jobs().get(jobId);
			}
			List<SubJob> subJobList = job.getEntities().getSubJobs();
			List<String> successServers = new ArrayList<>();
			List<String> failServers = new ArrayList<>();
			for (SubJob subJob : subJobList) {
				if ("SUCCESS".equals(subJob.getStatus())) {
					successServers.add(subJob.getEntities().getServerId());
				} else {
					failServers.add(subJob.getEntities().getServerId());
				}
			}
			System.out.println("create server success, jobId = " + jobId);
			System.out.println("success servers=" + successServers);
			System.out.println("fail servers=" + failServers);
		} else {
			System.out.println("create server failed");
		}

		//create server ,return AsyncServerRespEntity
		AsyncServerRespEntity rep1 = os.ecs().servers().createServer(creation);
		if (null != rep1) {
			Job job = os.ecs().jobs().get(rep1.getJobId());
			while (!"SUCCESS".equals(job.getStatus()) && !"FAIL".equals(job.getStatus())) {
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("jobStatus" + job.getStatus());
				job = os.ecs().jobs().get(rep1.getJobId());
			}
			List<SubJob> subJobList = job.getEntities().getSubJobs();
			List<String> successServers = new ArrayList<>();
			List<String> failServers = new ArrayList<>();
			for (SubJob subJob : subJobList) {
				if ("SUCCESS".equals(subJob.getStatus())) {
					successServers.add(subJob.getEntities().getServerId());
				} else {
					failServers.add(subJob.getEntities().getServerId());
				}
			}
			System.out.println("create server success, jobId = " + rep1.getJobId());
			System.out.println("success servers=" + successServers);
			System.out.println("fail servers=" + failServers);
		} else {
			System.out.println("create server failed");
		}

		ArrayList<String> serverIds = new ArrayList<>();
		serverIds.add("server-id-1");
		serverIds.add("server-id-2");

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
		// Stop server first
		os.compute().servers().action(serverId, Action.STOP);
		// Use the waitForServerStatus function to query the server status until the server is SHUTOFF.
		// The timeout for this operation is 3 minutes.
		os.compute().servers().waitForServerStatus(serverId, Status.SHUTOFF, 3, TimeUnit.MINUTES);
		String resizeJobId = os.ecs().servers().resize(resize, serverId);
		if (null != resizeJobId) {
			System.out.println("Start to resize server, jobId = " + resizeJobId);
		} else {
			System.out.println("resize server failed");
		}

		//get count and list of server
		CloudServers cloudServer = os.ecs().servers().listWithCount();
		if (cloudServer != null){
			System.out.println("server count: " + cloudServer.getCount());
			System.out.println("server list: " + cloudServer.getServers());
			if (cloudServer.getCount()>0){
				for (CloudServer nowCloudServer:cloudServer.getServers()) {
					//get cloud server schedulerHints
					System.out.println("schedulerHints: " + nowCloudServer.getSchedulerHints());
				}
			}
		}

		//get count and list of server with parameters
		Map<String, String> filter = new HashMap<String, String>();
		filter.put("offset", "0");
		filter.put("status", "ACTIVE");
		CloudServers serverObjects = os.ecs().servers().listWithCount(filter);
		if (serverObjects != null){
			System.out.println("server count: " + serverObjects.getCount());
			System.out.println("server list: " + serverObjects.getServers());
			if (serverObjects.getCount()>0){
				for (CloudServer nowCloudServer:serverObjects.getServers()) {
					//get cloud server schedulerHints
					System.out.println("schedulerHints: " + nowCloudServer.getSchedulerHints());
				}
			}
		}

		//Query cloud server details list
		List<CloudServer> cloudServerList = os.ecs().servers().list();
		if (cloudServerList != null){
			System.out.println("server list: " + cloudServerList);
			if (cloudServerList.size()>0){
				for (CloudServer nowCloudServer:cloudServerList) {
					//get cloud server schedulerHints
					System.out.println("schedulerHints: " + nowCloudServer.getSchedulerHints());
				}
			}
		}

		//Query cloud server details list by filteringParams
		Map<String, String> filteringParams = new HashMap<String, String>();
		filteringParams.put("offset", "0");
		filteringParams.put("status", "ACTIVE");
		List<CloudServer> filterServerCloudList = os.ecs().servers().list(filteringParams);
		if (filterServerCloudList != null){
			System.out.println("server list: " + filterServerCloudList);
			if (filterServerCloudList.size()>0){
				for (CloudServer nowCloudServer:filterServerCloudList) {
					//get cloud server schedulerHints
					System.out.println("schedulerHints: " + nowCloudServer.getSchedulerHints());
				}
			}
		}

		//Query cloud server detail
		String exitSchedulerHintsServerId = "xxxxxxxxxxxxxxxxxxxxxxxx";
		String notExitSchedulerHintsServerId = "xxxxxxxxxxxxxxxxxxxxxxxx";
		CloudServer cloudServerDetail = os.ecs().servers().get(notExitSchedulerHintsServerId);
		if (cloudServerDetail != null){
			System.out.println("server: " + cloudServerDetail);
			//get cloud server schedulerHints
			System.out.println("schedulerHints: " + cloudServerDetail.getSchedulerHints());
		}

		//cloud server reset password
		ResetPassword resetPassword = ResetPassword.builder().newPassword("xxxx").build();
		ActionResponse resetPwdActionResponse = os.ecs().servers().resetPassword(serverId, resetPassword);
		if (resetPwdActionResponse.isSuccess()) {
			System.out.println("reset password success");
		} else {
			System.out.println("reset password failed");
		}

		//get autorecovery configuration of server
		SupportAutoRecovery autoRecoveryConfig = os.ecs().servers().getAutoRecovery(serverId);
		if (null != autoRecoveryConfig) {
			System.out.println("get autorecovery configuration of server success: " + autoRecoveryConfig.getSupportAutoRecovery());
		} else {
			System.out.println("get autorecovery configuration of server failed");
		}

		//manage automatic recovery of a server
		SupportAutoRecovery supportAutoRecovery = SupportAutoRecovery.builder().supportAutoRecovery("true").build();
		ActionResponse actionResponse = os.ecs().servers().manageAutoRecovery(serverId, supportAutoRecovery);
		System.out.println("response:" + actionResponse);
	}
}
