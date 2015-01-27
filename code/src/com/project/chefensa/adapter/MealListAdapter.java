package com.project.chefensa.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.chefensa.R;
import com.project.chefensa.db.ChefensaDataSource;
import com.project.chefensa.model.Meal;

public class MealListAdapter extends BaseAdapter {

	List<Meal> mealList;
	Context mContext;

	public MealListAdapter(List<Meal> mealList, Context mContext) {
		this.mContext = mContext;
        getMeaList();
	}

    public void getMeaList(){
        mealList=ChefensaDataSource.getInstance(mContext).getMeals();
        System.out.println("MEALLIST--->" + mealList.toString());
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.meal_list_view_item, parent, false);
		}
	
		final ImageView mealImageView = (ImageView) convertView.findViewById(R.id.meal_image_view);
		final TextView mealDescriptionView = (TextView)convertView.findViewById(R.id.meal_description_view);
		TextView mealNameView = (TextView) convertView.findViewById(R.id.meal_name_view);
		ImageView mealCategoryView = (ImageView) convertView.findViewById(R.id.meal_category_view);
		ImageView chefImageView = (ImageView) convertView.findViewById(R.id.chef_image_view);
		LinearLayout chefLayout = (LinearLayout)convertView.findViewById(R.id.chef_layout);
		TextView chefNameView = (TextView) convertView.findViewById(R.id.chef_name_view);
		TextView mealContentView = (TextView)convertView.findViewById(R.id.meal_content_view);
		final TextView mealTypeView = (TextView) convertView.findViewById(R.id.meal_type_view);
		TextView priceView = (TextView) convertView.findViewById(R.id.price_view);
		TextView mealInCartView = (TextView) convertView.findViewById(R.id.meals_in_cart_count);
		ImageView minusButton = (ImageView) convertView.findViewById(R.id.minus_button);
		ImageView plusButton = (ImageView) convertView.findViewById(R.id.plus_button);
		
		mealImageView.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				mealDescriptionView.setVisibility(View.VISIBLE);
			}
		});
		
		mealDescriptionView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mealDescriptionView.setVisibility(View.GONE);
			}
		});

		Meal meal = getItem(position);

		mealImageView.setImageResource(R.drawable.lunch1);
		mealContentView.setText(meal.getMealContent());

		mealNameView.setText(meal.getMealName());
		int mealCategory = meal.getMealCategory();
		if (mealCategory == 0) {
			mealCategoryView.setImageResource(R.drawable.veg_symbol_image);
		} else if (mealCategory == 1) {
			mealCategoryView.setImageResource(R.drawable.non_veg_symbol_image);
		} else {
			mealCategoryView.setImageResource(R.drawable.non_veg_symbol_image);
		}
		
		chefImageView.setImageResource(R.drawable.chef_image);
		chefLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "Chef Details will open", Toast.LENGTH_SHORT).show();
			}
		});

		chefNameView.setText(meal.getChefName());

		mealTypeView.setText(meal.getMealType());
		priceView.setText(String.valueOf(meal.getMealPrice()));
		mealInCartView.setText(meal.getMealCount()+"");
        mealInCartView.setTag("cartcount" + position);
        plusButton.setTag("plus" + position);
		plusButton.setOnClickListener(mClickListener);
		minusButton.setTag("minus" + position);
		minusButton.setOnClickListener(mClickListener);

		mealDescriptionView.setText(meal.getMealDescription());
		
		return convertView;

	}
    private View.OnClickListener mClickListener = new View.OnClickListener() {

        public void onClick(View v) {
                if (v.getTag() instanceof String) {
                    String tag = (String)v.getTag();
                    {
                        if(tag.contains("plus")){
                            View view = (View) v.getParent();
                            int position = Integer.parseInt(tag.substring(4,5));
                            TextView mealInCartView =(TextView)view.findViewWithTag("cartcount"+position);
                            int i = Integer.parseInt(mealInCartView.getText().toString());

                            if(ChefensaDataSource.getInstance(mContext).getAvailableCount(mealList.get(position).getMealId())>0) {
                                ChefensaDataSource.getInstance(mContext).addToCart(mealList.get(position).getMealId(), 1);
                                if(i>=0) {
                                    mealInCartView.setText("" + (i + 1));
                                }
                            }

                        }
                        else if(tag.contains("minus")) {
                            View view = (View) v.getParent();
                            int position = Integer.parseInt(tag.substring(5,6));
                            TextView mealInCartView =(TextView)view.findViewWithTag("cartcount"+position);
                            int i = Integer.parseInt(mealInCartView.getText().toString());
                            if(ChefensaDataSource.getInstance(mContext).getInCartCount(mealList.get(position).getMealId())>0) {
                                ChefensaDataSource.getInstance(mContext).decreaseCartCount(mealList.get(position).getMealId());
                                if (i > 0) {
                                    mealInCartView.setText("" + (i - 1));
                                }
                            }
                        }
                    }
                }

        }
    };
}
