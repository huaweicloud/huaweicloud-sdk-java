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
package com.huawei.openstack4j.openstack.evs.v2_1.internal;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.common.AsyncRespEntity;
import com.huawei.openstack4j.openstack.evs.v2_1.domain.Extend;
import com.huawei.openstack4j.openstack.evs.v2_1.domain.Volumes;

public class VolumeService extends BaseElasticVolumeService{

	/**
	 * Create a Cloud Volume
	 * @param volume
	 * @return
	 */
	public AsyncRespEntity create(Volumes volume){
		checkArgument(!Strings.isNullOrEmpty(volume.getCloudVolumes().getAvailabilityZone()), "parameter `availabilityZone` should not be empty");
		checkArgument(!((volume.getCloudVolumes().getVolumeType()==null)), "parameter `volumeType` should not be empty");
		return post(AsyncRespEntity.class, "/cloudvolumes").entity(volume).execute();
	}
	
	/**
	 * Extended Cloud Drive
	 * @param extend
	 * @param volumeId
	 * @return
	 */
	public AsyncRespEntity extend(Extend extend,String volumeId){
		checkArgument(! (null == (extend.getOsExtend().getNewSize())), "parameter `newSize` should not be empty");
		return post(AsyncRespEntity.class, "/cloudvolumes/"+volumeId+"/action").entity(extend).execute();
	}
}
