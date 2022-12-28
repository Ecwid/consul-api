package com.ecwid.consul.v1.kv;

import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetBinaryValue;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.ecwid.consul.v1.kv.model.PutParams;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface KeyValueClient {

	Response<GetValue> getKVValue(String key);

	Response<GetValue> getKVValue(String key, String token);

	Response<GetValue> getKVValue(String key, QueryParams queryParams);

	Response<GetValue> getKVValue(String key, String token, QueryParams queryParams);


	Response<GetBinaryValue> getKVBinaryValue(String key);

	Response<GetBinaryValue> getKVBinaryValue(String key, String token);

	Response<GetBinaryValue> getKVBinaryValue(String key, QueryParams queryParams);

	Response<GetBinaryValue> getKVBinaryValue(String key, String token, QueryParams queryParams);


	Response<List<GetValue>> getKVValues(String keyPrefix);

	Response<List<GetValue>> getKVValues(String keyPrefix, String token);

	Response<List<GetValue>> getKVValues(String keyPrefix, QueryParams queryParams);

	Response<List<GetValue>> getKVValues(String keyPrefix, String token, QueryParams queryParams);


	Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix);

	Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token);

	Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, QueryParams queryParams);

	Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token, QueryParams queryParams);


	Response<List<String>> getKVKeysOnly(String keyPrefix);

	Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token);

	Response<List<String>> getKVKeysOnly(String keyPrefix, QueryParams queryParams);

	Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token, QueryParams queryParams);


	Response<Boolean> setKVValue(String key, String value);

	Response<Boolean> setKVValue(String key, String value, PutParams putParams);

	Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams);

	Response<Boolean> setKVValue(String key, String value, QueryParams queryParams);

	Response<Boolean> setKVValue(String key, String value, PutParams putParams, QueryParams queryParams);

	Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams, QueryParams queryParams);


	Response<Boolean> setKVBinaryValue(String key, byte[] value);

	Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams);

	Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams);

	Response<Boolean> setKVBinaryValue(String key, byte[] value, QueryParams queryParams);

	Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams, QueryParams queryParams);

	Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams, QueryParams queryParams);


	Response<Void> deleteKVValue(String key);

	Response<Void> deleteKVValue(String key, String token);

	Response<Void> deleteKVValue(String key, QueryParams queryParams);

	Response<Void> deleteKVValue(String key, String token, QueryParams queryParams);


	Response<Void> deleteKVValues(String key);

	Response<Void> deleteKVValues(String key, String token);

	Response<Void> deleteKVValues(String key, QueryParams queryParams);

	Response<Void> deleteKVValues(String key, String token, QueryParams queryParams);

}
