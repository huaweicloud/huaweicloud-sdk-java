/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
/******************************************************************************* 	                                                                                 
 *  Huawei has modified this source file.
 * 	Copyright 2018 Huawei Technologies Co.,Ltd.                                         
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
 * *******************************************************************************/
package com.huawei.openstack4j.openstack.internal;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.EndpointTokenProvider;
import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.OSClient.OSClientV2;
import com.huawei.openstack4j.api.artifact.ArtifactService;
import com.huawei.openstack4j.api.barbican.BarbicanService;
import com.huawei.openstack4j.api.client.CloudProvider;
import com.huawei.openstack4j.api.cloudeye.CloudEyeService;
import com.huawei.openstack4j.api.compute.ComputeService;
import com.huawei.openstack4j.api.deh.DehService;
import com.huawei.openstack4j.api.dns.v2.DNSService;
import com.huawei.openstack4j.api.gbp.GbpService;
import com.huawei.openstack4j.api.heat.HeatService;
import com.huawei.openstack4j.api.identity.EndpointURLResolver;
import com.huawei.openstack4j.api.image.ImageService;
import com.huawei.openstack4j.api.loadbalance.ELBService;
import com.huawei.openstack4j.api.magnum.MagnumService;
import com.huawei.openstack4j.api.manila.ShareService;
import com.huawei.openstack4j.api.map.reduce.MapReduceService;
import com.huawei.openstack4j.api.murano.v1.AppCatalogService;
import com.huawei.openstack4j.api.nat.NatService;
import com.huawei.openstack4j.api.networking.NetworkingService;
import com.huawei.openstack4j.api.scaling.AutoScalingService;
import com.huawei.openstack4j.api.senlin.SenlinService;
import com.huawei.openstack4j.api.storage.BlockStorageService;
import com.huawei.openstack4j.api.storage.ObjectStorageService;
import com.huawei.openstack4j.api.tacker.TackerService;
import com.huawei.openstack4j.api.telemetry.TelemetryService;
import com.huawei.openstack4j.api.types.Facing;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.api.workflow.WorkflowService;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.identity.AuthVersion;
import com.huawei.openstack4j.model.identity.URLResolverParams;
import com.huawei.openstack4j.model.identity.v2.Access;
import com.huawei.openstack4j.model.identity.v3.Token;
import com.huawei.openstack4j.openstack.antiddos.internal.AntiDDoSServices;
import com.huawei.openstack4j.openstack.bms.v1.internal.BareMetaService;
import com.huawei.openstack4j.openstack.cdn.v1.internal.CdnServices;
import com.huawei.openstack4j.openstack.cloud.trace.v1.internal.CloudTraceV1Service;
import com.huawei.openstack4j.openstack.cloud.trace.v2.internal.CloudTraceV2Service;
import com.huawei.openstack4j.openstack.csbs.v1.internal.CloudServerBackupService;
import com.huawei.openstack4j.openstack.database.internal.DatabaseServices;
import com.huawei.openstack4j.openstack.dss.v1.internal.DssService;
import com.huawei.openstack4j.openstack.ecs.v1.internal.ElasticComputeService;
import com.huawei.openstack4j.openstack.evs.v2.internal.ElasticVolumeService;
import com.huawei.openstack4j.openstack.fgs.v1.internal.FunctionGraphService;
import com.huawei.openstack4j.openstack.identity.internal.DefaultEndpointURLResolver;
import com.huawei.openstack4j.openstack.kms.internal.KeyManagementService;
import com.huawei.openstack4j.openstack.maas.internal.MaaSService;
import com.huawei.openstack4j.openstack.message.notification.internal.NotificationService;
import com.huawei.openstack4j.openstack.message.queue.internal.MessageQueueAsyncService;
import com.huawei.openstack4j.openstack.message.queue.internal.MessageQueueService;
import com.huawei.openstack4j.openstack.tms.v1.internal.TagManagementService;
import com.huawei.openstack4j.openstack.trove.internal.TroveService;
import com.huawei.openstack4j.openstack.vpc.v2.internal.VirtualPrivateCloudService;

/**
 * A client which has been identified. Any calls spawned from this session will
 * automatically utilize the original authentication that was successfully
 * validated and authorized
 *
 * @author Jeremy Unruh
 */
public abstract class OSClientSession<R, T extends OSClient<T>> implements EndpointTokenProvider {

	private static final Logger LOG = LoggerFactory.getLogger(OSClientSession.class);
	@SuppressWarnings("rawtypes")
	protected static final ThreadLocal<OSClientSession> sessions = new ThreadLocal<OSClientSession>();

	protected Config config;
	public Facing perspective;
	protected String region;
	public Token token;
	public String reqId;
	protected Set<ServiceType> supports;
	protected CloudProvider provider;
	protected Map<String, ? extends Object> headers;
	protected EndpointURLResolver fallbackEndpointUrlResolver = new DefaultEndpointURLResolver();
	
	public static OSClientSession getCurrent() {
		return sessions.get();
	}

	public static void setCurrent(OSClientSession session) {
		sessions.set(session);
	}

	public Token getToken(){
		return token;
	}
	
	public void setToken(Token token){
		this.token=token;
	}

	@SuppressWarnings("unchecked")
	@VisibleForTesting
	public R useConfig(Config config) {
		this.config = config;
		return (R) this;
	}
	
	public boolean supportsReAuthentication() {
		return true;
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T useRegion(String region) {
		this.region = region;
		return (T) this;
	}

	/**
	 * {@inheritDoc}
	 */
	public T removeRegion() {
		return useRegion(null);
	}

	/**
	 * @return the current perspective
	 */
	public Facing getPerspective() {
		return perspective;
	}

	/**
	 * @return the original client configuration associated with this session
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * {@inheritDoc}
	 */
	public ComputeService compute() {
		return Apis.getComputeServices();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public NetworkingService networking() {
		return Apis.getNetworkingServices();
	}
	
	/*
	 * {@inheritDoc}
	 */
	public AutoScalingService autoScaling() {
		return Apis.get(AutoScalingService.class);
	}
	/*
	 * {@inheritDoc}
	 */
	public ELBService loadBalancer() {
		return Apis.get(ELBService.class);
	}

	/*
	 * {@inheritDoc}
	 */	
	public CloudTraceV1Service cloudTraceV1() {
		return Apis.get(CloudTraceV1Service.class);
	}

	/*
	 * {@inheritDoc}
	 */
	public CloudTraceV2Service cloudTraceV2() {
		return Apis.get(CloudTraceV2Service.class);
	}
	
	public KeyManagementService keyManagement() {
		return Apis.get(KeyManagementService.class);
	}

	/*
	 * {@inheritDoc}
	 */

	public AntiDDoSServices antiDDoS() {
		return Apis.get(AntiDDoSServices.class);
	}

	/*
	 * {@inheritDoc}
	 */
	public NotificationService notification() {
		return Apis.get(NotificationService.class);
	}

	/*
	 * {@inheritDoc}
	 */
	public MessageQueueService messageQueue() {
		return Apis.get(MessageQueueService.class);
	}

	/*
	 * {@inheritDoc}
	 */
	public MaaSService maas() {
		return Apis.get(MaaSService.class);
	}

	/*
	 * {@inheritDoc}
	 */
	public DatabaseServices database() {
		return Apis.get(DatabaseServices.class);
	}
	/**
	 * {@inheritDoc}
	 */
	public ArtifactService artifact() {
		return Apis.getArtifactServices();
	}

	/**
	 * {@inheritDoc}
	 */
	public TackerService tacker() {
		return Apis.getTackerServices();
	}

	/**
	 * {@inheritDoc}
	 */
	public ImageService images() {
		return Apis.getImageService();
	}

	public com.huawei.openstack4j.api.image.v2.ImageService imagesV2() {
		return Apis.getImageV2Service();
	}

	/**
	 * {@inheritDoc}
	 */
	public BlockStorageService blockStorage() {
		return Apis.get(BlockStorageService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TelemetryService telemetry() {
		return Apis.get(TelemetryService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public ShareService share() {
		return Apis.get(ShareService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public HeatService heat() {
		return Apis.getHeatServices();
	}

	/**
	 * {@inheritDoc}
	 */
	public AppCatalogService murano() {
		return Apis.getMuranoServices();
	}

	/**
	 * {@inheritDoc}
	 */
	public MagnumService magnum() {
		return Apis.getMagnumService();
	}

	/**
	 * {@inheritDoc}
	 */
	public SenlinService senlin() {
		return Apis.getSenlinServices();
	}

    /**
     * {@inheritDoc}
     */
    public FunctionGraphService functionGraph() {
        return Apis.getFunctionGraphServices();
    }

    /**
     * {@inheritDoc}
     */
    public ObjectStorageService objectStorage() {
        return Apis.get(ObjectStorageService.class);
    }

	/**
	 * {@inheritDoc}
	 */
	public MapReduceService mrs() {
		return Apis.getMapReduceServices();
	}

	/**
	 * {@inheritDoc}
	 */
	public WorkflowService workflow() {
		return Apis.getWorkflowServices();
	}

	/**
	 * {@inheritDoc}
	 */
	public BarbicanService barbican() {
		return Apis.getBarbicanServices();
	}

	/**
	 * {@inheritDoc}
	 */
	public DNSService dns() {
		return Apis.getDNSService();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public CdnServices cdn() {
		return Apis.get(CdnServices.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public CloudEyeService cloudEye() {
		return Apis.getCloudEyeService();
	}

	/**
	 * {@inheritDoc}
	 */
	public MessageQueueAsyncService messageQueueAsync() {
		return Apis.get(MessageQueueAsyncService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T perspective(Facing perspective) {
		this.perspective = perspective;
		return (T) this;
	}

	public CloudProvider getProvider() {
		return (provider == null) ? CloudProvider.UNKNOWN : provider;
	}

	/**
	 * {@inheritDoc}
	 */
	public T headers(Map<String, ? extends Object> headers) {
		this.headers = headers;
		return (T) this;
	}

	public Map<String, ? extends Object> getHeaders() {
		return this.headers;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsCompute() {
		return getSupportedServices().contains(ServiceType.COMPUTE);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsIdentity() {
		return getSupportedServices().contains(ServiceType.IDENTITY);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsNetwork() {
		return getSupportedServices().contains(ServiceType.NETWORK);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsImage() {
		return getSupportedServices().contains(ServiceType.IMAGE);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsHeat() {
		return getSupportedServices().contains(ServiceType.ORCHESTRATION);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsMurano() {
		return getSupportedServices().contains(ServiceType.APP_CATALOG);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsBlockStorage() {
		return getSupportedServices().contains(ServiceType.BLOCK_STORAGE);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsObjectStorage() {
		return getSupportedServices().contains(ServiceType.OBJECT_STORAGE);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsTelemetry() {
		return getSupportedServices().contains(ServiceType.TELEMETRY);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsTelemetry_aodh() {
		return getSupportedServices().contains(ServiceType.TELEMETRY_AODH);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsShare() {
		return getSupportedServices().contains(ServiceType.SHARE);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsTrove() {
		return getSupportedServices().contains(ServiceType.DATABASE);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean supportsDNS() {
		return getSupportedServices().contains(ServiceType.DNS);
	}

	public Set<ServiceType> getSupportedServices() {
		return null;
	}

	public AuthVersion getAuthVersion() {
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public NatService nat() {
		return Apis.getNatServices();
	}

	/**
	 * {@inheritDoc}
	 */
	public DehService deh() {
		return Apis.getDehService();
	}
	/**
	 * {@inheritDoc}
	 */
	public GbpService gbp() {
		return Apis.getGbpServices();
	}

	/**
	 *
	 * @return
	 */
	public TroveService trove() {
		return Apis.getTroveServices();
	}
	
	/**
	 *
	 * @return
	 */
	 public ElasticComputeService ecs() {
	 	return Apis.getElasticComputeService();
	 }
	 
	 /**
	 *
	 * @return
	 */
	 public com.huawei.openstack4j.openstack.ecs.v1_1.internal.ElasticComputeService ecsV1_1() {
	 	return Apis.get(com.huawei.openstack4j.openstack.ecs.v1_1.internal.ElasticComputeService.class);
	 }
	 
	 /**
	  * 
	  * @return
	  */
	 public TagManagementService tms(){
		 return Apis.get(TagManagementService.class);
	 }
	 
	 /**
	  * 
	  * @return
	  */
	 public BareMetaService bms(){
		 return Apis.get(BareMetaService.class);
	 }
	 
	 
	 /**
	 *
	 * @return
	 */
	 public ElasticVolumeService evs() {
		return Apis.get(ElasticVolumeService.class);
	}

	/**
	 *
	 * @return
	 */
	 public DssService dss()
	{
		return Apis.get(DssService.class);
	}

	 /**
	 *
	 * @return
	 */
	 public com.huawei.openstack4j.openstack.evs.v2_1.internal.ElasticVolumeService evsV2_1() {
		return Apis.get(com.huawei.openstack4j.openstack.evs.v2_1.internal.ElasticVolumeService.class);
	}
	 /**
	  * 
	  * @return
	  */
	 public VirtualPrivateCloudService vpcV2(){
		 return Apis.get(VirtualPrivateCloudService.class);
	 };
	 
	 /**
	  * Returns the VirtualPrivateCloud Service API
	  * @return the VirtualPrivateCloudService
	  */
	 public com.huawei.openstack4j.openstack.vpc.v1.internal.VirtualPrivateCloudService vpc(){
		 return Apis.get(com.huawei.openstack4j.openstack.vpc.v1.internal.VirtualPrivateCloudService.class);
	 };

	 /**
	  * Returns the ImageManagement Service API
	  * @return the ImageManagementService
	  */
	public com.huawei.openstack4j.openstack.ims.v2.internal.ImageManagementService imsV2(){
		return Apis.get(com.huawei.openstack4j.openstack.ims.v2.internal.ImageManagementService.class);
	};
	/**
	 * Returns the ImageManagement Service API
	 * @return the ImageManagementService
	 */
	public com.huawei.openstack4j.openstack.ims.v1.internal.ImageManagementService ims(){
		return Apis.get(com.huawei.openstack4j.openstack.ims.v1.internal.ImageManagementService.class);
	};
	/**
	 * Returns the CloudServerBackup Service API
	 * @return
	 */
	public CloudServerBackupService csbs(){
		return Apis.get(CloudServerBackupService.class);
	}
	public static class OSClientSessionV2 extends OSClientSession<OSClientSessionV2, OSClientV2> implements OSClientV2 {

		Access access;

		private OSClientSessionV2(Access access, String endpoint, Facing perspective, CloudProvider provider,
				Config config) {
			this.access = access;
			this.config = config;
			this.perspective = perspective;
			this.provider = provider;
			sessions.set(this);
		}

		private OSClientSessionV2(Access access, OSClientSessionV2 parent, String region) {
			this.access = parent.access;
			this.perspective = parent.perspective;
			this.region = region;
		}

		public static OSClientSessionV2 createSession(Access access) {
			return new OSClientSessionV2(access, access.getEndpoint(), null, null, null);
		}

		public static OSClientSessionV2 createSession(Access access, Facing perspective, CloudProvider provider,
				Config config) {
			return new OSClientSessionV2(access, access.getEndpoint(), perspective, provider, config);
		}

		@Override
		public Access getAccess() {
			return access;
		}

		@Override
		public String getEndpoint() {
			return access.getEndpoint();
		}

		@Override
		public AuthVersion getAuthVersion() {
			return AuthVersion.V2;
		}

		private String addNATIfApplicable(String url) {
			if (config != null && config.isBehindNAT()) {
				try {
					URI uri = new URI(url);
					return url.replace(uri.getHost(), config.getEndpointNATResolution());
				} catch (URISyntaxException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			return url;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getEndpoint(ServiceType service) {
			final EndpointURLResolver eUrlResolver = (config != null && config.getEndpointURLResolver() != null)
					? config.getEndpointURLResolver() : fallbackEndpointUrlResolver;
			return addNATIfApplicable(eUrlResolver.findURLV2(URLResolverParams.create(access, service)
					.resolver(config != null ? config.getV2Resolver() : null).perspective(perspective).region(region)));
		}

		@Override
		public String getTokenId() {
			return access.getToken().getId();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public com.huawei.openstack4j.api.identity.v2.IdentityService identity() {
			return Apis.getIdentityV2Services();
		}

		@Override
		public Set<ServiceType> getSupportedServices() {
			if (supports == null)
				supports = Sets.immutableEnumSet(Iterables.transform(access.getServiceCatalog(),
						new com.huawei.openstack4j.openstack.identity.v2.functions.ServiceToServiceType()));
			return supports;
		}

	}
}
