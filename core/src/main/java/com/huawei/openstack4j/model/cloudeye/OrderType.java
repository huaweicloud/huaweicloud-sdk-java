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
package com.huawei.openstack4j.model.cloudeye;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.dns.v2.ZoneType;

/**
 * Created by coa.ke on 6/24/17.
 */
public enum OrderType {
    DESC, ASC;

    @JsonValue
    public String value() {
        return name().toLowerCase();
    }

    //default to DESC
    @JsonCreator
    public static OrderType value(String v)
    {
        if (v == null) return DESC;
        try {
            return valueOf(v.toUpperCase());
        } catch (IllegalArgumentException e) {
            return DESC;
        }
    }

}
