package com.huawei.openstack4j.openstack.iam.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("project")
public class AuthProject implements ModelEntity {

    private static final long serialVersionUID = -1876643900253395436L;

    /**
     * the id of the project
     */
    private String id;

    /**
     * the name of the project
     */
    private String name;

}
