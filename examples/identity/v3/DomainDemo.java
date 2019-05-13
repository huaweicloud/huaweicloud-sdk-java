import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Domain;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.v3.domain.PasswordConfig;

import java.util.List;

public class DomainDemo {

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

        //Querying the List of Domains Accessible to Users
        String tokenId = osclient.getToken().getId();
        List<? extends Domain> domainList = osclient.identity().tokens().getDomainScopes(tokenId);
        for(Domain domain : domainList){
            System.out.println(domain);
        }

        //Query domain password strength config
        String domainId = "**********";
        PasswordConfig sampleConfig = osclient.identity().domains().getDomainPasswordConfig(domainId);
        System.out.println(sampleConfig);

        //Query domain password strength config by option
        String domainId2 = "**********";
        String option = "password_regex";   //the value of option can be 'password_regex' or 'password_regex_description'
        PasswordConfig sampleConfig2 = osclient.identity().domains().getDomainPasswordConfigByOption(domainId2, option);
        System.out.println(sampleConfig2);

    }
}
