package io.leaf.core;

/**
 * Created by Gabo on 2015.07.31..
 */
public interface Definition {

    public String getGroupId();

    public String getArtifactId();

    public String getVersion();

    public default String getKey() {
        return getGroupId()+":"+getArtifactId()+":"+getVersion();
    }
}
