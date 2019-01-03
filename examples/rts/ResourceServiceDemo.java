import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.heat.Resource;
import com.huawei.openstack4j.openstack.OSFactory;

public class ResourceServiceDemo
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

        //show resource
        String stackName = "my_stack";
        String stackId = "017f8dc3-f424-4edf-a408-803d63a27cda";
        String resourceName = "server";
        Resource resource = os.heat().resources().show(stackName, stackId, resourceName);
        System.out.println(resource);

        //list stack resources
        for (Resource res : os.heat().resources().list(stackName, stackId))
        {
            System.out.println(res);
        }

        //list stack resources
        for (Resource res : os.heat().resources().list(stackName))
        {
            System.out.println(res);
        }
    }
}
