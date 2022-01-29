package com.ecwid.consul.v1.agent;

import com.ecwid.consul.v1.agent.model.LeafCertificate;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LeafCertificateDtoTest {

	@Nested
	class EqualsHashCode {

		@Test
		void leafCertificate() {
			EqualsVerifier.simple().forClass(LeafCertificate.class).verify();
		}

	}

}
