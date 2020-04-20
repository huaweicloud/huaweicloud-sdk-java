package com.huawei.openstack.sample;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.api.exceptions.ServerResponseException;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.model.dns.v2.Zone;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;
import com.huawei.openstack4j.openstack.rds.v3.domain.*;
import com.huawei.openstack4j.openstack.rds.v3.internal.InstanceManageService;
import com.huawei.openstack4j.openstack.trove.domain.Database;
import com.sun.istack.internal.localization.NullLocalizable;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;


public class RdsInstanceManageServiceDemo {

	private static final String LANGUAGE = "zh-cn";
	private static OSClient.OSClientAKSK osclient;

	public static class AuthenticateToken
	{
		String authUrl = "****************";//endpoint url
		String user = "****************";//username
		String password = "****************";//password
		String projectId = "****************";//projectId
		String userDomainId = "****************";	//domainId
		Config config = Config.newConfig().withLanguage(LANGUAGE).withSSLVerificationDisabled();
		OSClient.OSClientV3 osclient = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId))
				.scopeToProject(Identifier.byId(projectId)).authenticate();
	}
	public static void main(String[] args) {
		System.out.println("Instance test start....");
		OSFactory.enableHttpLoggingFilter(true);
		com.huawei.openstack.sample.Openstack4jSample.AuthenticateAksk authenticateAksk = new com.huawei.openstack.sample.Openstack4jSample.AuthenticateAksk();
		//choose ak/sk authenticate
		InstanceManageService instanceManageService =  authenticateAksk.osclient.rds().instanceManage();
		//list instance
		ListInstanceTest(instanceManageService,"913ae025896c4a019afcd5afc4ff38e6in01");
	    //list error log
		ListErrorLogTest(instanceManageService,"913ae025896c4a019afcd5afc4ff38e6in01");
		//list slow log
		ListSlowLogTest(instanceManageService,"913ae025896c4a019afcd5afc4ff38e6in01");
		//create instance
		CreatInstanceTest(instanceManageService);
		//restart instance
		RestartInstanceTest(instanceManageService,"913ae025896c4a019afcd5afc4ff38e6in01");
		//single to ha
		SingletohaTest(instanceManageService,"913ae025896c4a019afcd5afc4ff38e6in01");
		//resize flavor
		ResizeFlavorTest(instanceManageService,"913ae025896c4a019afcd5afc4ff38e6in01");
		//enlarge volume
		EnlargeVolumeTest(instanceManageService,"f0fd160d39b44322afa5541b1a232c69no01");
		//delete instance
		DeleteInstanceTest(instanceManageService,"ef3ee924961c42758ec4fc895ab73d74in01");
		//list all instance
		ListInstanceAllTest(instanceManageService);
		ListFlavorsTest(instanceManageService, "Sqlserver");
	}

	public static void CreatInstanceTest(InstanceManageService instanceManageService)
	{
		//Openstack4jSample.osclient = osclient;
		DataStore datastore = DataStore.builder().type("MySQL")
				.version("5.6").build();
		Ha createha = Ha.builder().mode("Ha")
				.replicationMode("semisync").build();
		Volume createVolume = Volume.builder().type("ULTRAHIGH")
				.size(100).build();
		BackupStrategy createBackupStrategy = BackupStrategy.builder().keepDays(7)
				.startTime("06:15-07:15").build();

		CreateInstanceRequest	CreateReq = CreateInstanceRequest.builder()
				.name("JAVA100_2_S-2")
				.volume(createVolume)
				.ha(createha)
				.datastore(datastore)
				.backupStrategy(createBackupStrategy)
				.flavorRef("rds.mysql.s1.medium.ha")
				.availabilityZone("cn-north-4b,cn-north-4b")
				.vpcId("3138ce3d-8837-49a6-b68a-4cdbc5b30a45")
				.subnetId("0f48e1d1-c244-422a-baa0-acfb1133c148")
				.securityGroupId("702e9e18-34a2-4eda-a847-59546c3f5fa5")
				.password("Huawei@123***")
				.port("3306")
				.region("cn-north-4").build();
		try {
			if (instanceManageService == null)
			{
				System.out.println("[Error information]Get authenticate is null!");
				return;
			}
			CreateInstanceResponse repCreate = instanceManageService.create(CreateReq);
			if (repCreate.getJobId() != "")
			{
				System.out.println(repCreate.getInstance());
			}else
			{
				System.out.println(repCreate.getInstance());
			}
		}
		catch (ServerResponseException e)
		{
			System.out.println(e.getStatusCode());
		}

	}
	public static void ListInstanceTest(InstanceManageService instanceManageService,String instanceId)
	{
		try {
			Map<String, String> filteringParams = new HashMap<String, String>();
			filteringParams.put("offset", "0");
			filteringParams.put("limit", "10");
			filteringParams.put("id", instanceId);
			if (instanceManageService == null)
			{
				System.out.println("[Error information]Get authenticate is null!");
				return;
			}
			ListInstanceResponse ListInstances = instanceManageService.list(filteringParams);					;
			if (ListInstances != null){
				System.out.println("server list: " + ListInstances);
				if (ListInstances.getInstances().length>0){
					for (ListInstanceResp InstanceInfor:ListInstances.getInstances()) {
						System.out.println("Instances number is : " + ListInstances.getInstances().length);
						System.out.println("InstanceInfor.getName: " + InstanceInfor.getName());
						System.out.println("InstanceInfor.getName: " + InstanceInfor.getId());
					}

				}
			}

			if (ListInstances.getTotalCount() != 0)
			{
				System.out.println(ListInstances.getInstances() );
			}
			else
			{
				System.out.println("Instances number is 0 !");
			}
		}
		catch (ServerResponseException e)
		{
			System.out.println(e.getStatusCode());
		}
	}
	public static void ListInstanceAllTest(InstanceManageService instanceManageService)
	{
		try {
			if (instanceManageService == null)
			{
				System.out.println("[Error information]Get authenticate is null!");
				return;
			}
			ListInstanceResponse rep = instanceManageService.list();
			if (rep.getTotalCount() != 0)
			{
				System.out.println(rep.getInstances() );
			}
			else
			{
				System.out.println("Instances number is 0 !");
			}
		}
		catch (ServerResponseException e)
		{
			System.out.println(e.getStatusCode());
		}
	}
	public static void DeleteInstanceTest(InstanceManageService instanceManageService, String instanceId)
	{
		try {
			if (instanceManageService == null)
			{
				System.out.println("[Error information]Get authenticate is null!");
				return;
			}
			ActionResponse response = instanceManageService.delete(instanceId);
			if (response.getCode() != 0)
			{
				System.out.println(response.getCode() );
			}
		}
		catch (ServerResponseException e)
		{
			System.out.println(e.getStatusCode());
		}
	}

	public static void RestartInstanceTest(InstanceManageService instanceManageService, String instanceId)
	{
		try {
			RestartInstanceRequest restart = RestartInstanceRequest.builder().restart("").build();
			InstanceCommonResponse rep = instanceManageService.restart(restart,instanceId);
			if (rep.getJobId() != "")
			{
				System.out.println(rep.getJobId() );
			}
		}
		catch (ServerResponseException e)
		{
			System.out.println(e.getStatusCode());
		}
	}

	public static void SingletohaTest(InstanceManageService instanceManageService, String instanceId)
	{
		try {
			SingleToHa single = SingleToHa.builder().azCodeNewNode("cn-north-4c").password("Huawei@123***").build();
			SingleToHaRdsRequest singletoha = SingleToHaRdsRequest.builder().singleToHa(single).build();
			if (instanceManageService == null)
			{
				System.out.println("[Error information]Get authenticate is null!");
				return;
			}
			InstanceCommonResponse rep = instanceManageService.singleToHa(singletoha,instanceId);
			if (rep.getJobId() != "")
			{
				System.out.println(rep.getJobId() );
			}
		}
		catch (ServerResponseException e)
		{
			System.out.println(e.getStatusCode());
		}
	}
	public static void ResizeFlavorTest(InstanceManageService instanceManageService, String instanceId)
	{
		try {
			ResizeFlavor resizeFlavor = ResizeFlavor.builder().specCode("rds.mysql.m1.4xlarge.ha").build();
			ResizeFlavorRequest resizeFlavorRequest = ResizeFlavorRequest.builder().resizeFlavor(resizeFlavor).build();
			if (instanceManageService == null)
			{
				System.out.println("[Error information]Get authenticate is null!");
				return;
			}
			InstanceCommonResponse rep = instanceManageService.resizeFlavor(resizeFlavorRequest,instanceId);
			if (rep.getJobId() != "")
			{
				System.out.println(rep.getJobId() );
			}
		}
		catch (ServerResponseException e)
		{
			System.out.println(e.getStatusCode());
		}
	}
	public static void EnlargeVolumeTest(InstanceManageService instanceManageService, String instanceId)
	{
		try {
			EnlargeVolumeSize volumeSize = EnlargeVolumeSize.builder().size(200).build();
			EnlargeVolumeRequest enlargeVolumeRequest = EnlargeVolumeRequest.builder().enlargeVolume(volumeSize).build();
			if (instanceManageService == null)
			{
				System.out.println("[Error information]Get authenticate is null!");
				return;
			}
			InstanceCommonResponse rep = instanceManageService.enlargeVolume(enlargeVolumeRequest,instanceId);
			if (rep.getJobId() != "")
			{
				System.out.println(rep.getJobId() );
			}
		}
		catch (ServerResponseException e)
		{
			System.out.println(e.getStatusCode());
		}
	}
	public static void ListErrorLogTest(InstanceManageService instanceManageService, String instanceId)
	{
		Map<String, String> filteringParams = new HashMap<String, String>();
		filteringParams.put("offset", "1");
		filteringParams.put("limit", "10");
		filteringParams.put("start_date", "2019-09-30T10:41:14+0800");
		filteringParams.put("end_date", "2019-10-16T10:41:14+0800");
		try {
			if (instanceManageService == null)
			{
				System.out.println("[Error information]Get authenticate is null!");
				return;
			}
			ErrorLogResponse rep = instanceManageService.listErrorLog(filteringParams,instanceId);
			if (rep.getTotalRecord() != 0)
			{
				System.out.println(rep.getErrorLogList() );
				for (ErrorLogList errorLogList:rep.getErrorLogList())
				{
					System.out.println(errorLogList.getContent());
				}
			}
			else
			{
				System.out.println("Get ErrorLogList number is 0 !");
			}
		}
		catch (ServerResponseException e)
		{
			System.out.println(e.getStatusCode());
		}
	}
	public static void ListSlowLogTest(InstanceManageService instanceManageService, String instanceId)
	{
		Map<String, String> filteringParams = new HashMap<String, String>();
		filteringParams.put("offset", "1");
		filteringParams.put("limit", "10");
		filteringParams.put("start_date", "2019-09-30T10:41:14+0800");
		filteringParams.put("end_date", "2019-10-16T10:41:14+0800");
		try {
			if (instanceManageService == null)
			{
				System.out.println("[Error information]Get authenticate is null!");
				return;
			}
			SlowLogListResponse rep = instanceManageService.listSlowLog(filteringParams,instanceId);
			if (rep.getTotalRecord() != 0)
			{
				System.out.println(rep.getSlowLogList());
				for (SlowLogList slowLogList:rep.getSlowLogList())
				{
					System.out.println(slowLogList.getDatabase());
				}
			}
			else
			{
				System.out.println("Get SlowLogList number is 0 !");
			}
		}
		catch (ServerResponseException e)
		{
			System.out.println(e.getStatusCode());
		}
	}

	public static void ListFlavorsTest(InstanceManageService instanceManageService, String engineName) {
		Map<String, String> filteringParams = new HashMap<String, String>();
		filteringParams.put("version_name", "8.0");
		filteringParams.put("spec_code", "rds.mysql.4xlarge.arm4.ha");
		instanceManageService.listFlavors(null, engineName);
	}

}