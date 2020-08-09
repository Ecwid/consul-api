package com.ecwid.consul.v1.kv.model;

import static org.junit.jupiter.api.Assertions.*;

import com.ecwid.consul.v1.QueryParams;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PutParamsTest {
	@Nested
	class EqualsAndHashCode {
		@Test
		void shouldVerify() {
			EqualsVerifier.simple().forClass(PutParams.class).verify();
		}
	}
}
