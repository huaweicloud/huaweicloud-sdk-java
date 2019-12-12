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
package com.huawei.openstack4j.openstack.ecs.v1.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterfaceAttachment implements ModelEntity{

	public static final long serialVersionUID = 1L;

	@JsonProperty("port_state")
	public String portState;

	@JsonProperty("net_id")
	public String networkId;

	@JsonProperty("port_id")
	public String portId;

	@JsonProperty("mac_addr")
	public String macAddr;

	@JsonProperty("fixed_ips")
	public List<FixedIp> fixedIps;

	@Getter
	public static class InterfaceAttachments extends ListResult<InterfaceAttachment> {

		private static final long serialVersionUID = 1L;
		
		@JsonProperty("interfaceAttachments")
		private List<InterfaceAttachment> interfaces;

		@Override
		protected List<InterfaceAttachment> value() {
			return interfaces;
		}
	}
}
