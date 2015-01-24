package com.project.chefensa.fragment;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.project.chefensa.R;
import com.project.chefensa.adapter.MealListAdapter;
import com.project.chefensa.model.Meal;

public class MealDetailFragment extends Fragment {
	
	private Context mContext;
	
	private List<Meal> mealList;
	
	ListView mealListView;

	public MealDetailFragment(List<Meal> mealList) {
		this.mealList = mealList;
	}

	public MealDetailFragment() {

	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.meal_detail_fragment_layout, container, false);
		mealListView = (ListView)view.findViewById(R.id.meals_list);
		mealListView.setAdapter(new MealListAdapter(mealList, mContext));
		return view;
	}

}
