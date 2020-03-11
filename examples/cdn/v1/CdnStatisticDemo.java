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

import java.util.List;

public class CdnStatisticDemo {

    public static void main(String args[]) {
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

        // set domainName
        String domainName = "example.domain.xxx";
        // set statType
        String statType = "flux";
        // set startTime and endTime
        long startTime = 1575907200000L;
        long endTime = startTime + 24 * 60 * 60 * 1000;
        // set interval
        int interval = 3600;
        // set region & isp
        String query_region = "ALL";
        String query_isp = "CTCC";
        // set area
        String serviceArea = null;
        // set enterprise_project_id or set is null or set is all
        String enterpriseProjectId = null;

        // new version API
        // Querying Details About Top 100 URLs
        TopUrl topurls = osclient.cdn().statistics().queryTopUrl(domainName, statType, startTime, endTime, serviceArea, enterpriseProjectId);
        System.out.println(topurls);

        // Querying Statistics About Domain Names in Batches
        DomainItemDetails domainItem = osclient.cdn().statistics().queryDomainItemDetails(domainName, statType, startTime, endTime, enterpriseProjectId);
        System.out.println(domainItem);

        // Querying Statistics About Domain Names by Region and Carrier in Batches
        DomainItemLocationDetails domainItemLocation = osclient.cdn().statistics().queryDomainItemLocationDetails(domainName, statType, startTime, endTime, query_region, query_isp, enterpriseProjectId);
        System.out.println(domainItemLocation);

        // old version API
        // Querying the Total Network Traffic
        Flux flux = osclient.cdn().statistics().queryTotalNetworkTraffic(domainName, startTime, endTime, enterpriseProjectId);
        System.out.println(flux);

        // Querying Details of Network Traffic
        FluxDetail fluxDetail = osclient.cdn().statistics().queryDetailsOfNetworkTraffic(domainName, startTime, endTime, interval, enterpriseProjectId);
        System.out.println(fluxDetail);

        // Querying the Peak Bandwidth Value
        Bandwidth bandwidth = osclient.cdn().statistics().queryPeakBandwidth(domainName, startTime, endTime, enterpriseProjectId);
        System.out.println(bandwidth);

        // Querying Details of the Network Bandwidth
        BandwidthDetail bandwidthDetail = osclient.cdn().statistics().queryDetailsOfNetworkBandwidth(domainName, startTime, endTime, interval, enterpriseProjectId);
        System.out.println(bandwidthDetail);

        // Querying Consumption Summary
        DomainSummary domainSummary = osclient.cdn().statistics().queryConsumptionSummary(domainName, statType, startTime, endTime, serviceArea, enterpriseProjectId);
        System.out.println(domainSummary);

        // Querying Consumption Details
        DomainSummaryDetail domainSummaryDetail = osclient.cdn().statistics().queryConsumptionSummaryDetails(domainName, statType, startTime, endTime, serviceArea, interval, enterpriseProjectId);
        System.out.println(domainSummaryDetail);

        // Querying Consumption of Each Domain Name
        List<DomainConsumption> domainConsumptions = osclient.cdn().statistics().queryDomainConsumptions(domainName, statType, startTime, endTime, serviceArea, enterpriseProjectId);
        System.out.println(domainConsumptions);

        // Querying Domain Consumption by Region
        RegionDetailSummary regiondata = osclient.cdn().statistics().queryRegionDetailSummary(domainName, statType, query_region, startTime, endTime, enterpriseProjectId);
        System.out.println(regiondata);

        // Querying Domain Consumption by Carrier
        CarrierDetailSummary crrierdata = osclient.cdn().statistics().queryCarrierDetailSummary(domainName, statType, query_isp, startTime, endTime, enterpriseProjectId);
        System.out.println(crrierdata);

        // Querying Statistics About Each Domain Name Under a Region or Carrier
        RegionCarrierDomain rcd = osclient.cdn().statistics().queryRegionCarrierDomain(domainName, statType, query_region, query_isp, startTime, endTime, enterpriseProjectId);
        System.out.println(rcd);

        // Querying Statistics About Domain Names Under a Region or Carrier
        RegionCarrierDetail rcdetail = osclient.cdn().statistics().queryRegionCarrierDetail(domainName, statType, query_region, query_isp, startTime, endTime, interval, enterpriseProjectId);
        System.out.println(rcdetail);

    }
}
