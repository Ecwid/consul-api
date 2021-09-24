package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import java.util.StringJoiner;

public class LeafCertificate {

	@SerializedName("SerialNumber")
	private String serialNumber;

	@SerializedName("CertPEM")
	private String certPEM;

	@SerializedName("PrivateKeyPEM")
	private String privateKeyPEM;

	@SerializedName("Service")
	private String service;

	@SerializedName("ServiceURI")
	private String serviceURI;

	@SerializedName("ValidAfter")
	private String validAfter;

	@SerializedName("ValidBefore")
	private String validBefore;

	@SerializedName("Namespace")
	private String namespace;

	@SerializedName("CreateIndex")
	private int createIndex;

	@SerializedName("ModifyIndex")
	private int modifyIndex;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCertPEM() {
		return certPEM;
	}

	public void setCertPEM(final String certPEM) {
		this.certPEM = certPEM;
	}

	public String getPrivateKeyPEM() {
		return privateKeyPEM;
	}

	public void setPrivateKeyPEM(final String privateKeyPEM) {
		this.privateKeyPEM = privateKeyPEM;
	}

	public String getService() {
		return service;
	}

	public void setService(final String service) {
		this.service = service;
	}

	public String getServiceURI() {
		return serviceURI;
	}

	public void setServiceURI(final String serviceURI) {
		this.serviceURI = serviceURI;
	}

	public String getValidAfter() {
		return validAfter;
	}

	public void setValidAfter(final String validAfter) {
		this.validAfter = validAfter;
	}

	public String getValidBefore() {
		return validBefore;
	}

	public void setValidBefore(final String validBefore) {
		this.validBefore = validBefore;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(final String namespace) {
		this.namespace = namespace;
	}

	public int getCreateIndex() {
		return createIndex;
	}

	public void setCreateIndex(final int createIndex) {
		this.createIndex = createIndex;
	}

	public int getModifyIndex() {
		return modifyIndex;
	}

	public void setModifyIndex(final int modifyIndex) {
		this.modifyIndex = modifyIndex;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final LeafCertificate that = (LeafCertificate) o;
		return createIndex == that.createIndex
				&& modifyIndex == that.modifyIndex
				&& Objects.equals(serialNumber, that.serialNumber)
				&& Objects.equals(certPEM, that.certPEM)
				&& Objects.equals(privateKeyPEM, that.privateKeyPEM)
				&& Objects.equals(service, that.service)
				&& Objects.equals(serviceURI, that.serviceURI)
				&& Objects.equals(validAfter, that.validAfter)
				&& Objects.equals(validBefore, that.validBefore)
				&& Objects.equals(namespace, that.namespace);
	}

	@Override
	public int hashCode() {
		return Objects.hash(serialNumber, certPEM, privateKeyPEM, service, serviceURI, validAfter, validBefore, namespace, createIndex, modifyIndex);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", LeafCertificate.class.getSimpleName() + "[", "]")
				.add("serialNumber='" + serialNumber + "'")
				.add("certPEM='" + certPEM + "'")
				.add("privateKeyPEM='" + privateKeyPEM + "'")
				.add("service='" + service + "'")
				.add("serviceURI='" + serviceURI + "'")
				.add("validAfter='" + validAfter + "'")
				.add("validBefore='" + validBefore + "'")
				.add("namespace='" + namespace + "'")
				.add("createIndex=" + createIndex)
				.add("modifyIndex=" + modifyIndex)
				.toString();
	}

}
