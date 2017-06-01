package com.ecwid.consul.v1.health.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class HealthService {

	public static class Node {
		@SerializedName("Node")
		private String node;

		@SerializedName("Address")
		private String address;

		@SerializedName("Meta")
		private HashMap<String, String> meta;

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

		public void setMeta(HashMap<String, String> meta) { this.meta = meta; }

		public HashMap<String, String> getMeta() { return meta; }

		@Override
		public String toString() {
			return "Node{" +
					"node='" + node + '\'' +
					", address='" + address + '\'' +
					'}';
		}
	}

	public static class Service {
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

	@SerializedName("Node")
	private Node node;

	@SerializedName("Service")
	private Service service;

	@SerializedName("Checks")
	private List<Check> checks;

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<Check> getChecks() {
		return checks;
	}

	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}

	@Override
	public String toString() {
		return "HealthService{" +
				"node=" + node +
				", service=" + service +
				", checks=" + checks +
				'}';
	}
}
