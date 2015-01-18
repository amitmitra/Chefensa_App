package com.project.chefensa.model;

public class Order {

	private long id;
	private long mealId;
	private long dateTime;
	private int mealCount;
	private int totalPrice;
	private int status;
	
	public Order(long id, long mealId, long dateTime,
			int mealCount, int totalPrice, int status) {
		super();
		this.id = id;
		this.mealId = mealId;
		this.dateTime = dateTime;
		this.mealCount = mealCount;
		this.totalPrice = totalPrice;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMealId() {
		return mealId;
	}

	public void setMealId(long mealId) {
		this.mealId = mealId;
	}

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}

	public int getMealCount() {
		return mealCount;
	}

	public void setMealCount(int mealCount) {
		this.mealCount = mealCount;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
