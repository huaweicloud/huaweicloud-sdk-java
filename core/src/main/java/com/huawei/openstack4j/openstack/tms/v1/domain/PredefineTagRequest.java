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

import com.fasterxml.jackson.annotation.JsonProperty;

public class PredefineTagRequest {
	/**
	 * 	键。最大长度36个字符。 字符集：A-Z, a-z , 0-9, ‘-‘, ‘_’, UNICODE字符（\u4E00-\u9FFF）。
	 */
	@JsonProperty("key")
	private String key;
	/**
	 *  值。每个值最大长度43个字符，可以为空字符串。 字符集：A-Z，a-z ， 0-9，‘.’，‘-’，‘_’，UNICODE字符（\u4E00-\u9FFF）
	 */
	@JsonProperty("value")
	private String value;
	
	
	@java.beans.ConstructorProperties({ "key", "value" })
	public PredefineTagRequest(String key, String value) {
		this.key = key;
		this.value = value;
	}
	public PredefineTagRequest() {
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static  PredefineTagRequestBuilder builder(){
		return new PredefineTagRequestBuilder();
	}
	
	public PredefineTagRequestBuilder toBuilder(){
		return new PredefineTagRequestBuilder().key(this.key).value(this.value);
	}
	
	@Override
	public String toString() {
		return "PredefineTagRequest [key=" + key + ", value=" + value + "]";
	}

	public static class PredefineTagRequestBuilder{
		private String key;
		private String value;
		
		public PredefineTagRequest.PredefineTagRequestBuilder key(String key){
			this.key = key;
			return this;
		}
		
		public PredefineTagRequest.PredefineTagRequestBuilder value(String value){
			this.value = value;
			return this;
		}

		public PredefineTagRequestBuilder() {
		}
		
		public PredefineTagRequest build(){
			return new PredefineTagRequest(key,value);
		}

		@Override
		public String toString() {
			return "PredefineTagRequestBuilder [key=" + key + ", value="
					+ value + "]";
		}
		
	}
	
}
