package com.ecwid.consul.transport;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface HttpTransport {

	public HttpResponse makeGetRequest(HttpRequest request);

	public HttpResponse makePutRequest(HttpRequest request);

	public HttpResponse makeDeleteRequest(HttpRequest request);

	public HttpResponse makePostRequest(HttpRequest request);

}
