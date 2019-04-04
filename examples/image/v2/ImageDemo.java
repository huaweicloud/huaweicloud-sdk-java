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

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.common.Payload;
import com.huawei.openstack4j.model.common.Payloads;
import com.huawei.openstack4j.model.image.v2.ContainerFormat;
import com.huawei.openstack4j.model.image.v2.DiskFormat;
import com.huawei.openstack4j.model.image.v2.Image;
import com.huawei.openstack4j.model.image.v2.Image.ImageVisibility;
import com.huawei.openstack4j.openstack.OSFactory;

public class ImageDemo {

	public static void main(String[] args) {

		 String authUrl = "https://iam.example.com/v3"; //endpoint Url
		 String user = "replace-with-your-username"; //用户名
		 String password = "replace-with-your-password"; //用户密码
		 String projectId = "replace-with-your-projectId"; //项目ID
		 String userDomainId = "replace-with-your-domainId"; //账号ID

	
		OSClientV3 os = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();


		Map<String, String> filter = new HashMap<String, String>();

		filter.put("limit", "2");
		filter.put("visibility", "private");
		filter.put("owner", projectId);

		@SuppressWarnings("unchecked")
		List<Image> images = (List<Image>) os.imagesV2().list(filter);
		if (null != images){
			System.out.println("list image success, image = " + images.toString());
		}else{
			System.out.println("list image failed");
		}

		
		//创建镜像元数据
		Image image = Builders.imageV2().osDistro("ubuntu") 
				.name("image-name") 
				.containerFormat(ContainerFormat.BARE) 
				.visibility(ImageVisibility.PRIVATE) 
				.diskFormat(DiskFormat.RAW) 
				.architecture("x86_64") 
				.build();

		Image createResp = os.imagesV2().create(image);
		if (null != createResp){
			System.out.println("create image success, image = " + images.toString());
		}else{
			System.out.println("create image failed");
		}

		
		//上传镜像文件
		Payload payload = Payloads.create(new File("/opt/cirros.img"));
		ActionResponse uploadResp = os.imagesV2().upload(image.getId(), payload, image);
		if(null != uploadResp){
			System.out.println("upload image success");
		}else {
			System.out.println("upload image failed");
		}


		//查询镜像信息
		Image getResp = os.imagesV2().get(image.getId());
		if (null != getResp){
			System.out.println("get image success, image = " + images.toString());
		}else{
			System.out.println("get image failed");
		}

		//删除镜像
		ActionResponse deleteResp = os.imagesV2().delete(image.getId());
		if(null != deleteResp){
			System.out.println("delete image success");
		}else {
			System.out.println("delete image failed");
		}

	}

}
