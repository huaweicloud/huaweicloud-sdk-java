/*******************************************************************************
 * 	Copyright 2019 HuaWei tld and OTC
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
package com.huawei.openstack4j.api.message.queue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.testng.annotations.Test;

import com.google.common.collect.Maps;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.common.AsyncHandler;
import com.huawei.openstack4j.openstack.message.queue.constant.ConsumeStatus;
import com.huawei.openstack4j.openstack.message.queue.domain.ConsumeConfirmResponse;
import com.huawei.openstack4j.openstack.message.queue.domain.QueueMessage;
import com.huawei.openstack4j.openstack.message.queue.domain.QueueMessageWithHandler;

@Test(suiteName = "MessageQueue/QueueMessageAsync", enabled = true)
public class QueueMessageAsyncTest extends AbstractTest {

    @Test
    public void produceAsyncTest(){
        HashMap<String, Object> attributes1 = Maps.newHashMap();
        attributes1.put("attr1", 1);
        attributes1.put("attr2", false);
        String queueId = "queue-id";
        QueueMessage message = QueueMessage.builder().body("sdk-unittests").attributes(attributes1).build();
        Future<ActionResponse> actionResponseFuture = osv3().messageQueueAsync().produceAsync(
                queueId, message, new AsyncHandler<ActionResponse>() {
                    @Override
                    public void onError(Exception exception) {
                        //发生错误执行的语句
                        System.out.println(exception);
                    }

                    @Override
                    public void onSuccess(ActionResponse result) {
                        //调用成功执行的语句
                        System.out.println(result);
                    }
                });
    }

    @Test
    public void consumeAsyncTest(){
        String queueId = "queue-id";
        String consumerGroupId = "consumergroup-id";
        Integer maxMessages = 5;
        Integer timeWait = 10;
        Future<List<QueueMessageWithHandler>> messageHandler = osv3().messageQueueAsync().consumeAsync(
                queueId,consumerGroupId,maxMessages,timeWait, new AsyncHandler<List<QueueMessageWithHandler>>() {
                    @Override
                    public void onError(Exception exception) {
                        //发生错误执行的语句
                        System.out.println(exception);
                    }

                    @Override
                    public void onSuccess(List<QueueMessageWithHandler> queueMessageWithHandlers) {
                        //调用成功执行的语句
                        System.out.println(queueMessageWithHandlers);
                    }
                });
    }

    @Test
    public void confirmConsumingAsyncTest(){
        String queueId = "queue-id";
        String consumerGroupId = "consumergroup-id";
        Map<String, ConsumeStatus> consumeResult = Maps.newHashMap();
        consumeResult.put("handler1", ConsumeStatus.SUCCESS);
        consumeResult.put("handler2", ConsumeStatus.SUCCESS);

        Future<ConsumeConfirmResponse> consumeConfirmResponseFuture = osv3().messageQueueAsync().confirmConsumingAsync(queueId, consumerGroupId, consumeResult, new AsyncHandler<ConsumeConfirmResponse>() {
            @Override
            public void onError(Exception exception) {
                //发生错误执行的语句
                System.out.println(exception);
            }

            @Override
            public void onSuccess(ConsumeConfirmResponse consumeConfirmResponse) {
                //调用成功执行的语句
                System.out.println(consumeConfirmResponse);
            }
        });
    }

    @Override
    protected Service service() {
        return Service.MESSAGE_QUEUE;
    }
}
