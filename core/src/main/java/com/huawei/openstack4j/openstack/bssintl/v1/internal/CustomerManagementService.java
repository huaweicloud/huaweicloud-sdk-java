/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co.,Ltd.
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
import com.huawei.openstack4j.openstack.bssintl.v1.domain.customerManagement.*;

import static com.google.common.base.Preconditions.checkArgument;

public class CustomerManagementService extends BaseBusinessSupportSystemIntlService
{
    /**
     * This API is used to check whether the account name, and mobile number or email address entered by the customer can be used for registration.
     * This API can be invoked only by the partner AK/SK or token.
     * @param domainID
     * @param req
     * @return
     */
    public CheckUserRsp checkUser(String domainID, CheckUserReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getSearchKey()), "parameter `SearchKey` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getSearchType()), "parameter `SearchType` should not be empty");
        return post(CheckUserRsp.class, uri("/%s/partner/customer-mgr/check-user", domainID)).entity(req).execute();
    }

    /**
     * This API is used to create a HUAWEI CLOUD account for a customer when the customer creates an account on your sales platform, and bind the customer account on the partner sales platform to the HUAWEI CLOUD account. In addition, the HUAWEI CLOUD account is bound to the partner account.
     * This API can be invoked only by the partner AK/SK or token.
     * @param req
     * @param domainID
     * @return
     */
    public CreateCustomerRsp createCustomer(String domainID, CreateCustomerReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getXAccountId()), "parameter `XAccountId` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(req.getXAccountType()), "parameter `XAccountType` should not be empty");
        return post(CreateCustomerRsp.class, uri("/%s/partner/customer-mgr/customer", domainID)).entity(req).execute();
    }

    /**
     * This API is used to query your customers.
     * This API can be invoked only by the partner account AK/SK or token.
     * @param req
     * @param domainID
     * @return
     */
    public QueryCustomerInfoListRsp queryCustomerInfoList(String domainID, QueryCustomerInfoListReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        return post(QueryCustomerInfoListRsp.class, uri("/%s/partner/customer-mgr/query", domainID)).entity(req)
            .execute();
    }

    /**
     * A partner can freeze an account of a customer associated with the partner by reseller model.
     * This API can be invoked only by the partner account AK/SK or token.
     * @param req
     * @param domainID
     * @return
     */
    public SetCustomersFrozenRsp setCustomersFrozen(String domainID, SetCustomersFrozenReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        return post(SetCustomersFrozenRsp.class, uri("/%s/partner/customer-mgr/frozens", domainID)).entity(req)
            .execute();
    }

    /**
     * A partner can unfreeze an account of a customer associated with the partner by reseller model.
     * This API can be invoked only by the partner account AK/SK or token.
     * @param req
     * @param domainID
     * @return SetCustomersFrozenRsp
     */
    public SetCustomersFrozenRsp setCustomersUnfrozen(String domainID, SetCustomersFrozenReq req)
    {
        checkArgument(!Strings.isNullOrEmpty(domainID), "parameter `domainID` should not be empty");
        return post(SetCustomersFrozenRsp.class, uri("/%s/partner/customer-mgr/unfrozens", domainID)).entity(req)
            .execute();
    }
}
