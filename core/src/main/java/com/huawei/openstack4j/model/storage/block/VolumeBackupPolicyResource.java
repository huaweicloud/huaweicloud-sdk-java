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
package com.huawei.openstack4j.model.storage.block;

import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 21:21:16
 */
public interface VolumeBackupPolicyResource extends ModelEntity {
	
	/**
	 * 
	 * @return resource id
	 */
	public String getId();

	/**
	 * 
	 * @return resource type
	 */
	public String getType();

	/**
	 * 
	 * @return resource availability zone
	 */
	public String getAvailabilityZone();

	/**
	 * 
	 * @return the POD resource belongs to
	 */
	public String getOsVolHostAttr();
	
	
	public interface VolumeBackupPolicyResourceActionResult extends ModelEntity {
		
		List<? extends VolumeBackupPolicyResource> getSuccessResources();
		
		List<? extends VolumeBackupPolicyResource> getFailResources();

	}

}

