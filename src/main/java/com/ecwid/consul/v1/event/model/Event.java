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

	/**
	 * Converted from https://github.com/hashicorp/consul/blob/master/api/event.go#L90-L104
	 * This is a hack. It simulates the index generation to convert an event ID into a WaitIndex.
	 *
	 * @return a Wait Index value suitable for passing in to {@link com.ecwid.consul.v1.QueryParams}
	 * for blocking eventList calls.
	 */
	public long getWaitIndex() {
		if (id == null || id.length() != 36) {
			return 0;
		}
		long lower = 0, upper = 0;
		for (int i = 0; i < 18; i++) {
			if (i != 8 && i != 13) {
				lower = lower * 16 + Character.digit(id.charAt(i), 16);
			}
		}
		for (int i = 19; i < 36; i++) {
			if (i != 23) {
				upper = upper * 16 + Character.digit(id.charAt(i), 16);
			}
		}
		return lower ^ upper;
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
