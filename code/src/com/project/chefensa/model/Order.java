package com.project.chefensa.model;

public class Order {

	private long id;
    private String deviceId;
	private String mealId;
	private long dateTime;
	private String mealCount;
	private int totalPrice;
	private int status;
    private String address;
    private String customerName;
    private String phoneNumber;
    private String customerEmail;
    private int orderPaymentType; //0 for PayNow, 1 for COD


    public Order(){

    }

    public Order(long id, String mealId, long dateTime,
                 String mealCount, int totalPrice, int status,String address) {
        super();
        this.id = id;
        this.mealId = mealId;
        this.dateTime = dateTime;
        this.mealCount = mealCount;
        this.totalPrice = totalPrice;
        this.status = status;
        this.address=address;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getOrderType() {
        return orderPaymentType;
    }

    public void setOrderType(int orderType) {
        this.orderPaymentType = orderType;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMealId() {
		return mealId;
	}

	public void setMealId(String mealId) {
		this.mealId = mealId;
	}

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}

	public String getMealCount() {
		return mealCount;
	}

	public void setMealCount(String mealCount) {
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
