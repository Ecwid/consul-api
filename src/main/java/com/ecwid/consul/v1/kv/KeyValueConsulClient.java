package com.ecwid.consul.v1.kv;

import java.util.List;

import com.ecwid.consul.ConsulException;
import com.ecwid.consul.SingleUrlParameters;
import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.json.GsonFactory;
import com.ecwid.consul.transport.RawResponse;
import com.ecwid.consul.transport.TLSConfig;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetBinaryValue;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.ecwid.consul.v1.kv.model.PutParams;
import com.google.gson.reflect.TypeToken;

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

    public KeyValueConsulClient(TLSConfig tlsConfig) {
	this(new ConsulRawClient(tlsConfig));
    }

    public KeyValueConsulClient(String agentHost, TLSConfig tlsConfig) {
	this(new ConsulRawClient(agentHost, tlsConfig));
    }

    public KeyValueConsulClient(String agentHost, int agentPort, TLSConfig tlsConfig) {
	this(new ConsulRawClient(agentHost, agentPort, tlsConfig));
    }

    @Override
    public Response<GetValue> getKVValue(String key) {
	return getKVValue(key, QueryParams.DEFAULT);
    }

    @Override
    public Response<GetValue> getKVValue(String key, String token) {
	return getKVValue(key, token, QueryParams.DEFAULT);
    }

    @Override
    public Response<GetValue> getKVValue(String key, UrlParameters UrlParameters) {
	return getKVValue(key, null, UrlParameters);
    }

    @Override
    public Response<GetValue> getKVValue(String key, String token, UrlParameters UrlParameters) {
	UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + key, tokenParams, UrlParameters);

	if (rawResponse.getStatusCode() == 200) {
	    List<GetValue> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<GetValue>>() {
	    }.getType());

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
    public Response<GetBinaryValue> getKVBinaryValue(String key) {
	return getKVBinaryValue(key, QueryParams.DEFAULT);
    }

    @Override
    public Response<GetBinaryValue> getKVBinaryValue(String key, String token) {
	return getKVBinaryValue(key, token, QueryParams.DEFAULT);
    }

    @Override
    public Response<GetBinaryValue> getKVBinaryValue(String key, UrlParameters UrlParameters) {
	return getKVBinaryValue(key, null, UrlParameters);
    }

    @Override
    public Response<GetBinaryValue> getKVBinaryValue(String key, String token, UrlParameters UrlParameters) {
	UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + key, tokenParams, UrlParameters);

	if (rawResponse.getStatusCode() == 200) {
	    List<GetBinaryValue> value = GsonFactory.getGson().fromJson(rawResponse.getContent(), new TypeToken<List<GetBinaryValue>>() {
	    }.getType());

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
    public Response<List<GetValue>> getKVValues(String keyPrefix) {
	return getKVValues(keyPrefix, QueryParams.DEFAULT);
    }

    @Override
    public Response<List<GetValue>> getKVValues(String keyPrefix, String token) {
	return getKVValues(keyPrefix, token, QueryParams.DEFAULT);
    }

    @Override
    public Response<List<GetValue>> getKVValues(String keyPrefix, UrlParameters UrlParameters) {
	return getKVValues(keyPrefix, null, UrlParameters);
    }

    @Override
    public Response<List<GetValue>> getKVValues(String keyPrefix, String token, UrlParameters UrlParameters) {
	UrlParameters recurseParam = new SingleUrlParameters("recurse");
	UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + keyPrefix, recurseParam, tokenParam, UrlParameters);

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
    public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix) {
	return getKVBinaryValues(keyPrefix, QueryParams.DEFAULT);
    }

    @Override
    public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token) {
	return getKVBinaryValues(keyPrefix, token, QueryParams.DEFAULT);
    }

    @Override
    public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, UrlParameters UrlParameters) {
	return getKVBinaryValues(keyPrefix, null, UrlParameters);
    }

    @Override
    public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token, UrlParameters UrlParameters) {
	UrlParameters recurseParam = new SingleUrlParameters("recurse");
	UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + keyPrefix, recurseParam, tokenParam, UrlParameters);

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
    public Response<List<String>> getKVKeysOnly(String keyPrefix) {
	return getKVKeysOnly(keyPrefix, QueryParams.DEFAULT);
    }

    @Override
    public Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token) {
	return getKVKeysOnly(keyPrefix, separator, token, QueryParams.DEFAULT);
    }

    @Override
    public Response<List<String>> getKVKeysOnly(String keyPrefix, UrlParameters UrlParameters) {
	return getKVKeysOnly(keyPrefix, null, null, UrlParameters);
    }

    @Override
    public Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token, UrlParameters UrlParameters) {
	UrlParameters keysParam = new SingleUrlParameters("keys");
	UrlParameters separatorParam = separator != null ? new SingleUrlParameters("separator", separator) : null;
	UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + keyPrefix, keysParam, separatorParam, tokenParam, UrlParameters);

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
	return setKVValue(key, value, QueryParams.DEFAULT);
    }

    @Override
    public Response<Boolean> setKVValue(String key, String value, PutParams putParams) {
	return setKVValue(key, value, putParams, QueryParams.DEFAULT);
    }

    @Override
    public Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams) {
	return setKVValue(key, value, token, putParams, QueryParams.DEFAULT);
    }

    @Override
    public Response<Boolean> setKVValue(String key, String value, UrlParameters UrlParameters) {
	return setKVValue(key, value, null, null, UrlParameters);
    }

    @Override
    public Response<Boolean> setKVValue(String key, String value, PutParams putParams, UrlParameters UrlParameters) {
	return setKVValue(key, value, null, putParams, UrlParameters);
    }

    @Override
    public Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams, UrlParameters UrlParameters) {
	UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makePutRequest("/v1/kv/" + key, value, putParams, tokenParam, UrlParameters);

	if (rawResponse.getStatusCode() == 200) {
	    boolean result = GsonFactory.getGson().fromJson(rawResponse.getContent(), boolean.class);
	    return new Response<Boolean>(result, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Boolean> setKVBinaryValue(String key, byte[] value) {
	return setKVBinaryValue(key, value, QueryParams.DEFAULT);
    }

    @Override
    public Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams) {
	return setKVBinaryValue(key, value, putParams, QueryParams.DEFAULT);
    }

    @Override
    public Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams) {
	return setKVBinaryValue(key, value, token, putParams, QueryParams.DEFAULT);
    }

    @Override
    public Response<Boolean> setKVBinaryValue(String key, byte[] value, UrlParameters UrlParameters) {
	return setKVBinaryValue(key, value, null, null, UrlParameters);
    }

    @Override
    public Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams, UrlParameters UrlParameters) {
	return setKVBinaryValue(key, value, null, putParams, UrlParameters);
    }

    @Override
    public Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams, UrlParameters UrlParameters) {
	UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makePutRequest("/v1/kv/" + key, value, putParams, tokenParam, UrlParameters);

	if (rawResponse.getStatusCode() == 200) {
	    boolean result = GsonFactory.getGson().fromJson(rawResponse.getContent(), boolean.class);
	    return new Response<Boolean>(result, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> deleteKVValue(String key) {
	return deleteKVValue(key, QueryParams.DEFAULT);
    }

    @Override
    public Response<Void> deleteKVValue(String key, String token) {
	return deleteKVValue(key, token, QueryParams.DEFAULT);
    }

    @Override
    public Response<Void> deleteKVValue(String key, UrlParameters UrlParameters) {
	return deleteKVValue(key, null, UrlParameters);
    }

    @Override
    public Response<Void> deleteKVValue(String key, String token, UrlParameters UrlParameters) {
	UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makeDeleteRequest("/v1/kv/" + key, tokenParam, UrlParameters);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }

    @Override
    public Response<Void> deleteKVValues(String key) {
	return deleteKVValues(key, QueryParams.DEFAULT);
    }

    @Override
    public Response<Void> deleteKVValues(String key, String token) {
	return deleteKVValues(key, token, QueryParams.DEFAULT);
    }

    @Override
    public Response<Void> deleteKVValues(String key, UrlParameters UrlParameters) {
	return deleteKVValues(key, null, UrlParameters);
    }

    @Override
    public Response<Void> deleteKVValues(String key, String token, UrlParameters UrlParameters) {
	UrlParameters recurseParam = new SingleUrlParameters("recurse");
	UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
	RawResponse rawResponse = rawClient.makeDeleteRequest("/v1/kv/" + key, tokenParam, recurseParam, UrlParameters);

	if (rawResponse.getStatusCode() == 200) {
	    return new Response<Void>(null, rawResponse);
	} else {
	    throw new OperationException(rawResponse);
	}
    }
}
