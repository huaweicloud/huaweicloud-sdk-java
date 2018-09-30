package sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.compute.Image;

public class ImageDemo {
	public static void main(String[] args){

		// Using credentials for authentication	
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxxxxx"; //username
		String password = "xxxxxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //Project Id
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //Domain Id
		
		// create connection
		OSClientV3 os = OSFactory.builderV3()	
			.endpoint(authUrl)
			.credentials(user, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();
		
		String imageId = "930b2347-9bcf-41e7-afe5-d529078ce3fb"; 
		
		//get image
		Image image = os.compute().images().get(imageId);
		if (null != image) {
			System.out.println("getImage success, name = " + image.getName());
		} else {
			System.out.println("getImage failed");
		}
		
		//get list of image
		List<? extends Image> imgList = os.compute().images().list();
		if (imgList.size() > 0) {
			System.out.println("getImageList success, size = " + imgList.size());
		} else {
			System.out.println("getImageList failed");
		}
		
		//show list of image detail
		List<? extends Image> imgListDetail = os.compute().images().list(true);
		if (imgListDetail.size() > 0) {
			System.out.println("getImageListDetail success, size = " + imgListDetail.size());
		} else {
			System.out.println("getImageListDetail failed");
		}
		
		//delete image
		ActionResponse rep = os.compute().images().delete(imageId);
		if (rep.isSuccess()) {
			System.out.println("deleteImage success");
		} else {
			System.out.println("deleteImage failed");
		}
	}
}