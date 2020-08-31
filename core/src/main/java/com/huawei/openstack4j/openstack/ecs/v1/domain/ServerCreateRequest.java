package com.huawei.openstack4j.openstack.ecs.v1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

public class ServerCreateRequest implements ModelEntity {

    private static final long serialVersionUID = -2887662917095175105L;

    @JsonProperty("dry_run")
    private Boolean isDryRun;
    @JsonProperty("server")
    private ServerCreate server;

    public ServerCreateRequest(Boolean isDryRun, ServerCreate servers1) {
        this.isDryRun = isDryRun;
        this.server = servers1;
    }
}
