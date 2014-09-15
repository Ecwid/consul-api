package com.ecwid.consul.transport;

import com.ecwid.consul.ConsulException;

import java.io.IOException;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class TransportException extends ConsulException {

	public TransportException(Throwable cause) {
		super(cause);
	}

}
