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
package com.huawei.openstack4j.openstack.evs.v2.internal;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.common.AsyncJobEntity;
import com.huawei.openstack4j.openstack.evs.v2.domain.CloudVolumes;
import com.huawei.openstack4j.openstack.evs.v2.domain.Extend;

public class VolumeService extends BaseElasticVolumeService{

	/**
	 * Create a Cloud Volume
	 * @param volume
	 * @return
	 */
	public String create(CloudVolumes volume){
		checkArgument(!Strings.isNullOrEmpty(volume.getAvailabilityZone()), "parameter `availabilityZone` should not be empty");
		checkArgument(!((volume.getVolumeType()==null)), "parameter `volumeType` should not be empty");
		return post(AsyncJobEntity.class, "/cloudvolumes").entity(volume).execute().getId();
	}
	
	/**
	 * Extended Cloud Drive
	 * @param extend
	 * @param volumeId
	 * @return
	 */
	public String extend(Extend extend,String volumeId){
		checkArgument(! (null == (extend.getOsExtend().getNewSize())), "parameter `newSize` should not be empty");
		return post(AsyncJobEntity.class, "/cloudvolumes/"+volumeId+"/action").entity(extend).execute().getId();
	}
}
