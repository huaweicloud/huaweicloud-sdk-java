package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronCertificate;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronCertificateUpdate;
/**
 *
 * @author QianBiao.NG
 * @date   2017-10-04 21:41:29
 */
public class Certificate {

	public static void main(String[] args) {
        
		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

		OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
			.credentials(user, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();
			
		String key =  "******";		
		String cert = "******";

//Create a SSL Certificate
		NeutronCertificate certificate = NeutronCertificate.builder().name("cert_test").description("cert for java-sdk").type("server").domain("www.elb.com").privateKey(key).certificate(cert).build();
		osclient.networking().lbaasV2().lbCertificate().create(certificate);

//List SSL　Certificates
		osclient.networking().lbaasV2().lbCertificate().list();
		
//Query a SSL Certificate
		String cert_id = "******";
		osclient.networking().lbaasV2().lbCertificate().get(cert_id);
		
//Update a SSL Certificate
		NeutronCertificateUpdate cert_mod = NeutronCertificateUpdate.builder().name("cert_update").domain("elb.com").build();
		osclient.networking().lbaasV2().lbCertificate().update(cert_mod, cert_id);
	}

}