package com.ecwid.consul.transport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;

import com.ecwid.consul.transport.TLSConfig.KeyStoreInstanceType;

/**
 * Default HTTPS client This class is thread safe
 *
 * @author Carlos Augusto Ribeiro Mantovani (gutomantovani@gmail.com)
 */
public final class DefaultHttpsTransport extends AbstractHttpTransport {

	public DefaultHttpsTransport(TLSConfig tlsConfig) {
		KeyStore clientStore;
		try {
			clientStore = KeyStore.getInstance(tlsConfig.getKeyStoreInstanceType().name());

			clientStore.load(new FileInputStream(tlsConfig.getCertficatePath()), tlsConfig.getCertificatePassword().toCharArray());

			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(clientStore, "".toCharArray());
			KeyManager[] kms = kmf.getKeyManagers();

			KeyStore trustStore = KeyStore.getInstance(KeyStoreInstanceType.JKS.name());
			trustStore.load(new FileInputStream(tlsConfig.getKeyStorePath()), tlsConfig.getKeyStorePassword().toCharArray());

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(trustStore);
			TrustManager[] tms = tmf.getTrustManagers();

			SSLContext sslContext = null;
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kms, tms, new SecureRandom());

			SSLSocketFactory sf = new SSLSocketFactory(sslContext);
			Scheme sch = new Scheme("https", sf, tlsConfig.getSslPort());
			this.httpClient.getConnectionManager().getSchemeRegistry().register(sch);

		} catch (KeyStoreException e) {
			throw new TransportException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new TransportException(e);
		} catch (CertificateException e) {
			throw new TransportException(e);
		} catch (FileNotFoundException e) {
			throw new TransportException(e);
		} catch (IOException e) {
			throw new TransportException(e);
		} catch (UnrecoverableKeyException e) {
			throw new TransportException(e);
		} catch (KeyManagementException e) {
			throw new TransportException(e);
		}
	}

	public DefaultHttpsTransport(HttpClient httpClient) {
		super(httpClient);
	}

}
