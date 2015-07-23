package io.leaf.petstore.api;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * Created by Gabo on 2015.07.23..
 */
public interface PetStoreService {

    void addPet(Pet pet, Handler<AsyncResult<JsonObject>> resultHandler);
}
