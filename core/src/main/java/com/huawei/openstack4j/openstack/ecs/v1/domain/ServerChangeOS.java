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

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A model represent server change OS
 *
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("os-change")
public class ServerChangeOS implements ModelEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1221913018097445570L;

	/**
	 * 云服务器管理员帐户的初始登录密码
	 */
	@JsonProperty("adminpass")
	private String adminPass;
	
	/**
	 * 密钥名称
	 */
	@JsonProperty("keyname")
	private String keyName;
	
	/**
	 * 用户ID
	 */
	@JsonProperty("userid")
	private String userId;
	
	/**
	 * 切换系统所使用的新镜像的ID，格式为UUID。
	 */
	@JsonProperty("imageid")
	private String imageId;
	
	/**
	 * 切换云服务器的元数据
	 */
	private Map<String, Object> metadata;
}		
