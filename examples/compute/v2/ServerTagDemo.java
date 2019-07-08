package sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerTag;
import com.huawei.openstack4j.model.compute.Server;

import java.util.ArrayList;
import java.util.List;

public class ServerTagDemo {
    public static void main(String[] args) {

        // Using credentials for authentication
        String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
        String user = "xxxxx"; //username
        String password = "xxxxx"; //password
        String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
        String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

        // create connection
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        String serverId = "xxxxxxxxxxxxxxxxxxxxxxxxx";
        String tag = "tag";

        //get server tags
        NovaServerTag queryTags = os.compute().serverTags().list(serverId);
        if (null != queryTags) {
            System.out.println("get cloud server tags success, tags = " + queryTags.getTags());
        } else {
            System.out.println("get cloud server tags failed");
        }

        //create single server tags
        List<String> tags = new ArrayList<>();
        tags.add("test1");
        NovaServerTag novaServerTag = NovaServerTag.builder().tags(tags).build();
        NovaServerTag createTags =  os.compute().serverTags().addTags(serverId, novaServerTag);
        if (null != createTags) {
            System.out.println("create cloud server tags success, tags = " + createTags.getTags());
        } else {
            System.out.println("create cloud server tags failed");
        }

        //delete server tags
        ActionResponse deleteRep = os.compute().serverTags().delete(serverId, tag);
        if (deleteRep.isSuccess()) {
            System.out.println("deleteTags success");
        } else {
            System.out.println("deleteTags failed");
        }

        //check whether server has a specified tags.
        ActionResponse tagsReq = os.compute().serverTags().check(serverId, tag);
        if (tagsReq.isSuccess()) {
            System.out.println("queryTags success");
        } else {
            System.out.println("queryTags failed");
        }

        //add tags to the specified server
        ActionResponse addTagsReq =  os.compute().serverTags().addSingle(serverId, tag);
        if (addTagsReq.isSuccess()) {
            System.out.println("addTags success");
        } else {
            System.out.println("addTags failed");
        }

        //delete server all tags
        ActionResponse deleteTagsReq = os.compute().serverTags().deleteAll(serverId);
        if (deleteTagsReq.isSuccess()) {
            System.out.println("deleteTags success");
        } else {
            System.out.println("deleteTags failed");
        }
    }
}
