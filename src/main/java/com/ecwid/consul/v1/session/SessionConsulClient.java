package com.ecwid.consul.v1.session;

import com.ecwid.consul.ConsulException;
import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.transport.RawResponse;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.session.model.NewSession;
import com.ecwid.consul.v1.session.model.Session;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class SessionConsulClient implements SessionClient {

	private final ConsulRawClient rawClient;

	public SessionConsulClient(ConsulRawClient rawClient) {
		this.rawClient = rawClient;
	}

	public SessionConsulClient() {
		this(new ConsulRawClient());
	}

	public SessionConsulClient(String agentHost) {
		this(new ConsulRawClient(agentHost));
	}

	public SessionConsulClient(String agentHost, int agentPort) {
		this(new ConsulRawClient(agentHost, agentPort));
	}

	@Override
	public Response<String> sessionCreate(NewSession newSession, QueryParams queryParams) {
		String json = GsonFactory.getGson().toJson(newSession);
		RawResponse rawResponse = rawClient.makePutRequest("/v1/session/create", json, queryParams);

		if (rawResponse.getStatusCode() == 200) {
			Map<String, String> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<Map<String, String>>() {
			}.getType());
			return new Response<String>(value.get("ID"), rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<Void> sessionDestroy(String session, QueryParams queryParams) {
		RawResponse rawResponse = rawClient.makePutRequest("/v1/session/destroy/" + session, "", queryParams);

		if (rawResponse.getStatusCode() == 200) {
			return new Response<Void>(null, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<Session> getSessionInfo(String session, QueryParams queryParams) {
		RawResponse rawResponse = rawClient.makeGetRequest("/v1/session/info/" + session, queryParams);

		if (rawResponse.getStatusCode() == 200) {
			List<Session> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<Session>>() {
			}.getType());

			if (value == null || value.isEmpty()) {
				return new Response<Session>(null, rawResponse);
			} else if (value.size() == 1) {
				return new Response<Session>(value.get(0), rawResponse);
			} else {
				throw new ConsulException("Strange response (list size=" + value.size() + ")");
			}
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<List<Session>> getSessionNode(String node, QueryParams queryParams) {
		RawResponse rawResponse = rawClient.makeGetRequest("/v1/session/node/" + node, queryParams);

		if (rawResponse.getStatusCode() == 200) {
			List<Session> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<Session>>() {
			}.getType());
			return new Response<List<Session>>(value, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<List<Session>> getSessionList(QueryParams queryParams) {
		RawResponse rawResponse = rawClient.makeGetRequest("/v1/session/list", queryParams);

		if (rawResponse.getStatusCode() == 200) {
			List<Session> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<Session>>() {
			}.getType());
			return new Response<List<Session>>(value, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	public Response<Session> renewSession(String session, QueryParams queryParams) {
		RawResponse rawResponse = rawClient.makePutRequest("/v1/session/renew/" + session, "", queryParams);

		if (rawResponse.getStatusCode() == 200) {
			List<Session> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<Session>>() {
			}.getType());

			if (value.size() == 1) {
				return new Response<Session>(value.get(0), rawResponse);
			} else {
				throw new ConsulException("Strange response (list size=" + value.size() + ")");
			}
		} else {
			throw new OperationException(rawResponse);
		}
	}
}
