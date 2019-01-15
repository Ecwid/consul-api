package com.ecwid.consul.v1;

import com.ecwid.consul.UrlParameters;

import java.util.ArrayList;
import java.util.List;

public class TagsParameters implements UrlParameters {

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
}
