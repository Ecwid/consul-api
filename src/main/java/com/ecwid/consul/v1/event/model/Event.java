package com.ecwid.consul.v1.event.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Event {

	@SerializedName("ID")
	private String id;

	@SerializedName("Name")
	private String name;

	@SerializedName("Payload")
	private String payload;

	@SerializedName("NodeFilter")
	private String nodeFilter;

	@SerializedName("ServiceFilter")
	private String serviceFilter;

	@SerializedName("TagFilter")
	private String tagFilter;

	@SerializedName("Version")
	private int version;

	@SerializedName("LTime")
	private int lTime;

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

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getNodeFilter() {
		return nodeFilter;
	}

	public void setNodeFilter(String nodeFilter) {
		this.nodeFilter = nodeFilter;
	}

	public String getServiceFilter() {
		return serviceFilter;
	}

	public void setServiceFilter(String serviceFilter) {
		this.serviceFilter = serviceFilter;
	}

	public String getTagFilter() {
		return tagFilter;
	}

	public void setTagFilter(String tagFilter) {
		this.tagFilter = tagFilter;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getlTime() {
		return lTime;
	}

	public void setlTime(int lTime) {
		this.lTime = lTime;
	}

	@Override
	public String toString() {
		return "Event{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", payload='" + payload + '\'' +
				", nodeFilter='" + nodeFilter + '\'' +
				", serviceFilter='" + serviceFilter + '\'' +
				", tagFilter='" + tagFilter + '\'' +
				", version=" + version +
				", lTime=" + lTime +
				'}';
	}
}
