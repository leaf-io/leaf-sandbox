package io.leaf.core;

import io.leaf.core.manager.LeafManagerService;
import io.leaf.core.manager.LeafManagerServiceImpl;
import io.leaf.core.node.AbstractLeafNodeVerticle;
import io.leaf.core.node.LeafVerticleManager;
import io.leaf.core.proxy.ProxyHelper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Created by rendesg on 2015.07.31..
 */
public class LeafCoreVerticle  extends AbstractLeafNodeVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        LeafVerticleManager leafVerticleManager = new LeafVerticleManager();
        leafVerticleManager.startService(LeafCoreVerticle.class, new Handler<AsyncResult<String>>() {
            public void handle(AsyncResult<String> startResult) {
                if (startResult.succeeded()) {
                    System.out.println("*********************");
                    System.out.println("* Leaf CORE started *");
                    System.out.println("*********************");
                }
                else {
                    System.out.println("Some serious fuckup happened :(");
                }
            }
        });
    }

    @Override
    public Class getServiceClass() {
        return null;
    }

    @Override
    public Definition getServiceImpl() {
        return null;
    }

    @Override
    public void start() throws Exception {
        // Create the server implementation
        LeafManagerServiceImpl service = new LeafManagerServiceImpl(vertx);

        // Register the handler
        ProxyHelper.registerService(LeafManagerService.class, vertx, service, LeafManagerService.CORE_TOPIC);
    }
}
