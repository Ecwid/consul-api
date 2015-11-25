package com.ecwid.consul.v1.event;

import java.util.List;

import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.transport.RawResponse;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.event.model.Event;
import com.ecwid.consul.v1.event.model.EventParams;
import com.google.gson.reflect.TypeToken;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class EventConsulClient implements EventClient {

    private final ConsulRawClient rawClient;

    public EventConsulClient(ConsulRawClient rawClient) {
	this.rawClient = rawClient;
    }

    public EventConsulClient() {
	this(new ConsulRawClient());
    }

    public EventConsulClient(TLSConfig tlsConfig) {
	this(new ConsulRawClient(tlsConfig));
    }

    public EventConsulClient(String agentHost) {
	this(new ConsulRawClient(agentHost));
    }

    public EventConsulClient(String agentHost, TLSConfig tlsConfig) {
	this(new ConsulRawClient(agentHost, tlsConfig));
    }

    public EventConsulClient(String agentHost, int agentPort, TLSConfig tlsConfig) {
	this(new ConsulRawClient(agentHost, agentPort, tlsConfig));
    }

    @Override
    public Response<Event> eventFire(String event, String payload, EventParams eventParams, QueryParams queryParams) {
	RawResponse rawResponse = rawClient.makePutRequest("/v1/event/fire/" + event, payload, eventParams, queryParams);

	if (rawResponse.getStatusCode() == 200) {
	    Event value = GsonFactory.getGson().fromJson(rawResponse.getContent(), Event.class);
	    return new Response<Event>(value, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<List<Event>> eventList(QueryParams queryParams) {
	return eventList(null, queryParams);
    }

    @Override
    public Response<List<Event>> eventList(String event, QueryParams queryParams) {
	UrlParameters eventParams = event != null ? new SingleUrlParameters("name", event) : null;
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/event/list", eventParams, queryParams);

	if (rawResponse.getStatusCode() == 200) {
	    List<Event> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<Event>>() {
	    }.getType());
	    return new Response<List<Event>>(value, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }
}
