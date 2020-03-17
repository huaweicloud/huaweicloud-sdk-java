/*******************************************************************************
 * 	Copyright 2020 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.openstack.iam.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.iam.domain.userResource.CreateUserReq;
import com.huawei.openstack4j.openstack.iam.domain.userResource.CreateUserResp;
import com.huawei.openstack4j.openstack.iam.domain.userResource.QueryUserResp;
import com.huawei.openstack4j.openstack.iam.domain.userResource.UpdateUserByAdminReq;
import com.huawei.openstack4j.openstack.iam.domain.userResource.UpdateUserReq;
import com.huawei.openstack4j.openstack.iam.domain.userResource.UpdateUserByAdminResp;

import static com.huawei.openstack4j.core.transport.ClientConstants.*;
import static com.google.common.base.Preconditions.checkArgument;


public class UserService extends BaseIamService {

    /**
     * Create a new user
     *
     * @param user the user entity
     * @return the newly created user
     */
    public CreateUserResp create(CreateUserReq user) {
        checkArgument(!Strings.isNullOrEmpty(user.getDomainId()), "parameter domain ID should not be empty");
        checkArgument(!Strings.isNullOrEmpty(user.getName()), "parameter user name should not be empty");
        return post(CreateUserResp.class, uri(PATH_IAM_USERS)).entity(user).execute();
    }

    /**
     * Query user detail with email and mobile
     *
     * @param userId the user id
     * @return the user detail
     */
    public QueryUserResp query(String userId) {
        checkArgument(!Strings.isNullOrEmpty(userId), "parameter user ID should not be empty");
        return get(QueryUserResp.class, PATH_IAM_USERS, "/", userId).execute();
    }

    /**
     * Update user info by user
     *
     * @param user the user entity
     * @return the new user detail
     */
    public ActionResponse update(String userId, UpdateUserReq user) {
        checkArgument(!Strings.isNullOrEmpty(userId), "parameter user ID should not be empty");
        return put(ActionResponse.class, PATH_IAM_USERS, "/",userId, PATH_IAM_INFO).entity(user).execute();
    }

    /**
     * Update user info by administrator
     *
     * @param user the user entity
     * @return the new user detail
     */
    public UpdateUserByAdminResp updateByAdmin(String userId, UpdateUserByAdminReq user) {
        checkArgument(!Strings.isNullOrEmpty(userId), "parameter user ID should not be empty");
        return put(UpdateUserByAdminResp.class, PATH_IAM_USERS, "/",userId).entity(user).execute();
    }
}
