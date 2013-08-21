package com.parousia.eatouts.data;

public class BaseRequest {

	long currentLat;
	long currentLong;
	String currentAddress;
	public BaseRequest(long currentLat, long currentLong, String currentAddress) {
		super();
		this.currentLat = currentLat;
		this.currentLong = currentLong;
		this.currentAddress = currentAddress;
	}
	public long getCurrentLat() {
		return currentLat;
	}
	public void setCurrentLat(long currentLat) {
		this.currentLat = currentLat;
	}
	public long getCurrentLong() {
		return currentLong;
	}
	public void setCurrentLong(long currentLong) {
		this.currentLong = currentLong;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	
	
}
