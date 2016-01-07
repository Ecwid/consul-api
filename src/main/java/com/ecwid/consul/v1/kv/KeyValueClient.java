package com.ecwid.consul.v1.kv;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetBinaryValue;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.ecwid.consul.v1.kv.model.PutParams;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public interface KeyValueClient {

	public Response<GetValue> getKVValue(String key);

	public Response<GetValue> getKVValue(String key, String token);

	public Response<GetValue> getKVValue(String key, UrlParameters UrlParameters);

	public Response<GetValue> getKVValue(String key, String token, UrlParameters UrlParameters);


	public Response<GetBinaryValue> getKVBinaryValue(String key);

	public Response<GetBinaryValue> getKVBinaryValue(String key, String token);

	public Response<GetBinaryValue> getKVBinaryValue(String key, UrlParameters UrlParameters);

	public Response<GetBinaryValue> getKVBinaryValue(String key, String token, UrlParameters UrlParameters);



	public Response<List<GetValue>> getKVValues(String keyPrefix);

	public Response<List<GetValue>> getKVValues(String keyPrefix, String token);

	public Response<List<GetValue>> getKVValues(String keyPrefix, UrlParameters UrlParameters);

	public Response<List<GetValue>> getKVValues(String keyPrefix, String token, UrlParameters UrlParameters);



	public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix);

	public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token);

	public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, UrlParameters UrlParameters);

	public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token, UrlParameters UrlParameters);



	public Response<List<String>> getKVKeysOnly(String keyPrefix);

	public Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token);

	public Response<List<String>> getKVKeysOnly(String keyPrefix, UrlParameters UrlParameters);

	public Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token, UrlParameters UrlParameters);



	public Response<Boolean> setKVValue(String key, String value);

	public Response<Boolean> setKVValue(String key, String value, PutParams putParams);

	public Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams);

	public Response<Boolean> setKVValue(String key, String value, UrlParameters UrlParameters);

	public Response<Boolean> setKVValue(String key, String value, PutParams putParams, UrlParameters UrlParameters);

	public Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams, UrlParameters UrlParameters);



	public Response<Boolean> setKVBinaryValue(String key, byte[] value);

	public Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams);

	public Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams);

	public Response<Boolean> setKVBinaryValue(String key, byte[] value, UrlParameters UrlParameters);

	public Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams, UrlParameters UrlParameters);

	public Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams, UrlParameters UrlParameters);



	public Response<Void> deleteKVValue(String key);

	public Response<Void> deleteKVValue(String key, String token);

	public Response<Void> deleteKVValue(String key, UrlParameters UrlParameters);

	public Response<Void> deleteKVValue(String key, String token, UrlParameters UrlParameters);



	public Response<Void> deleteKVValues(String key);

	public Response<Void> deleteKVValues(String key, String token);

	public Response<Void> deleteKVValues(String key, UrlParameters UrlParameters);

	public Response<Void> deleteKVValues(String key, String token, UrlParameters UrlParameters);

}
