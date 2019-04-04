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
package com.huawei.openstack4j.openstack.vpc.v1.internal;

import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.core.transport.HttpMethod;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.common.functions.RemoveProjectIdFromURL;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;
import com.huawei.openstack4j.openstack.vpc.v1.domain.Port;
import com.huawei.openstack4j.openstack.vpc.v1.domain.Port.Ports;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PortCreate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.PortUpdate;

/**
 * The implementation of manipulation of Port
 * 
 * @author ChangjunZhao
 * @date   2018-03-25
 */
public class PortService extends BaseOpenStackService {
	
	public static String CONTENT_JSON = "application/json;charset=utf-8";
	
	public PortService(){
		super(ServiceType.VPC,RemoveProjectIdFromURL.INSTANCE);
	}

	/**
	 * Query port list
	 * @return
	 */
	public List<? extends Port> list(){
		return list(null);
	}
	
	/**
	 * Query port list with filter
	 * @param filteringParams
	 * @return
	 */
	public List<? extends Port> list(Map<String, String> filteringParams) {
		Invocation<Ports> flavorInvocation = get(Ports.class, uri("/ports"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	flavorInvocation = flavorInvocation.param(entry.getKey(), entry.getValue());
            }
        }
		
		return flavorInvocation.execute().getList();
	}
	
	/**
	 * Creating a port
	 * @param port
	 * @return
	 */
	public Port create(PortCreate port){
		Preconditions.checkNotNull(port, "parameter `port` should not be null");
		Preconditions.checkNotNull(port.getNetworkId(), "parameter `port.networkId` should not be empty");
		return post(Port.class, uri("/ports")).entity(port).execute();
	}
	
	/**
	 * Query port details
	 * @param portId
	 * @return
	 */
	public Port get(String portId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(portId), "parameter `portId` should not be empty");
		return get(Port.class, uri("/ports/%s",portId)).execute();
	}
	
	/**
	 * Update port
	 * @param portId
	 * @return
	 */
	public Port update(String portId, PortUpdate portUpdate){
		Preconditions.checkNotNull(portUpdate, "parameter `portUpdate` should not be null");
		Preconditions.checkNotNull(portId, "parameter `portId` should not be empty");
		return put(Port.class, uri("/ports/%s",portId)).entity(portUpdate).execute();
	}
	
	/**
	 * Delete port
	 * @param portId
	 * @return
	 */
	public ActionResponse delete(String portId){
		Preconditions.checkArgument(!Strings.isNullOrEmpty(portId), "parameter `portId` should not be empty");
		return deleteWithResponse(uri("/ports/%s",portId)).execute();
	}

	@Override
	protected <R> Invocation<R> builder(Class<R> returnType, String path, HttpMethod method) {
		//path = "/v1" + path;
		// setup common headers for port service
		Invocation<R> invocation = super.builder(returnType, path, method);
		Config config = invocation.getRequest().getConfig();
		return invocation.header("Content-Type", CONTENT_JSON).header("X-Language", config.getLanguage());
	}
	
}
