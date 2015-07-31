package io.leaf.core.noderegistry;

/**
 * Created by Gabo on 2015.07.31..
 */
public class NodeEntry {

    private String managementTopic;

    private NodeStatus status;

    public String getManagementTopic() {
        return managementTopic;
    }

    public void setManagementTopic(String managementTopic) {
        this.managementTopic = managementTopic;
    }

    public NodeStatus getStatus() {
        return status;
    }

    public void setStatus(NodeStatus status) {
        this.status = status;
    }
}
