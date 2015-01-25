package com.project.chefensa;

import java.util.logging.Logger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
	
	Logger logger = Logger.getLogger(GcmBroadcastReceiver.class.getSimpleName());

	@Override
	public void onReceive(Context context, Intent intent) {
		String messageType = intent.getStringExtra("meassage_type");
		logger.info(messageType);
        setResultCode(Activity.RESULT_OK);

	}

}
