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
package com.huawei.openstack4j.openstack.vpc.v1.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * Model represent attributes of Vpc Create Request
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@JsonRootName("vpc")
public class VpcCreate implements ModelEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * vpc name
	 */
	private String name;
	
	/**
	 * vpc subnet cidr
	 */
	private String cidr;
	
	/**
	 * enterprise project id
	 */
	@JsonProperty("enterprise_project_id")
	private String enterpriseProjectId;
	
	
}
