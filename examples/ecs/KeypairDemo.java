package sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.compute.Keypair;

public class KeypairDemo {
	public static void main(String[] args) {
		
		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxxx"; //username
		String password = "xxxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId
	
		//create connection
		OSClientV3 os = OSFactory.builderV3()
			.endpoint(authUrl)
			.credentials(user, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();
		
		String testPublicKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; 

		//create keypair
		Keypair keypair = os.compute().keypairs().create("keypair-test", testPublicKey);
		if (null != keypair) {
			System.out.println("create keypair success, PublicKey = " + keypair.getPublicKey());
		} else {
			System.out.println("create keypair failed");
		}
		
		//get keypair
		Keypair keypairInfo = os.compute().keypairs().get(keypair.getName());
		if (null != keypairInfo) {
			System.out.println("get keypairInfo success, id = " + keypairInfo.getId());
		} else {
			System.out.println("get keypairInfo failed");
		}
		
		//get list of keypair
		List<? extends Keypair> list = os.compute().keypairs().list();
		if (list.size() > 0) {
			System.out.println("get keypairList success, size = " + list.size());
		} else {
			System.out.println("get keypairList failed");
		}
		
		//delete keypair
		ActionResponse rep = os.compute().keypairs().delete(keypair.getName());
		if (rep.isSuccess()) {
			System.out.println("deletekeypair success");
		} else {
			System.out.println("deletekeypair failed");
		}
	}
}
