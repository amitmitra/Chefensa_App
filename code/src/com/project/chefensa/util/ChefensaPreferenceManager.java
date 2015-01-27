package com.project.chefensa.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by harshwardhan.c on 24/01/15.
 */
public class ChefensaPreferenceManager {
	private static ChefensaPreferenceManager instance = null;

	public static ChefensaPreferenceManager instance() {
		if (instance == null) {
			synchronized (ChefensaPreferenceManager.class) {
				if (instance == null)
					instance = new ChefensaPreferenceManager();
			}
		}
		return instance;
	}

	private ChefensaPreferenceManager() {
	}

	private SharedPreferences sharedPreferences;

	public void initialize(Context context) {
		this.sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
	}

	public static final String KEY_IS_CUSTOMER_REGISTERED = "is_customer_registered"; 
	public static final String KEY_CURRENTMEAL_DATE = "current_meal_date";
	public static final String GCM_REGISTRATION_ID = "gcm_registration_id";
	public static final String PROPERTY_APP_VERSION = "app_version";

	public void saveCurrentMealDate(long sn) {
		this.sharedPreferences.edit().putLong(KEY_CURRENTMEAL_DATE, sn)
				.commit();
	}

	public long getCurrentMealDate() {
		long time = this.sharedPreferences.getLong(KEY_CURRENTMEAL_DATE, 0);
		return time;
	}

	//GCM Registration Id
	public void saveGCMRegistrationId(String regId) {
		this.sharedPreferences.edit().putString(GCM_REGISTRATION_ID, regId).commit();
	}

	public String getGCMRegistrationId() {
		String regId = this.sharedPreferences.getString(GCM_REGISTRATION_ID, "");
		return regId;
	}
	
	public void saveAppVersion(int appVersion){
		this.sharedPreferences.edit().putInt(PROPERTY_APP_VERSION, appVersion).commit();		
	}
	
	public int getAppVersion(){
		int appVersion = this.sharedPreferences.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
		return appVersion;
	}
	
	public void setCustomerRegisteredFlag(boolean customerRegisteredFlag){
		this.sharedPreferences.edit().putBoolean(KEY_IS_CUSTOMER_REGISTERED, customerRegisteredFlag).commit();
	}
	
	public boolean getCustomerRegisteredFlag(){
		boolean flag = this.sharedPreferences.getBoolean(KEY_IS_CUSTOMER_REGISTERED, false);
		return flag;
	}
}
