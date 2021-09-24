package com.ecwid.consul.v1.agent;

import com.ecwid.consul.v1.agent.model.AuthorizeRequest;
import com.ecwid.consul.v1.agent.model.AuthorizeResponse;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AuthorizeDtoTest {

	@Nested
	class EqualsHashCode {

		@Test
		void authorizeRequest() {
			EqualsVerifier.simple().forClass(AuthorizeRequest.class).verify();
		}

		@Test
		void authorizeResponse() {
			EqualsVerifier.simple().forClass(AuthorizeResponse.class).verify();
		}

	}

}
