package com.project.chefensa;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.project.chefensa.adapter.CartMealListAdapter;
import com.project.chefensa.adapter.NavDrawerListAdapter;
import com.project.chefensa.fragment.ContactUsFragment;
import com.project.chefensa.fragment.HowItWorksFragment;
import com.project.chefensa.fragment.MealDetailFragment;
import com.project.chefensa.fragment.MealOrderFragment;
import com.project.chefensa.fragment.MyOrdersFragment;
import com.project.chefensa.fragment.MyProfileFragment;
import com.project.chefensa.fragment.UserLoginFragment;
import com.project.chefensa.model.NavDrawerItem;
import com.project.chefensa.util.ConstantUtil;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements FragmentManager.OnBackStackChangedListener{
	
	private Context mContext;
	
	private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
    private NavDrawerListAdapter adapter;
	
	//flipboard animation
    private Handler mHandler = new Handler();
    public boolean mShowingBack = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		mContext = this;
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer_list_item);
        
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch(position){
				case 0:
					if(isLunchTime()){
						getFragmentManager().beginTransaction().replace(R.id.container,
										new MealDetailFragment(1, "7:00PM - 11:00PM", ConstantUtil.dinnerList)).addToBackStack(null).commit();
						} else {
							getFragmentManager().beginTransaction().replace(R.id.container,
									new MealDetailFragment(0, "11:00AM - 3:00PM", ConstantUtil.dinnerList)).addToBackStack(null).commit();
						}
					break;
				case 1:
					getFragmentManager().beginTransaction().replace(R.id.container, new UserLoginFragment()).addToBackStack(null).commit();
					break;
				case 2:
					getFragmentManager().beginTransaction().replace(R.id.container, new MyOrdersFragment()).addToBackStack(null).commit();
					break;
				case 3:
					getFragmentManager().beginTransaction().replace(R.id.container, new MyProfileFragment()).addToBackStack(null).commit();
					break;
				case 4:
					getFragmentManager().beginTransaction().replace(R.id.container, new HowItWorksFragment()).addToBackStack(null).commit();
					break;
				case 5:
					getFragmentManager().beginTransaction().replace(R.id.container, new ContactUsFragment()).addToBackStack(null).commit();
					break;
				}
				mDrawerLayout.closeDrawer(mDrawerList);
				mHandler.post(new Runnable() {
	    			@Override
	    			public void run() {
	    				invalidateOptionsMenu();
	    			}
	    		});
			}
		});
        
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        navDrawerItems = new ArrayList<NavDrawerItem>();
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        navMenuIcons.recycle();
        
        adapter = new NavDrawerListAdapter(this, navDrawerItems);
        mDrawerList.setAdapter(adapter);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.app_name){
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        //flipboard animation
        ConstantUtil.populateTestData();
        if (savedInstanceState == null) {
        	if(isLunchTime()){
        		getFragmentManager().beginTransaction().add(R.id.container, new MealDetailFragment(0 , "11:00AM - 3:00PM", ConstantUtil.lunchList)).commit();
        	} else {
        		getFragmentManager().beginTransaction().add(R.id.container, new MealDetailFragment(1 , "7:00PM - 11:00PM", ConstantUtil.lunchList)).commit();
        	}
        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }
        getFragmentManager().addOnBackStackChangedListener(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ConstantUtil.lunchList.clear();
		ConstantUtil.dinnerList.clear();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem switchMenuItem;
		if(isLunchTime()){
			switchMenuItem = menu.add(Menu.NONE, R.id.action_flip,
					Menu.NONE, mShowingBack ? R.string.lunch_string : R.string.dinner_string);
		} else {
			switchMenuItem = menu.add(Menu.NONE, R.id.action_flip,
					Menu.NONE, mShowingBack ? R.string.dinner_string : R.string.lunch_string);
		}
		switchMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		MenuItem cartMenuItem = menu.findItem(R.id.action_cart);
		/*if (fragmentType != FRAGMENT_TYPE_MEALLISTFRAGMENT) {
			switchMenuItem.setVisible(false);
			cartMenuItem.setVisible(false);
		} else {
			switchMenuItem.setVisible(true);
			cartMenuItem.setVisible(true);
		}*/
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
		if (item.getItemId() == R.id.action_flip) {
			flipCard();
            return true;
		}
		if(item.getItemId() == R.id.action_cart){
			showCart();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void flipCard() {
		if (mShowingBack) {
			getFragmentManager().popBackStack();
			return;
		}

		mShowingBack = true;
		if(isLunchTime()){
		getFragmentManager().beginTransaction().setCustomAnimations(R.animator.card_flip_right_in,
						R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out).replace(R.id.container,
						new MealDetailFragment(1, "7:00PM - 11:00PM", ConstantUtil.dinnerList)).addToBackStack(null).commit();
		} else {
			getFragmentManager().beginTransaction().setCustomAnimations(R.animator.card_flip_right_in,
					R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out).replace(R.id.container,
					new MealDetailFragment(0, "11:00AM - 3:00PM", ConstantUtil.dinnerList)).addToBackStack(null).commit();
		}
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				invalidateOptionsMenu();
			}
		});
	}
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

	@Override
	public void onBackStackChanged() {
		mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        invalidateOptionsMenu();
	}
	
	public void showCart(){
		final Dialog cartDialog = new Dialog(mContext);
		cartDialog.setContentView(R.layout.cart_dialog_view);
		cartDialog.setTitle(R.string.cart_string);
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(cartDialog.getWindow().getAttributes());
	    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		cartDialog.getWindow().setAttributes(lp);
		
		ListView mealListView = (ListView)cartDialog.findViewById(R.id.cart_meal_list);
		mealListView.setAdapter(new CartMealListAdapter(ConstantUtil.lunchList, mContext));
		mealListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				cartDialog.dismiss();
			}
			
		});
		Button placeOrderButton = (Button)cartDialog.findViewById(R.id.place_order_button);
		placeOrderButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getFragmentManager().beginTransaction().replace(R.id.container, new MealOrderFragment()).addToBackStack(null).commit();
				cartDialog.dismiss();
			}
		});
		cartDialog.show();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				invalidateOptionsMenu();
			}
		});
	}
	
	private boolean isLunchTime(){
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.getTime().getHours();
		if(hour >= 1 && hour < 15){
			return true;
		} else {
			return false;
		}
	}
}
