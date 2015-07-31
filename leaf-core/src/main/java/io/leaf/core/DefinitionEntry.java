package io.leaf.core;

/**
 * Created by Gabo on 2015.07.31..
 */
public class DefinitionEntry implements Definition {

    protected String groupId;
    protected String artifactId;
    protected String version;
    
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
