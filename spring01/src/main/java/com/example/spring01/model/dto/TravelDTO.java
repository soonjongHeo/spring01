package com.example.spring01.model.dto;

import java.util.Date;

public class TravelDTO {
	private String travelId;
	private String travelName;
	private String travelDate;
	private String travelAddress;
	private String travelLatitude;
	private String travelLongitude;
	private Date regDt;
	private Date updDt;
	   
	public String getTravelId() {
		return travelId;
	}
	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}
	public String getTravelName() {
		return travelName;
	}
	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}
	public String getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
	public String getTravelAddress() {
		return travelAddress;
	}
	public void setTravelAddress(String travelAddress) {
		this.travelAddress = travelAddress;
	}
	public String getTravelLatitude() {
		return travelLatitude;
	}
	public void setTravelLatitude(String travelLatitude) {
		this.travelLatitude = travelLatitude;
	}
	public String getTravelLongitude() {
		return travelLongitude;
	}
	public void setTravelLongitude(String travelLongitude) {
		this.travelLongitude = travelLongitude;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	public TravelDTO() {
	}
	
	@Override
	public String toString() {
		return "TravelDTO [travelId=" + travelId + ", travelName=" + travelName + ", travelDate=" + travelDate
				+ ", travelAddress=" + travelAddress + ", travelLatitude=" + travelLatitude + ", travelLongitude="
				+ travelLongitude + ", regDt=" + regDt + ", updDt=" + updDt + "]";
	}
	
	 
	
}
