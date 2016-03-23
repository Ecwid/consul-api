package com.ecwid.consul.transport;

import com.ecwid.consul.Utils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class AbstractHttpTransport implements HttpTransport {

	private static final int DEFAULT_CONNECTION_TIMEOUT = 10000; // 10 sec
	private static final int DEFAULT_READ_TIMEOUT = 60000; // 60 sec

	protected final HttpClient httpClient;

	public AbstractHttpTransport() {
		PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager();
		connectionManager.setMaxTotal(1000);
		connectionManager.setDefaultMaxPerRoute(500);

		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, DEFAULT_CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, DEFAULT_READ_TIMEOUT);

		this.httpClient = new DefaultHttpClient(connectionManager, httpParams);
	}

	public AbstractHttpTransport(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

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

	private RawResponse executeRequest(HttpUriRequest httpRequest) {
		try {
			return httpClient.execute(httpRequest, new ResponseHandler<RawResponse>() {
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