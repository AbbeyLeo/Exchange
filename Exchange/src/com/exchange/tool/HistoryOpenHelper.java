package com.exchange.tool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryOpenHelper extends SQLiteOpenHelper {
	private static final int DATAABSE_VERSION = 1;
	private static final String User_TABLE_NAME = "history_user";
	private static final String User_TABLE_CREATE = "create table "+User_TABLE_NAME+"(history_id integer primary key autoincrement,user_account text,user_password,last_login integer)";
	public HistoryOpenHelper(Context context) {
		super(context, "history.db", null, DATAABSE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("create a database");  
        //execSQL”√”⁄÷¥––SQL”Ôæ‰  
		db.execSQL(User_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
