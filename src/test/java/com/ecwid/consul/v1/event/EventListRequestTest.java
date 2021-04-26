package com.ecwid.consul.v1.event;

import com.ecwid.consul.v1.NodeMetaParameters;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class EventListRequestTest {
	@Nested
	class EqualsAndHashCode {
		@Test
		void shouldVerify() {
			EqualsVerifier.forClass(EventListRequest.class).verify();
		}
	}
}
