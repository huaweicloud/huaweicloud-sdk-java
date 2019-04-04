/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
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

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.vpc.v1.domain.Vpc;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VpcCreate;
import com.huawei.openstack4j.openstack.vpc.v1.domain.VpcUpdate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VpcDemo {

    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        // Create VPC
        String cidr = "172.17.0.100/26";
        VpcCreate vpc = VpcCreate.builder()
                .cidr(cidr)
                .name("testVpc")
                .enterpriseProjectId("")
                .build();
        Vpc createResp = osclient.vpc().vpcs().create(vpc);
        if (null != createResp) {
            System.out.println("create VPC success, id = " + createResp.getId());
        } else {
            System.out.println("create VPC failed");
        }

        // Get VPC
        Vpc getResp = osclient.vpc().vpcs().get(createResp.getId());
        if (null != getResp) {
            System.out.println("get VPC success, id = " + getResp.getId());
        } else {
            System.out.println("get VPC failed");
        }

        // List all VPC
        List<? extends Vpc> listResp = osclient.vpc().vpcs().list();
        if (null != listResp) {
            System.out.println("List all VPC success, size is = " + listResp.size());
        } else {
            System.out.println("get VPC failed");
        }

        // List VPC with filter
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("limit", "5");
        List<? extends Vpc> listWithFilterResp = osclient.vpc().vpcs().list(filteringParams);
        if (null != listWithFilterResp) {
            System.out.println("List VPC success, size is = " + listResp.size());
        } else {
            System.out.println("List VPC failed");
        }

        //Update a VPC
        VpcUpdate vpcUpdate = VpcUpdate.builder().name("testUpdate").build();
        Vpc updateResp = osclient.vpc().vpcs().update(getResp.getId(),vpcUpdate);
        if (null != updateResp) {
            System.out.println("Update a VPC success, id = " + updateResp.getId());
        } else {
            System.out.println("Update a VPC failed");
        }

        //Delete a VPC
        ActionResponse resp = osclient.vpc().vpcs().delete(getResp.getId());
        if (resp.isSuccess()) {
            System.out.println("Delete a VPC success");
        } else {
            System.out.println("Delete a VPC failed");
        }

    }
}
