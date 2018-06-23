package com.exchange.tool;

import java.util.List;

import com.exchange.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ImageListViewAdapter extends ArrayAdapter<Integer>{
	private int resourceId;
	public ImageListViewAdapter(Context context,
			int textViewResourceId, List<Integer> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		int imageid = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
		ImageView imageView = (ImageView) view.findViewById(R.id.image_view_item);
		imageView.setImageResource(imageid);
		return view;
	}
	
	
}
