package com.ecwid.consul.v1.connect;

import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.transport.HttpResponse;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.connect.model.CaConfigurationRequest;
import com.ecwid.consul.v1.connect.model.CaConfigurationResponse;
import com.ecwid.consul.v1.connect.model.CaRoots;

public class ConnectConsulClient implements ConnectClient {

	private final ConsulRawClient rawClient;

	public ConnectConsulClient(ConsulRawClient rawClient) {
		this.rawClient = rawClient;
	}

	public ConnectConsulClient() {
		this(new ConsulRawClient());
	}

	public ConnectConsulClient(TLSConfig tlsConfig) {
		this(new ConsulRawClient(tlsConfig));
	}

	public ConnectConsulClient(String agentHost) {
		this(new ConsulRawClient(agentHost));
	}

	public ConnectConsulClient(String agentHost, TLSConfig tlsConfig) {
		this(new ConsulRawClient(agentHost, tlsConfig));
	}

	public ConnectConsulClient(String agentHost, int agentPort) {
		this(new ConsulRawClient(agentHost, agentPort));
	}

	public ConnectConsulClient(String agentHost, int agentPort, TLSConfig tlsConfig) {
		this(new ConsulRawClient(agentHost, agentPort, tlsConfig));
	}

	@Override
	public Response<CaRoots> connectListCaRoots() {
		HttpResponse httpResponse = rawClient.makeGetRequest("/v1/connect/ca/roots");
		if (httpResponse.getStatusCode() == 200) {
			return new Response<>(GsonFactory.getGson().fromJson(httpResponse.getContent(), CaRoots.class), httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<CaConfigurationResponse> connectGetCaConfiguration() {
		HttpResponse httpResponse = rawClient.makeGetRequest("/v1/connect/ca/configuration");
		if (httpResponse.getStatusCode() == 200) {
			return new Response<>(GsonFactory.getGson()
					.fromJson(httpResponse.getContent(), CaConfigurationResponse.class), httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<CaConfigurationResponse> connectUpdateCaConfiguration(CaConfigurationRequest request) {
		HttpResponse httpResponse = rawClient.makePutRequest(
				"/v1/connect/ca/configuration",
				GsonFactory.getGson().toJson(request));

		if (httpResponse.getStatusCode() == 200) {
			return new Response<>(GsonFactory.getGson()
					.fromJson(httpResponse.getContent(), CaConfigurationResponse.class), httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

}
