package com.ecwid.consul.v1.acl.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class NewAcl {

	@SerializedName("Name")
	private String name;

	@SerializedName("Type")
	private AclType type;

	@SerializedName("Rules")
	private String rules;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AclType getType() {
		return type;
	}

	public void setType(AclType type) {
		this.type = type;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	@Override
	public String toString() {
		return "NewAcl{" +
				"name='" + name + '\'' +
				", type=" + type +
				", rules='" + rules + '\'' +
				'}';
	}
}
