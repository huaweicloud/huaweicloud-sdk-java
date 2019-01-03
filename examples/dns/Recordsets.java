package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.dns.v2.RecordSetType;
import com.huawei.openstack4j.model.dns.v2.Recordset;
import com.huawei.openstack4j.model.dns.v2.ZoneType;
import com.huawei.openstack4j.model.dns.v2.builder.RecordsetBuilder;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.dns.v2.options.RecordsetListOptions;
import com.huawei.openstack.sample.Constant;


import java.util.ArrayList;
import java.util.List;

public class Recordsets {
    private static OSClient.OSClientV3 os = null;
    public static void main(String[] args){

         os = OSFactory.builderV3()
                .endpoint(Constants.AUTH_URL)
                .credentials(Constants.USERNAME, Constants.PASSWORD, Identifier.byId(Constants.USERDOMAINID))
                .scopeToProject(Identifier.byId(Constants.PROJECT_ID))
                .authenticate();
         //create Recordset
         createRecordset();

         //list Recordsets
         list();

         //list Recordsets by options
         listByOptions();

         //list Recordsets by zone id
         listByZoneId();

         //list Recordsets by zone id and limit
         listByZoneIdAndLimit();
         
         //delete Recordset
         deleteRecordset();

         //another method to create Recordset
         createRecordset2();

         //list Recordsets
         list();

         //list Recordsets by options
         listByOptions();

         //list Recordsets by zone id
         listByZoneId();

         //list Recordsets by zone id and limit
         listByZoneIdAndLimit();

         //delete Recordset
         deleteRecordset();
    }

    private static void createRecordset() {
        RecordsetBuilder builder = Builders.recordset();
        List<String> records = new ArrayList<>();
        records.add(Constants.A_VALUE);
        Recordset recordset = (Recordset) builder.name(Constants.RECORD_NAME)
                .description(Constants.RECORDSET_DESCRIPTION)
                .ttl(Constants.TTL)
                .type(RecordSetType.A)
                .records(records)
                .build();
        Recordset result = os.dns().recordsets().create(Constants.RECORDSET_ZONE_ID,recordset);
        Constants.RECORDSET_ID = result.getId();
    }

    private static void createRecordset2() {
        List recordList = new ArrayList<String>();
        recordList.add(Constants.A_VALUE);
        Recordset result = os.dns().recordsets()
                .create(Constants.RECORDSET_ZONE_ID, Constants.RECORD_NAME, Constants.RECORDSET_DESCRIPTION, Constants.TYPE,Constants.TTL, recordList);
        Constants.RECORDSET_ID = result.getId();
    }

    private static void listByOptions() {
        RecordsetListOptions options = new RecordsetListOptions();
        options.zoneType(ZoneType.PRIVATE);
        options.limit(Constants.LIMIT_VALUE);
        os.dns().recordsets().list(options);
    }

    private static void listByZoneIdAndLimit() {
        os.dns().recordsets().list(Constants.RECORDSET_ZONE_ID,Constants.LIMIT_VALUE,null);
    }

    private static void listByZoneId() {
        os.dns().recordsets().list();
    }

    private static void list() {
        os.dns().recordsets().list();
    }

    private static void getByZoneIdAndRecordsetId() {
        os.dns().recordsets().get(Constants.RECORDSET_ZONE_ID,Constants.RECORDSET_ID);
    }

    private static void deleteRecordset(){
        os.dns().recordsets().delete(Constants.RECORDSET_ZONE_ID, Constants.RECORDSET_ID);
    }
}
