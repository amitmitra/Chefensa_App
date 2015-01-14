package com.project.chefensa.model;

public class Meal {

	private String name;
	private String price;
	private int mealImageResource;
	private String description;
	private String mealType;
	private int mealCategory;
	private int spicyness;
	private String chefName;
	private int chefImageResource;
	private int canServePeople;
	private int quantityAvailable;
	
	public Meal(String name, String price, int mealImageResource,
			String description, String mealType, int mealCategory,
			int spicyness, String chefName, int chefImageResource,
			int canServePeople, int quantityAvailable) {
		super();
		this.name = name;
		this.price = price;
		this.mealImageResource = mealImageResource;
		this.description = description;
		this.mealType = mealType;
		this.mealCategory = mealCategory;
		this.spicyness = spicyness;
		this.chefName = chefName;
		this.chefImageResource = chefImageResource;
		this.canServePeople = canServePeople;
		this.quantityAvailable = quantityAvailable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getMealImageResource() {
		return mealImageResource;
	}

	public void setMealImageResource(int mealImageResource) {
		this.mealImageResource = mealImageResource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public int getMealCategory() {
		return mealCategory;
	}

	public void setMealCategory(int mealCategory) {
		this.mealCategory = mealCategory;
	}

	public int getSpicyness() {
		return spicyness;
	}

	public void setSpicyness(int spicyness) {
		this.spicyness = spicyness;
	}

	public String getChefName() {
		return chefName;
	}

	public void setChefName(String chefName) {
		this.chefName = chefName;
	}

	public int getChefImageResource() {
		return chefImageResource;
	}

	public void setChefImageResource(int chefImageResource) {
		this.chefImageResource = chefImageResource;
	}

	public int getCanServePeople() {
		return canServePeople;
	}

	public void setCanServePeople(int canServePeople) {
		this.canServePeople = canServePeople;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
}
