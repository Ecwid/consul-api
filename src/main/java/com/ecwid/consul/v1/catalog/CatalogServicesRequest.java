package com.ecwid.consul.v1.catalog;

import com.ecwid.consul.ConsulRequest;
import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.v1.NodeMetaParameters;
import com.ecwid.consul.v1.QueryParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class CatalogServicesRequest implements ConsulRequest {

	private final String datacenter;
	private final Map<String, String> nodeMeta;
	private final QueryParams queryParams;
	private final String token;

	public CatalogServicesRequest(String datacenter, Map<String, String> nodeMeta, QueryParams queryParams, String token) {
		this.datacenter = datacenter;
		this.nodeMeta = nodeMeta;
		this.queryParams = queryParams;
		this.token = token;
	}

	public String getDatacenter() {
		return datacenter;
	}

	public Map<String, String> getNodeMeta() {
		return nodeMeta;
	}

	public QueryParams getQueryParams() {
		return queryParams;
	}

	public String getToken() {
		return token;
	}

	public static class Builder {
		private String datacenter;
		private Map<String, String> nodeMeta;
		private QueryParams queryParams;
		private String token;

		private Builder() {
		}

		public Builder setDatacenter(String datacenter) {
			this.datacenter = datacenter;
			return this;
		}

		public Builder setNodeMeta(Map<String, String> nodeMeta) {
			if (nodeMeta == null) {
				this.nodeMeta = null;
			} else {
				this.nodeMeta = Collections.unmodifiableMap(nodeMeta);
			}

			return this;
		}

		public Builder setQueryParams(QueryParams queryParams) {
			this.queryParams = queryParams;
			return this;
		}

		public Builder setToken(String token) {
			this.token = token;
			return this;
		}

		public CatalogServicesRequest build() {
			return new CatalogServicesRequest(datacenter, nodeMeta, queryParams, token);
		}
	}

	public static Builder newBuilder() {
		return new CatalogServicesRequest.Builder();
	}

	@Override
	public List<UrlParameters> asUrlParameters() {
		List<UrlParameters> params = new ArrayList<>();

		if (datacenter != null) {
			params.add(new SingleUrlParameters("dc", datacenter));
		}

		if (nodeMeta != null) {
			params.add(new NodeMetaParameters(nodeMeta));
		}

		if (queryParams != null) {
			params.add(queryParams);
		}

		if (token != null) {
			params.add(new SingleUrlParameters("token", token));
		}

		return params;
	}
}
