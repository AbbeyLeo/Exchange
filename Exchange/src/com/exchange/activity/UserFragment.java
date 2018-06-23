package com.exchange.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exchange.entity.USER;
import com.exchange.tool.HistoryOpenHelper;
import com.exchange.tool.HttpRequestPicTool;
import com.exchange.tool.HttpRequestTool;
import com.google.gson.Gson;

public class UserFragment extends Fragment {
	private LinearLayout settingUserInfo;
	private ImageView userImage;
	private TextView userName;
	//声明定义此Activity的Handler
		public Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					Bundle data = msg.getData();
					String val = data.getString("value").trim();
					System.out.println(val);
					Gson gson = new Gson();
					USER user = gson.fromJson(val, USER.class);
					SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();
					editor.putString("user_id", user.getUser_id()+"");
					editor.putString("user_name", user.getUser_nickName());
					editor.putString("user_password", user.getUser_password());
					editor.putString("user_account", user.getUser_account()+"");
					editor.putString("user_gender", user.getUser_gender()+"");
					editor.putString("user_address", user.getUser_address());
					editor.putString("user_email", user.getUser_email()+"");
					editor.putString("user_tel", user.getUser_tel()+"");
					editor.putString("user_type", user.getUser_type()+"");
					editor.putString("user_imgUrl", user.getUser_imgUrl());
					editor.commit();
					updateUserInfo(user);
					break;
				default:
					break;
				}
			}
			private void updateUserInfo(USER user) {
				userName.setText(user.getUser_nickName());
				String[] imgUrlArray = user.getUser_imgUrl().split(";");
				imgUrlArray[0] = getResources().getString(R.string.ServerIP) + imgUrlArray[0];
				HttpRequestPicTool.GetHttpRequestPic(imgUrlArray, handlerSetUserImage);
			}
		};
		public Handler handlerSetUserImage = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Bundle data = msg.getData();
				switch (msg.what) {
				case 0:
					Bitmap[] pics = (Bitmap[]) data.getParcelableArray("mBitmaps");
					userImage.setImageBitmap(pics[0]);
					break;
				default:
					break;
				}
			}
		};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user, container, false);
		userImage = (ImageView)view.findViewById(R.id.userImage);
		userName = (TextView)view.findViewById(R.id.User_username);
		settingUserInfo = (LinearLayout)view.findViewById(R.id.settingUserInfo);
		settingUserInfo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//转向更新用户信息activity
				Intent intent = new Intent(getActivity(),
						SettingUserInfoActivity.class);
				startActivity(intent);
			}
		});
		HistoryOpenHelper dbHelper = new HistoryOpenHelper(getActivity());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("history_user", null, null, null, null, null,
				null);
		Map<String, String> map = new HashMap<String, String>();
		if (cursor.moveToFirst()) {
			cursor.move(0);
			String username = cursor.getString(1);
			String password = cursor.getString(2);
			map.put("user.user_account", username);
			map.put("user.user_password", password);
		}
		db.close();
		// 调用Http请求工具类
		HttpRequestTool.sendHttpRequest(
				getResources().getString(R.string.ServerIP)
						+ "login.action", map, handler);
		return view;
	}

}
