/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.                                       
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
package com.huawei.openstack4j.openstack.cdn.v1.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("task")
public class Task implements ModelEntity{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	@JsonProperty("task_type")
	private String taskType;
	
	private String status;
	
	private Integer processing;
	
	private Integer succeed;
	
	private Integer failed;
	
	private Integer total;
	
	@JsonProperty("create_time")
	private Long createTime;
	
	private List<String> urls;
	
	public static class Tasks extends ListResult<Task> {

		private static final long serialVersionUID = 1L;
		
		private Integer total;
		
		@JsonProperty("tasks")
		List<Task> tasks;
		
		public Integer getTotal() {
			return total;
		}

		@Override
		protected List<Task> value() {
			return tasks;
		}

	}
}
