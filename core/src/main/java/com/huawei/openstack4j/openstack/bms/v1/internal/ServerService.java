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
package com.huawei.openstack4j.openstack.bms.v1.internal;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.bms.v1.domain.BareMetaServer;
import com.huawei.openstack4j.openstack.bms.v1.domain.ServerCreate;
import com.huawei.openstack4j.openstack.bms.v1.domain.ServerRename;
import com.huawei.openstack4j.openstack.bms.v1.domain.VolumeAttachment;
import com.huawei.openstack4j.openstack.common.AsyncRespEntity;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;

public class ServerService extends BaseBareMetaService{

	
	/**
	 * 创建一台或多台裸金属服务器。
	 * @param creation
	 * @return
	 */
	public AsyncRespEntity create(ServerCreate creation){
		checkArgument(!Strings.isNullOrEmpty(creation.getImageRef()), "parameter `imageRef` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getFlavorRef()), "parameter `flavorRef` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getName()), "parameter `name` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getVpcId()), "parameter `vpcId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getAvailabilityZone()), "parameter `availability_zone` should not be empty");
		checkArgument(creation.getNetworks() != null && creation.getNetworks().size() > 0,
				"parameter `networks` should not be empty");
		checkArgument(creation.getMetadata() != null, "parameter `metadata` should not be empty");
		checkArgument(creation.getExtendParam() != null, "parameter `extendparam` should not be empty");
		return post(AsyncRespEntity.class, "/baremetalservers").entity(creation).execute();
	}
	
	/**
	 * 查询裸金属服务器详情
	 * @param serverId
	 * @return
	 */
	public BareMetaServer get(String serverId){
		checkArgument(!Strings.isNullOrEmpty(serverId), "parameter `serverId` should not be empty");
		return get(BareMetaServer.class, uri("/baremetalservers/")+serverId).execute();
	}
	
	/**
	 * 修改裸金属服务器名称
	 * @param serverId
	 * @param name
	 * @return
	 */
	public BareMetaServer rename(String serverId,String name){
		checkArgument(!Strings.isNullOrEmpty(name), "parameter `name` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(serverId), "parameter `serverId` should not be empty");
		return put(BareMetaServer.class, uri("/baremetalservers/")+serverId).entity(ServerRename.builder().name(name).build()).execute();
	}
	
	/**
	 * 裸金属服务器挂载云硬盘（不支持批量挂载）。
	 * @param serverId
	 * @param volume
	 * @return
	 */
	public ActionResponse attachVolume(String serverId, VolumeAttachment volume) {
		checkArgument(!Strings.isNullOrEmpty(serverId), "parameter `serverId` should not be empty");
		checkArgument(volume != null, "parameter `volume` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(post(Void.class, uri("/baremetalservers/%s/attachvolume", serverId)).entity(volume)
						.executeWithResponse());
	}
	
	/**
	 * 裸金属服务器卸载云磁盘。
	 * @param serverId
	 * @param attachmentId
	 * @return
	 */
	public ActionResponse detachVolume(String serverId, String attachmentId) {
		checkArgument(!Strings.isNullOrEmpty(serverId), "parameter `serverId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(attachmentId), "parameter `attachmentId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(Void.class, uri("/baremetalservers/%s/detachvolume/%s", serverId, attachmentId))
						.executeWithResponse());
	}
	
	
	
	
}
