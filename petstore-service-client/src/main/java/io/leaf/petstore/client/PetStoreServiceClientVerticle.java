package io.leaf.petstore.client;

import io.leaf.petstore.api.Pet;
import io.leaf.petstore.api.PetStoreService;
import io.leaf.petstore.api.ProxyHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.example.util.ExampleRunner;

/**
 * Created by Gabo on 2015.07.23..
 */
public class PetStoreServiceClientVerticle extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        ExampleRunner.runJavaExample("io.leaf.petstore.client", PetStoreServiceClientVerticle.class, true);
    }

    @Override
    public void start() throws Exception {
        PetStoreService service = ProxyHelper.createProxy(PetStoreService.class, vertx, "io.leaf.petstore");

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
