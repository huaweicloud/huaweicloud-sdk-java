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
package com.huawei.openstack4j.openstack.csbs.v1.internal;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.openstack.csbs.v1.domain.Region;
import com.huawei.openstack4j.openstack.csbs.v1.domain.Region.Regions;
import com.huawei.openstack4j.openstack.csbs.v1.domain.ReplicationRecord;
import com.huawei.openstack4j.openstack.csbs.v1.domain.ReplicationRecord.ReplicationRecords;

public class ReplicationManagementService extends BaseCloudServerBackupService{

	/**
	 *查询本区域的复制能力，例如支持复制到哪些区域。 
	 * @return
	 */
	public List<Region> get() {
		return get(Regions.class,uri("/replication_capabilities")).execute().getList();
	}
	/**
	 *查询副本的复制记录信息 
	 * @return
	 */
	public List<ReplicationRecord> list() {
		return get(ReplicationRecords.class,uri("/replication_records")).execute().getList();
	}
	/**
	 * 查询副本的复制记录信息
	 * @param filteringParams
	 * @return
	 */
	public List<ReplicationRecord> list(Map<String, String> filteringParams) {
		Invocation<ReplicationRecords> req = get(ReplicationRecords.class,uri("/replication_records"));
		if (filteringParams != null) {
			for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
				req = req.param(entry.getKey(), entry.getValue());
			}
		}
		return req.execute().getList();
	}
}
