package com.ecwid.consul.v1.kv.model;

import com.ecwid.consul.json.Base64TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class GetBinaryValue {

	@SerializedName("CreateIndex")
	private long createIndex;

	@SerializedName("ModifyIndex")
	private long modifyIndex;

	@SerializedName("LockIndex")
	private Long lockIndex;

	@SerializedName("Flags")
	private long flags;

	@SerializedName("Session")
	private String session;

	@SerializedName("Key")
	private String key;

	@SerializedName("Value")
	@JsonAdapter(Base64TypeAdapter.class)
	private byte[] value;

	public long getCreateIndex() {
		return createIndex;
	}

	public void setCreateIndex(long createIndex) {
		this.createIndex = createIndex;
	}

	public long getModifyIndex() {
		return modifyIndex;
	}

	public void setModifyIndex(long modifyIndex) {
		this.modifyIndex = modifyIndex;
	}

	public Long getLockIndex() {
		return lockIndex;
	}

	public void setLockIndex(Long lockIndex) {
		this.lockIndex = lockIndex;
	}

	public long getFlags() {
		return flags;
	}

	public void setFlags(long flags) {
		this.flags = flags;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "GetBinaryValue{" +
				"createIndex=" + createIndex +
				", modifyIndex=" + modifyIndex +
				", lockIndex=" + lockIndex +
				", flags=" + flags +
				", session='" + session + '\'' +
				", key='" + key + '\'' +
				", value=" + Arrays.toString(value) +
				'}';
	}
}
