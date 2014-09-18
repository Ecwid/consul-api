package com.ecwid.consul.v1;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.Utils;
import com.ecwid.consul.transport.DefaultHttpTransport;
import com.ecwid.consul.transport.HttpTransport;
import com.ecwid.consul.transport.RawResponse;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class ConsulRawClient {

	private static final String DEFAULT_HOST = "localhost";
	private static final int DEFAULT_PORT = 8500;

	// one real HTTP client for all instances
	private static final HttpTransport DEFAULT_HTTP_TRANSPORT = new DefaultHttpTransport();

	private final HttpTransport httpTransport;
	private final String agentAddress;

	public ConsulRawClient() {
		this(DEFAULT_HOST);
	}

	public ConsulRawClient(String agentHost) {
		this(agentHost, DEFAULT_PORT);
	}

	public ConsulRawClient(String agentHost, int agentPort) {
		this(DEFAULT_HTTP_TRANSPORT, agentHost, agentPort);
	}

	// hidden constructor, for tests
	ConsulRawClient(HttpTransport httpTransport, String agentHost, int agentPort) {
		this.httpTransport = httpTransport;
		this.agentAddress = "http://" + agentHost + ":" + agentPort;
	}

	public RawResponse makeGetRequest(String endpoint, UrlParameters... urlParams) {
		String url = agentAddress + endpoint;
		url = Utils.generateUrl(url, urlParams);

		return httpTransport.makeGetRequest(url);
	}

	public RawResponse makePutRequest(String endpoint, String content, UrlParameters... urlParams) {
		String url = agentAddress + endpoint;
		url = Utils.generateUrl(url, urlParams);

		return httpTransport.makePutRequest(url, content);
	}

	public RawResponse makePutRequest(String endpoint, byte[] content, UrlParameters... urlParams) {
		String url = agentAddress + endpoint;
		url = Utils.generateUrl(url, urlParams);

		return httpTransport.makePutRequest(url, content);
	}

	public RawResponse makeDeleteRequest(String endpoint, UrlParameters... urlParams) {
		String url = agentAddress + endpoint;
		url = Utils.generateUrl(url, urlParams);

		return httpTransport.makeDeleteRequest(url);
	}

}
