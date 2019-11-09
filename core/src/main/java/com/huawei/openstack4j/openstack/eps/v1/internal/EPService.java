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
package com.huawei.openstack4j.openstack.eps.v1.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.eps.v1.contants.EPActionEnum;
import com.huawei.openstack4j.openstack.eps.v1.domain.*;
import com.huawei.openstack4j.openstack.eps.v1.domain.action.EPResourceActionRequest;
import com.huawei.openstack4j.openstack.eps.v1.domain.filter.EPResourceFilterRequest;
import com.huawei.openstack4j.openstack.eps.v1.domain.filter.EPResourceFilterResponse;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.huawei.openstack4j.core.transport.ClientConstants.PATH_ENTERPRISE_PROJECTS;
import static com.huawei.openstack4j.core.transport.ClientConstants.PATH_ENTERPRISE_PROJECT_QUOTAS;

public class EPService extends BaseEPManagementService
{

    /**
     * 创建企业项目
     * @param createRequest
     * @return EPCreateResponse
     */
    public EPCreateResponse create(EPCreateRequest createRequest)
    {
        checkArgument(createRequest != null, "parameter `createRequest` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(createRequest.getName()), "parameter `name` should not be empty");
        return post(EPCreateResponse.class, uri(PATH_ENTERPRISE_PROJECTS)).entity(createRequest).execute();
    }

    /**
     * @description 获取企业项目列表
     * @return
     */
    public EPListResponse list()
    {
        return get(EPListResponse.class, uri(PATH_ENTERPRISE_PROJECTS)).execute();
    }

    /**
     * @description 获取企业项目列表
     * @return
     */
    public EPListResponse list(Map<String, String> queryParams)
    {
        Invocation<EPListResponse> serverInvocation = get(EPListResponse.class, PATH_ENTERPRISE_PROJECTS);
        if (queryParams != null)
        {
            for (Map.Entry<String, String> entry : queryParams.entrySet())
            {
                serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return serverInvocation.execute();
    }

    /**
     * @description 获取企业项目详情
     * @return
     */
    public EPQueryResponse get(String epID)
    {
        checkArgument(!Strings.isNullOrEmpty(epID), "parameter `epID` should not be empty");
        return get(EPQueryResponse.class, uri(PATH_ENTERPRISE_PROJECTS) + "/" + epID).execute();
    }

    /**
     * @description 修改企业项目
     * @return
     */
    public EPQueryResponse modify(String epID, EPModifyRequest modifyRequest)
    {
        checkArgument(!Strings.isNullOrEmpty(epID), "parameter `epID` should not be empty");
        checkArgument(modifyRequest != null, "parameter `modifyRequest` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(modifyRequest.getName()), "parameter `name` should not be empty");
        return put(EPQueryResponse.class, uri(PATH_ENTERPRISE_PROJECTS) + "/" + epID).entity(modifyRequest).execute();
    }

    /**
     * @description 启停企业项目
     * @return
     */
    public ActionResponse action(String epID, EPActionRequest actionRequest)
    {
        checkArgument(!Strings.isNullOrEmpty(epID), "parameter `epID` should not be empty");
        checkArgument(actionRequest != null, "parameter `modifyRequest` should not be empty");
        // 校验action
        EPActionEnum action = EPActionEnum.value(actionRequest.getAction());
        checkArgument(action != null, "parameter `action` should not be empty");
        return  post(ActionResponse.class, uri(PATH_ENTERPRISE_PROJECTS) + "/" + epID+ "/action").entity(actionRequest).execute();
    }

    /**
     * @description 获取企业项目详情
     * @return
     */
    public EPQuotaResponse quotas()
    {
        return get(EPQuotaResponse.class, uri(PATH_ENTERPRISE_PROJECT_QUOTAS)).execute();
    }

    /**
     * @description 查询企业项目的资源列表
     * @return
     */
    public EPResourceFilterResponse filterResource(String epID, EPResourceFilterRequest filterRequest)
    {
        checkArgument(!Strings.isNullOrEmpty(epID), "parameter `epID should not be empty");
        checkArgument(filterRequest != null, "parameter `filterRequest` should not be empty");
        return  post(EPResourceFilterResponse.class, uri(PATH_ENTERPRISE_PROJECTS) + "/" + epID+ "/resources/filter").entity(filterRequest).execute();
    }

    /**
     * @description 企业项目的资源迁移
     * @return
     */
    public ActionResponse migrateResource(String epID, EPResourceActionRequest actionRequest)
    {
        checkArgument(!Strings.isNullOrEmpty(epID), "parameter `epID should not be empty");
        checkArgument(actionRequest != null, "parameter `actionRequest` should not be empty");
        return  post(ActionResponse.class, uri(PATH_ENTERPRISE_PROJECTS) + "/" + epID+ "/resources-migrate").entity(actionRequest).execute();
    }
}
