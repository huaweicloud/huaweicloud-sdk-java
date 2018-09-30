package com.huawei.openstack.sample.classic;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBCertificate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBCertificateUpdate;

public class ClassicCertificate {

	public static void main(String[] args) {

		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

		OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();

		// Create a SSL Certificate
		String key = "******";
		String cert = "******";

		ELBCertificate ssl_cer = ELBCertificate.builder().privateKey(key).certificate(cert).build();
		osclient.loadBalancer().certs().create(ssl_cer);

		// List SSL Certificates
		osclient.loadBalancer().certs().list();

		// Update a SSL Certificate
		String ssl_cert_id = "******";
		String cert_name_update = "my_cert_update";
		ELBCertificateUpdate cert_update = ELBCertificateUpdate.builder().name(cert_name_update).build();
		osclient.loadBalancer().certs().update(ssl_cert_id, cert_update);

		// Delete a SSL Certificate
		osclient.loadBalancer().certs().delete(ssl_cert_id);
	}

}