package com.ecwid.consul.v1.kv.model;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class GetValue {

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
	private String value;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDecodedValue(Charset charset) {
		if (value == null) {
			return null;
		}
		if (charset == null) {
			charset = Charset.forName("UTF-8");
		}
		return new String(DatatypeConverter.parseBase64Binary(value), charset);
	}

	public String getDecodedValue() {
		return getDecodedValue(null);
	}

	@Override
	public String toString() {
		return "GetValue{" +
				"createIndex=" + createIndex +
				", modifyIndex=" + modifyIndex +
				", lockIndex=" + lockIndex +
				", flags=" + flags +
				", session='" + session + '\'' +
				", key='" + key + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
