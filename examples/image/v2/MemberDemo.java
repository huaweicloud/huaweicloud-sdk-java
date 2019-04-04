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

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.image.v2.Member;
import com.huawei.openstack4j.model.image.v2.Member.MemberStatus;
import com.huawei.openstack4j.openstack.OSFactory;

public class MemberDemo {

	public static void main(String[] args) {
		 String authUrl = "https://iam.example.com/v3"; //endpoint Url
		 String user = "replace-with-your-username"; //用户名
		 String password = "replace-with-your-password"; //用户密码
		 String projectId = "replace-with-your-projectId"; //项目ID
		 String userDomainId = "replace-with-your-domainId"; //账号ID

		OSClientV3 os = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();
		
	
		String imageId = "image ID";
		String memberId = "member ID";
		
		//添加成员
		Member createResp = os.imagesV2().createMember(imageId, memberId);
		if (null != createResp){
			System.out.println("create member success , member = " + createResp);
		}else {
			System.out.println("create member failed");
		}

		//查询成员
		Member getResp = os.imagesV2().getMember(imageId, memberId);
		if (null != getResp){
			System.out.println("get member success , member = " + getResp);
		}else {
			System.out.println("get member failed");
		}
		
		//删除成员
		ActionResponse deleteResp = os.imagesV2().deleteMember(imageId, memberId);
		if (null != deleteResp){
			System.out.println("delete member success");
		}else {
			System.out.println("delete member failed");
		}

		//更新成员状态
		Member updateResp = os.imagesV2().updateMember(imageId, memberId, MemberStatus.ACCEPTED);
		if (null != updateResp){
			System.out.println("update member success ,member =" + updateResp);
		}else {
			System.out.println("update member failed");
		}
	}

}
