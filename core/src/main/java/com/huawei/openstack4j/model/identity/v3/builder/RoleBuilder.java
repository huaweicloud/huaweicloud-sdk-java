/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.model.identity.v3.builder;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.identity.v3.Role;

/**
 * A Builder which creates an identity v3 role
 *
 *
 */
public interface RoleBuilder extends Builder<RoleBuilder, Role> {

    /**
     * @see Role#getId()
     */
    RoleBuilder id(String id);

    /**
     * @see Role#getName()
     */
    RoleBuilder name(String name);

    /**
     * @see Role#getLinks()
     */
    RoleBuilder links(Map<String, String> links);

    /**
     * @see Role#getDisplayName()
     */
    RoleBuilder displayName(String displayName);

    /**
     * @see Role#getType()
     */
    RoleBuilder type(String type);

    /**
     * @see Role#getCatalog()
     */
    RoleBuilder catalog(String catalog);

    /**
     * @see Role#getFlag()
     */
    RoleBuilder flag(String flag);

    /**
     * @see Role#getDescription()
     */
    RoleBuilder description(String description);

    /**
     * @see Role#getUpdatedTime()
     */
    RoleBuilder updatedTime(String updatedTime);

    /**
     * @see Role#getCreatedTime()
     */
    RoleBuilder createdTime(String createdTime);

    /**
     * @see Role#getDescriptionCn()
     */
    RoleBuilder descriptionCn(String descriptionCn);

    /**
     * @see Role#getPolicy()
     */
    RoleBuilder policy(Map<String, Object> policy);

    /**
     * @see Role#getLinks()
     */
	RoleBuilder domainId(String domainId);

}
