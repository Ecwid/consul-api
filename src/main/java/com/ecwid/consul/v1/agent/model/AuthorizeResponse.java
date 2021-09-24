package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import java.util.StringJoiner;

public class AuthorizeResponse {

	@SerializedName("Authorized")
	private boolean authorized;

	@SerializedName("Reason")
	public String reason;

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(final boolean authorized) {
		this.authorized = authorized;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final AuthorizeResponse that = (AuthorizeResponse) o;
		return authorized == that.authorized && Objects.equals(reason, that.reason);
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorized, reason);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", AuthorizeResponse.class.getSimpleName() + "[", "]")
				.add("authorized=" + authorized)
				.add("reason='" + reason + "'")
				.toString();
	}

}
