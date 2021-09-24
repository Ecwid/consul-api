package com.ecwid.consul.v1.connect.intentions.model;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import java.util.StringJoiner;

public class IntentionHttpHeaderPermission {

    @SerializedName("Name")
    private String name;

    @SerializedName("Present")
    private boolean present;

    @SerializedName("Exact")
    private String exact;

    @SerializedName("Prefix")
    private String prefix;

    @SerializedName("Suffix")
    private String suffix;

    @SerializedName("Regex")
    private String regex;

    @SerializedName("Invert")
    private boolean invert;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(final boolean present) {
        this.present = present;
    }

    public String getExact() {
        return exact;
    }

    public void setExact(final String exact) {
        this.exact = exact;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(final String suffix) {
        this.suffix = suffix;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(final String regex) {
        this.regex = regex;
    }

    public boolean isInvert() {
        return invert;
    }

    public void setInvert(final boolean invert) {
        this.invert = invert;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final IntentionHttpHeaderPermission that = (IntentionHttpHeaderPermission) o;
        return present == that.present
                && invert == that.invert
                && Objects.equals(name, that.name)
                && Objects.equals(exact, that.exact)
                && Objects.equals(prefix, that.prefix)
                && Objects.equals(suffix, that.suffix)
                && Objects.equals(regex, that.regex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, present, exact, prefix, suffix, regex, invert);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IntentionHttpHeaderPermission.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("present=" + present)
                .add("exact='" + exact + "'")
                .add("prefix='" + prefix + "'")
                .add("suffix='" + suffix + "'")
                .add("regex='" + regex + "'")
                .add("invert=" + invert)
                .toString();
    }

}
