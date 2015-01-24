package com.project.chefensa.model;


public class Chef {
	
	private long id;
	private String name;
	private int gender;
	private String phoneNumber;
	private String email;
	private long addressId;
	private String imageUrl;
	private String description;
	private String languages;
	private int maritalStatus;
	private String ethnicity;
	private int chefCategory;
	private int mealCategory;
	private String workingDays;
	private int workingTime;
	private String mealTypes;
	private String speciality;
	private String rating;
	private int capacity;
	private int experience;
	private long createTime;
	
	public Chef(){
		
	}
	
	public Chef(long id, String name, int gender, String phoneNumber,
			String email, long addressId, String imageUrl,
			String description, String languages, int maritalStatus,
			String ethnicity, int chefCategory, int mealCategory,
			String workingDays, int workingTime, String mealTypes,
			String speciality, String rating, int capacity,
			int experience, long createTime) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.addressId = addressId;
		this.imageUrl = imageUrl;
		this.description = description;
		this.languages = languages;
		this.maritalStatus = maritalStatus;
		this.ethnicity = ethnicity;
		this.chefCategory = chefCategory;
		this.mealCategory = mealCategory;
		this.workingDays = workingDays;
		this.workingTime = workingTime;
		this.mealTypes = mealTypes;
		this.speciality = speciality;
		this.rating = rating;
		this.capacity = capacity;
		this.experience = experience;
		this.createTime = createTime;
	}
	
	public Chef(String name, int gender, String phoneNumber,
			String email, long addressId, String imageUrl,
			String description, String languages, int maritalStatus,
			String ethnicity, int chefCategory, int mealCategory,
			String workingDays, int workingTime, String mealTypes,
			String speciality, int capacity) {
		super();
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.addressId = addressId;
		this.imageUrl = imageUrl;
		this.description = description;
		this.languages = languages;
		this.maritalStatus = maritalStatus;
		this.ethnicity = ethnicity;
		this.chefCategory = chefCategory;
		this.mealCategory = mealCategory;
		this.workingDays = workingDays;
		this.workingTime = workingTime;
		this.mealTypes = mealTypes;
		this.speciality = speciality;
		this.capacity = capacity;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public int getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(int maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public int getChefCategory() {
		return chefCategory;
	}
	public void setChefCategory(int chefCategory) {
		this.chefCategory = chefCategory;
	}
	public int getMealCategory() {
		return mealCategory;
	}
	public void setMealCategory(int mealCategory) {
		this.mealCategory = mealCategory;
	}
	public String getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}
	public int getWorkingTime() {
		return workingTime;
	}
	public void setWorkingTime(int workingTime) {
		this.workingTime = workingTime;
	}
	public String getMealTypes() {
		return mealTypes;
	}
	public void setMealTypes(String mealTypes) {
		this.mealTypes = mealTypes;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
}
