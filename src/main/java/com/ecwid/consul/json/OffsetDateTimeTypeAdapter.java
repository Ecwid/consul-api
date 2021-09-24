package com.ecwid.consul.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;

public class OffsetDateTimeTypeAdapter extends TypeAdapter<OffsetDateTime> {

	@Override
	public void write(JsonWriter out, OffsetDateTime value) throws IOException {
		if (value == null) {
			out.nullValue();
		} else {
			out.value(value.toString());
		}
	}

	@Override
	public OffsetDateTime read(JsonReader in) throws IOException {
		if (in.peek() == JsonToken.NULL) {
			in.nextNull();
			return null;
		} else {
			String timestamp = in.nextString();
			return OffsetDateTime.parse(timestamp);
		}
	}

}
