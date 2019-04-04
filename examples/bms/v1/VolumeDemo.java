/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack.sample.v1;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.bms.v1.domain.VolumeAttachment;

public class VolumeDemo {
	public static void main(String[] args) {
		
		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";
		
		OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();
		
		String serverId = "******";
		String volumeId = "******";
		String device = "******";
		
		//1.bms detach Volume
		ActionResponse detachVolume = osclient.bms().servers().detachVolume(serverId, volumeId);
		System.out.println(detachVolume);
		System.out.println("detach Volume success...");
		
		//2.bms attach Volume
		VolumeAttachment volume = new VolumeAttachment(volumeId,device);
		ActionResponse attachVolume = osclient.bms().servers().attachVolume(serverId, volume);
		System.out.println(attachVolume);
		System.out.println("attach Volume success...");
	}

}
