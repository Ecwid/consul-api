package com.ecwid.consul.v1.acl.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public enum AclType {

	@SerializedName("client")
	CLIENT,

	@SerializedName("management")
	MANAGEMENT

}
