package com.ecwid.consul.v1;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.Utils;
import com.ecwid.consul.transport.*;
import org.apache.http.client.HttpClient;

import java.util.Arrays;
import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class ConsulRawClient {

	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 8500;
	public static final String DEFAULT_PATH = "";

	// one real HTTP client for all instances
	private static final HttpTransport DEFAULT_HTTP_TRANSPORT = new DefaultHttpTransport();

	private final HttpTransport httpTransport;
	private final String agentAddress;

	public static final class Builder {
		private String agentHost;
		private int agentPort;
		private String agentPath;
		private HttpTransport httpTransport;

		public static ConsulRawClient.Builder builder() {
			return new ConsulRawClient.Builder();
		}

		private Builder() {
			this.agentHost = DEFAULT_HOST;
			this.agentPort = DEFAULT_PORT;
			this.agentPath = DEFAULT_PATH;
			this.httpTransport = DEFAULT_HTTP_TRANSPORT;
		}

		public Builder setHost(String host) {
			this.agentHost = host;
			return this;
		}

		public Builder setPort(int port) {
			this.agentPort = port;
			return this;
		}

		public Builder setPath(String path) {
			this.agentPath = path;
			return this;
		}

		public Builder setTlsConfig(TLSConfig tlsConfig) {
			this.httpTransport = new DefaultHttpsTransport(tlsConfig);
			return this;
		}

		public Builder setHttpClient(HttpClient httpClient) {
			this.httpTransport = new DefaultHttpTransport(httpClient);
			return this;
		}

		public ConsulRawClient build() {
			return new ConsulRawClient(httpTransport, agentHost, agentPort, agentPath);
		}
	}

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
		this(DEFAULT_HTTP_TRANSPORT, agentHost, agentPort, DEFAULT_PATH);
	}

	public ConsulRawClient(HttpClient httpClient) {
		this(DEFAULT_HOST, httpClient);
	}

	public ConsulRawClient(String agentHost, HttpClient httpClient) {
		this(new DefaultHttpTransport(httpClient), agentHost, DEFAULT_PORT, DEFAULT_PATH);
	}

	public ConsulRawClient(String agentHost, int agentPort, HttpClient httpClient) {
		this(new DefaultHttpTransport(httpClient), agentHost, agentPort, DEFAULT_PATH);
	}

	public ConsulRawClient(String agentHost, int agentPort, TLSConfig tlsConfig) {
		this(new DefaultHttpsTransport(tlsConfig), agentHost, agentPort, DEFAULT_PATH);
	}

	public ConsulRawClient(HttpClient httpClient, String host, int port, String path) {
		this(new DefaultHttpTransport(httpClient), host, port, path);
	}

	// hidden constructor, for tests
	ConsulRawClient(HttpTransport httpTransport, String agentHost, int agentPort, String path) {
		this.httpTransport = httpTransport;

		// check that agentHost has scheme or not
		String agentHostLowercase = agentHost.toLowerCase();
		if (!agentHostLowercase.startsWith("https://") && !agentHostLowercase.startsWith("http://")) {
			// no scheme in host, use default 'http'
			agentHost = "http://" + agentHost;
		}

		this.agentAddress = Utils.assembleAgentAddress(agentHost, agentPort, path);
	}

	public HttpResponse makeGetRequest(String endpoint, UrlParameters... urlParams) {
		return makeGetRequest(endpoint, Arrays.asList(urlParams));
	}

	public HttpResponse makeGetRequest(String endpoint, List<UrlParameters> urlParams) {
		String url = prepareUrl(agentAddress + endpoint);
		url = Utils.generateUrl(url, urlParams);

		HttpRequest request = HttpRequest.Builder.newBuilder()
			.setUrl(url)
			.build();

		return httpTransport.makeGetRequest(request);
	}

	public HttpResponse makeGetRequest(Request request) {
		String url = prepareUrl(agentAddress + request.getEndpoint());
		url = Utils.generateUrl(url, request.getUrlParameters());

		HttpRequest httpRequest = HttpRequest.Builder.newBuilder()
			.setUrl(url)
			.addHeaders(Utils.createTokenMap(request.getToken()))
			.build();

		return httpTransport.makeGetRequest(httpRequest);
	}

	public HttpResponse makePutRequest(String endpoint, String content, UrlParameters... urlParams) {
		String url = prepareUrl(agentAddress + endpoint);
		url = Utils.generateUrl(url, urlParams);

		HttpRequest request = HttpRequest.Builder.newBuilder()
			.setUrl(url)
			.setContent(content)
			.build();

		return httpTransport.makePutRequest(request);
	}

	public HttpResponse makePutRequest(Request request) {
		//,  String endpoint, byte[] binaryContent, UrlParameters... urlParams
		String url = prepareUrl(agentAddress + request.getEndpoint());
		url = Utils.generateUrl(url, request.getUrlParameters());

		HttpRequest httpRequest = HttpRequest.Builder.newBuilder()
			.setUrl(url)
			.setContent(request.getContent()) // if content is not null then it has priority over binaryContent
			.setBinaryContent(request.getBinaryContent())
			.addHeaders(Utils.createTokenMap(request.getToken()))
			.build();

		return httpTransport.makePutRequest(httpRequest);
	}

	public HttpResponse makeDeleteRequest(Request request) {
		String url = prepareUrl(agentAddress + request.getEndpoint());
		url = Utils.generateUrl(url, request.getUrlParameters());

		HttpRequest httpRequest = HttpRequest.Builder.newBuilder()
			.setUrl(url)
			.addHeaders(Utils.createTokenMap(request.getToken()))
			.build();

		return httpTransport.makeDeleteRequest(httpRequest);
	}

	private String prepareUrl(String url) {
		if (url.contains(" ")) {
			// temp hack for old clients who did manual encoding and just use %20
			// TODO: Remove it in 2.0
			return Utils.encodeUrl(url);
		} else {
			return url;
		}
	}

}
