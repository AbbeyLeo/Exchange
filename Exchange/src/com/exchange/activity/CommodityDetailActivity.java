package com.exchange.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exchange.entity.CATEGORY;
import com.exchange.entity.COMMODITY;
import com.exchange.tool.HttpRequestPicTool;
import com.exchange.tool.HttpRequestTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CommodityDetailActivity extends Activity {
	private TextView commodity_title;
	private TextView commodity_expectPrice;
	private TextView commodity_originalPrice;
	private TextView commodity_detail;
	private ImageView backButtom;
	private List<View> releaseViewList = new ArrayList<View>();
	private ViewPager releaseViewPager; 
	// 声明定义此Activity的Handler
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Bundle data = msg.getData();
				String val = data.getString("value").trim();
				// System.out.println(val);
				Gson gson = new Gson();
				List<COMMODITY> commoditys = gson.fromJson(val,
						new TypeToken<List<COMMODITY>>() {
						}.getType());
				updateCommdityDetail(commoditys.get(0));
			default:
				break;
			}
		}
	};
	public Handler handlerSetCommodityImgViewPage = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Bundle data = msg.getData();
			switch (msg.what) {
			case 0:
				Bitmap[] pics = (Bitmap[]) data.getParcelableArray("mBitmaps");
				for(int i=0;i<pics.length;i++){
					ImageView imageView = new ImageView(CommodityDetailActivity.this);
					imageView.setImageBitmap(pics[i]);
					imageView.setMaxHeight(280);  
					releaseViewList.add(imageView);
				}
				PagerAdapter pagerAdapter = new PagerAdapter() {
					@Override
					public boolean isViewFromObject(View arg0, Object arg1) {
						return arg0 == arg1;
					}
					@Override
					public int getCount() {
						return releaseViewList.size();  
					}
					@Override
					public void destroyItem(ViewGroup container, int position,
							Object object) {
						container.removeView(releaseViewList.get(position));
					}
					@Override
					public Object instantiateItem(ViewGroup container, int position) {
						container.addView(releaseViewList.get(position));
						return releaseViewList.get(position); 
					}
				};
				releaseViewPager.setAdapter(pagerAdapter);  
			default:
				break;
			}
		}
	};
	private void updateCommdityDetail(COMMODITY c){
		commodity_title.setText(c.getCommodity_name());
		commodity_expectPrice.setText("现价 "+c.getCommodity_expectPrice()+"");
		commodity_originalPrice.setText("原价 "+c.getCommodity_originalPrice()+"");
		commodity_detail.setText(c.getCommodity_detail());
		String[] imgUrlArray = c.getCommodity_imgUrl().split(";");
		for(int i=0;i<imgUrlArray.length;i++){
			imgUrlArray[i] = getResources().getString(
					R.string.ServerIP)
					+ imgUrlArray[i];
		}
		HttpRequestPicTool.GetHttpRequestPic(imgUrlArray, handlerSetCommodityImgViewPage);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commodity_detail);
		initComponent();
		Intent intent = getIntent();
		String commodity_id = intent.getStringExtra("commodity_id");
		Map<String, String> map = new HashMap<String, String>();
		map.put("commodity.commodity_id", commodity_id);
		HttpRequestTool.sendHttpRequest(
				getResources().getString(R.string.ServerIP)
						+ "commodityQueryByCategoryId.action", map, handler);
	}

	private void initComponent() {
		commodity_title = (TextView)findViewById(R.id.commodityTitle);
		commodity_expectPrice = (TextView)findViewById(R.id.commodityPrice);
		commodity_originalPrice = (TextView) findViewById(R.id.oldPrice);
		commodity_detail = (TextView)findViewById(R.id.commodity_detail);
		backButtom = (ImageView)findViewById(R.id.backImage);
		releaseViewPager = (ViewPager)findViewById(R.id.releaseViewPager);
		backButtom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		commodity_originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
	}
}
