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
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.common.functions.ReplaceVersionOfURL;
import com.huawei.openstack4j.openstack.iam.domain.projectResource.QueryProjectResp;
import com.huawei.openstack4j.openstack.iam.domain.projectResource.UpdateProjectReq;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;

import static com.google.common.base.Preconditions.checkArgument;
import static com.huawei.openstack4j.core.transport.ClientConstants.PATH_PROJECTS;

public class ProjectService extends BaseOpenStackService {
    public ProjectService() {
        super(ServiceType.IAM, ReplaceVersionOfURL.instance("/v3-ext"));
    }

    /**
     * Query project detail and status
     *
     * @param projectId the project id
     * @return the queryProjectResp
     */
    public QueryProjectResp query(String projectId) {
        checkArgument(!Strings.isNullOrEmpty(projectId), "parameter project id should not be empty");
        return get(QueryProjectResp.class, PATH_PROJECTS, "/", projectId).execute();
    }


    /**
     * Update project status
     *
     * @param projectId the project id
     * @param project the ppdateProjectReq entity
     * @return the actionResponse
     */
    public ActionResponse updateStatus(String projectId, UpdateProjectReq project) {
        checkArgument(!Strings.isNullOrEmpty(projectId), "parameter project id should not be empty");
        return put(ActionResponse.class, PATH_PROJECTS, "/",projectId).entity(project).execute();
    }
}
