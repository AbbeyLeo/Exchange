package com.exchange.tool;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.exchange.activity.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

//http请求工具类
public class HttpRequestPicTool {
	public static void GetHttpRequestPic(final String imageUrl[],final Handler handler){
		Runnable networkTask = new Runnable() {
			@Override
			public void run() {
				Bitmap mBitmap = null;  
				HttpURLConnection conn;
				Bitmap[] bPic = new Bitmap[imageUrl.length];
				for(int i=0;i<imageUrl.length;i++){
					System.out.println(imageUrl[i]);
					bPic[i] = HttpRequestPicTool.getBitmap(imageUrl[i]);
				}
				Message msg = new Message();
				msg.what = 0;
				Bundle data = new Bundle();
				data.putParcelable("mBitmap", mBitmap);
				data.putParcelableArray("mBitmaps", bPic);
				msg.setData(data);
				handler.sendMessage(msg);
			}
		};
		new Thread(networkTask).start();
	}
	public static Bitmap getBitmap(String imageUrl){  
        Bitmap mBitmap = null;  
        try {  
            URL url = new URL(imageUrl);  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
            InputStream is = conn.getInputStream();  
            mBitmap = BitmapFactory.decodeStream(is);  
              
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        return mBitmap;  
    }
}
