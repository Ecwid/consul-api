package com.ecwid.consul;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SingleUrlParametersTest {

	@Test
	public void testToUrlParameters() throws Exception {
		UrlParameters parameters = new SingleUrlParameters("key", "value");
		Assert.assertEquals(Arrays.asList("key=value"), parameters.toUrlParameters());

		parameters = new SingleUrlParameters("key");
		Assert.assertEquals(Arrays.asList("key"), parameters.toUrlParameters());

	}
}