package com.exchange.tool;

import android.content.Context;

//��λת����
public class TransformUnit {
	//dipת����px
	public static int dip2px(Context context, float dpValue) {
		 final float scale = context.getResources().getDisplayMetrics().density;
		 return (int) (dpValue * scale + 0.5f); 
		}
}
