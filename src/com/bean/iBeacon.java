package com.bean;
public class iBeacon {
	private String name;
	private String address;  // 设备地址（Mac）  
	private String uuid;     // Proximity UUID  
	private int major;       // Major  
	private int minor;       // Minor  
	private int rssi;        // 场强 
	private int tx_power;
	private String location;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setIlocation(String ilocation) {
		this.location = ilocation;
	}
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	public int getTx_power() {
		return tx_power;
	}
	public void setTx_power(int tx_power) {
		this.tx_power = tx_power;
	}

}
