package com.ecwid.consul.v1;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class QueryParams implements UrlParameters {
	public static final class Builder {
		public static Builder builder() {
			return new Builder();
		}

		private String datacenter;
		private ConsistencyMode consistencyMode;
		private long waitTime;
		private long index;

		private Builder() {
			this.datacenter = null;
			this.consistencyMode = ConsistencyMode.DEFAULT;
			this.waitTime = -1;
			this.index = -1;
		}

		public Builder setConsistencyMode(ConsistencyMode consistencyMode) {
			this.consistencyMode = consistencyMode;
			return this;
		}

		public Builder setDatacenter(String datacenter) {
			this.datacenter = datacenter;
			return this;
		}

		public Builder setWaitTime(long waitTime) {
			this.waitTime = waitTime;
			return this;
		}

		public Builder setIndex(long index) {
			this.index = index;
			return this;
		}

		public QueryParams build() {
			return new QueryParams(datacenter, consistencyMode, waitTime, index);
		}
	}

	public static final QueryParams DEFAULT = new QueryParams(ConsistencyMode.DEFAULT);

	private final String datacenter;
	private final ConsistencyMode consistencyMode;

	private final long waitTime;
	private final long index;

	private QueryParams(String datacenter, ConsistencyMode consistencyMode, long waitTime, long index) {
		this.datacenter = datacenter;
		this.consistencyMode = consistencyMode;
		this.waitTime = waitTime;
		this.index = index;
	}

	public QueryParams(String datacenter) {
		this(datacenter, ConsistencyMode.DEFAULT, -1, -1);
	}

	public QueryParams(ConsistencyMode consistencyMode) {
		this(null, consistencyMode, -1, -1);
	}

	public QueryParams(String datacenter, ConsistencyMode consistencyMode) {
		this(datacenter, consistencyMode, -1, -1);
	}

	public QueryParams(long waitTime, long index) {
		this(null, ConsistencyMode.DEFAULT, waitTime, index);
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
			params.add("index=" + Utils.toUnsignedString(index));
		}

		return params;
	}
}
