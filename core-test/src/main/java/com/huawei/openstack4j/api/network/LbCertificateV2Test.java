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
    	//String key = "-----BEGIN CERTIFICATE-----MIIDIjCCAougAwIBAgIJALV96mEtVF4EMA0GCSqGSIb3DQEBBQUAMGoxCzAJBgNVBAYTAnh4MQswCQYDVQQIEwJ4eDELMAkGA1UEBxMCeHgxCzAJBgNVBAoTAnh4MQswCQYDVQQLEwJ4eDELMAkGA1UEAxMCeHgxGjAYBgkqhkiG9w0BCQEWC3h4eEAxNjMuY29tMB4XDTE3MTExMzAyMjYxM1oXDTIwMTExMjAyMjYxM1owajELMAkGA1UEBhMCeHgxCzAJBgNVBAgTAnh4MQswCQYDVQQHEwJ4eDELMAkGA1UEChMCeHgxCzAJBgNVBAsTAnh4MQswCQYDVQQDEwJ4eDEaMBgGCSqGSIb3DQEJARYLeHh4QDE2My5jb20wgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAMU832iM+d3FILgTWmpZBUoYcIWVcAAYE7FsZ9LNerOyjJpyi256oypdBvGs9JAUBN5WaFk81UQx29wAyNixX+bKa0DBWpUDqr84V1f9vdQc75v9WoujcnlKszzpV6qePPC7igJJpu4QOI362BrWzJCYQbg4Uzo1KYBhLFxl0TovAgMBAAGjgc8wgcwwHQYDVR0OBBYEFMbTvDyvE2KsRy9zPq/JWOjovG+WMIGcBgNVHSMEgZQwgZGAFMbTvDyvE2KsRy9zPq/JWOjovG+WoW6kbDBqMQswCQYDVQQGEwJ4eDELMAkGA1UECBMCeHgxCzAJBgNVBAcTAnh4MQswCQYDVQQKEwJ4eDELMAkGA1UECxMCeHgxCzAJBgNVBAMTAnh4MRowGAYJKoZIhvcNAQkBFgt4eHhAMTYzLmNvbYIJALV96mEtVF4EMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEFBQADgYEAASkC/1iwiALa2RU3YCxqZFEEsZZvQxikrDkDbFeoa6Tk49Fnb1f7FCW6PTtY3HPWl5ygsMsSy0Fi3xp3jmuIwzJhcQ3tcK5gC99HWp6Kw37RL8WoB8GWFU0Q4tHLOjBIxkZROPRhH+zMIrqUexv6fsb3NWKhnlfh1Mj5wQE4Ldo=-----END CERTIFICATE-----";
    	//String certificate = "-----BEGIN RSA PRIVATE KEY-----MIICXQIBAAKBgQDFPN9ojPndxSC4E1pqWQVKGHCFlXAAGBOxbGfSzXqzsoyacotueqMqXQbxrPSQFATeVmhZPNVEMdvcAMjYsV/mymtAwVqVA6q/OFdX/b3UHO+b/VqLo3J5SrM86Veqnjzwu4oCSabuEDiN+tga1syQmEG4OFM6NSmAYSxcZdE6LwIDAQABAoGBAJvLzJCyIsCJcKHWL6onbSUtDtyFwPViD1QrVAtQYabF14g8CGUZG/9fgheuTXPtTDcvu7cZdUArvgYW3I9F9IBb2lmF3a44xfiAKdDhzr4DK/vQhvHPuuTeZA41r2zp8Cu+Bp40pSxmoAOK3B0/peZAka01Ju7c7ZChDWrxleHZAkEA/6dcaWHotfGSeW5YLbSms3f0m0GH38nRl7oxyCW6yMIDkFHURVMBKW1OhrcuGo8u0nTMi5IH9gRg5bH8XcujlQJBAMWBQgzCHyoSeryD3TFieXIFzgDBw6Ve5hyMjUtjvgdVKoxRPvpOkclc39QHP6Dm2wrXXHEej+9RILxBZCVQNbMCQQC42i+Ut0nHvPuXN/UkXzomDHdeh1ySsOAO4H+8Y6OSI87l3HUrByCQ7stX1z3L0HofjHqV9Koy9emGTFLZEzSdAkB7Ei6cUKKmztkYe3rr+RcATEmwAw3tEJOHmrW5ErApVZKr2TzLMQZ7WZpIPzQRCYnY2ZZLDuZWFFG3vW+wKKktAkAaQ5GNzbwkRLpXF1FZFuNF7erxypzstbUmU/31b7tSi5LmxTGKL/xRYtZEHjya4Ikkkgt40q1MrUsgIYbFYMf2-----END RSA PRIVATE KEY-----"; 
    	
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
