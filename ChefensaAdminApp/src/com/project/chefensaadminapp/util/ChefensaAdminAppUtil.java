package com.project.chefensaadminapp.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


public class ChefensaAdminAppUtil {
	
	public static final String SERVER_URL = "http://192.168.0.3:8080/";
	
	public static final int GENDER_MALE = 0;
	public static final int GENDER_FEMALE = 1;
	
	public static final int MARITAL_STATUS_UNMARRIED = 0;
	public static final int MARITAL_STATUS_MARRIED = 1;
	
	public static final int MEAL_TYPE_VEG = 0;
	public static final int MEAL_TYPE_EGG = 1;
	public static final int MEAL_TYPE_NON_VEG = 2;
	
	public static final int CHEF_TYPE_HOUSE_WIFE = 0;
	public static final int CHEF_TYPE_VENDOR = 1;
	
	public static final int WORKING_TIME_MORNING = 0;
	public static final int WORKING_TIME_EVENING = 1;
	public static final int WORKING_TIME_BOTH = 2;
	
	public static String sendHttpRequest(String url, String jsonString){
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpResponse response;
		String responseString = null;
		try {
			StringEntity se = new StringEntity( jsonString);
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			post.setEntity(se);
			response = httpClient.execute(post);
			responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return responseString;
	}
	
}
