import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Token;
import com.huawei.openstack4j.openstack.OSFactory;

public class TokenDemo {
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

        //Checking the Validity of a Specified Token
        String tokenId = "**********";
        Token token = osclient.identity().tokens().get(tokenId);
        System.out.println(token);

    }
}
