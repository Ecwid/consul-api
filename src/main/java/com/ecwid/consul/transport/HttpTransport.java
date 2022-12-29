package com.ecwid.consul.transport;


/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface HttpTransport {

	HttpResponse makeGetRequest(HttpRequest request);

	HttpResponse makePutRequest(HttpRequest request);

	HttpResponse makeDeleteRequest(HttpRequest request);

}
