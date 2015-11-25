package com.ecwid.consul.transport;

public class TLSConfig {

    public enum KeyStoreInstanceType {
	JKS, JCEKS, PKCS12, PKCS11, DKS
    }

    private KeyStoreInstanceType keyStoreInstanceType;

    private String certficatePath;

    private String certificatePassword;

    private String keyStorePath;

    private String keyStorePassword;

    private int sslPort;

    public TLSConfig(KeyStoreInstanceType keyStoreInstanceType, String certficatePath, String certificatePassword, String keyStorePath,
            String keyStorePassword, int sslPort) {
	super();
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

    public void setKeyStoreInstanceType(KeyStoreInstanceType keyStoreInstanceType) {
	this.keyStoreInstanceType = keyStoreInstanceType;
    }

    public String getCertficatePath() {
	return certficatePath;
    }

    public void setCertficatePath(String certficatePath) {
	this.certficatePath = certficatePath;
    }

    public String getCertificatePassword() {
	return certificatePassword;
    }

    public void setCertificatePassword(String certificatePassword) {
	this.certificatePassword = certificatePassword;
    }

    public String getKeyStorePath() {
	return keyStorePath;
    }

    public void setKeyStorePath(String keyStorePath) {
	this.keyStorePath = keyStorePath;
    }

    public String getKeyStorePassword() {
	return keyStorePassword;
    }

    public void setKeyStorePassword(String keyStorePassword) {
	this.keyStorePassword = keyStorePassword;
    }

    public int getSslPort() {
	return sslPort;
    }

    public void setSslPort(int sslPort) {
	this.sslPort = sslPort;
    }

}
