package com.project.chefensa.util;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by amitmitra on 06/01/15.
 */
public class ChefensaUtil {

    public static final String CHEFENSA_SHARED_PREF = "chefensasharedpref";

    public static final String LOGIN_TYPE_KEY = "logintype";
    public static final String PERSON_NAME_KEY = "pname";
    public static final String PERSON_EMAIL_KEY = "pemail";
    public static final String PERSON_PHONE_NUMBER_KEY = "pnumber";
    public static final String IS_PERSON_LOGGEDIN_KEY = "ispersonloggedin";
    public static final String PERSON_LOGIN_TYPE_KEY = "plogintype";


    public static final int LOGIN_TYPE_GOOGLE = 1;
    public static final int LOGIN_TYPE_FACEBOOK = 2;

    public static void showToast(Context c, String text){
        Toast.makeText(c, text, Toast.LENGTH_SHORT).show();
    }

    public static boolean supportsGooglePlayServices(Context c) {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(c) == ConnectionResult.SUCCESS;
    }

}
