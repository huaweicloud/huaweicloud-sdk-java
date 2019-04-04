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

import java.util.ArrayList;
import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.ims.v2.domain.ImageCreateByExternalImage;
import com.huawei.openstack4j.openstack.ims.v2.domain.ImageCreateByInstance;
import com.huawei.openstack4j.openstack.ims.v2.domain.ImageUpdate;

public class ImsImageDemo {

	public static void main(String[] args) {

		 String authUrl = "https://iam.example.com/v3"; //endpoint Url
		 String user = "replace-with-your-username"; //用户名
		 String password = "replace-with-your-password"; //用户密码
		 String projectId = "replace-with-your-projectId"; //项目ID
		 String userDomainId = "replace-with-your-domainId"; //账号ID

	
		OSClientV3 os = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();


		//更新镜像信息
		ImageUpdate item = new ImageUpdate("add", "/name", "go-sdk-test-java");
		List<ImageUpdate> items = new ArrayList<ImageUpdate>();
		items.add(item);
		String imageId = "XXXXXX";
		com.huawei.openstack4j.openstack.ims.v2.domain.Image updateResp = os.imsV2().images().update(items, imageId);
		if (null != updateResp){
			System.out.println("update image success, updateImage = " + updateResp.toString());
		}else{
			System.out.println("update image failed");
		}

		
		//虚拟机创建镜像
		String instanceId = "9862d90e-42cc-4419-ad1e-be1b0e64f923";
		String imageName = "sdk-java-test-name";
		String description = "sdk-test";
		ImageCreateByInstance create = ImageCreateByInstance.builder().instanceId(instanceId).name(imageName).description(description).build();
		String rspByInstance = os.imsV2().images().create(create);
		if(null != rspByInstance){
			System.out.println("create image by instance success, image = " + rspByInstance);
		}else{
			System.out.println("create image by instance failed");
		}


        //文件创建镜像
		imageName = "sdk-java-file-create";
		String imageUrl = "your-bucker-name:your-imagefile-name";
		String osVersion = "CentOS 7.2 64bit";
		int minDisk = 40;
		boolean isConfig = true;
		String cmkId = null;
		ImageCreateByExternalImage imageCreateByFile = ImageCreateByExternalImage.builder().name(imageName).imageUrl(imageUrl).osVersion(osVersion).minDisk(minDisk)
				.isConfig(isConfig).cmkId(cmkId).build();
		String rspByFile = os.imsV2().images().create(imageCreateByFile);
		if(null != rspByFile){
			System.out.println("create image by external image success, image = " + rspByFile);
		}else{
			System.out.println("create image by external image failed");
		}

	}

}
