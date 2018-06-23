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

//��¼��Activity
public class LoginActivity extends Activity {
	public static final int SHOW_RESPONSE = 0;
	private TextView change_to_register_textView; // ������ת��ע��ҳ���textView���
	private EditText phoneEditView; // �����û��˺ŵ�EditView���
	private EditText passwordEditView; // �����û������EditView���
	private Button login_in_button; // �����û���¼��Button���
	private static ProgressDialog pd; // ������¼�ȴ���
	//���������Activity��Handler
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				pd.dismiss();
				Bundle data = msg.getData();
				String val = data.getString("value").trim();
				if (val.equals("ʧ��") || val.equals("") || val == null) {
					Toast.makeText(LoginActivity.this, "��¼ʧ��",
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

	// ��ʼ�����
	private void initComponent() {
		change_to_register_textView = (TextView) findViewById(R.id.change_to_register_textView); // ��ȡ��ת��ע��ҳ���textView���
		phoneEditView = (EditText) findViewById(R.id.phoneEditView); // ��ȡ�û��˺ŵ�EditView���
		passwordEditView = (EditText) findViewById(R.id.passwordEditView); // ��ȡ�û������EditView���
		login_in_button = (Button) findViewById(R.id.login_in_button); // ��ȡ�û���¼��Button���
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
		// ע������¼�
		// ��ת��ע��ҳ��ļ����¼�
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
		// ��¼�ļ����¼�
		login_in_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ��ȡ�û�������˺ţ�������ȥ���ո��ַ�������
				String user_account = phoneEditView.getText().toString().trim();
				// ��ȡ�û���������룬������ȥ���ո��ַ�������
				String user_password = passwordEditView.getText().toString()
						.trim();
				// ���������˺������map
				Map<String, String> map = new HashMap<String, String>();
				// �����˺�����
				map.put("user.user_account", user_account);
				map.put("user.user_password", user_password);
				pd = ProgressDialog.show(LoginActivity.this, "���ڵ�¼",
						"��¼�У����Ժ󡭡�");
				// ����Http���󹤾���
				HttpRequestTool.sendHttpRequest(
						getResources().getString(R.string.ServerIP)
								+ "login.action", map, handler);
				// //����������¼���
				// Intent intent = new Intent(LoginActivity.this,
				// NavigationBarActivity.class);
				// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				// | Intent.FLAG_ACTIVITY_NEW_TASK);
				// startActivity(intent);
			}
		});
	}

	// �����¼���
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
