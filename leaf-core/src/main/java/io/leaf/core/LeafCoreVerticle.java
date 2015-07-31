package io.leaf.core;

import io.leaf.core.manager.LeafManagerService;
import io.leaf.core.manager.LeafManagerServiceImpl;
import io.leaf.core.node.AbstractLeafNodeVerticle;
import io.leaf.core.proxy.ProxyHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.example.util.ExampleRunner;

/**
 * Created by rendesg on 2015.07.31..
 */
public class LeafCoreVerticle  extends AbstractLeafNodeVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        ExampleRunner.runJavaExample("io.leaf.core", LeafCoreVerticle.class, true);
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

        System.out.println("*********************");
        System.out.println("* Leaf CORE started *");
        System.out.println("*********************");
    }
}
