package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Service {

	@SerializedName("ID")
	private String id;

	@SerializedName("Service")
	private String service;

	@SerializedName("Tags")
	private List<String> tags;

	@SerializedName("Address")
	private String address;

	@SerializedName("Port")
	private Integer port;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Service{" +
				"id='" + id + '\'' +
				", service='" + service + '\'' +
				", tags=" + tags +
				", address='" + address + '\'' +
				", port=" + port +
				'}';
	}
}