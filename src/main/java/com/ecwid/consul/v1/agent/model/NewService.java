package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 * @author Spencer Gibb (spencer@gibb.us)
 */
public class NewService {

	public static class Check {

		@SerializedName("Script")
		private String script;
		
		@SerializedName("DockerContainerID")
		private String dockerContainerID;
		
		@SerializedName("Shell")
		private String shell;

		@SerializedName("Interval")
		private String interval;

		@SerializedName("TTL")
		private String ttl;

		@SerializedName("HTTP")
		private String http;

		@SerializedName("Method")
		private String method;

		@SerializedName("Header")
		private Map<String, List<String>> header;

		@SerializedName("TCP")
		private String tcp;

		@SerializedName("Timeout")
		private String timeout;

		@SerializedName("DeregisterCriticalServiceAfter")
		private String deregisterCriticalServiceAfter;

		@SerializedName("TLSSkipVerify")
		private Boolean tlsSkipVerify;

		@SerializedName("Status")
		private String status;

		@SerializedName("GRPC")
		private String grpc;

		@SerializedName("GRPCUseTLS")
		private Boolean grpcUseTLS;

		public String getScript() {
			return script;
		}

		public void setScript(String script) {
			this.script = script;
		}
		
		public String getDockerContainerID() {
		    	return dockerContainerID;
		}

		public void setDockerContainerID(String dockerContainerID) {
		   	this.dockerContainerID = dockerContainerID;
		}

		public String getShell() {
		    	return shell;
		}

		public void setShell(String shell) {
		    	this.shell = shell;
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

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public Map<String, List<String>> getHeader() {
			return header;
		}

		public void setHeader(Map<String, List<String>> header) {
			this.header = header;
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

		public String getDeregisterCriticalServiceAfter() {
			return deregisterCriticalServiceAfter;
		}

		public void setDeregisterCriticalServiceAfter(String deregisterCriticalServiceAfter) {
			this.deregisterCriticalServiceAfter = deregisterCriticalServiceAfter;
		}

		public Boolean getTlsSkipVerify() {
			return tlsSkipVerify;
		}

		public void setTlsSkipVerify(Boolean tlsSkipVerify) {
			this.tlsSkipVerify = tlsSkipVerify;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getGrpc() { return grpc; }

		public void setGrpc(String grpc) { this.grpc = grpc; }

		public Boolean getGrpcUseTLS() { return grpcUseTLS; }

		public void setGrpcUseTLS(Boolean grpcUseTLS) { this.grpcUseTLS = grpcUseTLS; }

		@Override
		public String toString() {
			return "Check{" +
				"script='" + script + '\'' +
				", dockerContainerID='" + dockerContainerID + '\'' +
				", shell='" + shell + '\'' +
				", interval='" + interval + '\'' +
				", ttl='" + ttl + '\'' +
				", http='" + http + '\'' +
				", method='" + method + '\'' +
				", header=" + header +
				", tcp='" + tcp + '\'' +
				", timeout='" + timeout + '\'' +
				", deregisterCriticalServiceAfter='" + deregisterCriticalServiceAfter + '\'' +
				", tlsSkipVerify=" + tlsSkipVerify +
				", status='" + status + '\'' +
				", grpc='" + grpc + '\'' +
				", grpcUseTLS=" + grpcUseTLS +
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

	@SerializedName("Meta")
	private Map<String, String> meta;

	@SerializedName("Port")
	private Integer port;

	@SerializedName("EnableTagOverride")
	private Boolean enableTagOverride;

	@SerializedName("Check")
	private Check check;

	@SerializedName("Checks")
	private List<Check> checks;

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

	public Map<String, String> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Boolean getEnableTagOverride() {
		return enableTagOverride;
	}

	public void setEnableTagOverride(Boolean enableTagOverride) {
		this.enableTagOverride = enableTagOverride;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

	public List<Check> getChecks() {
		return checks;
	}

	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}

	@Override
	public String toString() {
		return "NewService{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", tags=" + tags +
				", address='" + address + '\'' +
				", meta=" + meta +
				", port=" + port +
				", enableTagOverride=" + enableTagOverride +
				", check=" + check +
				", checks=" + checks +
				'}';
	}
}
