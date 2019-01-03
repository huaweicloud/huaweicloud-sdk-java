import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.heat.Event;
import com.huawei.openstack4j.openstack.OSFactory;

public class EventsServiceDemo
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

        //show resource event
        String stackName = "stackName";
        String stackId = "1c27e11d-de74-4f88-9354-7354f7fb0348";
        String resourceName = "resourceName";
        String eventId = "1c27e11d-de74-4f88-9354-7354f7fb0348";
        Event events = os.heat().events().show(stackName, stackId, resourceName, eventId);
        System.out.println(events);

        //list resource event
        for (Event event : os.heat().events().list(stackName, stackId))
        {
            System.out.println(event);
        }

        //list resource events
        for (Event event : os.heat().events().list(stackName, stackId, resourceName))
        {
            System.out.println(event);
        }
    }
}
