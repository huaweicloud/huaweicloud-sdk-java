/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack4j.openstack.fgs.v1.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FunctionTrigger;

import static com.google.common.base.Preconditions.checkArgument;

public class TriggerService extends BaseFunctionGraphService {

    private final String triggersUrlFmt = ClientConstants.FGS_TRIGGERS + ClientConstants.URI_SEP + "%s";

    /**
     * Get all the trigger settings for the specified function
     *
     * @param functionUrn
     * @return
     */
    public FunctionTrigger[] listTriggersForFunction(String functionUrn) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        return get(FunctionTrigger[].class, uri(triggersUrlFmt, functionUrn)).execute();
    }

    /**
     * Create trigger
     *
     * @param functionUrn
     * @param functionTriggers trigger entity
     * @return
     */
    public FunctionTrigger createTriggerInstance(String functionUrn, FunctionTrigger functionTriggers) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionTriggers.getTriggerTypeCode()),
                "parameter `TriggerTypeCode` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionTriggers.getEventTypeCode()), "parameter `EventTypeCode` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionTriggers.getEventData().toString()),
                "parameter `EventData` should not be empty");
        return post(FunctionTrigger.class, uri(triggersUrlFmt, functionUrn)).entity(functionTriggers).execute();
    }

    /**
     * Delete trigger
     *
     * @param functionUrn
     * @param trigger_type_code Trigger type
     * @param trigger_id        Trigger coding
     */
    public ActionResponse deleteTrigger(String functionUrn, String trigger_type_code, String trigger_id) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(trigger_type_code), "parameter `trigger_type_code` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(trigger_id), "parameter `trigger_id` should not be empty");
        String url = triggersUrlFmt + ClientConstants.URI_SEP + "%s" + ClientConstants.URI_SEP + "%s";
        return deleteWithResponse(uri(url, functionUrn, trigger_type_code, trigger_id)).execute();
    }

    /**
     * Get information about the specified trigger
     *
     * @param functionUrn
     * @param trigger_type_code Trigger type
     * @param trigger_id        Trigger coding
     * @return
     */
    public FunctionTrigger getTriggerInstance(String functionUrn, String trigger_type_code, String trigger_id) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(trigger_type_code), "parameter `trigger_type_code` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(trigger_id), "parameter `trigger_id` should not be empty");
        String url = triggersUrlFmt + ClientConstants.URI_SEP + "%s" + ClientConstants.URI_SEP + "%s";
        return get(FunctionTrigger.class, uri(url, functionUrn, trigger_type_code, trigger_id)).execute();
    }

    /**
     * Delete all triggers for the specified function
     *
     * @param functionUrn
     */
    public ActionResponse deleteAllTriggersForFunction(String functionUrn) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        return deleteWithResponse(uri(triggersUrlFmt, functionUrn)).execute();
    }
}
