/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

public class ImageTagDemo {
    public static void main(String[] args){
        String authUrl = "https://iam.example.com/v3"; //endpoint Url
        String user = "replace-with-your-username"; //用户名
        String password = "replace-with-your-password"; //用户密码
        String projectId = "replace-with-your-projectId"; //项目ID
        String userDomainId = "replace-with-your-domainId"; //账号ID

        OSClient.OSClientV3 os = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
                .authenticate();


        String imageId = "image_id";
        String tag = "image_tag";

        //新增或修改镜像标签
        ActionResponse addTagRsp = os.imagesV2().updateTag(imageId, tag);
        if (null != addTagRsp){
            System.out.println("add or update tag success");
        }else {
            System.out.println("add or update tag failed");
        }

        //删除镜像标签
        ActionResponse deleteTagRsp = os.imagesV2().deleteTag(imageId, tag);
        if (null != deleteTagRsp){
            System.out.println("delete tag success");
        }else {
            System.out.println("delete tag failed");
        }

    }
}
