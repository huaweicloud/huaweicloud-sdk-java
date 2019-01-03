import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.heat.SoftwareConfigService;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.heat.SoftwareConfig;
import com.huawei.openstack4j.openstack.OSFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.util.HashMap;

public class SoftwareConfigServiceDemo
{
    public static void main(String[] args)
    {
        // Using credentials for authentication
        String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
        String user = "xxxxx"; //username
        String password = "xxxxx"; //password
        String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
        String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

        // create connection
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId))
                .authenticate();

        //create SoftwareConfig
        SoftwareConfigService softwareConfigService = os.heat().softwareConfig();
        HashMap obj = null;
        try
        {
            obj = new Yaml().loadAs(new FileReader("config.yaml"), HashMap.class);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        HashMap resources = (HashMap) obj.get("resources");
        HashMap configTest = (HashMap) resources.get("config_test");
        HashMap properties = (HashMap) configTest.get("properties");
        Object config = properties.get("config");
        Object group = properties.get("group");
        SoftwareConfig softwareConfig = Builders.softwareConfig()
                .config(config == null ? "" : config.toString())
                .group(group == null ? "" : group.toString())
                .name("config_1")
                .build();
        System.out.println(softwareConfigService.create(softwareConfig));

        //show SoftwareConfig
        String configId = "690f9b5e-0ccb-41cf-aad1-509c71b42a7f";
        System.out.println(os.heat().softwareConfig().show(configId));

        //delete SoftwareConfig
        System.out.println(os.heat().softwareConfig().delete(configId));
    }
}
