package io.leaf.core.interfaceregistry;

import io.leaf.core.data.DefinitionEntry;

/**
 * Created by Gabo on 2015.07.31..
 */
public class InterfaceEntry extends DefinitionEntry {
    
    protected String topic;

    public String getTopic() {
        return jsonObject.getString("topic");
    }

    public void setTopic(String topic) {
        jsonObject.put("topic", topic);
    }
}
