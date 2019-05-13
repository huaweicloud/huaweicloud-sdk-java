package sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.compute.Flavor;

public class FlavorDemo {
	public static void main(String[] args) {
		
		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxxxxx"; //username
		String password = "xxxxxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

		// create connection
		OSClientV3 os = OSFactory.builderV3()
		.endpoint(authUrl)
		.credentials(user, password, Identifier.byId(userDomainId))
		.scopeToProject(Identifier.byId(projectId)).authenticate()
		
		//get list of flavor
		List<? extends Flavor> flavors = os.compute().flavors().list();
		String flrId = null;
		if (flavors.size() > 0) {
			flrId = flavors.get(0).getId();
			System.out.println("get flavorList success, size = " + flavors.size());
		} else {
			System.out.println("get flavorList failed");
		}
		
		//get flavor
		Flavor flavor = os.compute().flavors().get(flrId);
		if (null != flavor) {
			System.out.println("get flavor success, name = " + flavor.getName());
		} else {
			System.out.println("get flavor failed");
		}
	}
}
