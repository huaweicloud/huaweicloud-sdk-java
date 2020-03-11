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

import java.util.List;

import com.google.common.base.Preconditions;
import com.huawei.openstack4j.openstack.cdn.v1.domain.*;
import com.huawei.openstack4j.openstack.cdn.v1.domain.DomainConsumption.DomainConsumptions;
import com.huawei.openstack4j.openstack.cdn.v1.exception.ServerCdnErrorResponseException;

/**
 * CDN Statistics Service
 */
public class StatisticService extends BaseCdnServices {

    /**
     * Querying the Total Network Traffic
     *
     * @param domainName domain name
     * @return {@link Flux} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Flux queryTotalNetworkTraffic(String domainName, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        return queryTotalNetworkTraffic(domainName, null, null, enterpriseProjectId);
    }

    /**
     * Querying the Total Network Traffic
     *
     * @param domainName domain name
     * @param startTime  start time
     * @param endTime    end time
     * @return {@link Flux} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Flux queryTotalNetworkTraffic(String domainName, Long startTime, Long endTime, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Invocation<Flux> fluxInvocation = get(Flux.class, uri("/statistics/flux")).param("domain_name", domainName);
        if (startTime != null) {
            fluxInvocation = fluxInvocation.param("start_time", startTime);
        }

        if (endTime != null) {
            fluxInvocation = fluxInvocation.param("end_time", endTime);
        }

        if (enterpriseProjectId != null) {
            fluxInvocation = fluxInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return fluxInvocation.execute(this.buildExecutionOptions(Flux.class));
    }

    /**
     * Querying Details of Network Traffic
     *
     * @param domainName domain name
     * @return {@link FluxDetail} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public FluxDetail queryDetailsOfNetworkTraffic(String domainName, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        return queryDetailsOfNetworkTraffic(domainName, null, null, null, enterpriseProjectId);
    }

    /**
     * Querying Details of Network Traffic
     *
     * @param domainName domain name
     * @param startTime  start time
     * @param endTime    end time
     * @param interval   interval
     * @return {@link FluxDetail} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public FluxDetail queryDetailsOfNetworkTraffic(String domainName, Long startTime, Long endTime, Integer interval, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Invocation<FluxDetail> fluxDetailInvocation = get(FluxDetail.class, uri("/statistics/flux-detail"))
                .param("domain_name", domainName);
        if (startTime != null) {
            fluxDetailInvocation = fluxDetailInvocation.param("start_time", startTime);
        }
        if (endTime != null) {
            fluxDetailInvocation = fluxDetailInvocation.param("end_time", endTime);
        }
        if (interval != null) {
            fluxDetailInvocation = fluxDetailInvocation.param("interval", interval);
        }
        if (enterpriseProjectId != null) {
            fluxDetailInvocation = fluxDetailInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return fluxDetailInvocation.execute(this.buildExecutionOptions(FluxDetail.class));
    }

    /**
     * Querying the Peak Bandwidth Value
     *
     * @param domainName domain name
     * @return {@link Bandwidth} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Bandwidth queryPeakBandwidth(String domainName, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        return queryPeakBandwidth(domainName, null, null, enterpriseProjectId);
    }

    /**
     * Querying the Peak Bandwidth Value
     *
     * @param domainName domain name
     * @param startTime  start time
     * @param endTime    end time
     * @return {@link Bandwidth} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public Bandwidth queryPeakBandwidth(String domainName, Long startTime, Long endTime, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Invocation<Bandwidth> bandwidthInvocation = get(Bandwidth.class, uri("/statistics/bandwidth"))
                .param("domain_name", domainName);
        if (startTime != null) {
            bandwidthInvocation = bandwidthInvocation.param("start_time", startTime);
        }

        if (endTime != null) {
            bandwidthInvocation = bandwidthInvocation.param("end_time", endTime);
        }

        if (enterpriseProjectId != null) {
            bandwidthInvocation = bandwidthInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return bandwidthInvocation.execute(this.buildExecutionOptions(Bandwidth.class));
    }

    /**
     * Querying Details of the Network Bandwidth
     *
     * @param domainName domain name
     * @return {@link BandwidthDetail} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public BandwidthDetail queryDetailsOfNetworkBandwidth(String domainName, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        return queryDetailsOfNetworkBandwidth(domainName, null, null, null, enterpriseProjectId);
    }

    /**
     * Querying Details of the Network Bandwidth
     *
     * @param domainName domain name
     * @param startTime  start time
     * @param endTime    end time
     * @param interval   interval
     * @return {@link BandwidthDetail} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public BandwidthDetail queryDetailsOfNetworkBandwidth(String domainName, Long startTime, Long endTime,
                                                          Integer interval, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Invocation<BandwidthDetail> bandwidthDetailInvocation = get(BandwidthDetail.class,
                uri("/statistics/bandwidth-detail")).param("domain_name", domainName);
        if (startTime != null) {
            bandwidthDetailInvocation = bandwidthDetailInvocation.param("start_time", startTime);
        }
        if (endTime != null) {
            bandwidthDetailInvocation = bandwidthDetailInvocation.param("end_time", endTime);
        }
        if (interval != null) {
            bandwidthDetailInvocation = bandwidthDetailInvocation.param("interval", interval);
        }
        if (enterpriseProjectId != null) {
            bandwidthDetailInvocation = bandwidthDetailInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return bandwidthDetailInvocation.execute(this.buildExecutionOptions(BandwidthDetail.class));
    }

    /**
     * Querying Consumption Summary
     *
     * @param domainName domain name
     * @param statType   stat type
     * @return {@link DomainSummary} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public DomainSummary queryConsumptionSummary(String domainName, String statType, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        return queryConsumptionSummary(domainName, statType, null, null, null, enterpriseProjectId);
    }

    /**
     * Querying Consumption Summary
     *
     * @param domainName  domain name
     * @param statType    stat type
     * @param startTime   start time
     * @param endTime     end time
     * @param serviceArea service area
     * @return {@link DomainSummary} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public DomainSummary queryConsumptionSummary(String domainName, String statType, Long startTime, Long endTime,
                                                 String serviceArea, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Preconditions.checkNotNull(statType, "parameter `statType` should not be null");
        Invocation<DomainSummary> domainSummaryInvocation = get(DomainSummary.class,
                uri("/statistics/domain-summary")).param("domain_name", domainName).param("stat_type", statType);
        if (startTime != null) {
            domainSummaryInvocation = domainSummaryInvocation.param("start_time", startTime);
        }
        if (endTime != null) {
            domainSummaryInvocation = domainSummaryInvocation.param("end_time", endTime);
        }
        if (serviceArea != null) {
            domainSummaryInvocation = domainSummaryInvocation.param("service_area", serviceArea);
        }
        if (enterpriseProjectId != null) {
            domainSummaryInvocation = domainSummaryInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return domainSummaryInvocation.execute(this.buildExecutionOptions(DomainSummary.class));
    }

    /**
     * Querying Consumption Details
     *
     * @param domainName domain name
     * @param statType   stat type
     * @return {@link DomainSummaryDetail} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public DomainSummaryDetail queryConsumptionSummaryDetails(String domainName, String statType, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        return queryConsumptionSummaryDetails(domainName, statType, null, null, null, null, enterpriseProjectId);
    }

    /**
     * Querying Consumption Details
     *
     * @param domainName  domain name
     * @param statType    stat type
     * @param startTime   start time
     * @param endTime     end time
     * @param serviceArea service area
     * @param interval    interval
     * @return {@link DomainSummaryDetail} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public DomainSummaryDetail queryConsumptionSummaryDetails(String domainName, String statType, Long startTime, Long endTime,
                                                              String serviceArea, Integer interval, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Preconditions.checkNotNull(statType, "parameter `statType` should not be null");
        Invocation<DomainSummaryDetail> domainSummaryDetailsInvocation = get(DomainSummaryDetail.class,
                uri("/statistics/domain-summary-detail")).param("domain_name", domainName).param("stat_type", statType);
        if (startTime != null) {
            domainSummaryDetailsInvocation = domainSummaryDetailsInvocation.param("start_time", startTime);
        }
        if (endTime != null) {
            domainSummaryDetailsInvocation = domainSummaryDetailsInvocation.param("end_time", endTime);
        }
        if (serviceArea != null) {
            domainSummaryDetailsInvocation = domainSummaryDetailsInvocation.param("service_area", serviceArea);
        }
        if (interval != null) {
            domainSummaryDetailsInvocation = domainSummaryDetailsInvocation.param("interval", interval);
        }
        if (enterpriseProjectId != null) {
            domainSummaryDetailsInvocation = domainSummaryDetailsInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return domainSummaryDetailsInvocation.execute(this.buildExecutionOptions(DomainSummaryDetail.class));
    }

    /**
     * Querying Consumption of Each Domain Name
     *
     * @param domainName domain name
     * @param statType   stat type
     * @return list of {@link DomainConsumption} instances
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public List<DomainConsumption> queryDomainConsumptions(String domainName, String statType, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        return queryDomainConsumptions(domainName, statType, null, null, null, enterpriseProjectId);
    }

    /**
     * Querying Consumption of Each Domain Name
     *
     * @param domainName  domain name
     * @param statType    stat type
     * @param startTime   start time
     * @param endTime     end time
     * @param serviceArea service area
     * @return list of {@link DomainConsumption} instances
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public List<DomainConsumption> queryDomainConsumptions(String domainName, String statType, Long startTime, Long endTime,
                                                           String serviceArea, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Preconditions.checkNotNull(statType, "parameter `statType` should not be null");
        Invocation<DomainConsumptions> domainConsumptionsInvocation = get(DomainConsumptions.class,
                uri("/statistics/domain")).param("domain_name", domainName).param("stat_type", statType);
        if (startTime != null) {
            domainConsumptionsInvocation = domainConsumptionsInvocation.param("start_time", startTime);
        }
        if (endTime != null) {
            domainConsumptionsInvocation = domainConsumptionsInvocation.param("end_time", endTime);
        }
        if (serviceArea != null) {
            domainConsumptionsInvocation = domainConsumptionsInvocation.param("service_area", serviceArea);
        }
        if (enterpriseProjectId != null) {
            domainConsumptionsInvocation = domainConsumptionsInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return domainConsumptionsInvocation.execute(this.buildExecutionOptions(DomainConsumptions.class)).getDomainConsumptions();
    }

    /**
     * Querying Domain Item Details
     *
     * @param domainName          domain name
     * @param statType            stat type
     * @param startTime           start time
     * @param endTime             end time
     * @param enterpriseProjectId enterprise project ID
     * @return {@link DomainItemDetails} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public DomainItemDetails queryDomainItemDetails(String domainName, String statType, Long startTime,
                                                    Long endTime, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(startTime, "parameter `startTime` should not be null");
        Preconditions.checkNotNull(endTime, "parameter `endTime` should not be null");
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Preconditions.checkNotNull(statType, "parameter `statType` should not be null");
        Invocation<DomainItemDetails> domainItemDetailsInvocation = get(DomainItemDetails.class,
                uri("/statistics/domain-item-details")).param("domain_name", domainName).param("stat_type", statType).param("start_time", startTime).param("end_time", endTime);

        if (enterpriseProjectId != null) {
            domainItemDetailsInvocation = domainItemDetailsInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return domainItemDetailsInvocation.execute(this.buildExecutionOptions(DomainItemDetails.class));
    }


    /**
     * Querying Domain Item Loation Details
     *
     * @param domainName          domain name
     * @param statType            stat type
     * @param startTime           start time
     * @param endTime             end time
     * @param region              region
     * @param isp                 carrier
     * @param enterpriseProjectId enterprise project ID
     * @return {@link DomainItemLocationDetails} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public DomainItemLocationDetails queryDomainItemLocationDetails(String domainName, String statType, Long startTime,
                                                                    Long endTime, String region, String isp, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(startTime, "parameter `startTime` should not be null");
        Preconditions.checkNotNull(endTime, "parameter `endTime` should not be null");
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Preconditions.checkNotNull(statType, "parameter `statType` should not be null");
        Preconditions.checkNotNull(region, "parameter `region` should not be null");
        Preconditions.checkNotNull(isp, "parameter `isp` should not be null");
        Invocation<DomainItemLocationDetails> domainItemLocationDetailsInvocation = get(DomainItemLocationDetails.class,
                uri("/statistics/domain-item-location-details")).param("domain_name", domainName).param("stat_type", statType).param("start_time", startTime).param("end_time", endTime).param("region", region).param("isp", isp);

        if (enterpriseProjectId != null) {
            domainItemLocationDetailsInvocation = domainItemLocationDetailsInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return domainItemLocationDetailsInvocation.execute(this.buildExecutionOptions(DomainItemLocationDetails.class));
    }

    /**
     * Querying Region Detail Summary
     *
     * @param domainName          domain name
     * @param statType            stat type
     * @param startTime           start time
     * @param endTime             end time
     * @param region              region
     * @param enterpriseProjectId enterprise project ID
     * @return {@link RegionDetailSummary} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public RegionDetailSummary queryRegionDetailSummary(String domainName, String statType, String region, Long startTime,
                                                        Long endTime, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Preconditions.checkNotNull(statType, "parameter `statType` should not be null");
        Preconditions.checkNotNull(region, "parameter `region` should not be null");
        Invocation<RegionDetailSummary> RegionDetailSummaryInvocation = get(RegionDetailSummary.class,
                uri("/statistics/region-detail-summary")).param("domain_name", domainName).param("stat_type", statType).param("region", region);
        if (startTime != null) {
            RegionDetailSummaryInvocation = RegionDetailSummaryInvocation.param("start_time", startTime);
        }
        if (endTime != null) {
            RegionDetailSummaryInvocation = RegionDetailSummaryInvocation.param("end_time", endTime);
        }
        if (enterpriseProjectId != null) {
            RegionDetailSummaryInvocation = RegionDetailSummaryInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return RegionDetailSummaryInvocation.execute(this.buildExecutionOptions(RegionDetailSummary.class));
    }

    /**
     * Querying Carrier Detail Summary
     *
     * @param domainName          domain name
     * @param statType            stat type
     * @param startTime           start time
     * @param endTime             end time
     * @param carrier             carrier
     * @param enterpriseProjectId enterprise project ID
     * @return {@link CarrierDetailSummary} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public CarrierDetailSummary queryCarrierDetailSummary(String domainName, String statType, String carrier, Long startTime,
                                                          Long endTime, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Preconditions.checkNotNull(statType, "parameter `statType` should not be null");
        Preconditions.checkNotNull(carrier, "parameter `carrier` should not be null");
        Invocation<CarrierDetailSummary> CarrierDetailSummaryInvocation = get(CarrierDetailSummary.class,
                uri("/statistics/carrier-detail-summary")).param("domain_name", domainName).param("stat_type", statType).param("carrier", carrier);
        if (startTime != null) {
            CarrierDetailSummaryInvocation = CarrierDetailSummaryInvocation.param("start_time", startTime);
        }
        if (endTime != null) {
            CarrierDetailSummaryInvocation = CarrierDetailSummaryInvocation.param("end_time", endTime);
        }
        if (enterpriseProjectId != null) {
            CarrierDetailSummaryInvocation = CarrierDetailSummaryInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return CarrierDetailSummaryInvocation.execute(this.buildExecutionOptions(CarrierDetailSummary.class));
    }

    /**
     * Querying Top Url
     *
     * @param domainName          domain name
     * @param statType            stat type
     * @param startTime           start time
     * @param endTime             end time
     * @param serviceArea         service area
     * @param enterpriseProjectId enterprise project ID
     * @return {@link TopUrl} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public TopUrl queryTopUrl(String domainName, String statType, Long startTime,
                              Long endTime, String serviceArea, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Preconditions.checkNotNull(statType, "parameter `statType` should not be null");
        Invocation<TopUrl> TopUrlInvocation = get(TopUrl.class, uri("/statistics/top-url")).param("domain_name", domainName).param("stat_type", statType);
        if (startTime != null) {
            TopUrlInvocation = TopUrlInvocation.param("start_time", startTime);
        }
        if (endTime != null) {
            TopUrlInvocation = TopUrlInvocation.param("end_time", endTime);
        }
        if (serviceArea != null) {
            TopUrlInvocation = TopUrlInvocation.param("service_area", serviceArea);
        }
        if (enterpriseProjectId != null) {
            TopUrlInvocation = TopUrlInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return TopUrlInvocation.execute(this.buildExecutionOptions(TopUrl.class));
    }

    /**
     * Querying Region Carrier Domain
     *
     * @param domainName          domain name
     * @param statType            stat type
     * @param region              region
     * @param carrier             carrier
     * @param startTime           start time
     * @param endTime             end time
     * @param enterpriseProjectId enterprise project ID
     * @return {@link RegionCarrierDomain} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public RegionCarrierDomain queryRegionCarrierDomain(String domainName, String statType, String region, String carrier, Long startTime,
                                                        Long endTime, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Preconditions.checkNotNull(statType, "parameter `statType` should not be null");
        Preconditions.checkNotNull(region, "parameter `region` should not be null");
        Preconditions.checkNotNull(carrier, "parameter `carrier` should not be null");
        Invocation<RegionCarrierDomain> RegionCarrierDomainInvocation = get(RegionCarrierDomain.class,
                uri("/statistics/region-carrier-domain")).param("domain_name", domainName).param("stat_type", statType).param("region", region).param("carrier", carrier);
        if (startTime != null) {
            RegionCarrierDomainInvocation = RegionCarrierDomainInvocation.param("start_time", startTime);
        }
        if (endTime != null) {
            RegionCarrierDomainInvocation = RegionCarrierDomainInvocation.param("end_time", endTime);
        }
        if (enterpriseProjectId != null) {
            RegionCarrierDomainInvocation = RegionCarrierDomainInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return RegionCarrierDomainInvocation.execute(this.buildExecutionOptions(RegionCarrierDomain.class));
    }

    /**
     * Querying Region Carrier Detail
     *
     * @param domainName          domain name
     * @param statType            stat type
     * @param region              region
     * @param carrier             carrier
     * @param startTime           start time
     * @param endTime             end time
     * @param interval            interval
     * @param enterpriseProjectId enterprise project ID
     * @return {@link RegionCarrierDetail} instance
     * @throws ServerCdnErrorResponseException CDN Exception
     */
    public RegionCarrierDetail queryRegionCarrierDetail(String domainName, String statType, String region, String carrier, Long startTime,
                                                        Long endTime, Integer interval, String enterpriseProjectId) throws ServerCdnErrorResponseException {
        Preconditions.checkNotNull(domainName, "parameter `domainName` should not be null");
        Preconditions.checkNotNull(statType, "parameter `statType` should not be null");
        Preconditions.checkNotNull(region, "parameter `region` should not be null");
        Preconditions.checkNotNull(carrier, "parameter `carrier` should not be null");
        Invocation<RegionCarrierDetail> RegionCarrierDetailInvocation = get(RegionCarrierDetail.class,
                uri("/statistics/region-carrier-detail")).param("domain_name", domainName).param("stat_type", statType).param("region", region).param("carrier", carrier);
        if (startTime != null) {
            RegionCarrierDetailInvocation = RegionCarrierDetailInvocation.param("start_time", startTime);
        }
        if (endTime != null) {
            RegionCarrierDetailInvocation = RegionCarrierDetailInvocation.param("end_time", endTime);
        }
        if (interval != null) {
            RegionCarrierDetailInvocation = RegionCarrierDetailInvocation.param("interval", interval);
        }
        if (enterpriseProjectId != null) {
            RegionCarrierDetailInvocation = RegionCarrierDetailInvocation.param("enterprise_project_id", enterpriseProjectId);
        }
        return RegionCarrierDetailInvocation.execute(this.buildExecutionOptions(RegionCarrierDetail.class));
    }

}
