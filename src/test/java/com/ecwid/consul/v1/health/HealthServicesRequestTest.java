package com.ecwid.consul.v1.health;

import com.ecwid.consul.v1.Filter;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HealthServicesRequestTest {
	@Nested
	class EqualsAndHashCode {
		@Test
		void shouldVerify() {
			EqualsVerifier.forClass(HealthServicesRequest.class)
					.withPrefabValues(
							Filter.class,
							Filter.of(Filter.MatchingOperator.EQUAL, Filter.Selector.of("foo"), "bar"),
							Filter.in("baz", Filter.Selector.of("fang"))
					)
					.verify();
		}
	}
}
