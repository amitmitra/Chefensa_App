package com.project.chefensa.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.chefensa.R;

public class MenuListAdapter extends BaseAdapter {

	Context context;
	String[] mListTitle;
	int[] mListIcon;
	LayoutInflater inflater;

	public MenuListAdapter(Context context, String[] title, int[] icon) {
		this.context = context;
		this.mListTitle = title;
		this.mListIcon = icon;
	}

	@Override
	public int getCount() {
		return mListTitle.length;
	}

	@Override
	public String getItem(int position) {
		return mListTitle[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView txtTitle;
		ImageView imgIcon;

        if(convertView == null){
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }


		// Locate the TextViews in drawer_list_item.xml
		txtTitle = (TextView) convertView.findViewById(R.id.title);

		// Locate the ImageView in drawer_list_item.xml
		imgIcon = (ImageView) convertView.findViewById(R.id.icon);

		// Set the results into TextViews
		txtTitle.setText(mListTitle[position]);

		// Set the results into ImageView
		imgIcon.setImageResource(mListIcon[position]);

		return convertView;
	}

}
