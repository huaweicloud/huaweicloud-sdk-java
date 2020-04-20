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
import java.util.List;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyParaResponse implements ModelEntity{

	private static final long serialVersionUID = 3852207997958593217L;

	/**
	 * Specifies the parameter template ID
	 */
	@JsonProperty("configuration_id")
	private String configurationId;

	/**
	 * Specifies the parameter template name
	 */
	@JsonProperty("configuration_name")
	private String configutationName;

	/**
	* Specifies the result of applying the parameter template
	*/
	@JsonProperty("apply_results")
	private List<ApplyResult> applyResults;

	/**
	 * Each parameter template is applied to DB instances successfully
	 */
	private Boolean success;
}
