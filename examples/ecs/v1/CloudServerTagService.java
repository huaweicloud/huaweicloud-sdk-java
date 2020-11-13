package com.huawei.openstack.sample;
import java.util.ArrayList;
import java.util.List;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.ecs.v1.domain.*;

public class CloudServerTagService {
	public static void main(String[] args) throws InterruptedException {

		// Using credentials for authentication
		String authUrl = "https://iam.XXX.YYY.com/v3"; //endpoint Url
		String user = "xxxxxxxx"; //username
		String password = "xxxxxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

		//create connection
		OSClientV3 os = OSFactory.builderV3()
				.endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId))
				.scopeToProject(Identifier.byId(projectId)).authenticate();
		List<ServerTags> serverTags = new ArrayList<>();
		serverTags.add(ServerTags.builder().key("key1").value("value1").build());
		String serverId = "server-id";

		//cloud server tags add
		ActionResponse addActionResponse = os.ecs().tags().add(serverId,serverTags);
		if (addActionResponse.isSuccess()) {
			System.out.println("add server tags success");
		} else {
			System.out.println("add server tags failed");
		}

		//cloud server tags delete
		ActionResponse deleteActionResponse = os.ecs().tags().delete(serverId,serverTags);
		if (deleteActionResponse.isSuccess()) {
			System.out.println("delete server tags success");
		} else {
			System.out.println("delete server tags failed");
		}

		//get cloud server tags
		CloudServerTag cloudServerTag = os.ecs().tags().list(serverId);
		if (cloudServerTag != null){
			List<ServerTags> serverTagsList = cloudServerTag.getServerTags();
			System.out.println("size:"  + serverTagsList.size());
			if (serverTagsList.size()>0){
				ServerTags nowServerTags = cloudServerTag.getServerTags().get(0);
				System.out.println("key:" + nowServerTags.getKey());
				System.out.println("value:" + nowServerTags.getValue());
			}
		}

		//get project tags
		ProjectTag projectTag = os.ecs().tags().listProjectTags();
		if (projectTag != null){
			System.out.println("tag size:" + projectTag.getTags().size());
			if (projectTag.getTags().size()>0){
				ProjectTag.Tag nowTag = projectTag.getTags().get(0);
				System.out.println("key:" + nowTag.getKey());
				List<String> values = nowTag.getValues();
				System.out.println("values:" + values);
				if (values != null && values.size()>0){
					String value = values.get(0);
					System.out.println("value:" + value);
				}
			}
		}
	}
}
