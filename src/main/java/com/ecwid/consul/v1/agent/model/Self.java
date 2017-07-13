package com.ecwid.consul.v1.agent.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Self {

	public static enum LogLevel {
		TRACE,
		DEBUG,
		INFO,
		WARN,
		ERR
	}

	public static class Config {

		@SerializedName("Bootstrap")
		private boolean bootstrap;

		@SerializedName("Server")
		private boolean server;

		@SerializedName("Datacenter")
		private String datacenter;

		@SerializedName("DataDir")
		private String dataDir;

		@SerializedName("DNSRecursor")
		private String dnsRecursor;

		@SerializedName("Domain")
		private String domain;

		@SerializedName("LogLevel")
		private LogLevel logLevel;

		@SerializedName("NodeName")
		private String nodeName;

		@SerializedName("NodeID")
		private String nodeId;

		@SerializedName("ClientAddr")
		private String clientAddress;

		@SerializedName("BindAddr")
		private String bindAddress;

		@SerializedName("AdvertiseAddr")
		private String advertiseAddress;

		@SerializedName("Ports")
		private Map<String, Integer> ports;

		@SerializedName("LeaveOnTerm")
		private boolean leaveOnTerm;

		@SerializedName("SkipLeaveOnInt")
		private boolean skipLeaveOnInt;

		@SerializedName("StatsiteAddr")
		private String statsiteAddress;

		@SerializedName("Protocol")
		private int protocol;

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

		@SerializedName("StartJoin")
		private List<String> startJoin;

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

		public boolean isServer() {
			return server;
		}

		public void setServer(boolean server) {
			this.server = server;
		}

		public String getDatacenter() {
			return datacenter;
		}

		public void setDatacenter(String datacenter) {
			this.datacenter = datacenter;
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

		public String getDomain() {
			return domain;
		}

		public void setDomain(String domain) {
			this.domain = domain;
		}

		public LogLevel getLogLevel() {
			return logLevel;
		}

		public void setLogLevel(LogLevel logLevel) {
			this.logLevel = logLevel;
		}

		public String getNodeName() {
			return nodeName;
		}

		public void setNodeName(String nodeName) {
			this.nodeName = nodeName;
		}

		public String getNodeId() {
			return nodeId;
		}

		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}

		public String getClientAddress() {
			return clientAddress;
		}

		public void setClientAddress(String clientAddress) {
			this.clientAddress = clientAddress;
		}

		public String getBindAddress() {
			return bindAddress;
		}

		public void setBindAddress(String bindAddress) {
			this.bindAddress = bindAddress;
		}

		public String getAdvertiseAddress() {
			return advertiseAddress;
		}

		public void setAdvertiseAddress(String advertiseAddress) {
			this.advertiseAddress = advertiseAddress;
		}

		public Map<String, Integer> getPorts() {
			return ports;
		}

		public void setPorts(Map<String, Integer> ports) {
			this.ports = ports;
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

		public String getStatsiteAddress() {
			return statsiteAddress;
		}

		public void setStatsiteAddress(String statsiteAddress) {
			this.statsiteAddress = statsiteAddress;
		}

		public int getProtocol() {
			return protocol;
		}

		public void setProtocol(int protocol) {
			this.protocol = protocol;
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

		public List<String> getStartJoin() {
			return startJoin;
		}

		public void setStartJoin(List<String> startJoin) {
			this.startJoin = startJoin;
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
					", server=" + server +
					", datacenter='" + datacenter + '\'' +
					", dataDir='" + dataDir + '\'' +
					", dnsRecursor='" + dnsRecursor + '\'' +
					", domain='" + domain + '\'' +
					", logLevel='" + logLevel + '\'' +
					", nodeName='" + nodeName + '\'' +
					", nodeId='" + nodeId + '\'' +
					", clientAddress='" + clientAddress + '\'' +
					", bindAddress='" + bindAddress + '\'' +
					", advertiseAddress='" + advertiseAddress + '\'' +
					", ports=" + ports +
					", leaveOnTerm=" + leaveOnTerm +
					", skipLeaveOnInt=" + skipLeaveOnInt +
					", statsiteAddress='" + statsiteAddress + '\'' +
					", protocol=" + protocol +
					", enableDebug=" + enableDebug +
					", verifyIncoming=" + verifyIncoming +
					", verifyOutgoing=" + verifyOutgoing +
					", caFile='" + caFile + '\'' +
					", certFile='" + certFile + '\'' +
					", keyFile='" + keyFile + '\'' +
					", startJoin=" + startJoin +
					", uiDir='" + uiDir + '\'' +
					", pidFile='" + pidFile + '\'' +
					", enableSyslog=" + enableSyslog +
					", rejoinAfterLeave=" + rejoinAfterLeave +
					'}';
		}
	}

	@SerializedName("Config")
	private Config config;

	@SerializedName("Member")
	private Member member;

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
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
