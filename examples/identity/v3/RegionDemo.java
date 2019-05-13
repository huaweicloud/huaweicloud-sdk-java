import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Region;
import com.huawei.openstack4j.openstack.OSFactory;

import java.util.List;

public class RegionDemo {

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

        //Querying a Region List
        List<? extends Region> regionList = osclient.identity().regions().list();
        for(Region region : regionList){
            System.out.println(region);
        }

        //Querying Region Details
        String regionId = "**********";
        Region region = osclient.identity().regions().get(regionId);
        System.out.println("Region Id : " + region.getId());

    }
}
