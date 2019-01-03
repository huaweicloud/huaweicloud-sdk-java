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
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronCertificate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronCertificate.NeutronCertificates;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronCertificateUpdate;

@Test
public class LbCertificateV2Test extends AbstractTest  {


	private static final String CERTIFICATE_CREATE_JSON = "/network/certificate_create.json";
    private static final String CERTIFICATE_UPDATE_JSON = "/network/certificate_update.json";
    private static final String	CERTIFICATE_LIST_JSON = "/network/certificate_list.json";
    private static final String CERTIFICATE_DETAIL_JSON = "/network/certificate_detail.json";
    String id = "23ef9aad4ecb463580476d324a6c71af";
    
    public void testCreateSSL() throws IOException{
    	respondWith(CERTIFICATE_CREATE_JSON);

    	NeutronCertificate cer = osv3().networking().lbaasV2().lbCertificate().get(id);
    	String key = cer.getPrivateKey();
    	String certificate = cer.getCertificate();
    	respondWith(CERTIFICATE_CREATE_JSON);
    	NeutronCertificate model = NeutronCertificate.builder().privateKey(key).type("server").certificate(certificate).build();
    	NeutronCertificate res = osv3().networking().lbaasV2().lbCertificate().create(model);
    	assertNotNull(res);
    	
    }
    
	public void testUpdateSSL() throws IOException{
		respondWith(CERTIFICATE_UPDATE_JSON);
		String name = "test_certificate";		
		NeutronCertificateUpdate model = NeutronCertificateUpdate.builder().name(name).build();
	     NeutronCertificate res = osv3().networking().lbaasV2().lbCertificate().update(model, id);
	    assertNotNull(res);
    	
	}
	public void testDetailSSL() throws IOException{
		respondWith(CERTIFICATE_DETAIL_JSON);		
		NeutronCertificate res = osv3().networking().lbaasV2().lbCertificate().get(id);
		assertTrue(id.equals(res.getId()));
    }
    
	public void testListSSL() throws IOException{
		respondWith(CERTIFICATE_LIST_JSON);
		NeutronCertificates res = osv3().networking().lbaasV2().lbCertificate().list();
		assertTrue(res.getList().size() == 1);
	    	
	}
	public void testDeleteSSL() throws IOException{
		int code = 200;
		respondWith(code);		
		 ActionResponse res = osv3().networking().lbaasV2().lbCertificate().delete(id);
		 assertTrue(res.getCode() == code);
	}
	
	
	
	@Override
	protected Service service() {
		return Service.NETWORK;
	}
}
