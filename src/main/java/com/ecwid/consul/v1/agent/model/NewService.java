package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class NewService {

	public static class Check {
		@SerializedName("Script")
		private String script;
		@SerializedName("Interval")
		private Integer interval;
		@SerializedName("TTL")
		private Integer ttl;

		public String getScript() {
			return script;
		}

		public void setScript(String script) {
			this.script = script;
		}

		public Integer getInterval() {
			return interval;
		}

		public void setInterval(Integer interval) {
			this.interval = interval;
		}

		public Integer getTtl() {
			return ttl;
		}

		public void setTtl(Integer ttl) {
			this.ttl = ttl;
		}

		@Override
		public String toString() {
			return "Check{" +
					"script='" + script + '\'' +
					", interval=" + interval +
					", ttl=" + ttl +
					'}';
		}
	}

	@SerializedName("ID")
	private String id;

	@SerializedName("Name")
	private String name;

	@SerializedName("Tags")
	private List<String> tags;

	@SerializedName("Port")
	private int port;

	@SerializedName("Check")
	private Check check;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "NewService{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", tags=" + tags +
				", port=" + port +
				", check=" + check +
				'}';
	}
}
