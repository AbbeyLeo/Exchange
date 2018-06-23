package com.exchange.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ResourceAsColor")
public class NavigationBarActivity extends FragmentActivity {
	int color_BALCK = Color.BLACK;
	private MainFragment mainFragment;
	private ClassifyFragment classifyFragment;
	private MessageFragment messageFragment;
	private UserFragment userFragment;
	private ImageView indexImageView;
	private ImageView categoryImageView;
	private ImageView messageImageView;
	private ImageView personalImageView;
	private TextView tv_navigationIndex;
	private TextView tv_navigationClassify;
	private TextView tv_navigationMessage;
	private TextView tv_navigationPersonal;
	private LinearLayout turnToRelease;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actiity_navigationbar);
		indexImageView = (ImageView) findViewById(R.id.indexImageView);
		categoryImageView = (ImageView) findViewById(R.id.categoryImageView);
		messageImageView = (ImageView) findViewById(R.id.messageImageView);
		personalImageView = (ImageView) findViewById(R.id.personalImageView);
		turnToRelease = (LinearLayout)findViewById(R.id.turnToRelease);
		tv_navigationIndex = (TextView)findViewById(R.id.tv_navigationIndex);
		tv_navigationClassify = (TextView)findViewById(R.id.tv_navigationClassify);
		tv_navigationMessage = (TextView)findViewById(R.id.tv_navigationMessage);
		tv_navigationPersonal = (TextView)findViewById(R.id.tv_navigationPersonal);
		
		setDefaultFragment();
		bindview();
//		checkCompleteUserInfo();
	}

	private void bindview() {
		indexImageView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				selected();
				resetTvColor();
				tv_navigationIndex.setTextColor(color_BALCK);
				indexImageView.setSelected(true);
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				mainFragment = new MainFragment();
				transaction.replace(R.id.frameLayoutContent, mainFragment);
				transaction.commit();
				checkCompleteUserInfo();
			}
		});
		categoryImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				selected();
				resetTvColor();
				tv_navigationClassify.setTextColor(color_BALCK);
				categoryImageView.setSelected(true);
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				classifyFragment = new ClassifyFragment();
				transaction.replace(R.id.frameLayoutContent, classifyFragment);
				transaction.commit();
				checkCompleteUserInfo();
			}
		});
		messageImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selected();
				resetTvColor();
				tv_navigationMessage.setTextColor(color_BALCK);
				messageImageView.setSelected(true);
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				messageFragment = new MessageFragment();
				transaction.replace(R.id.frameLayoutContent, messageFragment);
				transaction.commit();
				checkCompleteUserInfo();
			}
		});
		personalImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selected();
				resetTvColor();
				tv_navigationPersonal.setTextColor(color_BALCK);
				personalImageView.setSelected(true);
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				userFragment = new UserFragment();
				transaction.replace(R.id.frameLayoutContent, userFragment);
				transaction.commit();
				checkCompleteUserInfo();
			}
		});
		turnToRelease.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(NavigationBarActivity.this,ReleaseActivity.class);
				startActivity(intent);
			}
		});
	}

	private void setDefaultFragment() {
		indexImageView.setSelected(true);
		resetTvColor();
		tv_navigationIndex.setTextColor(color_BALCK);
		mainFragment = new MainFragment();
		
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
		mainFragment = new MainFragment();
		transaction.replace(R.id.frameLayoutContent, mainFragment);
		
		transaction.commit();
	}

	// 重置所有图片的选中状态
	public void selected() {
		indexImageView.setSelected(false);
		categoryImageView.setSelected(false);
		messageImageView.setSelected(false);
		personalImageView.setSelected(false);
	}
	//重置所以导航栏的文字颜色
	
	public void resetTvColor(){
		tv_navigationIndex.setTextColor(R.color.RegisterBackground);
		tv_navigationClassify.setTextColor(R.color.RegisterBackground);
		tv_navigationMessage.setTextColor(R.color.RegisterBackground);
		tv_navigationPersonal.setTextColor(R.color.RegisterBackground);
	}
	//判断是否完善用户信息
	public void checkCompleteUserInfo(){
		SharedPreferences sharedPreferences = getSharedPreferences("userInfo",
				Context.MODE_PRIVATE);
		String address = sharedPreferences.getString("user_address", "没拿到");
		if(address.equals("没拿到")||address==null||address.equals("")){
			selected();
			resetTvColor();
			tv_navigationPersonal.setTextColor(color_BALCK);
			personalImageView.setSelected(true);
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction transaction = fm.beginTransaction();
			userFragment = new UserFragment();
			transaction.replace(R.id.frameLayoutContent, userFragment);
			transaction.commit();
			Toast.makeText(NavigationBarActivity.this, "请先完善用户信息",
					Toast.LENGTH_SHORT).show();
		}
	}
}
