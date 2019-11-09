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
package com.huawei.openstack4j.openstack.ecs.v1.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.ecs.v1.domain.CloudServerTag;
import com.huawei.openstack4j.openstack.ecs.v1.domain.CloudServerTagAction;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ProjectTag;
import com.huawei.openstack4j.openstack.ecs.v1.domain.ServerTags;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class TagService extends BaseElasticComputeServices {

    /**
     * cloud server tags add
     *
     * @param serverId
     * @param serverTags
     * @return
     */
    public ActionResponse add(String serverId, List<ServerTags> serverTags) {
        checkArgument(!Strings.isNullOrEmpty(serverId), "parameter `serverId` should not be empty");
        checkArgument(!(serverTags == null || serverTags.size() <= 0), "parameter `serverTags` should not be empty");
        CloudServerTagAction cloudServerTagAction = new CloudServerTagAction("create", serverTags);
        return post(ActionResponse.class, "/cloudservers/" + serverId + "/tags/action").entity(cloudServerTagAction).execute();
    }

    /**
     * cloud server tags delete
     *
     * @param serverId
     * @param serverTags
     * @return
     */
    public ActionResponse delete(String serverId, List<ServerTags> serverTags) {
        checkArgument(!Strings.isNullOrEmpty(serverId), "parameter `serverId` should not be empty");
        checkArgument(!(serverTags == null || serverTags.size() <= 0), "parameter `serverTags` should not be empty");
        CloudServerTagAction cloudServerTagAction = new CloudServerTagAction("delete", serverTags);
        return post(ActionResponse.class, "/cloudservers/" + serverId + "/tags/action").entity(cloudServerTagAction).execute();
    }

    /**
     * get cloud server tags
     *
     * @param serverId
     * @return
     */
    public CloudServerTag list(String serverId) {
        checkArgument(!Strings.isNullOrEmpty(serverId), "parameter `serverId` should not be empty");
        return get(CloudServerTag.class, "/cloudservers/" + serverId + "/tags").execute();
    }

    /**
     * get project tags
     *
     * @return
     */
    public ProjectTag listProjectTags() {
        return get(ProjectTag.class, "/cloudservers/tags").execute();
    }

}


