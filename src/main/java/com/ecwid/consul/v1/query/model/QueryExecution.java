package com.ecwid.consul.v1.query.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QueryExecution {

	public static class DNS {
		@SerializedName("TTL")
		private String ttl;

		public String getTtl() { return ttl; }

		public void setTtl(String ttl) { this.ttl = ttl; }

		@Override
		public String toString() {
			return "DNS{" +
				"ttl=" + ttl +
				'}';
		}
	}

	@SerializedName("Service")
	private String service;

	@SerializedName("Nodes")
	private List<QueryNode> nodes;

	@SerializedName("DNS")
	private DNS dns;

	@SerializedName("Datacenter")
	private String datacenter;

	@SerializedName("Failovers")
	private Integer failovers;

	public String getService() { return service; }

	public void setService(String service) { this.service = service; }

	public List<QueryNode> getNodes() { return nodes; }

	public void setNodes(List<QueryNode> nodes) { this.nodes = nodes; }

	public String getDatacenter() { return datacenter; }

	public void setDatacenter(String datacenter) { this.datacenter = datacenter; }

	public Integer getFailovers() { return failovers; }

	public void setFailovers(Integer failovers) { this.failovers = failovers; }

	@Override
	public String toString() {
		return "CatalogNode{" +
			"service=" + service +
			", nodes=" + nodes +
			", dns=" + dns +
			", datacenter=" + datacenter +
			", failovers=" + failovers +
			'}';
	}
}
