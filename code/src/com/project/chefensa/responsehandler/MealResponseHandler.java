package com.project.chefensa.responsehandler;

import com.project.chefensa.model.Meal;

import java.util.ArrayList;

/**
 * Created by harshwardhan.c on 24/01/15.
 */
public interface MealResponseHandler {
    public void mealResponseRecieved(ArrayList<Meal> mealArrayList);
}
