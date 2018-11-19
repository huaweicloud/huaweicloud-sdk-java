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
import com.huawei.openstack4j.openstack.cdn.v1.domain.CacheConfig;
import com.huawei.openstack4j.openstack.cdn.v1.domain.CacheRule;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Domain;
import com.huawei.openstack4j.openstack.cdn.v1.domain.DomainCreate;
import com.huawei.openstack4j.openstack.cdn.v1.domain.HttpsInfo;
import com.huawei.openstack4j.openstack.cdn.v1.domain.OriginHost;
import com.huawei.openstack4j.openstack.cdn.v1.domain.PreheatingTask;
import com.huawei.openstack4j.openstack.cdn.v1.domain.PreheatingTaskCreate;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Referer;
import com.huawei.openstack4j.openstack.cdn.v1.domain.RefreshTask;
import com.huawei.openstack4j.openstack.cdn.v1.domain.RefreshTaskCreate;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Source;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Task;
import com.huawei.openstack4j.openstack.cdn.v1.domain.TaskDetail;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdnDomainDemo
{

    public static void main(String[] args)
    {
        // step 1: add cloud service override endpoint
        OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
        endpointResolver.addOverrideEndpoint(ServiceType.CDN, "https://cdn.example.com/v1.0");
        // step 2: setup the authentication credit
        String user = "username";
        String password = "password";
        String projectId = "projectId";
        String userDomainId = "userDomainId";
        String authUrl = "xxxxxxx";

        // step 3: initial OpenStack4j Client
        OSFactory.enableHttpLoggingFilter(true);
        // config of the client
        Config config = Config.newConfig()
                .withEndpointURLResolver(endpointResolver);

        // initial client
        OSClient.OSClientV3 osclient = OSFactory.builderV3()
                .withConfig(config)
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId))
                .authenticate();

        //Parameter package
        Map<String, String> params = new HashMap<>();
        //Multi-project account enterprise_project_id
        params.put("enterprise_project_id", "xxxxxxx");

        //create domain
        Source source = Source.builder().activeStandby(1).ipOrDomain("1.2.3.4").originType("ipaddr").build();
        List<Source> sources = new ArrayList<>();
        sources.add(source);
        DomainCreate creation = DomainCreate.builder().businessType("web").domainName("xxxxxxx")
                .enterpriseProjectId(params.get("enterprise_project_id"))
                .sources(sources).build();
        System.out.println(osclient.cdn().domains().create(creation));

        //create preheatingTask
        List<String> urls = new ArrayList<>();
        urls.add("http://xxxxxxx/test.jpg");
        urls.add("https://xxxxxxx/test/di.png");
        urls.add("http://xxxxxxx/test17/test.doc");
        urls.add("http://xxxxxxx/test16.jpg");
        PreheatingTaskCreate preheatingTaskCreation = PreheatingTaskCreate.builder().urls(urls).build();
        PreheatingTask preheatingTask = osclient.cdn().domains().createPreheatingTask(preheatingTaskCreation, params);
        System.out.println(preheatingTask);
        System.out.println("CreatePreheatingTask success...");

        //create refreshTask
        List<String> refreshTaskUrls = new ArrayList<>();
        refreshTaskUrls.add("http://xxxxxxx/web");
        refreshTaskUrls.add("http://xxxxxxx/index.html");
        RefreshTaskCreate refreshTaskCreate = RefreshTaskCreate.builder().type("file").urls(refreshTaskUrls).build();
        RefreshTask refreshTask = osclient.cdn().domains().createRefreshTask(refreshTaskCreate, params);
        System.out.println(refreshTask);
        System.out.println("CreateRefreshTask success...");

        //set cacheConfig
        CacheRule rule = CacheRule.builder().content(".jpg;.png").rule_type(1).ttl(2).ttlType(3).priority(1).build();
        List<CacheRule> rules = new ArrayList<CacheRule>();
        rules.add(rule);
        CacheConfig newCacheConfig = CacheConfig.builder().ignoreUrlParameter(false).rules(rules).build();
        CacheConfig cacheConfig = osclient.cdn().domains().setCacheConfig("xxxxxxx", newCacheConfig, params);
        System.out.println(cacheConfig);
        System.out.println("domain setCacheConfig success...");

        //set referer
        Referer newReferfer = Referer.builder().refererList("www.xxx.com;www.xxx2.com").refererType(2)
                .includeEmpty(true).build();
        Referer referer = osclient.cdn().domains().setReferer("xxxxxxx", newReferfer, params);
        System.out.println(referer);
        System.out.println("domain setReferer success...");

        //set origin
        source = Source.builder().ipOrDomain("1.2.3.4").originType("ipaddr").activeStandby(1).build();
        List<Source> sours = new ArrayList<>();
        sours.add(source);
        Source.Origin neworigin = Source.Origin.builder().sources(sours).build();
        Source.Origin origin = osclient.cdn().domains().setOrigin("xxxxxxx", neworigin, params);
        System.out.println(origin);
        System.out.println("domain setOrigin success...");

        //set originHost
        OriginHost newOriginHost = OriginHost.builder().customizeDomain("xxxxxxx")
                .originHostType("customize").build();
        OriginHost originHost = osclient.cdn().domains().setOriginHost("xxxxxxx", newOriginHost, params);
        System.out.println(originHost);
        System.out.println("domain setOriginHost success...");

        //set httpsInfo
        HttpsInfo newHttpsInfo = HttpsInfo.builder().certificate("xxxxxxx")
                .certName("cdn_test_cert").httpsStatus(2)
                .privateKey("xxxxxxx").forceRedirectHttps(0)
                .build();
        HttpsInfo httpsInfo = osclient.cdn().domains().setHttpsInfo("xxxxxxx", newHttpsInfo, params);
        System.out.println(httpsInfo);
        System.out.println("domain setHttpsInfo success...");

        //get CacheConfig
        cacheConfig = osclient.cdn().domains().getCacheConfig("xxxxxxx", params);
        System.out.println(cacheConfig);
        System.out.println("domain getCacheConfig success...");

        //get domain detail
        Domain domain = osclient.cdn().domains().getDetail("xxxxxxx", params);
        System.out.println(domain);
        System.out.println("domain getDetail success...");

        //get httpsInfo
        httpsInfo = osclient.cdn().domains().getHttpsInfo("xxxxxxx", params);
        System.out.println(httpsInfo);
        System.out.println("domain getHttpsInfo success...");

        //get referer
        referer = osclient.cdn().domains().getReferer("xxxxxxx", params);
        System.out.println(referer);
        System.out.println("domain getReferer success...");

        //get originHost
        originHost = osclient.cdn().domains().getOriginHost("xxxxxxx", params);
        System.out.println(originHost);
        System.out.println("domain getOriginHost success...");

        //list
        params.put("page_size", "10");
        params.put("page_number", "1");
        Domain.Domains domains = osclient.cdn().domains().list(params);
        System.out.println(domains.getList());

        //query historyTasks
        Task.Tasks tasks = osclient.cdn().domains().queryTasks(params);
        System.out.println(tasks.getList());
        System.out.println("QueryHistoryTasks success...");

        // query historyTasksDetail
        TaskDetail taskDetail = osclient.cdn().domains().getTaskDetail("xxxxxxx", params);
        System.out.println(taskDetail);
        System.out.println("QueryTaskDetail success...");

        //disable domain:domain names that are not blocked and locked can be disabled if they are deactivated or configured to fail
        domain = osclient.cdn().domains().disable("xxxxxxx", params);
        System.out.println(domain);
        System.out.println("domain disable success...");

        //enable domain:domain names that are not blocked and locked can be disabled if they are deactivated or configured to fail
        domain = osclient.cdn().domains().enable("xxxxxxx", params);
        System.out.println(domain);
        System.out.println("domain enable success...");

        //delete domain:deactivate only, configuration fails, audit failed, and the failed domain name can be deleted
        domain = osclient.cdn().domains().delete("xxxxxxx", params);
        System.out.println(domain);
        System.out.println("domain delecte success...");
    }
}
