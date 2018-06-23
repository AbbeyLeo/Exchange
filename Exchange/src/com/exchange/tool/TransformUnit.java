package com.exchange.tool;

import android.content.Context;

//单位转换类
public class TransformUnit {
	//dip转换成px
	public static int dip2px(Context context, float dpValue) {
		 final float scale = context.getResources().getDisplayMetrics().density;
		 return (int) (dpValue * scale + 0.5f); 
		}
}
