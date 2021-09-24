package com.ecwid.consul.v1.connect.intentions.model;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import java.util.StringJoiner;

public class IntentionPermission {

    @SerializedName("Action")
    private String action;

    @SerializedName("HTTP")
    private IntentionHttpPermission http;

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public IntentionHttpPermission getHttp() {
        return http;
    }

    public void setHttp(final IntentionHttpPermission http) {
        this.http = http;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final IntentionPermission that = (IntentionPermission) o;
        return Objects.equals(action, that.action)
                && Objects.equals(http, that.http);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, http);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IntentionPermission.class.getSimpleName() + "[", "]")
                .add("action='" + action + "'")
                .add("http=" + http)
                .toString();
    }

}
