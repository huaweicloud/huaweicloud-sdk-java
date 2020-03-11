package com.huawei.openstack.sample.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Group;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupDemo {

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

        //Create a user group
        //POST  /v3/groups
        Group sampleGroup01 = KeystoneGroup.builder().domainId("**********").name("**********").description("**********").build();
        Group group01 = osclient.identity().groups().create(sampleGroup01);
        System.out.println(group01);

        //Add a user to a user group
        //PUT  /v3/groups/{group_id}/users/{user_id}
        String groupId02 = "**********";
        String userId02 = "**********";
        ActionResponse resp02 = osclient.identity().groups().addUserToGroup(groupId02, userId02);
        if(resp02.isSuccess()){
            System.out.println("Add user to user group successfully");
        }else{
            System.out.println("Add user to user group failed : " + resp02.getFault());
        }

        //Check whether a user belongs to a user group
        //HEAD  /v3/groups/{group_id}/users/{user_id}
        String groupId03 = "**********";
        String userId03 = "**********";
        ActionResponse response = osclient.identity().groups().checkGroupUser(groupId03, userId03);
        if(response.isSuccess()){
            System.out.println("The user belongs to this usergroup");
        }else{
            System.out.println("The user does not belong to this usergroup");
        }

        //Delete a user group
        //DELETE  /v3/groups/{group_id}
        String groupId04 = "**********";
        ActionResponse response04 = osclient.identity().groups().delete(groupId04);
        if(response04.isSuccess()){
            System.out.println("Delete group successfully.");
        }else{
            System.out.println("Delete group failed.");
        }

        //List user group with filterparam
        //GET  /v3/groups
        Map<String, String> filteringParams = new HashMap<>();
        String domainId05 = "**********";
        filteringParams.put("domain_id", domainId05);
        List<? extends Group> groupList = osclient.identity().groups().list(filteringParams);
        for(Group group : groupList) {
            System.out.println(group);
        }

        //Query user group detail
        //GET  /v3/groups/{group_id}
        String groupId06 = "**********";
        Group sampleGroup06 = osclient.identity().groups().get(groupId06);
        System.out.println(sampleGroup06);

        //Delete a User from a User Group
        //DELETE  /v3/groups/{group_id}/users/{user_id}
        String groupId07 = "**********";
        String userId07 = "**********";
        ActionResponse resp07 = osclient.identity().groups().removeUserFromGroup(groupId07, userId07);
        if(resp07.isSuccess()){
            System.out.println("Delete user from user group successfully");
        }else{
            System.out.println("Delete user from user group failed : " + resp07.getFault());
        }

        //Update a user group
        //PATCH  /v3/groups/{group_id}
        String groupId08 = "**********";
        Group sampleGroup08 = KeystoneGroup.builder().id(groupId08).domainId("**********").name("**********").description("**********").build();
        Group group08 = osclient.identity().groups().update(sampleGroup08);
        System.out.println(group08);
    }
}
