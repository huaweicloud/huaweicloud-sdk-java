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
package com.huawei.openstack4j.openstack.ecs.v1.domain;

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
import com.huawei.openstack4j.openstack.common.GenericLink;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.common.ListResult;
import com.huawei.openstack4j.openstack.compute.domain.NovaAddresses;
import com.huawei.openstack4j.openstack.compute.domain.NovaFault;
import com.huawei.openstack4j.openstack.compute.domain.NovaSecurityGroup;
import com.huawei.openstack4j.openstack.ecs.v1.contants.DiskConfig;
import com.huawei.openstack4j.openstack.ecs.v1.contants.Status;
import com.huawei.openstack4j.openstack.ecs.v1_1.domain.SchedulerHints;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("server")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CloudServer implements ModelEntity{

	public static final long serialVersionUID = 1L;
	public String id;
	public String name;
	public NovaAddresses addresses;
	public List<GenericLink> links;
	public Object image;
	public CloudFlavor flavor;
	public String accessIPv4;
	public String accessIPv6;
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
	@JsonProperty("OS-SRV-USG:launched_at")
	private Date launchedAt;
	@JsonProperty("OS-SRV-USG:terminated_at")
	private Date terminatedAt;
	@JsonProperty("os-extended-volumes:volumes_attached")
	private List<IdResourceEntity> osExtendedVolumesAttached;
	@JsonProperty("OS-EXT-SRV-ATTR:hostname")
	private String hostname;
	@JsonProperty("OS-EXT-SRV-ATTR:reservation_id")
	private String reservationId;
	@JsonProperty("OS-EXT-SRV-ATTR:launch_index")
	private String launchIndex;
	@JsonProperty("OS-EXT-SRV-ATTR:kernel_id")
	private String kernelId;
	@JsonProperty("OS-EXT-SRV-ATTR:ramdisk_id")
	private String ramdiskId;
	@JsonProperty("OS-EXT-SRV-ATTR:root_device_name")
	private String rootDeviceName;
	@JsonProperty("OS-EXT-SRV-ATTR:user_data")
	private String userdata;
//	private String uuid;
//	private String adminPass;
	@JsonProperty("locked")
	private Boolean locked;
	@JsonProperty("tags")
	private List<String> tags;
//	@JsonProperty("os:scheduler_hints")
//	private SchedulerHints schedulerHints;
	@JsonProperty("enterprise_project_id")
	private String enterpriseProjectId;
	@JsonProperty("sys_tags")
	private List<SysTags> sysTags;
	@Getter
	public static class CloudServers extends ListResult<CloudServer> {

		
		/**
		 * 
		 */
		private static final long serialVersionUID = 4969884416569652933L;
		
		@JsonProperty("servers")
		private List<CloudServer> servers;

		private Integer count;

		@Override
		protected List<CloudServer> value() {
			return servers;
		}
	}
}
