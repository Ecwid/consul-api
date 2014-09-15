package com.ecwid.consul.v1.catalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class CatalogNode {

	public static class Service {
		@SerializedName("ID")
		private String id;

		@SerializedName("Service")
		private String service;

		@SerializedName("Tags")
		private List<String> tags;

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
					", port=" + port +
					'}';
		}
	}

	@SerializedName("Node")
	private Node node;

	@SerializedName("Services")
	private Map<String, Service> services;

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Map<String, Service> getServices() {
		return services;
	}

	public void setServices(Map<String, Service> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "CatalogNode{" +
				"node=" + node +
				", services=" + services +
				'}';
	}
}


