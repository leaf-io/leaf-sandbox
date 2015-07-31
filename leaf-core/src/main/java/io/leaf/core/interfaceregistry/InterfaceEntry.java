package io.leaf.core.interfaceregistry;

import io.leaf.core.Definition;
import io.leaf.core.DefinitionEntry;

/**
 * Created by Gabo on 2015.07.31..
 */
public class InterfaceEntry extends DefinitionEntry {
    
    protected String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}