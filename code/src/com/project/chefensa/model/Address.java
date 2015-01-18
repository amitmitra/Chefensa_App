package com.project.chefensa.model;

public class Address {
	
	private long id;
	private String country;
	private String state;
	private String city;
	private String locality;
	private int pin;
	private String initialAddress;
	private String landmark;
	private String coordinates;
	private int priority;
	
	public Address(){
		
	}
	
	public Address(long id, String country, String state, String city,
			String locality, int pin, String initialAddress,
			String landmark, String coordinates, int priority) {
		this.id = id;
		this.country = country;
		this.state = state;
		this.city = city;
		this.locality = locality;
		this.pin = pin;
		this.initialAddress = initialAddress;
		this.landmark = landmark;
		this.coordinates = coordinates;
		this.priority = priority;
	}
	
	public Address(String country, String state, String city,
			String locality, int pin, String initialAddress,
			String landmark, String coordinates, int priority) {
		this.country = country;
		this.state = state;
		this.city = city;
		this.locality = locality;
		this.pin = pin;
		this.initialAddress = initialAddress;
		this.landmark = landmark;
		this.coordinates = coordinates;
		this.priority = priority;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getInitialAddress() {
		return initialAddress;
	}
	public void setInitialAddress(String initialAddress) {
		this.initialAddress = initialAddress;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
