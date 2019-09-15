package sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.common.Quota;

public class QuotaDemo {
    public static void main(String[] args) {

        // step 1: setup the authentication credit
        String user = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        String password = "xxxxxxxxxxxxxxx";
        String projectId = "xxxxxxxxxxxxxxx";
        String userDomainId = "xxxxxxxxxxxxxxxxxx";
        String authUrl = "https://iam.xxx.yyy.com/v3";

        // step 2: initial client
        OSClientV3 osClient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        // step 3: Use client to visit CES get quota API
        List<Quota> list = osClient.cloudEye().quotas().get();
        System.out.println(list);
    }
}