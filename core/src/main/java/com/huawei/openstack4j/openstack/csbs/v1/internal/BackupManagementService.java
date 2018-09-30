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
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckPointItem;

public class BackupManagementService extends BaseCloudServerBackupService{

	/**
	 * 根据指定id查询单个备份
	 * @param checkpointItemId
	 * @return
	 */
	public CheckPointItem get(String checkpointItemId){
        return get(CheckPointItem.class, uri("/checkpoint_items/")+checkpointItemId).execute();
	}
	
	/**
	 * 删除指定备份记录下的所有的备份
	 * @param checkpointItemId
	 * @param providerId
	 * @return
	 */
	public ActionResponse delete(String providerId,String checkpointId){
		checkArgument(!Strings.isNullOrEmpty(checkpointId),"parameter `checkpoint_id` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(providerId),"parameter `providerId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(Void.class, uri("/providers/"+providerId+"/checkpoints/"+checkpointId)).executeWithResponse());
		
	}
	/**
	 * 删除指定备份记录下某条的备份
	 * @param providerId
	 * @param checkpointId
	 * @param checkpointItemId
	 * @return
	 */
	public ActionResponse delete(String providerId,String checkpointId,String checkpointItemId){
		checkArgument(!Strings.isNullOrEmpty(checkpointId),"parameter `checkpoint_id` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(checkpointItemId),"parameter `checkpointItemId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(providerId),"parameter `providerId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(Void.class, uri("/providers/"+providerId+"/checkpoints/"+checkpointId)).param("checkpoint_items", checkpointItemId).executeWithResponse());
		
	}
	
}
