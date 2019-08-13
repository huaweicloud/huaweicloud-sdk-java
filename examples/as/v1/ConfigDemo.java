/*******************************************************************************
 *  Copyright 2019 Huawei Technologies Co., Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 *******************************************************************************/
package com.huawei.openstack.sample;

import java.util.ArrayList;
import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.Bandwidth;
import com.huawei.openstack4j.model.scaling.Disk;
import com.huawei.openstack4j.model.scaling.Disk.DiskType;
import com.huawei.openstack4j.model.scaling.Disk.VolumeType;
import com.huawei.openstack4j.model.scaling.Eip;
import com.huawei.openstack4j.model.scaling.InstanceConfig;
import com.huawei.openstack4j.model.scaling.PublicIp;
import com.huawei.openstack4j.model.scaling.ScalingConfig;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingConfigCreate;

import jersey.repackaged.com.google.common.collect.Lists;

public class ConfigDemo {
    AuthenticationDemo as_auth = new AuthenticationDemo();
    
    OSClientV3 osclient = as_auth.initailV3Client();
    
    // List all scalingConfigs
    public void listConfigs() {
        List< ? extends ScalingConfig> configlist = osclient.autoScaling().configs().list();
        
        if (null != configlist) {
            System.out.println("List all scalingConfigs success, size is = " + configlist.size());
        } else {
            System.out.println("get scalingConfigs failed");
        }
    }
    
    // Get scalingConfig
    public void getConfig(String configId) {
        ScalingConfig config = osclient.autoScaling().configs().get(configId);
        if (null != config) {
            System.out.print("get scalingConfig success, id = " + config.toString());
        } else {
            System.out.println("get scalingConfig failed");
        }
    }
    
    // Create scalingConfig
    public String creatConfig() {
        String keyname = "******";
        Disk disk = Disk.builder().size(40).volumeType(VolumeType.SATA).diskType(DiskType.SYS).build();
        Bandwidth build = Bandwidth.builder().chargingMode(Bandwidth.ChargingMode.TRAFFIC)
            .shareType(Bandwidth.ShareType.PER).size("100").build();
            
        Eip eip = Eip.builder().ipType("5_sbgp").bandwidth(build).build();
        PublicIp publicIp = PublicIp.builder().eip(eip).build();
        
        InstanceConfig instaceConfig = InstanceConfig.builder().flavorRef("c2.medium").imageRef("******")
            .disks(Lists.newArrayList(disk)).keyName(keyname).publicIp(publicIp).userData("MTlz").build();
        ASAutoScalingConfigCreate config =
            ASAutoScalingConfigCreate.builder().configName("test-config").instanceConfig(instaceConfig).build();
        String configId = osclient.autoScaling().configs().create(config);
        if (null != configId) {
            System.out.println("create scalingConfig success, id = " + configId);
            return configId;
        } else {
            System.out.println("create scalingConfig failed");
            return null;
        }
    }
    
    // Delete scalingConfig
    public void deleteConfig(String configId) {
        ActionResponse resp = osclient.autoScaling().configs().delete(configId);
        if (resp.isSuccess()) {
            System.out.println("Delete scalingConfig success");
        }
        
        ArrayList<String> configs = Lists.newArrayList();
        configs.add(configId);
        resp = osclient.autoScaling().configs().delete(configs);
        
    }
    
    public static void main(String[] args) {
        ConfigDemo asConfig = new ConfigDemo();
        String configId = asConfig.creatConfig();
        if (null == configId) {
            return;
        }
        asConfig.getConfig(configId);
        asConfig.listConfigs();
        asConfig.deleteConfig(configId);
        
    }
}
