 /*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co.,Ltd.                                         
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
package com.huawei.openstack4j.openstack.csbs.v1.internal;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.common.RestService;

public class CloudServerBackupService extends BaseCloudServerBackupService implements RestService {
	
	public BackupManagementService backups() {
		return Apis.get(BackupManagementService.class);
	}
	
//	public RestoreManagementService restores() {
//		return Apis.get(RestoreManagementService.class);
//	}
	
//	public QuotaManagementService quotas() {
//		return Apis.get(QuotaManagementService.class);
//	}
	
//	public ProjectManagementService protects() {
//		return Apis.get(ProjectManagementService.class);
//	}
	
//	public ReplicationManagementService replications() {
//		return Apis.get(ReplicationManagementService.class);
//	}
	
//	public BackupPoliciesService policies(){
//		return Apis.get(BackupPoliciesService.class);
//	}
	
//	public BackupResourceService resources(){
//		return Apis.get(BackupResourceService.class);
//	}
	
}
