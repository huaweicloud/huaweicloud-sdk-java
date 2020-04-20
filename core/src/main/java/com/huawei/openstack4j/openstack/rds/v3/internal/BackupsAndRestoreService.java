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
package com.huawei.openstack4j.openstack.rds.v3.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.rds.v3.domain.*;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class BackupsAndRestoreService extends BaseRdsServices implements RestService {

	private static final String API_PATH_INS = "/instances";

	private static final String API_PATH_END = "/backups/policy";

	private static final String API_PATH = "/backups";

	/**
	 * Setting an Automated Backup Policy
	 * @param updateModel
	 * @param instanceId
	 * @return
	 */
	public ActionResponse setBackupsPolicy(SetBackupsPolicyRequest updateModel, String instanceId){
		checkArgument((null != updateModel),"parameter `updateModel` should not be null");
		checkArgument((null != updateModel.getBackupPolicy()), "parameter 'backupPolicy' should not be null");
		checkArgument(!Strings.isNullOrEmpty(instanceId),"parameter `configId` should not be empty");
		ActionResponse response = putWithResponse(uri(API_PATH_INS + "/%s" + API_PATH_END, instanceId)).entity(updateModel).execute();
		return response;
	}

	/**
	 * Obtaining an Automated Backup Policy
	 * @param instanceId
	 * @return
	 */
	public BackupPolicy getBackupsPolicy(String instanceId){
		checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `configId` should not be empty");
		return get(BackupPolicy.class, uri(API_PATH_INS + "/" + instanceId + API_PATH_END)).execute();
	}

	/**
	 * Creating a Manual Backup
	 */
	public ManualBackupResponse create(ManualBackupRequest request){
		checkArgument((null != request), "parameter 'request' should not be null");
		checkArgument(!Strings.isNullOrEmpty(request.getInstanceId()), "parameter 'instanceId' should not be empty");
		checkArgument(!Strings.isNullOrEmpty(request.getName()), "parameter 'name' should not be empty");
		return post(ManualBackupResponse.class, uri(API_PATH)).entity(request).execute();
	}

	/**
	 * Obtaining Details About Backups
	 */
	public BackupsResponse list(String instanceId, Map<String, String> filteringParams){
		checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter 'instanceId' should not be empty");
		checkArgument((null != filteringParams), "parameter `filteringParams` should not be null!");
		Invocation<BackupsResponse> serverInvocation = get(BackupsResponse.class, uri(API_PATH)).param("instance_id", instanceId);
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return serverInvocation.execute();
	}

	/**
	 * Obtaining Details About Backups
	 */
	public BackupsResponse list(String instanceId){
		checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter 'instanceId' should not be empty");
		Invocation<BackupsResponse> serverInvocation = get(BackupsResponse.class, uri(API_PATH)).param("instance_id", instanceId);
		return serverInvocation.execute();
	}

	/**
	 * Obtaining the Link for Downloading a Backup File
	 */
	public BackupFileResponse listFile(String backupId){
		checkArgument(!Strings.isNullOrEmpty(backupId), "parameter 'backupId' should not be empty");
		return get(BackupFileResponse.class, uri("/backup-files")).param("backup_id", backupId).execute();
	}

	/**
	 * Deleting a Manual Backup
	 */
	public ActionResponse delete(String backupId){
		checkArgument(!Strings.isNullOrEmpty(backupId), "parameter 'backupId' should not be empty");
		return deleteWithResponse(uri(API_PATH + "/%s", backupId)).execute();
	}

	/**
	 * Querying the Restoration Time Range
	 */
	public RestoreTimeResponse getRestoreTime(String instanceId){
		checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter 'instanceId' should not be empty");
		return get(RestoreTimeResponse.class, uri(API_PATH_INS + "/%s" + "/restore-time", instanceId)).execute();
	}

	/**
	 * Restoring Data to a New DB Instance
	 */
	public CreateInstanceResponse restoreNewInstance(CreateInstanceRequest request){
		checkArgument((null != request), "parameter 'request' should not be null");
		checkArgument(!Strings.isNullOrEmpty(request.getName()), "parameter 'name' should not be empty");
		checkArgument(!Strings.isNullOrEmpty(request.getPassword()), "parameter 'password' should not be empty");
		checkArgument(!Strings.isNullOrEmpty(request.getFlavorRef()), "parameter 'flavorRef' should not be empty");
		checkArgument((null != request.getVolume()), "parameter 'volume' should not be null");
		checkArgument(!Strings.isNullOrEmpty(request.getAvailabilityZone()), "parameter 'availabilityZone' should not be empty");
		checkArgument(!Strings.isNullOrEmpty(request.getVpcId()), "parameter 'vpcId' should not be empty");
		checkArgument(!Strings.isNullOrEmpty(request.getSubnetId()), "parameter 'subnetId' should not be empty");
		checkArgument(!Strings.isNullOrEmpty(request.getSecurityGroupId()), "parameter 'securityGroupId' should not be empty");
		checkArgument((null != request.getRestorePoint()), "parameter 'restorePoint' should not be null");
		return post(CreateInstanceResponse.class, uri(API_PATH_INS)).entity(request).execute();
	}

	/**
	 * Restoring Data to an Existing DB Instance
	 */
	public RestoreExistInsResponse retoreExistInstance(RestoreExistInsRequest request){
		checkArgument((null != request), "parameter 'request' should not be null");
		checkArgument((null != request.getSource()), "parameter 'source' should not be null");
		checkArgument((null != request.getTarget()), "parameter 'target' should not be null");
		return post(RestoreExistInsResponse.class, uri(API_PATH_INS + "/recovery")).entity(request).execute();
	}
}
