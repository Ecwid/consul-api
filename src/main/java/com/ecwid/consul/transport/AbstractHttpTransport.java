package com.ecwid.consul.transport;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

public abstract class AbstractHttpTransport implements HttpTransport {

	private static final Logger log = Logger.getLogger(AbstractHttpTransport.class.getName());

	static final int DEFAULT_MAX_CONNECTIONS = 1000;
	static final int DEFAULT_MAX_PER_ROUTE_CONNECTIONS = 500;
	static final int DEFAULT_CONNECTION_TIMEOUT = 10 * 1000; // 10 sec

	// 10 minutes for read timeout due to blocking queries timeout
	// https://www.consul.io/api/index.html#blocking-queries
	static final int DEFAULT_READ_TIMEOUT = 1000 * 60 * 10; // 10 min

	@Override
	public HttpResponse makeGetRequest(HttpRequest request) {
		HttpGet httpGet = new HttpGet(request.getUrl());
		addHeadersToRequest(httpGet, request.getHeaders());

		return executeRequest(httpGet);
	}

	@Override
	public HttpResponse makePutRequest(HttpRequest request) {
		HttpPut httpPut = new HttpPut(request.getUrl());
		addHeadersToRequest(httpPut, request.getHeaders());
		if (request.getContent() != null) {
			httpPut.setEntity(new StringEntity(request.getContent(), StandardCharsets.UTF_8));
		} else {
			httpPut.setEntity(new ByteArrayEntity(request.getBinaryContent()));
		}

		return executeRequest(httpPut);
	}

	@Override
	public HttpResponse makeDeleteRequest(HttpRequest request) {
		HttpDelete httpDelete = new HttpDelete(request.getUrl());
		addHeadersToRequest(httpDelete, request.getHeaders());
		return executeRequest(httpDelete);
	}

	/**
	 * You should override this method to instantiate ready to use HttpClient
	 *
	 * @return HttpClient
	 */
	protected abstract HttpClient getHttpClient();

	private HttpResponse executeRequest(HttpUriRequest httpRequest) {
		logRequest(httpRequest);

		try {
			return getHttpClient().execute(httpRequest, response -> {
				int statusCode = response.getStatusLine().getStatusCode();
				String statusMessage = response.getStatusLine().getReasonPhrase();

				String content = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

				Long consulIndex = parseUnsignedLong(response.getFirstHeader("X-Consul-Index"));
				Boolean consulKnownLeader = parseBoolean(response.getFirstHeader("X-Consul-Knownleader"));
				Long consulLastContact = parseUnsignedLong(response.getFirstHeader("X-Consul-Lastcontact"));

				return new HttpResponse(statusCode, statusMessage, content, consulIndex, consulKnownLeader, consulLastContact);
			});
		} catch (IOException e) {
			throw new TransportException(e);
		}
	}

	private Long parseUnsignedLong(Header header) {
		if (header == null) {
			return null;
		}

		String value = header.getValue();
		if (value == null) {
			return null;
		}

		try {
			return Long.parseUnsignedLong(value);
		} catch (Exception e) {
			return null;
		}
	}

	private Boolean parseBoolean(Header header) {
		if (header == null) {
			return null;
		}

		if ("true".equals(header.getValue())) {
			return true;
		}

		if ("false".equals(header.getValue())) {
			return false;
		}

		return null;
	}

	private void addHeadersToRequest(HttpRequestBase request, Map<String, String> headers) {
		if (headers == null) {
			return;
		}

		for (Map.Entry<String, String> headerValue : headers.entrySet()) {
			String name = headerValue.getKey();
			String value = headerValue.getValue();

			request.addHeader(name, value);
		}
	}

	private void logRequest(HttpUriRequest httpRequest) {
		StringBuilder sb = new StringBuilder();

		// method
		sb.append(httpRequest.getMethod());
		sb.append(" ");

		// url
		sb.append(httpRequest.getURI());
		sb.append(" ");

		// headers, if any
		HeaderIterator iterator = httpRequest.headerIterator();
		if (iterator.hasNext()) {
			sb.append("Headers:[");

			Header header = iterator.nextHeader();
			sb.append(header.getName()).append("=").append(header.getValue());

			while (iterator.hasNext()) {
				header = iterator.nextHeader();
				sb.append(header.getName()).append("=").append(header.getValue());
				sb.append(";");
			}

			sb.append("] ");
		}

		//
		log.finest(sb.toString());
	}

}
