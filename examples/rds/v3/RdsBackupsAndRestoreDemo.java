/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * 	use this file except in compliance with the License. You may obtain a copy of
 * 	the License at
 *
 * 	    http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * 	License for the specific language governing permissions and limitations under
 * 	the License.
 *******************************************************************************/
package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.rds.v3.domain.*;
import java.util.HashMap;
import java.util.Map;

public class BackupsAndRestoreDemo {

	private static final String LANGUAGE = "zh-cn";

	public static void main(String[] args) {

		/**
		 * Using credentials for authentication
		 */

		String authUrl = "****************/";//endpoint url

		String user = "****************";//username

		String password = "****************";//password

		String projectId = "****************";//projectId

		String userDomianId = "****************";//domainId

		//initial Openstack4j Client
		OSFactory.enableHttpLoggingFilter(true);


		//create connection
		OSClient.OSClientV3 osClient = OSFactory.builderV3().endpoint(authUrl)
												.credentials(user, password, Identifier.byId(userDomianId))
												.scopeToProject(Identifier.byId(projectId)).authenticate();

		String instanceId = "****************";
		String backupId = "****************";

		//Setting an Automated Backup Policy
		BackupPolicy backupPolicy = BackupPolicy.builder().keepDays(7)
															.startTime("19:00-20:00")
															.period("1,2").build();

		SetBackupsPolicyRequest request = SetBackupsPolicyRequest.builder().backupPolicy(backupPolicy).build();

		ActionResponse actionResponse = osClient.rds().backupRestore().setBackupsPolicy(request, instanceId);
		if (actionResponse.isSuccess()){
			System.out.println("set backup policy success");
		}else {
			System.out.println("set backup policy failed");
		}

		//Obtaining an Automated Backup Policy
		BackupPolicy respBackupPolicy = osClient.rds().backupRestore().getBackupsPolicy(instanceId);
		if (null != respBackupPolicy){
			System.out.println("obtain backup policy success");
		}else {
			System.out.println("obtain backup policy failed");
		}

		//Creating a Manual Backup
		ManualBackupRequest manualBackupRequest = ManualBackupRequest.builder().instanceId(instanceId)
																				.name("backup")
																				.description("manual backup").build();
		ManualBackupResponse result = osClient.rds().backupRestore().create(manualBackupRequest);
		System.out.println(result.toString());
		if (null != result){
			System.out.println("create manual backup success");
		}else {
			System.out.println("create manual backup failed");
		}

		//Obtaining Details About Backups
		Map<String, String> filteringParams = new HashMap<String, String>();
		BackupsResponse backupsResponse = osClient.rds().backupRestore().list(instanceId, filteringParams);
		if (null != backupsResponse){
			System.out.println("obtain detail about backups success");
		}else {
			System.out.println("obtain detail about backups failed");
		}

		//Obtaining the Link for Downloading a Backup File
		BackupFileResponse backupFileResponse = osClient.rds().backupRestore().listFile(backupId);
		if (null != backupFileResponse){
			System.out.println("obtain the link for downloading a backup file success");
		}else {
			System.out.println("obtain the link for downloading a backup file failed");
		}

		//Deleting a Manual Backup
		ActionResponse deleteResponse = osClient.rds().backupRestore().delete(backupId);
		if (deleteResponse.isSuccess()){
			System.out.println("delete a manual backup success");
		}else {
			System.out.println("delete a manual backup failed");
		}

		//Querying the Restoration Time Range
		RestoreTimeResponse restoreTimeResponse = osClient.rds().backupRestore().getRestoreTime(instanceId);
		if (null != restoreTimeResponse){
			System.out.println("query the restoration time range success");
		}else {
			System.out.println("query the restoration time range failed");
		}

		//Restoring Data to a New DB Instance
		Ha createHa = Ha.builder().mode("ha")
							.replicationMode("async").build();

		DataStore createDatastore = DataStore.builder().type("MySQL")
				                      .version("5.6").build();

		Volume createVolume = Volume.builder().size(100)
												.type("ULTRAHIGH").build();

		BackupStrategy createBackupStrategy = BackupStrategy.builder().keepDays(7)
																		.startTime("06:15-07:15").build();

		RestorePoint restorePoint = RestorePoint.builder().instanceId(instanceId)
														.type("timestamp")
				                                        .restoreTime(1532001446987L).build();

		CreateInstanceRequest createInstanceRequest = CreateInstanceRequest.builder().name("targetInst")
															.availabilityZone("****************")
															.region("****************")
															.ha(createHa)
															.datastore(createDatastore)
															.port("3306")
															.flavorRef("****************")
															.volume(createVolume)
															.vpcId("****************")
															.subnetId("****************")
															.securityGroupId("****************")
															.backupStrategy(createBackupStrategy)
															.password("*********")
															.restorePoint(restorePoint).build();
		CreateInstanceResponse createInstanceResponse = osClient.rds().backupRestore().restoreNewInstance(createInstanceRequest);
		if (null != createInstanceResponse){
			System.out.println("restore data to a new DB instance success");
		}else {
			System.out.println("restore data to a new DB instance failed");
		}

		//Restoring Data to an Existing DB Instance
		Source source = Source.builder().instanceId(instanceId).build();

		Target target = Target.builder().instanceId(instanceId).build();

		RestoreExistInsRequest restoreExistInsRequest = RestoreExistInsRequest.builder().source(source)
																						.target(target).build();

		RestoreExistInsResponse restoreExistInsResponse = osClient.rds().backupRestore().retoreExistIns(restoreExistInsRequest);
		if (null != restoreExistInsResponse){
			System.out.println("restore data to an existing DB instance success");
		}else{
			System.out.println("restore data to an existing DB instance failed");
		}

	}
}
