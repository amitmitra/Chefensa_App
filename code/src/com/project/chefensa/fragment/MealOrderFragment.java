package com.project.chefensa.fragment;

import java.util.Iterator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.chefensa.R;
import com.project.chefensa.model.Meal;
import com.project.chefensa.util.ConstantUtil;

public class MealOrderFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.meal_order_fragment_layout, container, false);
		
		LinearLayout mealListView = (LinearLayout)view.findViewById(R.id.meal_order_list);
		TextView totalPriceView = (TextView)view.findViewById(R.id.total_view);
		populateOrderList(inflater, container, mealListView);
		totalPriceView.setText("Rs 650");
		return view;
	}
	
	private void populateOrderList(LayoutInflater inflater, ViewGroup container, LinearLayout mealListView){
		int i = 0;
		for (Iterator<Meal> iterator = ConstantUtil.lunchList.iterator(); iterator.hasNext();) {
			Meal meal = iterator.next();
			View view = inflater.inflate(R.layout.cart_list_item_view, container, false);
			TextView countView = (TextView)view.findViewById(R.id.item_number_view);
			TextView mealNameView = (TextView)view.findViewById(R.id.meal_name_view);
			TextView mealConView = (TextView)view.findViewById(R.id.meal_content_view);
			TextView mealQuantityView = (TextView)view.findViewById(R.id.meal_quantity_view);
			TextView mealPriceView = (TextView)view.findViewById(R.id.meal_price_view);
			i++;
			countView.setText(i + ".");
			mealNameView.setText(meal.getMealName());
			mealConView.setText(meal.getMealContent());
			mealQuantityView.setText("2");
			mealPriceView.setText("Rs 200");
			ImageView removeButton = (ImageView)view.findViewById(R.id.remove_from_cart_button);
			mealListView.addView(view);
		}
	}
}
