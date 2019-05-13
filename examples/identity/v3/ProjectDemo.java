import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Project;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneProject;

import java.util.List;

public class ProjectDemo {

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

        //Creating a Project
        String projectName = "**********";
        String domainId = "**********";
        String description = "**********";
        boolean enabled = true;
        osclient.identity().projects().create(domainId, projectName, description, enabled);

        //Modifying Project Data
        String projectId = "**********";
        String projectName2 = "**********";
        String description2 = "**********";
        Project sampleProject = KeystoneProject.builder().id(projectId).name(projectName2).description(description2).build();
        osclient.identity().projects().update(sampleProject);

        //Querying Project Information Based on the Specified Criteria
        String domainId2 = "**********";
        String projectName3 = "**********";
        Project sampleProject2 = osclient.identity().projects().getByName(projectName3, domainId2);
        System.out.println(sampleProject2);

        //Querying a User Project List
        String userId = "**********";
        List<? extends Project> projectList = osclient.identity().users().listUserProjects(userId);
        for(Project project : projectList){
            System.out.println(project);
        }

        //Querying the List of Projects Accessible to Users
        String tokenId = osclient.getToken().getId();
        List<? extends Project> projectList2 = osclient.identity().tokens().getProjectScopes(tokenId);
        for(Project project : projectList2){
            System.out.println(project);
        }

        //Querying Information About a Specified Project
        String projectId2 = "**********";
        Project sampleProject3 = osclient.identity().projects().get(projectId2);
        System.out.println(sampleProject3);

    }
}