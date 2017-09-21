package com.ecwid.consul.transport;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Default HTTP client This class is thread safe
 *
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class DefaultHttpTransport extends AbstractHttpTransport {

	private final HttpClient httpClient;

	public DefaultHttpTransport() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(DEFAULT_MAX_CONNECTIONS);
		connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE_CONNECTIONS);

		RequestConfig requestConfig = RequestConfig.custom().
				setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT).
				setConnectionRequestTimeout(DEFAULT_CONNECTION_TIMEOUT).
				setSocketTimeout(DEFAULT_READ_TIMEOUT).
				build();

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().
				setConnectionManager(connectionManager).
				setDefaultRequestConfig(requestConfig).
				useSystemProperties();

		this.httpClient = httpClientBuilder.build();
	}

	public DefaultHttpTransport(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	protected HttpClient getHttpClient() {
		return httpClient;
	}
}
