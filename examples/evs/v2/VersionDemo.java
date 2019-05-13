package com.huawei.openstack.sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.evs.v2.domain.Job;
import com.huawei.openstack4j.openstack.storage.block.domain.Version;

public class VersionDemo {
	public static void main(String[] args) throws InterruptedException
	{

		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxx"; //username
		String password = "xxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

		//create connection
		OSClientV3 os =
				OSFactory.builderV3().endpoint(authUrl).credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId)).authenticate();

//		// get versions
//		List<? extends Version> versions= os.evs().versions().versions();
//		System.out.println(versions);
//		if (null != versions && versions.size() >= 0)
//		{
//			System.out.println("get versions success, count = " + versions.size());
//		}
//		else
//		{
//			System.out.println("get versions failed");
//		}
//		Version version = versions.get(0);
//
//		// get versionsV2
//		List<? extends Version> versionsV2= os.evs().versions().version("v2");
//		System.out.println(versionsV2);
//		if (null != versionsV2 && versionsV2.size() >= 0)
//		{
//			System.out.println("get versions success, count = " + versionsV2.size());
//		}
//		else
//		{
//			System.out.println("get versions failed");
//		}
//		Version versionV2 = versionsV2.get(0);
	}
}
