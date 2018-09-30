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

import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.ResourceEntity;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 16:10:39
 */
public interface VolumeBackupPolicy extends ResourceEntity {

	public enum VolumeBackupPolicyStatus {
		ON, OFF;
	}

	/**
	 * 
	 * @return volume backup scheduled policy
	 */
	VolumeBackupScheduledPolicy getScheduledPolicy();

	public interface VolumeBackupScheduledPolicy extends ModelEntity {
		/**
		 * known as "rentention_num" in API documentation
		 * 
		 * @return max backup amount for a volume
		 */
		public Integer getMaxBackupAmount();

		/**
		 * backup scheduled frequency, every "frequency" days
		 * @return
		 */
		public Integer getFrequency();

		/**
		 * if true, the initial data backup in the current month will be retained
		 * @return
		 */
		public Boolean getRetainFirstBackupOfCurrentMonth();

		/**
		 * 
		 * @return scheduled at time, 12:00 for example
		 */
		public String getStartTime();

		/**
		 * 
		 * @return volume backup policy status, includes "ON", "OFF"
		 */
		public VolumeBackupPolicyStatus getStatus();
	}

}
