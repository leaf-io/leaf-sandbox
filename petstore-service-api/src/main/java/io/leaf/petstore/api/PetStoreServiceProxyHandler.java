package io.leaf.petstore.api;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHandler;

/**
 * Created by Gabo on 2015.07.23..
 */
public class PetStoreServiceProxyHandler extends ProxyHandler {

    private final Vertx vertx;
    private final PetStoreService service;

    public PetStoreServiceProxyHandler(Vertx vertx, PetStoreService service) {
        this.vertx = vertx;
        this.service = service;
    }

    public MessageConsumer<JsonObject> registerHandler(String address) {
        MessageConsumer<JsonObject> consumer = vertx.eventBus().<JsonObject>consumer(address).handler(this);
        this.setConsumer(consumer);
        return consumer;
    }

    public void handle(Message<JsonObject> msg) {
        JsonObject json = msg.body();
        String action = msg.headers().get("action");
        if (action == null) {
            throw new IllegalStateException("action not specified");
        }
        JsonObject document = (io.vertx.core.json.JsonObject)json.getValue("document");
        switch (action) {
            case "addPet": {
                Pet pet = new Pet();
                pet.setJsonObject(document);
                service.addPet(pet, createHandler(msg));
                break;
            }
            default: {
                throw new IllegalStateException("Invalid action: " + action);
            }
        }
    }

    private <T> Handler<AsyncResult<T>> createHandler(Message msg) {
        return res -> {
            if (res.failed()) {
                msg.fail(-1, res.cause().getMessage());
            } else {
                msg.reply(res.result());
            }
        };
    }
}
