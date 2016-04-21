package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Check {

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

	@SerializedName("Node")
	private String node;

	@SerializedName("CheckID")
	private String checkId;

	@SerializedName("Name")
	private String name;

	@SerializedName("Status")
	private CheckStatus status;

	@SerializedName("Notes")
	private String notes;

	@SerializedName("Output")
	private String output;

	@SerializedName("ServiceID")
	private String serviceId;

	@SerializedName("ServiceName")
	private String serviceName;

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

	public CheckStatus getStatus() {
		return status;
	}

	public void setStatus(CheckStatus status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
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

	@Override
	public String toString() {
		return "Check{" +
				"node='" + node + '\'' +
				", checkId='" + checkId + '\'' +
				", name='" + name + '\'' +
				", status=" + status +
				", notes='" + notes + '\'' +
				", output='" + output + '\'' +
				", serviceId='" + serviceId + '\'' +
				", serviceName='" + serviceName + '\'' +
				'}';
	}
}
