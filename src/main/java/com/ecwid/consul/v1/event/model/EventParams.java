package com.ecwid.consul.v1.event.model;

import java.util.ArrayList;
import java.util.List;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.Utils;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class EventParams implements UrlParameters {

	private String name;
	private String service;
	private String tag;
	private String node;

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

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	@Override
	public List<String> toUrlParameters() {
		List<String> result = new ArrayList<String>();

		if (name != null) {
			result.add("name=" + Utils.encodeValue(name));
		}

		if (service != null) {
			result.add("service=" + Utils.encodeValue(service));
		}

		if (tag != null) {
			result.add("tag=" + Utils.encodeValue(tag));
		}

		if (node != null) {
			result.add("node=" + Utils.encodeValue(node));
		}

		return result;
	}
}
