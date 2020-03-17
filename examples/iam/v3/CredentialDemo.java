package com.huawei.openstack.sample.iam.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.iam.domain.credentialResource.CreatePermanentCredentialReq;
import com.huawei.openstack4j.openstack.iam.domain.credentialResource.PermanentCredentialResp;
import com.huawei.openstack4j.openstack.iam.domain.credentialResource.Credentials;
import com.huawei.openstack4j.openstack.iam.domain.credentialResource.ListPermanentCredential;
import com.huawei.openstack4j.openstack.iam.domain.credentialResource.UpdateCredentialReq;
import com.huawei.openstack4j.openstack.iam.domain.credentialResource.UpdateCredentialResp;

public class CredentialDemo {
    public static void main(String[] args){

        String user = "**********";
        String password = "**********";
        String userDomainId = "**********";
        String authUrl = "**********";

        OSClient.OSClientV3 osclient = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId))
                .authenticate();

        //Create the credential for user
        //POST  /v3.0/OS-CREDENTIAL/credentials
        CreatePermanentCredentialReq createPermanentCredentialReq = CreatePermanentCredentialReq.builder().userId("**********").build();
        PermanentCredentialResp permanentCredentialResp = osclient.iam().credentials().createPermanentAccessKey(createPermanentCredentialReq);
        System.out.println(permanentCredentialResp);

        //Delete permanent access key
        //DELETE  /v3.0/OS-CREDENTIAL/credentials/{access_key}
        String accessKey = "**********";
        ActionResponse response = osclient.iam().credentials().deletePermanentAccessKey(accessKey);
        if (response.isSuccess()) {
            System.out.println("Delete access key successfully");
        } else {
            System.out.println("Update access key failed : " + response.getFault());
        }

        //List all permanent credentials
        //GET  /v3.0/OS-CREDENTIAL/credentials
        String userId = "**********";
        Credentials credentiallist = osclient.iam().credentials().listPermanentAccessKeys(userId);
        for (ListPermanentCredential credential : credentiallist.getList()) {
            System.out.println(credential);
        }

        //Query permanent access key
        //GET  /v3.0/OS-CREDENTIAL/credentials/{access_key}
        String accessKey07 = "**********";
        PermanentCredentialResp permanentCredentialResp07 = osclient.iam().credentials().queryPermanentAccessKey(accessKey07);
        System.out.println(permanentCredentialResp07);

        //Update permanent access key
        //PUT  /v3.0/OS-CREDENTIAL/credentials/{access_key}
        String accessKey08 = "**********";
        UpdateCredentialReq updateCredentialReq08 = UpdateCredentialReq.builder().description("**********").status("**********").build();
        UpdateCredentialResp updateCredentialResp08 = osclient.iam().credentials().updatePermanentAccessKey(accessKey08, updateCredentialReq08);
        System.out.println(updateCredentialResp08);

    }
}
