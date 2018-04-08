package com.ecwid.consul.v1;

import com.ecwid.consul.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.ecwid.consul.v1.QueryParams.Builder;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class QueryParamsTest {
	@Test
	public void queryParamsBuilder_ShouldReturnAllDefaults_WhenNoValuesAdded() {
		// Given
		final ConsistencyMode EXPECTED_MODE = ConsistencyMode.DEFAULT;
		final long EXPECTED_INDEX = -1;
		final long EXPECTED_WAIT_TIME = -1;
		final String EXPECTED_NEAR = null;

		// When
		QueryParams actual = Builder.builder().build();

		// Then
		assertNull(actual.getDatacenter());
		assertEquals(actual.getConsistencyMode(), EXPECTED_MODE);
		assertEquals(actual.getWaitTime(), EXPECTED_WAIT_TIME);
		assertEquals(actual.getIndex(), EXPECTED_INDEX);
		assertEquals(actual.getNear(), EXPECTED_NEAR);
	}

	@Test
	public void queryParamsBuilder_ShouldReturnQueryParams_WithCorrectValuesApplied() {
		// Given
		final String EXPECTED_DATACENTER = "testDC";
		final ConsistencyMode EXPECTED_MODE = ConsistencyMode.CONSISTENT;
		final long EXPECTED_INDEX = 100;
		final long EXPECTED_WAIT_TIME = 10000;
		final String EXPECTED_NEAR = "_agent";

		// When
		QueryParams actual = Builder.builder()
				.setDatacenter(EXPECTED_DATACENTER)
				.setConsistencyMode(EXPECTED_MODE)
				.setWaitTime(EXPECTED_WAIT_TIME)
				.setIndex(EXPECTED_INDEX)
				.setNear(EXPECTED_NEAR)
				.build();

		// Then
		assertEquals(actual.getDatacenter(), EXPECTED_DATACENTER);
		assertEquals(actual.getConsistencyMode(), EXPECTED_MODE);
		assertEquals(actual.getIndex(), EXPECTED_INDEX);
		assertEquals(actual.getWaitTime(), EXPECTED_WAIT_TIME);
		assertEquals(actual.getNear(), EXPECTED_NEAR);
	}

	@Test
	public void queryParamsToUrlParameters_ShouldContainSetQueryParams_WithCorrectValuesApplied() {
		// Given
		final String EXPECTED_DATACENTER = "testDC";
		final ConsistencyMode EXPECTED_MODE = ConsistencyMode.CONSISTENT;
		final long EXPECTED_WAIT = 1000L;
		final long EXPECTED_INDEX = 2000L;
		final String EXPECTED_NEAR = "_agent";

		// When
		List<String> urlParameters = Builder.builder()
				.setDatacenter(EXPECTED_DATACENTER)
				.setConsistencyMode(EXPECTED_MODE)
				.setWaitTime(EXPECTED_WAIT)
				.setIndex(EXPECTED_INDEX)
				.setNear(EXPECTED_NEAR)
				.build()
				.toUrlParameters();

		// Then
		assertThat(urlParameters, hasItem("dc=" + EXPECTED_DATACENTER));
		assertThat(urlParameters, hasItem(EXPECTED_MODE.name().toLowerCase()));
		assertThat(urlParameters, hasItem("wait=" + Utils.toSecondsString(EXPECTED_WAIT)));
		assertThat(urlParameters, hasItem("index=" + EXPECTED_INDEX));
		assertThat(urlParameters, hasItem("near=" + EXPECTED_NEAR));
	}
}
