package com.bean;

public class ScanRecord {
	private String  a_addr;
	private String i_addr;
	private String time;
	private String a_location;
	private float a_x;
	private float a_y;
	private float rssi;
	public String getTime() {
		return time;
	}
	public String getA_addr() {
		return a_addr;
	}
	public void setA_addr(String a_addr) {
		this.a_addr = a_addr;
	}
	public String getI_addr() {
		return i_addr;
	}
	public void setI_addr(String i_addr) {
		this.i_addr = i_addr;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getA_location() {
		return a_location;
	}
	public void setA_location(String a_location) {
		this.a_location = a_location;
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
	public float getRssi() {
		return rssi;
	}
	public void setRssi(float rssi) {
		this.rssi = rssi;
	}

}
