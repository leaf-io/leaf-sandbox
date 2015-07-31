package io.leaf.core.node;

import io.leaf.core.proxy.ProxyHelper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonObject;

/**
 * Created by Gabo on 2015.07.23..
 */
public class LeafNodeServiceProxy implements LeafNodeService {

    private Vertx _vertx;
    private String _address;

    public LeafNodeServiceProxy(Vertx vertx, String address) {
        this._vertx = vertx;
        this._address = address;
    }


    @Override
    public void startNode(Handler<AsyncResult<JsonObject>> resultHandler) {
        JsonObject _json = new JsonObject();
        JsonObject document = new JsonObject();
        _json.put(ProxyHelper.DOCUMENT, document);
        DeliveryOptions _deliveryOptions = new DeliveryOptions();
        _deliveryOptions.addHeader(ProxyHelper.COMMAND, "startNode");
        _vertx.eventBus().<JsonObject>send(_address, _json, _deliveryOptions, res -> {
            if (res.failed()) {
                resultHandler.handle(Future.<JsonObject>failedFuture(res.cause()));
            } else {
                resultHandler.handle(Future.succeededFuture(res.result().body()));
            }
        });
    }
}
