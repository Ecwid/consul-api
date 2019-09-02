package com.ecwid.consul.v1.status;

import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.transport.HttpResponse;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.Response;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class StatusConsulClient implements StatusClient {

	private final ConsulRawClient rawClient;

	public StatusConsulClient(ConsulRawClient rawClient) {
		this.rawClient = rawClient;
	}

	public StatusConsulClient() {
		this(new ConsulRawClient());
	}

	public StatusConsulClient(TLSConfig tlsConfig) {
		this(new ConsulRawClient(tlsConfig));
	}

	public StatusConsulClient(String agentHost) {
		this(new ConsulRawClient(agentHost));
	}

	public StatusConsulClient(String agentHost, TLSConfig tlsConfig) {
		this(new ConsulRawClient(agentHost, tlsConfig));
	}

	public StatusConsulClient(String agentHost, int agentPort) {
		this(new ConsulRawClient(agentHost, agentPort));
	}

	public StatusConsulClient(String agentHost, int agentPort, TLSConfig tlsConfig) {
		this(new ConsulRawClient(agentHost, agentPort, tlsConfig));
	}

	@Override
	public Response<String> getStatusLeader() {
		HttpResponse httpResponse = rawClient.makeGetRequest("/v1/status/leader");

		if (httpResponse.getStatusCode() == 200) {
			String value = GsonFactory.getGson().fromJson(httpResponse.getContent(), String.class);
			return new Response<String>(value, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<List<String>> getStatusPeers() {
		HttpResponse httpResponse = rawClient.makeGetRequest("/v1/status/peers");

		if (httpResponse.getStatusCode() == 200) {
			List<String> value = GsonFactory.getGson().fromJson(httpResponse.getContent(), new TypeToken<List<String>>() {
			}.getType());
			return new Response<List<String>>(value, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

}
