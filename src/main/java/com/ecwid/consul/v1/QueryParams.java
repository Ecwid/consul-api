package com.ecwid.consul.v1;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class QueryParams implements UrlParameters {

	public static final QueryParams DEFAULT = new QueryParams(ConsistencyMode.DEFAULT);

	private final String datacenter;
	private final ConsistencyMode consistencyMode;

	private final long waitTime;
	private final long index;

	public QueryParams(String datacenter) {
		this.datacenter = datacenter;
		this.consistencyMode = ConsistencyMode.DEFAULT;
		this.waitTime = -1;
		this.index = -1;
	}

	public QueryParams(ConsistencyMode consistencyMode) {
		this.datacenter = null;
		this.consistencyMode = consistencyMode;
		this.waitTime = -1;
		this.index = -1;
	}

	public QueryParams(String datacenter, ConsistencyMode consistencyMode) {
		this.datacenter = datacenter;
		this.consistencyMode = consistencyMode;
		this.waitTime = -1;
		this.index = -1;
	}

	public QueryParams(long waitTime, long index) {
		this.datacenter = null;
		this.consistencyMode = ConsistencyMode.DEFAULT;
		this.waitTime = waitTime;
		this.index = index;
	}

	public String getDatacenter() {
		return datacenter;
	}

	public ConsistencyMode getConsistencyMode() {
		return consistencyMode;
	}

	public long getWaitTime() {
		return waitTime;
	}

	public long getIndex() {
		return index;
	}

	@Override
	public List<String> toUrlParameters() {
		List<String> params = new ArrayList<String>();

		// add basic params
		if (datacenter != null) {
			params.add("dc=" + Utils.encodeValue(datacenter));
		}

		if (consistencyMode != ConsistencyMode.DEFAULT) {
			params.add(consistencyMode.name().toLowerCase());
		}

		if (waitTime != -1) {
			params.add("wait=" + waitTime + "s");
		}
		if (index != -1) {
			params.add("index=" + index);
		}

		return params;
	}
}
