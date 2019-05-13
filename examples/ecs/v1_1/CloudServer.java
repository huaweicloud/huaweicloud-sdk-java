package sample;

import java.util.concurrent.TimeUnit;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.compute.Action;
import com.huawei.openstack4j.model.compute.Server.Status;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.common.AsyncRespEntity;
import sun.misc.BASE64Encoder;

import com.huawei.openstack4j.openstack.ecs.v1.contants.IpType;
import com.huawei.openstack4j.openstack.ecs.v1.contants.PeriodType;
import com.huawei.openstack4j.openstack.ecs.v1.contants.VolumeType;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Personality;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.ResizeServer;
import com.huawei.openstack4j.openstack.ecs.v1_1.contants.ServerChargingMode;
import com.huawei.openstack4j.openstack.ecs.v1_1.contants.ShareType;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.Bandwidth;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.DataVolume;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.FloatingIPCreate;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.RootVolume;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.SchedulerHints;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.ServerCreate;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.ServerExtendParam;

public class CloudServer {
	public static void main(String[] args) {
		
		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxxx"; //username
		String password = "xxxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectid
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainid 
		
		// create connection
		OSClientV3 os = OSFactory.builderV3()
		.endpoint(authUrl)
		.credentials(user, password, Identifier.byId(userDomainId))
		.scopeToProject(Identifier.byId(projectId)).authenticate();
		
		int count = 1;
		String flavorId = "c1.2xlarge";
		String imageId = "69124c97-1d07-49cc-bca2-291ebcc43cf1";
		String vpcId = "515231d1-f0d9-4acf-a549-c79f90ef7c4e";
		String networkId = "dadd702e-f4ec-44c0-86e8-fb2397d15ae4";
		String secGroup = "c9ce5f25-f5d7-44eb-b486-f196f465edf0";
		
		String userData_org = "#!/bin/bash \r\n echo 'root:Cloud.1234' | chpasswd ;";
		byte[] userData_byte = userData_org.getBytes();
		String userData = new BASE64Encoder().encode(userData_byte);
		
		Bandwidth bandwidth = Bandwidth.builder().size(10).shareType(ShareType.PER).chargeMode(null).build();
		FloatingIPCreate FIPbuild = FloatingIPCreate.builder().ipType(IpType.BGP).bandwidth(bandwidth).build();
		
		String group = "ae089094-087b-4c39-ba98-f1862d5e45b1";
		
		ServerCreate creation1 = ServerCreate.builder()
				.name("test-prepaid")
                .flavorRef(flavorId)
                .imageRef(imageId)
                .userData(userData)
                .vpcId(vpcId)
                .addNetwork(networkId)
                .availabilityZone("az1.dc1")
                .addSecurityGroup(secGroup)
                .addTag("ztkey", "ztvalue")
                .publicIP(FIPbuild)
                .keyName("Keypair-ccad")
                .addMetadata("op_svc_userid","user_id")
                .isAutoRename(true)
                .addPersonality(Personality.builder().contents("some content").path("/etc/test.txt").build())
                .rootVolume(RootVolume.builder().type(VolumeType.SATA).build())
                .addDataVolume(DataVolume.builder().size(50).type(VolumeType.SSD).multiAttach(false).build())
                .extendParam(ServerExtendParam.builder().chargingMode(ServerChargingMode.prePaid).periodType(PeriodType.month).periodNum(1).isAutoPay(true).isAutoRenew(true).regionId("southchina").build())
                .count(count)
                .schedulerHints(SchedulerHints.builder().group(group).build())
                .build();
				
		//create prepaid server
		AsyncRespEntity rep1 = os.ecsV1_1().servers().create(creation1);
		if (null != rep1) {
			System.out.println("create server success, orderid = " + rep1.getOrderId());
		} else {
			System.out.println("create server failed");
		}
		
		//resize server
		String newFlavorId = "s3.2xlarge.4";
		String serverId = "d64db293-1937-4816-9e34-a99de8cd8fdb";
		ResizeServer resize = ResizeServer.builder().flavorRef(newFlavorId).build();
		os.compute().servers().action(serverId, Action.STOP);
		os.compute().servers().waitForServerStatus(serverId, Status.SHUTOFF, 3, TimeUnit.MINUTES);
		AsyncRespEntity resizeJobId = os.ecsV1_1().servers().resize(resize, serverId);
		if (null != resizeJobId) {
			System.out.println("Start to resize server, jobId = " + resizeJobId.getOrderId());
		} else {
			System.out.println("resize server failed");
		}
		
		ServerCreate creation2 = ServerCreate.builder()
				.name("test-postpaid")
                .flavorRef(flavorId)
                .imageRef(imageId)
                .userData(userData)
                .vpcId(vpcId)
                .addNetwork(networkId)
                .availabilityZone("az1.dc1")
                .addSecurityGroup(secGroup)
                .addTag("ztkey", "ztvalue")
                .publicIP(FIPbuild)
                .keyName("KeyPair-ccad")
                .addMetadata("Group", "testGroup")
                .isAutoRename(true)
                .addPersonality(Personality.builder().contents("some content").path("/etc/test.txt").build())
                .rootVolume(RootVolume.builder().type(VolumeType.SATA).build())
                .addDataVolume(DataVolume.builder().size(50).type(VolumeType.SSD).multiAttach(false).build())
                .extendParam(ServerExtendParam.builder().chargingMode(ServerChargingMode.postPaid).periodType(PeriodType.month).periodNum(1).isAutoPay(true).isAutoRenew(true).regionId("southchina").build())
                .count(count)
                .schedulerHints(SchedulerHints.builder().group(group).build())
                .build();
		
		//create postpaid server
		AsyncRespEntity rep2 = os.ecsV1_1().servers().create(creation2);
		if (null != rep2) {
			System.out.println("create server success, jobid = " + rep2.getJobId());
		} else {
			System.out.println("create server failed");
		}
	}
}
