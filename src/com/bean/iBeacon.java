package com.bean;
public class iBeacon {
	private String name;
	private String time;
	private String address;
	private int RSSI;
	private String iLocation;
//	private String uuid;
//	private int major;
//	private int minor;
//	private int txPower;
//	private double distance;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
//	
//	public double getDistance() {
//		return distance;
//	}
//	public void setDistance(double distance) {
//		this.distance = distance;
//	}
//	public int getTxPower() {
//		return txPower;
//	}
//	public void setTxPower(int txPower) {
//		this.txPower = txPower;
//	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	public String getUuid() {
//		return uuid;
//	}
//	public void setUuid(String uuid) {
//		this.uuid = uuid;
//	}
//	public int getMajor() {
//		return major;
//	}
//	public void setMajor(int major) {
//		this.major = major;
//	}
//	public int getMinor() {
//		return minor;
//	}
//	public void setMinor(int minor) {
//		this.minor = minor;
//	}
	public int getRSSI() {
		return RSSI;
	}
	public void setRSSI(int rSSI) {
		RSSI = rSSI;
	}
	public String getiLocation() {
		return iLocation;
	}
	public void setiLocation(String iLocation) {
		this.iLocation = iLocation;
	}

}
