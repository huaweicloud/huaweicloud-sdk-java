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
package com.huawei.openstack4j.openstack.cloudeye.internal;

import static com.google.common.base.Preconditions.*;
import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.cloudeye.AlarmService;
import com.huawei.openstack4j.model.cloudeye.Alarm;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.cloudeye.domain.CloudEyeAlarm;

public class CloudEyeAlarmServiceImpl extends BaseCloudEyeServices implements AlarmService {

	@Override
	public List<? extends Alarm> list() {
		return get(CloudEyeAlarm.CloudEyeAlarms.class, uri(PATH_ALARMS)).execute().getList();
	}

	@Override
	public List<? extends Alarm> list(AlarmFilterOptions options) {
		return get(CloudEyeAlarm.CloudEyeAlarms.class, uri(PATH_ALARMS)).params(options.getOptions()).execute()
				.getList();
	}

	@Override
	public Alarm get(String alarmId) {
		checkNotNull(alarmId);
		return get(CloudEyeAlarm.CloudEyeAlarms.class, PATH_ALARMS, "/", alarmId).execute().getList().get(0);
	}

	@Override
	public ActionResponse startAlarm(String alarmId) {
		checkNotNull(alarmId);
		Map<String, Boolean> entity = new HashMap<>();
		entity.put("alarm_enabled", true);
		return putWithResponse(PATH_ALARMS, "/", alarmId, PATH_ALARMS_ACTION).entity(entity).execute();
	}

	@Override
	public ActionResponse stopAlarm(String alarmId) {
		checkNotNull(alarmId);
		Map<String, Boolean> entity = new HashMap<>();
		entity.put("alarm_enabled", false);
		return putWithResponse(PATH_ALARMS, "/", alarmId, PATH_ALARMS_ACTION).entity(entity).execute();
	}

	@Override
	public ActionResponse deleteAlarm(String alarmId) {
		return deleteWithResponse(PATH_ALARMS, "/", alarmId).execute();
	}
}
