package com.huawei.openstack.sample.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Domain;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.v3.domain.PasswordConfig;

import java.util.List;

public class DomainDemo {

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

        //Query the list of domains accessible to users
        //GET  /v3/auth/domains
        String tokenId = osclient.getToken().getId();
        List<? extends Domain> domainList = osclient.identity().tokens().getDomainScopes(tokenId);
        for(Domain domain : domainList){
            System.out.println(domain);
        }
        //Query domain password strength config
        //GET  /v3/domains/{domain_id}/config/security_compliance
        String domainId = "**********";
        PasswordConfig sampleConfig = osclient.identity().domains().getDomainPasswordConfig(domainId);
        System.out.println(sampleConfig);

        //Query domain password strength config by option
        //GET  /v3/domains/{domain_id}/config/security_compliance/{option}
        //sample 01
        String domainId03 = "**********";
        String option03 = "password_regex";   //the value of option can be 'password_regex' or 'password_regex_description'
        PasswordConfig sampleConfig03 = osclient.identity().domains().getDomainPasswordConfigByOption(domainId03, option03);
        System.out.println(sampleConfig03);

        //Query domain password strength config by option
        //GET  /v3/domains/{domain_id}/config/security_compliance/{option}
        //sample 02
        String domainId04 = "**********";
        String option04 = "password_regex_description";   //the value of option can be 'password_regex' or 'password_regex_description'
        PasswordConfig sampleConfig04 = osclient.identity().domains().getDomainPasswordConfigByOption(domainId04, option04);
        System.out.println(sampleConfig04);


    }
}
