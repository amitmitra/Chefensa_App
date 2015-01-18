package com.project.chefensa.adapter;

import java.util.List;

import com.project.chefensa.R;
import com.project.chefensa.model.Meal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MealListAdapter extends BaseAdapter {

	List<Meal> mealList;
	Context mContext;
	
	private Animator mCurrentAnimator;
    private int mShortAnimationDuration;

	public MealListAdapter(List<Meal> mealList, Context mContext) {
		this.mealList = mealList;
		this.mContext = mContext;
		mShortAnimationDuration = mContext.getResources().getInteger(android.R.integer.config_shortAnimTime);
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
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.meal_list_view_item, null);
		}
		
		final RelativeLayout container = (RelativeLayout)convertView.findViewById(R.id.container);

		final RelativeLayout smallView = (RelativeLayout) convertView.findViewById(R.id.meal_small_layout);
		ImageView mealImageView = (ImageView) convertView.findViewById(R.id.meal_image_view);
		TextView mealNameView = (TextView) convertView.findViewById(R.id.meal_name_view);
		ImageView mealCategoryView = (ImageView) convertView.findViewById(R.id.meal_category_view);
		TextView mealTypeView = (TextView) convertView.findViewById(R.id.meal_type_view);
		TextView priceView = (TextView) convertView.findViewById(R.id.price_view);
		Spinner oderQuantitySpinner = (Spinner) convertView.findViewById(R.id.order_quantity_spinner);
		ImageButton addToCartButton = (ImageButton) convertView.findViewById(R.id.add_to_cart_button);

		final RelativeLayout largeView = (RelativeLayout) convertView.findViewById(R.id.meal_large_layout);
		TextView mealpreparationView = (TextView) convertView.findViewById(R.id.meal_preparation_view);
		TextView serveNoteView = (TextView) convertView.findViewById(R.id.serve_note_view);
		ImageButton chefDetailButton = (ImageButton) convertView.findViewById(R.id.chef_detail_button);
		TextView chefNameView = (TextView) convertView.findViewById(R.id.chef_name_view);
		
		smallView.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				smallView.setVisibility(View.GONE);
				largeView.setVisibility(View.VISIBLE);
			}
		});
		
		largeView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				largeView.setVisibility(View.GONE);
				smallView.setVisibility(View.VISIBLE);
			}
		});

		Meal meal = getItem(position);

		mealImageView.setImageResource(R.drawable.lunch1);

		mealNameView.setText(meal.getName());
		int mealCategory = meal.getMealCategory();
		if (mealCategory == 0) {
			mealCategoryView.setImageResource(R.drawable.veg_symbol_image);
		} else if (mealCategory == 1) {
			mealCategoryView.setImageResource(R.drawable.non_veg_symbol_image);
		} else {
			mealCategoryView.setImageResource(R.drawable.non_veg_symbol_image);
		}

		mealTypeView.setText(meal.getMealType());
		priceView.setText("Rs " + meal.getPrice());
		
		addToCartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "Order will be added to cart",
						Toast.LENGTH_SHORT).show();
			}
		});

		mealpreparationView
				.setText("A long description about the dish/meal describing the food ingredients and often"
						+ "containing the recipe of the meal Lorem ipsum dolor sit amet, consectetur adipiscing elit. \n"
						+ "Utsit amet iaculis dui imperdiet. leo at, sollicitudin ligula.");
		mealpreparationView.setGravity(Gravity.CENTER_VERTICAL);
		serveNoteView.setText("Meal will serves 4 people");

		chefDetailButton.setImageResource(R.drawable.chef_image);
		chefDetailButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "Chef Details will open",
						Toast.LENGTH_SHORT).show();
			}
		});

		chefNameView.setText(meal.getChefName());

		return convertView;

	}
}
