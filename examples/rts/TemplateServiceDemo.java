import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.heat.domain.HeatTemplate;

public class TemplateServiceDemo
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

        //get template as Map
        String stackNameOrId = "stack_name";
        System.out.println(os.heat().templates().getTemplateAsMap(stackNameOrId));

        //get template as Map
        String stackName = "stack_name";
        String stackId = "1c27e11d-de74-4f88-9354-7354f7fb0348";
        System.out.println(os.heat().templates().getTemplateAsMap(stackName, stackId));

        //get template as String
        String template = os.heat().templates().getTemplateAsString(stackName, stackId);
        System.out.println(template);

        //validate template
        System.out.println(os.heat().templates().validateTemplate(template));

        //validate template
        HeatTemplate heatTemplate = new HeatTemplate();
        heatTemplate = (HeatTemplate) heatTemplate.toBuilder().templateJson(template).build();
        System.out.println(os.heat().templates().validateTemplate(heatTemplate));

        //validate template
        String template_url = "/PATH_TO_HEAT_TEMPLATES/WordPress_Single_Instance.template";
        System.out.println(os.heat().templates().validateTemplateByURL(template_url));

    }
}
