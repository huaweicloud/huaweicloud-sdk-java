/*******************************************************************************
 * 	Copyright 2020 Huawei Technologies Co.,Ltd.
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
package com.huawei.openstack4j.openstack.iam.internal;

import com.google.common.base.Strings;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.iam.domain.credentialResource.CreatePermanentCredentialReq;
import com.huawei.openstack4j.openstack.iam.domain.credentialResource.Credentials;
import com.huawei.openstack4j.openstack.iam.domain.credentialResource.PermanentCredentialResp;
import com.huawei.openstack4j.openstack.iam.domain.credentialResource.UpdateCredentialReq;
import com.huawei.openstack4j.openstack.iam.domain.credentialResource.UpdateCredentialResp;

import static com.google.common.base.Preconditions.checkArgument;
import static com.huawei.openstack4j.core.transport.ClientConstants.PATH_IAM_CREDENTIALS;

public class CredentialService extends BaseIamService {
    private static final String CREDENTIALS = "credentials";
    private static final String SECURITYTOKENS = "securitytokens";

    /**
     * Create a permanent credential for user
     *
     * @param createPermanentCredentialReq the user entity
     * @return the createCredentialResp
     */
    public PermanentCredentialResp createPermanentAccessKey(CreatePermanentCredentialReq createPermanentCredentialReq) {
        checkArgument(!Strings.isNullOrEmpty(createPermanentCredentialReq.getUserId()), "parameter user ID should not be empty");
        return post(PermanentCredentialResp.class, PATH_IAM_CREDENTIALS, "/", CREDENTIALS).entity(createPermanentCredentialReq).execute();
    }


    /**
     * Delete the permanent credential
     *
     * @param accessKey the access key which is inactive
     * @return the actionResponse
     */
    public ActionResponse deletePermanentAccessKey(String accessKey) {
        checkArgument(!Strings.isNullOrEmpty(accessKey), "parameter access key should not be empty");
        return delete(ActionResponse.class, PATH_IAM_CREDENTIALS, "/", CREDENTIALS, "/", accessKey).execute();
    }

    /**
     * get the user credential list
     *
     * @param userId the user id
     * @return the ListPermanentCredential
     */
    public Credentials listPermanentAccessKeys(String userId) {
        checkArgument(!Strings.isNullOrEmpty(userId), "parameter user id should not be empty");
        return get(Credentials.class, PATH_IAM_CREDENTIALS, "/", CREDENTIALS).param("user_id", userId).execute();
    }

    /**
     * get the user credential
     *
     * @param accessKey the accessKey
     * @return the PermanentCredentialResp
     */
    public PermanentCredentialResp queryPermanentAccessKey(String accessKey) {
        checkArgument(!Strings.isNullOrEmpty(accessKey), "parameter access key should not be empty");
        return get(PermanentCredentialResp.class, PATH_IAM_CREDENTIALS, "/", CREDENTIALS, "/", accessKey).execute();
    }

    /**
     * update the user credential status
     *
     * @param accessKey the accessKey
     * @return the PermanentCredentialResp
     */
    public UpdateCredentialResp updatePermanentAccessKey(String accessKey, UpdateCredentialReq updateCredentialReq) {
        checkArgument(!Strings.isNullOrEmpty(accessKey), "parameter access key should not be empty");
        return put(UpdateCredentialResp.class, PATH_IAM_CREDENTIALS, "/", CREDENTIALS, "/", accessKey).entity(updateCredentialReq).execute();
    }
}
