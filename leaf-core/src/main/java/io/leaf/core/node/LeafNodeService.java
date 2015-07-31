package io.leaf.core.node;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * Created by Gabo on 2015.07.31..
 */
public interface LeafNodeService {

    default String getGroupId() {
        return "io.leaf";
    }

    default String getArtifactId() {
        return "leafnode-service-api";
    }

    default String getVersion() {
        return "1.0.0";
    }

    void startNode(Handler<AsyncResult<JsonObject>> resultHandler);
}
