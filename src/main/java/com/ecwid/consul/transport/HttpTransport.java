package com.ecwid.consul.transport;

import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface HttpTransport {

	public HttpResponse makeGetRequest(String url, Map<String, String> headers);

	public HttpResponse makePutRequest(String url, String content, Map<String, String> headers);

	public HttpResponse makePutRequest(String url, byte[] content, Map<String, String> headers);

	public HttpResponse makeDeleteRequest(String url, Map<String, String> headers);

}
