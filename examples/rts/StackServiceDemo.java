import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.heat.Stack;
import com.huawei.openstack4j.model.heat.StackCreate;
import com.huawei.openstack4j.model.heat.StackUpdate;
import com.huawei.openstack4j.openstack.OSFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StackServiceDemo
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

        //create stack
        String testStackName = "my_stack";
        Map<String, String> stackparams = new HashMap<String, String>();
        stackparams.put("name", "server_name");
        stackparams.put("network", "9b94fa12-657e-4f7d-a97e-b9a440391636");
        stackparams.put("flavor", "s2.small.1");
        stackparams.put("image", "04c6329c-ad97-4b0e-be4d-2d8e2dce8427");
        stackparams.put("key_name", "KeyPair-hetianlu");
        StackCreate stackCreate = Builders.stack()
                .name(testStackName)
                .templateFromFile("server.yaml")
                .parameters(stackparams)
                .build();
        Stack stack = os.heat().stacks().create(stackCreate);
        System.out.println(stack);

        //create stack
        //read template
        StringBuilder sb = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(new File("server.yaml")));
            String str = null;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
        }
        catch (Exception e)
        {
            System.out.println("read template failed!");
        }
        Stack stack_1 = os.heat().stacks().create(testStackName, sb.toString(), stackparams, true, 30000L);
        System.out.println(stack_1);

        //get stack details
        String stackName = "stackName";
        String stackId = "1c27e11d-de74-4f88-9354-7354f7fb0348";
        Stack stackDetails = os.heat().stacks().getDetails(stackName, stackId);
        System.out.println(stackDetails);

        //get stack by name
        Stack stack_2 = os.heat().stacks().getStackByName(stackName);
        System.out.println(stack_2);

        //list stacks
        List<? extends Stack> stacks = os.heat().stacks().list();
        for (Stack _stack : stacks)
        {
            System.out.println(_stack);
        }

        //update stack
        StackUpdate stackUpdate = Builders.stackUpdate()
                .templateFromFile("server.yaml")
                .parameters(stackparams)
                .build();
        ActionResponse actionResponse = os.heat().stacks().update(stackName, stackId, stackUpdate);
        System.out.println(actionResponse);

        //delete stack
        ActionResponse actionResponse_1 = os.heat().stacks().delete(stackName, stackId);
        System.out.println(actionResponse_1);
    }
}
