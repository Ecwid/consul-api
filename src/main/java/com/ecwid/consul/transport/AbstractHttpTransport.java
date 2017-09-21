package com.ecwid.consul.transport;

import com.ecwid.consul.Utils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public abstract class AbstractHttpTransport implements HttpTransport {

	static final int DEFAULT_MAX_CONNECTIONS = 1000;
	static final int DEFAULT_MAX_PER_ROUTE_CONNECTIONS = 500;
	static final int DEFAULT_CONNECTION_TIMEOUT = 10000; // 10 sec

	// 10 minutes for read timeout due to blocking queries timeout
	// https://www.consul.io/api/index.html#blocking-queries
	static final int DEFAULT_READ_TIMEOUT = 60000 * 10; // 10 min

	@Override
	public RawResponse makeGetRequest(String url) {
		HttpGet httpGet = new HttpGet(url);
		return executeRequest(httpGet);
	}

	@Override
	public RawResponse makePutRequest(String url, String content) {
		HttpPut httpPut = new HttpPut(url);
		httpPut.setEntity(new StringEntity(content, Charset.forName("UTF-8")));
		return executeRequest(httpPut);
	}

	@Override
	public RawResponse makePutRequest(String url, byte[] content) {
		HttpPut httpPut = new HttpPut(url);
		httpPut.setEntity(new ByteArrayEntity(content));
		return executeRequest(httpPut);
	}

	@Override
	public RawResponse makeDeleteRequest(String url) {
		HttpDelete httpDelete = new HttpDelete(url);
		return executeRequest(httpDelete);
	}

	protected abstract HttpClient getHttpClient();

	private RawResponse executeRequest(HttpUriRequest httpRequest) {
		try {
			return getHttpClient().execute(httpRequest, new ResponseHandler<RawResponse>() {
				@Override
				public RawResponse handleResponse(HttpResponse response) throws IOException {
					int statusCode = response.getStatusLine().getStatusCode();
					String statusMessage = response.getStatusLine().getReasonPhrase();

					String content = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));

					Long consulIndex = parseUnsignedLong(response.getFirstHeader("X-Consul-Index"));
					Boolean consulKnownLeader = parseBoolean(response.getFirstHeader("X-Consul-Knownleader"));
					Long consulLastContact = parseUnsignedLong(response.getFirstHeader("X-Consul-Lastcontact"));

					return new RawResponse(statusCode, statusMessage, content, consulIndex, consulKnownLeader, consulLastContact);
				}
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
			return Utils.parseUnsignedLong(value);
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

}