/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack.sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.cloudeye.Metric;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

/**
 *Override Example
 */
public class Override {

	private static final String LANGUAGE = "zh-cn";

	public static void main(String[] args) {

		// step 1: add cloud service override endpoint
		OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
		// "example" in the endpoint stands for "Region.Cloud"
		endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_EYE,
				"https://ces.example.com/V1.0/%(project_id)s");
		// endpoint override for the other service
		/*
		 endpointResolver.addOverrideEndpoint(ServiceType.VOLUME_BACKUP,
		 "https://vbs.example.com/v2/%(project_id)s");
		 endpointResolver.addOverrideEndpoint(ServiceType.AUTO_SCALING,
		 "https://as.example.com/autoscaling-api/v1/%(project_id)s");
		 endpointResolver.addOverrideEndpoint(ServiceType.LOAD_BALANCER,
		 "https://elb.example.com/v1.0/%(project_id)s");
		 endpointResolver.addOverrideEndpoint(ServiceType.MAP_REDUCE,
		 "https://mrs.example.com/v1.1/%(project_id)s");
		 endpointResolver.addOverrideEndpoint(ServiceType.KEY_MANAGEMENT,
		 "https://kms.example.com/v1.0/%(project_id)s");
		 endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_TRACE,
		 "https://cts.example.com/v1.0/%(project_id)s");
		 endpointResolver.addOverrideEndpoint(ServiceType.ANTI_DDOS,
		 "https://antiddos.example.com/v1/%(project_id)s");
		 endpointResolver.addOverrideEndpoint(ServiceType.Notification,
		 "https://smn.example.com/v2/%(project_id)s");
		 endpointResolver.addOverrideEndpoint(ServiceType.MessageQueue,
		 "https://dms.example.com/v1.0/%(project_id)s");
		 endpointResolver.addOverrideEndpoint(ServiceType.MAAS,
		 "https://maas.example.com/v1/%(project_id)s");
		 endpointResolver.addOverrideEndpoint(ServiceType.DATABASE, "https://rds.example.com");
		 endpointResolver.addOverrideEndpoint(ServiceType.DNS, "https://dns.example.com");
		 */

		// step 2: setup the authentication credit
		String user = "replace-with-your-username";
		String password = "replace-with-your-password";
		String projectId = "replace-with-your-project-id";
		String userDomainId = "replace-with-your-user-domain-id";
		// "example" in the endpoint stands for "Region.Cloud"
		String authUrl = "https://iam.example.com/v3";

		// step 3: initial OpenStack4j Client
		// open log switch
		OSFactory.enableHttpLoggingFilter(true);
		// config of the client
		// with language setting is required for RDS(trove&database) service
		// withSSLVerificationDisabled is required if the SSL certification of the cloud service is illegal
		Config config = Config.newConfig().withEndpointURLResolver(endpointResolver).withLanguage(LANGUAGE)
				.withSSLVerificationDisabled();

		// initial client
		OSClientV3 osclient = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId))
				.scopeToProject(Identifier.byId(projectId)).authenticate();
		
		// Use client to visit CES list zone API
		List<? extends Metric> list = osclient.cloudEye().metrics().getList();
		System.out.println(list);
	}

}