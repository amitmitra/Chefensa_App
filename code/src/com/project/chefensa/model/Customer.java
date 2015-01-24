package com.project.chefensa.model;

public class Customer {

	private long id;
	private String deviceId;
	private String customerName;
	private String primaryPhone;
	private String secondaryPhone;
	private String primaryEmail;
	private String secondaryEmail;
	private long totalHitsOnApp;
	private long noOfTImesOrdered;
	private long timesStayingMoreThan2Mins;
	private String gcmId;
	
	public Customer(){
		
	}
	
	public Customer(String deviceId){
		this.deviceId = deviceId;
	}
	
	public Customer(long id, String deviceId, String customerName,
			String primaryPhone, String secondaryPhone, String primaryEmail,
			String secondaryEmail, long totalHitsOnApp, long noOfTImesOrdered,
			long timesStayingMoreThan2Mins) {
		this.id = id;
		this.deviceId = deviceId;
		this.customerName = customerName;
		this.primaryPhone = primaryPhone;
		this.secondaryPhone = secondaryPhone;
		this.primaryEmail = primaryEmail;
		this.secondaryEmail = secondaryEmail;
		this.totalHitsOnApp = totalHitsOnApp;
		this.noOfTImesOrdered = noOfTImesOrdered;
		this.timesStayingMoreThan2Mins = timesStayingMoreThan2Mins;
	}

	public Customer(String deviceId, String customerName, String primaryPhone,
			String secondaryPhone, String primaryEmail, String secondaryEmail,
			long totalHitsOnApp, long noOfTImesOrdered,
			long timesStayingMoreThan2Mins) {
		this.deviceId = deviceId;
		this.customerName = customerName;
		this.primaryPhone = primaryPhone;
		this.secondaryPhone = secondaryPhone;
		this.primaryEmail = primaryEmail;
		this.secondaryEmail = secondaryEmail;
		this.totalHitsOnApp = totalHitsOnApp;
		this.noOfTImesOrdered = noOfTImesOrdered;
		this.timesStayingMoreThan2Mins = timesStayingMoreThan2Mins;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public long getTotalHitsOnApp() {
		return totalHitsOnApp;
	}

	public void setTotalHitsOnApp(long totalHitsOnApp) {
		this.totalHitsOnApp = totalHitsOnApp;
	}

	public long getNoOfTImesOrdered() {
		return noOfTImesOrdered;
	}

	public void setNoOfTImesOrdered(long noOfTImesOrdered) {
		this.noOfTImesOrdered = noOfTImesOrdered;
	}

	public long getTimesStayingMoreThan2Mins() {
		return timesStayingMoreThan2Mins;
	}

	public void setTimesStayingMoreThan2Mins(long timesStayingMoreThan2Mins) {
		this.timesStayingMoreThan2Mins = timesStayingMoreThan2Mins;
	}

	public String getGcmId() {
		return gcmId;
	}

	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}
}
