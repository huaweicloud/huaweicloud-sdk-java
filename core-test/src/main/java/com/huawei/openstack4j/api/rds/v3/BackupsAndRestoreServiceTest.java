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
package com.huawei.openstack4j.api.rds.v3;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.rds.v3.domain.*;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@org.testng.annotations.Test(suiteName = "RDS/RDSV3", enabled = true)
public class BackupsAndRestoreServiceTest extends AbstractTest{

	private static final String BACKUPS_POLICY_SET_REQUEST = "/rds/v3/backups_policy_set_request.json";

	private static final String BACKUPS_POLICY_SET_RESPONSE = "/rds/v3/backups_policy_set_response.json";

	private static final String BACKUPS_MANUAL_CREATE_RESPONSE = "/rds/v3/backups_manual_create_response.json";

	private static final String BACKUPS_LIST_RESPONSE = "/rds/v3/backups_list_response.json";

	private static final String BACKUPS_FILE_LIST_RESPONSE = "/rds/v3/backups_file_list_response.json";

	private static final String RESTORE_TIME_RESPONSE = "/rds/v3/restore_time_response.json";

	private static final String RESTORE_NEW_INS_RESPONSE = "/rds/v3/restore_new_ins_response.json";

	private static final String RESTORE_EXIST_INS_RESPONSE = "/rds/v3/restore_exist_ins_response.json";

	@Test
	public void setBackupsPolicyTest() throws IOException{
		String instanceId = "4b7a23f9db4e463fb4a85ade02027897in01";
		respondWith(BACKUPS_POLICY_SET_REQUEST);
		BackupPolicy backupPolicy = BackupPolicy.builder().keepDays(7)
															.startTime("19:00-20:00")
															.period("1,2").build();

		SetBackupsPolicyRequest request = SetBackupsPolicyRequest.builder().backupPolicy(backupPolicy).build();
		ActionResponse successResponse = osv3().rds().backupRestore().setBackupsPolicy(request, instanceId);
		assertNotNull(successResponse);
		assertTrue(successResponse.isSuccess());
	}

	@Test
	public void getBackupsPolicyTest() throws IOException{
		respondWith(BACKUPS_POLICY_SET_RESPONSE);
		String instanceId = "4b7a23f9db4e463fb4a85ade02027897in01";
		BackupPolicy backupPolicy = osv3().rds().backupRestore().getBackupsPolicy(instanceId);
		assertNotNull(backupPolicy);
	}

	@Test
	public void createTest() throws IOException{
		respondWith(BACKUPS_MANUAL_CREATE_RESPONSE);
		String instanceId = "4b7a23f9db4e463fb4a85ade02027897in01";
		String name = "backupDemo";
		ManualBackupRequest manualBackupRequest = ManualBackupRequest.builder().instanceId(instanceId)
				                                          .name("backupDemo")
				                                          .description("This is a description").build();
		ManualBackupResponse response = osv3().rds().backupRestore().create(manualBackupRequest);
		assertTrue(name.equals(response.getBackup().getName()));
	}

	@Test
	public void listTest() throws IOException{
		respondWith(BACKUPS_LIST_RESPONSE);
		Map<String, String> filteringParams = new HashMap<String, String>();
		String instanceId = "4b7a23f9db4e463fb4a85ade02027897in01";
		BackupsResponse backupsResponse = osv3().rds().backupRestore().list(instanceId, filteringParams);
		assertTrue(instanceId.equals(backupsResponse.getBackups().get(0).getInstanceId()));
	}

	@Test
	public void listFileTest() throws IOException{
		respondWith(BACKUPS_FILE_LIST_RESPONSE);
		String backupId = "2f4ddb93-b901-4b08-93d8-1d2e472f30fe";
		BackupFileResponse backupFileResponse= osv3().rds().backupRestore().listFile(backupId);
		assertNotNull(backupFileResponse);
	}

	@Test
	public void deleteTest(){
		int code = 200;
		respondWith(code);
		String backupId = "2f4ddb93-b901-4b08-93d8-1d2e472f30fe";
		ActionResponse deleteResponse = osv3().rds().backupRestore().delete(backupId);
		assertNotNull(deleteResponse);
		assertTrue(deleteResponse.isSuccess());
		assertTrue(deleteResponse.getCode() == code);
	}

	@Test
	public void getRestoreTimeTest() throws IOException{
		respondWith(RESTORE_TIME_RESPONSE);
		String instanceId = "4b7a23f9db4e463fb4a85ade02027897in01";
		RestoreTimeResponse restoreTimeResponse = osv3().rds().backupRestore().getRestoreTime(instanceId);
		assertNotNull(restoreTimeResponse);
	}

	@Test
	public void restoreNewInsTest() throws IOException{
		respondWith(RESTORE_NEW_INS_RESPONSE);
		String instanceId = "4b7a23f9db4e463fb4a85ade02027897in01";
		Ha createHa = Ha.builder().mode("ha")
				              .replicationMode("async").build();

		Volume createVolume = Volume.builder().size(100)
				                      .type("ULTRAHIGH").build();

		DataStore createDatastore = DataStore.builder().type("MySQL")
				                            .version("5.6").build();

		BackupStrategy createBackupStrategy = BackupStrategy.builder().keepDays(7)
				                                      .startTime("06:15-07:15").build();

		RestorePoint restorePoint = RestorePoint.builder().instanceId(instanceId)
				                            .type("timestamp")
				                            .restoreTime(1532001446987L).build();

		CreateInstanceRequest createInstanceRequest = CreateInstanceRequest.builder().name("targetInst")
																.availabilityZone("az2xahz,az1xahz")
																.region("cn-xianhz-1")
																.ha(createHa)
																.port("3306")
																.datastore(createDatastore)
																.flavorRef("rds.mysql.c2.medium.ha")
																.volume(createVolume)
																.vpcId("e9e3631a-5b2b-4275-8533-70d1ee0405e4")
																.subnetId("a056aa37-103a-4bc0-9d5f-ec1f3562ba2d")
																.securityGroupId("a9144c93-15c9-4fa0-8683-a4ad293b40ae")
																.backupStrategy(createBackupStrategy)
																.password("********")
																.restorePoint(restorePoint).build();

		CreateInstanceResponse result = osv3().rds().backupRestore().restoreNewInstance(createInstanceRequest);
		assertNotNull(result);
		assertTrue(instanceId.equals(result.getInstance().getId()));
	}

	@Test
	public void restoreExistIns() throws IOException{
		respondWith(RESTORE_EXIST_INS_RESPONSE);
		String instanceId = "4b7a23f9db4e463fb4a85ade02027897in01";
		String jobId = "ff80808157127d9301571bf8160c001d";
		Source source = Source.builder().instanceId(instanceId).build();
		Target target = Target.builder().instanceId(instanceId).build();
		RestoreExistInsRequest restoreExistInsRequest = RestoreExistInsRequest.builder().source(source)
				                                                .target(target).build();
		RestoreExistInsResponse result = osv3().rds().backupRestore().retoreExistInstance(restoreExistInsRequest);
		assertNotNull(result);
		assertTrue(jobId.equals(result.getJobId()));
	}

	@Override
	protected Service service() {
		return Service.RDSV3;
	}
}
