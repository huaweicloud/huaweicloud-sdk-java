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
import com.huawei.openstack4j.openstack.cdn.v1.domain.*;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Domain.Domains;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Source.Origin;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Task.Tasks;
import com.huawei.openstack4j.openstack.cdn.v1.domain.CdnIP.CdnIPs;
import com.huawei.openstack4j.openstack.cdn.v1.exception.ServerCdnErrorResponseException;

/**
 * Domain Name Configuration Service
 */
public class DomainService extends BaseCdnServices {

    /**
     * Querying an Acceleration Domain Name
     *
     * @param params Params map supports the following parameters: domain_name,business_type,domain_status,page_size,page_number
     * @return {@link Domains} instance
     */
    public Domains list(Map<String, String> params) {
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
     *
     * @param creation domain infomation
     * @return {@link Domain} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Domain create(DomainCreate creation) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(creation, "parameter `creation` should not be null");
        Preconditions.checkNotNull(creation.getBusinessType(), "parameter `business_type` should not be null");
        Preconditions.checkNotNull(creation.getDomainName(), "parameter `domain_name` should not be null");
        Preconditions.checkNotNull(creation.getSources(), "parameter `creation.sources` should not be empty");
        Preconditions.checkNotNull(creation.getSources().get(0).getIpOrDomain(), "parameter `ip_or_domain` should not be empty");
        return post(Domain.class, uri("/domains")).entity(creation).execute(this.buildExecutionOptions(Domain.class));
    }

    /**
     * Deleting an Acceleration Domain Name
     *
     * @param domainId domain ID
     * @return {@link Domain} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Domain delete(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Invocation<Domain> domainInvocation = delete(Domain.class, uri("/domains/%s", domainId));
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(Domain.class));
    }

    /**
     * Enabling an Acceleration Domain Name
     *
     * @param domainId domain ID
     * @return {@link Domain} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Domain enable(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Invocation<Domain> domainInvocation = put(Domain.class, uri("/domains/%s/enable", domainId));
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(Domain.class));
    }

    /**
     * Disabling an Acceleration Domain Name
     *
     * @param domainId domain ID
     * @return {@link Domain} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Domain disable(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Invocation<Domain> domainInvocation = put(Domain.class, uri("/domains/%s/disable", domainId));
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(Domain.class));
    }

    /**
     * Querying Details About an Acceleration Domain Name
     *
     * @param domainId domain ID
     * @return {@link Domain} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Domain getDetail(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Invocation<Domain> domainInvocation = get(Domain.class, uri("/domains/%s/detail", domainId));
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(Domain.class));
    }

    /**
     * Modifying Information About the Origin Server
     *
     * @param domainId domain ID
     * @param origin   origin infomation
     * @return {@link Origin} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Origin setOrigin(String domainId, Origin origin, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Preconditions.checkNotNull(origin, "parameter `origin` should not be null");
        Invocation<Origin> domainInvocation = put(Origin.class, uri("/domains/%s/origin", domainId)).entity(origin);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(Origin.class));
    }

    /**
     * Querying a Retrieval Host
     *
     * @param domainId domain ID
     * @return {@link OriginHost} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public OriginHost getOriginHost(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Invocation<OriginHost> domainInvocation = get(OriginHost.class, uri("/domains/%s/originhost", domainId));
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(OriginHost.class));
    }

    /**
     * Modifying the Configuration of the Retrieval Host
     *
     * @param domainId   domain ID
     * @param originHost origin host information
     * @return {@link OriginHost} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public OriginHost setOriginHost(String domainId, OriginHost originHost, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Preconditions.checkNotNull(originHost, "parameter `originHost` should not be null");
        Preconditions.checkNotNull(originHost.getOriginHostType(), "parameter `origin_host_type` should not be null");
        Invocation<OriginHost> domainInvocation = put(OriginHost.class, uri("/domains/%s/originhost", domainId)).entity(originHost);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(OriginHost.class));
    }

    /**
     * Configuring a Referrer List
     *
     * @param domainId domain ID
     * @param referer  referer information
     * @return {@link Referer} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Referer setReferer(String domainId, Referer referer, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Preconditions.checkNotNull(referer, "parameter `referer` should not be null");
        Preconditions.checkNotNull(referer.getRefererType(), "parameter `referer_type` should not be null");
        Invocation<Referer> domainInvocation = put(Referer.class, uri("/domains/%s/referer", domainId)).entity(referer);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(Referer.class));
    }

    /**
     * Querying a Referrer List
     *
     * @param domainId domain ID
     * @return {@link Referer} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Referer getReferer(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Invocation<Referer> domainInvocation = get(Referer.class, uri("/domains/%s/referer", domainId));
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(Referer.class));
    }

    /**
     * Configuring a Cache Rule
     *
     * @param domainId    domain ID
     * @param cacheConfig cache configuration
     * @return {@link CacheConfig} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public CacheConfig setCacheConfig(String domainId, CacheConfig cacheConfig, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Preconditions.checkNotNull(cacheConfig, "parameter `cacheConfig` should not be null");
        Invocation<CacheConfig> domainInvocation = put(CacheConfig.class, uri("/domains/%s/cache", domainId)).entity(cacheConfig);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(CacheConfig.class));
    }

    /**
     * Querying a Cache Rule
     *
     * @param domainId domain ID
     * @return {@link CacheConfig} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public CacheConfig getCacheConfig(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Invocation<CacheConfig> domainInvocation = get(CacheConfig.class, uri("/domains/%s/cache", domainId));
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(CacheConfig.class));
    }

    /**
     * Configuring HTTPS
     *
     * @param domainId  domain ID
     * @param httpsInfo https configuration
     * @return {@link HttpsInfo} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public HttpsInfo setHttpsInfo(String domainId, HttpsInfo httpsInfo, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Preconditions.checkNotNull(httpsInfo, "parameter `httpsInfo` should not be null");
        Preconditions.checkNotNull(httpsInfo.getHttpsStatus(), "parameter `https_status` should not be null");
        if (httpsInfo.getHttpsStatus() != 0) {
            Preconditions.checkNotNull(httpsInfo.getCertName(), "parameter `cert_name` should not be null");

        }
        Invocation<HttpsInfo> domainInvocation = put(HttpsInfo.class, uri("/domains/%s/https-info", domainId)).entity(httpsInfo);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(HttpsInfo.class));
    }

    /**
     * Querying HTTPS Configurations
     *
     * @param domainId domain ID
     * @return {@link HttpsInfo} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public HttpsInfo getHttpsInfo(String domainId, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Invocation<HttpsInfo> domainInvocation = get(HttpsInfo.class, uri("/domains/%s/https-info", domainId));
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                domainInvocation = domainInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return domainInvocation.execute(this.buildExecutionOptions(HttpsInfo.class));
    }

    /**
     * Creating a Cache Refreshing Task
     *
     * @param creation refresh task infomation
     * @return {@link RefreshTask} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public RefreshTask createRefreshTask(RefreshTaskCreate creation, Map<String, String> params) throws ServerCdnErrorResponseException {
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
     *
     * @param creation preheating task information
     * @return {@link PreheatingTask} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public PreheatingTask createPreheatingTask(PreheatingTaskCreate creation, Map<String, String> params) throws ServerCdnErrorResponseException {
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
     *
     * @param params Params map supports the following parameters:page_size,page_number,status,start_date,end_date,order_field,order_type,user_domain_id,file_type,task_id,enterprise_project_id
     * @return {@link Tasks} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Tasks queryTasks(Map<String, String> params) throws ServerCdnErrorResponseException {
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
     *
     * @param taskId query task ID
     * @return {@link TaskDetail} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public TaskDetail getTaskDetail(String taskId, Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(taskId, "parameter `taskId` should not be null");
        Invocation<TaskDetail> taskDetailInvocation = get(TaskDetail.class, uri("/historytasks/%s/detail", taskId));
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                taskDetailInvocation = taskDetailInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return taskDetailInvocation.execute(this.buildExecutionOptions(TaskDetail.class));
    }

    /**
     * Querying CDN ips
     *
     * @param params Params map supports the following parameters:ips,enterprise_project_id
     * @return {@link CdnIPs} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public CdnIPs queryCdnIPs(Map<String, String> params) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(params.get("ips"), "parameter `ips` should not be null");
        Invocation<CdnIPs> cdnIPsInvocation = get(CdnIPs.class, uri("/ip-info"));

        for (Map.Entry<String, String> entry : params.entrySet()) {
            cdnIPsInvocation = cdnIPsInvocation.param(entry.getKey(), entry.getValue());
        }

        return cdnIPsInvocation.execute(this.buildExecutionOptions(CdnIPs.class));
    }

    /**
     * Set range status
     *
     * @param rs range status
     * @return {@link OriginRange} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public OriginRange setOriginRange(String domainId, OriginRangeStatus rs) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Preconditions.checkNotNull(rs, "parameter `OriginRangeStatus` should not be null");
        Preconditions.checkNotNull(rs.getRangeStatus(), "parameter `range_status` should not be null");
        Invocation<OriginRange> originRangeInvocation = put(OriginRange.class, uri("/domains/%s/range-switch", domainId)).entity(rs);

        return originRangeInvocation.execute(this.buildExecutionOptions(OriginRange.class));
    }

    /**
     * Set follow302 status
     *
     * @param fs follow 302 status
     * @return {@link Follow302} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Follow302 setFollow302(String domainId, Follow302Status fs) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Preconditions.checkNotNull(fs, "parameter `Follow302Status` should not be null");
        Preconditions.checkNotNull(fs.getFollow302Status(), "parameter `follow302_status` should not be null");
        Invocation<Follow302> Follow302Invocation = put(Follow302.class, uri("/domains/%s/follow302-switch", domainId)).entity(fs);

        return Follow302Invocation.execute(this.buildExecutionOptions(Follow302.class));
    }

    /**
     * Set IP acl
     *
     * @param domainId domain ID
     * @param ipal     IP ACL
     * @return {@link IpAcl} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public IpAcl setIpAcl(String domainId, IpAclList ipal) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Preconditions.checkNotNull(ipal, "parameter `IpAclList` should not be null");
        Preconditions.checkNotNull(ipal.getType(), "parameter `type` should not be null");
        if (ipal.getType() != 0) {
            Preconditions.checkNotNull(ipal.getIpList(), "parameter `ip_list` should not be null");
        }
        Invocation<IpAcl> IpAclInvocation = put(IpAcl.class, uri("/domains/%s/ip-acl", domainId)).entity(ipal);
        return IpAclInvocation.execute(this.buildExecutionOptions(IpAcl.class));
    }

    /**
     * Get IP acl
     *
     * @param domainId domain ID
     * @return {@link IpAclList} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public IpAclList getIpAcl(String domainId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Invocation<IpAclList> IpAclListInvocation = get(IpAclList.class, uri("/domains/%s/ip-acl", domainId));
        return IpAclListInvocation.execute(this.buildExecutionOptions(IpAclList.class));
    }

    /**
     * Set Response Header
     *
     * @param domainId domain ID
     * @param rh       Response Header
     * @return {@link ResponseHeader} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public ResponseHeader setResponseHeader(String domainId, ResponseHeader rh) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Preconditions.checkNotNull(rh, "parameter `headers` should not be null");

        Invocation<ResponseHeader> ResponseHeaderInvocation = put(ResponseHeader.class, uri("/domains/%s/response-header", domainId)).entity(rh);
        return ResponseHeaderInvocation.execute(this.buildExecutionOptions(ResponseHeader.class));
    }

    /**
     * Get Response Header
     *
     * @param domainId domain ID
     * @return {@link ResponseHeader} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public ResponseHeader getResponseHeader(String domainId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainId, "parameter `domainId` should not be null");
        Invocation<ResponseHeader> ResponseHeaderInvocation = get(ResponseHeader.class, uri("/domains/%s/response-header", domainId));
        return ResponseHeaderInvocation.execute(this.buildExecutionOptions(ResponseHeader.class));
    }
}
