package com.ecwid.consul;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class TestUtils {

	public static final String LETS_ENCRYPT_X1 = "isrg-root-x1.pem";

	public static final String LETS_ENCRYPT_X2 = "isrg-root-x2.pem";

	public static Certificate getCertificate(String resourceName) throws IOException {
		String certificateAsString = getCertificateAsString(resourceName);
		try {
			return CertificateFactory.getInstance("X509")
					.generateCertificate(
							new ByteArrayInputStream(
									certificateAsString.getBytes(StandardCharsets.UTF_8)));
		} catch (CertificateException ex) {
			throw new IOException(ex);
		}
	}

	public static String getCertificateAsString(String resourceName) throws IOException {
		return getResourceAsString(resourceName, TestUtils.class);
	}

	public static String getResourceAsString(String resourceName, Class<?> resourceClass) throws IOException {
		try (InputStream is = resourceClass.getResourceAsStream(resourceName)) {
			if (is == null) {
				throw new IllegalArgumentException(String.format(
						"Could not find resource [%s] for class [%s].",
						resourceName, resourceClass));
			}

			return streamToString(is);
		}
	}

	public static String streamToString(InputStream stream) throws IOException {
		StringBuilder builder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line).append("\n");
			}
			return builder.toString();
		}
	}

}
