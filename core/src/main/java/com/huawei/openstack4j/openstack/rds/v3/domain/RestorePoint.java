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

import java.util.HashMap;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestorePoint implements ModelEntity{

	private static final long serialVersionUID = 5767461389301820511L;

	/**
	 * Specifies the DB instance ID
	 */
	@JsonProperty("instance_id")
	private String instanceId;

	/**
	 * Specifies the restoration mode
	 */
	private String type;

	/**
	 * Specifies the ID of the backup used to restore data
	 */
	@JsonProperty("backup_id")
	private String backupId;

	/**
	 * Specifies the time point of data restoration
	 */
	@JsonProperty("restore_time")
	private Long restoreTime;

	/**
	 * This parameter applies only to the Microsoft SQL Server DB engine
	 */
	@JsonProperty("database_name")
	private HashMap<String, String> databaseName;

}
