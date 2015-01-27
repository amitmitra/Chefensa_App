package com.project.chefensa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.project.chefensa.db.ChefensaDataSource;
import com.project.chefensa.model.Customer;
import com.project.chefensa.model.Meal;
import com.project.chefensa.responsehandler.CustomerHandler;
import com.project.chefensa.responsehandler.MealAvailabilityHandler;
import com.project.chefensa.responsehandler.MealResponseHandler;
import com.project.chefensa.util.APIRequestUtil;
import com.project.chefensa.util.ChefensaPreferenceManager;
import com.project.chefensa.util.ConstantUtil;


public class SplashScreenActivity extends Activity {
	
	private final Logger logger = Logger.getLogger(SplashScreenActivity.class.getSimpleName());
	
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	public static final String EXTRA_MESSAGE = "message";
    
    private static final String GCM_SENDER_ID = "170453453515";
    
	private static int SPLASH_TIME_OUT = 1000;
	private Context mContext;
	
	private GoogleCloudMessaging gcm;
	private String regId;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.splash_screen_layout);
		
		if(checkPlayServices()){
			gcm = GoogleCloudMessaging.getInstance(mContext);
			regId = ConstantUtil.getRegistrationId(mContext);
			if(regId.isEmpty() || regId.equals("")){
				registerInBackground();
			} else {
				APIRequestUtil.getInstance(mContext).increaseHit(ConstantUtil.getDeviceId(mContext));
			}
		} else {
			logger.info("No valid Google Play Services APK found.");
		}
		
		
		ChefensaPreferenceManager.instance().initialize(getApplicationContext());

        ChefensaPreferenceManager.instance().saveCurrentMealDate(0);
   if(!DateUtils.isToday(ChefensaPreferenceManager.instance().getCurrentMealDate())){

            MealResponseHandler mealResponseHandler = new MealResponseHandler() {
                @Override
                public void mealResponseRecieved(ArrayList<Meal>  mealList) {
                    if(!mealList.isEmpty()) {
                        ChefensaDataSource.getInstance(getApplicationContext()).clearMealTable();
                        ChefensaDataSource.getInstance(getApplicationContext()).addMeals(mealList);
                        Date date = new Date();
                        ChefensaPreferenceManager.instance().saveCurrentMealDate(date.getTime());
                    }
                    return;
                }
            };
            ArrayList<Meal> mealList = APIRequestUtil.getInstance(getApplicationContext()).getMenuForDay(mealResponseHandler);

        }else{
            MealAvailabilityHandler mealAvailHandler= new MealAvailabilityHandler() {
                @Override
                public void mealAvailabilityResponse(Map<Long, Integer> mealAvailMap) {
                    if(!mealAvailMap.isEmpty()){
                        ChefensaDataSource.getInstance(getApplicationContext()).updateMealCount(mealAvailMap);
                    }
                }
            };
           APIRequestUtil.getInstance(getApplicationContext()).getAvailableMealCount(mealAvailHandler);
      }
		
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, ChefensaFragmentActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
	}
	
	private boolean checkPlayServices() {
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            logger.info("This device is not supported.");
	            finish();
	        }
	        return false;
	    }
	    return true;
	}
	
	private void registerInBackground(){
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				if(gcm == null){
					gcm = GoogleCloudMessaging.getInstance(mContext);
				}		
				try {
					regId = gcm.register(GCM_SENDER_ID);
					Customer customer = new Customer();
					customer.setDeviceId(ConstantUtil.getDeviceId(mContext));
					customer.setGcmId(regId);	
					CustomerHandler custHandler = new CustomerHandler() {
						@Override
						public void getCustomerResponse() {;
							ChefensaPreferenceManager.instance().initialize(mContext);
							ChefensaPreferenceManager.instance().saveGCMRegistrationId(regId);
							ChefensaPreferenceManager.instance().setCustomerRegisteredFlag(true);
						}
					};	
					APIRequestUtil.getInstance(mContext).createCustomerUsingDeviceIdAndGCMId(mContext, customer, custHandler);
				} catch (IOException e) {
					logger.info("Error: GCM Rgistration Error");
					e.printStackTrace();
				}
				return null;
			}
		}.execute();
	}

}
