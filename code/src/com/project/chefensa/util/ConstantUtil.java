package com.project.chefensa.util;

import java.util.ArrayList;
import java.util.List;

import com.project.chefensa.model.Meal;

public class ConstantUtil {

	//meal timing constants
	public static final int MEAL_TIME_BREAKFAST = 0;
	public static final int MEAL_TIME_LUNCH = 1;
	public static final int MEAL_TIME_EVENING_SNACKS = 2;
	public static final int MEAL_TIME_DINEER = 3;
	
	//meal category constants
	public static final int MEAL_CATEGORY_VEG = 0;
	public static final int MEAL_CATEGORY_EGG = 1;
	public static final int MEAL_CATEGORY_NONVEG = 2;
	
	//spicyness constants
	public static final int NON_SPICY = 0;
	public static final int SPICY = 1;
	
	//cart constants
	public static final int MEAL_NOT_IN_CART = 0;
	public static final int MEAL_IN_CART = 1;
	
	//Meal Test Data
		public static List<Meal> lunchList = new ArrayList<Meal>();
		public static List<Meal> dinnerList = new ArrayList<Meal>();
	
	public static void populateTestData(){
		Meal lunch1 = new Meal("lunch1", null, "Desc1", "#North Indian #Sweat", 0, null, null, 
				1, null, "Amit Mitra", null, 0, 0, 100, 1, 20,  3.5f);
		lunchList.add(lunch1);
		Meal lunch2 = new Meal("lunch1", null, "Desc1", "#North Indian #Sweat", 0, null, null, 
				1, null, "Amit Mitra", null, 0, 0, 100, 1, 20,  3.5f);
		lunchList.add(lunch2);
		Meal lunch3 = new Meal("lunch1", null, "Desc1", "#North Indian #Sweat", 0, null, null, 
				1, null, "Amit Mitra", null, 0, 0, 100, 1, 20,  3.5f);
		lunchList.add(lunch3);
		Meal lunch4 = new Meal("lunch1", null, "Desc1", "#North Indian #Sweat", 0, null, null, 
				1, null, "Amit Mitra", null, 0, 0, 100, 1, 20,  3.5f);
		lunchList.add(lunch4);
		Meal lunch5 = new Meal("lunch1", null, "Desc1", "#North Indian #Sweat", 0, null, null, 
				1, null, "Amit Mitra", null, 0, 0, 100, 1, 20,  3.5f);
		lunchList.add(lunch5);
		
		Meal dinner1 = new Meal("lunch1", null, "Desc1", "#North Indian #Sweat", 0, null, null, 
				1, null, "Amit Mitra", null, 0, 0, 100, 1, 20,  3.5f);
		dinnerList.add(dinner1);
		Meal dinner2 = new Meal("lunch1", null, "Desc1", "#North Indian #Sweat", 0, null, null, 
				1, null, "Amit Mitra", null, 0, 0, 100, 1, 20,  3.5f);
		dinnerList.add(dinner2);
		Meal dinner3 = new Meal("lunch1", null, "Desc1", "#North Indian #Sweat", 0, null, null, 
				1, null, "Amit Mitra", null, 0, 0, 100, 1, 20,  3.5f);
		dinnerList.add(dinner3);
		Meal dinner4 = new Meal("lunch1", null, "Desc1", "#North Indian #Sweat", 0, null, null, 
				1, null, "Amit Mitra", null, 0, 0, 100, 1, 20,  3.5f);
		dinnerList.add(dinner4);
		Meal dinner5 = new Meal("lunch1", null, "Desc1", "#North Indian #Sweat", 0, null, null, 
				1, null, "Amit Mitra", null, 0, 0, 100, 1, 20,  3.5f);
		dinnerList.add(dinner5);
		Meal dinner6 = new Meal("lunch1", null, "Desc1", "#North Indian #Sweat", 0, null, null, 
				1, null, "Amit Mitra", null, 0, 0, 100, 1, 20,  3.5f);
		dinnerList.add(dinner6);
	}
}
