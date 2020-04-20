package tms.v1;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.tms.v1.domain.PredefineTagRequest;
import com.huawei.openstack4j.openstack.tms.v1.domain.PredefineTags;
import com.huawei.openstack4j.openstack.tms.v1.internal.TagFilterOption;

import java.util.ArrayList;
import java.util.List;

public class TagDemo
{
    public static void main(String[] args) {

        // step 1: setup the authentication credit
        String user = "*********";
        String password = "*********";
        String userDomainId = "*********";
        String authUrl = "https://iam.xxx.com/v3";
        // step 2: initial client
        OSClientV3 osClient = OSFactory.builderV3().endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId)).authenticate();

        // step 3: create Tag
        List<PredefineTagRequest> createTagList = new ArrayList<>();
        PredefineTagRequest createTagRequest = new PredefineTagRequest("key1", "value1");
        createTagList.add(createTagRequest);
        String createResponse =  osClient.tms().tags().create(createTagList);
        System.out.println(createResponse);

        // step 4: query Tag
        PredefineTags queryTags  =  osClient.tms().tags().list();
        System.out.println(queryTags.toString());

        // step 5: query Tag with param
        TagFilterOption option = TagFilterOption.create().key("key1");
        PredefineTags queryTagsWithParam  =  osClient.tms().tags().list(option);
        System.out.println(queryTagsWithParam.toString());

        // step 6: delete Tag
        List<PredefineTagRequest> deleteTagList = new ArrayList<>();
        PredefineTagRequest deleteTagRequest = new PredefineTagRequest("key1", "value1");
        deleteTagList.add(deleteTagRequest);
        String deleteResponse =  osClient.tms().tags().delete(deleteTagList);
        System.out.println(deleteResponse);

        // step 5: modify Tag
        PredefineTagRequest oldTag = new PredefineTagRequest("key1", "value1");
        PredefineTagRequest newTag = new PredefineTagRequest("key1", "value1");
        ActionResponse modifyResponse =  osClient.tms().tags().modify(oldTag, newTag);
        System.out.println(modifyResponse.toString());
    }
}