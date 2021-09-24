package com.ecwid.consul.v1.agent.model;

import com.ecwid.consul.json.CertificateChainTypeAdapterFactory;
import com.ecwid.consul.json.OffsetDateTimeTypeAdapter;
import com.ecwid.consul.json.CertificateTypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.security.cert.Certificate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class CaRoot {

	@SerializedName("ID")
	private String id;

	@SerializedName("Name")
	private String name;

	@SerializedName("SerialNumber")
	private int serialNumber;

	@SerializedName("SigningKeyID")
	private String signingKeyId;

	@SerializedName("ExternalTrustDomain")
	private String externalTrustDomain;

	@SerializedName("NotBefore")
	@JsonAdapter(OffsetDateTimeTypeAdapter.class)
	private OffsetDateTime notBefore;

	@SerializedName("NotAfter")
	@JsonAdapter(OffsetDateTimeTypeAdapter.class)
	private OffsetDateTime notAfter;

	@SerializedName("RootCert")
	@JsonAdapter(CertificateTypeAdapter.class)
	private Certificate rootCert;

	@SerializedName("IntermediateCerts")
	@JsonAdapter(CertificateChainTypeAdapterFactory.class)
	private List<Certificate> intermediateCerts;

	@SerializedName("Active")
	private boolean active;

	@SerializedName("PrivateKeyType")
	private String privateKeyType;

	@SerializedName("PrivateKeyBits")
	private int privateKeyBits;

	@SerializedName("CreateIndex")
	private int createIndex;

	@SerializedName("ModifyIndex")
	private int modifyIndex;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(final int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSigningKeyId() {
		return signingKeyId;
	}

	public void setSigningKeyId(final String signingKeyId) {
		this.signingKeyId = signingKeyId;
	}

	public String getExternalTrustDomain() {
		return externalTrustDomain;
	}

	public void setExternalTrustDomain(final String externalTrustDomain) {
		this.externalTrustDomain = externalTrustDomain;
	}

	public OffsetDateTime getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(final OffsetDateTime notBefore) {
		this.notBefore = notBefore;
	}

	public OffsetDateTime getNotAfter() {
		return notAfter;
	}

	public void setNotAfter(final OffsetDateTime notAfter) {
		this.notAfter = notAfter;
	}

	public Certificate getRootCert() {
		return rootCert;
	}

	public void setRootCert(final Certificate rootCert) {
		this.rootCert = rootCert;
	}

	public List<Certificate> getIntermediateCerts() {
		return intermediateCerts;
	}

	public void setIntermediateCerts(final List<Certificate> intermediateCerts) {
		this.intermediateCerts = intermediateCerts;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	public String getPrivateKeyType() {
		return privateKeyType;
	}

	public void setPrivateKeyType(final String privateKeyType) {
		this.privateKeyType = privateKeyType;
	}

	public int getPrivateKeyBits() {
		return privateKeyBits;
	}

	public void setPrivateKeyBits(final int privateKeyBits) {
		this.privateKeyBits = privateKeyBits;
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
		final CaRoot caRoot = (CaRoot) o;
		return serialNumber == caRoot.serialNumber
				&& active == caRoot.active
				&& privateKeyBits == caRoot.privateKeyBits
				&& createIndex == caRoot.createIndex
				&& modifyIndex == caRoot.modifyIndex
				&& Objects.equals(id, caRoot.id)
				&& Objects.equals(name, caRoot.name)
				&& Objects.equals(signingKeyId, caRoot.signingKeyId)
				&& Objects.equals(externalTrustDomain, caRoot.externalTrustDomain)
				&& Objects.equals(notBefore, caRoot.notBefore)
				&& Objects.equals(notAfter, caRoot.notAfter)
				&& Objects.equals(rootCert, caRoot.rootCert)
				&& Objects.equals(intermediateCerts, caRoot.intermediateCerts)
				&& Objects.equals(privateKeyType, caRoot.privateKeyType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, serialNumber, signingKeyId,
				externalTrustDomain, notBefore, notAfter, rootCert,
				intermediateCerts, active, privateKeyType, privateKeyBits,
				createIndex, modifyIndex);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CaRoot.class.getSimpleName() + "[", "]")
				.add("id='" + id + "'")
				.add("name='" + name + "'")
				.add("serialNumber=" + serialNumber)
				.add("signingKeyId='" + signingKeyId + "'")
				.add("externalTrustDomain='" + externalTrustDomain + "'")
				.add("notBefore='" + notBefore + "'")
				.add("notAfter='" + notAfter + "'")
				.add("rootCert='" + rootCert + "'")
				.add("intermediateCerts=" + intermediateCerts)
				.add("active=" + active)
				.add("privateKeyType='" + privateKeyType + "'")
				.add("privateKeyBits=" + privateKeyBits)
				.add("createIndex=" + createIndex)
				.add("modifyIndex=" + modifyIndex)
				.toString();
	}

}
