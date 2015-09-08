package com.ecwid.consul.transport;

import org.apache.http.client.HttpClient;

/**
 * Default HTTP client This class is thread safe
 *
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class DefaultHttpTransport extends AbstractHttpTransport {

    public DefaultHttpTransport() {
	super();
    }

    public DefaultHttpTransport(HttpClient httpClient) {
	super(httpClient);
    }

}
