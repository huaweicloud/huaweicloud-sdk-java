package sample;

import java.util.List;

import java.util.ArrayList;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.eps.v1.domain.*;
import com.huawei.openstack4j.openstack.eps.v1.domain.action.EPResourceActionRequest;
import com.huawei.openstack4j.openstack.eps.v1.domain.filter.EPResourceFilterRequest;
import com.huawei.openstack4j.openstack.eps.v1.domain.filter.EPResourceFilterResponse;


public class EPDemo {
    public static void main(String[] args) {

        // step 1: setup the authentication credit
        String user = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        String password = "xxxxxxxxxxxxxxx";
        String userDomainId = "xxxxxxxxxxxxxxx";
        String authUrl = "https://iam.xxx.com/v3";

        // step 2: initial client
        OSClientV3 osClient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId)).authenticate();

        // step 3: query EP quotas
        EPQuotaResponse quotaResponse =  osClient.eps().epService().quotas();
        System.out.println(quotaResponse);

        // step 4: create EP
        EPCreateRequest createRequest = new EPCreateRequest();
        createRequest.setName("xxx");
        createRequest.setDescription("yyyy");
        EPCreateResponse createResponse =  osClient.eps().epService().create(createRequest);
        System.out.println(createResponse);

        // step 5: query all EP
        EPListResponse response = osClient.eps().epService().list();
        System.out.println(response);

        if(response == null || response.getEnterpriseProjects() == null || response.getEnterpriseProjects().size() == 0)
        {
            return;
        }
        // step 6: query EP detail
        String epID  = response.getEnterpriseProjects().get(0).getId();
        EPQueryResponse ep = osClient.eps().epService().get(epID);
        System.out.println(ep);

        // step 7: modify EP
        EPModifyRequest modifyRequest = new EPModifyRequest();
        modifyRequest.setName("ddddd");
        modifyRequest.setDescription("uuuu");
        EPQueryResponse modifyResponse = osClient.eps().epService().modify(epID, modifyRequest);
        System.out.println(modifyResponse);

        // step 8: enable or disable EP
        EPActionRequest actionRequest = new EPActionRequest();
        //enable:启用 disable:停用
        actionRequest.setAction("disable");
        ActionResponse actionResponse = osClient.eps().epService().action(epID, actionRequest);
        System.out.println(actionResponse);

        // step 9: filter EP Resource
        EPResourceFilterRequest  filterRequest = new EPResourceFilterRequest();
        //projectid根据实际用户的为准
        List<String> projects = new ArrayList<>();
        projects.add("9a24ebd5e4ee45c0ac9336c6f9d83ec2");
        //支持的资源类型已官网为准
        List<String> resourceTypes = new ArrayList<>();
        resourceTypes.add("ecs");
        resourceTypes.add("disk");
        filterRequest.setProjects(projects);
        filterRequest.setResourceTypes(resourceTypes);
        EPResourceFilterResponse filterResponse = osClient.eps().epService().filterResource(epID, filterRequest);
        System.out.println(filterResponse);

        if(filterResponse == null || filterResponse.getTotalCount() == 0)
        {
            return;
        }
        // step 10: migrate EP Resource
        EPResourceActionRequest resourceActionRequest = new EPResourceActionRequest();
        resourceActionRequest.setAction("bind");
        resourceActionRequest.setProjectId(filterResponse.getResources().get(0).getProjectId());
        resourceActionRequest.setResourceType(filterResponse.getResources().get(0).getResourceType());
        resourceActionRequest.setResourceId(filterResponse.getResources().get(0).getResourceId());
        ActionResponse resourceActionResponse = osClient.eps().epService().migrateResource(epID, resourceActionRequest);
        System.out.println(resourceActionResponse);
    }
}