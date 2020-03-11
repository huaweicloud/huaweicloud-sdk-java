/*******************************************************************************
 * 	Copyright 2020 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack4j.openstack.cdn.v1.domain;

import com.huawei.openstack4j.model.ModelEntity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@JsonRootName("domain")
public class DomainCreate implements ModelEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Domain name to be created
     */
    @JsonProperty("domain_name")
    private String domainName;

    /**
     * Business type of domain name
     */
    @JsonProperty("business_type")
    private String businessType;

    /**
     * Enterprise project ID
     */
    @JsonProperty("enterprise_project_id")
    private String enterpriseProjectId;

    /**
     * Origin of the domain name
     */
    private List<Source> sources;
}
