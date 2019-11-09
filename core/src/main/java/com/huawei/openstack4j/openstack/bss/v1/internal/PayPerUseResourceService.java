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
package com.huawei.openstack4j.openstack.bss.v1.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.bss.v1.domain.resource.QueryCustomerResourceReq;
import com.huawei.openstack4j.openstack.bss.v1.domain.resource.QueryCustomerResourceRsp;

import static com.google.common.base.Preconditions.checkArgument;

public class PayPerUseResourceService extends BaseBusinessSupportSystemService
{
    /**
     * A customer can query its pay-per-use resources on the partner sales platform. The on-demand resource data has a latency, and the latency for each cloud service data varies. The data obtained using this API is for reference only.
     * This API can be invoked using the partner AK/SK or token only.
     * @param req
     * @param domainID
     * @return
     */
    public QueryCustomerResourceRsp queryCustomerResource(String domainID, QueryCustomerResourceReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getCustomerId()), "parameter `SearchKey` should not be empty");
        return post(QueryCustomerResourceRsp.class, uri("/%s/partner/customer-mgr/customer-resource/query-resources", domainID)).entity(req).execute();
    }
}
