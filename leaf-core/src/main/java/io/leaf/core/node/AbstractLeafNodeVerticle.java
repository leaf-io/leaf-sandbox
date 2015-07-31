package io.leaf.core.node;

import io.leaf.core.data.Definition;
import io.leaf.core.interfaceregistry.InterfaceEntry;
import io.leaf.core.manager.LeafManagerService;
import io.leaf.core.proxy.ProxyHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.VertxImpl;
import io.vertx.core.json.JsonObject;

/**
 * Created by Gabo on 2015.07.31..
 */
public abstract class AbstractLeafNodeVerticle<I extends Definition> extends AbstractVerticle implements LeafNodeService {

    public final static String MANAGEMENT_SUFFIX="management";

    protected LeafManagerService leafManager;

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

        leafManager = ProxyHelper.createProxy(LeafManagerService.class, vertx, LeafManagerService.CORE_TOPIC);

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
        if (serviceImpl != null) {
            System.out.println("Querying interface..");
            leafManager.getInterfaceEntry(serviceImpl.getKey(), (interfaceResult) -> {
                if (interfaceResult.succeeded()) {
                    JsonObject rawResult = interfaceResult.result();

                    InterfaceEntry ie = new InterfaceEntry();
                    ie.setJsonObject(rawResult);

                    System.out.println("Starting service");
                    ProxyHelper.registerService(serviceClass, vertx, serviceImpl, ie.getTopic());

                    resultHandler.handle(Future.succeededFuture(new JsonObject()));
                }
                else {
                    System.out.println("Unexpected error: "+interfaceResult.cause());
                    resultHandler.handle(Future.failedFuture(interfaceResult.cause()));
                }
            });


        }
    }
}
