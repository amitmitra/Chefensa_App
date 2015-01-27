package com.project.chefensa.model;

public class Address {
	
	private long id;
	private String addressText;

	public Address(){
	}
	
	public Address(long id, String addressText) {
		this.id = id;
        this.addressText=addressText;

	}
	
	public Address(String addressText) {
        this.addressText=addressText;
    }
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddressText() {
		return addressText;
	}
	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}
}
