package com.project.chefensa.model;

public class Customer {
	
	private long id;
	private String customerName;
	private String primaryNumber;
	private String secondaryNumber;
	private String primaryEmail;
	private String secondaryEmail;
	
	public Customer(){
		
	}
	
	public Customer(String customerName, String primaryNumber,
			String secondaryNumber, String primaryEmail, String secondaryEmail) {
		this.customerName = customerName;
		this.primaryNumber = primaryNumber;
		this.secondaryNumber = secondaryNumber;
		this.primaryEmail = primaryEmail;
		this.secondaryEmail = secondaryEmail;
	}

	public Customer(long id, String customerName, String primaryNumber,
			String secondaryNumber, String primaryEmail, String secondaryEmail) {
		this.id = id;
		this.customerName = customerName;
		this.primaryNumber = primaryNumber;
		this.secondaryNumber = secondaryNumber;
		this.primaryEmail = primaryEmail;
		this.secondaryEmail = secondaryEmail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPrimaryNumber() {
		return primaryNumber;
	}

	public void setPrimaryNumber(String primaryNumber) {
		this.primaryNumber = primaryNumber;
	}

	public String getSecondaryNumber() {
		return secondaryNumber;
	}

	public void setSecondaryNumber(String secondaryNumber) {
		this.secondaryNumber = secondaryNumber;
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
}
