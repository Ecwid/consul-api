package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Self {

	public enum LogLevel {
		TRACE,
		DEBUG,
		INFO,
		WARN,
		ERR
	}

	public static class Config {

		@SerializedName("Datacenter")
		private String datacenter;

		@SerializedName("NodeName")
		private String nodeName;

		@SerializedName("Revision")
		private String revision;

		@SerializedName("Server")
		private boolean server;

		@SerializedName("Version")
		private String version;

		public String getDatacenter() {
			return datacenter;
		}

		public void setDatacenter(String datacenter) {
			this.datacenter = datacenter;
		}

		public String getNodeName() {
			return nodeName;
		}

		public void setNodeName(String nodeName) {
			this.nodeName = nodeName;
		}

		public String getRevision() {
			return revision;
		}

		public void setRevision(String revision) {
			this.revision = revision;
		}

		public boolean isServer() {
			return server;
		}

		public void setServer(boolean server) {
			this.server = server;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		@Override
		public String toString() {
			return "DebugConfig{" +
					"datacenter='" + datacenter + "'" +
					", nodeName='" + nodeName + "'" +
					", revision='" + revision + '\'' +
					", server=" + server +
					", version='" + version + '\'' +
					'}';
		}

	}

	public static class DebugConfig {

		@SerializedName("Bootstrap")
		private boolean bootstrap;

		@SerializedName("DataDir")
		private String dataDir;

		@SerializedName("DNSRecursor")
		private String dnsRecursor;

		@SerializedName("DNSDomain")
		private String dnsDomain;

		@SerializedName("LogLevel")
		private LogLevel logLevel;

		@SerializedName("NodeID")
		private String nodeId;

		@SerializedName("ClientAddrs")
		private String[] clientAddresses;

		@SerializedName("BindAddr")
		private String bindAddress;

		@SerializedName("LeaveOnTerm")
		private boolean leaveOnTerm;

		@SerializedName("SkipLeaveOnInt")
		private boolean skipLeaveOnInt;

		@SerializedName("EnableDebug")
		private boolean enableDebug;

		@SerializedName("VerifyIncoming")
		private boolean verifyIncoming;

		@SerializedName("VerifyOutgoing")
		private boolean verifyOutgoing;

		@SerializedName("CAFile")
		private String caFile;

		@SerializedName("CertFile")
		private String certFile;

		@SerializedName("KeyFile")
		private String keyFile;

		@SerializedName("UiDir")
		private String uiDir;

		@SerializedName("PidFile")
		private String pidFile;

		@SerializedName("EnableSyslog")
		private boolean enableSyslog;

		@SerializedName("RejoinAfterLeave")
		private boolean rejoinAfterLeave;

		public boolean isBootstrap() {
			return bootstrap;
		}

		public void setBootstrap(boolean bootstrap) {
			this.bootstrap = bootstrap;
		}


		public String getDataDir() {
			return dataDir;
		}

		public void setDataDir(String dataDir) {
			this.dataDir = dataDir;
		}

		public String getDnsRecursor() {
			return dnsRecursor;
		}

		public void setDnsRecursor(String dnsRecursor) {
			this.dnsRecursor = dnsRecursor;
		}

		public String getDnsDomain() {
			return dnsDomain;
		}

		public void setDnsDomain(String dnsDomain) {
			this.dnsDomain = dnsDomain;
		}

		public LogLevel getLogLevel() {
			return logLevel;
		}

		public void setLogLevel(LogLevel logLevel) {
			this.logLevel = logLevel;
		}

		public String getNodeId() {
			return nodeId;
		}

		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}

		public String[] getClientAddresses() {
			return clientAddresses;
		}

		public void setClientAddresses(String[] clientAddresses) {
			this.clientAddresses = clientAddresses;
		}

		public String getBindAddress() {
			return bindAddress;
		}

		public void setBindAddress(String bindAddress) {
			this.bindAddress = bindAddress;
		}

		public boolean isLeaveOnTerm() {
			return leaveOnTerm;
		}

		public void setLeaveOnTerm(boolean leaveOnTerm) {
			this.leaveOnTerm = leaveOnTerm;
		}

		public boolean isSkipLeaveOnInt() {
			return skipLeaveOnInt;
		}

		public void setSkipLeaveOnInt(boolean skipLeaveOnInt) {
			this.skipLeaveOnInt = skipLeaveOnInt;
		}

		public boolean isEnableDebug() {
			return enableDebug;
		}

		public void setEnableDebug(boolean enableDebug) {
			this.enableDebug = enableDebug;
		}

		public boolean isVerifyIncoming() {
			return verifyIncoming;
		}

		public void setVerifyIncoming(boolean verifyIncoming) {
			this.verifyIncoming = verifyIncoming;
		}

		public boolean isVerifyOutgoing() {
			return verifyOutgoing;
		}

		public void setVerifyOutgoing(boolean verifyOutgoing) {
			this.verifyOutgoing = verifyOutgoing;
		}

		public String getCaFile() {
			return caFile;
		}

		public void setCaFile(String caFile) {
			this.caFile = caFile;
		}

		public String getCertFile() {
			return certFile;
		}

		public void setCertFile(String certFile) {
			this.certFile = certFile;
		}

		public String getKeyFile() {
			return keyFile;
		}

		public void setKeyFile(String keyFile) {
			this.keyFile = keyFile;
		}

		public String getUiDir() {
			return uiDir;
		}

		public void setUiDir(String uiDir) {
			this.uiDir = uiDir;
		}

		public String getPidFile() {
			return pidFile;
		}

		public void setPidFile(String pidFile) {
			this.pidFile = pidFile;
		}

		public boolean isEnableSyslog() {
			return enableSyslog;
		}

		public void setEnableSyslog(boolean enableSyslog) {
			this.enableSyslog = enableSyslog;
		}

		public boolean isRejoinAfterLeave() {
			return rejoinAfterLeave;
		}

		public void setRejoinAfterLeave(boolean rejoinAfterLeave) {
			this.rejoinAfterLeave = rejoinAfterLeave;
		}


		@Override
		public String toString() {
			return "Config{" +
					"bootstrap=" + bootstrap +
					", dataDir='" + dataDir + '\'' +
					", dnsRecursor='" + dnsRecursor + '\'' +
					", dnsDomain='" + dnsDomain + '\'' +
					", logLevel='" + logLevel + '\'' +
					", nodeId='" + nodeId + '\'' +
					", clientAddresses='" + Arrays.toString(clientAddresses) + '\'' +
					", bindAddress='" + bindAddress + '\'' +
					", leaveOnTerm=" + leaveOnTerm +
					", skipLeaveOnInt=" + skipLeaveOnInt +
					", enableDebug=" + enableDebug +
					", verifyIncoming=" + verifyIncoming +
					", verifyOutgoing=" + verifyOutgoing +
					", caFile='" + caFile + '\'' +
					", certFile='" + certFile + '\'' +
					", keyFile='" + keyFile + '\'' +
					", uiDir='" + uiDir + '\'' +
					", pidFile='" + pidFile + '\'' +
					", enableSyslog=" + enableSyslog +
					", rejoinAfterLeave=" + rejoinAfterLeave +
					'}';
		}


	}

	@SerializedName("Config")
	private Config config;

	@SerializedName("DebugConfig")
	private DebugConfig debugConfig;

	@SerializedName("Member")
	private Member member;

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public DebugConfig getDebugConfig() {
		return debugConfig;
	}

	public void setDebugConfig(DebugConfig debugConfig) {
		this.debugConfig = debugConfig;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Self{" +
				"config=" + config +
				", member=" + member +
				'}';
	}
}
