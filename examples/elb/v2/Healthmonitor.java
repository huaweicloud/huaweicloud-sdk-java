package com.huawei.openstack.sample;

import javax.ws.rs.HttpMethod;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.network.ext.HealthMonitorType;
import com.huawei.openstack4j.model.network.ext.HealthMonitorV2;
import com.huawei.openstack4j.model.network.ext.HealthMonitorV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorV2Update;

public class Healthmonitor {

	public static void main(String[] args) {
        
		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

	OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
			.credentials(user, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();
	
			
			
		String pool_id = "******";
//Create a healthmonitor		
		HealthMonitorV2 hm = NeutronHealthMonitorV2.builder().adminStateUp(true).poolId(pool_id).delay(10).maxRetries(10).timeout(10).type(HealthMonitorType.HTTP).build();
		osclient.networking().lbaasV2().healthMonitor().create(hm);
		
//List all health monitor
		osclient.networking().lbaasV2().healthMonitor().list();
		
//Query info about a healthmonitor
		String hm_id = "";
		osclient.networking().lbaasV2().healthMonitor().get(hm_id);
		
//Update a healthmonitor
		HealthMonitorV2Update hm_mod = NeutronHealthMonitorV2Update.builder().delay(3).maxRetries(5).httpMethod(HttpMethod.GET).expectedCodes("200").build();
		osclient.networking().lbaasV2().healthMonitor().update(hm_id, hm_mod);
		
//Delete a healthmonitor
		osclient.networking().lbaasV2().healthMonitor().delete(hm_id);
	}

}