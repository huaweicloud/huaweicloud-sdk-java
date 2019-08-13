import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Service;
import com.huawei.openstack4j.openstack.OSFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDemo {
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

        //Querying Services
        List<? extends Service> serviceList = osclient.identity().serviceEndpoints().list();
        for(Service service : serviceList){
            System.out.println(service);
        }

        //Querying Services with filterparam
        Map<String, String> filteringParams = new HashMap<>();
        filteringParams.put("type", "compute");
        List<? extends Service> serviceList = osclient.identity().serviceEndpoints().list(filteringParams);
        for(Service service : serviceList){
            System.out.println(service);
        }

        //Querying Service Details
        String serviceId = "**********";
        Service sampleService = osclient.identity().serviceEndpoints().get(serviceId);
        System.out.println(sampleService);

    }
}
