package com.ecwid.consul.v1;

import com.ecwid.consul.UrlParameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TagsParameters implements UrlParameters {

	private final String[] tags;

	public TagsParameters(String[] tags) {
		this.tags = tags;
	}

	@Override
	public List<String> toUrlParameters() {
		List<String> params = new ArrayList<>();

		if (tags != null) {
			for (String tag : tags) {
				if (tag != null) {
					params.add("tag=" + tag);
				}
			}
		}

		return params;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TagsParameters)) {
			return false;
		}
		TagsParameters that = (TagsParameters) o;
		return Arrays.equals(tags, that.tags);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(tags);
	}
}
