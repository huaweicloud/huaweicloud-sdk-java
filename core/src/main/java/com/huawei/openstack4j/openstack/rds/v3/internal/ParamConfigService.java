package com.huawei.openstack4j.openstack.rds.v3.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.rds.v3.domain.*;
import com.huawei.openstack4j.openstack.rds.v3.domain.Configuration.Configurations;
import com.huawei.openstack4j.openstack.rds.v3.domain.Configuration;
import java.util.List;
import static com.google.common.base.Preconditions.checkArgument;

public class ParamConfigService extends BaseRdsServices implements RestService{

	private static final String API_PATH = "/configurations";

	/**
	 * Obtaining a Parameter Template List
	 *
	 * @return
	 */
	public Configurations list(){
		Invocation<Configurations> req = get(Configurations.class, uri(API_PATH));
		return req.execute();
	}

	/**
	 * Creating a Parameter Template
	 *
	 * @param request
	 * @return
	 */
	public Configuration create(ConfigurationCreate request){
		checkArgument((null != request), "parameter 'request' should not be null");
		checkArgument(!Strings.isNullOrEmpty(request.getName()), "parameter 'name' should not be null");
		checkArgument((null != request.getValues()), "parameter 'values' should not be null");
		checkArgument((null != request.getDatastore()), "parameter 'dataStore' should not be null");
		return post(Configuration.class, uri(API_PATH)).entity(request).execute();
	}

	/**
	 * Modifying a Parameter Template
	 *
	 * @param updateModel
	 * @param configId
	 * @return
	 */
	public ActionResponse update(ConfigurationUpdate updateModel, String configId){
		checkArgument((null != updateModel),"parameter 'updateModel' should not be null");
		checkArgument((null != updateModel.getValues()), "parameter 'values' should not be null");
		checkArgument(!Strings.isNullOrEmpty(configId),"parameter 'configId' should not be empty");
		ActionResponse resp = putWithResponse(uri(API_PATH + "/%s", configId)).entity(updateModel).execute();
		return ActionResponse.actionSuccess();
	}

	/**
	 * Applying a Parameter Template
	 *
	 * @param instanceIds
	 * @param configId
	 * @return
	 */
	public ApplyParaResponse applyParamTemplate(InstanceIds instanceIds, String configId){
		checkArgument(instanceIds != null, "parameter 'instanceIds' should not be empty");
		checkArgument(!Strings.isNullOrEmpty(configId), "parameter 'configId' should not be empty");

//		HashMap<String, Object> entity = Maps.newHashMap();
//		entity.put("instance_ids", instanceIds);
		return put(ApplyParaResponse.class, uri(API_PATH + "/%s/apply", configId)).entity(instanceIds).execute();
	}

	/**
	 * Modifying Parameters of a Specified DB Instance
	 *
	 * @param values
	 * @param instanceId
	 * @return
	 */
	public UpdateInstanceParaResponse updateInstanceParam(Values values, String instanceId){
		checkArgument((null != values),"parameter 'values' should not be null");
		checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter 'instanceId' should not be empty");
		return put(UpdateInstanceParaResponse.class, uri("/instances/%s" + API_PATH, instanceId)).entity(values).execute();
	}

	/**
	 * Obtaining the Parameter Template of a Specified DB instance
	 *
	 * @param instanceId
	 * @return
	 */
	public InstanceParaTemplate getSpecifiedInstanceParamTemplate(String instanceId){
		checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter 'instanceId' should not be empty");
		return get(InstanceParaTemplate.class, uri("/instances/%s" + API_PATH, instanceId)).execute();
	}

	/**
	 * Obtaining Parameters in a Specified Parameter Template
	 *
	 * @param configId
	 * @return
	 */
	public InstanceParaTemplate getSpecifiedParamTemplate(String configId){
		checkArgument(!Strings.isNullOrEmpty(configId), "parameter 'configId' should not be empty");
		return get(InstanceParaTemplate.class, uri(API_PATH + "/%s", configId)).execute();
	}

	/**
	 * Deleting a Parameter Template
	 *
	 * @param configId
	 * @return
	 */
	public ActionResponse delete(String configId){
		checkArgument(!Strings.isNullOrEmpty(configId), "parameter 'configId' should not be empty");
		return deleteWithResponse(uri(API_PATH + "/%s", configId)).execute();
	}
}
