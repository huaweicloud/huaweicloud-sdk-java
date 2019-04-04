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
import com.huawei.openstack4j.openstack.vpc.v1.contants.ShareType;

/**
 * Model represent attributes of bandwidth
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("bandwidth")
public class BandWidth implements ModelEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * bandwidth name
	 */
	private String name;
	
	/**
	 * bandwidth size
	 */
	private Integer size;

	/**
	 * 	Bandwidth sharing type
	 */
	@JsonProperty("share_type")
	private ShareType shareType;
	
	/**
	 * bandwidth share type
	 */
	@JsonProperty("publicip_info")
	private List<PublicIpInfo> publicipInfo;
	
	/**
	 * bandwidth share type, PER or WHOLE
	 */
	@JsonProperty("tenant_id")
	private String tenantId;
	
	/**
	 * bandwidth bandwidth type, bgp/union/double/telcom
	 */
	@JsonProperty("bandwidth_type")
	private String bandwidthType;
	
	/**
	 * bandwidth charge mode, bandwidth or traffic
	 */
	@JsonProperty("charge_mode")
	private String chargeMode;
	
	/**
	 * Bandwidth billing info
	 */
	@JsonProperty("billing_info")
	private String billingInfo;
	
	/**
	 * enterprise project id
	 */
	@JsonProperty("enterprise_project_id")
	private String enterpriseProjectId;
	
	public static class Bandwidths extends ListResult<BandWidth> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("bandwidths")
		private List<BandWidth> bandwidths;

		public List<BandWidth> value() {
			return bandwidths;
		}

	}

}
