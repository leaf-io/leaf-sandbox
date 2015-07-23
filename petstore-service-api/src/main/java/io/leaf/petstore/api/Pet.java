package io.leaf.petstore.api;

import io.vertx.core.json.JsonObject;

/**
 * Created by Gabo on 2015.07.23..
 */
public class Pet {

    private JsonObject jsonObject = new JsonObject();

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public String getName() {
        return jsonObject.getString("name");
    }

    public void setName(String name) {
        jsonObject.put("name", name);
    }
}
