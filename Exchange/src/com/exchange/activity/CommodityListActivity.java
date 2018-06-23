package com.exchange.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Toast;

import com.exchange.entity.CATEGORY;
import com.exchange.entity.COMMODITY;
import com.exchange.tool.HttpRequestPicTool;
import com.exchange.tool.HttpRequestTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CommodityListActivity extends Activity {
	private ListView commodity_list;
	private String[] commodity_id;
	private String[] commodity_name;
	private double[] commodity_expectPrice;
	private String[] commodity_imgUrl;
	private String[] commodity_degree;
	// 声明定义此Activity的Handler
	public Handler handlerGetCategory = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Bundle data = msg.getData();
				String val = data.getString("value").trim();
				System.out.println(val);
				Gson gson = new Gson();
				List<CATEGORY> categorys = gson.fromJson(val,
						new TypeToken<List<CATEGORY>>() {
						}.getType());
				Map<String, String> map = new HashMap<String, String>();
				map.put("commodity.commodity_Ptype", categorys.get(0)
						.getCategory_id() + "");
				HttpRequestTool.sendHttpRequest(
						getResources().getString(R.string.ServerIP)
								+ "commodityQueryByCategoryPId.action", map,
						handlerGetCommodityList);
			default:
				break;
			}
		}

	};
	public Handler handlerGetCommodityList = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Bundle data = msg.getData();
				String val = data.getString("value").trim();
				System.out.println(val);
				transformJsonString(val);
			default:
				break;
			}
		}

		private void transformJsonString(String jsonValue) {
			Gson gson = new Gson();
			List<COMMODITY> categoryTemp = gson.fromJson(jsonValue,
					new TypeToken<List<COMMODITY>>() {
					}.getType());
			int size = categoryTemp.size();
			COMMODITY[] categorys = (COMMODITY[]) categoryTemp
					.toArray(new COMMODITY[size]);
			commodity_name = new String[size];
			commodity_expectPrice = new double[size];
			commodity_degree = new String[size];
			commodity_imgUrl = new String[size];
			commodity_id = new String[size];
			for (int i = 0; i < categorys.length; i++) {
				commodity_id[i] = categorys[i].getCommodity_id();
				commodity_name[i] = categorys[i].getCommodity_name();
				commodity_expectPrice[i] = categorys[i]
						.getCommodity_expectPrice();
				commodity_degree[i] = categorys[i].getCommodity_degree();
				String[] imgUrlArray = categorys[i].getCommodity_imgUrl()
						.split(";");
				commodity_imgUrl[i] = getResources().getString(
						R.string.ServerIP)
						+ imgUrlArray[0];
			}
			HttpRequestPicTool.GetHttpRequestPic(commodity_imgUrl,
					handlerSetCommodityList);
		}
	};
	public Handler handlerSetCommodityList = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Bundle data = msg.getData();
			switch (msg.what) {
			case 0:
				Bitmap[] pics = (Bitmap[]) data.getParcelableArray("mBitmaps");
				List<Map<String, Object>> listd = new ArrayList<Map<String, Object>>();
				Map<String, Object> map;
				for (int i = 0; i < commodity_name.length; i++) {
					map = new HashMap<String, Object>();
					map.put("commodity_id", commodity_id[i]);
					map.put("listview_commodity_name", commodity_name[i]);
					map.put("listview_commodity_expectPrice",
							commodity_expectPrice[i]);
					map.put("listview_commodity_degree", commodity_degree[i]);
					map.put("listview_commodity_img", pics[i]);
					listd.add(map);
				}
				setListView(listd);
			default:
				break;
			}
		}

	};

	private void setListView(List<Map<String, Object>> listdate) {
		SimpleAdapter adapter = new SimpleAdapter(
				this,
				listdate,
				R.layout.listview_commodity,
				new String[] { "listview_commodity_name",
						"listview_commodity_expectPrice",
						"listview_commodity_degree", "listview_commodity_img" },
				new int[] { R.id.listview_commodity_name,
						R.id.listview_commodity_expectPrice,
						R.id.listview_commodity_degree,
						R.id.listview_commodity_img });
		adapter.setViewBinder(new ViewBinder() {
			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				// 判断是否为我们要处理的对象
				if (view instanceof ImageView && data instanceof Bitmap) {
					ImageView iv = (ImageView) view;
					iv.setImageBitmap((Bitmap) data);
					return true;
				} else
					return false;
			}
		});
		commodity_list.setAdapter(adapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commodity_list);
		initComponent();
		Intent intent = getIntent();
		String icon_srcId = intent.getIntExtra("icon_srcId", 0) + "";
		Map<String, String> map = new HashMap<String, String>();
		map.put("category.category_icon", icon_srcId);
		HttpRequestTool.sendHttpRequest(
				getResources().getString(R.string.ServerIP)
						+ "categoryqueryByIconsrc.action", map,
				handlerGetCategory);
	}

	private void initComponent() {
		commodity_list = (ListView) findViewById(R.id.commodity_list);
		commodity_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long arg3) {
				HashMap<String, String> map = (HashMap<String, String>) parent
						.getItemAtPosition(position);
				Toast.makeText(view.getContext(), map.get("commodity_id"),
						Toast.LENGTH_SHORT).show();
				String cid = map.get("commodity_id");
				System.out.println(cid);
				Intent intent = new Intent(CommodityListActivity.this,
						CommodityDetailActivity.class);
				intent.putExtra("commodity_id", cid);
				startActivity(intent);
			}
		});
	}

}
