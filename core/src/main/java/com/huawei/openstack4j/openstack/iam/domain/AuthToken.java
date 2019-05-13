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
@JsonRootName("token")
public class AuthToken implements ModelEntity {

    private static final long serialVersionUID = -8011278540697181490L;

    /**
     * the value of the token
     */
    private String id;

    /**
     * the duration time of the securitytoken
     */
    @JsonProperty("duration-seconds")
    private String durationSeconds;

}
