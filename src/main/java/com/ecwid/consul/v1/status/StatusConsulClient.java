package com.ecwid.consul.v1.status;

import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.transport.RawResponse;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulRawClient;
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
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/status/leader");

	if (rawResponse.getStatusCode() == 200) {
	    String value = GsonFactory.getGson().fromJson(rawResponse.getContent(), String.class);
	    return new Response<String>(value, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<List<String>> getStatusPeers() {
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/status/peers");

	if (rawResponse.getStatusCode() == 200) {
	    List<String> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<String>>() {
	    }.getType());
	    return new Response<List<String>>(value, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

}
