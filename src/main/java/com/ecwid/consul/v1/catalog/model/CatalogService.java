package com.ecwid.consul.v1.catalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class CatalogService {

	@SerializedName("Node")
	private String node;

	@SerializedName("Address")
	private String address;

	@SerializedName("ServiceID")
	private String serviceId;

	@SerializedName("ServiceName")
	private String serviceName;

	@SerializedName("ServiceTags")
	private List<String> serviceTags;

	@SerializedName("ServiceAddress")
	private String serviceAddress;

	@SerializedName("ServicePort")
	private Integer servicePort;

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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public List<String> getServiceTags() {
		return serviceTags;
	}

	public void setServiceTags(List<String> serviceTags) {
		this.serviceTags = serviceTags;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public Integer getServicePort() {
		return servicePort;
	}

	public void setServicePort(Integer servicePort) {
		this.servicePort = servicePort;
	}

	@Override
	public String toString() {
		return "CatalogService{" +
				"node='" + node + '\'' +
				", address='" + address + '\'' +
				", serviceId='" + serviceId + '\'' +
				", serviceName='" + serviceName + '\'' +
				", serviceTags=" + serviceTags +
				", serviceAddress='" + serviceAddress + '\'' +
				", servicePort=" + servicePort +
				'}';
	}
}
