package com.huawei.openstack4j.openstack.iam.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

import java.util.List;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("identity")
public class AuthIdentity implements ModelEntity {

    private static final long serialVersionUID = 2899115558053618996L;

    /**
     * the method of creating the securitytoken
     */
    private List<String> methods;

    /**
     * the token information
     */
    private AuthToken token;

    /**
     * the assume role information
     */
    @JsonProperty("assume_role")
    private AuthAssumeRole assumeRole;

}
