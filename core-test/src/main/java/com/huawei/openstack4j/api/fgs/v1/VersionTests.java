package com.huawei.openstack4j.api.fgs.v1;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FunctionMetadata;
import com.huawei.openstack4j.openstack.fgs.v1.domain.FunctionVersionAlias;
import com.huawei.openstack4j.openstack.fgs.v1.domain.StrategyConfig;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VersionTests extends AbstractTest {

    private static final String FGS_PUBLIC = "/fgs/v1/functionGraph_publishVersion.json";
    private static final String FGS_LIST_VERSION = "/fgs/v1/functionGraph_listVersion.json";
    private static final String FGS_VERSION_ALIASES = "/fgs/v1/functionGraph_createVersionAliases.json";
    private static final String FGS_LIST_VERSION_ALIASES = "/fgs/v1/functionGraph_listVersionAliases.json";
    private static final String FUNCTION_URN = "xxx";
    private static final String CODE_SIZE = "272";
    private static final String VERSION_NAME = "dev";
    private static final int LENGTH = 1;
    private static final int DELETE_HTTP_CODE = 204;
    private static final int MEMORY_SIZE = 128;
    private static final int TIME_OUT = 30;



    @Override
    protected Service service() {
        return Service.FGS;
    }

    @Test
    public void publishVersionTest() throws IOException {
        respondWith(FGS_PUBLIC);
        String version = "replace-you-version";
        FunctionMetadata functionMetadata = FunctionMetadata.builder()
                .funcName("replace-you-funcName")
                .strategyConfig(StrategyConfig.builder().concurrency(-1).build())
                .runtime(ClientConstants.FGS_RUNGTIME_PYTHON2)
                .handler("test.handler")
                .memorySize(MEMORY_SIZE)
                .timeout(TIME_OUT)
                .build();
        FunctionMetadata build = functionMetadata.builder().version(version).build();
        FunctionMetadata functionMetadata1 = osv3().functionGraph().versions().publishVersion(FUNCTION_URN, build);
        assertEquals(functionMetadata1.getCodeSize(), CODE_SIZE);
    }

    @Test
    public void listFunctionVersionsTest() throws IOException {
        int marker = 0;
        int maxItems = 40;
        respondWith(FGS_LIST_VERSION);
        FunctionMetadata.FunctionVersions functionVersions = osv3().functionGraph().versions().listFunctionVersions(FUNCTION_URN, marker, maxItems);
        List<FunctionMetadata> data = functionVersions.getData();
        assertEquals(data.size(), LENGTH);
        assertEquals(data.get(0).getCodeSize(), CODE_SIZE);
    }

    @Test
    public void createVersionAliasTest() throws IOException {
        respondWith(FGS_VERSION_ALIASES);
        FunctionVersionAlias versionAlias = osv3().functionGraph().versions().createVersionAlias(FUNCTION_URN, getFunctionVersionAlias());
        assertEquals(versionAlias.getName(), VERSION_NAME);
    }

    @Test
    public void updateVersionAliasTest() throws IOException {
        respondWith(FGS_VERSION_ALIASES);
        FunctionVersionAlias functionVersionAlias = osv3().functionGraph().versions().updateVersionAlias(FUNCTION_URN, getFunctionVersionAlias());
        assertEquals(functionVersionAlias.getName(), VERSION_NAME);
    }

    @Test
    public void deleteVersionAliasTest() {
        respondWith(DELETE_HTTP_CODE);
        ActionResponse actionResponse = osv3().functionGraph().versions().deleteVersionAlias(FUNCTION_URN, VERSION_NAME);
        assertTrue(actionResponse.isSuccess());
    }

    @Test
    public void getVersionAliasTest() throws IOException {
        respondWith(FGS_VERSION_ALIASES);
        FunctionVersionAlias versionAlias = osv3().functionGraph().versions().getVersionAlias(FUNCTION_URN, VERSION_NAME);
        assertEquals(versionAlias.getName(), VERSION_NAME);
    }

    @Test
    public void listVersionAliasTest() throws IOException {
        respondWith(FGS_LIST_VERSION_ALIASES);
        FunctionVersionAlias[] functionVersionAliases = osv3().functionGraph().versions().listVersionAlias(FUNCTION_URN);
        assertEquals(functionVersionAliases.length, LENGTH);
        assertEquals(functionVersionAliases[0].getName(),VERSION_NAME);
    }

    private FunctionVersionAlias getFunctionVersionAlias() {
        return FunctionVersionAlias.builder()
                .name("replace-you-name")
                .version("replace-you-Version")
                .build();
    }
}
