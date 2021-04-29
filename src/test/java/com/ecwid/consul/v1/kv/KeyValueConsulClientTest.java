package com.ecwid.consul.v1.kv;

import com.ecwid.consul.ConsulTestConstants;
import com.ecwid.consul.v1.kv.model.DeleteParams;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.pszymczyk.consul.ConsulProcess;
import com.pszymczyk.consul.ConsulStarterBuilder;
import com.pszymczyk.consul.infrastructure.Ports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class KeyValueConsulClientTest {

	private static final Random rnd = new Random();

	private ConsulProcess consul;
	private int port = Ports.nextAvailable();

	private KeyValueConsulClient consulClient = new KeyValueConsulClient("localhost", port);

	@BeforeEach
	void setUp() {
		consul = ConsulStarterBuilder.consulStarter()
			.withConsulVersion(ConsulTestConstants.CONSUL_VERSION)
			.withHttpPort(port)
			.build()
			.start();
	}

	@AfterEach
	void tearDown() {
		consul.close();
	}

	@Test
	void testSetKVBinaryValue() throws Exception {
		final String testKey = "test_key";
		final byte[] testValue = new byte[100];
		rnd.nextBytes(testValue);

		// Make sure there is no such key before test running
		Assertions.assertNull(consulClient.getKVValue(testKey).getValue());
		// Set the key
		consulClient.setKVBinaryValue(testKey, testValue);
		// Make sure test key exists
		Assertions.assertArrayEquals(consulClient.getKVBinaryValue(testKey).getValue().getValue(), testValue);
	}

	@Test
	void testDeleteKvValue() throws Exception {
		final String testKey = "test_key";
		final String testValue = "test_value";

		// Make sure there is no such key before test running
		Assertions.assertNull(consulClient.getKVValue(testKey).getValue());

		// Set the key
		consulClient.setKVValue(testKey, testValue);
		// Make sure test key exists
		Assertions.assertEquals(consulClient.getKVValue(testKey).getValue().getDecodedValue(), testValue);

		// Delete key
		consulClient.deleteKVValue(testKey);
		// Make sure there is no such key before test running
		Assertions.assertNull(consulClient.getKVValue(testKey).getValue());
	}

	@Test
	void testDeleteKvValueCas() {
		final String testKey = "test_key";
		final String testValue = "test_value";
		final String testModifiedValue = "test_value2";

		// Make sure there is no such key before test running
		Assertions.assertNull(consulClient.getKVValue(testKey).getValue());

		// Set the key
		consulClient.setKVValue(testKey, testValue);
		// Make sure test key exists
		GetValue getValue = consulClient.getKVValue(testKey).getValue();
		Assertions.assertEquals(testValue, getValue.getDecodedValue());

		// Modify the value
		consulClient.setKVValue(testKey, testModifiedValue);
		// Make sure it returns modified value
		GetValue modifiedGetValue = consulClient.getKVValue(testKey).getValue();
		Assertions.assertEquals(testModifiedValue, modifiedGetValue.getDecodedValue());
		// Verify that consul changed the modified index
		Assertions.assertNotEquals(getValue.getModifyIndex(), modifiedGetValue.getModifyIndex());

		// Delete key with the old modify index
		DeleteParams deleteOldIndex = new DeleteParams();
		deleteOldIndex.setCas(getValue.getModifyIndex());
		consulClient.deleteKVValue(testKey, deleteOldIndex);
		// Make sure the key is still there
		Assertions.assertEquals(testModifiedValue, consulClient.getKVValue(testKey).getValue().getDecodedValue());

		// Delete the key with the new modify index
		DeleteParams deleteNewIndex = new DeleteParams();
		deleteNewIndex.setCas(modifiedGetValue.getModifyIndex());
		consulClient.deleteKVValue(testKey, deleteNewIndex);
		// Make sure the key is gone
		Assertions.assertNull(consulClient.getKVValue(testKey).getValue());
	}

	@Test
	void testDeleteKvValues() throws Exception {
		final String testKeyPrefix = "test_key";
		final String testValue = "test_value";

		// Prepare test keys under testKeyPrefix prefix
		for (int i = 0; i < 10; i++) {
			final String testKey = testKeyPrefix + "/" + i;
			// Make sure there is no such key before test running
			Assertions.assertNull(consulClient.getKVValue(testKey).getValue());

			// Set the key
			consulClient.setKVValue(testKey, testValue);

			// Make sure test key exists
			Assertions.assertEquals(consulClient.getKVValue(testKey).getValue().getDecodedValue(), testValue);
		}

		// Delete all keys in single shot
		consulClient.deleteKVValues(testKeyPrefix);

		// Make sure all keys have been deleted
		Assertions.assertNull(consulClient.getKVKeysOnly(testKeyPrefix).getValue());
	}

}
