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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostModel implements ModelEntity {
	private static final long serialVersionUID = -1178225869131660199L;

	@JsonProperty("id")
    private String serverId;

    @JsonProperty("name")
    private String hostName;

    @JsonProperty("ip")
    private String internalIp;

    @JsonProperty("status")
    private String hostStatus;

    @JsonProperty("flavor")
    private String nodeSize;

    @JsonProperty("type")
    private String nodeType;

    @JsonProperty("mem")
    private String mem;

    @JsonProperty("cpu")
    private String cpu;

    @JsonProperty("root_volume_size")
    private String disk;

    @JsonProperty("data_volume_type")
    private String dataVolumeType;

    @JsonProperty("data_volume_size")
    private int dataVolumeSize;

    @JsonProperty("data_volume_count")
    private int dataVolumeCount;

}
