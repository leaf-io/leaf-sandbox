package io.leaf.core.node;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import io.leaf.core.Definition;
import io.leaf.core.interfaceregistry.InterfaceEntry;
import io.leaf.core.manager.LeafManagerService;
import io.leaf.core.proxy.ProxyHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.impl.VertxImpl;
import io.vertx.core.json.JsonObject;

import java.util.Set;
import java.util.UUID;

/**
 * Created by Gabo on 2015.07.31..
 */
public abstract class AbstractLeafNodeVerticle<I extends Definition> extends AbstractVerticle implements LeafNodeService {

    public final static String MANAGEMENT_SUFFIX="management";

    protected Class<I> serviceClass;
    protected I serviceImpl;

    protected String nodeId;
    protected String managementTopic;

    public AbstractLeafNodeVerticle() {
        this.serviceClass = getServiceClass();
        this.serviceImpl = getServiceImpl();
    }

    public abstract Class<I> getServiceClass();

    public abstract I getServiceImpl();

    @Override
    public void start() throws Exception {
        System.out.println("Leaf Node started..");

        nodeId = ((VertxImpl)vertx).getClusterManager().getNodeID();

        if (serviceImpl != null) {
            this.managementTopic = serviceImpl.getKey() + ":"+ nodeId+":"+MANAGEMENT_SUFFIX;
        }

        ProxyHelper.registerService(LeafNodeService.class, vertx, this, managementTopic);

        LeafManagerService leafManager = ProxyHelper.createProxy(LeafManagerService.class, vertx, LeafManagerService.CORE_TOPIC);

        leafManager.registerNode(managementTopic, (registerRes) -> {
            if (registerRes.succeeded()) {
                System.out.println("Node registration success!");
            } else {
                System.out.println("Node registration failed!");
            }
        });
    }

    @Override
    public void startNode(Handler<AsyncResult<JsonObject>> resultHandler) {
        System.out.println("Start node");
        if (serviceImpl != null) {
            System.out.println("Staring service");
            ProxyHelper.registerService(serviceClass, vertx, serviceImpl, serviceImpl.getKey());
        }
    }
}
