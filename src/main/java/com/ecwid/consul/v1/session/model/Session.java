package com.ecwid.consul.v1.session.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Session {

    @SerializedName("LockDelay")
    private long lockDelay;

    @SerializedName("Checks")
    private List<String> checks;

    @SerializedName("Node")
    private String node;

    @SerializedName("ID")
    private String id;

    @SerializedName("CreateIndex")
    private long createIndex;

    @SerializedName("Behavior")
    private String behavior;

    @SerializedName("TTL")
    private String ttl;

    public long getLockDelay() {
        return lockDelay;
    }

    public void setLockDelay(long lockDelay) {
        this.lockDelay = lockDelay;
    }

    public List<String> getChecks() {
        return checks;
    }

    public void setChecks(List<String> checks) {
        this.checks = checks;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateIndex() {
        return createIndex;
    }

    public void setCreateIndex(long createIndex) {
        this.createIndex = createIndex;
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

    @Override
    public String toString() {
        return "Session{" + "lockDelay=" + lockDelay + ", checks=" + checks + ", node='" + node + '\'' + ", id='" + id + '\'' + ", createIndex="
                + createIndex + ", behavior='" + behavior + "', ttl='" + ttl + '\'' + '}';
    }
}
