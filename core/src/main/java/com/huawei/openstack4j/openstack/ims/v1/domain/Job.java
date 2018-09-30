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
package com.huawei.openstack4j.openstack.ims.v1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

import java.util.Map;

/**
 * Created on 2018/8/30.
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job implements ModelEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8008233051567951575L;
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
     *任务ID
     */
    @JsonProperty("job_id")
    private String job_id;
    /**
     *任务类型
     */
    @JsonProperty("job_type")
    private String job_type;
    /**
     *任务开始执行时间
     */
    @JsonProperty("begin_time")
    private String begin_time;
    /**
     *任务结束时间
     */
    @JsonProperty("end_time")
    private String end_time;
    /**
     *错误码
     */
    @JsonProperty("error_code")
    private String error_code;
    /**
     *失败原因
     */
    @JsonProperty("fail_reason")
    private String fail_reason;
    /**
     *任务自定义属性
     */
    @JsonProperty("entities")
    private Map<String,String> entities;

}
