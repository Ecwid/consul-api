package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class NewCheck {

	@SerializedName("ID")
	private String id;

	@SerializedName("Name")
	private String name;

	@SerializedName("Notes")
	private String notes;

	@SerializedName("Script")
	private String script;

	@SerializedName("HTTP")
	private String http;

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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
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

	@Override
	public String toString() {
		return "NewCheck{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", notes='" + notes + '\'' +
				", script='" + script + '\'' +
				", http='" + http + '\'' +
				", tcp='" + tcp + '\'' +
				", dockerContainerID='" + dockerContainerID + '\'' +
				", shell='" + shell + '\'' +
				", interval='" + interval + '\'' +
				", timeout='" + timeout + '\'' +
				", ttl='" + ttl + '\'' +
				'}';
	}
}
