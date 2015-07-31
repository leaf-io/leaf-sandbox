package io.leaf.core.data;

import io.vertx.core.json.JsonObject;

/**
 * Created by Gabo on 2015.07.31..
 */
public class JsonDTO {

    protected JsonObject jsonObject = new JsonObject();

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }
}
