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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

/**
 * Model represent attributes of Quotas resource
 *
 * @author ChangjunZhao
 * @date   2018-10-01
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("profile")
public class Profile implements ModelEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3293456765255971631L;

	/**
	 * order id
	 */
	@JsonProperty("order_id")
	private String orderId;

	/**
	 * product id
	 */
	@JsonProperty("product_id")
	private String productId;
	
	/**
	 * region id
	 */
	@JsonProperty("region_id")
	private String regionId;

	/**
	 * user id
	 */
	@JsonProperty("user_id")
	private String userId;

}
