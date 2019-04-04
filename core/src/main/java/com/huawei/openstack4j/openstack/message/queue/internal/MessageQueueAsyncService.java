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
package com.huawei.openstack4j.openstack.message.queue.internal;

import static java.util.concurrent.Executors.newFixedThreadPool;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.common.AsyncHandler;
import com.huawei.openstack4j.openstack.internal.OSClientSession;
import com.huawei.openstack4j.openstack.message.queue.constant.ConsumeStatus;
import com.huawei.openstack4j.openstack.message.queue.domain.ConsumeConfirmRequest;
import com.huawei.openstack4j.openstack.message.queue.domain.ConsumeConfirmResponse;
import com.huawei.openstack4j.openstack.message.queue.domain.ConsumeRequest;
import com.huawei.openstack4j.openstack.message.queue.domain.ProduceRequest;
import com.huawei.openstack4j.openstack.message.queue.domain.Queue;
import com.huawei.openstack4j.openstack.message.queue.domain.QueueMessage;
import com.huawei.openstack4j.openstack.message.queue.domain.QueueMessageWithHandler;

public class MessageQueueAsyncService extends BaseMessageQueueServices implements RestService {
	
	private static final int DEFAULT_THREAD_POOL_SIZE = 100;
	private final ExecutorService executorService;
	private QueueMessageService queueMessageService = new QueueMessageService();

	public MessageQueueAsyncService()
    {
	 	ExecutorService executor = null ;
	 	Config config = OSClientSession.getCurrent().getConfig();
	 	if(config != null){
	 		executor = config.getExecutor();
	 	}
	 	this.executorService =
	             (null == executor) ? newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE) : executor;
    }

    /**
     * produce a message to a {@link Queue},
     * @param queueId
     * @param message
     * @param asyncHandler
     * @return
     */
	public Future<ActionResponse> produceAsync(String queueId,
                                               QueueMessage message, AsyncHandler<ActionResponse> asyncHandler) {
		final ProduceRequest request = ProduceRequest.builder().queueId(queueId).message(message).build();
		return submit(request, asyncHandler,
				new InnerExecutor<ProduceRequest, ActionResponse>() {
					@Override
					public ActionResponse innerExecute(ProduceRequest request) {
						return queueMessageService.produce(request.getQueueId(),request.getMessage());
					}
				});
	}

    /**
     * consume messages of a Queue
     * @param queueId
     * @param consumerGroupId
     * @param maxMessages
     * @param timeWait
     * @param asyncHandler
     * @return
     */
	public Future<List<QueueMessageWithHandler>> consumeAsync(String queueId, String consumerGroupId, Integer maxMessages,
                                                              Integer timeWait, AsyncHandler<List<QueueMessageWithHandler>> asyncHandler){
        final ConsumeRequest request = ConsumeRequest.builder().queueId(queueId).consumerGroupId(consumerGroupId)
                .maxMessages(maxMessages).timeWait(timeWait).build();
        return submit(request, asyncHandler,
                new InnerExecutor<ConsumeRequest, List<QueueMessageWithHandler>>() {
                    @Override
                    public List<QueueMessageWithHandler> innerExecute(ConsumeRequest request) {
                        return queueMessageService.consume(request.getQueueId(), request.getConsumerGroupId(), request.getMaxMessages(), request.getTimeWait());
                    }
                });
    }

    /**
     * used to confirm the consumption of specified messages.
     * @param queueId
     * @param consumerGroupId
     * @param consumeResult
     * @param asyncHandler
     * @return
     */
    public Future<ConsumeConfirmResponse>  confirmConsumingAsync(String queueId, String consumerGroupId, Map<String, ConsumeStatus> consumeResult
                                                        , AsyncHandler<ConsumeConfirmResponse> asyncHandler){

        final ConsumeConfirmRequest request = ConsumeConfirmRequest.builder().queueId(queueId).consumerGroupId(consumerGroupId).consumeResult(consumeResult).build();
        return submit(request, asyncHandler,
                new InnerExecutor<ConsumeConfirmRequest, ConsumeConfirmResponse>() {
                    @Override
                    public ConsumeConfirmResponse innerExecute(ConsumeConfirmRequest request) {
                        return queueMessageService.confirmConsuming(request.getQueueId(), request.getConsumerGroupId(), request.getConsumeResult());
                    }
                });
    }

    /**
     * Turn off the thread pool
     */
	public void closePool(){
		if(executorService != null){
			executorService.shutdown();
		} 
	}


    /**
     *
     * @param request
     * @param asyncHandler
     * @param innerExecutor
     * @param <REQUEST>
     * @param <RESULT>
     * @return
     */
	private <REQUEST, RESULT> Future<RESULT> submit(final REQUEST request ,final AsyncHandler<RESULT> asyncHandler,
            final InnerExecutor<REQUEST, RESULT> innerExecutor )
        {
    		@SuppressWarnings("rawtypes")
			final OSClientSession session = OSClientSession.getCurrent();
            return executorService.submit(new java.util.concurrent.Callable<RESULT>()
            {
                @Override
                public RESULT call()
                    throws Exception
                {
                	OSClientSession.setCurrent(session);
                    RESULT result = null;
                    
                    try
                    {
                        result = innerExecutor.innerExecute(request);
                    }
                    catch (Exception ex)
                    {
                        if (asyncHandler != null)
                        {
                            asyncHandler.onError(ex);
                        }
                        throw ex;
                    }
                    
                    if (asyncHandler != null)
                    {
                        asyncHandler.onSuccess(result);
                    }
                    return result;
                }
            });
        }

    /**
     *ConsumeRequest
     * @param <REQUEST>
     * @param <RESULT>
     */
    protected static interface InnerExecutor<REQUEST, RESULT>
    {
        /*
         * The interface that the asynchronous method needs to execute
         */
        RESULT innerExecute(REQUEST request);
    }

}
