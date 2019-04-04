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
package com.huawei.openstack4j.openstack.evs.v2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

 /**
  *
  *  查询Job的执行状态。
     对于创建云硬盘、删除云硬盘、扩容云硬盘等异步API，命令下
     发后，会返回job_id，通过job_id可以查询任务的执行状态。
  * @author
  *
  */
 @Getter
 @ToString
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
 public class Job implements ModelEntity{

     private static final long serialVersionUID = 5134163296376741355L;

     /**
      *  Job的状态。
         SUCCESS：成功。
         RUNNING：运行中。
         FAIL：失败。
         INIT：正在初始化。
      */
     private String status;

     /**
      * Job操作的对象。
      根据不同Job类型，显示不同的内容，云硬盘相关操作显示volume_id。
      */
     private JobEntities entities;

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

 }
