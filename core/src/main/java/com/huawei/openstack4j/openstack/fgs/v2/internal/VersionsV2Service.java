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
package com.huawei.openstack4j.openstack.fgs.v2.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.fgs.v2.domain.FunctionVersionAlias;

import static com.google.common.base.Preconditions.checkArgument;

public class VersionsV2Service extends BaseFunctionGraphV2Service {

    private final String urlFmt = ClientConstants.FGS_PATH_V2 + ClientConstants.URI_SEP + "%s";


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
     * @param aliasName   Function version alias
     */
    public ActionResponse deleteVersionAlias(String functionUrn, String aliasName) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(aliasName), "parameter `alias_name` should not be empty");
        String url = urlFmt + ClientConstants.FGS_ALIASES + ClientConstants.URI_SEP + "%s";
        return deleteWithResponse(uri(url, functionUrn, aliasName)).execute();
    }

    /**
     * Get the version alias information specified by the function
     *
     * @param functionUrn
     * @param aliasName   Specify function name
     * @return
     */
    public FunctionVersionAlias getVersionAlias(String functionUrn, String aliasName) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(aliasName), "parameter `alias_name` should not be empty");
        String url = urlFmt + ClientConstants.FGS_ALIASES + ClientConstants.URI_SEP + "%s";
        return get(FunctionVersionAlias.class, uri(url, functionUrn, aliasName)).execute();
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
