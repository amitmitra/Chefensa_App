	package com.project.chefensa.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.protocol.ResponseConnControl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.chefensa.model.Chef;
import com.project.chefensa.model.Customer;
import com.project.chefensa.model.Meal;
import com.project.chefensa.model.Order;
import com.project.chefensa.responsehandler.CustomerHandler;
import com.project.chefensa.responsehandler.MealAvailabilityHandler;
import com.project.chefensa.responsehandler.MealResponseHandler;

public class APIRequestUtil extends Application {
	
	private static final Logger logger = Logger.getLogger(APIRequestUtil.class.getSimpleName());
	
    private static RequestQueue mRequestQueue;
    private static APIRequestUtil mInstance;
    private static Context mCtx;
    private ImageLoader mImageLoader;
    private static final String APIURL = "http://192.168.0.14:8080/Chefensa-WebService";

    ArrayList<Meal> mealArrayList = new ArrayList<Meal>();

    private APIRequestUtil(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader =  new ImageLoader(mRequestQueue, new LruBitMapCache(LruBitMapCache.getCacheSize(mCtx)));
    }

    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


    public static synchronized APIRequestUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new APIRequestUtil(context);
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

    public ArrayList<Meal> getMenuForDay(final MealResponseHandler mealHandler){

        String url = APIURL;
        url = url + "/menu";
        url = url + "/menuList";
        Calendar calendar = Calendar.getInstance();
        String dateString = calendar.get(Calendar.YEAR) + ":"+ (calendar.get(Calendar.MONTH)+1) + ":"+ calendar.get(Calendar.DATE);
        url = url + "?date="+ dateString;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
            	logger.info("meal list response successfully received");
              if(jsonArray!=null){
                  Gson gson = new GsonBuilder().create();
                  try {
                      for (int i = 0; i < jsonArray.length(); i++) {
                          mealArrayList.add(gson.fromJson(jsonArray.get(i).toString(), Meal.class));
                      }
                      logger.info("response successfully parsed");
                      mealHandler.mealResponseRecieved(mealArrayList);
                  }catch (JSONException e){
                	  e.printStackTrace();
                  }
              }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                logger.info("not able to fetch meals for the day" + volleyError.getMessage());
            }
        });
        System.out.println(jsonArrayRequest);
        mRequestQueue.add(jsonArrayRequest);
        return mealArrayList;
    }

    public Map<Long,Integer> getAvailableMealCount(final MealAvailabilityHandler mealAvailabilityHandler){
        final Map<Long,Integer> mealCountMap = new HashMap<Long, Integer>();
        String url = APIURL;
        url = url + "/menu/menuList/mealCount";
        StringRequest stringRequest = new StringRequest(url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null){
                    String[] mealList = response.split(",");
                    for(int i=0;i<mealList.length;i++){
                        String[] meal=mealList[i].split(":");
                        mealCountMap.put(Long.parseLong(meal[0]),Integer.parseInt(meal[1]));
                    }
                    mealAvailabilityHandler.mealAvailabilityResponse(mealCountMap);
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                logger.info("not able to fetch availability of meals " + error.getMessage());
            }
        });
        mRequestQueue.add(stringRequest);
        System.out.println("API Request" + stringRequest);
        return mealCountMap;
    }

    public Chef getChefDetails(long chefId){
        Chef chef = new Chef();
        String url = APIURL;
        url = url + "/chef/chefInfo";
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
        //final Order order1= new Order(0,1,234,4,20,1);

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
                String jsonString =gson.toJson(order);
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
    
	public void createCustomerUsingDeviceIdAndGCMId(Context c,
			final Customer cust, final CustomerHandler handler) {
		String url = APIURL;
		url = url + "/customer/create";
		
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject arg0) {
				logger.log(Level.INFO, "customer successfully");
				handler.getCustomerResponse();
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				logger.log(Level.INFO, "Not able create customer data: " + error.getMessage());
			}
		}){
			@Override
			public String getBodyContentType() {
				return "application/json; charset=utf-8";
			}

			@Override
			public byte[] getBody() {
				String httpPostBody = "";
				Gson gson = new GsonBuilder().create();
				String jsonString = gson.toJson(cust);
				httpPostBody = jsonString;
				return httpPostBody.getBytes();
			}
		};
		
		mRequestQueue.add(request);
	}
	
	public void increaseHit(String deviceId){
		String url = APIURL;
        url = url + "/customer/hit?deviceId=" + deviceId;
        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				logger.info("Successful transaction");
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				logger.info("transaction unsuccessful : " + error.toString());
			}
		});
        mRequestQueue.add(request);
	}
}