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
package com.huawei.openstack4j.api.network;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.networking.domain.IKEPolicy;
import com.huawei.openstack4j.openstack.networking.domain.IKEPolicy.IkeVersion;
import com.huawei.openstack4j.openstack.networking.domain.IKEPolicy.LifeTime;
import com.huawei.openstack4j.openstack.networking.domain.IKEPolicy.Pfs;

@Test
public class IkePolicyTest extends AbstractTest {

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

	private static final String IKEPOLICY_CREATE_JSON = "/network/ikepolicy_create.json";
	private static final String IKEPOLICY_UPDATE_JSON = "/network/ikepolicy_update.json";
	private static final String IKEPOLICY_LIST_JSON = "/network/ikepolicy_list.json";
	private static final String IKEPOLICY_DETAIL_JSON = "/network/ikepolicy_detail.json";

	public void testCreate() throws IOException {
		respondWith(IKEPOLICY_CREATE_JSON);
		final String id = "5522aff7-1b3c-48dd-9c3c-b50f016b73db";
		final String phase1NegotiationMode = "main";
		final String authAlgorithm = "sha1";
		final String encryptionAlgorithm = "aes-128";
		final String units = "seconds";
		final Integer value = 7200;
		final String name = "ikepolicy1";

		IKEPolicy ikeModel = IKEPolicy.builder()
				.phase1NegotiationMode(phase1NegotiationMode)
				.authAlgorithm(authAlgorithm)
				.encryptionAlgorithm(encryptionAlgorithm).pfs(Pfs.group5)
				.lifetime(new LifeTime(units, value)).ikeVersion(IkeVersion.V1)
				.name(name).build();
		IKEPolicy res = osv3().networking().ikePolicies().create(ikeModel);
		assertNotNull(id.equals(res.getId()));

	}

	public void testUpdate() throws IOException {
		respondWith(IKEPOLICY_UPDATE_JSON);

		final String id = "5522aff7-1b3c-48dd-9c3c-b50f016b73db";
		IKEPolicy ikeUpdate = IKEPolicy.builder()
				.encryptionAlgorithm("aes-256").build();
		IKEPolicy res = osv3().networking().ikePolicies().update(id, ikeUpdate);
		assertNotNull(id.equals(res.getId()));

	}

	public void testList() throws IOException {
		respondWith(IKEPOLICY_LIST_JSON);
		Integer size = 1;
		List<IKEPolicy> res = osv3().networking().ikePolicies().list(null);
		assertNotNull(res.size() == size);

	}

	public void testGet() throws IOException {
		respondWith(IKEPOLICY_DETAIL_JSON);
		final String id = "6ecd9cf3-ca64-46c7-863f-f2eb1b9e838a";
		IKEPolicy res = osv3().networking().ikePolicies().get(id);
		assertNotNull(res);

	}

	public void testDelete() throws IOException {
		respondWith(200);
		final String id = "66e3b16c-8ce5-40fb-bb49-ab6d8dc3f2aa";
		ActionResponse res = osv3().networking().ikePolicies().delete(id);
		assertNotNull(200 == res.getCode());

	}
}
