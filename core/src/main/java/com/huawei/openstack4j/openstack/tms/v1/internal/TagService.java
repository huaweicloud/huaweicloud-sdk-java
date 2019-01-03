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
package com.huawei.openstack4j.openstack.tms.v1.internal;

import static com.google.common.base.Preconditions.checkArgument;
import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.tms.v1.contants.Action;
import com.huawei.openstack4j.openstack.tms.v1.domain.PredefineTagRequest;
import com.huawei.openstack4j.openstack.tms.v1.domain.PredefineTags;
import com.huawei.openstack4j.openstack.tms.v1.domain.PredefineTagsAction;

public class TagService extends BaseTagManagementService {

	/**
	 * 用于创建预定义标签。用户创建预定义标签后，可以使用预定义标签来给资源创建标签。该接口支持幂等特性和处理批量数据。
	 * @param tagsAtion
	 * @return
	 */
	public String create(List<PredefineTagRequest> tagList) {
		checkArgument(tagList.size()>0, "parameter `predefine_tag_request` should not be empty");
		PredefineTagsAction tagsAtion = PredefineTagsAction.builder().action(Action.Create).tagList(tagList).build();
		return post(String.class,uri("/predefine_tags/action")).entity(tagsAtion).execute();
	}
	
	/**
	 * 用于删除预定义标签。该接口支持幂等特性和处理批量数据。
	 * @param tagsAtion
	 * @return
	 */
	public String delete(List<PredefineTagRequest> tagList) {
		checkArgument(tagList.size()>0, "parameter `predefine_tag_request` should not be empty");
		PredefineTagsAction tagsAtion = PredefineTagsAction.builder().action(Action.Delete).tagList(tagList).build();
		return post(String.class, uri("/predefine_tags/action")).entity(tagsAtion).execute();
	}

	/**
	 * 
	 * @return
	 */
	public PredefineTags list() {
		return get(PredefineTags.class, uri(PATH_PREDEFINE_TAG)).execute();
	}

	/**
	 * 
	 * @param option
	 * @return
	 */
	public PredefineTags list(TagFilterOption option) {
		return get(PredefineTags.class, uri(PATH_PREDEFINE_TAG)).params(option.getOptions()).execute();
	}

	/**
	 * 
	 * @param oldTag
	 * @param newTag
	 * @return
	 */
	public ActionResponse modify(PredefineTagRequest oldTag, PredefineTagRequest newTag) {
		checkArgument(!Strings.isNullOrEmpty(oldTag.getKey()), "parameter `oldTag.key` should not be empty");
//		checkArgument(!Strings.isNullOrEmpty(oldTag.getValue()), "parameter `oldTag.value` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(newTag.getKey()), "parameter `newTag.key` should not be empty");
//		checkArgument(!Strings.isNullOrEmpty(newTag.getValue()), "parameter `newTag.value` should not be empty");
		Map<String, Object> entity = new HashMap<>();
		entity.put("new_tag", newTag);
		entity.put("old_tag", oldTag);
		return putWithResponse(PATH_PREDEFINE_TAG).entity(entity).execute();
	}

	
}
