package com.ecwid.consul.v1.catalog.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Node {

	@SerializedName("Node")
	private String node;

	@SerializedName("Address")
	private String address;

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

	@Override
	public String toString() {
		return "Node{" +
				"node='" + node + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
