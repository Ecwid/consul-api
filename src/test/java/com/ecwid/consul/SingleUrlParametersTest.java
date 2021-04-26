package com.ecwid.consul;


import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SingleUrlParametersTest {

	@Test
	public void testToUrlParameters() throws Exception {
		UrlParameters parameters = new SingleUrlParameters("key");
		assertEquals(Collections.singletonList("key"), parameters.toUrlParameters());

		parameters = new SingleUrlParameters("key", "value");
		assertEquals(Collections.singletonList("key=value"), parameters.toUrlParameters());

		parameters = new SingleUrlParameters("key", "value value");
		assertEquals(Collections.singletonList("key=value+value"), parameters.toUrlParameters());
	}

	@Nested
	class EqualsAndHashCode {
		@Test
		void shouldVerify() {
			EqualsVerifier.forClass(SingleUrlParameters.class).verify();
		}
	}
}
