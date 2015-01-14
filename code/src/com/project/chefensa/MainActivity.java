package com.project.chefensa;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.project.chefensa.adapter.NavDrawerListAdapter;
import com.project.chefensa.fragment.MealDetailFragment;
import com.project.chefensa.model.Meal;
import com.project.chefensa.model.NavDrawerItem;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements FragmentManager.OnBackStackChangedListener{
	
	private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
    private NavDrawerListAdapter adapter;
    
	//Meal Test Data
	List<Meal> lunchList = new ArrayList<Meal>();
	List<Meal> dinnerList = new ArrayList<Meal>();
	
	private void populateTestData(){
		Meal lunch1 = new Meal("lunch1","100", R.drawable.lunch1, "Desc1", "#North Indian #Sweat", 
				0, 2, "Amit Mitra", R.drawable.ic_launcher, 1, 14);
		lunchList.add(lunch1);
		Meal lunch2 = new Meal("lunch2","150", R.drawable.lunch2, "Desc2", "#South Indian #Continental", 
				1, 1, "Abhishek Ladha", R.drawable.ic_launcher, 2, 20);
		lunchList.add(lunch2);
		Meal lunch3 = new Meal("lunch3","160", R.drawable.lunch3, "Desc3", "#Continental", 
				0, 2, "Nimish", R.drawable.ic_launcher, 1, 23);
		lunchList.add(lunch3);
		Meal lunch4 = new Meal("lunch4","80", R.drawable.lunch4, "Desc4", "#Punjabi #North Indian", 
				0, 3, "Karishma Kapoor", R.drawable.ic_launcher, 1, 2);
		lunchList.add(lunch4);
		Meal lunch5 = new Meal("lunch5","100", R.drawable.lunch5, "Desc5", "#North Indian #Rajasthani", 
				2, 2, "Bholi Punjaban", R.drawable.ic_launcher, 1, 1);
		lunchList.add(lunch5);
		
		Meal dinner1 = new Meal("dinner1","100", R.drawable.dinner1, "Desc Dinner 1", "#Gujrati Thali", 
				0, 1, "Amit Mitra", R.drawable.ic_launcher, 1, 10);
		dinnerList.add(dinner1);
		Meal dinner2 = new Meal("dinner2","250", R.drawable.dinner2, "Desc Dinner 1", "#South Indian #Hyderabadi", 
				1, 2, "Dada Muni", R.drawable.ic_launcher, 3, 10);
		dinnerList.add(dinner2);
		Meal dinner3 = new Meal("dinner3","60", R.drawable.dinner3, "Desc Dinner 1", "#Rajasthani Special", 
				0, 3, "Virat Kohli", R.drawable.ic_launcher, 1, 0);
		dinnerList.add(dinner3);
		Meal dinner4 = new Meal("dinner4","140", R.drawable.dinner4, "Desc Dinner 1", "#Bengali", 
				0, 3, "XYZ", R.drawable.ic_launcher, 3, 5);
		dinnerList.add(dinner4);
		Meal dinner5 = new Meal("dinner5","100", R.drawable.dinner5, "Desc Dinner 1", "#North Indian", 
				2, 2, "Kajol", R.drawable.ic_launcher, 1, 14);
		dinnerList.add(dinner5);
		Meal dinner6 = new Meal("dinner6","110", R.drawable.dinner6, "Desc Dinner 1", "#Continetal", 
				0, 2, "Amit Mitra", R.drawable.ic_launcher, 1, 6);
		dinnerList.add(dinner6);
	}
	
	//flipboard animation
    private Handler mHandler = new Handler();
    private boolean mShowingBack = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer_list_item);
        
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        navDrawerItems = new ArrayList<NavDrawerItem>();
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
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
        populateTestData();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new MealDetailFragment(0 , "11:00AM - 3:00PM", lunchList)).commit();
        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }
        getFragmentManager().addOnBackStackChangedListener(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		lunchList.clear();
		dinnerList.clear();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem item = menu.add(Menu.NONE, R.id.action_flip, Menu.NONE, mShowingBack ? R.string.lunch_string : R.string.dinner_string);
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
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
		return super.onOptionsItemSelected(item);
	}
	
	private void flipCard() {
		if (mShowingBack) {
			getFragmentManager().popBackStack();
			return;
		}

		mShowingBack = true;

		getFragmentManager().beginTransaction().setCustomAnimations(R.animator.card_flip_right_in,
						R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out).replace(R.id.container,
						new MealDetailFragment(1, "7:00PM - 11:00PM", dinnerList)).addToBackStack(null).commit();
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
}
