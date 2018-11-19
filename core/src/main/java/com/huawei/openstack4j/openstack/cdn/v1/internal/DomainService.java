/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.                                       
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
package com.huawei.openstack4j.openstack.cdn.v1.internal;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.huawei.openstack4j.openstack.cdn.v1.domain.CacheConfig;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Domain;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Domain.Domains;
import com.huawei.openstack4j.openstack.cdn.v1.domain.DomainCreate;
import com.huawei.openstack4j.openstack.cdn.v1.domain.HttpsInfo;
import com.huawei.openstack4j.openstack.cdn.v1.domain.OriginHost;
import com.huawei.openstack4j.openstack.cdn.v1.domain.PreheatingTask;
import com.huawei.openstack4j.openstack.cdn.v1.domain.PreheatingTaskCreate;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Referer;
import com.huawei.openstack4j.openstack.cdn.v1.domain.RefreshTask;
import com.huawei.openstack4j.openstack.cdn.v1.domain.RefreshTaskCreate;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Source.Origin;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Task.Tasks;
import com.huawei.openstack4j.openstack.cdn.v1.domain.TaskDetail;
import com.huawei.openstack4j.openstack.cdn.v1.exception.ServerCdnErrorResponseException;

/**
 * Domain Name Configuration Service
 * @author ChangjunZhao
 * @date   2018-05-06
 */
public class DomainService extends BaseCdnServices{

	/**
	 * Querying an Acceleration Domain Name
	 * @param params Params map supports the following parameters: <br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;<b>domain_name</b>:Specifies the acceleration domain name, which is matched in a fuzzy manner. The value contains 1 to 255 characters.<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;<b>business_type</b>:Specifies the business type of the domain name. Values can be: <b>web</b> (image and small file distribution), <b>download</b> (large file download acceleration), and <b>video</b> (on-demand audio and video acceleration).<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;<b>domain_status</b>:Specifies the status of the acceleration domain name. Values include <b>online</b>: CDN is enabled; <b>offline</b>: CDN is disabled; <b>configuring</b>: CDN is being configured; <b>configure_failed</b>: the configuration failed; <b>checking</b>: the configuration is being audited; <b>check_failed</b>: the audit failed; <b>deleting</b>: the acceleration domain name is being deleted.<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;<b>page_size</b>:Specifies the number of acceleration domain names on each page. The value ranges from 10 to 1000.<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;<b>page_number</b>:Specifies the page number that is queried. The value ranges from 1 to 65535.
	 * @return {@link Domains} instance
	 */
	public Domains list(Map<String, String> params){
		Preconditions.checkNotNull(params.get("page_size"), "parameter `page_size` should not be null");
		Preconditions.checkNotNull(params.get("page_number"), "parameter `page_number` should not be null");
		Invocation<Domains> domainInvocation = get(Domains.class, uri("/domains"));
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(Domains.class));
	}

	/**
	 * Creating an Acceleration Domain Name
	 * @param creation
	 * @return {@link Domain} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public Domain create(DomainCreate creation) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(creation, "parameter `creation` should not be null");
		Preconditions.checkNotNull(creation.getBusinessType(), "parameter `business_type` should not be null");
		Preconditions.checkNotNull(creation.getDomainName(), "parameter `domain_name` should not be null");
		Preconditions.checkNotNull(creation.getSources(),"parameter `creation.sources` should not be empty");
		Preconditions.checkNotNull(creation.getSources().get(0).getIpOrDomain(), "parameter `ip_or_domain` should not be empty");
		return post(Domain.class, uri("/domains")).entity(creation).execute(this.buildExecutionOptions(Domain.class));
	}

	/**
	 * Deleting an Acceleration Domain Name
	 * @param domainId
	 * @return {@link Domain} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public Domain delete(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Invocation<Domain> domainInvocation = delete(Domain.class, uri("/domains/%s",domainId));
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(Domain.class));
	}

	/**
	 * Enabling an Acceleration Domain Name
	 * @param domainId
	 * @return {@link Domain} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public Domain enable(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Invocation<Domain> domainInvocation = put(Domain.class, uri("/domains/%s/enable",domainId));
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(Domain.class));
	}

	/**
	 * Disabling an Acceleration Domain Name
	 * @param domainId
	 * @return {@link Domain} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public Domain disable(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Invocation<Domain> domainInvocation = put(Domain.class, uri("/domains/%s/disable",domainId));
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(Domain.class));
	}

	/**
	 * Querying Details About an Acceleration Domain Name
	 * @param domainId
	 * @return {@link Domain} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public Domain getDetail(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Invocation<Domain> domainInvocation = get(Domain.class, uri("/domains/%s/detail",domainId));
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(Domain.class));
	}

	/**
	 * Modifying Information About the Origin Server
	 * @param domainId
	 * @param origin
	 * @return {@link Origin} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public Origin setOrigin(String domainId, Origin origin, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Preconditions.checkNotNull(origin, "parameter `origin` should not be null");
		Invocation<Origin> domainInvocation = put(Origin.class, uri("/domains/%s/origin",domainId)).entity(origin);
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(Origin.class));
	}

	/**
	 * Querying a Retrieval Host
	 * @param domainId
	 * @return {@link OriginHost} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public OriginHost getOriginHost(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Invocation<OriginHost> domainInvocation = get(OriginHost.class, uri("/domains/%s/originhost",domainId));
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(OriginHost.class));
	}

	/**
	 * Modifying the Configuration of the Retrieval Host
	 * @param domainId
	 * @param originHost
	 * @return {@link OriginHost} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public OriginHost setOriginHost(String domainId, OriginHost originHost, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Preconditions.checkNotNull(originHost, "parameter `originHost` should not be null");
		Preconditions.checkNotNull(originHost.getOriginHostType(), "parameter `origin_host_type` should not be null");
		Invocation<OriginHost> domainInvocation = put(OriginHost.class, uri("/domains/%s/originhost",domainId)).entity(originHost);
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(OriginHost.class));
	}

	/**
	 * Configuring a Referrer List
	 * @param domainId
	 * @param referer
	 * @return {@link Referer} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public Referer setReferer(String domainId, Referer referer, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Preconditions.checkNotNull(referer, "parameter `referer` should not be null");
		Preconditions.checkNotNull(referer.getRefererType(), "parameter `referer_type` should not be null");
		Invocation<Referer> domainInvocation = put(Referer.class, uri("/domains/%s/referer",domainId)).entity(referer);
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(Referer.class));
	}

	/**
	 * Querying a Referrer List
	 * @param domainId
	 * @return {@link Referer} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public Referer getReferer(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Invocation<Referer> domainInvocation = get(Referer.class, uri("/domains/%s/referer",domainId));
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(Referer.class));
	}

	/**
	 * Configuring a Cache Rule
	 * @param domainId
	 * @param cacheConfig
	 * @return {@link CacheConfig} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public CacheConfig setCacheConfig(String domainId, CacheConfig cacheConfig, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Preconditions.checkNotNull(cacheConfig, "parameter `cacheConfig` should not be null");
		Invocation<CacheConfig> domainInvocation = put(CacheConfig.class, uri("/domains/%s/cache",domainId)).entity(cacheConfig);
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(CacheConfig.class));
	}

	/**
	 * Querying a Cache Rule
	 * @param domainId
	 * @return {@link CacheConfig} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public CacheConfig getCacheConfig(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Invocation<CacheConfig> domainInvocation = get(CacheConfig.class, uri("/domains/%s/cache",domainId));
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(CacheConfig.class));
	}

	/**
	 * Configuring HTTPS
	 * @param domainId
	 * @param httpsInfo
	 * @return {@link HttpsInfo} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public HttpsInfo setHttpsInfo(String domainId, HttpsInfo httpsInfo, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Preconditions.checkNotNull(httpsInfo, "parameter `httpsInfo` should not be null");
		Preconditions.checkNotNull(httpsInfo.getCertName(), "parameter `cert_name` should not be null");
		Preconditions.checkNotNull(httpsInfo.getHttpsStatus(), "parameter `https_status` should not be null");
		Invocation<HttpsInfo> domainInvocation = put(HttpsInfo.class, uri("/domains/%s/https-info",domainId)).entity(httpsInfo);
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(HttpsInfo.class));
	}

	/**
	 * Querying HTTPS Configurations
	 * @param domainId
	 * @return {@link HttpsInfo} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public HttpsInfo getHttpsInfo(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
		Invocation<HttpsInfo> domainInvocation = get(HttpsInfo.class, uri("/domains/%s/https-info",domainId));
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(HttpsInfo.class));
	}

	/**
	 * Creating a Cache Refreshing Task
	 * @param creation
	 * @return {@link RefreshTask} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public RefreshTask createRefreshTask(RefreshTaskCreate creation, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(creation, "parameter `creation` should not be null");
		Preconditions.checkNotNull(creation.getUrls(), "parameter `urls` should not be null");
		Invocation<RefreshTask> domainInvocation = post(RefreshTask.class, uri("/refreshtasks")).entity(creation);
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(RefreshTask.class));
	}

	/**
	 * Creating a Preheating Task
	 * @param creation
	 * @return {@link PreheatingTask} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public PreheatingTask createPreheatingTask(PreheatingTaskCreate creation, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(creation, "parameter `creation` should not be null");
		Preconditions.checkNotNull(creation.getUrls(), "parameter `urls` should not be null");
		Invocation<PreheatingTask> domainInvocation = post(PreheatingTask.class, uri("/preheatingtasks")).entity(creation);
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return domainInvocation.execute(this.buildExecutionOptions(PreheatingTask.class));
	}

	/**
	 * Querying a Cache Refreshing or Preheating Task
	 * @param params
	 * @return {@link Tasks} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public Tasks queryTasks(Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(params.get("page_size"), "parameter `page_size` should not be null");
		Preconditions.checkNotNull(params.get("page_number"), "parameter `page_number` should not be null");
		Invocation<Tasks> taskInvocation = get(Tasks.class, uri("/historytasks"));
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				taskInvocation = taskInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return taskInvocation.execute(this.buildExecutionOptions(Tasks.class));
	}

	/**
	 * Querying Details About a Cache Refreshing or Preheating Task
	 * @param taskId
	 * @return {@link TaskDetail} instance
	 * @throws ServerCdnErrorResponseException
	 */
	public TaskDetail getTaskDetail(String taskId, Map<String, String> params) throws ServerCdnErrorResponseException{
		Preconditions.checkNotNull(taskId, "parameter `taskId` should not be null");
		Preconditions.checkNotNull(params.get("page_size"), "parameter `pageSize` should not be null");
		Preconditions.checkNotNull(params.get("page_number"), "parameter `pageNumber` should not be null");
		Invocation<TaskDetail> taskDetailInvocation = get(TaskDetail.class, uri("/historytasks/%s/detail",taskId));
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				taskDetailInvocation = taskDetailInvocation.param(entry.getKey(), entry.getValue());
			}
		}
		return taskDetailInvocation.execute(this.buildExecutionOptions(TaskDetail.class));
	}
	
}
