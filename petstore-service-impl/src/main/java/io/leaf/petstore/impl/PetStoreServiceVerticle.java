package io.leaf.petstore.impl;

import io.leaf.core.manager.LeafManagerService;
import io.leaf.petstore.api.PetStoreService;
import io.leaf.petstore.api.ProxyHelper;
import io.leaf.service.Service;
import io.leaf.service.ServiceManager;
import io.vertx.core.*;

/**
 * Created by Gabo on 2015.07.23..
 */
public class PetStoreServiceVerticle  extends Service {

    PetStoreService service;

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        ServiceManager serviceManager = new ServiceManager();
        serviceManager.startService(PetStoreServiceVerticle.class, new Handler<AsyncResult<String>>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {

            }
        }, true);
    }

    @Override
    public void start() throws Exception {
        // Create the client object
        service = new PetStoreServiceImpl(vertx);
        // Register the handler
        ProxyHelper.registerService(PetStoreService.class, vertx, service, "io.leaf.petstore");

        System.out.println("PetStoreService implementation node started..");

        String managementAddress = "io.leaf.petstore.management";
        LeafManagerService leafManager = ProxyHelper.createProxy(LeafManagerService.class, vertx, LeafManagerService.CORE_TOPIC);

        leafManager.registerNode(managementAddress, (registerRes) -> {
            System.out.println("Registration success!");
        });
    }
}
