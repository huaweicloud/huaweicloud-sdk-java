package com.huawei.openstack.sample;

public class Constants {
     static final String AUTH_URL = "https://iam.xxxxxxxx.xxxxxxx.com/v3"; //endpoint Url
     static final String USERNAME = "your username"; //username
     static final String PASSWORD = "your password"; //password
    public static final String PROJECT_ID = "your projectid"; //your projectid
    static final String USERDOMAINID = "your domainid"; //domainid

    //Zone
     static final String ZONE_NAME = "1018.com";
     static final String MAIL = "123@xxxx.com";
     static final String ZONE_DESCRIPTION = "no description";
     static String ZONE_ID = "";
     static final String REGION = "xxxxx";
     static final String ASSOICATE_ROUTER_ID = "router id";
     static final String CREATE_ROUTER_ID = "router id";

    //Recordset
     static final String RECORD_NAME = "www.1024.com";
     static final String RECORDSET_DESCRIPTION = "description";
     static final String TYPE = "A";
     static final String RECORDSET_ZONE_ID= "recordset zone id";
     static final String A_VALUE = "100.100.100.100";
     static final int TTL = 300;
     static  String RECORDSET_ID = "";
     static final int LIMIT_VALUE = 1;


    //PTR
     static final String PTRNAME = "a.b.c.d";
     static final String FLOATINGIPID = "floating ip id";
     static final String PTR_DESCRIPTION = "description";
     static final String LIMIT = "limit";
     static final String PTR_LIMIT_VALUE = "1";
}
