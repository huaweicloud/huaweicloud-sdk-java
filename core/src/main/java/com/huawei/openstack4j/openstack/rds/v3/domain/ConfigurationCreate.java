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

import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;
import java.util.HashMap;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationCreate implements ModelEntity {

	private static final long serialVersionUID = -1926253102537545346L;

	/**
	 * Specifies the parameter template name
	 */
	private String name;

	/**
	 * Describes the parameter template
	 */
	private String description;

	/**
	 * Specifies the parameter values defined by users
	 */
	private HashMap<String, String> values;

	/**
	 * Specifies the database object
	 */
	private DataStore datastore;
}
