package com.project.chefensa.util;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.chefensa.model.Chef;
import com.project.chefensa.model.Customer;
import com.project.chefensa.model.Meal;
import com.project.chefensa.model.Order;

import org.json.JSONObject;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class APIInterface  extends Application {
    private static RequestQueue mRequestQueue;
    private static APIInterface mInstance;
    private static Context mCtx;
    private ImageLoader mImageLoader;
    private static final String APIURL = "http://192.168.0.3:8080/Chefensa-WebService";

    private APIInterface(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
        
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
        	private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);
        	
			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				cache.put(url, bitmap);
			}
			
			@Override
			public Bitmap getBitmap(String url) {
				return cache.get(url);
			}
		});
    }

    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


    public static synchronized APIInterface getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new APIInterface(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public ArrayList<Meal> getMenuForDay(){
        ArrayList<Meal> mealArrayList = new ArrayList<Meal>();
        String url = "";
        url = url + "/menu";
        url = url + "/menuList";
        Date date= new Date((new java.util.Date()).getTime());
        url = url + "?date="+ date;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //todo: parse meallist response
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mRequestQueue.add(jsonObjReq);
        return mealArrayList;
    }

    public Map<Long,Integer> getAvailableMealCount(){
        final Map<Long,Integer> mealCountMap = new HashMap<Long, Integer>();
        String url = "";
        url = url + "/menuList/mealCount";
        java.util.Date date= new java.util.Date();
        url = url + "?date="+ new Timestamp(date.getTime());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //todo parse network response
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mRequestQueue.add(jsonObjReq);
        return mealCountMap;
    }

    public Chef getChefDetails(long chefId){
        Chef chef = new Chef();
        String url = APIURL;
        url = url + "/chef/chefInfo";
        java.util.Date date= new java.util.Date();
        url = url + "?id="+ chefId;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //todo parse network response
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mRequestQueue.add(jsonObjReq);
        return chef;
    }

    public int createOrder(final Order order){
        int response=0;
        final Order order1= new Order(0,1,234,4,20,1);

        String url = APIURL;
        url = url + "/order/placeOrder" ;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                String httpPostBody="";
                Gson gson = new GsonBuilder().create();
                String jsonString =gson.toJson(order1);
                // usually you'd have a field with some values you'd want to escape, you need to do it yourself if overriding getBody. here's how you do it
                httpPostBody=jsonString;
                System.out.println(httpPostBody);
                return httpPostBody.getBytes();
            }
        };
        mRequestQueue.add(jsonObjReq);

        return response;
    }

    public Customer getCustomerInfo(long id){
        Customer cust;
        id=1;
        String url = APIURL;
        url = url + "/customer/customerInfo";
        java.util.Date date= new java.util.Date();
        url = url + "?id="+ id;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //todo parse network response
                        Gson gson = new GsonBuilder().create();
                        Customer cust = gson.fromJson(response.toString(),Customer.class);
                        System.out.println("");
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mRequestQueue.add(jsonObjReq);
        cust=null;
        return cust;
    }
    public int sendCustomerDeviceId(){
            String deviceId= "deviceid";//todo: get device iD
            final Customer cust = new Customer(deviceId);
        int response=0;
        // final Order order1= new Order(0,1,234,4,20,1);

        String url = APIURL;
        url = url + "/customer/create" ;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                String httpPostBody="";
                Gson gson = new GsonBuilder().create();
                String jsonString =gson.toJson(cust);
                // usually you'd have a field with some values you'd want to escape, you need to do it yourself if overriding getBody. here's how you do it
                httpPostBody=jsonString;
                System.out.println(httpPostBody);
                return httpPostBody.getBytes();
            }
        };
        mRequestQueue.add(jsonObjReq);

        return response;

    }
}