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
package com.huawei.openstack4j.openstack.internal;

import static com.google.common.base.Preconditions.checkArgument;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.OSClient.OSClientAKSK;
import com.huawei.openstack4j.api.client.CloudProvider;
import com.huawei.openstack4j.api.exceptions.RegionEndpointNotFoundException;
import com.huawei.openstack4j.api.identity.EndpointURLResolver;
import com.huawei.openstack4j.api.identity.v3.IdentityService;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.model.identity.AuthVersion;
import com.huawei.openstack4j.model.identity.URLResolverParams;
import com.huawei.openstack4j.openstack.identity.internal.AKSKEndpointURLResolver;

public class OSClientSessionAKSK extends OSClientSession<OSClientSessionAKSK, OSClientAKSK> implements OSClientAKSK {

	private String accessKey;
	private String secretKey;
	private String cloudDomainName;
	private String projectId;
	private String domainId;

	CloudProvider provider = CloudProvider.HUAWEI;

	protected EndpointURLResolver defaultEndpointURLResolver = AKSKEndpointURLResolver.instance();

	/*
	 * {@inheritDoc}
	 */
	@Override
	public String getEndpoint() {
		throw new RuntimeException("Token is not required by AKSK-authentication");
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public String getEndpoint(ServiceType service) {
		/*final EndpointURLResolver endpointResolver = (config != null && config.getEndpointURLResolver() != null)
				? config.getEndpointURLResolver() : defaultEndpointURLResolver;		*/
		URLResolverParams params = URLResolverParams.create(service).perspective(perspective).region(region)
				.domain(cloudDomainName).projectId(projectId);
		//如果有重写就先去重写匹配，重写匹配不到就去配置文件匹配
		String url = null;
		try{
			if(config != null && config.getEndpointURLResolver() != null){
				url = config.getEndpointURLResolver().resolve(params);
				if(url == null){
					url = defaultEndpointURLResolver.resolve(params);
				}
			}else{
				url = defaultEndpointURLResolver.resolve(params);
			}
		}catch(NullPointerException e){
			throw new RegionEndpointNotFoundException("region endpoint can not be found");
		}
		return addNATIfApplicable(url);
	}

	private String addNATIfApplicable(String url) {
		if (config != null && config.isBehindNAT()) {
			try {
				URI uri = new URI(url);
				return url.replace(uri.getHost(), config.getEndpointNATResolution());
			} catch (URISyntaxException e) {
				LoggerFactory.getLogger(OSClientSessionAKSK.class).error(e.getMessage(), e);
			}
		}
		return url;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public String getTokenId() {
		return null;
	}
	
	public AuthVersion getAuthVersion() {
		return AuthVersion.AKSK;
	}
	
	/* 
	 * {@inheritDoc}
	 */
	@Override
	public boolean supportsReAuthentication() {
		return false;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public com.huawei.openstack4j.api.OSClient.OSClientAKSK credentials(String accessKey, String secretKey, String region,
																		String projectId, String cloudDomainName) {
		checkArgument(!Strings.isNullOrEmpty(accessKey),"parameter `accessKey` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(secretKey),"parameter `secretKey` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(region),"parameter `region` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(projectId),"parameter `projectId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(cloudDomainName),"parameter `cloudDomainName` should not be empty");
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.cloudDomainName = cloudDomainName;
		this.projectId = projectId;
		this.useRegion(region);
		sessions.set(this);
		return this;
	}


	@Override
	public com.huawei.openstack4j.api.OSClient.OSClientAKSK credentials(String accessKey, String secretKey, String region,
																		String projectId, String domainId, String cloudDomainName) {

		checkArgument(!Strings.isNullOrEmpty(accessKey),"parameter `accessKey` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(secretKey),"parameter `secretKey` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(region),"parameter `region` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(domainId),"parameter `domainId` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(cloudDomainName),"parameter `domain` should not be empty");

		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.cloudDomainName = cloudDomainName;
		this.domainId = domainId;
		this.useRegion(region);
		sessions.set(this);
		return this;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public com.huawei.openstack4j.api.OSClient.OSClientAKSK credentials(String accessKey, String secretKey, String region,
																		String cloudDomainName) {

		checkArgument(!Strings.isNullOrEmpty(accessKey),"parameter `accessKey` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(secretKey),"parameter `secretKey` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(region),"parameter `region` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(cloudDomainName),"parameter `domain` should not be empty");

		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.cloudDomainName = cloudDomainName;
		this.useRegion(region);
		sessions.set(this);
		return this;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public String getCloudDomain() {
		return cloudDomainName;
	}

	public String getProjectId() {
		return projectId;
	}
	public String getDomainId() {
		return domainId;
	}
	
	public String getRegion() {
		return region;
	}

	@Override
	public IdentityService identity() {
		return Apis.getIdentityV3Services();
	}

}
