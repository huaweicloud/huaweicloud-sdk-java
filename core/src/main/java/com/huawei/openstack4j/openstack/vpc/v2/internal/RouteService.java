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
import com.huawei.openstack4j.openstack.vpc.v2.domain.Route;

public class RouteService extends BaseOpenStackService {

    public RouteService(){
        super(ServiceType.VPC2, RemoveProjectIdFromURL.INSTANCE);
    }

    /**
     * 查询VPC路由列表
     * @return
     */
    public List<Route> list(){
       return list(null);
    }

    /**
     * 查询VPC路由列表
     * @param filteringParams
     * @return
     */
    public List<Route> list(Map<String, String> filteringParams){
        Invocation<Route.Routes> req = get(Route.Routes.class, uri("/vpc/routes"));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                req = req.param(entry.getKey(), entry.getValue());
            }
        }
        return req.execute().getList();
    }

    /**
     * 查询VPC路由
     * @param routeId
     * @return
     */
    public Route get(String routeId){
        checkArgument(!Strings.isNullOrEmpty(routeId),"parameter `routeId` should not be empty");
        return get(Route.class,uri("/vpc/routes/%s",routeId)).execute();
    }

    /**
     * 创建VPC路由
     * @param routeCreate
     * @return
     */
    public Route create(Route routeCreate){
        checkArgument(routeCreate != null,"parameter `routeCreate` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(routeCreate.getDestination()),"parameter `routeCreate.destination` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(routeCreate.getNexthop()),"parameter `routeCreate.nexthop` should not be empty");
        checkArgument(routeCreate.getType() != null,"parameter `routeCreate.type` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(routeCreate.getVpcId()),"parameter `routeCreate.vpcId` should not be empty");
        return post(Route.class,uri("/vpc/routes")).entity(routeCreate).execute();
    }

    /**
     * 删除VPC路由
     * @param routeId
     * @return
     */
    public ActionResponse delete(String routeId){
        checkArgument(!Strings.isNullOrEmpty(routeId),"parameter `routeId` should not be empty");
        return ToActionResponseFunction.INSTANCE.apply(delete(Void.class,
                uri("/vpc/routes/%s",routeId)).executeWithResponse());
    }
}
