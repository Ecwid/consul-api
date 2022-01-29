package com.ecwid.consul.v1.connect.intentions.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class IntentionHttpPermission {

    @SerializedName("PathExact")
    private String pathExact;

    @SerializedName("PathPrefix")
    private String pathPrefix;

    @SerializedName("PathRegex")
    private String pathRegex;

    @SerializedName("Methods")
    private List<String> methods = new ArrayList<>();

    @SerializedName("Header")
    private List<IntentionHttpHeaderPermission> header = new ArrayList<>();

    public String getPathExact() {
        return pathExact;
    }

    public void setPathExact(final String pathExact) {
        this.pathExact = pathExact;
    }

    public String getPathPrefix() {
        return pathPrefix;
    }

    public void setPathPrefix(final String pathPrefix) {
        this.pathPrefix = pathPrefix;
    }

    public String getPathRegex() {
        return pathRegex;
    }

    public void setPathRegex(final String pathRegex) {
        this.pathRegex = pathRegex;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(final List<String> methods) {
        this.methods = methods;
    }

    public List<IntentionHttpHeaderPermission> getHeader() {
        return header;
    }

    public void setHeader(final List<IntentionHttpHeaderPermission> header) {
        this.header = header;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final IntentionHttpPermission that = (IntentionHttpPermission) o;
        return Objects.equals(pathExact, that.pathExact)
                && Objects.equals(pathPrefix, that.pathPrefix)
                && Objects.equals(pathRegex, that.pathRegex)
                && Objects.equals(methods, that.methods)
                && Objects.equals(header, that.header);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathExact, pathPrefix, pathRegex, methods, header);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IntentionHttpPermission.class.getSimpleName() + "[", "]")
                .add("pathExact='" + pathExact + "'")
                .add("pathPrefix='" + pathPrefix + "'")
                .add("pathRegex='" + pathRegex + "'")
                .add("methods=" + methods)
                .add("header=" + header)
                .toString();
    }

}
