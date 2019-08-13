/*******************************************************************************
 * 	Copyright 2019 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.core.transport.HttpMethod;
import com.huawei.openstack4j.core.transport.HttpRequest;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.core.transport.internal.HttpExecutor;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;

import java.util.HashMap;

/**
 * Utility class for retrieving ECS instance metadata.
 */
public class ECSMetadataUtils {

    /** Default root url for the openstack metadata apis. */
    private static final String OPENSTACK_METADATA_ROOT = "/openstack/latest";
    /** Default root url for the metadata apis compatible with EC2. */
    private static final String EC2_METADATA_ROOT = "/latest/meta-data";

    /** Default endpoint for the ECS Instance Metadata Service. */
    private static final String ECS_METADATA_SERIVCE_URL = "http://169.254.169.254";

    private static final String EC2_METADATA_SERVICE_OVERRIDE_URL = "ecsMetadataServiceOverrideEndpoint";

    /**
     * Returns the temporary security credentials (access, secret, securitytoken,
     * and expires_at) associated with the IAM roles on the instance.
     */
    public static SecurityKey getSecurityKey() {
        String securityKeyInfo = getResource(String.class, OPENSTACK_METADATA_ROOT + "/securitykey");
        return jsonToObject(securityKeyInfo, SecurityKey.class);
    }

    /**
     * Get the type of the instance.
     */
    public static String getInstanceType() {
        return getResource(String.class, EC2_METADATA_ROOT + "/instance-type");
    }

    /**
     * Returns the host address of the ECS Instance Metadata Service.
     */
    public static String getEndpointForECSMetadataService() {
        String overridUrl = System.getProperty(EC2_METADATA_SERVICE_OVERRIDE_URL);
        return overridUrl != null ? overridUrl : ECS_METADATA_SERIVCE_URL;
    }

    /**
     * Get resource and return contents from metadata service
     * with the specify path.
     */
    private static <T> T getResource(Class<T> returnType, String path) {
        String endpoint = getEndpointForECSMetadataService();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "*/*");
        HttpRequest<T> build = HttpRequest.builder(returnType)
                .method(HttpMethod.GET).headers(headers).endpoint(endpoint).path(path).build();
        HttpResponse res = HttpExecutor.create().execute(build);
        return res.getEntity(returnType);
    }

    /**
     * Transfer json string to the specify Object.
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        try {
            return ObjectMapperSingleton.getContext(clazz).readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse Json String.", e);
        }
    }

    /**
     * The temporary security credentials (access, secret, securitytoken,
     * and expires_at) associated with the IAM role.
     */
    @JsonRootName("credential")
    public static class SecurityKey {
        public String access;
        public String secret;
        @JsonProperty("securitytoken")
        public String securityToken;
        @JsonProperty("expires_at")
        public String expiresAt;
    }
}
