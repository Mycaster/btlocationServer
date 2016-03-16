package com.bean;

public class ScanRecord {
	private String  anchornode_mac;
	private String ibeacon_mac;
	private String time;
	private String location;
	private float anchornode_x;
	private float anchornode_y;
	private float rssi;
	
	public float getRssi() {
		return rssi;
	}
	public void setRssi(float rssi) {
		this.rssi = rssi;
	}
	public String getAnchornode_mac() {
		return anchornode_mac;
	}
	public void setAnchornode_mac(String anchornode_mac) {
		this.anchornode_mac = anchornode_mac;
	}
	public String getIbeacon_mac() {
		return ibeacon_mac;
	}
	public void setIbeacon_mac(String ibeacon_mac) {
		this.ibeacon_mac = ibeacon_mac;
	}
	public float getAnchornode_x() {
		return anchornode_x;
	}
	public void setAnchornode_x(float anchornode_x) {
		this.anchornode_x = anchornode_x;
	}
	public float getAnchornode_y() {
		return anchornode_y;
	}
	public void setAnchornode_y(float anchornode_y) {
		this.anchornode_y = anchornode_y;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
