package io.leaf.petstore.client;

import io.leaf.core.data.Definition;
import io.leaf.core.node.AbstractLeafNodeVerticle;
import io.leaf.core.node.LeafVerticleManager;
import io.leaf.core.proxy.ProxyHelper;
import io.leaf.petstore.api.Pet;
import io.leaf.petstore.api.PetStoreService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Created by Gabo on 2015.07.23..
 */
public class PetStoreServiceClientVerticle extends AbstractLeafNodeVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        LeafVerticleManager leafVerticleManager = new LeafVerticleManager();
        leafVerticleManager.startService(PetStoreServiceClientVerticle.class, new Handler<AsyncResult<String>>() {
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
        service = ProxyHelper.createProxy(PetStoreService.class, vertx, "io.leaf:petstore-service-api:1.0.0");
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
