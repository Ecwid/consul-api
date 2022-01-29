package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class CaRoots {

	@SerializedName("ActiveRootID")
	private String activeRootId;

	@SerializedName("Roots")
	private List<CaRoot> roots = new ArrayList<>();

	public String getActiveRootId() {
		return activeRootId;
	}

	public void setActiveRootId(final String activeRootId) {
		this.activeRootId = activeRootId;
	}

	public List<CaRoot> getRoots() {
		return roots;
	}

	public void setRoots(final List<CaRoot> roots) {
		this.roots = roots;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final CaRoots caRoots = (CaRoots) o;
		return Objects.equals(activeRootId, caRoots.activeRootId) && Objects.equals(roots, caRoots.roots);
	}

	@Override
	public int hashCode() {
		return Objects.hash(activeRootId, roots);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CaRoots.class.getSimpleName() + "[", "]")
				.add("activeRootId='" + activeRootId + "'")
				.add("roots=" + roots)
				.toString();
	}

}
