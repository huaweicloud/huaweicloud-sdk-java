package com.huawei.openstack4j.openstack.ecs.v1_1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

public class ServerCreateRequest implements ModelEntity {

    private static final long serialVersionUID = -6252374090930619106L;
    @JsonProperty("dry_run")
    private Boolean isDryRun;

    @JsonProperty("server")
    private ServerCreate server;

    public ServerCreateRequest(Boolean isDryRun, ServerCreate server) {
        this.isDryRun = isDryRun;
        this.server = server;
    }
}
