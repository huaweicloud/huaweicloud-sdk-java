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
public class BackupFile implements ModelEntity{

	private static final long serialVersionUID = -7822659030104433244L;

	/**
	 * Indicates the file name
	 */
	private String name;

	/**
	 * Indicates the file size in KB
	 */
	private Long size;

	/**
	 * Indicates the link for downloading the backup file
	 */
	@JsonProperty("download_link")
	private String downloadLink;

	/**
	 * Indicates the link expiration time
	 */
	@JsonProperty("link_expired_time")
	private String linkExpiredTime;
}
