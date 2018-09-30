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
package com.huawei.openstack4j.openstack.ims.v1.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.common.AsyncJobEntity;
import com.huawei.openstack4j.openstack.ims.v1.domain.ImageCreateByBackup;
import com.huawei.openstack4j.openstack.ims.v1.domain.ImageCreateByInstance;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created on 2018/8/29.
 */
public class ImageService extends BaseImageManagementService {


    /**
     *使用弹性云服务器制作整机镜像
     * @param imageCreateByInstance
     * @return
     */
    public String create(ImageCreateByInstance imageCreateByInstance){
        checkArgument(!Strings.isNullOrEmpty(imageCreateByInstance.getName()), "parameter `name` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(imageCreateByInstance.getInstanceId()), "parameter `instanceId` should not be empty");
        return post(AsyncJobEntity.class, "/cloudimages/wholeimages/action").entity(imageCreateByInstance).execute().getId();
    }

    /**
     *使用云服务器备份制作整机镜像
     * @param imageCreateByBackup
     * @return
     */
    public String create(ImageCreateByBackup imageCreateByBackup){
        checkArgument(!Strings.isNullOrEmpty(imageCreateByBackup.getName()), "parameter `name` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(imageCreateByBackup.getBackupId()), "parameter `backupId` should not be empty");
        return post(AsyncJobEntity.class, "/cloudimages/wholeimages/action").entity(imageCreateByBackup).execute().getId();
    }


}
