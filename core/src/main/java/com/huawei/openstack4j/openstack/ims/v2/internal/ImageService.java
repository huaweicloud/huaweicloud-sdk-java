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
package com.huawei.openstack4j.openstack.ims.v2.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.common.AsyncJobEntity;
import com.huawei.openstack4j.openstack.ims.v2.domain.DataImage;
import com.huawei.openstack4j.openstack.ims.v2.domain.ImageCreateByExternalImage;
import com.huawei.openstack4j.openstack.ims.v2.domain.ImageCreateByInstance;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created on 2018/8/29.
 */
public class ImageService extends BaseImageManagementService{

    /**
     *使用云服务器制作镜像
     * @param imageCreateByInstance
     * @return
     */
    public String create(ImageCreateByInstance imageCreateByInstance){
        if(null !=imageCreateByInstance.getDataImages() && imageCreateByInstance.getDataImages().size()>0 ){
            checkArgument(Strings.isNullOrEmpty(imageCreateByInstance.getInstanceId()), "parameter `instanceId` should be empty");
            for(DataImage data : imageCreateByInstance.getDataImages()){
                checkArgument(!Strings.isNullOrEmpty(data.getName()), "parameter `name` should not be empty");
                checkArgument(!Strings.isNullOrEmpty(data.getVolumeId()), "parameter `volumeId` should not be empty");
            }
        }else{
            checkArgument(!Strings.isNullOrEmpty(imageCreateByInstance.getName()), "parameter `name` should not be empty");
            checkArgument(!Strings.isNullOrEmpty(imageCreateByInstance.getInstanceId()), "parameter `instanceId` should not be empty");
        }
        return post(AsyncJobEntity.class, "/cloudimages/action").entity(imageCreateByInstance).execute().getId();
    }

    /**
     *使用上传至OBS桶中的外部镜像文件制作镜像
     * @param imageCreateByExternalImage
     * @return
     */
    public String create(ImageCreateByExternalImage imageCreateByExternalImage){
        checkArgument(!Strings.isNullOrEmpty(imageCreateByExternalImage.getName()), "parameter `name` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(imageCreateByExternalImage.getImageUrl()), "parameter `imageUrl` should not be empty");
        checkArgument(!(null==(imageCreateByExternalImage.getMinDisk())), "parameter `minDisk` should not be empty");
        return post(AsyncJobEntity.class, "/cloudimages/action").entity(imageCreateByExternalImage).execute().getId();
    }


}
