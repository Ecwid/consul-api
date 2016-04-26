package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 * @author Spencer Gibb (spencer@gibb.us)
 */
public class NewService {

	public static class Check {
		@SerializedName("Script")
		private String script;
		@SerializedName("Interval")
		private String interval;
		@SerializedName("TTL")
		private String ttl;
		@SerializedName("HTTP")
		private String http;
		@SerializedName("TCP")
		private String tcp;
        @SerializedName("Timeout")
        private String timeout;

		public String getScript() {
			return script;
		}

		public void setScript(String script) {
			this.script = script;
		}

		public String getInterval() {
			return interval;
		}

		public void setInterval(String interval) {
			this.interval = interval;
		}

		public String getTtl() {
			return ttl;
		}

		public void setTtl(String ttl) {
			this.ttl = ttl;
		}

		public String getHttp() {
			return http;
		}

		public void setHttp(String http) {
			this.http = http;
		}

		public String getTcp() {
			return tcp;
		}

		public void setTcp(String tcp) {
			this.tcp = tcp;
		}

		public String getTimeout() {
          return timeout;
        }

        public void setTimeout(String timeout) {
          this.timeout = timeout;
        }

        @Override
		public String toString() {
			return "Check{" +
					"script='" + script + '\'' +
					", interval=" + interval +
					", ttl=" + ttl +
					", http=" + http +
					", tcp=" + tcp +
                    ", timeout=" + timeout +
					'}';
		}
	}

	@SerializedName("ID")
	private String id;

	@SerializedName("Name")
	private String name;

	@SerializedName("Tags")
	private List<String> tags;

	@SerializedName("Address")
	private String address;

	@SerializedName("Port")
	private Integer port;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
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
				", address='" + address + '\'' +
				", port=" + port +
				", check=" + check +
				'}';
	}
}
