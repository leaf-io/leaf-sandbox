package io.leaf.petstore.impl;

import io.leaf.petstore.api.PetStoreService;
import io.leaf.petstore.api.ProxyHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.example.util.ExampleRunner;

/**
 * Created by Gabo on 2015.07.23..
 */
public class PetStoreServiceVerticle  extends AbstractVerticle {

    PetStoreService service;

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        ExampleRunner.runJavaExample("io.leaf.petstore.impl", PetStoreServiceVerticle.class, true);
    }

    @Override
    public void start() throws Exception {
        // Create the client object
        service = new PetStoreServiceImpl(vertx);
        // Register the handler
        ProxyHelper.registerService(PetStoreService.class, vertx, service, "io.leaf.petstore");

        System.out.println("PetStoreService implementation node started..");
    }
}
