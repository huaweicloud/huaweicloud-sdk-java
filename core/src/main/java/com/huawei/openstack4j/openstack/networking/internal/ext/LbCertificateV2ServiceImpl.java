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
package com.huawei.openstack4j.openstack.networking.internal.ext;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.networking.ext.LbCertificateV2Service;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronCertificate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronCertificate.NeutronCertificates;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronCertificateUpdate;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

public class LbCertificateV2ServiceImpl extends BaseNetworkingServices implements LbCertificateV2Service{
	
	private static final String API_PATH = "/lbaas/certificates";
	
	@Override
	public NeutronCertificate get(String id) {
		checkArgument(!Strings.isNullOrEmpty(id),"parameter `certificateId` should not be empty");			
		return get(NeutronCertificate.class, uri(API_PATH+"/%s",id)).execute();
	}

	@Override
	public NeutronCertificates list() {
		return get(NeutronCertificates.class, uri(API_PATH)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends NeutronCertificate> list(Map<String, String> filteringParams) {
		Invocation<NeutronCertificates> serverInvocation = get(NeutronCertificates.class, uri(API_PATH));
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return serverInvocation.execute().getList();
	}

	@Override
	public NeutronCertificate create(NeutronCertificate model) {
		checkArgument((null != model),"parameter `certificateModel` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(model.getPrivateKey()),"parameter `privateKey` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(model.getCertificate()),"parameter `certificate` should not be empty");
		return post(NeutronCertificate.class, uri(API_PATH)).entity(model).execute();
	}

	@Override
	public NeutronCertificate update(NeutronCertificateUpdate model, String id) {
		checkArgument((null != model),"parameter `updateModel` should not be null");
		checkArgument(!Strings.isNullOrEmpty(id),"parameter `id` should not be empty");
		return put(NeutronCertificate.class, uri(API_PATH+"/%s",id)).entity(model).execute();
	}

	@Override
	public ActionResponse delete(String id) {
		checkArgument(!Strings.isNullOrEmpty(id),"parameter `certificateId` should not be empty");
		return ToActionResponseFunction.INSTANCE
				.apply(delete(Void.class, uri(API_PATH+"/%s",id)).executeWithResponse());	
	}

}
