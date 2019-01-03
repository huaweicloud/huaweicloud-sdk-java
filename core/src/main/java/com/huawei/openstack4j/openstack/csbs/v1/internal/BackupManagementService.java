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

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.csbs.v1.domain.BackupCount;
import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckPoint;
import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckPointItem;
import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckPointItem.CheckPointItems;
import com.huawei.openstack4j.openstack.csbs.v1.domain.CheckPointResp;

public class BackupManagementService extends BaseCloudServerBackupService {

	/**
	 * 根据指定id查询单个备份
	 * 
	 * @param checkpointItemId
	 * @return
	 */
	public CheckPointItem get(String checkpointItemId) {
		return get(CheckPointItem.class,
				uri("/checkpoint_items/") + checkpointItemId).execute();
	}

	/**
	 * 删除指定备份记录下的所有的备份
	 * 
	 * @param checkpointItemId
	 * @param providerId
	 * @return
	 */
	public ActionResponse delete(String providerId, String checkpointId) {
		checkArgument(!Strings.isNullOrEmpty(checkpointId),
				"parameter `checkpoint_id` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(providerId),
				"parameter `providerId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(
						Void.class,
						uri("/providers/" + providerId + "/checkpoints/"
								+ checkpointId)).executeWithResponse());

	}

	/**
	 * 删除指定备份记录下某条的备份
	 * 
	 * @param providerId
	 * @param checkpointId
	 * @param checkpointItemId
	 * @return
	 */
	public ActionResponse delete(String providerId, String checkpointId,
			String checkpointItemId) {
		checkArgument(!Strings.isNullOrEmpty(checkpointId),
				"parameter `checkpoint_id` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(checkpointItemId),
				"parameter `checkpointItemId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(providerId),
				"parameter `providerId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(
						Void.class,
						uri("/providers/" + providerId + "/checkpoints/"
								+ checkpointId)).param("checkpoint_items",
						checkpointItemId).executeWithResponse());

	}

	/**
	 * 手工执行备份策略，创建备份
	 * 
	 * @param checkPoint
	 * @param providerId
	 * @return
	 */
	public CheckPointResp create(CheckPoint checkPoint, String providerId) {
		checkArgument(!Strings.isNullOrEmpty(providerId),
				"parameter `providerId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(checkPoint.getPlanId()),
				"parameter `planId` should not be empty");
		checkArgument(null != checkPoint.getParameters(),
				"parameter `checkPointParam` should not be empty");
		return post(CheckPointResp.class,
				uri("/providers/%s/checkpoints", providerId)).entity(checkPoint).execute();
	}

	/**
	 * 查询备份数量
	 * 
	 * @return
	 */
	public Integer get() {
		return get(BackupCount.class, "/checkpoint_items/count").execute().getCount();
	}

	/**
	 * 查询备份数量
	 * 
	 * @param filteringParams
	 * @return
	 */
	public Integer get(Map<String, String> filteringParams) {
		Invocation<BackupCount> req = get(BackupCount.class,
				uri("/checkpoint_items/count"));
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				req = req.param(entry.getKey(), entry.getValue());
			}
		}
		return req.execute().getCount();
	}
	/**
	 * 查询所有备份
	 * @return
	 */
	public List<CheckPointItem> list() {
		return get(CheckPointItems.class, "/checkpoint_items").execute().getList();
	}
	
	/**
	 * 查询所有备份
	 * @param filteringParams
	 * @return
	 */
	public List<CheckPointItem> list(Map<String, String> filteringParams) {
		Invocation<CheckPointItems> req = get(CheckPointItems.class,
				uri("/checkpoint_items"));
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				req = req.param(entry.getKey(), entry.getValue());
			}
		}
		return req.execute().getList();
	}
	/**
	 * 选择备份策略执行复制，为备份策略生成的未进行过复制的备份创建复制。
	 * @param replication
	 * @param providerId
	 * @return
	 */
//	public Replication create(Replication replication, String providerId) {
//		checkArgument(!Strings.isNullOrEmpty(providerId),
//				"parameter `providerId` should not be empty");
//		checkArgument(!Strings.isNullOrEmpty(replication.getPolicyId()),
//				"parameter `policyId` should not be empty");
//		checkArgument(null != replication.getDestinationRegion(),
//				"parameter `destinationRegion` should not be empty");
//		checkArgument(null != replication.getDestinationProjectId(),
//				"parameter `destinationProjectId` should not be empty");
//		return post(Replication.class,
//				uri("/providers/%s/checkpoints/replicate", providerId)).entity(replication).execute();
//	}
	/**
	 *选择单个备份进行复制，如果该备份在目标区域正在生成或者已经生成复制，则无法再次复制 
	 * @param replication
	 * @param providerId
	 * @param checkpointItemId
	 * @return
	 */
//	public Replication create(Replication replication, String providerId,String checkpointItemId) {
//		checkArgument(!Strings.isNullOrEmpty(providerId),
//				"parameter `providerId` should not be empty");
//		checkArgument(!Strings.isNullOrEmpty(checkpointItemId),
//				"parameter `checkpointItemId` should not be empty");
//		checkArgument(null != replication.getDestinationRegion(),
//				"parameter `destinationRegion` should not be empty");
//		checkArgument(null != replication.getDestinationProjectId(),
//				"parameter `destinationProjectId` should not be empty");
//		return post(Replication.class,
//				uri("/providers/%s/checkpoint_items/%s/replicate", providerId,checkpointItemId)).entity(replication).execute();
//	}
}
