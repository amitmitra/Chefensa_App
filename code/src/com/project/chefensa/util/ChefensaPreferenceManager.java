package com.project.chefensa.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;


/**
 * Created by harshwardhan.c on 24/01/15.
 */
public class ChefensaPreferenceManager{
    private static ChefensaPreferenceManager instance = null;

    public static ChefensaPreferenceManager instance(){
        if (instance == null)
        {
            synchronized(ChefensaPreferenceManager.class) {
                if (instance == null)
                    instance = new ChefensaPreferenceManager();
            }
        }
        return instance;
    }

    private ChefensaPreferenceManager(){}

    private SharedPreferences sharedPreferences;

    public void initialize(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static final String KEY_CURRENTMEAL_DATE= "current_meal_date";

    public void saveCurrentMealDate(long sn){
        this.sharedPreferences.edit().putLong(KEY_CURRENTMEAL_DATE, sn).commit();
    }
    public long getCurrentMealDate(){
        long time= this.sharedPreferences.getLong(KEY_CURRENTMEAL_DATE, 0);
        return time;
    }
}
