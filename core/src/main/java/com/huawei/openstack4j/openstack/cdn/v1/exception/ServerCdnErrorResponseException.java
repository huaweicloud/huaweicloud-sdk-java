/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.                                       
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
package com.huawei.openstack4j.openstack.cdn.v1.exception;

import com.google.common.base.MoreObjects;
import com.huawei.openstack4j.api.exceptions.ResponseException;
import com.huawei.openstack4j.api.exceptions.StatusCode;

/**
 * Captures Server 200 Errors
 *
 * @author ChangjunZhao
 */
public class ServerCdnErrorResponseException extends ResponseException {

	private static final long serialVersionUID = 1L;

	private StatusCode code;
	
	private String errorCode;

	public ServerCdnErrorResponseException(String message, String errorCode) {
		super(message, 200);
		this.errorCode = errorCode;
	}

	public ServerCdnErrorResponseException(String message, int status, Throwable cause) {
		super(message, status, cause);
		code = StatusCode.fromCode(status);
	}

	/**
	 * @return the status code mapping for the current {@link #getStatus()}
	 */
	public StatusCode getStatusCode() {
		return code;
	}
	
	

	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				     .add("message", getMessage()).add("errorCode", getErrorCode())
				     .toString();
	}

}
