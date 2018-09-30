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
package com.huawei.openstack4j.openstack.tms.v1.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

public class PredefineTags implements ModelEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 	标签列表。
	 */
	@JsonProperty("tags")
	private List<PredefineTag> tags;
	/**
	 * 总记录数。
	 */
	@JsonProperty("total_count")
	private String totalCount;
	/**
	 * 分页位置标识。当前查询最后一条数据的索引位置。
	 */
	private String marker;
	
	@java.beans.ConstructorProperties({ "tags", "totalCount", "marker" })
	public PredefineTags(List<PredefineTag> tags, String totalCount,
			String marker) {
		this.tags = tags;
		this.totalCount = totalCount;
		this.marker = marker;
	}

	public PredefineTags() {
	}

	public List<PredefineTag> getTags() {
		return tags;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public String getMarker() {
		return marker;
	}

	@Override
	public String toString() {
		return "PredefineTags [tags=" + tags + ", totalCount=" + totalCount
				+ ", marker=" + marker + "]";
	}

	public static PredefineTagsBuilder builder(){
		return new PredefineTagsBuilder();
	}

	public PredefineTagsBuilder toBuilder(){
		return new PredefineTagsBuilder().tags(this.tags).totalCount(this.totalCount).marker(this.marker);
	}
	
	public static class PredefineTagsBuilder{
		
		private List<PredefineTag> tags;
		private String totalCount;
		private String marker;
		
		public PredefineTagsBuilder() {
		}
		
		public PredefineTags.PredefineTagsBuilder tags(List<PredefineTag> tags){
			this.tags = tags;
			return this;
		}
		
		public PredefineTags.PredefineTagsBuilder totalCount(String totalCount){
			this.totalCount = totalCount;
			return this;
		}
		
		public PredefineTags.PredefineTagsBuilder marker(String marker){
			this.marker = marker;
			return this;
		}
		
		public PredefineTags build(){
			return new PredefineTags(tags, totalCount,marker);
		}

		@Override
		public String toString() {
			return "PredefineTagsBuilder [tags=" + tags + ", totalCount="
					+ totalCount + ", marker=" + marker + "]";
		}
		
		
	}
	
	
	

} 
