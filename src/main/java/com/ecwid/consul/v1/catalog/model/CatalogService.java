package com.ecwid.consul.v1.catalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class CatalogService {

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

	@SerializedName("NodeMeta")
	private Map<String, String> nodeMeta;

	@SerializedName("ServiceID")
	private String serviceId;

	@SerializedName("ServiceName")
	private String serviceName;

	@SerializedName("ServiceTags")
	private List<String> serviceTags;

	@SerializedName("ServiceAddress")
	private String serviceAddress;

	@SerializedName("ServiceMeta")
	private Map<String, String> serviceMeta;

	@SerializedName("ServicePort")
	private Integer servicePort;

	@SerializedName("ServiceEnableTagOverride")
	private Boolean serviceEnableTagOverride;

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

	public Map<String, String> getNodeMeta() {
		return nodeMeta;
	}

	public void setNodeMeta(Map<String, String> nodeMeta) {
		this.nodeMeta = nodeMeta;
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

	public Map<String, String> getServiceMeta() {
		return serviceMeta;
	}

	public void setServiceMeta(Map<String, String> serviceMeta) {
		this.serviceMeta = serviceMeta;
	}

	public Integer getServicePort() {
		return servicePort;
	}

	public void setServicePort(Integer servicePort) {
		this.servicePort = servicePort;
	}

	public Boolean getServiceEnableTagOverride() {
		return serviceEnableTagOverride;
	}

	public void setServiceEnableTagOverride(Boolean serviceEnableTagOverride) {
		this.serviceEnableTagOverride = serviceEnableTagOverride;
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
		return "CatalogService{" +
				"id='" + id + '\'' +
				", node='" + node + '\'' +
				", address='" + address + '\'' +
				", datacenter='" + datacenter + '\'' +
				", taggedAddresses=" + taggedAddresses +
				", nodeMeta=" + nodeMeta +
				", serviceId='" + serviceId + '\'' +
				", serviceName='" + serviceName + '\'' +
				", serviceTags=" + serviceTags +
				", serviceAddress='" + serviceAddress + '\'' +
				", serviceMeta=" + serviceMeta +
				", servicePort=" + servicePort +
				", serviceEnableTagOverride=" + serviceEnableTagOverride +
				", createIndex=" + createIndex +
				", modifyIndex=" + modifyIndex +
				'}';
	}
}
