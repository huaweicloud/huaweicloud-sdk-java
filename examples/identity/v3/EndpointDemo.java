import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Endpoint;
import com.huawei.openstack4j.openstack.OSFactory;

import java.util.List;

public class EndpointDemo {

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

        //Querying Endpoints
        List<? extends Endpoint> endpointList = osclient.identity().serviceEndpoints().listEndpoints();
        for(Endpoint endpoint : endpointList){
            System.out.println(endpoint);
        }

        //Querying Endpoint Details
        String endpointId = "**********";
        Endpoint sampleEndpoint = osclient.identity().serviceEndpoints().getEndpoint(endpointId);
        System.out.println(sampleEndpoint);

    }
}
