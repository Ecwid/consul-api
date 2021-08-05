package com.ecwid.consul.v1.session.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

	/**
	 * @deprecated this field is deprecated as of consul 1.7, the fields nodeChecks and serviceChecks should be used instead
	 */
	@Deprecated
	@SerializedName("Checks")
	private List<String> checks;

	@SerializedName("NodeChecks")
	private List<String> nodeChecks;

	@SerializedName("ServiceChecks")
	private List<ServiceCheck> serviceChecks;

	@SerializedName("Behavior")
	private Session.Behavior behavior;

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

	public List<String> getNodeChecks() {
		return nodeChecks;
	}

	public void setNodeChecks(List<String> nodeChecks) {
		this.nodeChecks = nodeChecks;
	}

	public List<ServiceCheck> getServiceChecks() {
		return serviceChecks;
	}

	public void setServiceChecks(List<ServiceCheck> serviceChecks) {
		this.serviceChecks = serviceChecks;
	}

	public Session.Behavior getBehavior() {
		return behavior;
	}

	public void setBehavior(Session.Behavior behavior) {
		this.behavior = behavior;
	}

	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
}
