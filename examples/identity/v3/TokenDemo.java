package com.huawei.openstack.sample.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.v3.Token;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.v3.domain.token.Assumerole;
import com.huawei.openstack4j.openstack.identity.v3.domain.token.Auth;
import com.huawei.openstack4j.openstack.identity.v3.domain.token.Domain;
import com.huawei.openstack4j.openstack.identity.v3.domain.token.Identity;
import com.huawei.openstack4j.openstack.identity.v3.domain.token.Password;
import com.huawei.openstack4j.openstack.identity.v3.domain.token.Project;
import com.huawei.openstack4j.openstack.identity.v3.domain.token.Scope;
import com.huawei.openstack4j.openstack.identity.v3.domain.token.Totp;
import com.huawei.openstack4j.openstack.identity.v3.domain.token.User;

import java.util.Arrays;

public class TokenDemo {
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

        //Check the validity of a specified token with catalog
        //GET  /v3/auth/tokens
        String getTokenId01 = "**********";
        Token getToken01 = osclient.identity().tokens().get(getTokenId01);
        System.out.println(getToken01);

        //Check the validity of a specified token without catalog
        //GET  /v3/auth/tokens
        String getTokenId02 = "**********";
        Token getToken02 = osclient.identity().tokens().getWithoutCatalog(getTokenId02,"**********");
        System.out.println(getToken02);

        //create user token by password
        //POST  /v3/auth/tokens
        //sample 01
        String nocatalog = "**********";
        String method01 = "password";
        String domainName01 = "**********";
        String userName01 = "**********";
        String password01 = "**********";
        String projectName01 = "**********";
        Auth auth01 = Auth.builder().identity(Identity.builder()
                .password(Password.builder().user(User.builder().domain(Domain.builder().name(domainName01).build()).name(userName01).password(password01).build()).build())
                .methods(Arrays.asList(method01)).build())
                .scope(Scope.builder().project(Project.builder().name(projectName01).build()).build()).build();
        Token token01 = osclient.identity().tokens().create(nocatalog,auth01);
        System.out.println(token01);

        //create user token by password
        //POST  /v3/auth/tokens
        //sample 02
        String nocatalog02 = "**********";
        String method02 = "password";
        String domainName02 = "**********";
        String userName02 = "**********";
        String password02 = "**********";
        Auth auth02 = Auth.builder().identity(Identity.builder()
                .password(Password.builder().user(User.builder().domain(Domain.builder().name(domainName02).build()).name(userName02).password(password02).build()).build())
                .methods(Arrays.asList(method02)).build())
                .scope(Scope.builder().domain(Domain.builder().name(domainName02).build()).build()).build();
        Token token02 = osclient.identity().tokens().create(nocatalog02,auth02);
        System.out.println(token02);

        //create user token by agency token
        //POST  /v3/auth/tokens
        //sample 03
        String nocatalog03 = "**********";
        String xroleName03 = "**********";
        String method03 = "assume_role";
        String projectName03 = "**********";
        String domainId03 = "**********";
        Auth auth03 = Auth.builder().identity(Identity.builder()
                .assumerole(Assumerole.builder().domainId(domainId03).xroleName(xroleName03).build())
                .methods(Arrays.asList(method03)).build())
                .scope(Scope.builder().project(Project.builder().name(projectName03).build()).build()).build();
        Token token03 = osclient.identity().tokens().create(nocatalog03,auth03);
        System.out.println(token03.getAssumedBy().toString());

        //create user token by agency token
        //POST  /v3/auth/tokens
        //sample 04
        String nocatalog04 = "**********";
        String xroleName04 = "**********";
        String method04 = "assume_role";
        String domainName04 = "**********";
        Auth auth04 = Auth.builder().identity(Identity.builder()
                .assumerole(Assumerole.builder().domainName(domainName04).xroleName(xroleName04).build())
                .methods(Arrays.asList(method04)).build())
                .scope(Scope.builder().domain(Domain.builder().name(domainName04).build()).build()).build();
        Token token04 = osclient.identity().tokens().create(nocatalog04,auth04);
        System.out.println(token04);

        //create user token by password and MFA
        //POST  /v3/auth/tokens
        //sample 05
        String nocatalog05 = "**********";
        String[] methods05 = {"password", "totp"};
        String domainName05 = "**********";
        String userName05 = "**********";
        String password05 = "**********";
        String userId05 = "**********";
        String passcode05 = "**********";
        Auth auth05 = Auth.builder().identity(Identity.builder()
                .totp(Totp.builder().user(User.builder().passcode(passcode05).id(userId05).build()).build())
                .password(Password.builder().user(User.builder().password(password05).name(userName05).domain(Domain.builder().name(domainName05).build()).build()).build())
                .methods(Arrays.asList(methods05)).build())
                .scope(Scope.builder().domain(Domain.builder().name(domainName05).build()).build()).build();
        Token token05 = osclient.identity().tokens().create(nocatalog05,auth05);
        System.out.println(token05);

        //create user token by password and MFA
        //POST  /v3/auth/tokens
        //sample 06
        String nocatalog06 = "**********";
        String[] methods06 = {"password", "totp"};
        String domainName06 = "**********";
        String userName06 = "**********";
        String password06 = "**********";
        String userId06 = "**********";
        String passcode06 = "**********";
        String projectName06 = "**********";
        Auth auth06 = Auth.builder().identity(Identity.builder()
                .totp(Totp.builder().user(User.builder().passcode(passcode06).id(userId06).build()).build())
                .password(Password.builder().user(User.builder().password(password06).name(userName06).domain(Domain.builder().name(domainName06).build()).build()).build())
                .methods(Arrays.asList(methods06)).build())
                .scope(Scope.builder().project(Project.builder().name(projectName06).build()).build()).build();
        Token token06 = osclient.identity().tokens().create(nocatalog06,auth06);
        System.out.println(token06);
    }
}
