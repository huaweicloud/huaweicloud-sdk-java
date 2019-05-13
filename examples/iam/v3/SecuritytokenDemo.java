import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.iam.domain.*;

import java.util.Arrays;

public class SecuritytokenDemo {

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

        //Creating Securitytoken by constructing auth entity(token)
        String durationSeconds = "**********";
        Auth auth = Auth.builder().identity(AuthIdentity.builder().methods(Arrays.asList("token")).token(AuthToken.builder().durationSeconds(durationSeconds).build()).build()).build();
        System.out.println(auth);
        Securitytoken sampleSecuritytoken2 = osclient.iam().securitytokens().create(auth);
        System.out.println(sampleSecuritytoken2);

        //Creating Securitytoken by constructing auth entity(assume_role)
        String agencyName = "**********";
        String domainName = "**********";
        String projectId = "**********";
        String durationSeconds2 = "**********";
        Auth auth2 = Auth.builder().identity(AuthIdentity.builder().methods(Arrays.asList("assume_role")).assumeRole(AuthAssumeRole.builder().agencyName(agencyName).domainName(domainName).durationSeconds(durationSeconds2).build()).build()).scope(AuthScope.builder().project(AuthProject.builder().id(projectId).build()).build()).build();
        System.out.println(auth);
        Securitytoken sampleSecuritytoken3 = osclient.iam().securitytokens().create(auth2);
        System.out.println(sampleSecuritytoken3);

    }
}
