package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class NewCheck {

	@SerializedName("ID")
	private String id;

	@SerializedName("Name")
	private String name;

	@SerializedName("ServiceID")
	private String serviceId;

	@SerializedName("Notes")
	private String notes;

	/**
	 * @deprecated Please use Args parameter instead
	 */
	@SerializedName("Script")
	@Deprecated
	private String script;

	@SerializedName("Args")
	private List<String> args;

	@SerializedName("HTTP")
	private String http;

	@SerializedName("Method")
	private String method;

	@SerializedName("Header")
	private Map<String, List<String>> header;

	@SerializedName("TCP")
	private String tcp;

	@SerializedName("DockerContainerID")
	private String dockerContainerID;

	@SerializedName("Shell")
	private String shell;

	@SerializedName("Interval")
	private String interval;

	@SerializedName("Timeout")
	private String timeout;

	@SerializedName("TTL")
	private String ttl;

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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @deprecated Please use Args parameter instead
	 */
	@Deprecated
	public String getScript() {
		return script;
	}

	/**
	 * @deprecated Please use Args parameter instead
	 */
	@Deprecated
	public void setScript(String script) {
		this.script = script;
	}

	public List<String> getArgs() {
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = args;
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

	public String getGrpc() {
		return grpc;
	}

	public void setGrpc(String grpc) {
		this.grpc = grpc;
	}

	public Boolean getGrpcUseTLS() {
		return grpcUseTLS;
	}

	public void setGrpcUseTLS(Boolean grpcUseTLS) {
		this.grpcUseTLS = grpcUseTLS;
	}

	@Override
	public String toString() {
		return "NewCheck{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", serviceId='" + serviceId + '\'' +
				", notes='" + notes + '\'' +
				", script='" + script + '\'' +
				", args=" + args +
				", http='" + http + '\'' +
				", method='" + method + '\'' +
				", header=" + header +
				", tcp='" + tcp + '\'' +
				", dockerContainerID='" + dockerContainerID + '\'' +
				", shell='" + shell + '\'' +
				", interval='" + interval + '\'' +
				", timeout='" + timeout + '\'' +
				", ttl='" + ttl + '\'' +
				", deregisterCriticalServiceAfter='" + deregisterCriticalServiceAfter + '\'' +
				", tlsSkipVerify=" + tlsSkipVerify +
				", status='" + status + '\'' +
				", grpc='" + grpc + '\'' +
				", grpcUseTLS=" + grpcUseTLS +
				'}';
	}
}
