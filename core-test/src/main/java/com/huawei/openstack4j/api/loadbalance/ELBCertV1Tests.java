/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package com.huawei.openstack4j.api.loadbalance;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.loadbalance.Certificate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBCertificate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBCertificateUpdate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBCertificate.Certificates;

@Test(suiteName = "ELBLoadBalancer/CertV1")
public class ELBCertV1Tests extends AbstractTest {

	private static final String JSON_CERT_CREATE = "/loadbalance/elb_cert_create.json";
	private static final String JSON_CERTS = "/loadbalance/elb_certs.json";
	private static final String JSON_CERT_UPDATE = "/loadbalance/elb_cert_update.json";
	private static final String JSON_CERT_LIST= "/loadbalance/elb_certs_list.json";

	public void createCertTest() throws IOException {
		respondWith(JSON_CERT_CREATE);
		Certificate cert = ELBCertificate.builder().name("SDK-test-cert").description("desc")
				.certificate("xxxxxx")
				.privateKey("xxxxxx")
				.build();
		Certificate create = osv3().loadBalancer().certs().create(cert);
		assertTrue("60884e005ce546b1b580bf259cd37016".equals(create.getId()));
	}

	public void deleteCertTest() {
		respondWith(204);
		String certificateId = "60884e005ce546b1b580bf259cd37016";
		ActionResponse resp = osv3().loadBalancer().certs().delete(certificateId);
		assertTrue(resp.isSuccess(), resp.getFault());
	}

	public void updateCertTest() throws IOException {
		respondWith(JSON_CERTS);
		respondWith(JSON_CERT_UPDATE);
		Certificates certs = osv3().loadBalancer().certs().list();
		assertTrue(certs != null && Integer.valueOf(certs.getInstanceNum()) == 1, "No cert for update");
		if(certs == null) return;
		Certificate cert = certs.getCertificates().get(0);
		String after = new StringBuilder(cert.getDescription()).reverse().toString();
		ELBCertificateUpdate update = ELBCertificateUpdate.fromCertificate(cert).toBuilder().description(after).build();
		Certificate afterUpdate = osv3().loadBalancer().certs().update(cert.getId(), update);
		assertTrue(after.equals(afterUpdate.getDescription()));
	}
	
	public void listCertTest() throws IOException {
		respondWith(JSON_CERT_LIST);
		Certificates cer = osv3().loadBalancer().certs().list();
		assertEquals(cer.getCertificates().size(), 1);
		assertEquals(cer.getInstanceNum(), "1");
		assertEquals(cer.getCertificates().get(0).getId(), "5b8f908b5495452aa13beede0afc5d99");
		assertEquals(cer.getCertificates().get(0).getName(), "cert-bky");
		assertEquals(cer.getCertificates().get(0).getDescription(), "certificate");
		assertEquals(cer.getCertificates().get(0).getDomain(), "xxxxxx");
		assertEquals(cer.getCertificates().get(0).getCertificate(), "xxxxxx");
		assertEquals(cer.getCertificates().get(0).getPrivateKey(), "xxxxxx");
		
	}
	
	@Override
	protected Service service() {
		return Service.LOAD_BALANCER;
	}
}
