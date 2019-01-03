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
package com.huawei.openstack4j.openstack.csbs.v1.internal;

 import com.google.common.base.Strings;
 import com.huawei.openstack4j.openstack.csbs.v1.domain.*;

 import static com.google.common.base.Preconditions.checkArgument;

 public class BackupResourceService extends BaseCloudServerBackupService {

     /**
      *创建资源备份
      * @param providerId
      * @param resourceId
      * @param protect
      * @return
      */
    public ProtectResp create(String providerId,String resourceId, Protect protect){
        checkArgument(!Strings.isNullOrEmpty(providerId),"parameter `providerId` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(resourceId),"parameter `resourceId` should not be empty");
        return post(ProtectResp.class,uri("/providers/%s/resources/%s/action", providerId,resourceId)).entity(protect).execute();
    }

     /**
      * 查询资源备份能力
      * @param providerId
      * @param checkProtectable
      * @return
      */
    public CheckProtectableResp getProtectable(String providerId, CheckProtectable checkProtectable){
        checkArgument(!Strings.isNullOrEmpty(providerId),"parameter `providerId` should not be empty");
        return post(CheckProtectableResp.class,uri("/providers/%s/resources/action", providerId)).entity(checkProtectable).execute();
    }

     /**
      *查询资源备份能力
      * @param providerId
      * @param checkRestorable
      * @return
      */
    public CheckRestorableResp getRestorable(String providerId, CheckRestorable checkRestorable){
        checkArgument(!Strings.isNullOrEmpty(providerId),"parameter `providerId` should not be empty");
        return post(CheckRestorableResp.class,uri("/providers/%s/resources/action", providerId)).entity(checkRestorable).execute();
    }
}
