package com.ecwid.consul.transport;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class RawResponse {

	private final int statusCode;
	private final String statusMessage;

	private final String content;

	private final Integer consulIndex;
	private final Boolean consulKnownLeader;
	private final Integer consulLastContact;

	public RawResponse(int statusCode, String statusMessage, String content, Integer consulIndex, Boolean consulKnownLeader, Integer consulLastContact) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.content = content;
		this.consulIndex = consulIndex;
		this.consulKnownLeader = consulKnownLeader;
		this.consulLastContact = consulLastContact;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public String getContent() {
		return content;
	}

	public Integer getConsulIndex() {
		return consulIndex;
	}

	public Boolean isConsulKnownLeader() {
		return consulKnownLeader;
	}

	public Integer getConsulLastContact() {
		return consulLastContact;
	}
}
