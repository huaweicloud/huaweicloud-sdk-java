/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co., Ltd.
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * 	use this file except in compliance with the License. You may obtain a copy of
 * 	the License at
 *
 * 	    http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * 	License for the specific language governing permissions and limitations under
 * 	the License.
 *******************************************************************************/
package com.huawei.openstack4j.openstack.fgs.v2.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class FunctionMetadata implements ModelEntity {
    private static final long serialVersionUID = -6880803566524871319L;

    @JsonProperty("func_urn")
    private String funcUrn;

    @JsonProperty("domain_id")
    private String domainId;

    @JsonProperty("namespace")
    private String namespace;

    @JsonProperty("project_name")
    private String projectName;

    @JsonProperty("package")
    private String packageName;

    @JsonProperty("runtime")
    private String runtime;

    @JsonProperty("timeout")
    private int timeout;

    @JsonProperty("handler")
    private String handler;

    @JsonProperty("memory_size")
    private int memorySize;

    @JsonProperty("cpu")
    private int cpu;

    @JsonProperty("code_type")
    private String codeType;

    @JsonProperty("code_filename")
    private String codeFileName;

    @JsonProperty("code_size")
    private int codeSize;

    @JsonProperty("digest")
    private String digest;

    @JsonProperty("version")
    private String version;

    @JsonProperty("image_name")
    private String imageName;

    @JsonProperty("last_modified")
    private String lastModified;

    @JsonProperty("func_code")
    private FuncCode funcCode;

    @JsonProperty("strategy_config")
    private StrategyConfig strategyConfig;

    @JsonProperty("description")
    private String description;

    @JsonProperty("depend_list")
    private List<String> dependList;

    @JsonProperty("dependencies")
    private List<FuncDependencie> dependencies;

    @JsonProperty("xrole")
    private String xrole;

    @JsonProperty("user_data")
    private String userData;

    @JsonProperty("func_name")
    private String funcName;

    @JsonProperty("code_url")
    private String codeUrl;

    @JsonProperty("version_description")
    private String versionDescription;

    @JsonProperty("app_xrole")
    private String appXrole;

    @JsonProperty("initializer_handler")
    private String initializerHandler;

    @JsonProperty("initializer_timeout")
    private int initializerTimeout;

    @JsonProperty("concurrency")
    private int concurrency;

    @JsonProperty("extend_config")
    private String extendConfig;

    @JsonProperty("func_vpc")
    private FuncVpc funcVpc;

    @JsonProperty("mount_config")
    private MountConfig mountConfig;

    public static class Functions extends ListResult<FunctionMetadata> {
        private static final long serialVersionUID = 3674265240585920922L;

        @JsonProperty("functions")
        private List<FunctionMetadata> data;

        @JsonProperty("next_marker")
        private String nextMarker;

        @Override
        protected List<FunctionMetadata> value() {
            return data;
        }

        public String getNextMarker() {
            return nextMarker;
        }
    }

    @Getter
    public static class FunctionVersions extends ListResult<FunctionMetadata> {
        private static final long serialVersionUID = -6745451132251018282L;

        @JsonProperty("versions")
        private List<FunctionMetadata> data;

        @JsonProperty("next_marker")
        private String nextMarker;

        @Override
        protected List<FunctionMetadata> value() {
            return data;
        }

    }
}
