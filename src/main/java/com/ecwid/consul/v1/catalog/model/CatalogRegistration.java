package com.ecwid.consul.v1.catalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class CatalogRegistration {

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

	public static enum CheckStatus {
		@SerializedName("unknown")
		UNKNOWN,
		@SerializedName("passing")
		PASSING,
		@SerializedName("warning")
		WARNING,
		@SerializedName("critical")
		CRITICAL
	}

	public static class Check {

		@SerializedName("Node")
		private String node;

		@SerializedName("CheckID")
		private String checkId;

		@SerializedName("Name")
		private String name;

		@SerializedName("Notes")
		private String notes;

		@SerializedName("Status")
		private CheckStatus status;

		@SerializedName("ServiceID")
		private String serviceId;

		public String getNode() {
			return node;
		}

		public void setNode(String node) {
			this.node = node;
		}

		public String getCheckId() {
			return checkId;
		}

		public void setCheckId(String checkId) {
			this.checkId = checkId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public CheckStatus getStatus() {
			return status;
		}

		public void setStatus(CheckStatus status) {
			this.status = status;
		}

		public String getServiceId() {
			return serviceId;
		}

		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}

		@Override
		public String toString() {
			return "Check{" +
					"node='" + node + '\'' +
					", checkId='" + checkId + '\'' +
					", name='" + name + '\'' +
					", notes='" + notes + '\'' +
					", status=" + status +
					", serviceId='" + serviceId + '\'' +
					'}';
		}
	}

	@SerializedName("Datacenter")
	private String datacenter;

	@SerializedName("Node")
	private String node;

	@SerializedName("Address")
	private String address;

	@SerializedName("Service")
	private Service service;

	@SerializedName("Check")
	private Check check;

	@SerializedName("WriteRequest")
	private WriteRequest writeRequest;

	public String getDatacenter() {
		return datacenter;
	}

	public void setDatacenter(String datacenter) {
		this.datacenter = datacenter;
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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

	public WriteRequest getWriteRequest() {
		return writeRequest;
	}

	public void setWriteRequest(WriteRequest writeRequest) {
		this.writeRequest = writeRequest;
	}

	@Override
	public String toString() {
		return "CatalogRegistration{" +
				"datacenter='" + datacenter + '\'' +
				", node='" + node + '\'' +
				", address='" + address + '\'' +
				", service=" + service +
				", check=" + check +
				", writeRequest=" + writeRequest +
				'}';
	}
}
