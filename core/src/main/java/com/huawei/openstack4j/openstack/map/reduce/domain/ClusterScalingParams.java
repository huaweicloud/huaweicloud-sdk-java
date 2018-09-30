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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClusterScalingParams implements ModelEntity {

 
	private static final long serialVersionUID = -6195817066255758469L;

	/**
     * 扩容时api-gw获取的新订单号
     */
    @JsonProperty("order_id")
    private String orderId;

    /**
     * scale_in （缩容） or scale_out（扩容）
     */
    @JsonProperty("scale_type")
    private String scaleType;

    /**
     * node_core
     */
    @JsonProperty("node_id")
    private String nodeId;

    @JsonProperty("node_group")
    private String nodeGroup;

    @JsonProperty("skip_bootstrap_scripts")
    private String skipBootstrapScripts;

    @JsonProperty("scale_without_start")
    private Boolean scaleWithoutStart;

    /**
     * 扩容或缩容节点数
     */
    @JsonProperty("instances")
    private int instances;

    private String instancesStr;

    @JsonProperty("include_instances")
    private String[] includeInstances;

    /**
     * 如果是scale_in，这个列表里的节点是要保留的；如果是scale_out留空
     */
    @JsonProperty("exclude_instances")
    private String[] excludeInstances;

    @JsonProperty("task_node_info")
    private TaskNodeGroup taskNodeInfo;
}
