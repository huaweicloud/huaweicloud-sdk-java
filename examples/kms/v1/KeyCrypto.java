package kms.v1;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.kms.domain.DEK;
import com.huawei.openstack4j.openstack.kms.domain.DecryptDEK;
import com.huawei.openstack4j.openstack.kms.domain.DecryptData;
import com.huawei.openstack4j.openstack.kms.domain.DecryptedDEK;
import com.huawei.openstack4j.openstack.kms.domain.DecryptedData;
import com.huawei.openstack4j.openstack.kms.domain.EncryptDEK;
import com.huawei.openstack4j.openstack.kms.domain.EncryptData;
import com.huawei.openstack4j.openstack.kms.domain.EncryptedDEK;
import com.huawei.openstack4j.openstack.kms.domain.EncryptedData;
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

		OSClientV3 osclient = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId))
				.scopeToProject(Identifier.byId(projectId)).authenticate();

		// create-key
		KeyCreate create = KeyCreate.builder().alias("SDK-test").description("desc").build();
		String keyId = osclient.keyManagement().keys().create(create).getId();

		// create-datakey
		DEK dek = osclient.keyManagement().crypto().createDEK(keyId, null, null);

		// encrypt-datakey
		EncryptDEK encrypt = EncryptDEK.builder().keyId(keyId).plainText(dek.getPlainText()).build();
		EncryptedDEK encryptedDEK = osclient.keyManagement().crypto().encryptDEK(encrypt);

		// decrypt-datakey
		DecryptDEK decrypt = DecryptDEK.builder().keyId(keyId).cipherText(encryptedDEK.getCipherText()).build();
		DecryptedDEK decryptDEK = osclient.keyManagement().crypto().decryptDEK(decrypt);
		System.out.println("data key is "+ decryptDEK.getDataKey());

		// encrypt-data
		EncryptData encryptData = EncryptData.builder().keyId(keyId).plainText("plain test data").build();
		EncryptedData encryptedData = osclient.keyManagement().crypto().encryptData(encryptData);

		// decrypt-data
		DecryptData decryptData = DecryptData.builder().cipherText(encryptedData.getCipherText()).build();
		DecryptedData decryptedData = osclient.keyManagement().crypto().decryptData(decryptData);
		System.out.println("plain data is "+decryptedData.getPlainText());

	}

}