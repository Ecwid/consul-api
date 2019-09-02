package com.ecwid.consul.transport;

import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface HttpTransport {

	public RawResponse makeGetRequest(String url, Map<String, String> headers);

	public RawResponse makePutRequest(String url, String content, Map<String, String> headers);

	public RawResponse makePutRequest(String url, byte[] content, Map<String, String> headers);

	public RawResponse makeDeleteRequest(String url, Map<String, String> headers);

}
