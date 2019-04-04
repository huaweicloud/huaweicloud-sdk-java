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
package com.huawei.openstack4j.model.compute;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BDMVolumeType {

    SATA("SATA"), 	// 普通IO磁盘类型。
    SAS("SAS"),		// 高IO磁盘类型。
    SSD("SSD")		// 超高IO磁盘类型。
    ;


    String value;

    BDMVolumeType(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

    @JsonCreator
    public static BDMVolumeType forValue(String value) {
        if (value != null) {
            for (BDMVolumeType state : BDMVolumeType.values()) {
                if (value.equals(state.value)) {
                    return state;
                }
            }
        }
        return null;
    }
}
