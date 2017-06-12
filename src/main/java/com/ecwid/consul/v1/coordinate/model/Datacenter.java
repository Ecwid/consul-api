package com.ecwid.consul.v1.coordinate.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public class Datacenter {

	@SerializedName("Datacenter")
	private String datacenter;

	@SerializedName("AreaID")
	private String areaId;

	@SerializedName("Coordinates")
	private List<Node> coordinates;

	public String getDatacenter() {
		return datacenter;
	}

	public void setDatacenter(String datacenter) {
		this.datacenter = datacenter;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
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
				", areaId='" + areaId + '\'' +
				", coordinates=" + coordinates +
				'}';
	}
}
