/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.api.storage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.annotations.Test;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.storage.block.domain.CinderBlockQuotaSetResponse;

@Test(suiteName = "Block Storage Tests")
public class VolumeQuotaTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.BLOCK_STORAGE;
    }

    @Test
    public void quotaForTenantTest() throws Exception {
        respondWith("/storage/v2/cinder_volume_quota_set.json");

        final String projectId = "2fe576ee18e541898fe20b3208d53217";
        CinderBlockQuotaSetResponse quotaSetResponse = osv3().blockStorage().quotaSets().quotaForTenant(projectId);
        Map<String, Object> quotaSet = quotaSetResponse.getQuotaSet();
        assertEquals((String)quotaSet.get("id"), projectId);
        Map<String, Integer> volumes = (Map<String, Integer>)quotaSet.get("volumes");
        assertTrue(4 == volumes.get("reserved"));
    }
}