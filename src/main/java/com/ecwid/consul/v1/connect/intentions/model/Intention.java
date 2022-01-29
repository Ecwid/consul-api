package com.ecwid.consul.v1.connect.intentions.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Intention {

    @SerializedName("SourceType")
    private String sourceType = "consul";

    @SerializedName("Action")
    private String action;

    @SerializedName("Permissions")
    private List<IntentionPermission> permissions = new ArrayList<>();

    @SerializedName("Description")
    private String description;

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(final String sourceType) {
        this.sourceType = sourceType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public List<IntentionPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(final List<IntentionPermission> permissions) {
        this.permissions = permissions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Intention intention = (Intention) o;
        return Objects.equals(sourceType, intention.sourceType)
                && Objects.equals(action, intention.action)
                && Objects.equals(permissions, intention.permissions)
                && Objects.equals(description, intention.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceType, action, permissions, description);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Intention.class.getSimpleName() + "[", "]")
                .add("sourceType='" + sourceType + "'")
                .add("action='" + action + "'")
                .add("permissions=" + permissions)
                .add("description='" + description + "'")
                .toString();
    }

}
