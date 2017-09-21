package com.ecwid.consul.transport;

public final class TLSConfig {

	public enum KeyStoreInstanceType {
		JKS, JCEKS, PKCS12, PKCS11, DKS
	}

	private final KeyStoreInstanceType keyStoreInstanceType;
	private final String certificatePath;
	private final String certificatePassword;
	private final String keyStorePath;
	private final String keyStorePassword;

	public TLSConfig(KeyStoreInstanceType keyStoreInstanceType, String certificatePath, String certificatePassword, String keyStorePath,
					 String keyStorePassword) {
		this.keyStoreInstanceType = keyStoreInstanceType;
		this.certificatePath = certificatePath;
		this.certificatePassword = certificatePassword;
		this.keyStorePath = keyStorePath;
		this.keyStorePassword = keyStorePassword;
	}

	public KeyStoreInstanceType getKeyStoreInstanceType() {
		return keyStoreInstanceType;
	}

	public String getCertificatePath() {
		return certificatePath;
	}

	public String getCertificatePassword() {
		return certificatePassword;
	}

	public String getKeyStorePath() {
		return keyStorePath;
	}

	public String getKeyStorePassword() {
		return keyStorePassword;
	}
}
