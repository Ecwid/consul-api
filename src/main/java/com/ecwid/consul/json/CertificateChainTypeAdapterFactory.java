package com.ecwid.consul.json;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CertificateChainTypeAdapterFactory implements TypeAdapterFactory {

	private final CertificateTypeAdapter certificateTypeAdapter = new CertificateTypeAdapter();

	@Override
	@SuppressWarnings("unchecked")
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		Class<? super T> rawType = type.getRawType();
		if (!Collection.class.isAssignableFrom(rawType)) {
			return null;
		}
		return new TypeAdapter<T>() {

			@Override
			public void write(JsonWriter out, T value) throws IOException {
				List<Certificate> certificates = (List<Certificate>) value;
				if (certificates == null) {
					out.nullValue();
				} else {
					out.beginArray();
					for (Certificate certificate : certificates) {
						certificateTypeAdapter.write(out, certificate);
					}
					out.endArray();
				}
			}

			@Override
			public T read(JsonReader in) throws IOException {
				final List<Certificate> certificates = new ArrayList<>();
				in.beginArray();
				while (in.peek() == JsonToken.STRING) {
					certificates.add(certificateTypeAdapter.read(in));
				}
				in.endArray();
				return (T) certificates;
			}
		};
	}

}
