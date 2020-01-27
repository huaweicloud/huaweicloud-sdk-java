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

        //Creating a User
        User sampleUser = KeystoneUser.builder().name("**********").password("**********").build();
        osclient.identity().users().create(sampleUser);

        //Create User with param
        String domainId = "**********";
        String userName = "**********";
        String userPassword = "**********";
        User user1 = osclient.identity().users().create(domainId, userName, password);
        System.out.println(user1);

        //Deleting a User
        String userId1 = "**********";
        ActionResponse resp = osclient.identity().users().delete(userId1);
        if(resp.isSuccess()){
            System.out.println("Delete user successfully");
        }else{
            System.out.println("Delete user failed : " +  resp.getFault());
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

        //Modifying User Information
        String userId4 = "**********";
        User updateUser = KeystoneUser.builder()
                .id(userId4)
                .name("**********")
                .build();
        osclient.identity().users().update(updateUser);

        //Changing a Password
        String userId5 = "**********";
        String originalPassword = "**********";
        String newPassword = "**********";
        ActionResponse resp3 = osclient.identity().users().changePassword(userId5, originalPassword, newPassword);
        if(resp3.isSuccess()){
            System.out.println("Change password successfully");
        }else{
            System.out.println("Change password failed : " + resp3.getFault());
        }

        //Querying a User List
        List<? extends User> userList = osclient.identity().users().list();
        for(User user2 : userList){
            System.out.println(user2);
        }

        //Querying a User List with filterparam
        Map<String, String> filteringParams = new HashMap<>();
        String userName="********";
        filteringParams.put("name", userName);
        List<? extends User> userList = osclient.identity().users().list(filteringParams);
        for(User user2 : userList){
            System.out.println(user2);
        }

        //Querying a User List by name
        String userName="********";
        List<? extends User> userList = osclient.identity().users().getByName(userName);
        for(User user2 : userList){
            System.out.println(user2);
        }

        //Querying a User by domainId and name
        String domainId = "*********";
        String userName = "********";
        User user2 = osclient.identity().users().getByName(userName, domainId);
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

        //Querying Users in a User Group
        String groupId = "**********";
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("enabled", "true");
        List<? extends User> groupUserList = osclient.identity().groups().listGroupUsers(groupId, filteringParams);
        for(User user2 : groupUserList){
            System.out.println(user2);
        }

    }
}
