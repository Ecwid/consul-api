package com.ecwid.consul.v1.health;

import com.ecwid.consul.ConsulRequest;
import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.v1.NodeMetaParameters;
import com.ecwid.consul.v1.QueryParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class HealthChecksForServiceRequest implements ConsulRequest {

	private final String datacenter;
	private final String near;
	private final Map<String, String> nodeMeta;
	private final QueryParams queryParams;

	public HealthChecksForServiceRequest(String datacenter, String near, Map<String, String> nodeMeta, QueryParams queryParams) {
		this.datacenter = datacenter;
		this.near = near;
		this.nodeMeta = nodeMeta;
		this.queryParams = queryParams;
	}

	public String getDatacenter() {
		return datacenter;
	}

	public String getNear() {
		return near;
	}

	public Map<String, String> getNodeMeta() {
		return nodeMeta;
	}

	public QueryParams getQueryParams() {
		return queryParams;
	}

	public static class Builder {
		private String datacenter;
		private String near;
		private Map<String, String> nodeMeta;
		private QueryParams queryParams;

		private Builder() {
		}

		public Builder setDatacenter(String datacenter) {
			this.datacenter = datacenter;
			return this;
		}

		public Builder setNear(String near) {
			this.near = near;
			return this;
		}

		public Builder setNodeMeta(Map<String, String> nodeMeta) {
			this.nodeMeta = nodeMeta != null ? Collections.unmodifiableMap(nodeMeta) : null;
			return this;
		}

		public Builder setQueryParams(QueryParams queryParams) {
			this.queryParams = queryParams;
			return this;
		}

		public HealthChecksForServiceRequest build() {
			return new HealthChecksForServiceRequest(datacenter, near, nodeMeta, queryParams);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@Override
	public List<UrlParameters> asUrlParameters() {
		List<UrlParameters> params = new ArrayList<>();

		if (datacenter != null) {
			params.add(new SingleUrlParameters("dc", datacenter));
		}

		if (near != null) {
			params.add(new SingleUrlParameters("near", near));
		}

		if (nodeMeta != null) {
			params.add(new NodeMetaParameters(nodeMeta));
		}

		if (queryParams != null) {
			params.add(queryParams);
		}

		return params;
	}
}
