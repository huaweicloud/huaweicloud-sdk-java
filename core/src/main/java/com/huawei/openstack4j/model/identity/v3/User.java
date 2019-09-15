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
package com.huawei.openstack4j.model.identity.v3;

import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.identity.v3.builder.UserBuilder;

/**
 * identity v3 user model class
 *
 * @see <a href="http://developer.openstack.org/api-ref-identity-v3.html#users-v3">API reference</a>
 */
public interface User extends ModelEntity, Buildable<UserBuilder> {

    /**
     * Globally unique within the owning domain.
     *
     * @return the Id of the user
     */
    String getId();

    /**
     * @return the name of the user
     */
    String getName();

    /**
     * @return the email of the user
     */
    String getEmail();

    /**
     * @return the mobile of the user
     */
    String getMobile();

    /**
     * @return the description of the user
     */
    String getDescription();

    /**
     * @return the password of the user
     */
    String getPassword();

    /**
     * @return the password status of the user
     */
    Boolean getPwdStatus();

    /**
     * @return the password strength of the user
     */
    String getPwdStrength();

    /**
     * @return the forceResetPwd of the user
     */
    Boolean getForceResetPwd();

    /**
     * @return the defaultProjectId of the user
     */
    String getDefaultProjectId();

    /**
     * @return the last project id of the user
     */
    String getLastProjectId();

    /**
     * @return the password_expires_at of the user
     */
    String getPasswordExpiresAt();

    /**
     * @return the domainId of the user
     */
    String getDomainId();

    /**
     * @return the domain of the user
     */
    Domain getDomain();

    /**
     * @return the links of the user
     */
    Map<String, String> getLinks();
    /**
     * @return the extra of the user
     */
    Map<String, String> getExtra();

    /**
     * @return the enabled status of the user
     */
    boolean isEnabled();

    /**
     * sets the enabled status of the user
     *
     * @param enabled the enabled
     */
    void setEnabled(Boolean enabled);

}
