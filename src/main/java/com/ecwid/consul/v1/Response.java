package com.ecwid.consul.v1;

import com.ecwid.consul.transport.RawResponse;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class Response<T> {

	private final T value;

	private final Integer consulIndex;
	private final Boolean consulKnownLeader;
	private final Integer consulLastContact;

	public Response(T value, Integer consulIndex, Boolean consulKnownLeader, Integer consulLastContact) {
		this.value = value;
		this.consulIndex = consulIndex;
		this.consulKnownLeader = consulKnownLeader;
		this.consulLastContact = consulLastContact;
	}

	public Response(T value, RawResponse rawResponse) {
		this(value, rawResponse.getConsulIndex(), rawResponse.isConsulKnownLeader(), rawResponse.getConsulLastContact());
	}

	public T getValue() {
		return value;
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

	@Override
	public String toString() {
		return "Response{" +
				"value=" + value +
				", consulIndex=" + consulIndex +
				", consulKnownLeader=" + consulKnownLeader +
				", consulLastContact=" + consulLastContact +
				'}';
	}
}
