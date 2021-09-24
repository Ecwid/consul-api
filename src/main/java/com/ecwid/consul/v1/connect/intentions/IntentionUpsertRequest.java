package com.ecwid.consul.v1.connect.intentions;

import com.ecwid.consul.v1.connect.intentions.model.Intention;
import java.util.Objects;
import java.util.StringJoiner;

public class IntentionUpsertRequest {

    private String source;

    private String destination;

    private String ns = "";

    private Intention intention;

    public String getSource() {
        return source;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(final String ns) {
        this.ns = ns;
    }

    public Intention getIntention() {
        return intention;
    }

    public void setIntention(final Intention intention) {
        this.intention = intention;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final IntentionUpsertRequest that = (IntentionUpsertRequest) o;
        return Objects.equals(source, that.source)
                && Objects.equals(destination, that.destination)
                && Objects.equals(ns, that.ns)
                && Objects.equals(intention, that.intention);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, ns, intention);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IntentionUpsertRequest.class.getSimpleName() + "[", "]")
                .add("source='" + source + "'")
                .add("destination='" + destination + "'")
                .add("ns='" + ns + "'")
                .add("intention=" + intention)
                .toString();
    }

}
