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
import com.huawei.openstack4j.openstack.ims.v1.domain.*;


public class ImsImageDemoV1 {

	public static void main(String[] args) {

		 String authUrl = "https://iam.example.com/v3"; //endpoint Url
		 String user = "replace-with-your-username"; //用户名
		 String password = "replace-with-your-password"; //用户密码
		 String projectId = "replace-with-your-projectId"; //项目ID
		 String userDomainId = "replace-with-your-domainId"; //账号ID

	
		OSClientV3 os = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();

		//虚拟机创建整机镜像
		String instanceId = "your-instance-id";
		String imageName = "sdk-java-image-name";
		int maxRam = 1024;
		int minRam = 512;
		ImageCreateByInstance imageCreateByInstance = ImageCreateByInstance.builder().name(imageName).instanceId(instanceId).maxRam(maxRam).minRam(minRam).build();
		String rspByinstance = os.ims().images().create(imageCreateByInstance);
		if(null != rspByinstance){
			System.out.println("create whole image by instance success, jobId = " + rspByinstance);
		}else{
			System.out.println("create whole image by instance failed");
		}

		//整机备份创建整机镜像
		String backupId = "your-backup-id";
		imageName = "sdk-java-backup-create";
		maxRam = 1024;
		minRam = 512;
		ImageCreateByBackup imageCreateByBackup = ImageCreateByBackup.builder().backupId(backupId).name(imageName).maxRam(maxRam).minRam(minRam).build();
		String rspByBackup = os.ims().images().create(imageCreateByBackup);
		if(null != rspByBackup){
			System.out.println("create whole image by backup success, jobId = " + rspByBackup);
		}else{
			System.out.println("create whole image by instance  failed");
		}

		//外部文件创建数据盘镜像
		String imageUrl = "bucker-name:your-imagefile-name";
		int minDisk = 40;
		String osType = "Linux";
		ImageCreateByOBS imageCreateByOBS = ImageCreateByOBS.builder().name(imageName).imageUrl(imageUrl).minDisk(minDisk).osType(osType).build();
		String rspCreateDataImageByFile = os.ims().images().create(imageCreateByOBS);
		if(null != rspCreateDataImageByFile){
			System.out.println("create data image by file success, jobId = " + rspCreateDataImageByFile);
		}else{
			System.out.println("create data image by file failed");
		}

		//导出镜像
		String imageId = "your-image-id";
		String bucketUrl = "bucket-name:your-imagefile-name";
		String bucketFormat = "qcow2";
		ExportImage exportImage = ExportImage.builder().bucketUrl(bucketUrl).fileFormat(bucketFormat).build();
		String rspByExport = os.ims().images().export(exportImage, imageId);
		if(null != rspByExport){
			System.out.println("export image success, jobId = " + rspByExport);
		}else{
			System.out.println("export image failed");
		}

		//注册镜像，约束限制详情请见接口参考文档
		RegistImage registImage = RegistImage.builder().imageUrl(imageUrl).build();
		String rspRegistImage = os.ims().images().regist(registImage, imageId);
		if (null != rspRegistImage){
			System.out.println("regist image success, jobId = " + rspRegistImage);
		}else{
			System.out.println("regist image failed");
		}

	}

}
