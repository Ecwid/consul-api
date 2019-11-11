package com.ecwid.consul.v1.transactions;

import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.session.SessionClient;
import com.ecwid.consul.v1.session.SessionConsulClient;
import com.ecwid.consul.v1.session.model.NewSession;
import com.ecwid.consul.v1.transactions.model.TxnResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionsConsulClientTest {

	@Test
	void commit() {
		ConsulRawClient rowClient = new ConsulRawClient();
		TransactionsConsulClient client = new TransactionsConsulClient(rowClient);

		SessionClient sessionClient = new SessionConsulClient(rowClient);
		NewSession newSession = new NewSession();
		newSession.setName("LOCK_SESSIONRawResponse_NAME");
		String session = sessionClient.sessionCreate(newSession, null).getValue();
		String key = "KEY_TO_BE_LOCK";
		ParamBuilder builder = ParamBuilder.getInstance()
			.kvLock(key,  "_LOCK", session)
			.kvGet(key)
			.kvSet(key,  "_MODIFY")
			.kvUnlock(key,  "_UNLOCK", session)
			.kvDelete(key);

		Response<TxnResult> resultResponse = client.commit(builder);
		System.out.println(resultResponse.getValue());
		sessionClient.sessionDestroy(session, null);
	}
}
