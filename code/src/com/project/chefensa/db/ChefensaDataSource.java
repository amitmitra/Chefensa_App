package com.project.chefensa.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.project.chefensa.model.Address;
import com.project.chefensa.model.Customer;
import com.project.chefensa.model.Meal;
import com.project.chefensa.model.Order;
import com.project.chefensa.util.ConstantUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ChefensaDataSource {

	private static SQLiteDatabase database;
	private static DatabaseHandler dbHandler;
    private  static ChefensaDataSource mInstance;
    
    public static ChefensaDataSource source;
	
	private ChefensaDataSource(Context mContext) {
		dbHandler = new DatabaseHandler(mContext);
	}
    public static synchronized ChefensaDataSource getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ChefensaDataSource(context);
        }
        return mInstance;
    }
	
	private void open() throws SQLException{
		database = dbHandler.getWritableDatabase();
	}
	
	private void close(){
		//dbHandler.close();
	}
	
	public void addCustomer(Customer customer){
        open();
		database.beginTransaction();
		try{
			ContentValues values = new ContentValues();
			values.put(DatabaseHandler.COLUMN_CUSTOMERID, customer.getId());
			values.put(DatabaseHandler.COLUMN_CUSTOMERNAME, customer.getCustomerName());
			values.put(DatabaseHandler.COLUMN_PRIMARYNUMBER, customer.getPrimaryPhone());
			values.put(DatabaseHandler.COLUMN_PRIMARYEMAIL, customer.getPrimaryEmail());
			database.insert(DatabaseHandler.TABLE_CUSTOMER, null, values);
			database.setTransactionSuccessful();
		} finally{
			database.endTransaction();
            close();
		}
	}
	
	public Customer getCustomer(){
        open();
		Cursor cursor = database.query(DatabaseHandler.TABLE_CUSTOMER, null, null, null, null, null, null, null);
		Customer customer = null;
		if(cursor != null && cursor.getCount() > 0){
			cursor.moveToFirst();
			customer = new Customer();
			customer.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_CUSTOMERID)));
			customer.setCustomerName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_CUSTOMERNAME)));
			customer.setPrimaryPhone(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_PRIMARYNUMBER)));
			customer.setPrimaryEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_PRIMARYEMAIL)));
		}
        close();
		return customer;
	}
	
	public void deleteCustomer(){
        open();
		database.beginTransaction();
		try{
			database.delete(DatabaseHandler.TABLE_CUSTOMER, null, null);
			database.setTransactionSuccessful();
		}finally{
			database.endTransaction();
            close();
		}
	}
	public void clearMealTable(){
        open();
        try {
            database.delete(DatabaseHandler.TABLE_MEAL, null, null);
        }catch (SQLException e){

        }

    }
	public List<Address> getAllAddress(){
        open();
		List<Address> addresses = new ArrayList<Address>();
		Cursor cursor = database.query(DatabaseHandler.TABLE_ADDRESS, null, null, null, null, null, null);
		if(cursor != null && cursor.getCount() > 0){
			cursor.moveToFirst();
			do{
				Address address = new Address();
				address.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_ADDRESSID)));
				address.setAddressText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_ADDRESSTEXT)));
				addresses.add(address);
			} while(cursor.moveToNext());
		}
        close();
		return addresses;
	}
	
	public void addAddress(Address address) {
        open();
		database.beginTransaction();
		try {
			ContentValues values = new ContentValues();
			values.put(DatabaseHandler.COLUMN_ADDRESSID, address.getId());
			values.put(DatabaseHandler.COLUMN_ADDRESSTEXT, address.getAddressText());
			database.insertOrThrow(DatabaseHandler.TABLE_ADDRESS, null, values);
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
            close();
		}
	}
	
	public void removeAddress(long addressId) {
        open();
		database.beginTransaction();
		try {
			database.delete(DatabaseHandler.TABLE_ADDRESS,
					DatabaseHandler.COLUMN_ADDRESSID + " = ?",
					new String[] { String.valueOf(addressId) });
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
            close();
		}
	}
	
	public void addMeals(List<Meal> meals) {
        open();
		database.beginTransaction();
		try {
			for (Iterator<Meal> iterator = meals.iterator(); iterator.hasNext();) {
				Meal meal = iterator.next();
				ContentValues values = new ContentValues();
				values.put(DatabaseHandler.COLUMN_MEALID, meal.getMealId());
				values.put(DatabaseHandler.COLUMN_NAME, meal.getMealName());
				values.put(DatabaseHandler.COLUMN_MEALCONTENT, meal.getMealContent());
				values.put(DatabaseHandler.COLUMN_DESCRIPTION, meal.getMealDescription());
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
				values.put(DatabaseHandler.COLUMN_PRICE, meal.getMealPrice());
				values.put(DatabaseHandler.COLUMN_MEALQUANTITY, meal.getMealQuantity());
				values.put(DatabaseHandler.COLUMN_AVAILABILITY, meal.getAvailability());
				values.put(DatabaseHandler.COLUMN_MEALRATING, meal.getRating());
				values.put(DatabaseHandler.COLUMN_MEALCOUNT, meal.getMealCount());
				database.insertOrThrow(DatabaseHandler.TABLE_MEAL, null, values);
			}
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
            close();
		}
	}
	
	public List<Meal> getMeals(){
		List<Meal> allMealsList = new ArrayList<Meal>();
        open();
		Cursor cursor = database.query(DatabaseHandler.TABLE_MEAL, null, null, null, null, null, null);
		if(cursor != null && cursor.getCount() > 0){
			cursor.moveToFirst();
			do{
				Meal meal = new Meal();
				meal.setMealId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALID)));
				meal.setMealName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_NAME)));
				meal.setMealContent(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALCONTENT)));
				meal.setMealDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_DESCRIPTION)));
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
				meal.setMealPrice(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_PRICE)));
				meal.setMealQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALQUANTITY)));
				meal.setAvailability(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_AVAILABILITY)));
				meal.setRating(cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALRATING)));
                meal.setMealCount(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALCOUNT)));
				allMealsList.add(meal);
			} while(cursor.moveToNext());
		}
		close();
		return allMealsList;
	}
	
	public List<Meal> getMeals(int mealTime){
		List<Meal> mealsList = new ArrayList<Meal>();
        open();
		Cursor cursor = database.query(DatabaseHandler.TABLE_MEAL, null,
				DatabaseHandler.COLUMN_MEALTIME + " = ?",
				new String[] { String.valueOf(mealTime) }, null, null, null);
		if(cursor != null && cursor.getCount() > 0){
			cursor.moveToFirst();
			do{
				Meal meal = new Meal();
				meal.setMealId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALID)));
				meal.setMealName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_NAME)));
				meal.setMealContent(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALCONTENT)));
				meal.setMealDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_DESCRIPTION)));
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
				meal.setMealPrice(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_PRICE)));
				meal.setMealQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALQUANTITY)));
				meal.setAvailability(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_AVAILABILITY)));
				meal.setRating(cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALRATING)));
                meal.setMealCount(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALCOUNT)));
				mealsList.add(meal);
			} while(cursor.moveToNext());
		}
		close();
		return mealsList;
	}

    public int updateMealCount(Map<Long,Integer> idCountMap) {
        open();
        //database.beginTransaction();
        int result = 0;
        try {
            Iterator it = idCountMap.entrySet().iterator();
            ContentValues values = new ContentValues();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry)it.next();
                values.put(DatabaseHandler.COLUMN_AVAILABILITY,(Integer)pairs.getValue());
                it.remove(); // avoids a ConcurrentModificationException
                result = database.update(
                        DatabaseHandler.TABLE_MEAL,
                        values,
                        DatabaseHandler.COLUMN_MEALID + " = ?",
                        new String[] { String.valueOf((Long)pairs.getKey())
                        });
                values.remove(DatabaseHandler.COLUMN_AVAILABILITY);
            }

            //database.setTransactionSuccessful();
        } finally {
            //database.endTransaction();
            close();
        }
        return result;
    }
	public int addToCart(long mealId, int count) {
        open();
		//database.beginTransaction();
		int result = 0;
		try {
			ContentValues values = new ContentValues();
            int cartCount = getInCartCount(mealId);
			values.put(DatabaseHandler.COLUMN_MEALCOUNT, cartCount+1);
			result = database.update(
					DatabaseHandler.TABLE_MEAL,
					values,
					DatabaseHandler.COLUMN_MEALID + " = ?"
							, //todo right logic
					new String[] { String.valueOf(mealId)
							 });
			//database.setTransactionSuccessful();
		} finally {
			//database.endTransaction();
            close();
		}
		return result;
	}

    public int decreaseCartCount(long mealId) {
        open();
        database.beginTransaction();
        int result = 0;
        try {
            ContentValues values = new ContentValues();
            int cartCount = getInCartCount(mealId);
            if(cartCount>0) {
                values.put(DatabaseHandler.COLUMN_MEALCOUNT, cartCount - 1);
                result = database.update(
                        DatabaseHandler.TABLE_MEAL,
                        values,
                        DatabaseHandler.COLUMN_MEALID + " = ?"
                        , //todo right logic
                        new String[]{String.valueOf(mealId)
                        });
                database.setTransactionSuccessful();
            }
            else{
                return 0;
            }
        } finally {
            database.endTransaction();
            close();
        }
        return result;
    }

    public int getInCartCount(long mealId){
        int count =0;
        List<Meal> mealsList = new ArrayList<Meal>();
        open();
        Cursor cursor = database.query(DatabaseHandler.TABLE_MEAL, null,
                DatabaseHandler.COLUMN_MEALID+ " = ?",
                new String[] { String.valueOf(mealId) }, null, null, null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            count =cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_MEALCOUNT));
        }
        close();
        return count;
    }
    public int getAvailableCount(long mealId){
        int count =0;
        List<Meal> mealsList = new ArrayList<Meal>();
        open();
        Cursor cursor = database.query(DatabaseHandler.TABLE_MEAL, null,
                DatabaseHandler.COLUMN_MEALID+ " = ?",
                new String[] { String.valueOf(mealId) }, null, null, null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            count =cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHandler.COLUMN_AVAILABILITY));
        }
        close();
        return count;
    }

	public int removeFromCart(long mealId) {
        open();
		database.beginTransaction();
		int result = 0;
		try {
			ContentValues values = new ContentValues();
			/*values.put(DatabaseHandler.COLUMN_ISMEALINCART,
					ConstantUtil.MEAL_NOT_IN_CART);*/
			values.put(DatabaseHandler.COLUMN_MEALCOUNT, 0);
			/*result = database.update(DatabaseHandler.TABLE_MEAL, values,
					DatabaseHandler.COLUMN_MEALID + " = ? and " + DatabaseHandler.COLUMN_ISMEALINCART + " = ?",
					new String[] { String.valueOf(mealId), String.valueOf(ConstantUtil.MEAL_IN_CART) });*/
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
            close();
		}
		return result;
	}

    public int enterOrderDetails(Order order){
        int result=0;
        return result;
    }

}
