package com.bean;

import java.util.Map;

public class AnchorNode {
	private String anchor_name;
	private float anchor_x;
	private float anchor_y;
	private String anchor_mac;
	private String anchor_location;
	private Map<String,iBeacon> scanresult;
	
	public String getAnchor_name() {
		return anchor_name;
	}
	public void setAnchor_name(String anchor_name) {
		this.anchor_name = anchor_name;
	}
	public float getAnchor_x() {
		return anchor_x;
	}
	public void setAnchor_x(float anchor_x) {
		this.anchor_x = anchor_x;
	}
	public float getAnchor_y() {
		return anchor_y;
	}
	public void setAnchor_y(float anchor_y) {
		this.anchor_y = anchor_y;
	}
	public String getAnchor_mac() {
		return anchor_mac;
	}
	public void setAnchor_mac(String anchor_mac) {
		this.anchor_mac = anchor_mac;
	}
	public Map<String, iBeacon> getScanresult() {
		return scanresult;
	}
	public void setScanresult(Map<String, iBeacon> scanresult) {
		this.scanresult = scanresult;
	}
	public String getAnchor_location() {
		return anchor_location;
	}
	public void setAnchor_location(String anchor_location) {
		this.anchor_location = anchor_location;
	}
	

}
