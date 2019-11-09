/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.openstack.bssintl.v1.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.enquiry.QueryRatingReq;
import com.huawei.openstack4j.openstack.bssintl.v1.domain.enquiry.QueryRatingRsp;

import static com.google.common.base.Preconditions.checkArgument;

public class EnquiryService extends BaseBusinessSupportSystemIntlService
{
    /**
     * The partner sales platform obtains the product prices on the HUAWEI CLOUD official website based on the product catalog.
     * This API can be invoked using the customer token, or the partner's AK/SK or token.
     * @param req
     * @param domainID
     * @return
     */
    public QueryRatingRsp queryRating(String domainID, QueryRatingReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getTenantId()), "parameter `tenantId` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getRegionId()), "parameter `regionId` should not be empty");
        checkArgument(!(null == req.getSubscriptionNum()), "parameter `subscriptionNum` should not be empty");
        checkArgument(!(null == req.getChargingMode()), "parameter `chargingMode` should not be empty");
        checkArgument(!(null == req.getProductInfos()), "parameter `productInfos` should not be empty");
        return post(QueryRatingRsp.class, uri("/%s/customer/product-mgr/query-rating", domainID)).entity(req).execute();
    }
}
