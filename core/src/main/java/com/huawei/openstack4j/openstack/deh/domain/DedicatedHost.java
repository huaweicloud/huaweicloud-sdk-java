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
package com.huawei.openstack4j.openstack.deh.domain;

import java.util.List;
import java.util.Map;

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
@JsonRootName("dedicated_host")
public class DedicatedHost implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6654999004967410367L;

	@JsonProperty("dedicated_host_id")
	private String dedicatedHostId;
	
	private String name;
	
	@JsonProperty("auto_placement")
	private AutoPlacement autoPlacement;
	
	@JsonProperty("availability_zone")
	private String availabilityZone;
	
	@JsonProperty("project_id")
	private String projectId;
	
	@JsonProperty("host_properties")
	private HostProperties hostProperties;
	
	private HostState  state;
	
	@JsonProperty("available_vcpus")
	private int availableVcpus;
	
	@JsonProperty("available_memory")
	private int	availableMemory;	

	@JsonProperty("allocated_at")
	private String allocatedAt;
	
	@JsonProperty("released_at")
	private String releasedAt;
	
	@JsonProperty("instance_total")
	private int	instanceTotal;
	
	@JsonProperty("instance_uuids")
	private List<String> instanceUuids;

	private Map<String, String> tags;

	@JsonProperty("sys_tags")
	private Map<String, String> sysTags;

	private Map<String, String> metadata;
	
	public  static class DedicatedHosts extends ListResult<DedicatedHost>{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -7607242585768409486L;
		
		@JsonProperty("dedicated_hosts")
		private List<DedicatedHost> dedicatedHosts;
		
		public List<DedicatedHost> getDedicatedHosts() {
			return dedicatedHosts;
		}
		
		public int getTotal() {
			return total;
		}
		
		private int total;
		@Override
		protected List<DedicatedHost> value() {
			return dedicatedHosts;
		}
		
		
	}
}
