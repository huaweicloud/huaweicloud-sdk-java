package com.huawei.openstack.sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.storage.block.domain.AvailabilityZone;

public class ZoneDemo
{
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

		//get availability_zone list
		List<? extends AvailabilityZone> azList = os.blockStorage().zones().list();
		if (null != azList && azList.size() >= 0)
		{
			System.out.println("get azList success, count = " + azList.size());
		}
		else
		{
			System.out.println("get azList failed");
		}
		AvailabilityZone az = azList.get(0);
	}
}
