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

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.GenericLink;
import com.huawei.openstack4j.openstack.common.ListResult;


@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flavor implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5852554276910921322L;
	
	/**
	 * 云服务器规格的ID。
	 */
	private String id;
	/**
	 * 云服务器规格的名称。
	 */
	private String name;
	/**
	 * 该云服务器规格对应的CPU核数
	 */
	private String vcpus;
	/**
	 * 该云服务器规格对应的内存大小，单位为MB
	 */
	private Integer ram;
	
	/**
	 * 该云服务器规格对应要求系统盘大小，0为不限制。此字段在本系统中无效
	 */
	private String disk;
	
	/**
	 * 保留字段
	 */
	private String swap;

	/**
	 * 保留字段
	 */
	@JsonProperty("OS-FLV-EXT-DATA:ephemeral")
	private Integer ephemeral;

	/**
	 * 保留字段
	 */
	@JsonProperty("OS-FLV-DISABLED:disabled")
	private Boolean disabled;

	/**
	 * 保留字段
	 */
	@JsonProperty("rxtx_factor")
	private Integer rxtxFactor;

	/**
	 * 保留字段
	 */
	@JsonProperty("rxtx_quota")
	private String rxtxQuota;

	/**
	 * 保留字段
	 */
	@JsonProperty("rxtx_cap")
	private String rxtxCap;
	
	@JsonProperty("os-flavor-access:is_public")
	private Boolean isPublic;
	/**
	 * 规格相关快捷链接地址
	 */
	@JsonProperty("links")
	private List<GenericLink> links;
	/**
	 * 云服务器规格的扩展字段
	 */
	@JsonProperty("os_extra_specs")
	private OsExtraSpecs osExtraSpecs;
	
	
	public  static class Flavors extends ListResult<Flavor>{
		/**
		 * 
		 */
		private static final long serialVersionUID = -38195172842707313L;
		@JsonProperty
		private List<Flavor> flavors;
		@Override
		protected List<Flavor> value() {
			return flavors;
		}
		
		
	}
}
