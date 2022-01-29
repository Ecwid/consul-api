package com.ecwid.consul.v1.connect.intentions;

import java.util.Objects;
import java.util.StringJoiner;

public class IntentionDeleteRequest {

    private String source;

    private String destination;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final IntentionDeleteRequest that = (IntentionDeleteRequest) o;
        return Objects.equals(source, that.source)
                && Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IntentionDeleteRequest.class.getSimpleName() + "[", "]")
                .add("source='" + source + "'")
                .add("destination='" + destination + "'")
                .toString();
    }

}
