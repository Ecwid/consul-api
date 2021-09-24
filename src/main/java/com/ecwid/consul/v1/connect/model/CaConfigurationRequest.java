package com.ecwid.consul.v1.connect.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import java.util.Objects;

public class CaConfigurationRequest {

	@SerializedName("Provider")
	private String provider;

	@SerializedName("Config")
	private Map<String, String> config;

	@SerializedName("ForceWithoutCrossSigning")
	private boolean forceWithoutCrossSigning;

	public String getProvider() {
		return provider;
	}

	public void setProvider(final String provider) {
		this.provider = provider;
	}

	public Map<String, String> getConfig() {
		return config;
	}

	public void setConfig(final Map<String, String> config) {
		this.config = config;
	}

	public boolean isForceWithoutCrossSigning() {
		return forceWithoutCrossSigning;
	}

	public void setForceWithoutCrossSigning(final boolean forceWithoutCrossSigning) {
		this.forceWithoutCrossSigning = forceWithoutCrossSigning;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final CaConfigurationRequest that = (CaConfigurationRequest) o;
		return forceWithoutCrossSigning == that.forceWithoutCrossSigning
				&& Objects.equals(provider, that.provider)
				&& Objects.equals(config, that.config);
	}

	@Override
	public int hashCode() {
		return Objects.hash(provider, config, forceWithoutCrossSigning);
	}

	@Override
	public String toString() {
		return "CaConfigurationRequest{" +
				"provider='" + provider + '\'' +
				", config=" + config +
				", forceWithoutCrossSigning=" + forceWithoutCrossSigning +
				'}';
	}

}
