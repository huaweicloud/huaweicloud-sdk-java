import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Role;
import com.huawei.openstack4j.openstack.OSFactory;

import java.util.List;

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

        //Querying a Role List
        List<? extends Role> roleList = osclient.identity().roles().list();
        for(Role role : roleList){
            System.out.println(role);
        }

        //Querying a Role List with filteringParams
        Map<String, String> filteringParams = new HashMap<>();
        String domainId3 = "**********";
        filteringParams.put("domain_id", domainId3);
        List<? extends Role> roleList = osclient.identity().roles().list(filteringParams);
        for(Role role : roleList){
            System.out.println(role);
        }

        //Querying Role Details
        String roleId = "**********";
        Role sampleRole = osclient.identity().roles().get(roleId);
        System.out.println(sampleRole);

        //Querying Permissions of a User Group Under a Domain
        String groupId = "**********";
        String domainId = "**********";
        List<? extends Role> roleList2 = osclient.identity().groups().listDomainGroupRoles(groupId, domainId);
        for(Role role : roleList2){
            System.out.println(role);
        }

        //Querying Permissions of a User Group Corresponding to a Project
        String groupId2 = "**********";
        String projectId = "**********";
        List<? extends Role> roleList3 = osclient.identity().groups().listProjectGroupRoles(groupId2, projectId);
        for(Role role : roleList3){
            System.out.println(role);
        }

        //Granting Permissions to a User Group of a Domain
        String domainId2 = "**********";
        String groupId3 = "**********";
        String roleId2 = "**********";
        ActionResponse resp = osclient.identity().roles().grantDomainGroupRole(domainId2, groupId3, roleId2);
        if(resp.isSuccess()){
            System.out.println("Grant permission to user group of a domain successfully");
        }else{
            System.out.println("Grant permission to user group of a domain failed : " + resp.getFault());
        }

        //Granting Permissions to a User Group Corresponding to a Project
        String projectId2 = "**********";
        String groupId4 = "**********";
        String roleId3 = "**********";
        ActionResponse resp2 = osclient.identity().roles().grantProjectGroupRole(projectId2, groupId4, roleId3);
        if(resp2.isSuccess()){
            System.out.println("Grant permission to user group of a project successfully");
        }else{
            System.out.println("Grant permission to user group of a project failed : " + resp2.getFault());
        }

        //Deleting Permissions of a User Group Corresponding to a Project
        String projectId3 = "**********";
        String groupId5 = "**********";
        String roleId4 = "**********";
        ActionResponse resp3 = osclient.identity().roles().revokeProjectGroupRole(projectId3, groupId5, roleId4);
        if(resp3.isSuccess()){
            System.out.println("Delete permission of user group corresponding to a project successfully");
        }else{
            System.out.println("Delete permission of user group corresponding to a project failed : " + resp3.getFault());
        }

        //Deleting Permissions of a User Group of a Domain
        String domainId3 = "**********";
        String groupId6 = "**********";
        String roleId5 = "**********";
        ActionResponse resp4 = osclient.identity().roles().revokeDomainGroupRole(domainId3, groupId6, roleId5);
        if(resp4.isSuccess()){
            System.out.println("Delete permission of user group corresponding to a domain successfully");
        }else{
            System.out.println("Delete permission of user group corresponding to a domain failed : " + resp4.getFault());
        }

        //Querying Whether a User Group Under a Domain Has Specific Permissions
        String domainId4 = "**********";
        String groupId7 = "**********";
        String roleId6 = "**********";
        ActionResponse resp5 = osclient.identity().roles().checkDomainGroupRole(domainId4, groupId7, roleId6);
        if(resp5.isSuccess()){
            System.out.println("The user group under the domain has this permission");
        }else{
            System.out.println("The user group under the domain does not has this permission");
        }

        //Querying Whether a User Group Corresponding to a Project Has Specific Permissions
        String projectId4 = "**********";
        String groupId8 = "**********";
        String roleId7 = "**********";
        ActionResponse resp6 = osclient.identity().roles().checkProjectGroupRole(projectId4, groupId8, roleId7);
        if(resp6.isSuccess()){
            System.out.println("The user group under the project has this permission");
        }else{
            System.out.println("The user group under the project does not has this permission");
        }

    }
}
