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
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.CreateRoleReq;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.CreateRoleResp;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.ListRoleResp;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.QueryRoleResp;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.UpdateRoleReq;
import com.huawei.openstack4j.openstack.iam.domain.customRoleResource.UpdateRoleResp;

import static com.google.common.base.Preconditions.checkArgument;
import static com.huawei.openstack4j.core.transport.ClientConstants.PATH_IAM_ROLES;


public class CustomRoleService extends BaseIamService {

    /**
     * Create agency custom role
     *
     * @param roleReq create custom role request
     * @return the newly created custome rolr
     */
    public CreateRoleResp create(CreateRoleReq roleReq) {
        checkArgument(!Strings.isNullOrEmpty(roleReq.getDescription()), "parameter description should not be empty");
        checkArgument(!Strings.isNullOrEmpty(roleReq.getDisplayName()), "parameter display name should not be empty");
        checkArgument(!Strings.isNullOrEmpty(roleReq.getType()), "parameter type name should not be empty");
        checkArgument(!(roleReq.getPolicy() == null), "parameter policy should not be empty");
        return post(CreateRoleResp.class, uri(PATH_IAM_ROLES)).entity(roleReq).execute();
    }

    /**
     * Delete custom role
     *
     * @param roleId role id
     * @return action response
     */
    public ActionResponse delete(String roleId) {
        checkArgument(!Strings.isNullOrEmpty(roleId), "parameter role id should not be empty");
        return delete(ActionResponse.class, PATH_IAM_ROLES, "/", roleId).header("Content-Type", CONTENT_JSON).execute();
    }

    /**
     * List custom roles
     *
     * @return role list
     */
    public ListRoleResp list() {
        return get(ListRoleResp.class, PATH_IAM_ROLES).header("Content-Type", CONTENT_JSON).execute();
    }

    /**
     * Get custom role detail
     *
     * @return role detail
     */
    public QueryRoleResp get(String roleId) {
        return get(QueryRoleResp.class, PATH_IAM_ROLES, "/", roleId).header("Content-Type", CONTENT_JSON).execute();
    }


    /**
     * Update custom role
     *
     * @return role detail
     */
    public UpdateRoleResp update(String roleId, UpdateRoleReq updateRoleReq) {
        checkArgument(!Strings.isNullOrEmpty(roleId), "parameter role id should not be empty");
        return patch(UpdateRoleResp.class, PATH_IAM_ROLES, "/", roleId).entity(updateRoleReq).execute();
    }
}
