package com.ecwid.consul.v1.kv;

import com.ecwid.consul.ConsulException;
import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.transport.RawResponse;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.kv.model.GetBinaryValue;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.ecwid.consul.v1.kv.model.PutParams;
import com.ecwid.consul.v1.Response;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class KeyValueConsulClient implements KeyValueClient {

	private final ConsulRawClient rawClient;

	public KeyValueConsulClient(ConsulRawClient rawClient) {
		this.rawClient = rawClient;
	}

	public KeyValueConsulClient() {
		this(new ConsulRawClient());
	}

	public KeyValueConsulClient(String agentHost) {
		this(new ConsulRawClient(agentHost));
	}

	public KeyValueConsulClient(String agentHost, int agentPort) {
		this(new ConsulRawClient(agentHost, agentPort));
	}

	@Override
	public Response<GetValue> getKVValue(String key, QueryParams queryParams) {
		return getKVValue(key, null, queryParams);
	}

	@Override
	public Response<GetValue> getKVValue(String key, String token, QueryParams queryParams) {
		UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
		RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + key, tokenParams, queryParams);

		if (rawResponse.getStatusCode() == 200) {
			List<GetValue> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<GetValue>>(){}.getType());

			if (value.size() == 0) {
				return new Response<GetValue>(null, rawResponse);
			} else if (value.size() == 1) {
				return new Response<GetValue>(value.get(0), rawResponse);
			} else {
				throw new ConsulException("Strange response (list size=" + value.size() + ")");
			}
		} else if (rawResponse.getStatusCode() == 404) {
			return new Response<GetValue>(null, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<GetBinaryValue> getKVBinaryValue(String key, QueryParams queryParams) {
		return getKVBinaryValue(key, null, queryParams);
	}

	@Override
	public Response<GetBinaryValue> getKVBinaryValue(String key, String token, QueryParams queryParams) {
		UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
		RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + key, tokenParams, queryParams);

		if (rawResponse.getStatusCode() == 200) {
			List<GetBinaryValue> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<GetBinaryValue>>(){}.getType());

			if (value.size() == 0) {
				return new Response<GetBinaryValue>(null, rawResponse);
			} else if (value.size() == 1) {
				return new Response<GetBinaryValue>(value.get(0), rawResponse);
			} else {
				throw new ConsulException("Strange response (list size=" + value.size() + ")");
			}
		} else if (rawResponse.getStatusCode() == 404) {
			return new Response<GetBinaryValue>(null, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<List<GetValue>> getKVValues(String keyPrefix, QueryParams queryParams) {
		return getKVValues(keyPrefix, null, queryParams);
	}

	@Override
	public Response<List<GetValue>> getKVValues(String keyPrefix, String token, QueryParams queryParams) {
		UrlParameters recurseParam = new SingleUrlParameters("recurse");
		UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
		RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + keyPrefix, recurseParam, tokenParam, queryParams);

		if (rawResponse.getStatusCode() == 200) {
			List<GetValue> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<GetValue>>() {
			}.getType());
			return new Response<List<GetValue>>(value, rawResponse);
		} else if (rawResponse.getStatusCode() == 404) {
			return new Response<List<GetValue>>(null, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, QueryParams queryParams) {
		return getKVBinaryValues(keyPrefix, null, queryParams);
	}

	@Override
	public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token, QueryParams queryParams) {
		UrlParameters recurseParam = new SingleUrlParameters("recurse");
		UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
		RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + keyPrefix, recurseParam, tokenParam, queryParams);

		if (rawResponse.getStatusCode() == 200) {
			List<GetBinaryValue> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<GetBinaryValue>>() {
			}.getType());
			return new Response<List<GetBinaryValue>>(value, rawResponse);
		} else if (rawResponse.getStatusCode() == 404) {
			return new Response<List<GetBinaryValue>>(null, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<List<String>> getKVKeysOnly(String keyPrefix, QueryParams queryParams) {
		return getKVKeysOnly(keyPrefix, null, null, queryParams);
	}

	@Override
	public Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token, QueryParams queryParams) {
		UrlParameters separatorParam = separator != null ? new SingleUrlParameters("separator", separator) : null;
		UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
		RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + keyPrefix, separatorParam, tokenParam, queryParams);

		if (rawResponse.getStatusCode() == 200) {
			List<String> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<String>>() {
			}.getType());
			return new Response<List<String>>(value, rawResponse);
		} else if (rawResponse.getStatusCode() == 404) {
			return new Response<List<String>>(null, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<Boolean> setKVValue(String key, String value) {
		return setKVValue(key, value, null, null);
	}

	@Override
	public Response<Boolean> setKVValue(String key, String value, PutParams putParams) {
		return setKVValue(key, value, null, putParams);
	}

	@Override
	public Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams) {
		UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
		RawResponse rawResponse = rawClient.makePutRequest("/v1/kv/" + key, value, putParams, tokenParam);

		if (rawResponse.getStatusCode() == 200) {
			boolean result = GsonFactory.getGson().fromJson(rawResponse.getContent(), boolean.class);
			return new Response<Boolean>(result, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<Boolean> setKVBinaryValue(String key, byte[] value) {
		return setKVBinaryValue(key, value, null, null);
	}

	@Override
	public Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams) {
		return setKVBinaryValue(key, value, null, putParams);
	}

	@Override
	public Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams) {
		UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
		RawResponse rawResponse = rawClient.makePutRequest("/v1/kv/" + key, value, putParams, tokenParam);

		if (rawResponse.getStatusCode() == 200) {
			boolean result = GsonFactory.getGson().fromJson(rawResponse.getContent(), boolean.class);
			return new Response<Boolean>(result, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<Void> deleteKVValue(String key) {
		return deleteKVValue(key, null);
	}

	@Override
	public Response<Void> deleteKVValue(String key, String token) {
		UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
		RawResponse rawResponse = rawClient.makeDeleteRequest("/v1/kv/" + key, tokenParam);

		if (rawResponse.getStatusCode() == 200) {
			return new Response<Void>(null, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}

	@Override
	public Response<Void> deleteKVValues(String key) {
		return deleteKVValues(key, null);
	}

	@Override
	public Response<Void> deleteKVValues(String key, String token) {
		UrlParameters recurseParam = new SingleUrlParameters("recurse");
		UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
		RawResponse rawResponse = rawClient.makeDeleteRequest("/v1/kv/" + key, tokenParam, recurseParam);

		if (rawResponse.getStatusCode() == 200) {
			return new Response<Void>(null, rawResponse);
		} else {
			throw new OperationException(rawResponse);
		}
	}
}
