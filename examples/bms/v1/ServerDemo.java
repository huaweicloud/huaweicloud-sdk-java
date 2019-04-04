/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * 	use this file except in compliance with the License. You may obtain a copy of
 * 	the License at
 *
 * 	    http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * 	License for the specific language governing permissions and limitations under
 * 	the License.
 *******************************************************************************/
package com.huawei.openstack.sample.v1;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.bms.v1.contants.DecBaremetal;
import com.huawei.openstack4j.openstack.bms.v1.contants.VolumeType;
import com.huawei.openstack4j.openstack.bms.v1.domain.BareMetaServer;
import com.huawei.openstack4j.openstack.bms.v1.domain.DataVolume;
import com.huawei.openstack4j.openstack.bms.v1.domain.SchedulerHints;
import com.huawei.openstack4j.openstack.bms.v1.domain.ServerCreate;
import com.huawei.openstack4j.openstack.bms.v1.domain.ServerExtendParam;
import com.huawei.openstack4j.openstack.common.AsyncRespEntity;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.ecs.v1.contants.PeriodType;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Network;

public class ServerDemo {
    public static void main(String[] args) {
    	String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";
		
		OSClientV3 osclient = OSFactory.builderV3().endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();

        //1.create bms server
        String serverId = "******";
        String imageRef = "******";
        String flavorRef = "******";
        String vpcId = "******";
        String subnetId = "******";
        String serverName = "******";
        String availabilityZone = "******";
        String ipAddress = "******";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("op_svc_userid", "*****");
        String securityGroupId = "******";
		String keyName = "******";
		List<IdResourceEntity> securityGroups = new ArrayList<IdResourceEntity>();
		IdResourceEntity idResourceEntity = new IdResourceEntity();
		idResourceEntity.setId(securityGroupId);
		securityGroups.add(idResourceEntity);
        ServerCreate creation = ServerCreate.builder().availabilityZone(availabilityZone).name(serverName).imageRef(imageRef).flavorRef(flavorRef)
                .dataVolumes(Arrays.asList(DataVolume.builder().type(VolumeType.SATA).shareable(false).size(10).build()))
                .vpcId(vpcId)
                .networks(Arrays.asList(Network.builder().subnetId(subnetId).ipAddress(ipAddress).build())).count(1)
                .schedulerHints(SchedulerHints.builder().decBaremetal(DecBaremetal.share).build()).extendParam(ServerExtendParam.builder().isAutoPay(true).periodType(PeriodType.month).periodNum(1).build())
                .metadata(map)
        		.keyName(keyName)
        		.securityGroups(securityGroups).build();
        
        AsyncRespEntity bareMetaServerCreate = osclient.bms().servers().create(creation);
        System.out.println(bareMetaServerCreate);
        System.out.println("create BMS Server success...");
        
        
        //2.query bms server details
        BareMetaServer BareMetaServerDetail = osclient.bms().servers().get(serverId);
        System.out.println(BareMetaServerDetail);
        System.out.println("query BMS Server success...");
        
        
        //3.rename bms server 
        String newServerName = "*******";
        BareMetaServer bareMetaServer = osclient.bms().servers().rename(serverId, newServerName);
        System.out.println(bareMetaServer);
        System.out.println("rename BMS Server success...");
    }
}
