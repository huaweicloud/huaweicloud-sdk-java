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
package com.huawei.openstack4j.openstack.scaling.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ASAutoScalingResourceTag implements ModelEntity {

	private static final long serialVersionUID = -927945211246868042L;
	
	@JsonProperty("key")
	private String key;
	
	
	@JsonProperty("value")
	private String value;
	
	
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public  static class ASAutoScalingResourceTags extends ListResult<ASAutoScalingResourceTag> {
		
		private static final long serialVersionUID = 290354594722132303L;
		
		@JsonProperty("tags")
		private List<ASAutoScalingResourceTag> tags;
		
		@JsonProperty("action")
		private ASAutoScalingTagActionType action;
		
				
		@Override
		protected List<ASAutoScalingResourceTag> value() {			
			return tags;
		}
		
	}
}
