package com.ecwid.consul.v1.connect.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import java.util.Objects;

public class CaConfigurationResponse {

	@SerializedName("Provider")
	private String provider;

	@SerializedName("Config")
	private Map<String, String> config;

	@SerializedName("CreateIndex")
	private int createIndex;

	@SerializedName("ModifyIndex")
	private int modifyIndex;

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
		final CaConfigurationResponse that = (CaConfigurationResponse) o;
		return createIndex == that.createIndex
				&& modifyIndex == that.modifyIndex
				&& Objects.equals(provider, that.provider)
				&& Objects.equals(config, that.config);
	}

	@Override
	public int hashCode() {
		return Objects.hash(provider, config, createIndex, modifyIndex);
	}

	@Override
	public String toString() {
		return "CaConfigurationResponse{" +
				"provider='" + provider + '\'' +
				", config=" + config +
				", createIndex=" + createIndex +
				", modifyIndex=" + modifyIndex +
				'}';
	}

}
