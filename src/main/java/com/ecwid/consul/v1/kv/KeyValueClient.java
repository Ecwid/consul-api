package com.ecwid.consul.v1.kv;

import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.ecwid.consul.v1.kv.model.PutParams;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface KeyValueClient {

	public Response<GetValue> getKVValue(String key, QueryParams queryParams);

	public Response<GetValue> getKVValue(String key, String token, QueryParams queryParams);

	public Response<List<GetValue>> getKVValues(String keyPrefix, QueryParams queryParams);

	public Response<List<GetValue>> getKVValues(String keyPrefix, String token, QueryParams queryParams);

	public Response<List<String>> getKVKeysOnly(String keyPrefix, QueryParams queryParams);

	public Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token, QueryParams queryParams);

	public Response<Boolean> setKVValue(String key, String value, QueryParams queryParams);

	public Response<Boolean> setKVValue(String key, String value, PutParams putParams, QueryParams queryParams);

	public Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams, QueryParams queryParams);

	public Response<Void> deleteKVValue(String key, QueryParams queryParams);

	public Response<Void> deleteKVValue(String key, String token, QueryParams queryParams);

	public Response<Void> deleteKVValues(String key, QueryParams queryParams);

	public Response<Void> deleteKVValues(String key, String token, QueryParams queryParams);

}
