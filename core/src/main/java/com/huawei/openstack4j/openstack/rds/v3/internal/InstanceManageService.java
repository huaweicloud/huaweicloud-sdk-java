package com.huawei.openstack4j.openstack.rds.v3.internal;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.database.domain.InstanceFlavor;
import com.huawei.openstack4j.openstack.rds.v3.domain.*;

import java.util.List;
import java.util.Map;
import static com.google.common.base.Preconditions.checkArgument;

public class InstanceManageService extends BaseRdsServices implements RestService {

    /**
     * create parameter template
     *
     * @param model
     * @return
     */
    public CreateInstanceResponse create(CreateInstanceRequest model) {
        checkArgument((null != model), "parameter `model` should not be null!");
        checkArgument((null != model.getName()), "parameter `name` should not be null!");
        checkArgument((null != model.getDatastore()), "parameter `datastore` should not be null!");
        checkArgument((null != model.getPassword()), "parameter `password` should not be null!");
        checkArgument((null != model.getFlavorRef()), "parameter `flavor_ref` should not be null!");
        checkArgument((null != model.getVolume()), "parameter `volume` should not be null!");
        checkArgument((null != model.getRegion()), "parameter `region` should not be null!");
        checkArgument((null != model.getVpcId()), "parameter `vpc_id` should not be null!");
        checkArgument((null != model.getAvailabilityZone()), "parameter `model` should not be null!");
        checkArgument((null != model.getSubnetId()), "parameter `subnet_id` should not be null!");
        checkArgument((null != model.getSecurityGroupId()), "parameter `security_group_id` should not be null!");
        return post(CreateInstanceResponse.class, uri("/instances")).entity(model).execute();
    }

    public ListInstanceResponse list() {
        return get(ListInstanceResponse.class, uri("/instances")).execute();
    }

    /**
     * Query cloud server details list by filteringParams
     *
     *@param filteringParams
     * @return
     */
    public ListInstanceResponse list(Map<String, String> filteringParams) {
        Invocation<ListInstanceResponse> serverInvocation = get(ListInstanceResponse.class, "/instances");
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        checkArgument((null != filteringParams), "parameter `filteringParams` should not be null!");
        return serverInvocation.execute();

    }
    /**
     * delete instanceId
     *
     * @param instanceId
     * @return
     */
    public ActionResponse delete(String instanceId) {
        checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty!");
        return deleteWithResponse(uri("/instances/%s", instanceId)).execute();
    }
    /**
     * Restart Instance parameter
     *
     * @param model
     * @param instanceId
     * @return
     */
    public InstanceCommonResponse restart(RestartInstanceRequest model, String instanceId) {
        checkArgument((null != model), "parameter `model` should not be null!");
        checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty!");
        return post(InstanceCommonResponse.class, uri("/instances/%s/action", instanceId)).entity(model).execute();
    }
    /**
     * Single To Ha parameter
     *
     * @param model
     * @param instanceId
     * @return
     */
    public InstanceCommonResponse singleToHa(SingleToHaRdsRequest model, String instanceId) {
        checkArgument((null != model), "parameter `model` should not be null!");
        checkArgument((null != model.getSingleToHa()), "parameter `single_to_ha` should not be null!");
        checkArgument((null != model.getSingleToHa().getAzCodeNewNode()), "parameter `az_code_new_node` should not be null!");
        checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty!");
        return post(InstanceCommonResponse.class, uri("/instances/%s/action", instanceId)).entity(model).execute();
    }
    /**
     * Resize Flavor parameter
     *
     * @param model
     * @param instanceId
     * @return
     */
    public InstanceCommonResponse resizeFlavor(ResizeFlavorRequest model, String instanceId) {
        checkArgument((null != model), "parameter `model` should not be null!");
        checkArgument((null != model.getResizeFlavor()), "parameter `resizeFlavor` should not be null!");
        checkArgument((null != model.getResizeFlavor().getSpecCode()), "parameter `spec_code` should not be null!");
        checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty!");
        return post(InstanceCommonResponse.class, uri("/instances/%s/action", instanceId)).entity(model).execute();
    }
    /**
     * Enlarge Volume parameter
     *
     * @param model
     * @param instanceId
     * @return
     */
    public InstanceCommonResponse enlargeVolume(EnlargeVolumeRequest model, String instanceId) {
        checkArgument((null != model), "parameter `model` should not be null!");
        checkArgument((null != model.getEnlargeVolume()), "parameter `enlarge_volume` should not be null!");
        checkArgument((model.getEnlargeVolume().getSize() >= 10), "parameter `size` should start value of each scaling is 10 GB!");
        checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty!");
        return post(InstanceCommonResponse.class, uri("/instances/%s/action", instanceId)).entity(model).execute();

    }

    public ErrorLogResponse listErrorLog(String instanceId) {
        checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty!");
        return get(ErrorLogResponse.class, uri("/instances/%s/errorlog",instanceId)).execute();
    }

    /**
     * Query Error Log details list by filteringParams
     *
     *@param filteringParams
     * @return
     */
    public ErrorLogResponse listErrorLog(Map<String, String> filteringParams, String instanceId) {
        Invocation<ErrorLogResponse> serverInvocation = get(ErrorLogResponse.class, uri("/instances/%s/errorlog",instanceId));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty!");
        checkArgument((null != filteringParams), "parameter `filteringParams` should not be null!");
        return serverInvocation.execute();

    }


    public SlowLogListResponse listSlowLog(String instanceId) {
        checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty!");
        return get(SlowLogListResponse.class, uri("/instances/%s/errorlog",instanceId)).execute();
    }

    /**
     * Query Slow Log details list by filteringParams
     *
     *@param filteringParams
     * @return
     */
    public SlowLogListResponse listSlowLog(Map<String, String> filteringParams, String instanceId) {
        Invocation<SlowLogListResponse> serverInvocation = get(SlowLogListResponse.class, uri("/instances/%s/slowlog",instanceId));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty!");
        checkArgument((null != filteringParams), "parameter `filteringParams` should not be null!");
        return serverInvocation.execute();

    }

    public FlavorList listFlavors(Map<String, String> filteringParams, String engineName) {
        Invocation<FlavorList> serverInvocation = get(FlavorList.class, uri("/flavors/%s", engineName));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        checkArgument(!Strings.isNullOrEmpty(engineName), "parameter `engineName` should not be empty!");
        return serverInvocation.execute();
    }

}
