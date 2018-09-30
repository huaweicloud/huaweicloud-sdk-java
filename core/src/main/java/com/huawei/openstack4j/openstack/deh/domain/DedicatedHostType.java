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
package com.huawei.openstack4j.openstack.deh.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;
import lombok.*;

import java.util.List;

/**
 * Created on 2018/8/8.
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DedicatedHostType implements ModelEntity {

    @JsonProperty("dedicated_host_types")
    private String dedicatedHostTypes;
    @JsonProperty("host_type")
    private String hostType;

    public static class DedicatedHostTypes extends ListResult<DedicatedHostType> {

        @JsonProperty("dedicated_host_types")
        private List<DedicatedHostType> dedicatedHostType;

        @Override
        protected List<DedicatedHostType> value() {
            return dedicatedHostType;
        }
    }
}
