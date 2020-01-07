package com.ecwid.consul.v1.kv.model;

import com.ecwid.consul.UrlParameters;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Binswanger (kbinswanger@gmail.com)
 */
public class DeleteParams implements UrlParameters {

	private Long cas;

	public Long getCas() {
		return cas;
	}

	public void setCas(Long cas) {
		this.cas = cas;
	}

	@Override
	public List<String> toUrlParameters() {
		List<String> params = new ArrayList<String>();

		if (cas != null) {
			params.add("cas=" + cas);
		}

		return params;
	}
}
