package io.leaf.petstore.api;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonObject;

/**
 * Created by Gabo on 2015.07.23..
 */
public class PetStoreServiceProxy implements PetStoreService {

    private Vertx _vertx;
    private String _address;

    public PetStoreServiceProxy(Vertx vertx, String address) {
        this._vertx = vertx;
        this._address = address;
    }

    public void addPet(Pet pet, Handler<AsyncResult<JsonObject>> resultHandler) {

        JsonObject _json = new JsonObject();
        _json.put("document", pet.getJsonObject());
        DeliveryOptions _deliveryOptions = new DeliveryOptions();
        _deliveryOptions.addHeader("action", "addPet");
        _vertx.eventBus().<JsonObject>send(_address, _json, _deliveryOptions, res -> {
            if (res.failed()) {
                resultHandler.handle(Future.failedFuture(res.cause()));
            } else {
                resultHandler.handle(Future.succeededFuture(res.result().body()));
            }
        });
    }
}
