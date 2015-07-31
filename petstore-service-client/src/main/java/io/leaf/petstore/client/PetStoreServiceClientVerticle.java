package io.leaf.petstore.client;

import io.leaf.core.Definition;
import io.leaf.core.node.AbstractLeafNodeVerticle;
import io.leaf.core.node.LeafNodeManager;
import io.leaf.petstore.api.Pet;
import io.leaf.petstore.api.PetStoreService;
import io.leaf.petstore.api.ProxyHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Created by Gabo on 2015.07.23..
 */
public class PetStoreServiceClientVerticle extends AbstractLeafNodeVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        LeafNodeManager serviceManager = new LeafNodeManager();
        serviceManager.startService(PetStoreServiceClientVerticle.class, new Handler<AsyncResult<String>>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {

            }
        }, true);
    }

    private PetStoreService service;

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
        service = ProxyHelper.createProxy(PetStoreService.class, vertx, "io.leaf.petstore");
    }

    public void addPet() {
        Pet pet = new Pet();
        pet.setName("Fluffy");

        service.addPet(pet, (r) -> {
            if (r.succeeded()) {
                System.out.println(r.result().encodePrettily());
            } else {
                System.out.println(r.cause());
            }
        });
    }
}
