package com.project.chefensa.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;

import com.project.chefensa.model.Meal;

public class ConstantUtil {
	
	private static final Logger logger = Logger.getLogger(ConstantUtil.class.getSimpleName());

	// meal timing constants
	public static final int MEAL_TIME_BREAKFAST = 0;
	public static final int MEAL_TIME_LUNCH = 1;
	public static final int MEAL_TIME_EVENING_SNACKS = 2;
	public static final int MEAL_TIME_DINEER = 3;

	// meal category constants
	public static final int MEAL_CATEGORY_VEG = 0;
	public static final int MEAL_CATEGORY_EGG = 1;
	public static final int MEAL_CATEGORY_NONVEG = 2;

	// spicyness constants
	public static final int NON_SPICY = 0;
	public static final int SPICY = 1;

	// cart constants
	public static final int MEAL_NOT_IN_CART = 0;
	public static final int MEAL_IN_CART = 1;

	// Meal Test Data
	public static List<Meal> lunchList = new ArrayList<Meal>();
	public static List<Meal> dinnerList = new ArrayList<Meal>();

	public static String getDeviceId(Context c) {
		TelephonyManager tm = (TelephonyManager) c
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}
	
	/*
	 * Method to find GCM registration Id
	 */
	public static String getRegistrationId(Context context) {
		ChefensaPreferenceManager.instance().initialize(context);
		String registrationId = ChefensaPreferenceManager.instance().getGCMRegistrationId();
	    if (registrationId.isEmpty()) {
	        logger.info("Registration not found.");
	        return "";
	    }    
	   /* int registeredVersion = ChefensaPreferenceManager.instance().getAppVersion();
	    int currentVersion = getAppVersion(context);
	    if (registeredVersion != currentVersion) {
	        logger.info("App version changed.");
	        return "";
	    }*/
	    return registrationId;
	}
	
	/*
	 * Method to find current app version
	 */
	private static int getAppVersion(Context context) {
	    try {
	        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.versionCode;
	    } catch (NameNotFoundException e) {
	    	throw new RuntimeException("Could not get package name: " + e);
	    }
	}
	
	public static void saveRegistrationId(Context context, String regId){
		
	}

}
