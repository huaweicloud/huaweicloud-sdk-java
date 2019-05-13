import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Group;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneGroup;

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

        //Creating a User Group
        Group sampleGroup = KeystoneGroup.builder().name("**********").description("**********").build();
        osclient.identity().groups().create(sampleGroup);

        //Deleting a User Group
        String groupId = "**********";
        osclient.identity().groups().delete(groupId);

        //Deleting a User from a User Group
        String groupId2 = "**********";
        String userId = "**********";
        ActionResponse resp = osclient.identity().groups().removeUserFromGroup(groupId2, userId);
        if(resp.isSuccess()){
            System.out.println("Delete user from user group successfully");
        }else{
            System.out.println("Delete user from user group failed : " + resp.getFault());
        }

        //Updating a User Group
        Group sampleGroup2 = KeystoneGroup.builder().id("**********").description("**********").build();
        osclient.identity().groups().update(sampleGroup2);

        //Querying a User Group
        String groupName = "**********";
        String domainId = "**********";
        Group sampleGroup3 = osclient.identity().groups().getByName(groupName, domainId);
        System.out.println(sampleGroup3);

        //Querying User Group Details
        String groupId3 = "**********";
        Group sampleGroup4 = osclient.identity().groups().get(groupId3);
        System.out.println(sampleGroup4);

        //Querying Whether a User Belongs to a User Group
        String groupId4 = "**********";
        String userId2 = "**********";
        ActionResponse response = osclient.identity().groups().checkGroupUser(groupId4, userId2);
        if(response.isSuccess()){
            System.out.println("The user belongs to this usergroup");
        }else{
            System.out.println("The user does not belong to this usergroup");
        }

    }
}
