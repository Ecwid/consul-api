package com.ecwid.consul.v1.transactions;

import com.ecwid.consul.ConsulTestConstants;
import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.KeyValueConsulClient;
import com.ecwid.consul.v1.session.SessionClient;
import com.ecwid.consul.v1.session.SessionConsulClient;
import com.ecwid.consul.v1.session.model.NewSession;
import com.ecwid.consul.v1.transactions.model.TxnResult;
import com.pszymczyk.consul.ConsulProcess;
import com.pszymczyk.consul.ConsulStarterBuilder;
import com.pszymczyk.consul.infrastructure.Ports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TransactionsConsulClientTest {

	private ConsulProcess consul;
	private int port = Ports.nextAvailable();

	private ConsulRawClient consulClient;
	private TransactionsConsulClient client;
	SessionClient sessionClient;

	@BeforeEach
	void setUp() {
		consulClient = new ConsulRawClient("localhost", port);

		consul = ConsulStarterBuilder.consulStarter()
			.withConsulVersion(ConsulTestConstants.CONSUL_VERSION)
			.withHttpPort(port)
			.build()
			.start();
		client = new TransactionsConsulClient(consulClient);
		sessionClient = new SessionConsulClient(consulClient);
	}

	@AfterEach
	void tearDown() {
		consul.close();
	}


	@Test
	void commit() {
		NewSession newSession = new NewSession();
		newSession.setName("LOCK_SESSIONRawResponse_NAME");
		String session = sessionClient.sessionCreate(newSession, null).getValue();
		String key = "KEY_TO_BE_LOCK";
		ParamBuilder builder = ParamBuilder.getInstance()
			.kvLock(key, "_LOCK", session)
			.kvGet(key)
			.kvSet(key, "_MODIFY")
			.kvUnlock(key, "_UNLOCK", session)
			// if delete success that is no result report
			.kvDelete(key);

		Response<TxnResult> resultResponse = client.commit(builder);
		sessionClient.sessionDestroy(session, null);

		assertEquals(4, resultResponse.getValue().getResults().size());
	}
}
