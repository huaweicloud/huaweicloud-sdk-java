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
package com.huawei.openstack4j.openstack.csbs.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.huawei.openstack4j.model.ModelEntity;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TriggerResp implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7379585601092788902L;

	/**
	 * 调度器属性
	 */
	private TriggerPropertiesResp properties;
	
	/**
	 * 调度器id
	 */
	private String id;
	
	/**
	 * 调度器名称
	 */
	private String name;
	
	/**
	 * 调度器类型
	 */
	private String type;
}
