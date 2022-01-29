package com.ecwid.consul.v1.agent;

import com.ecwid.consul.ConsulTestConstants;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.AuthorizeRequest;
import com.ecwid.consul.v1.agent.model.AuthorizeResponse;
import com.ecwid.consul.v1.agent.model.LeafCertificate;
import com.pszymczyk.consul.ConsulProcess;
import com.pszymczyk.consul.ConsulStarterBuilder;
import com.pszymczyk.consul.infrastructure.Ports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeafCertificateConsulClientTest {

	private static final String ACL_MASTER_TOKEN = "mastertoken";

	private ConsulProcess consul;

	private final int port = Ports.nextAvailable();

	private final AgentConsulClient consulClient = new AgentConsulClient("localhost", port);

	@BeforeEach
	public void setUp() {
		String customConfiguration =
				"{ \"acl_master_token\": \"" + ACL_MASTER_TOKEN + "\"" +
						", \"acl_datacenter\": \"dc-test\"" +
						", \"datacenter\": \"dc-test\" }";

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
	public void testLeafCertificate() {
		Response<LeafCertificate> response = consulClient.agentLeafCertificate("db");

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getValue());

		LeafCertificate responseValue = response.getValue();

		Assertions.assertNotNull(responseValue.getCertPEM());
		Assertions.assertNotNull(responseValue.getPrivateKeyPEM());
	}

}
