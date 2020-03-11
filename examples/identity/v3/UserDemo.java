package com.huawei.openstack.sample.identity.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Group;
import com.huawei.openstack4j.model.identity.v3.User;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDemo {

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

        //Create a user
        //POST  /v3/users
        //sample 01 with required parameter
        User createSampleUser01 = KeystoneUser.builder().name("**********").build();
        User createUser01 = osclient.identity().users().create(createSampleUser01);
        System.out.println(createUser01);


        //Create a user
        //POST  /v3/users
        //sample 02 with optional parameter
        User createSampleUser02 = KeystoneUser.builder().name("**********").password("**********").build();
        User createUser02 = osclient.identity().users().create(createSampleUser02);
        System.out.println(createUser02);


        //Create a user
        //POST  /v3/users
        //sample 03 with complete parameters
        User createSampleUser03 = KeystoneUser.builder().defaultProjectId("**********").description("**********").domainId("**********").enabled(true).name("**********").password("**********").build();
        User createUser03 = osclient.identity().users().create(createSampleUser03);
        System.out.println(createUser03);

        //Delete a user
        //DELETE  /v3/users/{user_id}
        String deleteUserId01 = "**********";
        ActionResponse deleteResp = osclient.identity().users().delete(deleteUserId01);
        if(deleteResp.isSuccess()){
            System.out.println("Delete user successfully");
        }else{
            System.out.println("Delete user failed : " +  deleteResp.getFault());
        }

        //Query the group which the user belongs
        //GET  /v3/users/{user_id}/groups
        String queryUserId01 = "**********";
        List<? extends Group> queryUserGroupList01 = osclient.identity().users().listUserGroups(queryUserId01);
        for(Group queryUserGroup01 : queryUserGroupList01){
            System.out.print(queryUserGroup01);
        }

        //List users
        //GET  /v3/users
        //sample 01 without query filter parameters
        List<? extends User> userList01 = osclient.identity().users().list();
        for(User listUser01 : userList01){
            System.out.println(listUser01);
        }

        //List users
        //GET  /v3/users
        //sample 02 with optional query  parameters
        Map<String, String> listFilteringParams02 = new HashMap<>();
        String listDomainId01 = "**********";
        listFilteringParams02.put("domain_id", listDomainId01);
        List<? extends User> userList02 = osclient.identity().users().list(listFilteringParams02);
        for(User listUser02 : userList02){
            System.out.println(listUser02);
        }

        //List users
        //GET  /v3/users
        //sample 03 with query filter parameters
        Map<String, String> listFilteringParams03 = new HashMap<>();
        String listUserName03 = "**********";
        listFilteringParams03.put("name", listUserName03);
        List<? extends User> userList03 = osclient.identity().users().list(listFilteringParams03);
        for(User listUser03 : userList03){
            System.out.println(listUser03);
        }

        //List users
        //GET  /v3/users
        //sample 04 with complete query parameters
        Map<String, String> listFilteringParams04 = new HashMap<>();
        String listDomainId04 = "**********";
        String listEnabled04 = "**********";
        String listUserName04 = "**********";
        String listPwdExpireAt04 = "**********";
        listFilteringParams04.put("domain_id", listDomainId04);
        listFilteringParams04.put("enabled", listEnabled04);
        listFilteringParams04.put("name", listUserName04);
        listFilteringParams04.put("password_expire_at", listPwdExpireAt04);
        List<? extends User> userList04 = osclient.identity().users().list(listFilteringParams04);
        for(User listUser04 : userList04){
            System.out.println(listUser04);
        }

        //List users by group
        //GET  /v3/groups/{group_id}/users
        //sample 01 without query parameters
        String listGroupId01 = "**********";
        Map<String, String> listGroupfilteringParams01 = new HashMap<>();
        List<? extends User> groupUserList01 = osclient.identity().groups().listGroupUsers(listGroupId01, listGroupfilteringParams01);
        for(User groupUser01 : groupUserList01){
            System.out.println(groupUser01);
        }

        //List users by group
        //GET  /v3/groups/{group_id}/users
        //sample 02 with query parameters
        String listGroupId02 = "**********";
        Map<String, String> listGroupFilteringParams02 = new HashMap<>();
        listGroupFilteringParams02.put("enabled", "true");
        List<? extends User> groupUserList02 = osclient.identity().groups().listGroupUsers(listGroupId02, listGroupFilteringParams02);
        for(User groupUser02 : groupUserList02){
            System.out.println(groupUser02);
        }

        //Query user detail
        //GET  /v3/users/{user_id}
        String queryUserDetailId01 = "**********";
        User queryDetailsampleUser01 = osclient.identity().users().get(queryUserDetailId01);
        System.out.println(queryDetailsampleUser01);


        //Update user Information
        //PATCH  /v3/users/{user_id}
        //sample 01 with optional parameters
        String updateUserId01 = "**********";
        User updateUser = KeystoneUser.builder()
                .id(updateUserId01)
                .name("**********")
                .build();
        User updateSampleUser01 = osclient.identity().users().update(updateUser);
        System.out.println(updateSampleUser01);

        //Update user Information
        //PATCH  /v3/users/{user_id}
        //sample 02 with complete parameters
        String updateUserId02 = "**********";
        User updateUser01 = KeystoneUser.builder()
                .id(updateUserId02)
                .defaultProjectId("**********")
                .description("**********")
                .domainId("**********")
                .enabled(true)
                .name("**********")
                .password("**********")
                .build();
        User updateSampleUser02 = osclient.identity().users().update(updateUser01);
        System.out.println(updateSampleUser02);

        //Change the password of the user
        //POST  /v3/users/{user_id}/password
        String changePWDuserId01 = "**********";
        String originalPassword = "**********";
        String newPassword = "**********";
        ActionResponse changePWDresp01 = osclient.identity().users().changePassword(changePWDuserId01, originalPassword, newPassword);
        if(changePWDresp01.isSuccess()){
            System.out.println("Change password successfully");
        }else{
            System.out.println("Change password failed : " + changePWDresp01.getFault());
        }

        //Adding a User to a User Group
        String userId3 = "**********";
        String groupId2 = "**********";
        ActionResponse resp2 = osclient.identity().groups().addUserToGroup(groupId2, userId3);
        if(resp2.isSuccess()){
            System.out.println("Add user to user group successfully");
        }else{
            System.out.println("Add user to user group failed : " + resp2.getFault());
        }

        //Querying a User List by name
        String userName="********";
        List<? extends User> userList = osclient.identity().users().getByName(userName);
        for(User user2 : userList){
            System.out.println(user2);
        }

        //Querying a User by domainId and name
        String domainId = "*********";
        String userName01 = "********";
        User user2 = osclient.identity().users().getByName(userName01, domainId);
        System.out.println(user2);


        //Querying User Details
        String userId6 = "**********";
        User sampleUser2 = osclient.identity().users().get(userId6);
        System.out.println(sampleUser2);

        //Querying the User Group to Which a User Belongs
        String userId7 = "**********";
        List<? extends Group> userGroupList = osclient.identity().users().listUserGroups(userId7);
        for(Group group : userGroupList){
            System.out.print(group);
        }
    }
}
