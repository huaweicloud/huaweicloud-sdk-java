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

import java.util.List;

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
public class CheckRestorableResp implements ModelEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8812879616609111849L;
	/**
     * 查询参数列表
     */
    private List<RestorableResp> restorable;
}
