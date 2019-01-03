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

import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

/**
 * Created on 2018/10/30.
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProtectionPlan implements ModelEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7045221388732039671L;

	/**
     *备份计划id
     */
    private String id;

    /**
     *备份计划名称
     */
    private String name;

    /**
     *备份对象列表
     */
    private List<Resource> resources;

}
