/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.
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
package com.huawei.openstack4j.openstack.fgs.v1.domain;

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

    /**
     *
     */
    private static final long serialVersionUID = 6880803566524871319L;


    @JsonProperty("CodeFileName")
    private String codeFileName;

    @JsonProperty("CodeSize")
    private String codeSize;

    @JsonProperty("CodeType")
    private String codeType;

    @JsonProperty("CodeUrl")
    private String codeUrl;

    @JsonProperty("Cpu")
    private int cpu;

    @JsonProperty("DependencyPkg")
    private String dependencyPkg;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Digest")
    private String digest;

    @JsonProperty("FuncName")
    private String funcName;

    @JsonProperty("FuncUrn")
    private String funcUrn;

    @JsonProperty("ImageName")
    private String imageName;

    @JsonProperty("LastModified")
    private String lastModified;

    @JsonProperty("MemorySize")
    private int memorySize;

    @JsonProperty("Namespace")
    private String namespace;

    @JsonProperty("Package")
    private String packageName;

    @JsonProperty("ProjectName")
    private String projectName;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Timeout")
    private int timeout;

    @JsonProperty("UserData")
    private String userData;

    @JsonProperty("UserDomain")
    private String userDomain;

    @JsonProperty("Version")
    private String version;

    @JsonProperty("VersionDescription")
    private String versionDescription;

    @JsonProperty("Xrole")
    private String xrole;

    @JsonProperty("Handler")
    private String handler;

    @JsonProperty("FuncCode")
    private FuncCode funcCode;

    @JsonProperty("StrategyConfig")
    private StrategyConfig strategyConfig;

    public static class Functions extends ListResult<FunctionMetadata> {

        private static final long serialVersionUID = 3674265240585920922L;

        @JsonProperty("Functions")
        private List<FunctionMetadata> data;

        @JsonProperty("NextMarker")
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

        /**
         *
         */
        private static final long serialVersionUID = -6745451132251018282L;

        @JsonProperty("Versions")
        private List<FunctionMetadata> data;

        @JsonProperty("NextMarker")
        private String nextMarker;

        @Override
        protected List<FunctionMetadata> value() {
            return data;
        }

    }

}
