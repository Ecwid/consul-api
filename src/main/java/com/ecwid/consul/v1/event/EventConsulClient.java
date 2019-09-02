package com.ecwid.consul.v1.event;

import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.transport.HttpResponse;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.event.model.Event;
import com.ecwid.consul.v1.event.model.EventParams;
import com.google.gson.reflect.TypeToken;

import java.util.List;

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
		HttpResponse httpResponse = rawClient.makePutRequest("/v1/event/fire/" + event, payload, eventParams, queryParams);

		if (httpResponse.getStatusCode() == 200) {
			Event value = GsonFactory.getGson().fromJson(httpResponse.getContent(), Event.class);
			return new Response<Event>(value, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<List<Event>> eventList(QueryParams queryParams) {
		return eventList(null, queryParams);
	}

	@Override
	public Response<List<Event>> eventList(String event, QueryParams queryParams) {
		EventListRequest request = EventListRequest.newBuilder()
				.setName(event)
				.setQueryParams(queryParams)
				.build();

		return eventList(request);
	}

	@Override
	public Response<List<Event>> eventList(EventListRequest eventListRequest) {
		HttpResponse httpResponse = rawClient.makeGetRequest("/v1/event/list", eventListRequest.asUrlParameters());

		if (httpResponse.getStatusCode() == 200) {
			List<Event> value = GsonFactory.getGson().fromJson(httpResponse.getContent(), new TypeToken<List<Event>>() {
			}.getType());
			return new Response<List<Event>>(value, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}
}
