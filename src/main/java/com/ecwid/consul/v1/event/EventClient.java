package com.ecwid.consul.v1.event;

import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.event.model.Event;
import com.ecwid.consul.v1.event.model.EventParams;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface EventClient {

	public Response<Event> eventFire(String event, String payload, EventParams eventParams, QueryParams queryParams);

	// -------------------------------------------------------------------------------

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #eventList(EventListRequest eventListRequest)}
	 */
	@Deprecated
	public Response<List<Event>> eventList(QueryParams queryParams);

	/**
	 * @deprecated This method will be removed in consul-api 2.0. Use {@link #eventList(EventListRequest eventListRequest)}
	 */
	@Deprecated
	public Response<List<Event>> eventList(String event, QueryParams queryParams);

	public Response<List<Event>> eventList(EventListRequest eventListRequest);

}
