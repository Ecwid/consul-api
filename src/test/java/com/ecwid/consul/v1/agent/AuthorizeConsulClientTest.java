package com.ecwid.consul.v1.agent;

import com.ecwid.consul.ConsulTestConstants;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.AuthorizeRequest;
import com.ecwid.consul.v1.agent.model.AuthorizeResponse;
import com.pszymczyk.consul.ConsulProcess;
import com.pszymczyk.consul.ConsulStarterBuilder;
import com.pszymczyk.consul.infrastructure.Ports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthorizeConsulClientTest {

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
	public void testAuthorize() {
		AuthorizeRequest request = new AuthorizeRequest();
		request.setTarget("db");
		request.setClientCertUri("spiffe://dc1-7e567ac2-551d-463f-8497-f78972856fc1.consul/ns/default/dc/dc1/svc/web");
		request.setClientCertSerial("04:00:00:00:00:01:15:4b:5a:c3:94");

		Response<AuthorizeResponse> response = consulClient.agentAuthorize(request);

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getValue());

		AuthorizeResponse responseValue = response.getValue();

		Assertions.assertTrue(responseValue.isAuthorized());
	}

}
