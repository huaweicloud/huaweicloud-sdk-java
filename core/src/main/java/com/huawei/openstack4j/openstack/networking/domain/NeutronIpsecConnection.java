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
package com.huawei.openstack4j.openstack.networking.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("ipsec_site_connection")
public class NeutronIpsecConnection implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8479461495757795664L;
	@JsonProperty
	private Dpd dpd;
	/*@JsonProperty
	private Integer timeout;
	@JsonProperty
	private String action;*/
	@JsonProperty("local_id")
	private String localId;
	@JsonProperty
	private String psk;
	@JsonProperty
	private String	initiator;
	@JsonProperty("ipsecpolicy_id")
	private String ipsecpolicyId;
	@JsonProperty("admin_state_up")
	private boolean adminStateUp;
	@JsonProperty
	private Integer mtu;
	@JsonProperty("peer_ep_group_id")
	private String peerEpGroupId;
	@JsonProperty("ikepolicy_id")
	private	String ikepolicyId;
	@JsonProperty("vpnservice_id")
	private String	vpnserviceId;
	@JsonProperty("local_ep_group_id")
	private String localEpGroupId;
	@JsonProperty("peer_address")
	private String peerAddress;
	@JsonProperty("peer_id")
	private String peerId;
	@JsonProperty
	private String name;
	@JsonProperty
	private String  description;	
	@JsonProperty("auth_mode")
	private String	authMode;
	@JsonProperty("peer_cidrs")
	private List<String> peerCidrs;
	@JsonProperty("tenant_id")
	private String tenantId;
	@JsonProperty("project_id")
	private String projectId;
	/*@JsonProperty
	private Integer interval;*/
	@JsonProperty
	private String id;
	@JsonProperty("route_mode")
	private String routeMode;
	@JsonProperty
	private String 	status;
	
	public  static class NeutronIpsecConnections extends ListResult<NeutronIpsecConnection>{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -1856746374735695561L;
		@JsonProperty("ipsec_site_connections")
		private List<NeutronIpsecConnection>	ipsecSiteConnections;
		
		@Override
		protected List<NeutronIpsecConnection> value() {
			return ipsecSiteConnections;
		}
		
		
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	public  static class Dpd{
		
		private String action;

		private Integer interval;
		
		private Integer	timeout;
		
		
		public String getAction() {
			return action;
		}

		public Integer getInterval() {
			return interval;
		}

	
		public Integer getTimeout() {
			return timeout;
		}

	

		
	}
}
