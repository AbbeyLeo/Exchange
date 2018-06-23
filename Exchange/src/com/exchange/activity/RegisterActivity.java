package com.exchange.activity;

import java.util.HashMap;
import java.util.Map;

import com.exchange.tool.Generation;
import com.exchange.tool.HttpRequestTool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private TextView change_to_login_textView; // 声明跳转到登录页面的textView组件
	private EditText RPhoneView; // 声明手机填写的editText组件
	private EditText RPasswordView; // 声明密码填写的editText组件
	private EditText RCheckTextView; // 声明验证码填写的editText组件
	private Button RCheckButton; // 声明获取验证码的按钮
	private Button RegisterButton; // 声明注册按钮
	private String phone;//声明注册的手机号，获取验证码的时候赋值
	private String cid;//声明储存生成验证码的标识符
	//声明定义此Activity的Handler
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Bundle data = msg.getData();
				String val = data.getString("value").trim();
				System.out.println(val);
				if(val.equals("1")){
					Toast.makeText(RegisterActivity.this, "验证码已成功发送",Toast.LENGTH_SHORT).show();
				}else if(val.equals("-3")) {
					Toast.makeText(RegisterActivity.this, "软件验证码功能已停用",Toast.LENGTH_SHORT).show();
					RCheckButton.setEnabled(true);
				}else if(val.equals("-4")) {
					Toast.makeText(RegisterActivity.this, "手机号格式不正确",Toast.LENGTH_SHORT).show();
					RCheckButton.setEnabled(true);
				}else if(val.equals("-41")) {
					Toast.makeText(RegisterActivity.this, "手机号码为空",Toast.LENGTH_SHORT).show();
					RCheckButton.setEnabled(true);
				}else if(val.equals("注册成功")) {
					Toast.makeText(RegisterActivity.this, "注册成功",Toast.LENGTH_SHORT).show();
					 Intent intent = new Intent(RegisterActivity.this,
					 LoginActivity.class);
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
	//初始化组件
	private void initComponent() {
		RPhoneView = (EditText) findViewById(R.id.phoneEditView);
		RPasswordView = (EditText) findViewById(R.id.passwordEditView);
		RCheckTextView = (EditText) findViewById(R.id.CheckPhoneText);
		RCheckButton = (Button) findViewById(R.id.getCheckNumBotton);
		RegisterButton = (Button) findViewById(R.id.register_in_button);
		change_to_login_textView = (TextView) findViewById(R.id.change_to_login_textView);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initComponent();
		// 注册监听事件
		RegisterButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String captcha = RCheckTextView.getText().toString().trim();
				String password = RPasswordView.getText().toString().trim();
				if(password.equals("")||password==null){
					Toast.makeText(RegisterActivity.this, "密码不能为空",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if(!captcha.equals("")&&captcha!=null){
					Map<String, String> map = new HashMap<String, String>();
					map.put("user.user_account", phone);
					map.put("user.user_password", password);
					map.put("nickName", cid);
					map.put("captcha", captcha);
					// 调用Http请求工具类
					HttpRequestTool.sendHttpRequest(getResources().getString(R.string.ServerIP)+ "register.action",map, handler);
				}
			}
		});
		//获取验证码的监听事件
		RCheckButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RCheckButton.setEnabled(false);
				Map<String, String> map = new HashMap<String, String>();
				phone = RPhoneView.getText().toString().trim();
				if(phone!=null&&!phone.equals("")){
					cid = Generation.generateCid();
					map.put("smsMob", phone);
					map.put("cid", cid);
					// 调用Http请求工具类
					HttpRequestTool.sendHttpRequest(getResources().getString(R.string.ServerIP)+ "createCaptcha.action",map, handler);
				}else{
					Toast.makeText(RegisterActivity.this, "注册手机号不能为空",
							Toast.LENGTH_SHORT).show();
					RCheckButton.setEnabled(true);
				}
			}
		});
		// 跳转到注册页面的监听事件
		change_to_login_textView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RegisterActivity.this,
						LoginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
	}

}
