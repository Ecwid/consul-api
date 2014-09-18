package com.ecwid.consul.transport;

import java.io.InputStream;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface HttpTransport {

	public RawResponse makeGetRequest(String url);

	public RawResponse makePutRequest(String url, String content);

	public RawResponse makePutRequest(String url, byte[] content);

	public RawResponse makeDeleteRequest(String url);

}
