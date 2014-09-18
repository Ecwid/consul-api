package com.ecwid.consul.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Base64TypeAdapter extends TypeAdapter<byte[]> {

	@Override
	public void write(JsonWriter out, byte[] value) throws IOException {
		if (value == null) {
			out.nullValue();
		} else {
			out.value(DatatypeConverter.printBase64Binary(value));
		}
	}

	@Override
	public byte[] read(JsonReader in) throws IOException {
		if (in.peek() == JsonToken.NULL) {
			in.nextNull();
			return null;
		} else {
			String data = in.nextString();
			return DatatypeConverter.parseBase64Binary(data);
		}
	}
}
