package com.project.chefensa.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.chefensa.R;
import com.project.chefensa.model.Meal;

public class CartMealListAdapter extends BaseAdapter {
	
	List<Meal> mealList;
	Context mContext;
	
	public CartMealListAdapter(List<Meal> mealList, Context mContext) {
		this.mealList = mealList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return mealList.size();
	}

	@Override
	public Meal getItem(int position) {
		return mealList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.cart_list_item_view, parent, false);
		}
		
		TextView itemNumberView = (TextView)convertView.findViewById(R.id.item_number_view);
		TextView mealNameView = (TextView)convertView.findViewById(R.id.meal_name_view);
		TextView mealContentView = (TextView)convertView.findViewById(R.id.meal_content_view);
		TextView mealQuantityView = (TextView)convertView.findViewById(R.id.meal_quantity_view);
		TextView mealPriceView = (TextView)convertView.findViewById(R.id.meal_price_view);
		
		ImageView removeButton = (ImageView)convertView.findViewById(R.id.remove_from_cart_button);
		
		Meal meal = getItem(position);
		
		itemNumberView.setText((position+1) + ". ");
		mealNameView.setText(meal.getName());
		mealContentView.setText(meal.getMealContent());
		mealQuantityView.setText("2");
		mealPriceView.setText("200");
		
		removeButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				
			}
		});
		return convertView;
	}

}
