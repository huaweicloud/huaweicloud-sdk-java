package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Project;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneProject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //create a project
        //POST  /v3/projects
        //sample 01 with required parameters
        Project sampleProject = KeystoneProject.builder().name("**********").build();
        Project project = osclient.identity().projects().create(sampleProject);
        System.out.println(project);

        //create a project
        //POST  /v3/projects
        //sample 02 with optional parameters
        Project sampleProject02 = KeystoneProject.builder().description("**********").name("**********").build();
        Project project02 = osclient.identity().projects().create(sampleProject02);
        System.out.println(project02);

        //create a project
        //POST  /v3/projects
        //sample 03 with complete parameters
        Project sampleProject03 = KeystoneProject.builder().description("**********").name("**********").domainId("**********").parentId("**********").build();
        Project project03 = osclient.identity().projects().create(sampleProject03);
        System.out.println(project03);

        //Query all accessible projects
        //GET  /v3/projects
        Map<String, Object> filteringParams04 = new HashMap<>();
        List<? extends Project> projectList04 = osclient.identity().projects().listByObject(filteringParams04);
        for(Project project04 : projectList04){
            System.out.println(project04);
        }

        //Query projects with filterparams
        //GET  /v3/projects
        String name05 = "**********";
        String domain_id05 = "**********";
        Map<String, Object> filteringParams05 = new HashMap<>();
        filteringParams05.put("name",name05);
        filteringParams05.put("domain_id",domain_id05);
        List<? extends Project> projectList05 = osclient.identity().projects().listByObject(filteringParams05);
        for(Project project05 : projectList05){
            System.out.println(project05);
        }

        //Query a user project list by administrator
        //GET  /v3/users/{user_id}/projects
        String userId06 = "**********";
        List<? extends Project> projectList06 = osclient.identity().users().listUserProjects(userId06);
        for(Project project06 : projectList06){
            System.out.println(project06);
        }

        //Query project information
        //GET  /v3/projects/{project_id}
        String projectId07 = "**********";
        Project sampleProject07 = osclient.identity().projects().get(projectId07);
        System.out.println(sampleProject07);

        //Update project
        //PATCH  /v3/projects/{project_id}
        String projectId08 = "**********";
        String projectName08 = "**********";
        String description08 = "**********";
        Project sampleProject08 = KeystoneProject.builder().id(projectId08).name(projectName08).description(description08).build();
        Project project08 = osclient.identity().projects().update(sampleProject08);
        System.out.println(project08);

        //Querying the List of Projects Accessible to Users
        //GET  /v3/auth/projects
        String tokenId09 = osclient.getToken().getId();
        List<? extends Project> projectList09 = osclient.identity().tokens().getProjectScopes(tokenId09);
        for(Project project09 : projectList09){
            System.out.println(project09);
        }
    }
}