package sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.compute.ServerGroup;

public class ServerGroupDemo {
	public static void main(String[] args) {
		
		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxx"; //username
		String password = "xxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

		// create connection
		OSClientV3 os = OSFactory.builderV3()
		.endpoint(authUrl)
		.credentials(user, password, Identifier.byId(userDomainId))
		.scopeToProject(Identifier.byId(projectId)).authenticate();
		
		//create serverGroup
		ServerGroup serverGroup = os.compute().serverGroups().create("zt-servergroup", "anti-affinity");
		if (null != serverGroup) {
			System.out.println("create serverGroup success, id = " + serverGroup.getId());
		} else {
			System.out.println("create serverGroup failed");
		}
		
		//get serverGroup
		ServerGroup serverGroupInfo = os.compute().serverGroups().get(serverGroup.getId());
		if (null != serverGroupInfo) {
			System.out.println("get serverGroupInfo success, name = " + serverGroupInfo.getName());
		} else {
			System.out.println("get serverGroupInfo failed");
		}
		
		//get list of serverGroup
		List<? extends ServerGroup> list = os.compute().serverGroups().list();
		if (list.size() > 0) {
			System.out.println("get ServerGroupList success, size = " + list.size());
		} else {
			System.out.println("get ServerGroupList failed");
		}
		
		//delete serverGroup
		ActionResponse rep = os.compute().serverGroups().delete(serverGroup.getId());
		if (rep.isSuccess()) {
			System.out.println("deleteserverGroup success");
		} else {
			System.out.println("deleteserverGroup failed");
		}
	}
}
