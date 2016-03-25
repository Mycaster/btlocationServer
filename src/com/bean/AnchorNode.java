package com.bean;

import java.util.Map;

public class AnchorNode {
	private String a_name;
	private float a_x;
	private float a_y;
	private String a_address;
	private String a_location;
	private Map<String,iBeacon> scanresult;
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	public float getA_x() {
		return a_x;
	}
	public void setA_x(float a_x) {
		this.a_x = a_x;
	}
	public float getA_y() {
		return a_y;
	}
	public void setA_y(float a_y) {
		this.a_y = a_y;
	}
	public String getA_address() {
		return a_address;
	}
	public void setA_address(String a_address) {
		this.a_address = a_address;
	}
	public String getA_location() {
		return a_location;
	}
	public void setA_location(String a_location) {
		this.a_location = a_location;
	}
	public Map<String, iBeacon> getScanresult() {
		return scanresult;
	}
	public void setScanresult(Map<String, iBeacon> scanresult) {
		this.scanresult = scanresult;
	}
	
}
