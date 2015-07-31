package io.leaf.core.manager;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * Created by Gabo on 2015.07.31..
 */
public interface LeafManagerService {

    public static final String CORE_TOPIC = "io.leaf.core";

    public void registerNode(String managementTopic, Handler<AsyncResult<JsonObject>> resultHandler);

}
