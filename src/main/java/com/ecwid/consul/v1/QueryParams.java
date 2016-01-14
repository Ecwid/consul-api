package com.ecwid.consul.v1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.Utils;

/**
 * Query parameters let you add specific parameters to Consul Queries
 * 
 * Use the {@link QueryParamsBuilder} to create a new {@link QueryParams} object
 * 
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class QueryParams implements UrlParameters {

    public static final QueryParams DEFAULT = new QueryParamsBuilder().build();

    /**
     * Specify a duration with its time unit
     */
    public static class TimeUnitWithValue {
        private final long value;
        private final TimeUnit unit;

        /**
         * Constructor
         * 
         * @param value
         * @param unit
         */
        public TimeUnitWithValue(long value, final TimeUnit unit) {
            if (unit == null && (value != 0 || value == -1)) {
                throw new IllegalArgumentException("TimeUnit must be defined for values != 0 or != -1");
            }
            this.unit = unit;
            this.value = value;
        }

        public long getValue() {
            return value;
        }

        public TimeUnit getUnit() {
            return unit;
        }

        /**
         * Displays a duration with its Go suffix as described here:
         * http://golang.org/src/time/format.go#L1143
         * 
         * @return a String value with correct suffix.
         */
        public String toGoString() {
            if (value <= 0) {
                return String.valueOf(value);
            } else {
                switch (unit) {
                case DAYS:
                    return value + "d";
                case HOURS:
                    return value + "h";
                case MINUTES:
                    return value + "m";
                case SECONDS:
                    return value + "s";
                case MILLISECONDS:
                    return value + "ms";
                case MICROSECONDS:
                    return value + "us"; //
                case NANOSECONDS:
                    return value + "ns";
                default:
                    throw new IllegalArgumentException("Unhandled timeunit " + unit);
                }
            }
        }
    }

    private final ConsistencyMode consistencyMode;

    private final TimeUnitWithValue waitTime;
    private final long index;

    private final Map<String, String> otherParameters;

    /**
     * Use the builder to create QueryParams
     */
    public static class QueryParamsBuilder {
        /**
         * Constructor
         */
        public QueryParamsBuilder() {
        }

        private TimeUnitWithValue waitTime = new TimeUnitWithValue(-1, TimeUnit.SECONDS);
        private long index = -1;
        private ConsistencyMode consistencyMode = ConsistencyMode.DEFAULT;
        private final HashMap<String, String> otherParameters = new HashMap<String, String>();

        /**
         * Use a specific datacenter instead of the one of the connected Consul
         * Agent
         *
         * @param datacenter
         *            the specific datacenter to query
         * @return the builder itself
         */
        public QueryParamsBuilder usingDataCenter(String datacenter) {
            return this.withNamedQueryParameter("dc", datacenter);
        }

        /**
         * Use a token to talk to Consul
         *
         * @param token
         *            a security token to use
         * @return the builder itself
         */
        public QueryParamsBuilder withToken(String token) {
            return this.withNamedQueryParameter("token", token);
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
                this.waitTime = new TimeUnitWithValue(timeout, unit);
            } else {
                final long valueInSecs = unit.toSeconds(timeout);
                if (valueInSecs > TimeUnit.MINUTES.toSeconds(10)) {
                    throw new IllegalArgumentException(String.format(
                            "Timeout must be lower or equal than 10 minutes, but was: %d seconds", valueInSecs));
                }
                this.waitTime = new TimeUnitWithValue(timeout, unit);
            }
            this.index = indexToWaitFor;
            return this;
        }

        /**
         * Add another query parameter
         * 
         * @param key
         *            the key to put
         * @param value
         *            the value to put
         * @return the builder
         */
        public QueryParamsBuilder withNamedQueryParameter(String key, String value) {
            otherParameters.put(key, value);
            return this;
        }

        /**
         * Get a copy of builder
         * 
         * @return another builder
         */
        public QueryParamsBuilder copy() {
            QueryParamsBuilder copy = new QueryParamsBuilder();
            copy.consistencyMode = consistencyMode;
            copy.index = index;
            // We can use same reference since immutable
            copy.waitTime = waitTime;
            copy.otherParameters.putAll(otherParameters);
            return copy;
        }

        /**
         * Use this method to build your query params
         *
         * @return the QueryParams
         */
        public QueryParams build() {
            return new QueryParams(consistencyMode, waitTime, index, otherParameters);
        }
    }

    /**
     * Private Constructor used by builder
     * 
     * @param datacenter
     *            use another datacenter than the default
     * @param consistencyMode
     *            use a specific consistencyMode
     * @param waitTimeInSeconds
     *            time to wait for Consul
     * @param index
     *            the X-Consul-Index to wait for
     * @param otherParameters
     *            other query parameters
     */
    private QueryParams(ConsistencyMode consistencyMode, TimeUnitWithValue waitTime, long index,
            Map<String, String> otherParameters) {
        this.consistencyMode = consistencyMode;
        this.waitTime = waitTime;
        this.index = index;
        this.otherParameters = otherParameters;
    }

    @Override
    public List<String> toUrlParameters() {
        List<String> params = new LinkedList<String>();
        for (Map.Entry<String, String> en : otherParameters.entrySet()) {
            params.add(Utils.encodeValue(en.getKey()) + "=" + Utils.encodeValue(en.getValue()));
        }
        // add basic params
        if (consistencyMode != ConsistencyMode.DEFAULT) {
            params.add(consistencyMode.name().toLowerCase());
        }
        if (waitTime != null && waitTime.getValue() >= 0) {
            params.add("wait=" + waitTime.toGoString());
        }
        if (index != -1) {
            params.add("index=" + index);
        }
        return params;
    }
}
