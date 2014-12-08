package com.ecwid.consul;

import java.util.Collections;
import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class SingleUrlParameters implements UrlParameters {

	private final String key;
	private final String value;

	public SingleUrlParameters(String key) {
		this.key = key;
		this.value = null;
	}

	public SingleUrlParameters(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public List<String> toUrlParameters() {
		if (value != null) {
			return Collections.singletonList(key + "=" + Utils.encodeValue(value));
		} else {
			return Collections.singletonList(key);
		}
	}
}
