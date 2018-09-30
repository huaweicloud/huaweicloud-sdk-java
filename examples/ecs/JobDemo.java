package sample;

import java.util.ArrayList;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.openstack.ecs.v1.domain.Job;

public class JobDemo {
	public static void main(String[] args) {

		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxx"; //username
		String password = "xxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxx"; //projectID
		String userDomainId = "xxxxxxxxxxxxxxxx"; //domainID 

		// create connection
		OSClientV3 os = OSFactory.builderV3()
		.endpoint(authUrl)
		.credentials(user, password, Identifier.byId(userDomainId))
		.scopeToProject(Identifier.byId(projectId)).authenticate();
		
		String serverId = "cf660fef-2619-43bd-b801-38017102d87e";
		ArrayList<String> serverIds = new ArrayList<String>();
		serverIds.add(serverId);
		//batch reboot server
		String rebootJobId = os.ecs().servers().reboot(serverIds, RebootType.SOFT);
		
		Job job = os.ecs().jobs().get(rebootJobId);
		if (null != job) {
			System.out.println("get jobid success!" + job);
		} else {
			System.out.println("get jobid failed!");
		}
	}
}
