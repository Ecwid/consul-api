package com.ecwid.consul.v1.catalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Node {

	@SerializedName("ID")
	private String id;

	@SerializedName("Node")
	private String node;

	@SerializedName("Address")
	private String address;

	@SerializedName("Datacenter")
	private String datacenter;

	@SerializedName("TaggedAddresses")
	private Map<String, String> taggedAddresses;

	@SerializedName("Meta")
	private Map<String, String> meta;

	@SerializedName("CreateIndex")
	private Long createIndex;

	@SerializedName("ModifyIndex")
	private Long modifyIndex;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDatacenter() {
		return datacenter;
	}

	public void setDatacenter(String datacenter) {
		this.datacenter = datacenter;
	}

	public Map<String, String> getTaggedAddresses() {
		return taggedAddresses;
	}

	public void setTaggedAddresses(Map<String, String> taggedAddresses) {
		this.taggedAddresses = taggedAddresses;
	}

	public Map<String, String> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}

	public Long getCreateIndex() {
		return createIndex;
	}

	public void setCreateIndex(Long createIndex) {
		this.createIndex = createIndex;
	}

	public Long getModifyIndex() {
		return modifyIndex;
	}

	public void setModifyIndex(Long modifyIndex) {
		this.modifyIndex = modifyIndex;
	}

	@Override
	public String toString() {
		return "Node{" +
				"id='" + id + '\'' +
				", node='" + node + '\'' +
				", address='" + address + '\'' +
				", datacenter='" + datacenter + '\'' +
				", taggedAddresses=" + taggedAddresses +
				", meta=" + meta +
				", createIndex=" + createIndex +
				", modifyIndex=" + modifyIndex +
				'}';
	}
}
