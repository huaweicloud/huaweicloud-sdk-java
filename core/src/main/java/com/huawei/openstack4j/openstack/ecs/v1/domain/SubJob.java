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


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubJob implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 876557640130051346L;

	/**
	 *  Job的状态。
		SUCCESS：成功。
		RUNNING：运行中。
		FAIL：失败。
		INIT：正在初始化。
	 */
	@JsonProperty("status")
	private String status;
	
	/**
	 * Job操作的对象。
		根据不同Job类型，显示不同的内容，云服务
		器相关操作显示server_id，网卡相关操作显示
		nic_id。有子Job时为子job的详情。
	 */
	@JsonProperty("entities")
	private SubJobEntities entities;
	
	/**
	 * Job ID。
	 */
	@JsonProperty("job_id")
	private String jobId;
	
	/**
	 * Job的类型。
	 */
	@JsonProperty("job_type")
	private String jobType;
	
	/**
	 * 开始时间。
	 */
	@JsonProperty("begin_time")
	private String beginTime;
	/**
	 * 结束时间。
	 */
	@JsonProperty("end_time")
	private String endTime;
	/**
	 * Job执行失败时的错误码。
	 */
	@JsonProperty("error_code")
	private String errorCode;
	
	/**
	 * Job执行失败时的错误原因。
	 */
	@JsonProperty("fail_reason")
	private String failReason;
	
	/**
	 * 出现错误时，返回的错误消息。
	 */
	@JsonProperty("message")
	private String message;
	
	/**
	 * 出现错误时，返回的错误码。
	 */
	@JsonProperty("code")
	private String code;
		
}
