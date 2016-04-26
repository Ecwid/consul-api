package com.ecwid.consul;

import org.junit.Assert;
import org.junit.Test;

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

	@Test
	public void testUnsignedLongParsing() throws Exception {
		checkUnsignedLongRange(-100, 100);
		checkUnsignedLongRange(Long.MIN_VALUE, Long.MIN_VALUE + 100);
		checkUnsignedLongRange(Long.MAX_VALUE - 100, Long.MAX_VALUE);
	}

	private void checkUnsignedLongRange(long start, long end) throws Exception {
		for (long l = start; l < end; l++) {
			String str = Utils.toUnsignedString(l);
			long l2 = Utils.parseUnsignedLong(str);
			Assert.assertEquals(l, l2);

			if (l >= 0) {
				Assert.assertEquals(Long.toString(l), str);
				Assert.assertEquals(l, l2);
			}
		}
	}
}