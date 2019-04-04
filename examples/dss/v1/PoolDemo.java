package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.dss.v1.domain.Pool;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

public class PoolDemo
{
	public static void main(String[] args) throws InterruptedException {

		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxx"; //username
		String password = "xxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

		//create connection
		OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
		endpointResolver.addOverrideEndpoint(ServiceType.DSS, "https://dss.xxx.yyy.com/v1/%(project_id)s");
		Config config = Config.newConfig().withEndpointURLResolver(endpointResolver);
		OSClient.OSClientV3 os = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToDomain(Identifier.byId(userDomainId))
				.scopeToProject(Identifier.byId(projectId)).authenticate();

		//get pool by poolId
		String dssId = "517eb026-2cdc-45da-9ebc-0c6ececb1236";
		Pool pool = os.dss().pools().get(dssId);

		if (null != pool) {
			System.out.println("get pool success, poolId = " + dssId);
		} else {
			System.out.println("get pool failed");
		}

		//get pool by poolId with usage
		Pool pool2 = os.dss().pools().get(dssId,true);

		if (null != pool2) {
			System.out.println("get pool success, poolId = " + dssId);
		} else {
			System.out.println("get pool failed");
		}
	}
}