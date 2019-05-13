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
@JsonRootName("domain")
public class AuthDomain implements ModelEntity {

    private static final long serialVersionUID = 5157730272408814244L;

    /**
     * the id of the domain
     */
    private String id;

    /**
     * the name of the domain
     */
    private String name;

}
