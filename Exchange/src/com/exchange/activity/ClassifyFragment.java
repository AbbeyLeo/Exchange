package com.exchange.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exchange.entity.ICON;
import com.exchange.tool.ConfigTool;
import com.exchange.tool.HttpRequestTool;
import com.exchange.tool.TransformUnit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ClassifyFragment extends Fragment {
	private static LinearLayout categoryIconBar = null;							//声明商品分类图标栏
	//声明定义此Fragment的Handler
		public Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					Bundle data = msg.getData();
					String val = data.getString("value").trim();
					System.out.println(val);
					Gson gson = new Gson();
					List<ICON> iconsTemp = gson.fromJson(val,new TypeToken<List<ICON>>(){}.getType());
					int size = iconsTemp.size();
					ICON[] icons = (ICON[])iconsTemp.toArray(new ICON[size]);
					//通过服务器返回的数据动态添加分类图标
					addhorizontalLinelayout(icons);
					break;
				default:
					break;
				}
			}
		};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_classify, container, false);
		categoryIconBar = (LinearLayout)view.findViewById(R.id.categoryIconBar);
		Map<String, String> map = new HashMap<String, String>();
		HttpRequestTool.sendHttpRequest(getResources().getString(R.string.ServerIP)+ "iconQuery.action",map, handler);
		return view;
	}

	private void addhorizontalLinelayout(ICON icons[]) {
		int categoryNum = icons.length;
		LinearLayout horizontalLinelayout = new LinearLayout(getActivity());
		LinearLayout.LayoutParams horizontalLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		horizontalLinelayout.setLayoutParams(horizontalLayoutParams);
		horizontalLinelayout.setGravity(Gravity.LEFT);
		for(int i = 1;i<=categoryNum;i++){
			createCategoryIcon(horizontalLinelayout,icons[i-1]);
			if((i==categoryNum||i%3==0)&&i!=0){
				categoryIconBar.addView(horizontalLinelayout);
				horizontalLinelayout = new LinearLayout(getActivity());
				horizontalLinelayout.setLayoutParams(horizontalLayoutParams);
				horizontalLinelayout.setGravity(Gravity.LEFT);
			}
		}
	}

	private void createCategoryIcon(LinearLayout horizontalLinelayout,ICON icon) {
		
		LinearLayout linelayouttest = new LinearLayout(getActivity());
		LinearLayout linelayouttest2 = new LinearLayout(getActivity());
		
		linelayouttest2.setGravity(Gravity.CENTER);
		int heightAndWidth = TransformUnit.dip2px(getActivity(), 55);
		linelayouttest2.setLayoutParams(new LayoutParams(heightAndWidth, heightAndWidth));
		GradientDrawable gd = new GradientDrawable();
		gd.setColor(Color.parseColor(icon.getIconColor()));																													//icon背景颜色
		gd.setCornerRadius(100);
		linelayouttest2.setBackgroundDrawable(gd);
		linelayouttest2.setId(icon.getIconResource());
		linelayouttest2.setOnClickListener(CategoryOnClickListener);
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(icon.getIconResource());																															//icon的resource
		int iconSize = TransformUnit.dip2px(getActivity(), 25);
		iv.setLayoutParams(new LayoutParams(iconSize,iconSize));
		iv.setScaleType(ScaleType.CENTER_INSIDE);
		linelayouttest2.addView(iv);
		
		linelayouttest.setOrientation(LinearLayout.VERTICAL);
		TextView tv = new TextView(getActivity());
		tv.setText(icon.getIconTitle());																																						//分类名称
		tv.setTextSize(13);
		tv.setTextColor(Color.BLACK);
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
		DisplayMetrics dm = ConfigTool.getPhoneSize(getActivity());
		int linelayouttestWidth = dm.widthPixels/3;
		LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(linelayouttestWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
		int marginTop = TransformUnit.dip2px(getActivity(), 25);
		ll.setMargins(0, marginTop, 0, 0);
		linelayouttest.setLayoutParams(ll);
		linelayouttest.setGravity(Gravity.CENTER_HORIZONTAL);
		linelayouttest.addView(linelayouttest2);
		linelayouttest.addView(tv);
		linelayouttest.setClickable(true);
		horizontalLinelayout.addView(linelayouttest);
	}
	private OnClickListener CategoryOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			System.out.println(v.getId());
			Intent intent = new Intent(getActivity(),CommodityListActivity.class);
			intent.putExtra("icon_srcId", v.getId());
			startActivity(intent);
		}
	};
}
