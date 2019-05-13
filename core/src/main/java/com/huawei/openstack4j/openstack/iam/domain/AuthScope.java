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
@JsonRootName("scope")
public class AuthScope implements ModelEntity {

    private static final long serialVersionUID = -5363065099764958747L;

    /**
     * the project scope
     */
    private AuthProject project;

    /**
     * the domain scope
     */
    private AuthDomain domain;

}
