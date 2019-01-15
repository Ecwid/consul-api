package com.ecwid.consul.v1.health;

import com.ecwid.consul.ConsulRequest;
import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.v1.TagsParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.v1.NodeMetaParameters;
import com.ecwid.consul.v1.QueryParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class HealthServicesRequest implements ConsulRequest {

	private final String datacenter;
	private final String near;
	private final String[] tags;
	private final Map<String, String> nodeMeta;
	private final boolean passing;
	private final QueryParams queryParams;
	private final String token;

	private HealthServicesRequest(String datacenter, String near, String[] tags, Map<String, String> nodeMeta, boolean passing, QueryParams queryParams, String token) {
		this.datacenter = datacenter;
		this.near = near;
		this.tags = tags;
		this.nodeMeta = nodeMeta;
		this.passing = passing;
		this.queryParams = queryParams;
		this.token = token;
	}

	public String getDatacenter() {
		return datacenter;
	}

	public String getNear() {
		return near;
	}

	public String getTag() {
		return tags != null && tags.length > 0 ? tags[0] : null;
	}

	public String[] getTags() {
		return tags;
	}

	public Map<String, String> getNodeMeta() {
		return nodeMeta;
	}

	public boolean isPassing() {
		return passing;
	}

	public QueryParams getQueryParams() {
		return queryParams;
	}

	public String getToken() {
		return token;
	}

	public static class Builder {
		private String datacenter;
		private String near;
		private String[] tags;
		private Map<String, String> nodeMeta;
		private boolean passing;
		private QueryParams queryParams;
		private String token;

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

		public Builder setTag(String tag) {
			this.tags = new String[]{tag};
			return this;
		}

		public Builder setTags(String[] tags) {
			this.tags = tags;
			return this;
		}

		public Builder setNodeMeta(Map<String, String> nodeMeta) {
			this.nodeMeta = nodeMeta != null ? Collections.unmodifiableMap(nodeMeta) : null;
			return this;
		}

		public Builder setPassing(boolean passing) {
			this.passing = passing;
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

		public HealthServicesRequest build() {
			return new HealthServicesRequest(datacenter, near, tags, nodeMeta, passing, queryParams, token);
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

		if (tags != null) {
			params.add(new TagsParameters(tags));
		}

		if (nodeMeta != null) {
			params.add(new NodeMetaParameters(nodeMeta));
		}

		params.add(new SingleUrlParameters("passing", String.valueOf(passing)));

		if (queryParams != null) {
			params.add(queryParams);
		}

		if (token != null) {
			params.add(new SingleUrlParameters("token", token));
		}

		return params;
	}
}