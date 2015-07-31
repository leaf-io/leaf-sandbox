package io.leaf.core.manager;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MembershipAdapter;
import com.hazelcast.core.MembershipEvent;
import io.leaf.core.interfaceregistry.InterfaceEntry;
import io.leaf.core.noderegistry.NodeEntry;
import io.leaf.core.noderegistry.NodeStatus;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.*;

/**
 * Created by Gabo on 2015.07.31..
 */
public class LeafManagerServiceImpl implements LeafManagerService {

    protected Map<String, InterfaceEntry> interfaceRegistry = new HashMap<String, InterfaceEntry>();
    protected List<NodeEntry> nodeRegistry = new ArrayList<NodeEntry>();

    public LeafManagerServiceImpl() {
        setupHazelCast();

        InterfaceEntry ie = new InterfaceEntry();
        ie.setGroupId("io.leaf");
        ie.setArtifactId("petstore-service-api");
        ie.setVersion("1.0.0");
        interfaceRegistry.put(ie.getKey(), ie);
    }

    private void setupHazelCast() {
        Set<HazelcastInstance> instances = Hazelcast.getAllHazelcastInstances();
        HazelcastInstance hz = instances.stream().findFirst().get();

        hz.getCluster().addMembershipListener(new MembershipAdapter() {

            @Override
            public void memberAdded(MembershipEvent membershipEvent) {
                System.out.println("added");
            }

            @Override
            public void memberRemoved(MembershipEvent membershipEvent) {
                System.out.println("removed");
            }
        });
    }

    @Override
    public void registerNode(String managementTopic, Handler<AsyncResult<JsonObject>> resultHandler) {
        System.out.println("Node registration start");
        NodeEntry ne = new NodeEntry();

        ne.setManagementTopic(managementTopic);
        ne.setStatus(NodeStatus.INACTIVE);

        nodeRegistry.add(ne);

        resultHandler.handle(Future.succeededFuture(new JsonObject()));
        System.out.println("Node registration end");
    }
}
