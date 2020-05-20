package sample;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.ecs.v1.domain.OSReinstall;
import com.huawei.openstack4j.openstack.common.AsyncJobEntity;
import com.huawei.openstack4j.openstack.ecs.v1.domain.OSReinstallMetadata;
import com.huawei.openstack4j.openstack.ecs.v1.domain.Job;
import sun.misc.BASE64Encoder;

public class CloudServer {
    public static void waitTime(int times,int interval,OSClientV3 osclient,String jobId){
        for(int i=1;i<=times;i++) {
            try {
                Thread.sleep(interval*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Job job = osclient.ecs().jobs().get(jobId);
            if("SUCCESS".equals(job.getStatus())) {
                System.out.println("job success after " + i + " tries");
                break;
            }else if("FAIL".equals(job.getStatus())){
                System.out.println("job failed after " + i + " tries");
                break;
            }
        }
    }

    public static void main(String[] args) {

        // Using credentials for authentication
        String authUrl = "https://iam.XXX.YYY.com/v3"; //endpoint Url
        String user = "xxxxx"; //username
        String password = "xxxxx"; //password
        String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
        String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

        // create connection
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId)).authenticate();

        String serverId = "server_id";

        int times = 60;
        int interval = 10;

        //reinstall server operating system
        String userDataOrg = "#!/bin/bash \r\n echo 'root:xxxxxx' | chpasswd ;";
        byte[] userDataByte = userDataOrg.getBytes();
        String userData = new BASE64Encoder().encode(userDataByte);

        OSReinstallMetadata metadata = OSReinstallMetadata.builder().userData(userData).build();
        OSReinstall osReinstall = OSReinstall.builder().adminPass("xxxxx").metadata(metadata).build();

        AsyncJobEntity asyncJobEntity = os.ecs().serversV2().reinstallOS(osReinstall, serverId);
        System.out.println("job id : " + asyncJobEntity.getJobId());
        waitTime(times,interval, os, asyncJobEntity.getJobId());
    }
}
