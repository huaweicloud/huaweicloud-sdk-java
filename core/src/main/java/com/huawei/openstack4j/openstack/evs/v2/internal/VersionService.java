 /*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.openstack.evs.v2.internal;

import static com.google.common.base.Preconditions.checkArgument;
import java.util.List;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.openstack.common.functions.RemoveVersionProjectIdFromURL;
import com.huawei.openstack4j.openstack.storage.block.domain.Version;

public class VersionService extends BaseElasticVolumeService{

    public VersionService() {
     super(ServiceType.EVS, RemoveVersionProjectIdFromURL.INSTANCE);
    }

//    /**
//     *  Get Version List
//     * @return List<? extends Version>
//     */
//     public List<? extends Version> versions() {
//         return get(Version.Versions.class, "/").execute().getList();
//     }
//
//    /**
//     *  Get Single Version
//     *  @param version version
//     * @return List<? extends Version>
//     */
//     public List<? extends Version> version(String version) {
//         checkArgument(!Strings.isNullOrEmpty(version), "`version` should not be empty");
//         return get(Version.Versions.class, "/" + version).execute().getList();
//    }
}
