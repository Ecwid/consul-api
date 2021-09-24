package com.ecwid.consul.v1.connect;

import com.ecwid.consul.ConsulTestConstants;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.connect.model.CaConfigurationRequest;
import com.ecwid.consul.v1.connect.model.CaConfigurationResponse;
import com.ecwid.consul.v1.connect.model.CaRoots;
import com.pszymczyk.consul.ConsulProcess;
import com.pszymczyk.consul.ConsulStarterBuilder;
import com.pszymczyk.consul.infrastructure.Ports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConnectConsulClientTest {

	private static final String ACL_MASTER_TOKEN = "mastertoken";

	private ConsulProcess consul;

	private final int port = Ports.nextAvailable();

	private final ConnectConsulClient consulClient = new ConnectConsulClient("localhost", port);

	@BeforeEach
	public void setUp() {
		String customConfiguration =
				"{ \"acl_master_token\": \"" + ACL_MASTER_TOKEN + "\""
						+ ", \"acl_datacenter\": \"dc-test\""
						+ ", \"datacenter\": \"dc-test\" }";

		consul = ConsulStarterBuilder.consulStarter()
				.withConsulVersion(ConsulTestConstants.CONSUL_VERSION)
				.withHttpPort(port)
				.withCustomConfig(customConfiguration)
				.build()
				.start();
	}

	@AfterEach
	public void tearDown() {
		consul.close();
	}

	@Test
	public void testListCaRoots() {
		Response<CaRoots> response = consulClient.connectListCaRoots();

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getValue());

		CaRoots responseValue = response.getValue();

		Assertions.assertNotNull(responseValue.getTrustDomain());
		Assertions.assertFalse(responseValue.getRoots().isEmpty());
	}

	@Test
	public void testGetCaConfiguration() {
		Response<CaConfigurationResponse> response = consulClient.connectGetCaConfiguration();

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getValue());
	}

	@Test
	public void testUpdateCaConfiguration() {
		Response<CaConfigurationResponse> caConfiguration = consulClient.connectGetCaConfiguration();
		CaConfigurationRequest request = new CaConfigurationRequest();
		request.setProvider(caConfiguration.getValue().getProvider());
		request.setConfig(caConfiguration.getValue().getConfig());
		Response<CaConfigurationResponse> response = consulClient.connectUpdateCaConfiguration(request);
		Assertions.assertNotNull(response);
	}

}
