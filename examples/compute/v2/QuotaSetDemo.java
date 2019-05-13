package sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.compute.Limits;
import com.huawei.openstack4j.model.compute.QuotaSet;

public class QuotaSetDemo {
	public static void main(String[] args) {

		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String username = "xxxxxxx"; //username
		String password = "xxxxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainID
		
		// create connection
		OSClientV3 os = OSFactory.builderV3()
			.endpoint(authUrl)
			.credentials(username, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();
		
		//get quotaset
		QuotaSet quotaSet = os.compute().quotaSets().get(projectId);
		if (null != quotaSet) {
			System.out.println("get quotaSet success, id = " + quotaSet.getId());
		} else {
			System.out.println("get quotaSet failed");
		}
		
		//get quotaSet limit
		Limits limits = os.compute().quotaSets().limits();
		if (null != limits) {
			System.out.println("get limits success, absolute = " + limits.getAbsolute());
		} else {
			System.out.println("get limits failed");
		}
	}
}
