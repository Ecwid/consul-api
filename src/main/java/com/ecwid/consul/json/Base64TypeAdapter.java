package com.ecwid.consul.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Base64;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Base64TypeAdapter extends TypeAdapter<byte[]> {

	@Override
	public void write(JsonWriter out, byte[] value) throws IOException {
		if (value == null) {
			out.nullValue();
		} else {
      out.value(Base64.getEncoder().encodeToString(value));
		}
	}

	@Override
	public byte[] read(JsonReader in) throws IOException {
		if (in.peek() == JsonToken.NULL) {
			in.nextNull();
			return new byte[0];
		} else {
			String data = in.nextString();
			return Base64.getDecoder().decode(data);
		}
	}
}
