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
import com.huawei.openstack4j.openstack.bssintl.v1.domain.periodResource.*;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class PeriodResourceService extends BaseBusinessSupportSystemIntlService
{

    /**
     * A customer can query one or all yearly/monthly resources on the customer platform.
     * This API can be invoked only by the customer AK/SK or token.
     * @param
     * @param domainID
     * @return
     */
    public QueryResourcesListRsp queryResourcesList(String domainID, Map<String, String> filteringParams)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");

        BaseOpenStackService.Invocation<QueryResourcesListRsp>
            queryResourcesListInvocation = get(QueryResourcesListRsp.class, uri("/%s/common/order-mgr/resources/detail", domainID));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                queryResourcesListInvocation = queryResourcesListInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return queryResourcesListInvocation.execute();
    }

    /**
     * When subscription to yearly/monthly resources of a customer is about to expire, the customer can renew the subscription to the resources.
     * This API can be invoked using the customer token only.
     * @param req
     * @param domainID
     * @return
     */
    public OrderRenewByResourceIdRsp orderRenewByResourceId(String domainID, OrderRenewByResourceIdReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!(null == req.getResourceIds()), "parameter `resource_ids` should not be empty");
        checkArgument(!(null == req.getPeriodType()), "parameter `period_type` should not be empty");
        checkArgument(!(null == req.getPeriodNum()), "parameter `period_num` should not be empty");
        checkArgument(!(null == req.getExpireMode()), "parameter `expire_mode` should not be empty");
        return post(OrderRenewByResourceIdRsp.class, uri("/%s/common/order-mgr/resources/renew", domainID)).entity(req).execute();
    }

    /**
     * If a customer has subscribed to a yearly/monthly resource, the customer can use this API to unsubscribe from the resource, including the renewed part and currently used part. The customer cannot use the resources after unsubscription.
     * This API can be invoked using the customer token only.
     * @param req
     * @param domainID
     * @return
     */
    public OrderDeleteByResourceIdRsp orderDeleteByResourceId(String domainID, OrderDeleteByResourceIdReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!(null == req.getResourceIds()), "parameter `resourceIds` should not be empty");
        checkArgument(!(null == req.getUnSubType()), "parameter `unSubType` should not be empty");
        return post(OrderDeleteByResourceIdRsp.class, uri("/%s/common/order-mgr/resources/delete", domainID)).entity(req).execute();
    }

    /**
     * A customer can use this API to enable automatic subscription renewal for its long-term yearly/monthly resources to prevent the resources from being deleted when they are expired.
     * This API can be invoked using the customer token only.
     * @param
     * @param domainID
     * @return
     */
    public AutoRenewRsp autoRenew(String domainID, String resourceId, Map<String, String> filteringParams)
    {

        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(resourceId), "parameter `resourceId` should not be empty");
        BaseOpenStackService.Invocation<AutoRenewRsp> autoRenewInvocation = post(AutoRenewRsp.class, uri("/%s/common/order-mgr/resources/%s/actions", domainID, resourceId));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                autoRenewInvocation = autoRenewInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return autoRenewInvocation.execute();
    }

    /**
     * A customer can disable automatic subscription renewal when needed. After disabling this function, the customer needs to manually renew the subscription to the resources before they expire.
     * This API can be invoked using the customer token only.
     * @param
     * @param domainID
     * @return
     */
    public CancelAutoRenewRsp cancelAutoRenew(String domainID, String resourceId, Map<String, String> filteringParams)
    {

        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(resourceId), "parameter `resourceId` should not be empty");
        BaseOpenStackService.Invocation<CancelAutoRenewRsp>
            cancelAutoRenewInvocation = delete(CancelAutoRenewRsp.class, uri("/%s/common/order-mgr/resources/%s/actions", domainID, resourceId));

        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                cancelAutoRenewInvocation = cancelAutoRenewInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return cancelAutoRenewInvocation.execute();
    }
}
