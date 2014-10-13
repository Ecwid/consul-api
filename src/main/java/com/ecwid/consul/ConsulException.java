package com.ecwid.consul;

/**
 * Base exception for any consul errors
 *
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class ConsulException extends RuntimeException {

	public ConsulException() {
	}

	public ConsulException(Throwable cause) {
		super(cause);
	}

	public ConsulException(String message) {
		super(message);
	}
}
