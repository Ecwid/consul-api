package com.ecwid.consul;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

	@Test
	public void testGenerateUrl_Simple() throws Exception {
		Assert.assertEquals("/some-url", Utils.generateUrl("/some-url"));
		Assert.assertEquals("/some-url", Utils.generateUrl("/some-url", (UrlParameters) null));
		Assert.assertEquals("/some-url", Utils.generateUrl("/some-url", null, null));
	}

	@Test
	public void testGenerateUrl_Parametrized() throws Exception {
		UrlParameters first = new SingleUrlParameters("key", "value");
		UrlParameters second = new SingleUrlParameters("key2");
		Assert.assertEquals("/some-url?key=value&key2", Utils.generateUrl("/some-url", first, second));
	}

	@Test
	public void testGenerateUrl_Encoded() throws Exception {
		UrlParameters first = new SingleUrlParameters("key", "value value");
		UrlParameters second = new SingleUrlParameters("key2");
		UrlParameters third = new SingleUrlParameters("key3", "value!value");
		Assert.assertEquals("/some-url?key=value+value&key2&key3=value%21value", Utils.generateUrl("/some-url", first, second, third));
	}
}