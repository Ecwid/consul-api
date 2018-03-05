package com.ecwid.consul.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class GsonFactory {

	private static final Gson GSON = new Gson();

	public static Gson getGson() {
		return GSON;
	}

}
