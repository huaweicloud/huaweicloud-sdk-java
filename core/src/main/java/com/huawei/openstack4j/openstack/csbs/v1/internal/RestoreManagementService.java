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

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.csbs.v1.domain.Restore;
import com.huawei.openstack4j.openstack.csbs.v1.domain.RestoreResp;


public class RestoreManagementService extends BaseCloudServerBackupService{
	
	/**
	 *使用备份进行恢复 
	 * @param restore
	 * @return
	 */
	public RestoreResp create(Restore restore) {
		checkArgument(!Strings.isNullOrEmpty(restore.getCheckpointId()),
				"parameter `checkpointId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(restore.getProviderId()),
				"parameter `providerId` should not be empty");
		checkArgument(null != restore.getParameters(),
				"parameter `restoreParam` should not be empty");
		
		return post(RestoreResp.class,uri("/restores")).entity(restore).execute();
	}
}
