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
package com.huawei.openstack4j.openstack.ecs.v1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("server")
public class Metadata {

	/**
	 * Windows弹性云服务器Administrator用户的密码。
	 */
	@JsonProperty("admin_pass")
	String adminPass;

	@java.beans.ConstructorProperties({ "adminPass" })
	public Metadata(String adminPass) {
		this.adminPass = adminPass;
	}

	public Metadata() {
	}

	public static MetadataBuilder builder() {
		return new MetadataBuilder();
	}

	public String getAdminPass() {
		return this.adminPass;
	}

	@Override
	public String toString() {
		return "Metadata(adminPass=" + this.getAdminPass() + ")";
	}

	public MetadataBuilder toBuilder() {
		return new MetadataBuilder().adminPass(this.adminPass);
	}

	public static class MetadataBuilder {
		private String adminPass;

		MetadataBuilder() {
		}

		public Metadata.MetadataBuilder adminPass(String adminPass) {
			this.adminPass = adminPass;
			return this;
		}

		public Metadata build() {
			return new Metadata(adminPass);
		}

		@Override
		public String toString() {
			return "Metadata.MetadataBuilder(adminPass=" + this.adminPass + ")";
		}
	}
}
