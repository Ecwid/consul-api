package com.ecwid.consul;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Utils {

	public static String generateUrl(String baseUrl, UrlParameters... params) {
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

}
