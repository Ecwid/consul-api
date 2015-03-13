package com.ecwid.consul.v1;

import com.ecwid.consul.ConsulException;
import com.ecwid.consul.transport.RawResponse;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class OperationException extends ConsulException {

	private final int statusCode;
	private final String statusMessage;

	public OperationException(int statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public OperationException(RawResponse rawResponse) {
		this(rawResponse.getStatusCode(), rawResponse.getStatusMessage());
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OperationException{");
        sb.append("statusCode=").append(statusCode);
        sb.append(", statusMessage='").append(statusMessage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
