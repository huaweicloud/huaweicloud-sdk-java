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
package com.huawei.openstack4j.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.api.types.Facing;
import com.huawei.openstack4j.api.types.ServiceType;

import lombok.Getter;
import lombok.Setter;

/**
 * Provides Endpoint information for the current authorized scope
 * 
 * @author Jeremy Unruh
 */
public interface ServiceEndpointProvider {

	/**
	 * Gets the endpoint for the specified ServiceType
	 *
	 * @param service the service to obtain the endpoint for
	 * @return the endpoint
	 */
	String getEndpoint(ServiceType service);

	/**
	 * @param service
	 * @param perspective
	 * @return
	 */
	String getEndpoint(ServiceType service, Facing perspective);

	@Getter
	@Setter
	public static class ServiceEndpoint {

		@JsonProperty("public")
		String publicEndpoint;

		@JsonProperty("admin")
		String adminEndpoint;

		@JsonProperty("internal")
		String internalEndpoint;

		public String getEndpointFor(Facing perspective) {
			if (perspective == null) {
				return publicEndpoint;
			}

			switch (perspective) {
			case PUBLIC:
				return publicEndpoint;
			case ADMIN:
				return adminEndpoint;
			case INTERNAL:
				return internalEndpoint;
			default:
				return publicEndpoint;
			}

		}

	}

}
