package com.project.chefensa.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.project.chefensa.model.Address;
import com.project.chefensa.model.Customer;
import com.project.chefensa.model.Meal;
import com.project.chefensa.util.ConstantUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ChefensaDataSource {

	private SQLiteDatabase database;
	private DatabaseHandler dbHandler;
	
	public ChefensaDataSource(Context mContext) {
		dbHandler = new DatabaseHandler(mContext);
	}
	
	public void open() throws SQLException{
		database = dbHandler.getWritableDatabase();
	}
	
	public void close(){
		dbHandler.close();
	}
	
	public void addCustomer(Customer customer){
		database.beginTransaction();
		try{
			ContentValues values = new ContentValues();
			values.put(DatabaseHandler.COLUMN_CUSTOMERID, customer.getId());
			values.put(DatabaseHandler.COLUMN_CUSTOMERNAME, customer.getCustomerName());
			values.put(DatabaseHandler.COLUMN_PRIMARYNUMBER, customer.getPrimaryPhone());
			values.put(DatabaseHandler.COLUMN_SECONDARYNUMBER, customer.getSecondaryPhone());
			values.put(DatabaseHandler.COLUMN_PRIMARYEMAIL, customer.getPrimaryEmail());
			values.put(DatabaseHandler.COLUMN_SECONDARYEMAIL, customer.getSecondaryEmail());
			database.insert(DatabaseHandler.TABLE_CUSTOMER, null, values);
			database.setTransactionSuccessful();
		} finally{
			database.endTransaction();
		}
	}
	
	public Customer getCustomer(){
		Cursor cursor = database.query(DatabaseHandler.TABLE_CUSTOMER, null, null, null, null, null, null, null);
		Customer customer = null;
		if(cursor != null && cursor.getCount() > 0){
			cursor.moveToFirst();
			customer = new Customer();
			customer.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_CUSTOMERID)));
			customer.setCustomerName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_CUSTOMERNAME)));
			customer.setPrimaryPhone(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_PRIMARYNUMBER)));
			customer.setSecondaryPhone(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_SECONDARYNUMBER)));
			customer.setPrimaryEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_PRIMARYEMAIL)));
			customer.setSecondaryEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_SECONDARYEMAIL)));
		}
		return customer;
	}
	
	public void deleteCustomer(){
		database.beginTransaction();
		try{
			database.delete(DatabaseHandler.TABLE_CUSTOMER, null, null);
			database.setTransactionSuccessful();
		}finally{
			database.endTransaction();
		}
	}
	
	public List<Address> getAllAddress(){
		List<Address> addresses = new ArrayList<Address>();
		Cursor cursor = database.query(DatabaseHandler.TABLE_ADDRESS, null, null, null, null, null, null);
		if(cursor != null && cursor.getCount() > 0){
			cursor.moveToFirst();
			do{
				Address address = new Address();
				address.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_ADDRESSID)));
				address.setCountry(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_COUNTRY)));
				address.setState(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_STATE)));
				address.setCity(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_CITY)));
				address.setLocality(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_LOCALITY)));
				address.setPin(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_PIN)));
				address.setInitialAddress(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_INITIALADDRESS)));
				address.setLandmark(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_LANDMARK)));
				address.setCoordinates(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_COORDINATES)));
				address.setPriority(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_PRIORITY)));
				addresses.add(address);
			} while(cursor.moveToNext());
		}
		return addresses;
	}
	
	public void addAddress(Address address) {
		database.beginTransaction();
		try {
			ContentValues values = new ContentValues();
			values.put(DatabaseHandler.COLUMN_ADDRESSID, address.getId());
			values.put(DatabaseHandler.COLUMN_COUNTRY, address.getCountry());
			values.put(DatabaseHandler.COLUMN_STATE, address.getState());
			values.put(DatabaseHandler.COLUMN_CITY, address.getCity());
			values.put(DatabaseHandler.COLUMN_LOCALITY, address.getLocality());
			values.put(DatabaseHandler.COLUMN_PIN, address.getPin());
			values.put(DatabaseHandler.COLUMN_INITIALADDRESS, address.getInitialAddress());
			values.put(DatabaseHandler.COLUMN_LANDMARK, address.getLandmark());
			database.insertOrThrow(DatabaseHandler.TABLE_ADDRESS, null, values);
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
	}
	
	public void removeAddress(long addressId) {
		database.beginTransaction();
		try {
			database.delete(DatabaseHandler.TABLE_ADDRESS,
					DatabaseHandler.COLUMN_ADDRESSID + " = ?",
					new String[] { String.valueOf(addressId) });
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
	}
	
	public void addMeals(List<Meal> meals) {
		database.beginTransaction();
		try {
			for (Iterator<Meal> iterator = meals.iterator(); iterator.hasNext();) {
				Meal meal = iterator.next();
				ContentValues values = new ContentValues();
				values.put(DatabaseHandler.COLUMN_MEALID, meal.getMealId());
				values.put(DatabaseHandler.COLUMN_NAME, meal.getName());
				values.put(DatabaseHandler.COLUMN_MEALCONTENT, meal.getMealContent());
				values.put(DatabaseHandler.COLUMN_DESCRIPTION, meal.getDescription());
				values.put(DatabaseHandler.COLUMN_MEALTYPE, meal.getMealType());
				values.put(DatabaseHandler.COLUMN_MEALCATEGORY, meal.getMealCategory());
				values.put(DatabaseHandler.COLUMN_MEALNOTE, meal.getMealNote());
				values.put(DatabaseHandler.COLUMN_MEALNUTRIENTS, meal.getMealNutrients());
				values.put(DatabaseHandler.COLUMN_MEALTIME, meal.getMealTime());
				values.put(DatabaseHandler.COLUMN_MEALIMAGEURL, meal.getMealImageUrl());
				values.put(DatabaseHandler.COLUMN_CHEFNAME, meal.getChefName());
				values.put(DatabaseHandler.COLUMN_CHEFIMAGEURL, meal.getChefImageUrl());
				values.put(DatabaseHandler.COLUMN_CHEFID, meal.getChefId());
				values.put(DatabaseHandler.COLUMN_SPICYNESS, meal.getSpicyness());
				values.put(DatabaseHandler.COLUMN_PRICE, meal.getPrice());
				values.put(DatabaseHandler.COLUMN_MEALQUANTITY, meal.getMealQuantity());
				values.put(DatabaseHandler.COLUMN_QUANTITYAVAILABLE, meal.getQuantityAvailable());
				values.put(DatabaseHandler.COLUMN_MEALRATING, meal.getMealRating());
				values.put(DatabaseHandler.COLUMN_ISMEALINCART, meal.getIsMealInCart());
				database.insertOrThrow(DatabaseHandler.TABLE_MEAL, null, values);
			}
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
	}
	
	public List<Meal> getMeals(){
		List<Meal> allMealsList = new ArrayList<Meal>();
		Cursor cursor = database.query(DatabaseHandler.TABLE_MEAL, null, null, null, null, null, null);
		if(cursor != null && cursor.getCount() > 0){
			cursor.moveToFirst();
			do{
				Meal meal = new Meal();
				meal.setMealId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALID)));
				meal.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_NAME)));
				meal.setMealContent(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALCONTENT)));
				meal.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_DESCRIPTION)));
				meal.setMealType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALTYPE)));
				meal.setMealCategory(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALCATEGORY)));
				meal.setMealNote(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALNOTE)));
				meal.setMealNutrients(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALNUTRIENTS)));
				meal.setMealTime(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALTIME)));
				meal.setMealImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALIMAGEURL)));
				meal.setChefName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_CHEFNAME)));
				meal.setChefImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_CHEFIMAGEURL)));
				meal.setChefId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_CHEFID)));
				meal.setSpicyness(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_SPICYNESS)));
				meal.setPrice(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_PRICE)));
				meal.setMealQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALQUANTITY)));
				meal.setQuantityAvailable(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_QUANTITYAVAILABLE)));
				meal.setMealRating(cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALRATING)));
				allMealsList.add(meal);
			} while(cursor.moveToNext());
		}
		
		return allMealsList;
	}
	
	public List<Meal> getMeals(int mealTime){
		List<Meal> mealsList = new ArrayList<Meal>();
		Cursor cursor = database.query(DatabaseHandler.TABLE_MEAL, null,
				DatabaseHandler.COLUMN_MEALTIME + " = ?",
				new String[] { String.valueOf(mealTime) }, null, null, null);
		if(cursor != null && cursor.getCount() > 0){
			cursor.moveToFirst();
			do{
				Meal meal = new Meal();
				meal.setMealId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALID)));
				meal.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_NAME)));
				meal.setMealContent(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALCONTENT)));
				meal.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_DESCRIPTION)));
				meal.setMealType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALTYPE)));
				meal.setMealCategory(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALCATEGORY)));
				meal.setMealNote(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALNOTE)));
				meal.setMealNutrients(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALNUTRIENTS)));
				meal.setMealTime(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALTIME)));
				meal.setMealImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALIMAGEURL)));
				meal.setChefName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_CHEFNAME)));
				meal.setChefImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_CHEFIMAGEURL)));
				meal.setChefId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_CHEFID)));
				meal.setSpicyness(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_SPICYNESS)));
				meal.setPrice(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_PRICE)));
				meal.setMealQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALQUANTITY)));
				meal.setQuantityAvailable(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_QUANTITYAVAILABLE)));
				meal.setMealRating(cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALRATING)));
				mealsList.add(meal);
			} while(cursor.moveToNext());
		}
		
		return mealsList;
	}
	
	public int addToCart(long mealId, int count) {
		database.beginTransaction();
		int result = 0;
		try {
			ContentValues values = new ContentValues();
			values.put(DatabaseHandler.COLUMN_ISMEALINCART,
					ConstantUtil.MEAL_IN_CART);
			values.put(DatabaseHandler.COLUMN_MEALCOUNT, count);
			result = database.update(
					DatabaseHandler.TABLE_MEAL,
					values,
					DatabaseHandler.COLUMN_MEALID + " = ? and "
							+ DatabaseHandler.COLUMN_ISMEALINCART + " = ?",
					new String[] { String.valueOf(mealId),
							String.valueOf(ConstantUtil.MEAL_NOT_IN_CART) });
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
		return result;
	}
	
	public int removeFromCart(long mealId) {
		database.beginTransaction();
		int result = 0;
		try {
			ContentValues values = new ContentValues();
			values.put(DatabaseHandler.COLUMN_ISMEALINCART,
					ConstantUtil.MEAL_NOT_IN_CART);
			values.put(DatabaseHandler.COLUMN_MEALCOUNT, 0);
			result = database.update(DatabaseHandler.TABLE_MEAL, values,
					DatabaseHandler.COLUMN_MEALID + " = ? and " + DatabaseHandler.COLUMN_ISMEALINCART + " = ?",
					new String[] { String.valueOf(mealId), String.valueOf(ConstantUtil.MEAL_IN_CART) });
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
		return result;
	}

}
