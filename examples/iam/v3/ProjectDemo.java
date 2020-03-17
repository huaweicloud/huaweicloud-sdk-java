package com.huawei.openstack.sample.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.iam.domain.projectResource.QueryProjectResp;
import com.huawei.openstack4j.openstack.iam.domain.projectResource.UpdateProjectReq;

public class ProjectDemo {

    public static void main(String[] args){

        String user = "**********";
        String password = "**********";
        String userDomainId = "**********";
        String authUrl = "**********";

        OSClient.OSClientV3 osclient = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId))
                .authenticate();

        //Query project information and status
        //GET  /v3-ext/projects/{project_id}
        String projectId = "**********";
        QueryProjectResp project = osclient.iam().projects().query(projectId);
        System.out.println(project);

        //Update project status
        //PUT  /v3-ext/projects/{project_id}
        String projectId1 = "**********";
        UpdateProjectReq projectReq = UpdateProjectReq.builder().status("**********").build();
        ActionResponse actionResponse = osclient.iam().projects().updateStatus(projectId1, projectReq);
        System.out.println(actionResponse);
    }
}