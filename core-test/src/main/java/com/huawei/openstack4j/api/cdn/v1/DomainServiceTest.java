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
package com.huawei.openstack4j.api.cdn.v1;

import okhttp3.mockwebserver.RecordedRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.cdn.v1.domain.CacheConfig;
import com.huawei.openstack4j.openstack.cdn.v1.domain.CacheRule;
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
import com.huawei.openstack4j.openstack.cdn.v1.domain.Source;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Source.Origin;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Task.Tasks;
import com.huawei.openstack4j.openstack.cdn.v1.domain.TaskDetail;
import com.huawei.openstack4j.openstack.cdn.v1.exception.ServerCdnErrorResponseException;

@Test(suiteName = "Cdn/Domain", enabled = true)
public class DomainServiceTest extends AbstractTest {

	@Test
	public void testListDomains() throws IOException, InterruptedException {
		respondWith("/cdn/domains_list.json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("page_size", "10");
		params.put("page_number", "1");

		Domains domains = osv3().cdn().domains().list(params);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains?page_number=1&page_size=10");
		Assert.assertEquals(request.getMethod(), "GET");
		Assert.assertEquals(domains.getList().size(), 2);

		Domain domain = domains.getList().get(0);
		Assert.assertEquals(domain.getDomainName(), "hec3.donyd.com");
		Assert.assertEquals(domain.getSources().get(0).getDomainId(), "8abe97de604d998f01604e7490130000");
		Assert.assertEquals(domain.getDomainOriginHost().getCustomizeDomain(), "test961.donyd.com");
	}

	@Test
	public void testCreateDomain() throws IOException, InterruptedException {
		respondWith("/cdn/get_domain_response.json");

		Source source = Source.builder().ipOrDomain("1.2.3.4").originType("ipaddr").activeStandby(1).build();
		List<Source> sources = new ArrayList<Source>();
		sources.add(source);
		DomainCreate creation = DomainCreate.builder().domainName("cdn-9b234.xxx.yyy.com")
				.businessType("web").sources(sources).build();

		Domain domain = osv3().cdn().domains().create(creation);

		Assert.assertEquals(domain.getDomainName(), "cdn-9b234.xxx.yyy.com");
		Assert.assertEquals(domain.getSources().get(0).getIpOrDomain(), "1.2.3.4");
		Assert.assertEquals(domain.getDomainOriginHost().getCustomizeDomain(), "test961.donyd.com");
	}

	@Test
	public void testDeleteDomain() throws IOException, InterruptedException {
		respondWith("/cdn/get_domain_response.json");
		Domain domain = osv3().cdn().domains().delete("ff80808260454e2601604eece675107b", null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertEquals(domain.getDomainName(), "cdn-9b234.xxx.yyy.com");
		Assert.assertEquals(domain.getSources().get(0).getIpOrDomain(), "1.2.3.4");
		Assert.assertEquals(domain.getDomainOriginHost().getCustomizeDomain(), "test961.donyd.com");
	}

	@Test
	public void testEnableDomain() throws IOException, InterruptedException {
		respondWith("/cdn/get_domain_response.json");
		osv3().cdn().domains().enable("ff80808260454e2601604eece675107b", null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b/enable");
		Assert.assertEquals(request.getMethod(), "PUT");
	}

	@Test
	public void testDisableDomain() throws IOException, InterruptedException {
		respondWith("/cdn/get_domain_response.json");
		osv3().cdn().domains().disable("ff80808260454e2601604eece675107b", null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b/disable");
		Assert.assertEquals(request.getMethod(), "PUT");
	}

	@Test
	public void testDomainDetail() throws IOException, InterruptedException {
		respondWith("/cdn/get_domain_response.json");
		Domain domain = osv3().cdn().domains().getDetail("ff80808260454e2601604eece675107b", null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b/detail");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(domain.getDomainName(), "cdn-9b234.xxx.yyy.com");
		Assert.assertEquals(domain.getSources().get(0).getIpOrDomain(), "1.2.3.4");
		Assert.assertEquals(domain.getDomainOriginHost().getCustomizeDomain(), "test961.donyd.com");
	}

	@Test
	public void testSetOrigin() throws IOException, InterruptedException {
		respondWith("/cdn/set_origin_response.json");

		Source source = Source.builder().ipOrDomain("1.2.3.4").originType("ipaddr").activeStandby(1).build();
		List<Source> sources = new ArrayList<Source>();
		sources.add(source);
		Origin neworigin = Origin.builder().sources(sources).build();
		Origin origin = osv3().cdn().domains().setOrigin("8abe97de604d998f01604e7490130000", neworigin, null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/8abe97de604d998f01604e7490130000/origin");
		Assert.assertEquals(request.getMethod(), "PUT");

		Assert.assertEquals(origin.getSources().get(0).getDomainId(), "8abe97de604d998f01604e7490130000");
		Assert.assertEquals(origin.getSources().get(0).getIpOrDomain(), "1.2.3.4");
	}

	@Test
	public void testGetOriginHost() throws IOException, InterruptedException {
		respondWith("/cdn/get_origin_host_response.json");
		OriginHost originHost = osv3().cdn().domains().getOriginHost("ff80808260454e2601604eece675107b", null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b/originhost");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(originHost.getDomainId(), "ff80808260454e2601604eece675107b");
	}

	@Test
	public void testSetOriginHost() throws IOException, InterruptedException {
		respondWith("/cdn/get_origin_host_response.json");
		OriginHost newOriginHost = OriginHost.builder().customizeDomain("cdn-9b234.xxx.yyy.com")
				.originHostType("customize").build();
		OriginHost originHost = osv3().cdn().domains().setOriginHost("ff80808260454e2601604eece675107b", newOriginHost, null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b/originhost");
		Assert.assertEquals(request.getMethod(), "PUT");

		Assert.assertEquals(originHost.getDomainId(), "ff80808260454e2601604eece675107b");
	}

	@Test
	public void testGetReferer() throws IOException, InterruptedException {
		respondWith("/cdn/get_and_set_referer.json");
		Referer referer = osv3().cdn().domains().getReferer("ff80808260454e2601604eece675107b", null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b/referer");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(referer.getRefererList(), "www.xxx.com;www.xxx2.com");
	}

	@Test
	public void testSetReferer() throws IOException, InterruptedException {
		respondWith("/cdn/get_and_set_referer.json");
		Referer newReferfer = Referer.builder().refererList("www.xxx.com;www.xxx2.com").refererType(1)
				.includeEmpty(false).build();
		Referer referer = osv3().cdn().domains().setReferer("ff80808260454e2601604eece675107b", newReferfer, null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b/referer");
		Assert.assertEquals(request.getMethod(), "PUT");

		Assert.assertEquals(referer.getIncludeEmpty(), Boolean.FALSE);
	}

	@Test
	public void testGetCacheConfig() throws IOException, InterruptedException {
		respondWith("/cdn/get_cache_config_response.json");
		CacheConfig cacheConfig = osv3().cdn().domains().getCacheConfig("ff80808260454e2601604eece675107b", null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b/cache");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(cacheConfig.getRules().get(0).getContent(), ".jpg;.png");
		Assert.assertEquals(cacheConfig.getIgnoreCacheControl(), Boolean.TRUE);
	}

	@Test
	public void testSetCacheConfig() throws IOException, InterruptedException {
		respondWith("/cdn/get_cache_config_response.json");
		CacheRule rule = CacheRule.builder().content(".jpg;.png").rule_type(1).ttl(2).ttlType(3).priority(1).build();
		List<CacheRule> rules = new ArrayList<CacheRule>();
		rules.add(rule);
		CacheConfig newCacheConfig = CacheConfig.builder().ignoreUrlParameter(false).rules(rules).build();
		CacheConfig cacheConfig = osv3().cdn().domains().setCacheConfig("ff80808260454e2601604eece675107b",
				newCacheConfig, null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b/cache");
		Assert.assertEquals(request.getMethod(), "PUT");

		Assert.assertEquals(cacheConfig.getIgnoreUrlParameter(), Boolean.FALSE);
		Assert.assertEquals(cacheConfig.getRules().get(0).getContent(), ".jpg;.png");
	}

	@Test
	public void testGetHttpsInfo() throws IOException, InterruptedException {
		respondWith("/cdn/get_httpsinfo_response.json");
		HttpsInfo httpsInfo = osv3().cdn().domains().getHttpsInfo("ff80808260454e2601604eece675107b", null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b/https-info");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(httpsInfo.getCertName(), "cdn_test_cert");
		Assert.assertEquals(httpsInfo.getForceRedirectHttps(), Integer.valueOf(0));
	}

	@Test
	public void testSetHttpsInfo() throws IOException, InterruptedException {
		respondWith("/cdn/get_httpsinfo_response.json");
		HttpsInfo newHttpsInfo = HttpsInfo.builder().certificate("-----BEGIN CERTIFICATE----------END CERTIFICATE-----")
				.certName("cdn_test_cert").httpsStatus(2)
				.privateKey("-----BEGIN RSA PRIVATE KEY---------END RSA PRIVATE KEY-----").forceRedirectHttps(0)
				.build();
		HttpsInfo httpsInfo = osv3().cdn().domains().setHttpsInfo("ff80808260454e2601604eece675107b", newHttpsInfo, null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b/https-info");
		Assert.assertEquals(request.getMethod(), "PUT");

		Assert.assertEquals(httpsInfo.getCertName(), "cdn_test_cert");
		Assert.assertEquals(httpsInfo.getForceRedirectHttps(), Integer.valueOf(0));
	}

	@Test
	public void testCreateRefreshTask() throws IOException, InterruptedException {
		respondWith("/cdn/create_refreshtask_response.json");
		List<String> urls = new ArrayList<String>();
		urls.add("https://www.xxx.com/test.jpg");
		urls.add("http://www.xxx.com/test2.jpg");
		RefreshTaskCreate creation = RefreshTaskCreate.builder().type("file").urls(urls).build();
		RefreshTask refreshTask = osv3().cdn().domains().createRefreshTask(creation, null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/refreshtasks");
		Assert.assertEquals(request.getMethod(), "POST");

		Assert.assertEquals(refreshTask.getTaskType(), "refresh");
		Assert.assertEquals(refreshTask.getUrls().size(), 2);
	}

	@Test
	public void testCreatePreheatingTask() throws IOException, InterruptedException {
		respondWith("/cdn/create_preheatingtask_response.json");
		List<String> urls = new ArrayList<String>();
		urls.add("http://www.xxx.com/test15.jpg");
		urls.add("https://www.xxx.cn/test/di.png");
		urls.add("http://www.xxx.com/test17/test.doc");
		urls.add("http://www.xxx.com/test16.jpg");
		PreheatingTaskCreate creation = PreheatingTaskCreate.builder().urls(urls).build();
		PreheatingTask preheatingTask = osv3().cdn().domains().createPreheatingTask(creation, null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/preheatingtasks");
		Assert.assertEquals(request.getMethod(), "POST");

		Assert.assertEquals(preheatingTask.getTaskType(), "preheating");
		Assert.assertEquals(preheatingTask.getUrls().size(), 4);
	}

	@Test
	public void testQueryTasks() throws IOException, InterruptedException {
		respondWith("/cdn/query_tasks_response.json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("page_size", "10");
		params.put("page_number", "1");
		Tasks tasks = osv3().cdn().domains().queryTasks(params);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/historytasks?page_number=1&page_size=10");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(tasks.getTotal(), Integer.valueOf(2));
		Assert.assertEquals(tasks.getList().get(0).getCreateTime(), Long.valueOf(1499157542180L));
		Assert.assertEquals(tasks.getList().get(0).getUrls().get(0), "https://www.xxx.com/test.jpg");
	}

	@Test
	public void testgetTaskDetail() throws IOException, InterruptedException {
		respondWith("/cdn/get_task_detail_response.json");

		Map<String, String> params = new HashMap<String, String>();
		params.put("page_size", "10");
		params.put("page_number", "1");
		params.put("enterprise_project_id", null);
		TaskDetail taskDetail = osv3().cdn().domains().getTaskDetail("8a29d3f05d0cbeee015d0cc1333c0007", params);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(),
				"/v1.0/cdn/historytasks/8a29d3f05d0cbeee015d0cc1333c0007/detail?page_number=1&page_size=10");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(taskDetail.getTaskType(), "preheating");
		Assert.assertEquals(taskDetail.getUrls().get(0).getId(), "8a29d3f05d0cbeee015d0cc133440009");
	}

	@Test
	public void testCdnErrorResponse() throws IOException, InterruptedException {
		respondWith("/cdn/cdn_error_response.json");
		try {
			osv3().cdn().domains().delete("ff80808260454e2601604eece675107b", null);
		} catch (ServerCdnErrorResponseException exception) {
			Assert.assertEquals(exception.getErrorCode(), "CDN.0103");
			Assert.assertEquals(exception.getMessage(), "Information about the origin server format is incorrect.");
		}
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/domains/ff80808260454e2601604eece675107b");
		Assert.assertEquals(request.getMethod(), "DELETE");
	}

	@Override
	protected Service service() {
		return Service.CDN;
	}

}
