package com.ecwid.consul.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Base64;

public class CertificateTypeAdapter extends TypeAdapter<Certificate> {

	private static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";

	private static final String END_CERT = "-----END CERTIFICATE-----";

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private static final int LINE_LENGTH = 64;

	@Override
	public void write(JsonWriter out, Certificate value) throws IOException {
		if (value == null) {
			out.nullValue();
		} else {
			try {
				Base64.Encoder encoder = Base64.getMimeEncoder(
						LINE_LENGTH, LINE_SEPARATOR.getBytes(StandardCharsets.UTF_8));
				final String encoded = BEGIN_CERT
						+ LINE_SEPARATOR
						+ new String(encoder.encode(value.getEncoded()), StandardCharsets.UTF_8)
						+ LINE_SEPARATOR
						+ END_CERT;
				out.value(encoded);
			} catch (CertificateEncodingException ex) {
				throw new IOException(ex);
			}
		}
	}

	@Override
	public Certificate read(JsonReader in) throws IOException {
		if (in.peek() == JsonToken.NULL) {
			in.nextNull();
			return null;
		} else {
			String certificate = in.nextString();
			try {
				return CertificateFactory.getInstance("X509")
						.generateCertificate(
								new ByteArrayInputStream(
										certificate.getBytes(StandardCharsets.UTF_8)));
			} catch (CertificateException ex) {
				throw new IOException(ex);
			}
		}
	}

}
