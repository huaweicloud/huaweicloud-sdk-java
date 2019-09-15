package com.huawei.openstack4j.api.fgs.v2;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.fgs.v2.domain.FunctionVersionAlias;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VersionV2Tests extends AbstractTest {

    private static final String FGS_VERSION_ALIASES = "/fgs/v1/functionGraph_createVersionAliases.json";
    private static final String FGS_LIST_VERSION_ALIASES = "/fgs/v1/functionGraph_listVersionAliases.json";
    private static final String FUNCTION_URN = "urn:fss:cn-north-7:a1eaedeb26e6497a987ea3386b174ffc:function:default:test001";
    private static final String VERSION_NAME = "replace-you-name";
    private static final int LENGTH = 1;
    private static final int DELETE_HTTP_CODE = 204;

    private String version = "replace-you-version";

    private OSClient.OSClientV3 MyOsclient;

    VersionV2Tests() {
        String authUrl = "https://127.0.0.1/v3";//endpointUrl
        String user = "xxxxxxx";//用户名
        String password = "xxxxxxx";//用户密码
        String projectId = "xxxxxxx";//项目ID
        String userDomainId = "xxxxxxx";//账号ID

        OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
        endpointResolver.addOverrideEndpoint(ServiceType.FGS,"https://127.0.0.1:443");
        endpointResolver.addOverrideEndpoint(ServiceType.IAM,"https://127.0.0.1:31943");

        OSFactory.enableHttpLoggingFilter(true);
        Config config = Config.newConfig().withEndpointURLResolver(endpointResolver).withLanguage("zh-cn")
                .withSSLVerificationDisabled();
        this.MyOsclient = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
                .authenticate();
    }

    @Override
    protected Service service() {
        return Service.FGS;
    }

    @Test
    public void createVersionAliasTest() throws IOException {
        respondWith(FGS_VERSION_ALIASES);
        FunctionVersionAlias versionAlias =this.MyOsclient.functionGraphV2().versions().createVersionAlias(FUNCTION_URN, getFunctionVersionAlias());

        assertEquals(versionAlias.getName(), VERSION_NAME);
    }

    @Test
    public void updateVersionAliasTest() throws IOException {
        respondWith(FGS_VERSION_ALIASES);
        FunctionVersionAlias functionVersionAlias = this.MyOsclient.functionGraphV2().versions().updateVersionAlias(FUNCTION_URN, getFunctionVersionAlias());

        assertEquals(functionVersionAlias.getName(), VERSION_NAME);
    }

    @Test
    public void getVersionAliasTest() throws IOException {
        respondWith(FGS_VERSION_ALIASES);
        FunctionVersionAlias versionAlias = this.MyOsclient.functionGraphV2().versions().getVersionAlias(FUNCTION_URN, VERSION_NAME);

        assertEquals(versionAlias.getName(), VERSION_NAME);
    }

    @Test
    public void listVersionAliasTest() throws IOException {
        respondWith(FGS_LIST_VERSION_ALIASES);
        FunctionVersionAlias[] functionVersionAliases = this.MyOsclient.functionGraphV2().versions().listVersionAlias(FUNCTION_URN);

        assertEquals(functionVersionAliases.length, LENGTH);
        assertEquals(functionVersionAliases[0].getName(),VERSION_NAME);
    }

    @Test
    public void deleteVersionAliasTest() {
        respondWith(DELETE_HTTP_CODE);
        ActionResponse actionResponse = this.MyOsclient.functionGraphV2().versions().deleteVersionAlias(FUNCTION_URN, VERSION_NAME);

        assertTrue(actionResponse.isSuccess());
    }

    private FunctionVersionAlias getFunctionVersionAlias() {
        return FunctionVersionAlias.builder()
                .name("replace-you-name")
                .version("latest")
                .build();
    }
}
