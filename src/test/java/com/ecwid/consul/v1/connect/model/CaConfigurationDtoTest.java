package com.ecwid.consul.v1.connect.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CaConfigurationDtoTest {

	@Nested
	class EqualsHashCode {

		@Test
		void caConfigurationResponse() {
			EqualsVerifier.simple().forClass(CaConfigurationResponse.class).verify();
		}

		@Test
		void caConfigurationRequest() {
			EqualsVerifier.simple().forClass(CaConfigurationRequest.class).verify();
		}

	}

}
