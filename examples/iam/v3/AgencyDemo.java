package com.huawei.openstack.sample.iam.v3;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.iam.domain.agencyResource.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgencyDemo {
    public static void main(String[] args) {

        String user = "**********";
        String password = "**********";
        String userDomainId = "**********";
        String authUrl = "**********";

        OSClient.OSClientV3 osclient = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToDomain(Identifier.byId(userDomainId))
                .authenticate();

        //Check permission for agency on domain
        //HEAD  /v3.0/OS-AGENCY/domains/{domain_id}/agencies/{agency_id}/roles/{role_id}
        String domianId01 = "**********";
        String agencyId01 = "**********";
        String roleId01 = "**********";
        ActionResponse actionResponse01 = osclient.iam().agencies().checkPermissionOnDomain(domianId01, agencyId01, roleId01);
        if (actionResponse01.isSuccess()) {
            System.out.println("The Agency has the permission on this domain.");
        } else {
            System.out.println("The Agency does not have the permission on this domain: " + actionResponse01.getFault());
        }

        //Check permission for agency on project
        //HEAD  /v3.0/OS-AGENCY/projects/{project_id}/agencies/{agency_id}/roles/{role_id}
        String projectId02 = "**********";
        String agencyId02 = "**********";
        String roleId02 = "**********";
        ActionResponse actionResponse02 = osclient.iam().agencies().checkPermissionOnProject(projectId02, agencyId02, roleId02);
        if (actionResponse02.isSuccess()) {
            System.out.println("The Agency has the permission on this project.");
        } else {
            System.out.println("The Agency does not have the permission on this project : " + actionResponse02.getFault());
        }

        //Create agency
        //POST  /v3.0/OS-AGENCY/agencies
        String description03 = "**********";
        String domainId03 = "**********";
        String duration03 = "**********";
        String name03 = "**********";
        String trustDomainId03 = "**********";
        String trustDomainName03 = "**********";
        CreateAgencyReq createAgencyReq03 = CreateAgencyReq.builder().description(description03).duration(duration03).domainId(domainId03)
                .name(name03).trustDomainId(trustDomainId03).trustDomainName(trustDomainName03).build();
        AgencyResp agencyResp03 = osclient.iam().agencies().create(createAgencyReq03);
        System.out.println(agencyResp03);

        //Delete agency
        //DELETE  /v3.0/OS-AGENCY/agencies/{agency_id}
        String agecyId04 = "**********";
        ActionResponse actionResponse04 = osclient.iam().agencies().delete(agecyId04);
        if (actionResponse04.isSuccess()) {
            System.out.println("Delete agency successfully");
        } else {
            System.out.println("Delete agency failed : " + actionResponse04.getFault());
        }

        //Grant permissioin to agency on domain
        //PUT  /v3.0/OS-AGENCY/domains/{domain_id}/agencies/{agency_id}/roles/{role_id}
        String domianId05 = "**********";
        String agencyId05 = "**********";
        String roleId05 = "**********";
        ActionResponse actionResponse05 = osclient.iam().agencies().addPermissionOnDomain(domianId05, agencyId05, roleId05);
        if (actionResponse05.isSuccess()) {
            System.out.println("Grant agency permission on domain successfully.");
        } else {
            System.out.println("Grant agency permission on domain failed : " + actionResponse05.getFault());
        }

        //Grant permissioin to agency on project
        //PUT  /v3.0/OS-AGENCY/projects/{project_id}/agencies/{agency_id}/roles/{role_id}
        String projectId06 = "**********";
        String agencyId06 = "**********";
        String roleId06 = "**********";
        ActionResponse actionResponse06 = osclient.iam().agencies().addPermissionOnProject(projectId06, agencyId06, roleId06);
        if (actionResponse06.isSuccess()) {
            System.out.println("Grant agency permission on project successfully.");
        } else {
            System.out.println("Grant agency permission on project failed : " + actionResponse06.getFault());
        }

        //List agencies
        //GET  /v3.0/OS-AGENCY/agencies
        String domianId07 = "**********";
        String name07 = "**********";
        String trustDomainId07 = "**********";
        Map<String, String> listFilteringParams07 = new HashMap<>();
        listFilteringParams07.put("name", name07);
        listFilteringParams07.put("trust_domain_id", trustDomainId07);
        ListAgenciesResp listAgenciesResp07 = osclient.iam().agencies().listAgencies(domianId07, listFilteringParams07);
        for (AgencyResp agencyResp07 : listAgenciesResp07.getAgencies()) {
            System.out.println(agencyResp07);
        }

        //List permissions for agency on domain
        //GET  /v3.0/OS-AGENCY/domains/{domain_id}/agencies/{agency_id}/roles
        String domainId08 = "**********";
        String agencyId08 = "**********";
        ListPermissionsResp permissionList08 = osclient.iam().agencies().listPermissionsOnDomain(domainId08, agencyId08);
        for (AgencyRole permission08 : permissionList08.getRoles()) {
            System.out.println(permission08);
        }

        //List permissions for agency on project
        //GET  /v3.0/OS-AGENCY/projects/{project_id}/agencies/{agency_id}/roles
        String projectId09 = "**********";
        String agencyId09 = "**********";
        ListPermissionsResp permissionList09 = osclient.iam().agencies().listPermissionsOnProject(projectId09, agencyId09);
        for (AgencyRole permission09 : permissionList09.getRoles()) {
            System.out.println(permission09);
        }

        //Query agency detail
        //GET  /v3.0/OS-AGENCY/agencies/{agency_id}
        String agencyId10 = "**********";
        AgencyResp agencyResp10 = osclient.iam().agencies().get(agencyId10);
        System.out.println(agencyResp10);

        //Remove permissioin to agency on domain
        //DELETE  /v3.0/OS-AGENCY/domains/{domain_id}/agencies/{agency_id}/roles/{role_id}
        String domianId11 = "**********";
        String agencyId11 = "**********";
        String roleId11 = "**********";
        ActionResponse actionResponse11 = osclient.iam().agencies().deletePermissionOnDomain(domianId11, agencyId11, roleId11);
        if (actionResponse11.isSuccess()) {
            System.out.println("Delete agency permission on domain successfully.");
        } else {
            System.out.println("Delete agency permission on domain failed : " + actionResponse11.getFault());
        }

        //Remove permissioin to agency on project
        //DELETE  /v3.0/OS-AGENCY/projects/{project_id}/agencies/{agency_id}/roles/{role_id}
        String projectId12 = "**********";
        String agencyId12 = "**********";
        String roleId12 = "**********";
        ActionResponse actionResponse12 = osclient.iam().agencies().deletePermissionOnProject(projectId12, agencyId12, roleId12);
        if (actionResponse12.isSuccess()) {
            System.out.println("Delete agency permission on project successfully.");
        } else {
            System.out.println("Delete agency permission on project failed : " + actionResponse12.getFault());
        }

        //Update agency
        //PUT  /v3.0/OS-AGENCY/agencies/{agency_id}
        String agencyId13 = "**********";
        String description13 = "**********";
        String duration13 = "**********";
        String trustDomainId13 = "**********";
        String trustDomainName13 = "**********";
        UpdateAgencyReq updateAgencyReq13 = UpdateAgencyReq.builder().description(description13).duration(duration13)
                .trustDomainId(trustDomainId13).trustDomainName(trustDomainName13).build();
        AgencyResp agencyResp13 = osclient.iam().agencies().update(agencyId13, updateAgencyReq13);
        System.out.println(agencyResp13);


    }
}
