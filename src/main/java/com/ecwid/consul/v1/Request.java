package com.ecwid.consul.v1;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.transport.HttpRequest;

import java.util.ArrayList;
import java.util.List;

public final class Request {

	private final String endpoint;
	private final List<UrlParameters> urlParameters;

	private final String content;
	private final byte[] binaryContent;

	private final String token;

	private Request(String endpoint, List<UrlParameters> urlParameters, String content, byte[] binaryContent, String token) {
		if (content != null && binaryContent != null) {
			throw new IllegalArgumentException("You should set only content or binaryContent, not both.");
		}

		this.endpoint = endpoint;
		this.urlParameters = urlParameters;
		this.content = content;
		this.binaryContent = binaryContent;
		this.token = token;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public List<UrlParameters> getUrlParameters() {
		return urlParameters;
	}

	public String getContent() {
		return content;
	}

	public byte[] getBinaryContent() {
		return binaryContent;
	}

	public String getToken() {
		return token;
	}

	// -------------------------------
	// Builder
	public static class Builder {
		private String endpoint;
		private List<UrlParameters> urlParameters = new ArrayList<>();

		private String content;
		private byte[] binaryContent;

		private String token;

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder setEndpoint(String endpoint) {
			this.endpoint = endpoint;
			return this;
		}

		public Builder addUrlParameters(List<UrlParameters> urlParameters) {
			this.urlParameters.addAll(urlParameters);
			return this;
		}

		public Builder addUrlParameter(UrlParameters urlParameter) {
			this.urlParameters.add(urlParameter);
			return this;
		}

		public Builder setContent(String content) {
			this.content = content;
			return this;
		}

		public Builder setBinaryContent(byte[] binaryContent) {
			this.binaryContent = binaryContent;
			return this;
		}

		public Builder setToken(String token) {
			this.token = token;
			return this;
		}

		public Request build() {
			return new Request(endpoint, urlParameters, content, binaryContent, token);
		}
	}
}
