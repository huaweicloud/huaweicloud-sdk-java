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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Model represent attributes of security group
 *
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("security_group")
public class SecurityGroup implements ModelEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * security group name
	 */
	private String name;
	
	private String description;
	
	@JsonProperty("vpc_id")
	private String vpcId;
	
	/**
	 * security group rules
	 */
	@JsonProperty("security_group_rules")
	private List<SecurityGroupRule> securityGroupRules;
	
	/**
	 * enterprise project id
	 */
	@JsonProperty("enterprise_project_id")
	private String enterpriseProjectId;
	
	public static class SecurityGroups extends ListResult<SecurityGroup> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("security_groups")
		private List<SecurityGroup> securityGroups;

		public List<SecurityGroup> value() {
			return securityGroups;
		}

	}

}
