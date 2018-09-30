/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.openstack.bms.v1.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;
import com.huawei.openstack4j.openstack.compute.domain.NovaAddresses;
import com.huawei.openstack4j.openstack.compute.domain.NovaFault;
import com.huawei.openstack4j.openstack.compute.domain.NovaSecurityGroup;
import com.huawei.openstack4j.openstack.ecs.v1.contants.DiskConfig;
import com.huawei.openstack4j.openstack.ecs.v1.contants.Status;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("server")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BareMetaServer implements ModelEntity{

	public static final long serialVersionUID = 1L;
	public String id;
	public String name;
	public NovaAddresses addresses;
	public List<GenericLink> links;
	public BareMetaImage image;
	public BareMetaFlavor flavor;
	@JsonProperty("config_drive")
	public String configDrive;
	public Status status;
	public Integer progress;
	public NovaFault fault;
	@JsonProperty("tenant_id")
	public String tenantId;
	@JsonProperty("user_id")
	public String userId;
	@JsonProperty("key_name")
	public String keyName;
	public String hostId;
	public String updated;
	public String created;
	@JsonProperty("description")
	private String description;
	@JsonProperty("host_status")
	private String hostStatus;
	private String accessIPv4;
	private String accessIPv6;
	public Map<String, String> metadata;
	@JsonProperty("security_groups")
	private List<NovaSecurityGroup> securityGroups;
	@JsonProperty("OS-EXT-STS:task_state")
	private String taskState;
	@JsonProperty("OS-EXT-STS:power_state")
	private String powerState;
	@JsonProperty("OS-EXT-STS:vm_state")
	private String vmState;
	@JsonProperty("OS-EXT-SRV-ATTR:host")
	private String host;
	@JsonProperty("OS-EXT-SRV-ATTR:instance_name")
	private String instanceName;
	@JsonProperty("OS-EXT-SRV-ATTR:hypervisor_hostname")
	private String hypervisorHostname;
	@JsonProperty("OS-DCF:diskConfig")
	private DiskConfig diskConfig;
	@JsonProperty("OS-EXT-AZ:availability_zone")
	private String availabilityZone;
	@JsonProperty("OS-EXT-SERVICE:service_state")
	private String serviceState;
	@JsonProperty("OS-SRV-USG:launched_at")
	private Date launchedAt;
	@JsonProperty("OS-SRV-USG:terminated_at")
	private Date terminatedAt;
	@JsonProperty("os-extended-volumes:volumes_attached")
	private List<IdResourceEntity> osExtendedVolumesAttached;
	@JsonProperty("tags")
	private List<String> tags;
	
	
	public static class BareMetaServers extends ListResult<BareMetaServer> {

		
		/**
		 * 
		 */
		private static final long serialVersionUID = 4969884416569652933L;
		
		@JsonProperty("servers")
		private List<BareMetaServer> servers;

		@Override
		protected List<BareMetaServer> value() {
			return servers;
		}
	}
}
