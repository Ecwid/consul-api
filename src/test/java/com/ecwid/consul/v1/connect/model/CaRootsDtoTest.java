package com.ecwid.consul.v1.connect.model;

import com.ecwid.consul.TestUtils;
import java.io.IOException;
import java.security.cert.Certificate;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CaRootsDtoTest {

	@Nested
	class EqualsHashCode {

		@Test
		void caRoots() throws IOException {
			EqualsVerifier.simple()
					.forClass(CaRoots.class)
					.withPrefabValues(Certificate.class,
							TestUtils.getCertificate(TestUtils.LETS_ENCRYPT_X1),
							TestUtils.getCertificate(TestUtils.LETS_ENCRYPT_X2))
					.verify();
		}

	}

}
