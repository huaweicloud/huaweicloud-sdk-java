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
import com.huawei.openstack4j.model.network.SecurityGroup;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.networking.domain.NeutronSecurityGroup;

import java.util.List;

public class SecurityGroupDemo {
    public static void main(String[] args) {

        String user = "******";
        String password = "******";
        String projectId = "******";
        String userDomainId = "******";
        String authUrl = "******";

        OSClient.OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        //Create a SecurityGroup
        String securityGroupName = "******";
        SecurityGroup securityGroup = NeutronSecurityGroup.builder().name(securityGroupName).build();
        SecurityGroup createResp = osclient.networking().securitygroup().create(securityGroup);
        if (null != createResp) {
            System.out.println("Create a SecurityGroup success, id = " + createResp.getId());
        } else {
            System.out.println("Create a SecurityGroup failed");
        }

        //Delete a SecurityGroup
        String securityGroupId = "******";
        ActionResponse resp = osclient.networking().securitygroup().delete(securityGroupId);
        if (resp.isSuccess()) {
            System.out.println("Delete a SecurityGroup success");
        } else {
            System.out.println("Delete a SecurityGroup failed");
        }

        //Get a SecurityGroup
        SecurityGroup getResp = osclient.networking().securitygroup().get(securityGroupId);
        if (null != getResp) {
            System.out.println("Get a SecurityGroup success, id = " + getResp.getId());
        } else {
            System.out.println("Get a SecurityGroup failed");
        }

        //List all SecurityGroups
        List<? extends SecurityGroup> listResp = osclient.networking().securitygroup().list();
        if (null != listResp && listResp.size() > 0) {
            System.out.println("List all SecurityGroups success, size = " + listResp.size());
        } else {
            System.out.println("List all SecurityGroups failed");
        }


    }
}
