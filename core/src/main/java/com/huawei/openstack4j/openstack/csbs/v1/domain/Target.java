package com.huawei.openstack4j.openstack.csbs.v1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

/**
 * Created on 2018/10/30.
 */
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Target implements ModelEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 662542387798821273L;

	/**
     * 待恢复到的目标id
     */
    @JsonProperty("resource_id")
    private String resourceId;

    /**
     * 待恢复到的目标资源类型
     */
    @JsonProperty("resource_type")
    private String resourceType;

    /**
     * 云服务器恢复时的卷对应关系列表
     */
    private List<RestoreVolume> volumes;

}
