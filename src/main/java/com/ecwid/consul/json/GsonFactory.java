package com.ecwid.consul.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class GsonFactory {

	private static final GsonBuilder GSON_BUILDER = new GsonBuilder();

	private static final ThreadLocal<Gson> GSON = new ThreadLocal<Gson>() {
		@Override
		protected Gson initialValue() {
			return GSON_BUILDER.create();
		}
	};

	public static Gson getGson() {
		return GSON.get();
	}

}
