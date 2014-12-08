package com.ecwid.consul.v1.kv.model;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.Utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class PutParams implements UrlParameters {

	private long flags;
	private Long cas;
	private String acquireSession;
	private String releaseSession;

	public long getFlags() {
		return flags;
	}

	public void setFlags(long flags) {
		this.flags = flags;
	}

	public Long getCas() {
		return cas;
	}

	public void setCas(Long cas) {
		this.cas = cas;
	}

	public String getAcquireSession() {
		return acquireSession;
	}

	public void setAcquireSession(String acquireSession) {
		this.acquireSession = acquireSession;
	}

	public String getReleaseSession() {
		return releaseSession;
	}

	public void setReleaseSession(String releaseSession) {
		this.releaseSession = releaseSession;
	}

	@Override
	public List<String> toUrlParameters() {
		List<String> params = new ArrayList<String>();

		if (flags != 0) {
			params.add("flags=" + flags);
		}
		if (cas != null) {
			params.add("cas=" + cas);
		}
		if (acquireSession != null) {
			params.add("acquire=" + Utils.encodeValue(acquireSession));
		}
		if (releaseSession != null) {
			params.add("release=" + Utils.encodeValue(releaseSession));
		}

		return params;
	}
}
