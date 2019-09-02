package com.ecwid.consul.v1;

import com.ecwid.consul.ConsulException;
import com.ecwid.consul.transport.HttpResponse;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class OperationException extends ConsulException {

	private final int statusCode;
	private final String statusMessage;
	private final String statusContent;

	public OperationException(int statusCode, String statusMessage, String statusContent) {
		super("OperationException(statusCode=" + statusCode + ", statusMessage='" + statusMessage + "', statusContent='" + statusContent + "')");
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.statusContent = statusContent;
	}

	public OperationException(HttpResponse httpResponse) {
		this(httpResponse.getStatusCode(), httpResponse.getStatusMessage(), httpResponse.getContent());
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public String getStatusContent() {
		return statusContent;
	}

	@Override
	public String toString() {
		return "OperationException{" +
				"statusCode=" + statusCode +
				", statusMessage='" + statusMessage + '\'' +
				", statusContent='" + statusContent + '\'' +
				'}';
	}
}
