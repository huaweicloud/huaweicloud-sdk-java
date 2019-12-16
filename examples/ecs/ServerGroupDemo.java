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
		
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //终端节点（Endpoint）
		//authUrl请在 华为云>开发者>API Endpoint 中查询
		
		String user = "xxxxx"; //华为云账号名
		String password = "xxxxx"; //华为云登陆密码
		
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //项目ID
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //账号ID
		//projectId和userDomainId请在 华为云控制台>用户名称>我的凭证>API凭证 中查询

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
