package com.ecwid.consul.v1.connect.intentions;

import java.util.Objects;
import java.util.StringJoiner;

public class IntentionListRequest {

    private String filter = "";

    private String ns = "";

    public IntentionListRequest() {
    }

    public IntentionListRequest(final String filter) {
        this.filter = filter;
    }

    public IntentionListRequest(final String filter, final String ns) {
        this.filter = filter;
        this.ns = ns;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(final String filter) {
        this.filter = filter;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(final String ns) {
        this.ns = ns;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final IntentionListRequest that = (IntentionListRequest) o;
        return Objects.equals(filter, that.filter)
                && Objects.equals(ns, that.ns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filter, ns);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IntentionListRequest.class.getSimpleName() + "[", "]")
                .add("filter='" + filter + "'")
                .add("ns='" + ns + "'")
                .toString();
    }

}
