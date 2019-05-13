package com.huawei.openstack4j.openstack.iam.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("auth")
public class Auth implements ModelEntity {

    private static final long serialVersionUID = 8176244044209710349L;

    /**
     * the identity of the auth entity
     */
    @JsonProperty
    private AuthIdentity identity;

    /**
     * the scope of the auth entity
     */
    private AuthScope scope;

}
