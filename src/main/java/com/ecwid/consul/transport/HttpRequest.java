package com.ecwid.consul.transport;

import java.util.Map;

public final class HttpRequest {

	private final String url;
	private final Map<String, String> headers;

	private final String content;
	private final byte[] binaryContent;

	public HttpRequest(String url) {
		this.url = url;
		this.headers = null;
		this.content = null;
		this.binaryContent = null;
	}

	public HttpRequest(String url, Map<String, String> headers) {
		this.url = url;
		this.headers = headers;
		this.content = null;
		this.binaryContent = null;
	}

	public HttpRequest(String url, Map<String, String> headers, String content) {
		this.url = url;
		this.headers = headers;
		this.content = content;
		this.binaryContent = null;
	}

	public HttpRequest(String url, Map<String, String> headers, byte[] binaryContent) {
		this.url = url;
		this.headers = headers;
		this.content = null;
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
}
