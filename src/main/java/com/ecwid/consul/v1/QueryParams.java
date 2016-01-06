package com.ecwid.consul.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.Utils;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class QueryParams implements UrlParameters {

	public static final QueryParams DEFAULT = new QueryParams(ConsistencyMode.DEFAULT);

	private final String datacenter;
	private final ConsistencyMode consistencyMode;

	private final long waitTime;
	private final long index;

	/**
	 * Use the builder to create QueryParams
	 */
	public static class QueryParamsBuilder {
		/**
		 * Constructor
		 */
		public QueryParamsBuilder() {
		}

		private long waitTimeInSeconds = -1;
		private long index = -1;
		private String datacenter = null;
		private ConsistencyMode consistencyMode;

		/**
		 * Use a specific datacenter instead of the one of the connected Consul
		 * Agent
		 *
		 * @param datacenter
		 *            the specific datacenter to query
		 * @return the builder itself
		 */
		public QueryParamsBuilder usingDataCenter(String datacenter) {
			this.datacenter = datacenter;
			return this;
		}

		/**
		 * Specify a different ConsistencyMode than ConsistencyMode.DEFAULT
		 *
		 * @param consistencyMode
		 *            the consistency mode to override
		 * @return the builder itself
		 */
		public QueryParamsBuilder withConsistencyMode(ConsistencyMode consistencyMode) {
			this.consistencyMode = consistencyMode;
			return this;
		}

		/**
		 * Set the query in blocking mode. Values for timeout are -1 (do not
		 * block) or between 0 and 10 minutes.
		 *
		 * @param indexToWaitFor
		 *            the Consul index to wait for until change occurs
		 * @param timeout
		 *            the timeout to wait for
		 * @param unit
		 *            the TimeUnit
		 * @return the builder itself
		 * @throws IllegalArgumentException
		 *             if timeout values are out of Range
		 */
		public QueryParamsBuilder blockUntilValueChanges(long indexToWaitFor, long timeout, TimeUnit unit) {
			if (timeout < 0) {
				if (timeout != -1) {
					throw new IllegalArgumentException("timeout can be -1 or a positive value");
				}
				this.waitTimeInSeconds = timeout;
			} else {
				final long valueInSecs = unit.toSeconds(timeout);
				if (valueInSecs > TimeUnit.MINUTES.toSeconds(10)) {
					throw new IllegalArgumentException(String.format(
							"Timeout must be lower or equal than 10 minutes, but was: %d seconds", valueInSecs));
				}
				this.waitTimeInSeconds = valueInSecs;
			}
			this.index = indexToWaitFor;
			return this;
		}

		/**
		 * Use this method to build your query params
		 *
		 * @return the QueryParams
		 */
		public QueryParams build() {
			return new QueryParams(datacenter, consistencyMode, waitTimeInSeconds, index);
		}
	}

	/**
	 * Private Constructor used by builder
	 * @param datacenter use another datacenter than the default
	 * @param consistencyMode use a specific consistencyMode
	 * @param waitTimeInSeconds time to wait for Consul
	 * @param index the X-Consul-Index to wait for
	 */
	private QueryParams(String datacenter, ConsistencyMode consistencyMode, long waitTimeInSeconds, long index) {
		this.datacenter = datacenter;
		this.consistencyMode = consistencyMode;
		this.waitTime = waitTimeInSeconds;
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
