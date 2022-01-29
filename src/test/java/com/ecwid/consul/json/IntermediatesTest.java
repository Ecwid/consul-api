package com.ecwid.consul.json;

import com.ecwid.consul.TestUtils;
import com.ecwid.consul.v1.agent.model.CaRoots;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntermediatesTest {

	@Test
	public void testTwoIntermediatesDeserialization() throws IOException {
		String intermediatesJson = resourceAsString("two-intermediates.json");
		CaRoots caRoot = GsonFactory.getGson().fromJson(intermediatesJson, CaRoots.class);
		Assertions.assertEquals(2, caRoot.getRoots().get(0).getIntermediateCerts().size());
	}

	@Test
	public void testEmptyIntermediatesDeserialization() throws IOException {
		String intermediatesJson = resourceAsString("empty-intermediates.json");
		CaRoots caRoot = GsonFactory.getGson().fromJson(intermediatesJson, CaRoots.class);
		Assertions.assertEquals(0, caRoot.getRoots().get(0).getIntermediateCerts().size());
	}

	@Test
	public void testNullIntermediatesDeserialization() throws IOException {
		String intermediatesJson = resourceAsString("null-intermediates.json");
		CaRoots caRoot = GsonFactory.getGson().fromJson(intermediatesJson, CaRoots.class);
		Assertions.assertNull(caRoot.getRoots().get(0).getIntermediateCerts());
	}

	private String resourceAsString(String resource) throws IOException {
		try (InputStream stream = getClass().getResourceAsStream(resource)) {
			if (stream == null) {
				throw new IllegalStateException(String.format("Missing %s resource.", resource));
			}
			return TestUtils.streamToString(stream);
		}
	}

}
