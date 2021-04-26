package com.ecwid.consul.v1.health;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HealthChecksForServiceRequestTest {
	@Nested
	class EqualsAndHashCode {
		@Test
		void shouldVerify() {
			EqualsVerifier.forClass(HealthChecksForServiceRequest.class).verify();
		}
	}
}
