package com.project.chefensa.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.chefensa.R;
import com.project.chefensa.db.ChefensaDataSource;
import com.project.chefensa.model.Meal;

public class CartMealListAdapter extends BaseAdapter {
	
	List<Meal> cartList;
	Context mContext;
	
	public CartMealListAdapter(Context mContext) {
		this.mContext = mContext;
        cartList = new ArrayList<Meal>();
        getMealsInCart();
	}
    private void getMealsInCart(){
        List<Meal> mealList = ChefensaDataSource.getInstance(mContext).getMeals();
        for (int i = 0; i < mealList.size(); i++) {
            Meal element = mealList.get(i);
            if(element.getMealCount()>0){
                cartList.add(element);
            }
        }
    }

	@Override
	public int getCount() {
		return cartList.size();
	}

	@Override
	public Meal getItem(int position) {
		return cartList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
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
		mealNameView.setText(meal.getMealName());
		mealContentView.setText(meal.getMealContent());
		mealQuantityView.setText(cartList.get(position).getMealCount()+"");
		mealPriceView.setText(cartList.get(position).getMealCount()+"");
		
		removeButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				ChefensaDataSource.getInstance(mContext).removeFromCart(cartList.get(position).getMealId());
			}
		});
		return convertView;
	}

}
