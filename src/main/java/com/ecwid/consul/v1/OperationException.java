package com.ecwid.consul.v1;

import com.ecwid.consul.ConsulException;
import com.ecwid.consul.transport.RawResponse;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class OperationException extends ConsulException {

	private final int statusCode;
	private final String statusMessage;

	public OperationException(int statusCode, String statusMessage, String content) {
        super(content);
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public OperationException(RawResponse rawResponse) {
		this(rawResponse.getStatusCode(), rawResponse.getStatusMessage(), rawResponse.getContent());
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

    @Override
    public String toString() {
        return super.toString() + " {"
        		+ "statusCode=" + statusCode
        		+ ", statusMessage='" + statusMessage
         		+ "}";
    }

}
