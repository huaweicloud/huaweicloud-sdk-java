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

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Bandwidth;
import com.huawei.openstack4j.openstack.cdn.v1.domain.BandwidthDetail;
import com.huawei.openstack4j.openstack.cdn.v1.domain.DomainConsumption;
import com.huawei.openstack4j.openstack.cdn.v1.domain.DomainSummary;
import com.huawei.openstack4j.openstack.cdn.v1.domain.DomainSummaryDetail;
import com.huawei.openstack4j.openstack.cdn.v1.domain.Flux;
import com.huawei.openstack4j.openstack.cdn.v1.domain.FluxDetail;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Cdn/Statistic", enabled = true)
public class StatisticServiceTest extends AbstractTest {

	@Test
	public void testQueryTotalNetworkTraffic() throws IOException, InterruptedException {
		respondWith("/cdn/query_total_network_traffic_response.json");

		Long queryDate = new Date().getTime();
		Flux flux = osv3().cdn().statistics().queryTotalNetworkTraffic("www.huawei.com", queryDate, queryDate,null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/statistics/flux?start_time="+queryDate+"&domain_name=www.huawei.com&end_time="+queryDate+"");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(flux.getEndTime(), Long.valueOf(1502380500000L));
		Assert.assertEquals(flux.getStartTime(), Long.valueOf(1498838400000L));
		Assert.assertEquals(flux.getValue(), Long.valueOf(835038583L));
	}

	@Test
	public void testQueryDetailsOfNetworkTraffic() throws IOException, InterruptedException {
		respondWith("/cdn/query_details_of_network_traffic_response.json");

		FluxDetail fluxDetail = osv3().cdn().statistics().queryDetailsOfNetworkTraffic("www.huawei.com",null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/statistics/flux-detail?domain_name=www.huawei.com");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(fluxDetail.getEndTime(), Long.valueOf(1502380500000L));
		Assert.assertEquals(fluxDetail.getStartTime(), Long.valueOf(1498838400000L));
		Assert.assertEquals(fluxDetail.getValues().size(), 2);
	}

	@Test
	public void testQueryPeakBandwidth() throws IOException, InterruptedException {
		respondWith("/cdn/query_peak_bandwidth_response.json");

		Bandwidth bandwidth = osv3().cdn().statistics().queryPeakBandwidth("www.huawei.com",null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/statistics/bandwidth?domain_name=www.huawei.com");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(bandwidth.getEndTime(), Long.valueOf(1502380500000L));
		Assert.assertEquals(bandwidth.getValue(), Long.valueOf(835038583L));
	}

	@Test
	public void testQueryDetailsOfNetworkBandwidth() throws IOException, InterruptedException {
		respondWith("/cdn/query_bandwidth_detail_response.json");

		BandwidthDetail bandwidthDetail = osv3().cdn().statistics().queryDetailsOfNetworkBandwidth("www.huawei.com",null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/statistics/bandwidth-detail?domain_name=www.huawei.com");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(bandwidthDetail.getEndTime(), Long.valueOf(1502380500000L));
		Assert.assertEquals(bandwidthDetail.getValues().get(0), Long.valueOf(835038583L));
		Assert.assertEquals(bandwidthDetail.getInterval(),Integer.valueOf(300));
	}

	@Test
	public void testQueryConsumptionSummary() throws IOException, InterruptedException {
		respondWith("/cdn/query_consumption_summary_response.json");

		DomainSummary domainSummary = osv3().cdn().statistics().queryConsumptionSummary("www.huawei.com", "flux_hit_rate",null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/statistics/domain-summary?domain_name=www.huawei.com&stat_type=flux_hit_rate");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(domainSummary.getEndTime(), Long.valueOf(1513180799346L));
		Assert.assertEquals(domainSummary.getValue(), Long.valueOf(835038583L));
		Assert.assertEquals(domainSummary.getServiceArea(),"mainland_china");
	}

	@Test
	public void testQueryConsumptionSummaryDetails() throws IOException, InterruptedException {
		respondWith("/cdn/query_consumption_summary_detail_response.json");

		DomainSummaryDetail domainSummaryDetail = osv3().cdn().statistics().queryConsumptionSummaryDetails("www.huawei.com", "bs_flux",null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/statistics/domain-summary-detail?domain_name=www.huawei.com&stat_type=bs_flux");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(domainSummaryDetail.getEndTime(), Long.valueOf(1502380500000L));
		Assert.assertEquals(domainSummaryDetail.getValues().size(), 2);
		Assert.assertEquals(domainSummaryDetail.getServiceArea(),"outside_mainland_china");
	}

	@Test
	public void testQueryDomainConsumptions() throws IOException, InterruptedException {
		respondWith("/cdn/query_domain_consumptions_response.json");

		List<DomainConsumption> domainConsumptions = osv3().cdn().statistics().queryDomainConsumptions("s4.donyd.com", "flux",null);
		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/cdn/statistics/domain?domain_name=s4.donyd.com&stat_type=flux");
		Assert.assertEquals(request.getMethod(), "GET");

		DomainConsumption domainConsumption = domainConsumptions.get(0);
		Assert.assertEquals(domainConsumption.getServiceArea(), "mainland_china");
		Assert.assertEquals(domainConsumptions.size(), 2);
		Assert.assertEquals(domainConsumption.getStartTime(),Long.valueOf(1513094400000L));
	}

	@Override
	protected Service service() {
		return Service.CDN;
	}

}
