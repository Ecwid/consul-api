package com.ecwid.consul.v1.event.model;

import com.ecwid.consul.UrlParameters;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class EventParams implements UrlParameters {

	private String name;
	private String service;
	private String tag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public List<String> toUrlParameters() {
		List<String> result = new ArrayList<String>();

		if (name != null) {
			result.add("name=" + name);
		}

		if (service != null) {
			result.add("service=" + service);
		}

		if (tag != null) {
			result.add("tag=" + tag);
		}

		return result;
	}
}
