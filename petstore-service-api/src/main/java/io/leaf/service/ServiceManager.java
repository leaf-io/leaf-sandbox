package io.leaf.service;

import io.vertx.core.*;

/**
 * Date: 2015.07.23.
 * Time: 21:33
 *
 * @author Andras Toth
 */
public class ServiceManager {

    public void startService(Class<? extends Service> serviceType, Handler<AsyncResult<String>> handler) {
        startService(serviceType, handler, false);
    }

    public void startService(Class<? extends Service> serviceType, Handler<AsyncResult<String>> handler, boolean clustered) {
        startService(serviceType, handler, false, null);
    }

    public void startService(Class<? extends Service> serviceType, Handler<AsyncResult<String>> handler, boolean clustered, DeploymentOptions deploymentOptions) {

            Vertx.clusteredVertx(
                    new VertxOptions().setClustered(clustered),
                    vertxAsyncResult -> {
                        try {
                            vertxAsyncResult.result().deployVerticle(serviceType.newInstance(), handler);
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
            );

    }

}
