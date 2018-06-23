package com.exchange.activity;

import java.util.HashMap;
import java.util.Map;

import com.exchange.entity.USER;
import com.exchange.tool.HttpRequestTool;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingUserInfoActivity extends Activity {
	private EditText et_userNick;
	private EditText et_userSex;
	private EditText et_userAddress;
	private EditText et_userEmail;
	private EditText et_userTel;
	private Button btn_updateUserInfo;
	// 声明定义此Activity的Handler
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Bundle data = msg.getData();
				String val = data.getString("value").trim();
				if (val.equals("失败") || val.equals("") || val == null) {
					Toast.makeText(SettingUserInfoActivity.this, "修改失败",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(SettingUserInfoActivity.this, "修改成功",
							Toast.LENGTH_SHORT).show();
					finish();
				}
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settinguserinfo);
		initCompent();
	}

	private void initCompent() {
		et_userNick = (EditText) findViewById(R.id.et_userNick);
		et_userSex = (EditText) findViewById(R.id.et_userSex);
		et_userAddress = (EditText) findViewById(R.id.et_userAddress);
		et_userEmail = (EditText) findViewById(R.id.et_userEmail);
		et_userTel = (EditText) findViewById(R.id.et_userTel);
		btn_updateUserInfo = (Button) findViewById(R.id.btn_updateUserInfo);
		final SharedPreferences sharedPreferences = getSharedPreferences("userInfo",
				Context.MODE_PRIVATE);
		String user_gender = sharedPreferences.getString("user_gender", "NULL");
		et_userNick.setText(sharedPreferences.getString("user_name", "NULL"));
		et_userAddress.setText(sharedPreferences.getString("user_address",
				"NULL"));
		et_userSex.setText((user_gender.equals("0") ? "女" : "男"));
		et_userEmail.setText(sharedPreferences.getString("user_email", "NULL"));
		et_userTel.setText(sharedPreferences.getString("user_tel", "NULL"));
		btn_updateUserInfo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String gender_temp = et_userSex.getText().toString().trim();
				String user_name = et_userNick.getText().toString().trim();
				String user_address = et_userAddress.getText().toString()
						.trim();
				String user_gender = (gender_temp.equals("男") ? "1" : "0");
				String user_email = et_userEmail.getText().toString().trim();
				String user_tel = et_userTel.getText().toString().trim();
				System.out.println(user_name);
				System.out.println(user_address);
				System.out.println(user_gender);
				System.out.println(user_email);
				System.out.println(user_tel);
				// 声明储存账号密码的map
				Map<String, String> map = new HashMap<String, String>();
				map.put("user.user_id", sharedPreferences.getString("user_id", "NULL"));
				map.put("user.user_nickName", user_name);
				map.put("user.user_password", sharedPreferences.getString("user_password", "NULL"));
				map.put("user.user_account", sharedPreferences.getString("user_account", "NULL"));
				map.put("user.user_address", user_address);
				map.put("user.user_gender", user_gender);
				map.put("user.user_email", user_email);
				map.put("user.user_tel", user_tel);
				map.put("user.user_type", sharedPreferences.getString("user_type", "NULL"));
				map.put("user.user_imgUrl", sharedPreferences.getString("user_imgUrl", "NULL"));
				HttpRequestTool.sendHttpRequest(
						getResources().getString(R.string.ServerIP)
								+ "update.action", map, handler);
			}
		});
	}
}
