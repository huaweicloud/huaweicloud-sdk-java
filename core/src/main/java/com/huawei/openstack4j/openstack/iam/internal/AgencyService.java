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
import com.huawei.openstack4j.openstack.iam.domain.agencyResource.AgencyRole;
import com.huawei.openstack4j.openstack.iam.domain.agencyResource.CreateAgencyReq;
import com.huawei.openstack4j.openstack.iam.domain.agencyResource.AgencyResp;
import com.huawei.openstack4j.openstack.iam.domain.agencyResource.ListAgenciesResp;
import com.huawei.openstack4j.openstack.iam.domain.agencyResource.ListPermissionsResp;
import com.huawei.openstack4j.openstack.iam.domain.agencyResource.UpdateAgencyReq;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.huawei.openstack4j.core.transport.ClientConstants.PATH_IAM_AGENCIES;

public class AgencyService extends BaseIamService {
    public static final String AGENCIES = "agencies";


    /**
     * Check permission for agency on domain
     *
     * @param domainId the domain id
     * @param agencyId the agency id
     * @param roleId   the role id
     * @return the actionResponse
     */
    public ActionResponse checkPermissionOnDomain(String domainId, String agencyId, String roleId) {
        checkArgument(!Strings.isNullOrEmpty(domainId), "parameter domain id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(agencyId), "parameter agency id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(roleId), "parameter role id should not be empty");
        return head(ActionResponse.class, PATH_IAM_AGENCIES, uri("/domains/%s/agencies/%s/roles/%s", domainId, agencyId, roleId)).execute();
    }

    /**
     * Check permission for agency on project
     *
     * @param projectId the domain id
     * @param agencyId  the agency id
     * @param roleId    the role id
     * @return the actionResponse
     */
    public ActionResponse checkPermissionOnProject(String projectId, String agencyId, String roleId) {
        checkArgument(!Strings.isNullOrEmpty(projectId), "parameter project id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(agencyId), "parameter agency id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(roleId), "parameter role id should not be empty");
        return head(ActionResponse.class, PATH_IAM_AGENCIES, uri("/projects/%s/agencies/%s/roles/%s", projectId, agencyId, roleId)).execute();
    }

    /**
     * Create agency
     *
     * @param createAgencyReq the agency request entity
     * @return the actionResponse
     */
    public AgencyResp create(CreateAgencyReq createAgencyReq) {
        checkArgument(!Strings.isNullOrEmpty(createAgencyReq.getDomainId()), "parameter domain id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(createAgencyReq.getName()), "parameter agency name should not be empty");
        return post(AgencyResp.class, PATH_IAM_AGENCIES, "/", AGENCIES).entity(createAgencyReq).execute();
    }

    /**
     * Delete agency
     *
     * @param agencyId the id of agency to delete
     * @return the actionResponse
     */
    public ActionResponse delete(String agencyId) {
        checkArgument(!Strings.isNullOrEmpty(agencyId), "parameter agency id should not be empty");
        return delete(ActionResponse.class, PATH_IAM_AGENCIES, "/", AGENCIES, "/", agencyId).execute();
    }

    /**
     * Grant permission for agency on domain
     *
     * @param domainId the domain id
     * @param agencyId the agency id
     * @param roleId   the role id
     * @return the actionResponse
     */
    public ActionResponse addPermissionOnDomain(String domainId, String agencyId, String roleId) {
        checkArgument(!Strings.isNullOrEmpty(domainId), "parameter domain id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(agencyId), "parameter agency id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(roleId), "parameter role id should not be empty");
        return put(ActionResponse.class, PATH_IAM_AGENCIES, uri("/domains/%s/agencies/%s/roles/%s", domainId, agencyId, roleId)).execute();
    }

    /**
     * Grant permission for agency on project
     *
     * @param projectId the project id
     * @param agencyId  the agency id
     * @param roleId    the role id
     * @return the actionResponse
     */
    public ActionResponse addPermissionOnProject(String projectId, String agencyId, String roleId) {
        checkArgument(!Strings.isNullOrEmpty(projectId), "parameter project id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(agencyId), "parameter agency id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(roleId), "parameter role id should not be empty");
        return put(ActionResponse.class, PATH_IAM_AGENCIES, uri("/projects/%s/agencies/%s/roles/%s", projectId, agencyId, roleId)).execute();
    }

    /**
     * List agencies
     *
     * @param domainId        the domain id
     * @param filteringParams the filter parameters
     * @return the actionResponse
     */
    public ListAgenciesResp listAgencies(String domainId, Map<String, String> filteringParams) {
        checkArgument(!Strings.isNullOrEmpty(domainId), "parameter domain id should not be empty");
        filteringParams.put("domain_id", domainId);
        Invocation<ListAgenciesResp> flavorInvocation = get(ListAgenciesResp.class, PATH_IAM_AGENCIES, "/", AGENCIES);
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                flavorInvocation = flavorInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return flavorInvocation.execute();
    }

    /**
     * List permissions for agency on domain
     *
     * @param domainId the domain id
     * @param agencyID the agency id
     * @return the permission list
     */
    public ListPermissionsResp listPermissionsOnDomain(String domainId, String agencyID) {
        checkArgument(!Strings.isNullOrEmpty(domainId), "parameter domain id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(agencyID), "parameter agency id should not be empty");
        Invocation<ListPermissionsResp> flavorInvocation = get(ListPermissionsResp.class, PATH_IAM_AGENCIES, uri("/domains/%s/agencies/%s/roles", domainId, agencyID));
        return flavorInvocation.execute();
    }

    /**
     * List permissions for agency on project
     *
     * @param projectId the project id
     * @param agencyId  the agency id
     * @return the permission list
     */
    public ListPermissionsResp listPermissionsOnProject(String projectId, String agencyId) {
        checkArgument(!Strings.isNullOrEmpty(projectId), "parameter project id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(agencyId), "parameter agency id should not be empty");
        Invocation<ListPermissionsResp> flavorInvocation = get(ListPermissionsResp.class, PATH_IAM_AGENCIES, uri("/projects/%s/agencies/%s/roles", projectId, agencyId));
        return flavorInvocation.execute();
    }

    /**
     * Query agency detail
     *
     * @param agencyId the agency id
     * @return the permission list
     */
    public AgencyResp get(String agencyId) {
        checkArgument(!Strings.isNullOrEmpty(agencyId), "parameter agency id should not be empty");
        return get(AgencyResp.class, PATH_IAM_AGENCIES, uri("/agencies/%s", agencyId)).execute();
    }

    /**
     * Remove permission for agency on domain
     *
     * @param domainId the domain id
     * @param agencyId the agency id
     * @param roleId   the role id
     * @return the actionResponse
     */
    public ActionResponse deletePermissionOnDomain(String domainId, String agencyId, String roleId) {
        checkArgument(!Strings.isNullOrEmpty(domainId), "parameter domain id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(agencyId), "parameter agency id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(roleId), "parameter role id should not be empty");
        return delete(ActionResponse.class, PATH_IAM_AGENCIES, uri("/domains/%s/agencies/%s/roles/%s", domainId, agencyId, roleId)).execute();
    }

    /**
     * Remove permission for agency on project
     *
     * @param projectId the domain id
     * @param agencyId  the agency id
     * @param roleId    the role id
     * @return the actionResponse
     */
    public ActionResponse deletePermissionOnProject(String projectId, String agencyId, String roleId) {
        checkArgument(!Strings.isNullOrEmpty(projectId), "parameter project id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(agencyId), "parameter agency id should not be empty");
        checkArgument(!Strings.isNullOrEmpty(roleId), "parameter role id should not be empty");
        return delete(ActionResponse.class, PATH_IAM_AGENCIES, uri("/projects/%s/agencies/%s/roles/%s", projectId, agencyId, roleId)).execute();
    }

    /**
     * Update agency detail
     *
     * @param agencyId        the agency id
     * @param updateAgencyReq the agency request to update
     * @return the permission list
     */
    public AgencyResp update(String agencyId, UpdateAgencyReq updateAgencyReq) {
        checkArgument(!Strings.isNullOrEmpty(agencyId), "parameter agency id should not be empty");
        return put(AgencyResp.class, PATH_IAM_AGENCIES, uri("/agencies/%s", agencyId)).entity(updateAgencyReq).execute();
    }

}
