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
package com.huawei.openstack4j.openstack.fgs.v1.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FunctionMetadata;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FunctionVersionAlias;

import static com.google.common.base.Preconditions.checkArgument;

public class VersionsService extends BaseFunctionGraphService {

    private final String urlFmt = ClientConstants.FGS_PATH + ClientConstants.URI_SEP + "%s";

    /**
     * Release function version
     *
     * @param functionUrn
     * @param fmd FunctionMetadata
     * @return FunctionMetadata
     */
    public FunctionMetadata publishVersion(String functionUrn, FunctionMetadata fmd) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        String url = urlFmt + ClientConstants.FGS_VERSION;
        return post(FunctionMetadata.class, uri(url, functionUrn)).entity(fmd).execute();
    }


    /**
     * Get the list of versions of the specified function
     *
     * @param functionUrn
     * @param marker       Last recorded location of the last query
     * @param maxItems     The maximum number of function records obtained per query
     * @return
     */
    public FunctionMetadata.FunctionVersions listFunctionVersions(String functionUrn, int marker, int maxItems) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(Integer.toString(marker)), "parameter `marker` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(Integer.toString(maxItems)), "parameter `maxItems` should not be empty");
        String url = urlFmt + ClientConstants.FGS_VERSION;
        return get(FunctionMetadata.FunctionVersions.class, uri(url, functionUrn)).param(ClientConstants.FGS_MARKER, marker)
                .param(ClientConstants.FGS_MAXITEMS, maxItems).execute();
    }

    /**
     * Create a function version alias
     *
     * @param functionUrn
     * @param functionVersionAlias Function version alias entity
     * @return
     */
    public FunctionVersionAlias createVersionAlias(String functionUrn, FunctionVersionAlias functionVersionAlias) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionVersionAlias.getName()), "parameter `Name` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionVersionAlias.getVersion()), "parameter `Version` should not be empty");
        String url = urlFmt + ClientConstants.FGS_ALIASES;
        return post(FunctionVersionAlias.class, uri(url, functionUrn)).entity(functionVersionAlias).execute();
    }

    /**
     * Modify function version alias information
     *
     * @param functionUrn
     * @param functionVersionAlias Function version alias entity
     * @return
     */
    public FunctionVersionAlias updateVersionAlias(String functionUrn, FunctionVersionAlias functionVersionAlias) {
        String aliasName = functionVersionAlias.getName();
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(aliasName), "parameter `Name` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionVersionAlias.getVersion()),
                "parameter `Version` should not be empty");
        String url = urlFmt + ClientConstants.FGS_ALIASES + ClientConstants.URI_SEP + "%s";
        return put(FunctionVersionAlias.class, uri(url, functionUrn, aliasName)).entity(functionVersionAlias)
                .execute();
    }


    /**
     * Delete function version alias
     *
     * @param functionUrn
     * @param alias_name   Function version alias
     */
    public ActionResponse deleteVersionAlias(String functionUrn, String alias_name) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(alias_name), "parameter `alias_name` should not be empty");
        String url = urlFmt + ClientConstants.FGS_ALIASES + ClientConstants.URI_SEP + "%s";
        return deleteWithResponse(uri(url, functionUrn, alias_name)).execute();
    }

    /**
     * Get the version alias information specified by the function
     *
     * @param functionUrn
     * @param alias_name   Specify function name
     * @return
     */
    public FunctionVersionAlias getVersionAlias(String functionUrn, String alias_name) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(alias_name), "parameter `alias_name` should not be empty");
        String url = urlFmt + ClientConstants.FGS_ALIASES + ClientConstants.URI_SEP + "%s";
        return get(FunctionVersionAlias.class, uri(url, functionUrn, alias_name)).execute();
    }

    /**
     * Get the list of function version aliases
     *
     * @param functionUrn
     * @return
     */
    public FunctionVersionAlias[] listVersionAlias(String functionUrn) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        String url = urlFmt + ClientConstants.FGS_ALIASES;
        return get(FunctionVersionAlias[].class, uri(url, functionUrn)).execute();
    }
}
