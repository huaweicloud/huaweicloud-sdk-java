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
package com.huawei.openstack4j.openstack.map.reduce.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * Model represent attributes of JobExeCreation
 *
 * @author QianBiao.NG
 * @date 2017-07-05 16:07:41
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MapReduceJobExeCreate implements ModelEntity {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String innerId;

	@JsonProperty("cluster_id")
	private String clusterId;

	@JsonProperty("state")
	private String state;

	@JsonProperty("create_at")
	private int createAt;

	@JsonProperty("create_by")
	private String createBy;

	@JsonProperty("job_type")
	private int jobType; // 作业类型

	@JsonProperty("job_name")
	private String jobName; // 作业名称

	@JsonProperty("jar_path")
	private String jarPath; // jar包路径

	@JsonProperty("arguments")
	private String arguments; // 传入参数

	@JsonProperty("input")
	private String input; // 输入路径

	@JsonProperty("output")
	private String output; // 输出路径

	@JsonProperty("job_log")
	private String jobLog; // 日志路径

	@JsonProperty("hive_script_path")
	private String hiveScriptPath;

	@JsonProperty("hql")
	private String hql;

	@JsonProperty("token")
	private String token;

	@JsonProperty("token_domain_id")
	private String tokenDomainId;

	@JsonProperty("token_user_id")
	private String tokenUserId;

	@JsonProperty("token_project_id")
	private String tokenProjectId;

	@JsonProperty("shutdown_cluster")
	private boolean shutdownCluster;

	@JsonProperty("submit_job_once_cluster_run")
	private boolean submitJobOnceClusterRun;

	@JsonProperty("file_action")
	private String fileAction;
	
    @JsonProperty("is_public")
    private Boolean isPublic;

    @JsonProperty("is_protected")
    private Boolean isProtected;

}
