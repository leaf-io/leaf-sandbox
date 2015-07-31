package io.leaf.petstore.api;

import io.leaf.core.data.Definition;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * Created by Gabo on 2015.07.23..
 */
public interface PetStoreService extends Definition {

    default String getGroupId() {
        return "io.leaf";
    }

    default String getArtifactId() {
        return "petstore-service-api";
    }

    default String getVersion() {
        return "1.0.0";
    }

    void addPet(Pet pet, Handler<AsyncResult<JsonObject>> resultHandler);

    void getPetCount(Handler<AsyncResult<Integer>> resultHandler);
}
