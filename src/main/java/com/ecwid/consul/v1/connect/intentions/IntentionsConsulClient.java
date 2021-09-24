package com.ecwid.consul.v1.connect.intentions;

import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.transport.HttpResponse;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.Request;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.connect.intentions.model.IntentionResponse;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class IntentionsConsulClient implements IntentionsClient {

	private final ConsulRawClient rawClient;

	public IntentionsConsulClient(final ConsulRawClient rawClient) {
		this.rawClient = rawClient;
	}

	public IntentionsConsulClient() {
		this(new ConsulRawClient());
	}

	public IntentionsConsulClient(TLSConfig tlsConfig) {
		this(new ConsulRawClient(tlsConfig));
	}

	public IntentionsConsulClient(String agentHost) {
		this(new ConsulRawClient(agentHost));
	}

	public IntentionsConsulClient(String agentHost, TLSConfig tlsConfig) {
		this(new ConsulRawClient(agentHost, tlsConfig));
	}

	public IntentionsConsulClient(String agentHost, int agentPort) {
		this(new ConsulRawClient(agentHost, agentPort));
	}

	public IntentionsConsulClient(String agentHost, int agentPort, TLSConfig tlsConfig) {
		this(new ConsulRawClient(agentHost, agentPort, tlsConfig));
	}

	@Override
	public Response<Boolean> createIntention(IntentionUpsertRequest request, String token) {
		return updateIntention(request, token);
	}

	@Override
	public Response<Boolean> updateIntention(IntentionUpsertRequest request, String token) {
		String json = GsonFactory.getGson().toJson(request.getIntention());
		UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
		UrlParameters sourceParam = new SingleUrlParameters("source", request.getSource());
		UrlParameters destinationParam = new SingleUrlParameters("destination", request.getDestination());
		UrlParameters nsParam = new SingleUrlParameters("ns", request.getNs());

		HttpResponse httpResponse = rawClient.makePutRequest("/v1/connect/intentions/exact", json,
				sourceParam, destinationParam, nsParam, tokenParam);
		return processBooleanResponse(httpResponse);
	}

	@Override
	public Response<List<IntentionResponse>> listIntentions(IntentionListRequest request, String token) {
		UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
		UrlParameters filterParam = new SingleUrlParameters("filter", request.getFilter());
		UrlParameters nsParam = new SingleUrlParameters("ns", request.getNs());

		HttpResponse httpResponse = rawClient.makeGetRequest("/v1/connect/intentions",
				filterParam, nsParam, tokenParam);
		if (httpResponse.getStatusCode() == 200) {
			List<IntentionResponse> value = GsonFactory.getGson().fromJson(httpResponse.getContent(), INTENTION_RESPONSE_TYPE);
			return new Response<>(value, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	@Override
	public Response<Boolean> deleteIntention(IntentionDeleteRequest request, String token) {
		UrlParameters sourceParam = new SingleUrlParameters("source", request.getSource());
		UrlParameters destinationParam = new SingleUrlParameters("destination", request.getDestination());

		Request r = new Request.Builder()
				.setEndpoint("/v1/connect/intentions/exact")
				.addUrlParameter(sourceParam)
				.addUrlParameter(destinationParam)
				.setToken(token)
				.build();
		HttpResponse httpResponse = rawClient.makeDeleteRequest(r);
		return processBooleanResponse(httpResponse);
	}

	private Response<Boolean> processBooleanResponse(HttpResponse httpResponse) {
		if (httpResponse.getStatusCode() == 200) {
			Boolean value = GsonFactory.getGson().fromJson(httpResponse.getContent(), BOOLEAN_TYPE);
			if (!value) {
				throw new OperationException(httpResponse);
			}
			return new Response<>(value, httpResponse);
		} else {
			throw new OperationException(httpResponse);
		}
	}

	private static final Type BOOLEAN_TYPE = new BooleanTypeToken().getType();

	private static final Type INTENTION_RESPONSE_TYPE = new IntentionResponseType().getType();

	private static class BooleanTypeToken extends TypeToken<Boolean> {

	}

	private static class IntentionResponseType extends TypeToken<List<IntentionResponse>> {

	}

}
