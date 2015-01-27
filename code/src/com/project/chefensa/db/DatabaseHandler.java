package com.project.chefensa.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	 
    private static final String DATABASE_NAME = "chefensaDb";
    
    public static final String TABLE_MEAL = "meal";
    public static final String COLUMN_MEALID = "mealId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MEALCONTENT = "mealContent";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_MEALTYPE = "mealType";
    public static final String COLUMN_MEALCATEGORY = "mealCategory";
    public static final String COLUMN_MEALNOTE = "mealNote";
    public static final String COLUMN_MEALNUTRIENTS = "mealNutrients";
    public static final String COLUMN_MEALTIME = "mealTime";
    public static final String COLUMN_MEALIMAGEURL = "mealImageUrl";
    public static final String COLUMN_CHEFNAME = "chefName";
    public static final String COLUMN_CHEFIMAGEURL = "chefImageUrl";
    public static final String COLUMN_CHEFID = "chefId";
    public static final String COLUMN_SPICYNESS = "spicyness";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_MEALQUANTITY = "mealQuantity";
    public static final String COLUMN_AVAILABILITY = "quantityAvailable";
    public static final String COLUMN_MEALRATING = "mealRating";
    public static final String COLUMN_ISMEALINCART = "isMealInCart";
    public static final String COLUMN_MEALCOUNT = "mealCount";
    
    public static final String TABLE_ADDRESS = "address";
    public static final String COLUMN_ADDRESSID = "id";
    public static final String COLUMN_ADDRESSTEXT = "addressText";
    
    public static final String TABLE_CUSTOMER = "customer";
    public static final String COLUMN_CUSTOMERID = "id";
    public static final String COLUMN_CUSTOMERNAME = "customerName";
    public static final String COLUMN_PRIMARYNUMBER = "primaryNumber";
    public static final String COLUMN_PRIMARYEMAIL = "primaryEmail";

    public static final String TABLE_ORDER = "ordertable";
    public static final String COLUMN_ORDERID = "id";
    public static final String COLUMN_ORDERMEALID = "mealId";
    public static final String COLUMN_ORDERADDRESS = "address";
    public static final String COLUMN_ORDERDATETIME= "dateTime";
    public static final String COLUMN_ORDERMEALCOUNT = "mealCount";
    public static final String COLUMN_TOTALPRICE = "totalPrice";
    public static final String COLUMN_PAYMENTTYPE = "paymentType";
    public static final String COLUMN_STATUS = "status";

	private static final String CREATE_TABLE_MEAL = "CREATE TABLE " + TABLE_MEAL 
			+ "(" + COLUMN_MEALID + " INTEGER, " + COLUMN_NAME
			+ " TEXT, " + COLUMN_MEALCONTENT + " TEXT, " + COLUMN_DESCRIPTION
			+ " TEXT, " + COLUMN_MEALTYPE + " TEXT, " + COLUMN_MEALCATEGORY
			+ " INTEGER, " + COLUMN_MEALNOTE + " TEXT, " + COLUMN_MEALNUTRIENTS + " TEXT, " + COLUMN_MEALTIME 
			+ " INTEGER, " + COLUMN_MEALIMAGEURL + " TEXT, " + COLUMN_CHEFNAME
			+ " TEXT, " + COLUMN_CHEFIMAGEURL + " TEXT, " + COLUMN_CHEFID
			+ " INTEGER, " + COLUMN_SPICYNESS + " INTEGER, " + COLUMN_PRICE
			+ " INTEGER, " + COLUMN_MEALQUANTITY + " INTEGER, " + COLUMN_AVAILABILITY
			+ " INTEGER, " + COLUMN_MEALRATING + " REAL, " + COLUMN_MEALCOUNT + " INTEGER)";
	
	private static final String CREATE_TABLE_ADDRESS = "CREATE TABLE " + TABLE_ADDRESS
			+ "(" + COLUMN_ADDRESSID + " INTEGER PRIMARY KEY, " + COLUMN_ADDRESSTEXT
			+ " TEXT)";
	
	private static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE " + TABLE_CUSTOMER
			+ "(" + COLUMN_CUSTOMERID + " INTEGER PRIMARY KEY, " + COLUMN_CUSTOMERNAME
			+ " TEXT, " + COLUMN_PRIMARYNUMBER + " TEXT, " + COLUMN_PRIMARYEMAIL + " TEXT)";
	
	private static final String CREATE_TABLE_ORDER = "CREATE TABLE " + TABLE_ORDER
			+ "(" + COLUMN_ORDERID + " INTEGER, " + COLUMN_ORDERMEALID
			+ " TEXT, " + COLUMN_ORDERADDRESS + " TEXT, " + COLUMN_ORDERDATETIME
			+ " TIMESTAMP, " + COLUMN_ORDERMEALCOUNT + " TEXT, " + COLUMN_TOTALPRICE
			+ " INTEGER, "+ COLUMN_PAYMENTTYPE
			+ " TEXT, " + COLUMN_STATUS + " INTEGER)";
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_MEAL);
		db.execSQL(CREATE_TABLE_ADDRESS);
		db.execSQL(CREATE_TABLE_CUSTOMER);
		db.execSQL(CREATE_TABLE_ORDER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAL);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
		onCreate(db);
	}

}
