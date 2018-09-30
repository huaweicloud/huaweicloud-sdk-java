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
package com.huawei.openstack4j.api.cloudeye;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.cloudeye.Alarm;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.cloudeye.internal.AlarmFilterOptions;

public interface AlarmService extends RestService {
	
	/**
	 * list alarm
	 * @return
	 */
    List<? extends Alarm> list();
    
    /**
     * list alarm with filter options
     * 
     * @param options filter options
     * @return
     */
    List<? extends Alarm> list(AlarmFilterOptions options);

    /**
     * @param alarmId The id for the alarm
     * @return
     */
    Alarm get(String alarmId);

    /**
     * @param alarmId The id for the alarm which need to be started
     * @return
     */
    ActionResponse startAlarm(String alarmId);

    /**
     * @param alarmId The id for the alarm which need to be stopped
     * @return
     */
    ActionResponse stopAlarm(String alarmId);

    /**
     * @param alarmId The id for the alarm which need to be deleted
     * @return
     */
    ActionResponse deleteAlarm(String alarmId);


}
