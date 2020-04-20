package com.huawei.openstack4j.openstack.rds.v3.internal;

//注册中心
import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.common.RestService;


public class RdsService extends BaseRdsServices implements RestService{

    public InstanceManageService instanceManage(){
    	return Apis.get(InstanceManageService.class);
    }

    //补充其他资源对象,例如参数配置、备份、等资源对象的操作


	//parament config
	public ParamConfigService params(){
		return Apis.get(ParamConfigService.class);
	}

	//backups and restore
	public BackupsAndRestoreService backupRestore(){
		return Apis.get(BackupsAndRestoreService.class);
	}
}
