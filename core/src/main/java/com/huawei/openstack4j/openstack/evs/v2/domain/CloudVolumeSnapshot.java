/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.openstack.evs.v2.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("snapshot")
public class CloudVolumeSnapshot implements ModelEntity
{

	private static final long serialVersionUID = -8993366908821009751L;

	/**
	 * {
	 *       "status": "available",
	 *       "description": null,
	 *       "availability_zone": "kvmxen.dc1",
	 *       "dedicated_storage_id": null,
	 *       "updated_at": "2018-12-27T11:53:51.017086",
	 *       "volume_id": "b044731f-8b6b-4a91-8ad7-1daaffaeadc0",
	 *       "id": "9667d989-8632-41c6-ae78-333beab3bfbe",
	 *       "size": 10,
	 *       "os-extended-snapshot-attributes:progress": "100%",
	 *       "name": "autobk_snapshot_vbs_2018-05-22T12:14:38.778Z",
	 *       "created_at": "2018-05-22T12:14:39.783430",
	 *       "volume_type": "SAS",
	 *       "dedicated_storage_name": null,
	 *       "service_type": "EVS",
	 *       "os-extended-snapshot-attributes:project_id": "000efdc5f9064584b718b181df137bd7",
	 *       "metadata": {}
	 *     }
	 */

	/**
	 * 云硬盘快照ID。
	 */
	@JsonProperty("id")
	private String id;

	/**
	 * 云硬盘快照名称。
	 */
	@JsonProperty("name")
	private String name;

	/**
	 * 云硬盘快照描述信息。
	 */
	@JsonProperty("description")
	private String description;

	/**
	 * 快照所属的云硬盘ID。
	 */
	@JsonProperty("volume_id")
	private String volumeId;

	/**
	 * 云硬盘快照的状态。
	 */
	@JsonProperty("status")
	private String status;

	/**
	 * 云硬盘快照大小。
	 */
	@JsonProperty("size")
	private Integer size;

	/**
	 * 云硬盘快照创建时间。
	 */
	@JsonProperty("created_at")
	private String createdAt;

	/**
	 * 云硬盘快照的元数据信息。
	 * 如果元数据中包含__system__enableActive字段，则表示该快照为云服务器创建备份时自动生成的快照。
	 */
	@JsonProperty("metadata")
	private Map<String, String> metadata;

	/**
	 * 服务类型。
	 */
	@JsonProperty("service_type")
	private String serviceType;

	/**
	 * 专属存储ID。
	 */
	@JsonProperty("dedicated_storage_id")
	private String dedicatedStorageId;

	/**
	 * 专属存储的名称。
	 */
	@JsonProperty("dedicated_storage_name")
	private String dedicatedStorageName;

	/**
	 * 云硬盘快照更新时间。
	 */
	@JsonProperty("update_at")
	private String updateAt;

	/**
	 * 快照进度。
	 */
	@JsonProperty("os-extended-snapshot-attributes:progress")
	private String progress;

	/**
	 * 租户ID。
	 */
	@JsonProperty("os-extended-snapshot-attributes:project_id")
	private String projectId;
}
