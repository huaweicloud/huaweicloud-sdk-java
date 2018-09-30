package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.network.ext.LbMethod;
import com.huawei.openstack4j.model.network.ext.LbPoolV2;
import com.huawei.openstack4j.model.network.ext.LbPoolV2Update;
import com.huawei.openstack4j.model.network.ext.Protocol;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLbPoolV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLbPoolV2Update;

public class Pool {

	public static void main(String[] args) {
		                                                                                                 
		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

	OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
			.credentials(user, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();
	
			
//Create a Pool
		String lsnr_id = "******";
		LbPoolV2 pool = NeutronLbPoolV2.builder().adminStateUp(true).listenerId(lsnr_id).protocol(Protocol.HTTP).name("HTTP-group").lbMethod(LbMethod.LEAST_CONNECTIONS).build();
		osclient.networking().lbaasV2().lbPool().create(pool);
//List all Pools
		osclient.networking().lbaasV2().lbPool().list();
//Query info about a Pool
		String pool_id = "******";
		osclient.networking().lbaasV2().lbPool().get(pool_id);
//Update a Pool
		LbPoolV2Update pool_mod = NeutronLbPoolV2Update.builder().name("HTTP-pool").build();
		osclient.networking().lbaasV2().lbPool().update(pool_id, pool_mod);
//Delete a Pool
		String pool_id_todel = "******";
		osclient.networking().lbaasV2().lbPool().delete(pool_id_todel);
		}

}