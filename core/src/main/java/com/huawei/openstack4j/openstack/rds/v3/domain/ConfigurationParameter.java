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
public class ConfigurationParameter implements ModelEntity{

	private static final long serialVersionUID = -2877318909322127776L;

	/**
	 * Indicates the parameter name
	 */
	private String name;

	/**
	 * Indicates the parameter value
	 */
	private String value;

	/**
	 * Indicates whether a reboot is required
	 */
	@JsonProperty("restart_required")
	private Boolean restartRequired;

	/**
	 * Indicates whether the parameter is read-only
	 */
	private Boolean readonly;

	/**
	 * Indicates the parameter value range
	 */
	@JsonProperty("value_range")
	private String valueRange;

	/**
	 * Indicates the parameter type, which can be integer, string, boolean, list, or float
	 */
	private String type;

	/**
	 * Indicates the parameter description
	 */
	private String description;
}
