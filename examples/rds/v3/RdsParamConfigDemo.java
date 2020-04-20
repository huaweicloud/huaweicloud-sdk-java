package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.rds.v3.domain.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParamConfigDemo {

	private static final String LANGUAGE = "zh-cn";

	public static void main(String[] args){
		/**
		 * Using credentials for authentication
		 */
		String authUrl = "***********";//endpoint url

		String user = "***********";//username

		String password = "***********";//password

		String projectId = "***********";//projectId

		String userDomainId = "***********";	//domainId

		//initial OpenStack4j Client
		OSFactory.enableHttpLoggingFilter(true);

		//config of client
		//withLanguage setting is required for RDS(trove&database) service
		//withSSLVerificationDisabled is required if the SSL certification of the cloud service is illegal
		Config config = Config.newConfig().withLanguage(LANGUAGE)
				                .withSSLVerificationDisabled();

		//create connection
		OSClient.OSClientV3 osclient = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
				                               .credentials(user, password, Identifier.byId(userDomainId))
				                               .scopeToProject(Identifier.byId(projectId)).authenticate();

		String configId = "***********";
		List<String> ids = Arrays.asList("***********","***********");//instanceId样例
		HashMap<String, String> values = new HashMap<>();

		//get parameter template list
		Configuration.Configurations configurations = osclient.rds().params().list();
		if (configurations.getList().size() > 0){
			System.out.println("get parameter template list success, size = " + configurations.getList().size());
		}else {
			System.out.println("get parameter template list failed");
		}

		//create parameter template
		values.put("max_connections", "10");
		values.put("autocommit", "OFF");
		DataStore dataStore = DataStore.builder().type("mysql")
				                      .version("5.6").build();
		ConfigurationCreate request = ConfigurationCreate.builder().name("configuration_test")
				                              .description("configuration_test")
				                              .values(values)
				                              .dataStore(dataStore).build();
		Configuration configuration = osclient.rds().params().create(request);
		if (null != configuration) {
			System.out.println("create parameter template success, name = " + configuration.getName());
		} else {
			System.out.println("create parameter template failed");
		}

		//update parameter template
		values.put("max_connections", "20");
		values.put("autocommit", "ON");
		ConfigurationUpdate update = ConfigurationUpdate.builder().name("configuration_update")
				                             .description("configuration_update")
				                             .values(values).build();
		ActionResponse repDetach = osclient.rds().params().update(update, configId);
		if (repDetach.isSuccess()){
			System.out.println("update parameter template success");
		}else {
			System.out.println("update parameter template failed");
		}

		//apply parameter template
		InstanceIds instanceIds = InstanceIds.builder()
				                          .instanceIds(ids).build();
		ApplyParaResponse response = osclient.rds().params().applyParamTemplate(instanceIds, configId);
		if (null != response){
			System.out.println("apply parameter template success , configurationName = " + response.getConfigutationName());
		}else {
			System.out.println("apply parameter template failed");
		}

		//update instance parameter
		values.put("max_connections", "30");
		values.put("autocommit", "ON");
		Values valueUpdate = Values.builder().values(values).build();
		try {
			UpdateInstanceParaResponse resp = osclient.rds().params().updateInstanceParam(valueUpdate, "***********");
			if (null != resp){
				System.out.println("update instance parameter success" + resp.toString());
			}else {
				System.out.println("update instance parameter failed");
			}
		}catch (Exception e){
			System.out.println(e.toString());
		}

		//get specified instance parameter template
		InstanceParaTemplate result = osclient.rds().params().getSpecifiedInstanceParamTemplate("***********");
		if (null != result){
			System.out.println("get specified instance parameter template success , result = " + result);
		}else {
			System.out.println("get specified instance parameter template failed");
		}

		//get specified parameter template parameter
		InstanceParaTemplate instanceParaTemplate = osclient.rds().params().getSpecifiedParamTemplate(configId);
		if (null != instanceParaTemplate){
			System.out.println("get specified parameter template parameter success, id = " + instanceParaTemplate.getId());
		}else {
			System.out.println("get specified parameter template parameter failed");
		}

		//delete parameter template
		ActionResponse actionResponse = osclient.rds().params().delete(configId);
		if (actionResponse.isSuccess()){
			System.out.println("delete parameter template success");
		}else {
			System.out.println("delete parameter template failed");
		}
	}

}
