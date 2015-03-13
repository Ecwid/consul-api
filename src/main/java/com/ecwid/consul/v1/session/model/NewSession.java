package com.ecwid.consul.v1.session.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class NewSession {

    @SerializedName("LockDelay")
    private long lockDelay;

    @SerializedName("Name")
    private String name;

    @SerializedName("Node")
    private String node;

    @SerializedName("Checks")
    private List<String> checks;

    @SerializedName("Behavior")
    private String behavior;

    @SerializedName("TTL")
    private String ttl;

    public long getLockDelay() {
        return lockDelay;
    }

    public void setLockDelay(long lockDelayInSeconds) {
        this.lockDelay = lockDelayInSeconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public List<String> getChecks() {
        return checks;
    }

    public void setChecks(List<String> checks) {
        this.checks = checks;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
}
