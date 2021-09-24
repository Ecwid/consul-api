package com.ecwid.consul.v1.connect.model;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public class CaRoots extends com.ecwid.consul.v1.agent.model.CaRoots {

	@SerializedName("TrustDomain")
	private String trustDomain;

	public String getTrustDomain() {
		return trustDomain;
	}

	public void setTrustDomain(final String trustDomain) {
		this.trustDomain = trustDomain;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		final CaRoots caRoots = (CaRoots) o;
		return Objects.equals(trustDomain, caRoots.trustDomain);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), trustDomain);
	}

	@Override
	public String toString() {
		return "CaRoots{" +
				"trustDomain='" + trustDomain + '\'' +
				"} " + super.toString();
	}

}
