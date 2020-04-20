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
import com.huawei.openstack4j.openstack.common.ListResult;
import lombok.*;
import java.util.List;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Configuration implements ModelEntity{

	/**
	 * Indicates the parameter template ID
	 */
	private String id;

	/**
	 * Indicates the parameter template name
	 */
	private String name;

	/**
	 * Indicates the parameter template description
	 */
	private String description;

	/**
	* Indicates the database version name
	*/
	@JsonProperty("datastore_version_name")
	private String datastoreVersionName;

	/**
	 * Indicates the database name
	 */
	@JsonProperty("datastore_name")
	private String datastoreName;

	/**
	 * Indicates the creation time
	 */
	private String created;

	/**
	 * Indicates the updated time
	 */
	private String updated;

	/**
	 * Specifies whether the parameter template is created by users
	 */
	@JsonProperty("user_defined")
	private Boolean userDefined;

	public static class Configurations extends ListResult<Configuration> {

		private static final long serialVersionUID = -7607242585768409486L;

		@JsonProperty("configurations")
		private List<Configuration> configurations;

		@Override
		protected List<Configuration> value() {
			return configurations;
		}


	}
}
