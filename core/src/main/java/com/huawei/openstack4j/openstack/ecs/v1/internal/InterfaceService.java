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
package com.huawei.openstack4j.openstack.ecs.v1.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.openstack.ecs.v1.domain.InterfaceAttachment.InterfaceAttachments;
import static com.google.common.base.Preconditions.checkArgument;

public class InterfaceService extends BaseElasticComputeServices{

    /**
     * get interfaces of server
     *
     * @param serverId
     * @return
     */
    public InterfaceAttachments list(String serverId) {
        checkArgument(!Strings.isNullOrEmpty(serverId), "parameter `serverId` should not be empty");
        return get(InterfaceAttachments.class, uri("/cloudservers/" + serverId + "/os-interface")).execute();
    }


}
