package com.huawei.openstack.sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientAKSK;
import com.huawei.openstack4j.model.identity.v3.User;
import com.huawei.openstack4j.openstack.OSFactory;

public class AKSKDemo {

	public static void main(String[] args) {

		// setup the authentication credit
		String ak = "replace-your-ak";
		String sk = "replace-your-sk";
		String projectId = "replace-your-projectId";
		String userDomainId = "replace-with-your-domainId";
		String region = "replace-your-region";
		String cloudDomainName = "replace-your-cloudDomainName";

		OSClientAKSK osclient = OSFactory.builderAKSK().credentials(ak, sk, region, projectId, userDomainId, cloudDomainName).authenticate();

		// use client to visit IAM list user API
		List<? extends User> list = osclient.identity().users().list();
		if(list.size() > 0)
		{
			System.out.println("get userList success, size = " + list.size());
			for (User user : list) {
				System.out.println(user);
			}
		}
		else {
			System.out.println("no user exists.");
		}
	}
}
