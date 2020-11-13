package com.huawei.openstack4j.openstack.ecs.v1.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("remote_console")
public class RemoteConsole implements ModelEntity {

    /**
     * 远程登录的类型，请将type配置为“novnc”。
     */
    private String type;

    /**
     * 远程登录协议，请将protocol配置为“vnc”。
     */
    private String protocol;
}
