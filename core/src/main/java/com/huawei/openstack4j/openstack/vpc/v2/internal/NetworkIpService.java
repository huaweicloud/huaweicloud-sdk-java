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
package com.huawei.openstack4j.openstack.vpc.v2.internal;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.openstack.common.functions.RemoveProjectIdFromURL;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;
import com.huawei.openstack4j.openstack.vpc.v2.domain.NetworkIpAvailability;

public class NetworkIpService extends BaseOpenStackService {

    public NetworkIpService(){
        super(ServiceType.VPC2, RemoveProjectIdFromURL.INSTANCE);
    }

    /**
     * 查询网络IP使用情况
     * @param networkId
     * @return
     */
    public NetworkIpAvailability get(String networkId){
        checkArgument(!Strings.isNullOrEmpty(networkId),"parameter `peeringId` should not be empty");
        return get(NetworkIpAvailability.class,uri("/network-ip-availabilities/%s",networkId)).execute();
    }

}
