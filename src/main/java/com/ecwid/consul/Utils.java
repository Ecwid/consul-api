package com.ecwid.consul;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Utils {

	public static String encodeValue(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("So strange - every JVM has to support UTF-8 encoding.");
		}
	}

	public static String encodeUrl(String str) {
		try {
			URL url = new URL(str);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			return uri.toASCIIString();
		} catch (Exception e) {
			throw new RuntimeException("Can't encode url", e);
		}
	}

	public static String generateUrl(String baseUrl, UrlParameters... params) {
		return generateUrl(baseUrl, Arrays.asList(params));
	}

	public static String generateUrl(String baseUrl, List<UrlParameters> params) {
		if (params == null) {
			return baseUrl;
		}

		List<String> allParams = new ArrayList<String>();
		for (UrlParameters item : params) {
			if (item != null) {
				allParams.addAll(item.toUrlParameters());
			}
		}

		// construct the whole url
		StringBuilder result = new StringBuilder(baseUrl);

		Iterator<String> paramsIterator = allParams.iterator();
		if (paramsIterator.hasNext()) {
			result.append("?").append(paramsIterator.next());
			while (paramsIterator.hasNext()) {
				result.append("&").append(paramsIterator.next());
			}
		}
		return result.toString();
	}

	public static String toUnsignedString(long l) {
		if (l >= 0) {
			return Long.toString(l);
		} else {
			long quot = (l >>> 1) / 5;
			long rem = l - quot * 10;
			return Long.toString(quot) + rem;
		}
	}

	public static long parseUnsignedLong(String s) {
		if (s.charAt(0) == '-') {
			throw new NumberFormatException("An unsigned long was expected. Cannot parse negative number " + s);
		}
		int length = s.length();
		// Long.MAX_VALUE is 19 digits in length so anything
		// shorter than that is trivial to parse.
		if (length < 19) {
			return Long.parseLong(s);
		}
		long front = Long.parseLong(s.substring(0, length - 1));
		int onesDigit = Character.digit(s.charAt(length - 1), 10);
		if (onesDigit < 0) {
			throw new NumberFormatException("Invalid last digit for " + onesDigit);
		}
		long result = front * 10 + onesDigit;
		if (compareLong(result + Long.MIN_VALUE, front + Long.MIN_VALUE) < 0) {
			throw new NumberFormatException("The number " + s + " is greater than 2^64");
		}
		return result;
	}

	private static int compareLong(long x, long y) {
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}

	public static String toSecondsString(long waitTime) {
		return String.valueOf(waitTime) + "s";
	}

	public static String assembleAgentAddress(String host, int port, String path) {
		String agentPath = "";
		if (path != null && !path.trim().isEmpty()) {
			agentPath = "/" + path;
		}

		return String.format("%s:%d%s", host, port, agentPath);
	}
}
