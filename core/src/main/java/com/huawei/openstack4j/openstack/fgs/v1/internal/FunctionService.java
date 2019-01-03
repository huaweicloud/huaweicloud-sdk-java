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

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class FunctionService extends BaseFunctionGraphService {

    private final String urlFmt = ClientConstants.FGS_PATH + ClientConstants.URI_SEP + "%s";

    /**
     * Get function list
     *
     * @param marker Last recorded position of the last query maxitems The maximum
     *               number of function records obtained per query, maximum: 400
     * @return function list
     */
    public FunctionMetadata.Functions listFunction(int marker, int maxItems) {
        checkArgument(!Strings.isNullOrEmpty(Integer.toString(marker)), "parameter `marker` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(Integer.toString(maxItems)), "parameter `maxItems` should not be empty");
        return get(FunctionMetadata.Functions.class, uri(ClientConstants.FGS_PATH)).param(ClientConstants.FGS_MARKER, marker)
                .param(ClientConstants.FGS_MAXITEMS, maxItems).execute();
    }

    /**
     * Get the metadata of the function
     *
     * @param functionUrn
     * @return FunctionMetadata Function metadata entity
     */
    public FunctionMetadata getFunctionMetadata(String functionUrn) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        String url = urlFmt + ClientConstants.FGS_CONFIG;
        return get(FunctionMetadata.class, uri(url, functionUrn)).execute();
    }

    /**
     * Get the code of the specified function
     *
     * @param functionUrn
     * @return FunctionMetadata Function metadata entity
     */
    public FunctionMetadata getFunctionCode(String functionUrn) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        String url = urlFmt + ClientConstants.FGS_CODE;
        return get(FunctionMetadata.class, uri(url, functionUrn)).execute();
    }

    /**
     * Create function
     *
     * @param functionMetadata Pending function entity
     * @return FunctionMetadata Function metadata entity
     */
    public FunctionMetadata createFunction(FunctionMetadata functionMetadata) {
        checkArgument(!Strings.isNullOrEmpty(functionMetadata.getFuncName()), "parameter `FuncName` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionMetadata.getPackageName()), "parameter `Package` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionMetadata.getCodeType()), "parameter `CodeType` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionMetadata.getHandler()), "parameter `Handler` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(Integer.toString(functionMetadata.getMemorySize())),
                "parameter `MemorySize` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionMetadata.getRuntime()), "parameter `Runtime` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(Integer.toString(functionMetadata.getTimeout())),
                "parameter `Timeout` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionMetadata.getFuncCode().getFile()),
                "parameter `FuncCode.File` should not be empty");
        return post(FunctionMetadata.class, ClientConstants.FGS_PATH).entity(functionMetadata).execute();
    }

    /**
     * Delete funtion
     *
     * @param functionUrn
     */
    public ActionResponse deleteFunction(String functionUrn) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        return deleteWithResponse(uri(urlFmt, functionUrn)).execute();
    }

    /**
     * Modify function code
     *
     * @param functionUrn
     * @param functionMetadata Modified function entity
     * @return
     */
    public FunctionMetadata updateFunctionCode(String functionUrn, FunctionMetadata functionMetadata) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionMetadata.getCodeType()), "parameter `CodeType` should not be empty");
        String url = urlFmt + ClientConstants.FGS_CODE;
        return put(FunctionMetadata.class, uri(url, functionUrn)).entity(functionMetadata).execute();
    }


    /**
     * Modify the function metadata information
     *
     * @param functionUrn
     * @param functionMetadata Modified function metadata entity
     * @return FunctionMetadata
     */
    public FunctionMetadata updateFunctionConfig(String functionUrn, FunctionMetadata functionMetadata) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        checkArgument(functionMetadata.getStrategyConfig() != null,
                "parameter `StrategyConfig` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(Integer.toString(functionMetadata.getStrategyConfig().getConcurrency())),
                "parameter `StrategyConfig.Concurrency` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionMetadata.getHandler()), "parameter `Handler` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(Integer.toString(functionMetadata.getMemorySize())),
                "parameter `MemorySize` should not be empty");
        checkArgument(!Strings.isNullOrEmpty(functionMetadata.getRuntime()), "parameter `Runtime` should not be empty");
        String url = urlFmt + ClientConstants.FGS_CONFIG;
        return put(FunctionMetadata.class, uri(url, functionUrn)).entity(functionMetadata).execute();
    }


    /**
     * Synchronous execution function
     *
     * @param functionUrn
     * @param data         Function's event/json format
     */
    public String invokeFunction(String functionUrn, Map<?, ?> data) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        String url = urlFmt + ClientConstants.FGS_INVOCATIONS;
        return post(String.class, uri(url, functionUrn)).entity(data).execute();
    }

    /**
     * Asynchronous call function
     *
     * @param functionUrn
     * @param data         Function's event/json format
     * @return
     */
    public String asyncInvokeFunction(String functionUrn, Map<?, ?> data) {
        checkArgument(!Strings.isNullOrEmpty(functionUrn), "parameter `functionUrn` should not be empty");
        String url = urlFmt + ClientConstants.FGS_INVOCATIONS_ASYNC;
        return post(String.class, uri(url, functionUrn)).entity(data).execute();
    }

}
