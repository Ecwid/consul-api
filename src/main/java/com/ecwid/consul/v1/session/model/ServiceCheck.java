package com.ecwid.consul.v1.session.model;

import com.google.gson.annotations.SerializedName;

public class ServiceCheck {

	@SerializedName("ID")
	private String id;

	@SerializedName("Namespace")
	private String namespace;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public String toString() {
		return "ServiceCheck{" +
				"id='" + id + '\'' +
				", namespace='" + namespace + '\'' +
				'}';
	}
}
