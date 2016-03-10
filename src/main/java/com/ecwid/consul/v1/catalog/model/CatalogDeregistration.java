package com.ecwid.consul.v1.catalog.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class CatalogDeregistration {

	@SerializedName("Datacenter")
	private String datacenter;

	@SerializedName("Node")
	private String node;

	@SerializedName("CheckID")
	private String checkId;

	@SerializedName("ServiceID")
	private String serviceId;

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

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public WriteRequest getWriteRequest() {
		return writeRequest;
	}

	public void setWriteRequest(WriteRequest writeRequest) {
		this.writeRequest = writeRequest;
	}

	@Override
	public String toString() {
		return "CatalogDeregistration{" +
				"datacenter='" + datacenter + '\'' +
				", node='" + node + '\'' +
				", checkId='" + checkId + '\'' +
				", serviceId='" + serviceId + '\'' +
				", writeRequest=" + writeRequest +
				'}';
	}
}
