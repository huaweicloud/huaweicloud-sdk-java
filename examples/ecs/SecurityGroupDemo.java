package sample;

import java.util.List;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.compute.IPProtocol;
import com.huawei.openstack4j.model.compute.SecGroupExtension;

public class SecurityGroupDemo {
	public static void main(String[] args) {
		
		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxxxx"; //username
		String password = "xxxxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId
		
		// create connection
		OSClientV3 os = OSFactory.builderV3()
		.endpoint(authUrl)
		.credentials(user, password, Identifier.byId(userDomainId))
		.scopeToProject(Identifier.byId(projectId)).authenticate();
		
		//create securityGroups
		SecGroupExtension secGroup = os.compute().securityGroups().create("test-name", "for sdk test");
		if (null != secGroup) {
			System.out.println("create secGroup success, id = " + secGroup.getId());
		} else {
			System.out.println("create secGroup failed");
		}
				
		//create securityGroups rule
		SecGroupExtension.Rule rule = os.compute().securityGroups().createRule(Builders.secGroupRule()
		.parentGroupId(secGroup.getId())
		.protocol(IPProtocol.TCP)
		.cidr("0.0.0.0/0")
		.range(80, 80).build());
		
		if (null != rule) {
			System.out.println("create rule success, id = " + rule.getId());
		} else {
			System.out.println("create rule failed");
		}

		//update securityGroups
		SecGroupExtension updSecurityGroup = os.compute().securityGroups().update(secGroup.getId(), "test", "testDesc");
		System.out.println("updated securityGroup is: " + updSecurityGroup);

		//get securityGroups
		SecGroupExtension sgInfo = os.compute().securityGroups().get(secGroup.getId());
		if (null != sgInfo) {
			System.out.println("get sgInfo success, name = " + sgInfo.getName());
		} else {
			System.out.println("get sgInfo failed");
		}
				
		//get list of securityGroups
		List<? extends SecGroupExtension> sgList = os.compute().securityGroups().list();
		if (sgList.size() > 0) {
			System.out.println("get sgList success, size = " + sgList.size());
		} else {
			System.out.println("get sgList failed");
		}
				
		//delete securityGroups rule
		ActionResponse repRule = os.compute().securityGroups().deleteRule(rule.getId());
		if (repRule.isSuccess()) {
			System.out.println("delete secGroupRule success");
		} else {
			System.out.println("delete secGroupRule failed");
		}
				
		//delete securityGroups
		ActionResponse repSecGroup = os.compute().securityGroups().delete(secGroup.getId());
		if (repSecGroup.isSuccess()) {
			System.out.println("delete secGroup success");
		} else {
			System.out.println("delete secGroup failed");
		}
	}
}