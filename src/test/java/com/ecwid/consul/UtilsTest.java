package com.ecwid.consul;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

	@Test
	public void testGenerateUrl() throws Exception {
		Assert.assertEquals("/some-url", Utils.generateUrl("/some-url"));
		Assert.assertEquals("/some-url", Utils.generateUrl("/some-url", (UrlParameters) null));
		Assert.assertEquals("/some-url", Utils.generateUrl("/some-url", null, null));

		//
		UrlParameters first = new SingleUrlParameters("key", "value");
		UrlParameters second = new SingleUrlParameters("key2");
		Assert.assertEquals("/some-url?key=value&key2", Utils.generateUrl("/some-url", first, second));
	}
}