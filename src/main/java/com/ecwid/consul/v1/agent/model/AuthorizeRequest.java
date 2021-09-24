package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import java.util.StringJoiner;

public class AuthorizeRequest {

	@SerializedName("Target")
	private String target;

	@SerializedName("ClientCertURI")
	private String clientCertUri;

	@SerializedName("ClientCertSerial")
	private String clientCertSerial;

	@SerializedName("Namespace")
	private String namespace = "default";

	public String getTarget() {
		return target;
	}

	public void setTarget(final String target) {
		this.target = target;
	}

	public String getClientCertUri() {
		return clientCertUri;
	}

	public void setClientCertUri(final String clientCertUri) {
		this.clientCertUri = clientCertUri;
	}

	public String getClientCertSerial() {
		return clientCertSerial;
	}

	public void setClientCertSerial(final String clientCertSerial) {
		this.clientCertSerial = clientCertSerial;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(final String namespace) {
		this.namespace = namespace;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final AuthorizeRequest authorize = (AuthorizeRequest) o;
		return Objects.equals(target, authorize.target)
				&& Objects.equals(clientCertUri, authorize.clientCertUri)
				&& Objects.equals(clientCertSerial, authorize.clientCertSerial)
				&& Objects.equals(namespace, authorize.namespace);
	}

	@Override
	public int hashCode() {
		return Objects.hash(target, clientCertUri, clientCertSerial, namespace);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", AuthorizeRequest.class.getSimpleName() + "[", "]")
				.add("target='" + target + "'")
				.add("clientCertUri='" + clientCertUri + "'")
				.add("clientCertSerial='" + clientCertSerial + "'")
				.add("namespace='" + namespace + "'")
				.toString();
	}

}
