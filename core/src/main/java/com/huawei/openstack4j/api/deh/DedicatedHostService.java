 /*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co.,Ltd.                                         
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
package com.huawei.openstack4j.api.deh;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHost;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHost.DedicatedHosts;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHostCreate;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHostType;
import com.huawei.openstack4j.openstack.deh.domain.DedicatedHostUpdate;

public interface DedicatedHostService extends RestService{

	/**
	 * 为租户分配一台或多台专属主机，需要设置相关参数，包括实例规格、所属AZ，以及数量。
	 * @param host
	 * @return
	 */
	List<String> create(DedicatedHostCreate host);
	
	/**
	 * 租户或管理员通过该接口查询专属主机列表。
	 * @return
	 */
	DedicatedHosts list();
	/**
	 * 租户或管理员通过该接口查询专属主机列表。
	 * 可以在URI中增加如下参数，来过滤查询结果：host_type、host_type_name、
	 * flavor、dedicated_host_id、state、tenant、
	 * availability_zone、name、limit、marker或者changes-since。
	 * @param param
	 * @return
	 */
	DedicatedHosts list(Map<String,String> param);
	
	/**
	 * 查询某一台专属主机的详情
	 * @param hostId
	 * @return
	 */
	DedicatedHost get(String hostId);
	/**
	 * 查询专属主机上的云服务器。
	 * @param hostId
	 * @return
	 */
	List <? extends Server> list(String hostId , String limit,String marker);

	/**
	 * 更新专属主机属性
	 * @param hostId
	 * @param dedicatedHostUpdate
	 * @return
	 */
	ActionResponse update(String hostId , DedicatedHostUpdate dedicatedHostUpdate);

	/**
	 * 释放专属主机
	 * @param hostId
	 * @return
	 */
	ActionResponse delete(String hostId);

	/**
	 * 查询可用的专属主机类型
	 * @param availabilityZone
	 * @return
	 */
	List<DedicatedHostType> listHostType(String availabilityZone);


}
