package com.exchange.tool;

import java.util.List;

import com.exchange.activity.R;
import com.exchange.bean.PublishedItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PublishItemAdapter extends ArrayAdapter<PublishedItem> {
	
	private int resourceId;										//布局xml文件的Id
//	private Context context;
	public PublishItemAdapter(Context context, int textViewResourceId,
			List<PublishedItem> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.resourceId = textViewResourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		PublishedItem publishedItem = getItem(position); 
		View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//		TextView txt1 = (TextView) view.findViewById(R.id.txt1);
//		TextView txt2 = (TextView) view.findViewById(R.id.txt2);
//		txt1.setText(publishedItem.getUsername());
//		txt2.setText(publishedItem.getPrice());
		ImageView head_pic = (ImageView) view.findViewById(R.id.head_pic);					//头像
		TextView name_view = (TextView) view.findViewById(R.id.username_tv);				//用户名
//		ListView commodity_image_lv = (ListView) view.findViewById(R.id.image_listview);	//商品图片
		TextView price_tv = (TextView) view.findViewById(R.id.price_tv);					//价格栏
		TextView time_tv = (TextView) view.findViewById(R.id.time_tv);						//时间栏
		TextView decration_tv = (TextView) view.findViewById(R.id.decration_tv);			//商品名详细描述
		
		name_view.setText(publishedItem.getUsername());
		price_tv.setText(publishedItem.getPrice());
		time_tv.setText(publishedItem.getTime());
		decration_tv.setText(publishedItem.getDecration());
		/*head_pic.setImageResource((publishedItem.getUser()).getHead_pic_id());	
		name_view.setText(publishedItem.getUser().getUsername());
		time_tv.setText(publishedItem.getTime());
		decration_tv.setText((publishedItem.getCmdt()).getDecration());
		price_tv.setText(publishedItem.getCmdt().getPrice());
		c((publishedItem.getUser()).getHead_pic_id());
		c(publishedItem.getUser().getUsername());
		c(publishedItem.getTime());*/
		//需要一个adapter给这个商品的图片ListView赋值
		/*List<Integer> image_pic_list  = publishedItem.getCmdt().getCmd_image_list();
		ImageListViewAdapter image_adapter = new ImageListViewAdapter(context, R.layout.image_item, image_pic_list);
		commodity_image_lv.setAdapter(image_adapter);*/

		return view;
	}
	
	

}
