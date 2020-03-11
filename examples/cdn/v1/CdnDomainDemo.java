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
package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.cdn.v1.domain.*;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdnDomainDemo {

    public static void main(String[] args) {
        // step 1: add cloud service override endpoint
        OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
        endpointResolver.addOverrideEndpoint(ServiceType.CDN, "xxx");//example:"https://cdn.myhuaweicloud.com/v1.0"

        // step 2: initial OpenStack4j Client
        OSFactory.enableHttpLoggingFilter(true);

        // step 3: config of the client
        Config config = Config.newConfig()
                .withEndpointURLResolver(endpointResolver);

        // step4：AKSK authorization：：setup the authentication credit
        String ak = "xxxx";
        String sk = "xxxx";
        String projectId = "xxxx";// the project ID of cn-north-1
        String region = "xxxx"; //example: region = "cn-north-1"
        String cloud = "xxxx"; //example: cloud = "myhuaweicloud.com"

        OSClient.OSClientAKSK osclient = OSFactory.builderAKSK().withConfig(config).credentials(ak, sk, region, projectId, cloud).authenticate();

        /*
        // step 4: token authorization：setup the authentication credit
        String user = "username"; // IAM User Name
        String password = "password"; // IAM User Password
        String projectId = "projectId"; // the project ID of cn-north-1
        String userDomainId = "userDomainId"; // Account ID
        String authUrl = "xxxxxxx"; // example: authUrl = "https://iam.myhuaweicloud.com/v3/"

        OSClient.OSClientV3 osclient = OSFactory.builderV3()
                .withConfig(config)
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId))
                .authenticate();
        */

        // Parameter package
        Map<String, String> params = new HashMap<>();

        // Multi-project account enterprise_project_id
//        params.put("enterprise_project_id", "xxxxxxx");

        // query domain ID or set Domain ID
        /*
        params.put("domain_name", "example.domain.com");
        params.put("page_size", "10");
        params.put("page_number", "1");
        String domainId = osclient.cdn().domains().list(params).first().getId();
         */
        String domainId = "xxxxxxxxxxxxxxxxxxxxx";
        System.out.println("domain id is " + domainId);


        // part 1:domain operation
        // get domain list
        Domain.Domains domains = osclient.cdn().domains().list(params);
        System.out.println(domains.getList());

        // create domain
        Source source = Source.builder().activeStandby(1).ipOrDomain("1.2.3.4").originType("ipaddr").build();
        List<Source> sources = new ArrayList<>();
        sources.add(source);
        DomainCreate creation = DomainCreate.builder().businessType("web").domainName("xxxxxxx")
                .enterpriseProjectId(params.get("enterprise_project_id"))
                .sources(sources).build();
        Domain domainCreate = osclient.cdn().domains().create(creation);
        System.out.println(domainCreate);
        System.out.println("domain Create success...");

        // get domain detail
        Domain domainDetail = osclient.cdn().domains().getDetail(domainId, params);
        System.out.println(domainDetail);
        System.out.println("domain getDetail success...");

        // delete domain:deactivate only, configuration fails, audit failed, and the failed domain name can be deleted
        Domain domainDelete = osclient.cdn().domains().delete(domainId, params);
        System.out.println(domainDelete);
        System.out.println("domain delecte success...");

        // enable domain:domain names that are not blocked and locked can be disabled if they are deactivated or configured to fail
        Domain domainEnable = osclient.cdn().domains().enable(domainId, params);
        System.out.println(domainEnable);
        System.out.println("domain enable success...");

        // disable domain:domain names that are not blocked and locked can be disabled if they are deactivated or configured to fail
        Domain domainDisable = osclient.cdn().domains().disable(domainId, params);
        System.out.println(domainDisable);
        System.out.println("domain disable success...");


        // part 2:domain configuration
        // set origin
        Source newSource1 = Source.builder().ipOrDomain("1.2.3.4").originType("ipaddr").activeStandby(1).build();
        Source newSource2 = Source.builder().ipOrDomain("example.source.com").originType("domain").activeStandby(0).build();
        List<Source> newSources = new ArrayList<>();
        newSources.add(newSource1);
        newSources.add(newSource2);
        Source.Origin newOrigin = Source.Origin.builder().sources(newSources).build();
        Source.Origin origin = osclient.cdn().domains().setOrigin(domainId, newOrigin, params);
        System.out.println(origin);
        System.out.println("domain setOrigin success...");

        // set originHost
        OriginHost newOriginHost = OriginHost.builder().customizeDomain("example.host.com").originHostType("customize").build();
        OriginHost originHost = osclient.cdn().domains().setOriginHost(domainId, newOriginHost, params);
        System.out.println(originHost);
        System.out.println("domain setOriginHost success...");

        // get originHost
        OriginHost originHosts = osclient.cdn().domains().getOriginHost(domainId, params);
        System.out.println(originHosts);
        System.out.println("domain getOriginHost success...");

        // set range
        OriginRangeStatus rangeStatus = OriginRangeStatus.builder().rangeStatus("on").build();
        OriginRange originRange = osclient.cdn().domains().setOriginRange(domainId, rangeStatus);
        System.out.println(originRange);
        System.out.println("domain setOriginRange success...");

        // set follow 302
        Follow302Status f302Status = Follow302Status.builder().follow302Status("off").build();
        Follow302 f302 = osclient.cdn().domains().setFollow302(domainId, f302Status);
        System.out.println(f302);
        System.out.println("domain setFollow302 success...");


        // set referer
        Referer referferNew = Referer.builder().refererList("www.xxx.com;www.xxx2.com").refererType(2)
                .includeEmpty(true).build();
        Referer referer = osclient.cdn().domains().setReferer(domainId, referferNew, params);
        System.out.println(referer);
        System.out.println("domain setReferer success...");

        // get referer
        Referer referers = osclient.cdn().domains().getReferer(domainId, params);
        System.out.println(referers);
        System.out.println("domain getReferer success...");

        // get ip-acl
        IpAclList ipAcllists = osclient.cdn().domains().getIpAcl(domainId);
        System.out.println(ipAcllists);
        System.out.println("domain getIpAcl success...");

        // set ip-acl
        List<String> newIpAcls = new ArrayList<>();
        newIpAcls.add("1.2.3.5");
        newIpAcls.add("2.3.4.5");
        IpAclList newIpAclList = IpAclList.builder().type(1).ipList(newIpAcls).build();
        IpAcl ipAcl = osclient.cdn().domains().setIpAcl(domainId, newIpAclList);
        System.out.println(ipAcl);
        System.out.println("domain setIpAcl success...");

        // set cacheConfig
        CacheRule rule1 = CacheRule.builder().rule_type(0).ttl(2).ttlType(3).priority(10).build();
        CacheRule rule2 = CacheRule.builder().rule_type(1).content(".jpg;.png").ttl(2).ttlType(3).priority(11).build();
        List<CacheRule> rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);
        CacheConfig newCacheConfig = CacheConfig.builder().ignoreUrlParameter(true).rules(rules).build();
        CacheConfig cacheConfig = osclient.cdn().domains().setCacheConfig(domainId, newCacheConfig, params);
        System.out.println(cacheConfig);
        System.out.println("domain setCacheConfig success...");

        // get CacheConfig
        cacheConfig = osclient.cdn().domains().getCacheConfig(domainId, params);
        System.out.println(cacheConfig);
        System.out.println("domain getCacheConfig success...");

        // set httpsInfo
        // https on
        HttpsInfo newHttpsInfo1 = HttpsInfo.builder().httpsStatus(2)
                .certName("cdn_test_cert").certificate("xxxxxxx")
                .privateKey("xxxxxxx").forceRedirectHttps(0).http2(0)
                .build();
        HttpsInfo httpsInfo1 = osclient.cdn().domains().setHttpsInfo(domainId, newHttpsInfo1, params);
        System.out.println(httpsInfo1);
        System.out.println("domain setHttpsInfo success...");

        // https off
        HttpsInfo newHttpsInfo2 = HttpsInfo.builder().httpsStatus(0).build();
        HttpsInfo httpsInfo2 = osclient.cdn().domains().setHttpsInfo(domainId, newHttpsInfo2, params);
        System.out.println(httpsInfo2);
        System.out.println("domain setHttpsInfo success...");

        // get httpsInfo
        HttpsInfo httpsInfo3 = osclient.cdn().domains().getHttpsInfo(domainId, params);
        System.out.println(httpsInfo3);
        System.out.println("domain getHttpsInfo success...");

        // get cdn ip-info
        params.put("ips", "x.x.x.x,x.x.x.x");
        CdnIP.CdnIPs cdnips = osclient.cdn().domains().queryCdnIPs(params);
        System.out.println(cdnips.getList());

        // set response header
        ResponseHeader newResponseHeader = ResponseHeader.builder()
                .accessControlAllowMethods("GET")
                .accessControlAllowOrigin("http://www.test.com")
                .accessControlMaxAge("123")
                .conteaccessControlExposeHeaders("content-type")
                .contentLanguage("zh-CN")
                .contentDisposition("test.xml").build();
        ResponseHeader responseHeader1 = osclient.cdn().domains().setResponseHeader(domainId, newResponseHeader);
        System.out.println(responseHeader1);
        System.out.println("domain setResponseHeader success...");

        // get response header
        ResponseHeader responseHeader2 = osclient.cdn().domains().getResponseHeader(domainId);
        System.out.println(responseHeader2);
        System.out.println("domain getResponseHeader success...");


        // part 3:content refresh and preheating
        // create refreshTask
        List<String> refreshTaskUrls = new ArrayList<>();
        refreshTaskUrls.add("http://example.domain.com/web");
        refreshTaskUrls.add("http://example.domain.com/index.html");
        RefreshTaskCreate refreshTaskCreate = RefreshTaskCreate.builder().type("file").urls(refreshTaskUrls).build();
        RefreshTask refreshTask = osclient.cdn().domains().createRefreshTask(refreshTaskCreate, params);
        System.out.println(refreshTask);
        System.out.println("CreateRefreshTask success...");

        // create preheatingTask
        List<String> urls = new ArrayList<>();
        urls.add("http://example.domain.com/test.jpg");
        urls.add("http://example.domain.com/test/di.png");
        PreheatingTaskCreate preheatingTaskCreation = PreheatingTaskCreate.builder().urls(urls).build();
        PreheatingTask preheatingTask = osclient.cdn().domains().createPreheatingTask(preheatingTaskCreation, params);
        System.out.println(preheatingTask);
        System.out.println("CreatePreheatingTask success...");

        // query historyTasks
        Task.Tasks tasks = osclient.cdn().domains().queryTasks(params);
        System.out.println(tasks.getList());
        System.out.println("QueryHistoryTasks success...");

        // query historyTasksDetail
        String taskId = "8888888";
        TaskDetail taskDetail = osclient.cdn().domains().getTaskDetail(taskId, params);
        System.out.println(taskDetail);
        System.out.println("QueryTaskDetail success...");
    }
}
