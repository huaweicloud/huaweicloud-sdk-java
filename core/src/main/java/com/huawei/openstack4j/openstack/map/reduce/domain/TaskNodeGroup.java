package com.huawei.openstack4j.openstack.map.reduce.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TaskNodeGroup {
    private String groupName = "task_node_default_group";

    @JsonProperty("node_num")
    private int nodeNum;

    @JsonProperty("node_size")
    private String nodeSize;

    @JsonProperty("node_spec_id")
    private String nodeSpecId;

    @JsonProperty("node_product_id")
    private String nodeProductId;

    @JsonProperty("vm_product_id")
    private String vmProductId;

    @JsonProperty("vm_spec_code")
    private String vmSpecCode;

    @JsonProperty("root_volume_size")
    private int rootVolumeSize;

    // now will use SATA type to find volume product id. if the type of root volume can be choosen, please fix here.
    @JsonProperty("root_volume_product_id")
    private String rootVolumeProductId;

    @JsonProperty("root_volume_type")
    private String rootVolumeType;

    @JsonProperty("root_volume_resource_spec_code")
    private String rootVolumeResourceSpecCode;

    @JsonProperty("root_volume_resource_type")
    private String rootVolumeResourceType;
    @JsonProperty("data_volume_type")
    private String dataVolumeType;

    @JsonProperty("data_volume_count")
    private int dataVolumeCount;

    @JsonProperty("data_volume_size")
    private int dataVolumeSize;

    @JsonProperty("data_volume_product_id")
    private String dataVolumeProductId;

    @JsonProperty("data_volume_resource_spec_code")
    private String dataVolumeResourceSpecCode;

    @JsonProperty("data_volume_resource_type")
    private String dataVolumeResourceType;

    @JsonProperty("auto_scaling_policy")
    private AutoScalingPolicy autoScalingPolicy;


}
