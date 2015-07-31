package io.leaf.petstore.test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import io.leaf.core.LeafCoreVerticle;
import io.leaf.core.node.LeafNodeManager;
import io.leaf.petstore.api.Pet;
import io.leaf.petstore.api.PetStoreService;
import io.leaf.petstore.api.ProxyHelper;
import io.leaf.petstore.client.PetStoreServiceClientVerticle;
import io.leaf.petstore.impl.PetStoreServiceVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import org.hamcrest.core.Is;
import org.junit.After;
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

    private LeafNodeManager serviceManager;

    @Before
    public void init() {
        serviceManager = new LeafNodeManager();
    }

    @After
    public void destroy() {
        serviceManager.close();
    }

    @Test
    public void basicTest() {
        final AtomicBoolean loaded = new AtomicBoolean(false);

        serviceManager.startService(LeafCoreVerticle.class, new Handler<AsyncResult<String>>() {
            public void handle(AsyncResult<String> stringAsyncResult) {
                    serviceManager.startService(PetStoreServiceVerticle.class, new Handler<AsyncResult<String>>() {
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

    @Test
    public void addTest() {
        final AtomicBoolean finished = new AtomicBoolean(false);

        serviceManager.startService(LeafCoreVerticle.class, new Handler<AsyncResult<String>>() {
            public void handle(AsyncResult<String> stringAsyncResult) {
                serviceManager.startService(PetStoreServiceVerticle.class, new Handler<AsyncResult<String>>() {
                    public void handle(AsyncResult<String> stringAsyncResult) {
                        try {
                            Thread.sleep(1500);
                        }
                        catch(Exception ex) {

                        }


                        PetStoreService service = ProxyHelper.createProxy(PetStoreService.class, serviceManager.getVertx(), "io.leaf:petstore-service-api:1.0.0");

                        service.getPetCount((count0Result) -> {
                            long pet0Count = count0Result.result();
                            Assert.assertEquals(0, pet0Count);

                            Pet myPet = new Pet();
                            myPet.setName("Bradley");
                            service.addPet(myPet, (addPetResult) -> {
                                if (addPetResult.succeeded()) {
                                    service.getPetCount((count1Result) -> {
                                        long pet1Count = count1Result.result();
                                        Assert.assertEquals(1, pet1Count);

                                        finished.set(true);
                                    });
                                }
                            });


                        });
                    }
                }, true);
            }
        }, true);

        Awaitility.waitAtMost(Duration.ONE_MINUTE).await().untilTrue(finished);



        Assert.assertThat(finished.get(), Is.is(true));
    }

}
