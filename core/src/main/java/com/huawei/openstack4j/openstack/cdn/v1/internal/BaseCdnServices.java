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
package com.huawei.openstack4j.openstack.cdn.v1.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.base.Function;
import com.google.common.io.CharStreams;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.HttpMethod;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.openstack.cdn.v1.domain.CdnError;
import com.huawei.openstack4j.openstack.cdn.v1.exception.ServerCdnErrorResponseException;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;
/**
 *  Base Service of Cdn Service
 * @author ChangjunZhao
 * @date   2018-05-15
 */
public class BaseCdnServices extends BaseOpenStackService{
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseCdnServices.class);

	public BaseCdnServices(){
		super(ServiceType.CDN);
	}
	
	/**
	 * HuaWei Cdn Service validate the content-type in every request
	 */
	@Override
	protected <R> Invocation<R> builder(Class<R> returnType, String path, HttpMethod method) {
		// add common base path for cdn service
		path = "/cdn" + path;
		// setup common headers for cdn service
		Invocation<R> invocation = super.builder(returnType, path, method);
		Config config = invocation.getRequest().getConfig();
		return invocation.header("Content-Type", CONTENT_JSON).header("X-Language", config.getLanguage());
	}
	
	/**
	 * deal with cdn response 200 status but error response.
	 * @param returnType
	 * @return {@link ExecutionOptions} instance
	 */
	protected <T> ExecutionOptions<T> buildExecutionOptions(final Class<T> returnType){
		Function<HttpResponse,T> f = new Function<HttpResponse, T>() {
			@Override
			public T apply(HttpResponse response) {
				InputStream inputStream = response.getInputStream();
				String json = "";
				try{
					json = CharStreams.toString(new InputStreamReader(inputStream, "UTF-8"));
					return (T) ObjectMapperSingleton.getContext(returnType).readValue(json, returnType);
				}catch(Exception e){
					CdnError error;
					try {
						error = ObjectMapperSingleton.getContext(CdnError.class).readValue(json, CdnError.class);
						throw new ServerCdnErrorResponseException(error.getErrorMsg(), error.getErrorCode());
					} catch (JsonParseException e1) {
						LOG.error(e1.getMessage());
					} catch (JsonMappingException e1) {
						LOG.error(e1.getMessage());
					} catch (IOException e1) {
						LOG.error(e1.getMessage());
					}
					LOG.error(e.getMessage());
				}finally{
					try {
						inputStream.close();
					} catch (IOException e) {
						LOG.error(e.getMessage());
					}
				}
				return null;
			}
		};
		return ExecutionOptions.create(f);
	}
}
