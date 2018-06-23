/**
 * 
 */
package com.exchange.tool;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author YXF
 *	获取手机配置类
 */
public class ConfigTool {
	//获取当前手机尺寸
	public static DisplayMetrics getPhoneSize(Context ctx){
		DisplayMetrics dm2 = ctx.getResources().getDisplayMetrics();
		return dm2;
	}
}
