package com.ecwid.consul.v1.coordinate.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Node {

	@SerializedName("Node")
	private String node;

	@SerializedName("Coord")
	private Coord coord;

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	@Override
	public String toString() {
		return "Node{" +
				"node='" + node + '\'' +
				", coord=" + coord +
				'}';
	}
}
