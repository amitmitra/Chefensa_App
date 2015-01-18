package com.project.chefensa.model;

public class Meal {

	private long mealId;
	private String name;
	private String mealContent;
	private String description;
	private String mealType;
	private int mealCategory;
	private String mealNote;
	private String mealNutrients;
	private int mealTime;
	private String mealImageUrl;
	private String chefName;
	private String chefImageUrl;
	private long chefId;
	private int spicyness;
	private int price;
	private int mealQuantity;
	private int quantityAvailable;
	private double mealRating;
	private int isMealInCart;
	private int mealCount;
	
	public Meal() {
		
	}

	public Meal(String name, String mealContent, String description,
			String mealType, int mealCategory, String mealNote,
			String mealNutrients, int mealTime, String mealImageUrl, String chefName,
			String chefImageUrl, long chefId, int spicyness, int price,
			int mealQuantity, int quantityAvailable, double mealRating, int isMealInCart,
			int mealCount) {
		this.name = name;
		this.mealContent = mealContent;
		this.description = description;
		this.mealType = mealType;
		this.mealCategory = mealCategory;
		this.mealNote = mealNote;
		this.mealNutrients = mealNutrients;
		this.mealTime = mealTime;
		this.mealImageUrl = mealImageUrl;
		this.chefName = chefName;
		this.chefImageUrl = chefImageUrl;
		this.chefId = chefId;
		this.spicyness = spicyness;
		this.price = price;
		this.mealQuantity = mealQuantity;
		this.quantityAvailable = quantityAvailable;
		this.mealRating = mealRating;
		this.isMealInCart = isMealInCart;
		this.mealCount = mealCount;
	}
	
	public Meal(String name, String mealContent, String description,
			String mealType, int mealCategory, String mealNote,
			String mealNutrients, int mealTime, String mealImageUrl, String chefName,
			String chefImageUrl, long chefId, int spicyness, int price,
			int mealQuantity, int quantityAvailable, float mealRating) {
		this.name = name;
		this.mealContent = mealContent;
		this.description = description;
		this.mealType = mealType;
		this.mealCategory = mealCategory;
		this.mealNote = mealNote;
		this.mealNutrients = mealNutrients;
		this.mealTime = mealTime;
		this.mealImageUrl = mealImageUrl;
		this.chefName = chefName;
		this.chefImageUrl = chefImageUrl;
		this.chefId = chefId;
		this.spicyness = spicyness;
		this.price = price;
		this.mealQuantity = mealQuantity;
		this.quantityAvailable = quantityAvailable;
		this.mealRating = mealRating;
	}

	public Meal(long mealId, String name, String mealContent,
			String description, String mealType, int mealCategory,
			String mealNote, String mealNutrients, int mealTime, String mealImageUrl,
			String chefName, String chefImageUrl, long chefId, int spicyness,
			int price, int mealQuantity, int quantityAvailable,
			int isMealInCart, int mealCount) {
		this.mealId = mealId;
		this.name = name;
		this.mealContent = mealContent;
		this.description = description;
		this.mealType = mealType;
		this.mealCategory = mealCategory;
		this.mealNote = mealNote;
		this.mealNutrients = mealNutrients;
		this.mealTime = mealTime;
		this.mealImageUrl = mealImageUrl;
		this.chefName = chefName;
		this.chefImageUrl = chefImageUrl;
		this.chefId = chefId;
		this.spicyness = spicyness;
		this.price = price;
		this.mealQuantity = mealQuantity;
		this.quantityAvailable = quantityAvailable;
		this.isMealInCart = isMealInCart;
		this.mealCount = mealCount;
	}

	public long getMealId() {
		return mealId;
	}

	public void setMealId(long mealId) {
		this.mealId = mealId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMealContent() {
		return mealContent;
	}

	public void setMealContent(String mealContent) {
		this.mealContent = mealContent;
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

	public String getMealNote() {
		return mealNote;
	}

	public void setMealNote(String mealNote) {
		this.mealNote = mealNote;
	}

	public String getMealNutrients() {
		return mealNutrients;
	}

	public void setMealNutrients(String mealNutrients) {
		this.mealNutrients = mealNutrients;
	}

	public int getMealTime() {
		return mealTime;
	}

	public void setMealTime(int mealTime) {
		this.mealTime = mealTime;
	}

	public String getMealImageUrl() {
		return mealImageUrl;
	}

	public void setMealImageUrl(String mealImageUrl) {
		this.mealImageUrl = mealImageUrl;
	}

	public String getChefName() {
		return chefName;
	}

	public void setChefName(String chefName) {
		this.chefName = chefName;
	}

	public String getChefImageUrl() {
		return chefImageUrl;
	}

	public void setChefImageUrl(String chefImageUrl) {
		this.chefImageUrl = chefImageUrl;
	}

	public long getChefId() {
		return chefId;
	}

	public void setChefId(long chefId) {
		this.chefId = chefId;
	}

	public int getSpicyness() {
		return spicyness;
	}

	public void setSpicyness(int spicyness) {
		this.spicyness = spicyness;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMealQuantity() {
		return mealQuantity;
	}

	public void setMealQuantity(int mealQuantity) {
		this.mealQuantity = mealQuantity;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public int getIsMealInCart() {
		return isMealInCart;
	}

	public void setIsMealInCart(int isMealInCart) {
		this.isMealInCart = isMealInCart;
	}

	public double getMealRating() {
		return mealRating;
	}

	public void setMealRating(double mealRating) {
		this.mealRating = mealRating;
	}

	public int getMealCount() {
		return mealCount;
	}

	public void setMealCount(int mealCount) {
		this.mealCount = mealCount;
	}
}
