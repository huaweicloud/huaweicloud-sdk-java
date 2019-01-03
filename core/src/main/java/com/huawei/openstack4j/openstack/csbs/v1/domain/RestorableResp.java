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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


/**
 * Created on 2018/10/30.
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestorableResp implements ModelEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7651585511973405860L;

	/**
     * 是否可备份或恢复
     */
    private Boolean result;

    /**
     * 资源类型
     */
    @JsonProperty("resource_type")
    private String resourceType;

    /**
     * 错误码
     */
    @JsonProperty("error_code")
    private String errorCode;

    /**
     * 错误原因
     */
    @JsonProperty("error_msg")
    private String errorMsg;

    /**
     * 资源id
     */
    @JsonProperty("resource_id")
    private String resourceId;

}
