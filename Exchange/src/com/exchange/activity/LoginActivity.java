package com.exchange.activity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exchange.entity.USER;
import com.exchange.tool.HistoryOpenHelper;
import com.exchange.tool.HttpRequestTool;
import com.google.gson.Gson;

//登录的Activity
public class LoginActivity extends Activity {
	public static final int SHOW_RESPONSE = 0;
	private TextView change_to_register_textView; // 声明跳转到注册页面的textView组件
	private EditText phoneEditView; // 声明用户账号的EditView组件
	private EditText passwordEditView; // 声明用户密码的EditView组件
	private Button login_in_button; // 声明用户登录的Button组件
	private static ProgressDialog pd; // 声明登录等待框
	//声明定义此Activity的Handler
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				pd.dismiss();
				Bundle data = msg.getData();
				String val = data.getString("value").trim();
				if (val.equals("失败") || val.equals("") || val == null) {
					Toast.makeText(LoginActivity.this, "登录失败",
							Toast.LENGTH_SHORT).show();
				} else {
					Gson gson = new Gson();
					USER user = gson.fromJson(val, USER.class);
					System.out.println(user.toString());
					SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
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
					loginSaveResultToDB();
					Intent intent = new Intent(LoginActivity.this,
							NavigationBarActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
							| Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				}
				break;
			default:
				break;
			}
		}
	};

	// 初始化组件
	private void initComponent() {
		change_to_register_textView = (TextView) findViewById(R.id.change_to_register_textView); // 获取跳转到注册页面的textView组件
		phoneEditView = (EditText) findViewById(R.id.phoneEditView); // 获取用户账号的EditView组件
		passwordEditView = (EditText) findViewById(R.id.passwordEditView); // 获取用户密码的EditView组件
		login_in_button = (Button) findViewById(R.id.login_in_button); // 获取用户登录的Button组件
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initComponent();
		HistoryOpenHelper dbHelper = new HistoryOpenHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("history_user", null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			cursor.move(0);
			String username = cursor.getString(1);
			String password = cursor.getString(2);
			long last_login = cursor.getInt(3);
			Date now = new Date();
			if ((now.getTime()/1000 - last_login) < 60 * 30) {
				db.close();
				Intent intent = new Intent(LoginActivity.this,
						NavigationBarActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			} 
		}
		db.close();
		// 注册监听事件
		// 跳转到注册页面的监听事件
		change_to_register_textView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						RegisterActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
		// 登录的监听事件
		login_in_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 获取用户输入的账号，并进行去除空格字符串处理
				String user_account = phoneEditView.getText().toString().trim();
				// 获取用户输入的密码，并进行去除空格字符串处理
				String user_password = passwordEditView.getText().toString()
						.trim();
				// 声明储存账号密码的map
				Map<String, String> map = new HashMap<String, String>();
				// 存入账号密码
				map.put("user.user_account", user_account);
				map.put("user.user_password", user_password);
				pd = ProgressDialog.show(LoginActivity.this, "正在登录",
						"登录中，请稍后……");
				// 调用Http请求工具类
				HttpRequestTool.sendHttpRequest(
						getResources().getString(R.string.ServerIP)
								+ "login.action", map, handler);
				// //测试跳过登录检测
				// Intent intent = new Intent(LoginActivity.this,
				// NavigationBarActivity.class);
				// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				// | Intent.FLAG_ACTIVITY_NEW_TASK);
				// startActivity(intent);
			}
		});
	}

	// 处理登录结果
	private void loginSaveResultToDB() {
		HistoryOpenHelper dbHelper = new HistoryOpenHelper(LoginActivity.this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("history_user", null, null, null, null, null,
				null);
		ContentValues cValue = new ContentValues();
		cValue.put("user_account", phoneEditView.getText().toString().trim());
		cValue.put("user_password", passwordEditView.getText().toString()
				.trim());
		Date now = new Date();
		cValue.put("last_login", now.getTime()/1000);
		if (cursor.moveToFirst()) {
			String whereClause = "history_id=1";
			db.update("history_user", cValue, whereClause, null);
		} else {
			db.insert("history_user", null, cValue);
		}
		db.close();
	}
}
