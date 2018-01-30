package com.eccl.common.util.iputil;

public class ClientVO {
	//客户端IP
	private String ip;
	//客户端IP归属地
	private String ipAddress;
	//客户端Mac地址
	private String mac;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
}
