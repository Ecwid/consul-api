package com.ecwid.consul.v1;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.ecwid.consul.v1.QueryParams.Builder;

public class QueryParamsTest {
	@Test
	public void queryParamsBuilder_ShouldReturnAllDefaults_WhenNoValuesAdded() {
		final ConsistencyMode EXPECTED_MODE = ConsistencyMode.DEFAULT;
		final long EXPECTED_INDEX = -1;
		final long EXPECTED_WAIT_TIME = -1;

		Builder builder = Builder.builder();

		QueryParams actual = builder.build();

		assertNull(actual.getDatacenter());
		assertEquals(actual.getConsistencyMode(), EXPECTED_MODE);
		assertEquals(actual.getWaitTime(), EXPECTED_WAIT_TIME);
		assertEquals(actual.getIndex(), EXPECTED_INDEX);
	}

	@Test
	public void queryParamsBuilder_ShouldReturnQueryParams_WithCorrectValuesApplied() {
		final String EXPECTED_DATACENTER = "testDC";
		final ConsistencyMode EXPECTED_MODE = ConsistencyMode.CONSISTENT;
		final long EXPECTED_INDEX = 100;
		final long EXPECTED_WAIT_TIME = 10000;

		Builder builder = Builder.builder();
		QueryParams actual = builder.setDatacenter(EXPECTED_DATACENTER)
			.setConsistencyMode(EXPECTED_MODE)
			.setWaitTime(EXPECTED_WAIT_TIME)
			.setIndex(EXPECTED_INDEX)
			.build();

		assertEquals(actual.getDatacenter(), EXPECTED_DATACENTER);
		assertEquals(actual.getConsistencyMode(), EXPECTED_MODE);
		assertEquals(actual.getIndex(), EXPECTED_INDEX);
		assertEquals(actual.getWaitTime(), EXPECTED_WAIT_TIME);
	}
}
