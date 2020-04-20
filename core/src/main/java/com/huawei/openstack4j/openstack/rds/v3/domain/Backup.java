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
package com.huawei.openstack4j.openstack.rds.v3.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Backup implements ModelEntity {

	private static final long serialVersionUID = -3207372565873387912L;

	/**
	 * Indicates the backup ID
	 */
	private String id;

	/**
	 * Indicates the DB instance ID
	 */
	@JsonProperty("instance_id")
	private String instanceId;

	/**
	 * Indicates the backup name
	 */
	private String name;

	/**
	 * Indicates the backup description
	 */
	private String description;

	/**
	 * Indicates a list of self-built Microsoft SQL Server databases that are partially backed up
	 */
	private Databases databases;

	/**
	 * Indicates the backup start time
	 */
	@JsonProperty("begin_time")
	private String beginTime;

	/**
	 *
	 */
	@JsonProperty("end_time")
	private String endTime;

	/**
	 *
	 */
	private Long size;

	/**
	 * Indicates the backup status
	 */
	private String status;

	/**
	 * Indicates the backup type
	 */
	private String type;

	/**
	 *
	 */
	private DataStore dataStore;
}
