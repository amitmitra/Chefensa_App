package com.project.chefensa.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.project.chefensa.R;
import com.project.chefensa.util.ConstantUtil;

public class TabbedFragment extends Fragment implements OnClickListener{

	public static final String TAG = TabbedFragment.class.getSimpleName();
	
	Context mContext;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  ConstantUtil.populateTestData();
    }
 
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tabbed_fragment_layout, container,
				false);

		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.meal_container,
						new MealDetailFragment(ConstantUtil.lunchList)).commit();

		return v;
	}
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	ConstantUtil.lunchList.clear();
    	ConstantUtil.dinnerList.clear();
    }

	@Override
	public void onClick(View v) {
		
	}
}
