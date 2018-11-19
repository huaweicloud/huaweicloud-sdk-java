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
package com.huawei.openstack.sample.statistic;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.cdn.v1.domain.*;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

import java.util.List;

public class CdnStatisticDemo
{

    public static void main(String args[])
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

        //set enterprise_project_id or set is null or set is all
        String enterpriseProjectId = "e451b831-6a62-4cd7-87bb-82fc38e1dad4";

        //set domainName
        String domainName = "example.domain.xxx";
        //set statType
        String fluxStatType = "flux";

        //set area
        String area = "xxxxxx";
        String outside_area = "xxxxxx";
        //set startTime and endTime
        long endTime = 1537200000000L + 7 * 24 * 60 * 60 * 1000;
        long startTime = 1537200000000L;

        int interval = 3600;

        //get  queryConsumptionSummary
        DomainSummary domainSummary = osclient.cdn()
                .statistics()
                .queryConsumptionSummary(domainName,
                        fluxStatType,
                        startTime,
                        endTime,
                        area,
                        enterpriseProjectId);
        System.out.println(domainSummary);

        // get queryConsumptionSummaryDetails
        DomainSummaryDetail domainSummaryDetail = osclient.cdn()
                .statistics()
                .queryConsumptionSummaryDetails(domainName,
                        fluxStatType,
                        startTime,
                        endTime,
                        area,
                        interval,
                        enterpriseProjectId);
        System.out.println(domainSummaryDetail);

        //get queryDetailsOfNetworkBandwidth
        BandwidthDetail bandwidthDetail = osclient.cdn()
                .statistics()
                .queryDetailsOfNetworkBandwidth(domainName, startTime, endTime, interval, enterpriseProjectId);
        System.out.println(bandwidthDetail);

        // get queryDetailsOfNetworkTraffic
        FluxDetail fluxDetail = osclient.cdn()
                .statistics()
                .queryDetailsOfNetworkTraffic(domainName, startTime, endTime, interval, enterpriseProjectId);
        System.out.println(fluxDetail);

        //get list queryDomainConsumptions
        List<DomainConsumption> domainConsumptions = osclient.cdn()
                .statistics()
                .queryDomainConsumptions(domainName, fluxStatType, startTime, endTime, area, enterpriseProjectId);
        System.out.println(domainConsumptions);

        //show queryPeakBandwidth
        Bandwidth bandwidth = osclient.cdn().statistics().queryPeakBandwidth(domainName, startTime, endTime, enterpriseProjectId);
        System.out.println(bandwidth);

        //show queryTotalNetworkTraffic
        Flux flux = osclient.cdn().statistics().queryTotalNetworkTraffic(domainName, startTime, endTime, enterpriseProjectId);
        System.out.println(flux);
    }
}
