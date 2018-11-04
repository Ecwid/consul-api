package com.ecwid.consul.v1.event;

import com.ecwid.consul.ConsulRequest;
import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.v1.QueryParams;

import java.util.ArrayList;
import java.util.List;

public final class EventListRequest implements ConsulRequest {

	private final String name;
	private final String node;
	private final String service;
	private final String tag;
	private final QueryParams queryParams;
	private final String token;

	private EventListRequest(String name, String node, String service, String tag, QueryParams queryParams, String token) {
		this.name = name;
		this.node = node;
		this.service = service;
		this.tag = tag;
		this.queryParams = queryParams;
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public String getNode() {
		return node;
	}

	public String getService() {
		return service;
	}

	public String getTag() {
		return tag;
	}

	public QueryParams getQueryParams() {
		return queryParams;
	}

	public String getToken() {
		return token;
	}

	public static class Builder {
		private String name;
		private String node;
		private String service;
		private String tag;
		private QueryParams queryParams;
		private String token;

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setNode(String node) {
			this.node = node;
			return this;
		}

		public Builder setService(String service) {
			this.service = service;
			return this;
		}

		public Builder setTag(String tag) {
			this.tag = tag;
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

		public EventListRequest build() {
			return new EventListRequest(name, node, service, tag, queryParams, token);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@Override
	public List<UrlParameters> asUrlParameters() {
		List<UrlParameters> params = new ArrayList<>();

		if (name != null) {
			params.add(new SingleUrlParameters("name", name));
		}

		if (node != null) {
			params.add(new SingleUrlParameters("node", node));
		}

		if (service != null) {
			params.add(new SingleUrlParameters("service", service));
		}

		if (tag != null) {
			params.add(new SingleUrlParameters("tag", tag));
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
