package io.leaf.core.node;

import io.leaf.core.Definition;
import io.leaf.core.interfaceregistry.InterfaceEntry;
import io.leaf.core.manager.LeafManagerService;
import io.leaf.core.proxy.ProxyHelper;
import io.vertx.core.AbstractVerticle;

/**
 * Created by Gabo on 2015.07.31..
 */
public abstract class AbstractLeafNodeVerticle<I extends Definition> extends AbstractVerticle{

    protected Class<I> serviceClass;
    protected I serviceImpl;

    protected String managementTopic;

    public AbstractLeafNodeVerticle() {
        this.serviceClass = getServiceClass();
        this.serviceImpl = getServiceImpl();

        if (serviceImpl != null) {
            this.managementTopic = serviceImpl.getKey() + ":management";
        }
    }

    public abstract Class<I> getServiceClass();

    public abstract I getServiceImpl();

    @Override
    public void start() throws Exception {
        System.out.println("Leaf Node started..");

        LeafManagerService leafManager = ProxyHelper.createProxy(LeafManagerService.class, vertx, LeafManagerService.CORE_TOPIC);

        if (serviceImpl != null) {
            ProxyHelper.registerService(serviceClass, vertx, serviceImpl, serviceImpl.getKey());

            leafManager.registerNode(managementTopic, (registerRes) -> {
                if (registerRes.succeeded()) {
                    System.out.println("Registration success!");
                } else {
                    System.out.println("Registration failed!");
                }
            });
        }
    }
}
