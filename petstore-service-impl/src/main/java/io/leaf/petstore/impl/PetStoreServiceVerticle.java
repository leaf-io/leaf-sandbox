package io.leaf.petstore.impl;

import io.leaf.core.node.AbstractLeafNodeVerticle;
import io.leaf.core.node.LeafVerticleManager;
import io.leaf.petstore.api.PetStoreService;
import io.vertx.core.*;

/**
 * Created by Gabo on 2015.07.23..
 */
public class PetStoreServiceVerticle extends AbstractLeafNodeVerticle<PetStoreService> {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        LeafVerticleManager serviceManager = new LeafVerticleManager();
        serviceManager.startService(PetStoreServiceVerticle.class, new Handler<AsyncResult<String>>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {

            }
        }, true);
    }

    @Override
    public Class<PetStoreService> getServiceClass() {
        return PetStoreService.class;
    }

    @Override
    public PetStoreService getServiceImpl() {
        return new PetStoreServiceImpl(vertx);
    }

}
