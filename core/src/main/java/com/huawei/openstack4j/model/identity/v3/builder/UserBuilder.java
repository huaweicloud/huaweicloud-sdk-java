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
import com.huawei.openstack4j.model.identity.v3.Domain;
import com.huawei.openstack4j.model.identity.v3.User;

/**
 * A Builder which creates a identity v3 project
 * 
 * 
 * 
 */
public interface UserBuilder extends Builder<UserBuilder, User> {

    /**
     * @see User#getId()
     */
    UserBuilder id(String id);

    /**
     * @see User#getName()
     */
    UserBuilder name(String name);

    /**
     * @see User#getDefaultProjectId()
     */
    UserBuilder defaultProjectId(String defaultProjectId);

    /**
     * @see User#getLastProjectId()
     */
    UserBuilder lastProjectId(String lastProjectId);

    /**
     * @see User#getDescription()
     */
    UserBuilder description(String description);

    /**
     * @see User#getDomainId()
     */
    UserBuilder domainId(String domainId);

    /**
     * Accepts an existing domain and uses its id
     * 
     * @see User#getDomain()
     */
    UserBuilder domain(Domain domain);

    /**
     * @see User#getEmail()
     */
    UserBuilder email(String email);

    /**
     * @see User#getMobile()
     */
    UserBuilder mobile(String mobile);

    /**
     * @see User#getLinks()
     */
    UserBuilder links(Map<String, String> links);

    /**
     * @see User#getExtra()
     */
    UserBuilder extra(Map<String, String> extra);

    /**
     * @see User#getPassword()
     */
    UserBuilder password(String password);

    /**
     * @see User#getPwdStatus()
     */
    UserBuilder pwdStatus(Boolean pwdStatus);

    /**
     * @see User#getPwdStrength()
     */
    UserBuilder pwdStrength(String pwdStrength);

    /**
     * @see User#getPasswordExpiresAt()
     */
    UserBuilder passwordExpiresAt(String passwordExpiresAt);

    /**
     * @see User#getForceResetPwd()
     */
    UserBuilder forceResetPwd(Boolean forceResetPwd);

    /**
     * @see User#isEnabled()
     */
    UserBuilder enabled(boolean enabled);

}
