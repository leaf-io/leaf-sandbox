package io.leaf.petstore.test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import io.leaf.petstore.api.PetStoreServiceProxy;
import io.leaf.petstore.api.ProxyHelper;
import io.leaf.petstore.client.PetStoreServiceClientVerticle;
import io.leaf.petstore.impl.PetStoreServiceVerticle;
import io.leaf.service.ServiceInitializationException;
import io.leaf.service.ServiceManager;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import org.hamcrest.Matcher;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Date: 2015.07.23.
 * Time: 21:48
 *
 * @author Andras Toth
 */
public class PetstoreProxyTest {

    private ServiceManager serviceManager;

    @Before
    public void init() {
        serviceManager = new ServiceManager();
    }

    @Test
    public void proxyTest() {
        final AtomicBoolean loaded = new AtomicBoolean(false);

        serviceManager.startService(PetStoreServiceVerticle.class, new Handler<AsyncResult<String>>() {
            public void handle(AsyncResult<String> stringAsyncResult) {
                    serviceManager.startService(PetStoreServiceClientVerticle.class, new Handler<AsyncResult<String>>() {
                        public void handle(AsyncResult<String> stringAsyncResult) {
                            //  TODO test call then set to true
                            loaded.set(true);
                        }
                    }, true);
            }
        }, true);

        Awaitility.waitAtMost(Duration.ONE_MINUTE).await().untilTrue(loaded);

        Assert.assertThat(loaded.get(), Is.is(true));
    }

}
