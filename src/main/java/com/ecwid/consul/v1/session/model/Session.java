package com.ecwid.consul.v1.session.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Session {

	public static enum Behavior {
		@SerializedName("release")
		RELEASE,

		@SerializedName("delete")
		DELETE
	}

	@SerializedName("LockDelay")
	private long lockDelay;

	@SerializedName("Checks")
	private List<String> checks;

	@SerializedName("Node")
	private String node;

	@SerializedName("ID")
	private String id;

	@SerializedName("Name")
	private String name;

	@SerializedName("CreateIndex")
	private long createIndex;

	@SerializedName("ModifyIndex")
	private long modifyIndex;

	@SerializedName("TTL")
	private String ttl;

	@SerializedName("Behavior")
	private Behavior behavior;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCreateIndex() {
		return createIndex;
	}

	public void setCreateIndex(long createIndex) {
		this.createIndex = createIndex;
	}

	public long getModifyIndex() {
		return modifyIndex;
	}

	public void setModifyIndex(long modifyIndex) {
		this.modifyIndex = modifyIndex;
	}

	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	public Behavior getBehavior() {
		return behavior;
	}

	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}

	@Override
	public String toString() {
		return "Session{" +
				"lockDelay=" + lockDelay +
				", checks=" + checks +
				", node='" + node + '\'' +
				", id='" + id + '\'' +
				", name='" + name + '\'' +
				", createIndex=" + createIndex +
				", modifyIndex=" + modifyIndex +
				", ttl='" + ttl + '\'' +
				", behavior=" + behavior +
				'}';
	}
}
