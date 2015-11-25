package com.ecwid.consul.v1;

import org.apache.http.client.HttpClient;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.Utils;
import com.ecwid.consul.transport.DefaultHttpTransport;
import com.ecwid.consul.transport.DefaultHttpsTransport;
import com.ecwid.consul.transport.HttpTransport;
import com.ecwid.consul.transport.RawResponse;
import com.ecwid.consul.transport.TLSConfig;

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

	public ConsulRawClient(TLSConfig tlsConfig) {
		this(DEFAULT_HOST, tlsConfig);
	}

	public ConsulRawClient(String agentHost) {
		this(agentHost, DEFAULT_PORT);
	}

	public ConsulRawClient(String agentHost, TLSConfig tlsConfig) {
		this(agentHost, DEFAULT_PORT, tlsConfig);
	}

	public ConsulRawClient(String agentHost, int agentPort) {
		this(DEFAULT_HTTP_TRANSPORT, agentHost, agentPort);
	}

	public ConsulRawClient(HttpClient httpClient) {
		this(DEFAULT_HOST, httpClient);
	}

	public ConsulRawClient(String agentHost, HttpClient httpClient) {
		this(new DefaultHttpTransport(httpClient), agentHost, DEFAULT_PORT);
	}

	public ConsulRawClient(String agentHost, int agentPort, HttpClient httpClient) {
		this(new DefaultHttpTransport(httpClient), agentHost, agentPort);
	}

	public ConsulRawClient(String agentHost, int agentPort, TLSConfig tlsConfig) {
		this(new DefaultHttpsTransport(tlsConfig), agentHost, agentPort);
	}

	// hidden constructor, for tests
	ConsulRawClient(HttpTransport httpTransport, String agentHost, int agentPort) {
		this.httpTransport = httpTransport;

		// check that agentHost has scheme or not
		String agentHostLowercase = agentHost.toLowerCase();
		if (!agentHostLowercase.startsWith("https://") && !agentHostLowercase.startsWith("http://")) {
			// no scheme in host, use default 'http'
			agentHost = "http://" + agentHost;
		}

		this.agentAddress = agentHost + ":" + agentPort;
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
