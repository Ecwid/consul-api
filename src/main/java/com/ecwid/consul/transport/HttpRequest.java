package com.ecwid.consul.transport;

import java.util.HashMap;
import java.util.Map;

public final class HttpRequest {

	private final String url;
	private final Map<String, String> headers;

	private final String content;
	private final byte[] binaryContent;

	private HttpRequest(String url, Map<String, String> headers, String content, byte[] binaryContent) {
		if (content != null && binaryContent != null) {
			throw new IllegalArgumentException("You should set only content or binaryContent, not both.");
		}

		this.url = url;
		this.headers = headers;
		this.content = content;
		this.binaryContent = binaryContent;
	}

	public String getUrl() {
		return url;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public String getContent() {
		return content;
	}

	public byte[] getBinaryContent() {
		return binaryContent;
	}

	// ---------------------------------------
	// Builder
	public static final class Builder {
		private String url;
		private Map<String, String> headers = new HashMap<>();
		private String content;
		private byte[] binaryContent;

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder addHeaders(Map<String, String> headers) {
			this.headers.putAll(headers);
			return this;
		}

		public Builder addHeader(String name, String value) {
			this.headers.put(name, value);
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

		public HttpRequest build() {
			return new HttpRequest(url, headers, content, binaryContent);
		}
	}

}
