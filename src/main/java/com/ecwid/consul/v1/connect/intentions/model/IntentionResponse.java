package com.ecwid.consul.v1.connect.intentions.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import java.util.Objects;

public class IntentionResponse extends Intention {

    @SerializedName("SourceNS")
    private String sourceNs;

    @SerializedName("SourceName")
    private String sourceName;

    @SerializedName("DestinationNS")
    private String destinationNs;

    @SerializedName("DestinationName")
    private String destinationName;

    @SerializedName("Meta")
    private Map<String, String> meta;

    @SerializedName("Precedence")
    private int precedence;

    @SerializedName("CreateIndex")
    private int createIndex;

    @SerializedName("ModifyIndex")
    private int modifyIndex;

    public String getSourceNs() {
        return sourceNs;
    }

    public void setSourceNs(final String sourceNs) {
        this.sourceNs = sourceNs;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(final String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationNs() {
        return destinationNs;
    }

    public void setDestinationNs(final String destinationNs) {
        this.destinationNs = destinationNs;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(final String destinationName) {
        this.destinationName = destinationName;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public void setMeta(final Map<String, String> meta) {
        this.meta = meta;
    }

    public int getPrecedence() {
        return precedence;
    }

    public void setPrecedence(final int precedence) {
        this.precedence = precedence;
    }

    public int getCreateIndex() {
        return createIndex;
    }

    public void setCreateIndex(final int createIndex) {
        this.createIndex = createIndex;
    }

    public int getModifyIndex() {
        return modifyIndex;
    }

    public void setModifyIndex(final int modifyIndex) {
        this.modifyIndex = modifyIndex;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final IntentionResponse that = (IntentionResponse) o;
        return precedence == that.precedence
                && createIndex == that.createIndex
                && modifyIndex == that.modifyIndex
                && Objects.equals(sourceNs, that.sourceNs)
                && Objects.equals(sourceName, that.sourceName)
                && Objects.equals(destinationNs, that.destinationNs)
                && Objects.equals(destinationName, that.destinationName)
                && Objects.equals(meta, that.meta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),
                sourceNs, sourceName, destinationNs, destinationName,
                meta, precedence, createIndex, modifyIndex);
    }

    @Override
    public String toString() {
        return "IntentionResponse{" +
                "sourceNs='" + sourceNs + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", destinationNs='" + destinationNs + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", meta=" + meta +
                ", precedence=" + precedence +
                ", createIndex=" + createIndex +
                ", modifyIndex=" + modifyIndex +
                "} " + super.toString();
    }

}
