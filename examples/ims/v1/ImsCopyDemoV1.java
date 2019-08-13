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
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.ims.v1.domain.CopyImageCrossRegion;
import com.huawei.openstack4j.openstack.ims.v1.domain.CopyImageInRegion;

public class ImsCopyDemoV1 {

    public static void main(String[] args) {
        String authUrl = "https://iam.example.com/v3"; //endpoint Url
        String user = "replace-with-your-username"; //用户名
        String password = "replace-with-your-password"; //用户密码
        String projectId = "replace-with-your-projectId"; //项目ID
        String userDomainId = "replace-with-your-domainId"; //账号ID


        OSClient.OSClientV3 os = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
                .authenticate();

        String imageName = "sdk-java-image-name";
        String imageId = "your-image-id";

        //镜像区域内复制
        CopyImageInRegion copyImageInRegion = CopyImageInRegion.builder().name(imageName).build();
        String rspCopy = os.ims().images().copyInRegion(copyImageInRegion, imageId);
        if (null != rspCopy){
            System.out.println("copy image in region success, jobId = " + rspCopy);
        }else{
            System.out.println("copy image in region failed");
        }

        //镜像跨区域复制
        String region = "your_region_id";
        String agencyName = "your_agency_name";
        String projectName = "project_name";
        CopyImageCrossRegion copyImageCrossRegion = CopyImageCrossRegion.builder().name(imageName).region(region).agencyName(agencyName).projectName(projectName).build();
        String rspCrcCopy = os.ims().images().copyCrossRegion(copyImageCrossRegion, imageId);
        if (null != rspCrcCopy){
            System.out.println("copy image cross region success, jobId = " + rspCrcCopy);
        }else{
            System.out.println("copy image cross region failed");
        }
    }
}
