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
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("bandwidth")
public class VirtualBandWidths implements ModelEntity {

	
	/**
	 * 
	* {
		    "bandwidth": {
		        "id": "3fa5b383-5a73-4dcb-a314-c6128546d855",
		        "name": "bandwidth123",
		        "size": 10,
		        "share_type": "PER",
		        "publicip_info": [
		            {
		                "publicip_id": "6285e7be-fd9f-497c-bc2d-dd0bdea6efe0",
		                "publicip_address": "161.17.101.9",
		                "publicip_type": "5_bgp"
		            }
		        ],
		        "tenant_id": "8b7e35ad379141fc9df3e178bd64f55c",
		        "bandwidth_type": "bgp",
		        "charge_mode": "bandwidth",
		        "billing_info": "CS1712121146TSQOJ:0616e2a5dc9f4985ba52ea8c0c7e273c:southchina:35f2b308f5d64441a6fa7999fbcd4321"
		    }
		}
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 1、功能说明：带宽名称
		2、取值范围：1-64，支持数字、字母、中文、_(下划线)、-（中划线）
		3、约束：
		如果share_type是PER，该字段是必选。
		如果bandwidth对象的id有值，该字段被忽略。
	 */
	
	/**
	 * 使用已有的共享带宽创建IP
	 */
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	/**
	 * 带宽（Mbit/s），取值范围为[1,300]。
	 */
	@JsonProperty("size")
	private Integer size;

	/**
	 * 	带宽的共享类型。 共享类型枚举：PER，表示独享。 目前只支持独享。
	 */
	@JsonProperty("share_type")
	private String shareType;

	/**
	 * 带宽的计费类型。
		未传该字段，表示按带宽计费。
		字段值为空，表示按带宽计费。
		字段值为“traffic”，表示按流量计费。
		字段为其它值，会导致创建云服务器失败。
	 */
	@JsonProperty("charge_mode")
	private String chargeMode;
	
	/**
	 * 用户所属租户ID
	 */
	@JsonProperty("tenant_id")
	private String tenantId;
	
	/**
	 * 按流量计费还是按带宽计费
	 */
	@JsonProperty("bandwidth_type")
	private String bandwidthType;
	
	/**
	 * 带宽对应的弹性公网IP信息
	 */
	@JsonProperty("publicip_info")
	private List<VirtualPublicIpInfo> virtualPublicIpResp;
	
	/**
	 * 账单信息
	 */
	@JsonProperty("billing_info")
	private String billingInfo;
	
	/**
	 * 企业项目ID
	 */
	@JsonProperty("enterprise_project_id")
	private String enterpriseProjectId;
	
	
	
	
	
	public static class VirtualBandWidthResps extends ListResult<VirtualBandWidths> {
	
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 4969884416569652933L;
			
			@JsonProperty("bandwidths")
			private List<VirtualBandWidths> bandwidths;
	
			@Override
			protected List<VirtualBandWidths> value() {
				return bandwidths;
			}
		}
}