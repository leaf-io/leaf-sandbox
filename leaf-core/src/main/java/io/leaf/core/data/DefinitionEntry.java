package io.leaf.core.data;

/**
 * Created by Gabo on 2015.07.31..
 */
public class DefinitionEntry extends JsonDTO implements Definition {

    protected String groupId;
    protected String artifactId;
    protected String version;
    
    public String getGroupId() {
        return jsonObject.getString("groupId");
    }

    public void setGroupId(String groupId) {
        jsonObject.put("groupId", groupId);
    }

    public String getArtifactId() {
        return jsonObject.getString("artifactId");
    }

    public void setArtifactId(String artifactId) {
        jsonObject.put("artifactId", artifactId);
    }

    public String getVersion() {
        return jsonObject.getString("version");
    }

    public void setVersion(String version) {
        jsonObject.put("version", version);
    }
}
