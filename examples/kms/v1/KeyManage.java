package kms.v1;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;
import com.huawei.openstack4j.openstack.kms.domain.Key;
import com.huawei.openstack4j.openstack.kms.domain.KeyCreate;
import com.huawei.openstack4j.model.common.Identifier;

public class KeyManage {

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
			

			//create-key
			KeyCreate create = KeyCreate.builder().alias("test").description("desc").build();
			String keyId = osclient.keyManagement().keys().create(create).getId();
			
			//describe-key
			Key get = osclient.keyManagement().keys().get(keyId, null);
			
			//list-keys
			List<Key> keys = osclient.keyManagement().keys().list(null).value();
			
			//disable-key
			Key disabled = osclient.keyManagement().keys().disable(keyId, null);
			
			//enable-key
			Key enabled = osclient.keyManagement().keys().enable(keyId, null);
			
			//shecdule-key-deletion
			Key deleted = osclient.keyManagement().keys().scheduleDeletion(keyId, 7, null);
			
			//cancel-key-deletion
			Key canceled = osclient.keyManagement().keys().cancelDeletion(keyId, null);
			
			//enable-key
			osclient.keyManagement().keys().enable(keyId, null);
			
			//gen-random
			Integer keyCreatedAmount = osclient.keyManagement().keys().getKeyCreatedAmount();
			}

}