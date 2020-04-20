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
package com.huawei.openstack4j.api.rds.v3;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.rds.v3.domain.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@org.testng.annotations.Test(suiteName = "RDS/RDSV3", enabled = true)
public class ParamConfigServiceTest extends AbstractTest{

	private static String CONFIGURATIONS_LIST = "/rds/v3/configuration_list.json";

	private static String CONFIGURATION_CREATE = "/rds/v3/configuration_create.json";

	private static String CONFIGURATION_CREATE_RESPONSE = "/rds/v3/configuration_create_response.json";

	private static String CONFIGURATION_UPDATE = "/rds/v3/configuration_update.json";

	private static String CONFIGURATION_APPLY_RESPONSE = "/rds/v3/configuration_apply_response.json";

	private static String CONFIGURATION_INS_UPDATE_RESPONSE = "/rds/v3/configuration_ins_update_response.json";

	private static String CONFIGURATION_INS_RESPONSE = "/rds/v3/configuration_ins_response.json";

	private static String CONFIGURATION_PARA_RESPONSE = "/rds/v3/configuration_para_response.json";

	HashMap<String, String> values = new HashMap<>();

	@Test
	public void listTest() throws IOException{
		respondWith(CONFIGURATIONS_LIST);
		Configuration.Configurations list = osv3().rds().params().list();
		assertTrue(list.getList().size() == 16);
	}

	@Test
	public void createTest() throws IOException{
		respondWith(CONFIGURATION_CREATE_RESPONSE);
		String name = "configuration_test";
		values.put("max_connections", "10");
		values.put("autocommit", "OFF");
		DataStore dataStore = DataStore.builder().type("mysql")
												.version("5.6").build();
		ConfigurationCreate request = ConfigurationCreate.builder().name("configuration_test")
																	.description("configuration_test")
																	.values(values)
																	.datastore(dataStore).build();
		Configuration configuration = osv3().rds().params().create(request);
		assertNotNull(name.equals(configuration.getName()));
	}

	@Test
	public void updateTest() throws IOException {
		respondWith(CONFIGURATION_UPDATE);
		String configId = "7f3faac779484f6ea4d7ceeae7fc0614pr01";
		ConfigurationUpdate update = ConfigurationUpdate.builder().name("configuration_update")
				                             .description("configuration_update")
				                             .values(values).build();
		ActionResponse successResponse = osv3().rds().params().update(update, configId);
		assertNotNull(successResponse);
		assertTrue(successResponse.isSuccess());
	}

	@Test
	public void updateInsParamTest() throws IOException{
		respondWith(CONFIGURATION_INS_UPDATE_RESPONSE);
		String instanceId = "00d5e5ca0e67426c80ed237c0e17212ain01";
		values.put("max_connections", "30");
		values.put("autocommit", "ON");
		Values valueUpdate = Values.builder().values(values).build();
		UpdateInstanceParaResponse response = osv3().rds().params().updateInstanceParam(valueUpdate ,instanceId);
		assertNotNull(response);
	}

	@Test
	public void applyParaTemplateTest() throws IOException{
		respondWith(CONFIGURATION_APPLY_RESPONSE);
		InstanceIds instanceIds = InstanceIds.builder()
				                          .instanceIds(Arrays.asList("00d5e5ca0e67426c80ed237c0e17212ain01","73ea2bf70c73497f89ee0ad4ee008aa2in01"))
				                          .build();
		String configId = "7f3faac779484f6ea4d7ceeae7fc0614pr01";
		ApplyParaResponse response = osv3().rds().params().applyParamTemplate(instanceIds, configId);
		assertTrue(configId.equals(response.getConfigurationId()));
	}

	@Test
	public void getSpecifiedInsParaTest() throws IOException{
		respondWith(CONFIGURATION_INS_RESPONSE);
		String instanceId = "00d5e5ca0e67426c80ed237c0e17212ain01";
		String datastoreName = "mysql";
		InstanceParaTemplate result = osv3().rds().params().getSpecifiedInstanceParamTemplate(instanceId);
		assertTrue(datastoreName.equals(result.getName()));
	}

	@Test
	public void getSpecifiedParaTemplateTest() throws IOException{
		respondWith(CONFIGURATION_PARA_RESPONSE);
		String configId = "7f3faac779484f6ea4d7ceeae7fc0614pr01";
		InstanceParaTemplate instanceParaTemplate = osv3().rds().params().getSpecifiedParamTemplate(configId);
		assertTrue(configId.equals(instanceParaTemplate.getId()));
	}

	@Test
	public void deleteTest(){
		int code = 200;
		respondWith(code);
		String configId = "7f3faac779484f6ea4d7ceeae7fc0614pr01";
		ActionResponse successResponse = osv3().rds().params().delete(configId);
		assertNotNull(successResponse);
		assertTrue(successResponse.getCode() == code);
	}

	@Override
	protected Service service() {
		return Service.RDSV3;
	}
}
