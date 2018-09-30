package com.huawei.openstack.sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.kms.domain.DecryptDEK;
import com.huawei.openstack4j.openstack.kms.domain.DecryptData;
import com.huawei.openstack4j.openstack.kms.domain.DecryptedDEK;
import com.huawei.openstack4j.openstack.kms.domain.DecryptedData;
import com.huawei.openstack4j.openstack.kms.domain.EncryptDEK;
import com.huawei.openstack4j.openstack.kms.domain.EncryptData;
import com.huawei.openstack4j.openstack.kms.domain.Key;
import com.huawei.openstack4j.openstack.kms.domain.KeyCreate;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;


public class KeyCrypto {

	public static void main(String[] args) {

		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";
			
		OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
		endpointResolver.addOverrideEndpoint(ServiceType.KEY_MANAGEMENT,
					"https://kms.example.com/v1.0/%(project_id)s");
		Config config = Config.newConfig().withEndpointURLResolver(endpointResolver);

		OSClientV3 osclient = OSFactory.builderV3()..withConfig(config).endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId))
				.scopeToProject(Identifier.byId(projectId)).authenticate();

		// create-key
		KeyCreate create = KeyCreate.builder().alias("test").description("desc").build();
		String keyId = osclient.keyManagement().keys().create(create).getId();

		// create-datakey
		DEK dek = osclient.keyManagement().crypto().createDEK(keyId, null, null);

		// encrypt-datakey
		EncryptDEK encrypt = EncryptDEK.builder().keyId(keyId).plainText(dek.getPlainText()).build();
		EncryptedDEK encryptedDEK = osclient.keyManagement().crypto().encryptDEK(encrypt);

		// decrypt-datakey
		DecryptDEK decrypt = DecryptDEK.builder().keyId(keyId).cipherText(encryptedDEK.getCipherText()).build();
		DecryptedDEK decryptDEK = osclient.keyManagement().crypto().decryptDEK(decrypt);

		// encrypt-data
		EncryptData encrypt = EncryptData.builder().keyId(keyId).plainText(plainText).build();
		encryptedData = osclient.keyManagement().crypto().encryptData(encrypt);

		// decrypt-data
		DecryptData decrypt = DecryptData.builder().cipherText(encryptedData.getCipherText()).build();
		DecryptedData decryptData = osclient.keyManagement().crypto().decryptData(decrypt);

	}

}