package com.ecwid.consul.v1.health;

import com.ecwid.consul.ConsulRequest;
import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.v1.TagsParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.v1.NodeMetaParameters;
import com.ecwid.consul.v1.QueryParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class HealthServicesRequest implements ConsulRequest {

	private final String datacenter;
	private final String near;
	private final String[] tags;
	private final Map<String, String> nodeMeta;
	private final boolean passing;
	private final QueryParams queryParams;
	private final String token;
	private final boolean cached;

	private HealthServicesRequest(
			String datacenter,
			String near,
			String[] tags,
			Map<String, String> nodeMeta,
			boolean passing,
			QueryParams queryParams,
			String token
	) {
		this.datacenter = datacenter;
		this.near = near;
		this.tags = tags;
		this.nodeMeta = nodeMeta;
		this.passing = passing;
		this.queryParams = queryParams;
		this.token = token;
		cached = false;
	}

	private HealthServicesRequest(
			String datacenter,
			String near,
			String[] tags,
			Map<String, String> nodeMeta,
			boolean passing,
			QueryParams queryParams,
			String token,
			boolean cached
	) {
		this.datacenter = datacenter;
		this.near = near;
		this.tags = tags;
		this.nodeMeta = nodeMeta;
		this.passing = passing;
		this.queryParams = queryParams;
		this.token = token;
		this.cached = cached;
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

	public boolean isCached() {
		return cached;
	}

	public static class Builder {
		private String datacenter;
		private String near;
		private String[] tags;
		private Map<String, String> nodeMeta;
		private boolean passing;
		private QueryParams queryParams;
		private String token;
		private boolean cached;

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

		/**
		 * Use cached queries, see https://www.consul.io/api-docs/features/caching
		 */
		public Builder setCached(boolean cached) {
			this.cached = cached;
			return this;
		}

		public HealthServicesRequest build() {
			return new HealthServicesRequest(datacenter, near, tags, nodeMeta, passing, queryParams, token, cached);
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

		if (cached) {
			// any value is true
			params.add(new SingleUrlParameters("cached", "1"));
		}

		return params;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof HealthServicesRequest)) {
			return false;
		}
		HealthServicesRequest that = (HealthServicesRequest) o;
		return passing == that.passing &&
			Objects.equals(datacenter, that.datacenter) &&
			Objects.equals(near, that.near) &&
			Arrays.equals(tags, that.tags) &&
			Objects.equals(nodeMeta, that.nodeMeta) &&
			Objects.equals(queryParams, that.queryParams) &&
			Objects.equals(token, that.token) &&
			Objects.equals(cached, that.cached);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(datacenter, near, nodeMeta, passing, queryParams, token, cached);
		result = 31 * result + Arrays.hashCode(tags);
		return result;
	}
}
