package com.project.chefensa.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.project.chefensa.model.Meal;

public class ConstantUtil {

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

	public static String getIMEICode(Context c) {
		TelephonyManager tm = (TelephonyManager) c
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}
}
