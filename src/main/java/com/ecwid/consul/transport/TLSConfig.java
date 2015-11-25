package com.ecwid.consul.transport;

public final class TLSConfig {

	public enum KeyStoreInstanceType {
		JKS, JCEKS, PKCS12, PKCS11, DKS
	}

	private final KeyStoreInstanceType keyStoreInstanceType;
	private final String certficatePath;
	private final String certificatePassword;
	private final String keyStorePath;
	private final String keyStorePassword;
	private final int sslPort;

	public TLSConfig(KeyStoreInstanceType keyStoreInstanceType, String certficatePath, String certificatePassword, String keyStorePath,
					 String keyStorePassword, int sslPort) {
		this.keyStoreInstanceType = keyStoreInstanceType;
		this.certficatePath = certficatePath;
		this.certificatePassword = certificatePassword;
		this.keyStorePath = keyStorePath;
		this.keyStorePassword = keyStorePassword;
		this.sslPort = sslPort;
	}

	public KeyStoreInstanceType getKeyStoreInstanceType() {
		return keyStoreInstanceType;
	}

	public String getCertficatePath() {
		return certficatePath;
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

	public int getSslPort() {
		return sslPort;
	}
}
