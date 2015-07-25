package io.leaf.service;

import io.vertx.core.*;

/**
 * Date: 2015.07.23.
 * Time: 21:33
 *
 * @author Andras Toth
 */
public class ServiceManager {

    private Vertx vertx;

    public Vertx getVertx() {
        return vertx;
    }

    public void startService(Class<? extends Service> serviceType, Handler<AsyncResult<String>> handler) {
        startService(serviceType, handler, false);
    }

    public void startService(Class<? extends Service> serviceType, Handler<AsyncResult<String>> handler, boolean clustered) {
        startService(serviceType, handler, false, null);
    }

    public void startService(Class<? extends Service> serviceType, Handler<AsyncResult<String>> handler, boolean clustered, DeploymentOptions deploymentOptions) {
        if (vertx == null) {
            Vertx.clusteredVertx(
                    new VertxOptions().setClustered(clustered),
                    vertxAsyncResult -> {
                        this.vertx = vertxAsyncResult.result();
                        deployVerticle(serviceType, handler);
                    }
            );
        }
        else {
            deployVerticle(serviceType, handler);
        }
    }

    protected void deployVerticle(Class<? extends Service> serviceType, Handler<AsyncResult<String>> handler) {
        try {
            vertx.deployVerticle(serviceType.newInstance(), handler);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
