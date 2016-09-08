package com.ecwid.consul.v1.coordinate.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Datacenter {

	@SerializedName("Datacenter")
	private String datacenter;

	@SerializedName("Coordinates")
	private List<Node> coordinates;

	public String getDatacenter() {
		return datacenter;
	}

	public void setDatacenter(String datacenter) {
		this.datacenter = datacenter;
	}

	public List<Node> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Node> coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "Datacenter{" +
				"datacenter='" + datacenter + '\'' +
				", coordinates=" + coordinates +
				'}';
	}
}
