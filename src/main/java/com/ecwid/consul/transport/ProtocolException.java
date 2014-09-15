package com.ecwid.consul.transport;

import com.ecwid.consul.ConsulException;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class ProtocolException extends ConsulException {

	private final int statusCode;
	private final String statusMessage;

	public ProtocolException(int statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public ProtocolException(RawResponse rawResponse) {
		this(rawResponse.getStatusCode(), rawResponse.getStatusMessage());
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}
}
