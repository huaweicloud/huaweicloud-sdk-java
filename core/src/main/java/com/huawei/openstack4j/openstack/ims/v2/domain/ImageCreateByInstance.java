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
package com.huawei.openstack4j.openstack.ims.v2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * Created on 2018/8/29.
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonRootName("volume")
public class ImageCreateByInstance implements ModelEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4953322640558725194L;

	/**
     * 系统盘镜像名称
     */
    @JsonProperty("name")
    private String name;

    /**
     *镜像描述信息
     */
    @JsonProperty("description")
    private String description;

    /**
     *需要转换的云服务器ID
     */
    @JsonProperty("instance_id")
    private String instanceId;

    /**
     *需要转换的数据盘信息，其中，当使用云服务器上的数据盘进行私有数据盘镜像创建时，该字段必选
     */
    @JsonProperty("data_images")
    private List<DataImage> dataImages;

    /**
     *镜像标签列表
     */
    @JsonProperty("tags")
    private List<String> tags;
    
    /**
     *新规范的镜像标签列表。。默认为空。tags和image_tags只能使用一个
     */
    @JsonProperty("image_tags")
    private List<Map> imageTags;
    
    /**
     *表示当前镜像所属的企业项目
     */
    @JsonProperty("enterprise_project_id")
    private String enterpriseProjectId;
    
    /**
     *表示镜像支持的最大内存，单位为MB
     */
    @JsonProperty("max_ram")
    private Integer maxRam;
    
    /**
     *表示镜像支持的最小内存，单位为MB，默认为0，表示不受限制
     */
    @JsonProperty("min_ram")
    private Integer minRam;


}
