package com.huawei.openstack.sample.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Role;
import com.huawei.openstack4j.openstack.OSFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleDemo {

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

        //Check role for group on domain
        //HEAD  /v3/domains/{domain_id}/groups/{group_id}/roles/{role_id}
        String domainId01 = "**********";
        String groupId01 = "********** ";
        String roleId01 = "**********";
        ActionResponse resp01 = osclient.identity().roles().checkDomainGroupRole(domainId01, groupId01, roleId01);
        if(resp01.isSuccess()){
            System.out.println("The user group under the domain has this permission");
        }else{
            System.out.println("The user group under the domain does not has this permission");
        }

        //Check role for group on project
        //HEAD  /v3/projects/{project_id}/groups/{group_id}/roles/{role_id}
        String projectId02 = "**********";
        String groupId02 = "**********";
        String roleId02 = "**********";
        ActionResponse resp02 = osclient.identity().roles().checkProjectGroupRole(projectId02, groupId02, roleId02);
        if(resp02.isSuccess()){
            System.out.println("The user group under the project has this permission");
        }else{
            System.out.println("The user group under the project does not has this permission");
        }

        //Grant permissions to a user group on a domain
        //PUT  /v3/domains/{domain_id}/groups/{group_id}/roles/{role_id}
        String domainId03 = "**********";
        String groupId03 = "**********";
        String roleId03 = "**********";
        ActionResponse resp = osclient.identity().roles().grantDomainGroupRole(domainId03, groupId03, roleId03);
        if(resp.isSuccess()){
            System.out.println("Grant permission to user group of a domain successfully");
        }else{
            System.out.println("Grant permission to user group of a domain failed : " + resp.getFault());
        }

        //Grant permissions to a user group on a project
        //PUT  /v3/projects/{project_id}/groups/{group_id}/roles/{role_id}
        String projectId04 = "**********";
        String groupId04 = "**********";
        String roleId04 = "**********";
        ActionResponse resp04 = osclient.identity().roles().grantProjectGroupRole(projectId04, groupId04, roleId04);
        if(resp04.isSuccess()){
            System.out.println("Grant permission to user group of a project successfully");
        }else{
            System.out.println("Grant permission to user group of a project failed : " + resp04.getFault());
        }

        //List a roles with filteringParams
        //GET  /v3/roles
        Map<String, String> filteringParams = new HashMap<>();
        String domainId05 = "**********";
        filteringParams.put("domain_id", domainId05);
        List<? extends Role> roleList05 = osclient.identity().roles().list(filteringParams);
        for(Role role : roleList05){
            System.out.println(role);
        }

        //List roles of a user group under a domain
        //GET  /v3/domains/{domain_id}/groups/{group_id}/roles
        String groupId06 = "**********";
        String domainId06 = "**********";
        List<? extends Role> roleList06 = osclient.identity().groups().listDomainGroupRoles(groupId06, domainId06);
        for(Role role : roleList06){
            System.out.println(role);
        }

        //List roles of a user group under a project
        //GET  /v3/projects/{project_id}/groups/{group_id}/roles
        String groupId07 = "**********";
        String projectId07 = "**********";
        List<? extends Role> roleList07 = osclient.identity().groups().listProjectGroupRoles(groupId07, projectId07);
        for (Role role : roleList07) {
            System.out.println(role);
        }

        //Query role detail
        //GET  /v3/roles/{role_id}
        String roleId08 = "**********";
        Role sampleRole08 = osclient.identity().roles().get(roleId08);
        System.out.println(sampleRole08);

        //Delete roles of a user group on a domain
        //DELETE  /v3/domains/{domain_id}/groups/{group_id}/roles/{role_id}
        String domainId09 = "**********";
        String groupId09 = "**********";
        String roleId09 = "**********";
        ActionResponse resp09 = osclient.identity().roles().revokeDomainGroupRole(domainId09, groupId09, roleId09);
        if(resp09.isSuccess()){
            System.out.println("Delete permission of user group corresponding to a domain successfully");
        }else{
            System.out.println("Delete permission of user group corresponding to a domain failed : " + resp09.getFault());
        }

        //Delete roles of a user group on a project
        //DELETE  /v3/projects/{project_id}/groups/{group_id}/roles/{role_id}
        String projectId10 = "**********";
        String groupId10 = "**********";
        String roleId10 = "**********";
        ActionResponse resp10 = osclient.identity().roles().revokeProjectGroupRole(projectId10, groupId10, roleId10);
        if(resp10.isSuccess()){
            System.out.println("Delete permission of user group corresponding to a project successfully");
        }else{
            System.out.println("Delete permission of user group corresponding to a project failed : " + resp10.getFault());
        }

        //Grant permissions to a user group on all projects
        //PUT  /v3/OS-INHERIT/domains/{domain_id}/groups/{group_id}/roles/{role_id}/inherited_to_projects
        String domainId11 = "**********";
        String groupId11 = "**********";
        String roleId11 = "**********";
        ActionResponse resp11 = osclient.identity().roles().grantGroupAllProjectsRole(domainId11, groupId11, roleId11);
        if(resp11.isSuccess()){
            System.out.println("Grant permissions to a user group on all projects successfully");
        }else{
            System.out.println("Grant permissions to a user group on all projects failed : " + resp.getFault());
        }
    }
}
