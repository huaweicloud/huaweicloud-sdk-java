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

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.common.functions.RemoveProjectIdFromURL;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;
import com.huawei.openstack4j.openstack.vpc.v2.domain.Peering;
import com.huawei.openstack4j.openstack.vpc.v2.domain.PeeringCreate;
import com.huawei.openstack4j.openstack.vpc.v2.domain.PeeringRequest;
import com.huawei.openstack4j.openstack.vpc.v2.domain.PeeringUpdate;

public class PeeringService extends BaseOpenStackService {

    public PeeringService(){
        super(ServiceType.VPC2, RemoveProjectIdFromURL.INSTANCE);
    }

    /**
     * 查询提交请求的租户的所有对等连接
     * @return
     */
    public List<Peering> list() {
        return  list(null);
    }

    /**
     * 查询提交请求的租户的所有对等连接
     * @param filteringParams
     * @return
     */
    public List<Peering> list(Map<String, String> filteringParams) {
        Invocation<Peering.Peerings> req = get(Peering.Peerings.class, uri("/vpc/peerings"));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                req = req.param(entry.getKey(), entry.getValue());
            }
        }
        return req.execute().getList();
    }

    /**
     * 查询对等连接详情
     * @param peeringId
     * @return
     */
    public Peering get(String peeringId){
        checkArgument(!Strings.isNullOrEmpty(peeringId),"parameter `peeringId` should not be empty");
        return get(Peering.class,uri("/vpc/peerings/%s",peeringId)).execute();
    }

    /**
     * 创建对等连接
     * @param peeringCreate
     * @return
     */
    public Peering create(PeeringCreate peeringCreate){
        checkArgument(peeringCreate != null,"parameter `peeringCreate` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(peeringCreate.getName()),"parameter `name` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(peeringCreate.getRequestVpcInfo().getVpcId()),"parameter `requestVpcInfo.vpcId` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(peeringCreate.getAcceptVpcInfo().getVpcId()),"parameter `acceptVpcInfo.vpcId` should not be empty");
        return post(Peering.class, uri("/vpc/peerings")).entity(peeringCreate).execute();
    }

    /**
     * 租户A名下的VPC申请和租户B的VPC建立对等连接，需要等待租户B接受该请求。
     * 此接口用于租户接受其他租户发起的对等连接请求。
     * @param peeringId
     * @return
     */
    public PeeringRequest accept(String peeringId){
        checkArgument(!Strings.isNullOrEmpty(peeringId),"parameter `peeringId` should not be empty");
        return put(PeeringRequest.class,uri("/vpc/peerings/%s/accept",peeringId)).execute();
    }

    /**
     * 租户A名下的VPC申请和租户B的VPC建立对等连接，需要等待租户B接受该请求。
     * 此接口用于租户拒绝其他租户发起的对等连接请求。
     * @param peeringId
     * @return
     */
    public PeeringRequest reject(String peeringId){
        checkArgument(!Strings.isNullOrEmpty(peeringId),"parameter `peeringId` should not be empty");
        return put(PeeringRequest.class,uri("/vpc/peerings/%s/reject",peeringId)).execute();
    }

    /**
     * 更新对等连接
     * @param peeringId
     * @param peeringUpdate
     * @return
     */
    public Peering update(String peeringId, PeeringUpdate peeringUpdate){
        checkArgument(!Strings.isNullOrEmpty(peeringId),"parameter `peeringId` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(peeringUpdate.getName()),"parameter `peeringUpdate.name` should not be empty");
        return put(Peering.class,uri("/vpc/peerings/%s",peeringId)).entity(peeringUpdate).execute();
    }

    /**
     * 删除对等连接
     * @param peeringId
     * @return
     */
    public ActionResponse delete(String peeringId){
        checkArgument(!Strings.isNullOrEmpty(peeringId),"parameter `peeringId` should not be empty");
        return ToActionResponseFunction.INSTANCE.apply(delete(Void.class,
                uri("/vpc/peerings/%s",peeringId)).executeWithResponse());
    }
}
