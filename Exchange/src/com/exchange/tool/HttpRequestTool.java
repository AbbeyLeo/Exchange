package com.exchange.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import com.exchange.activity.LoginActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

//http请求工具类
public class HttpRequestTool {
	public static void sendHttpRequest(final String requestAction,final Map<String, String> param,final Handler handler){
		Runnable networkTask = new Runnable() {
			@Override
			public void run() {
				// 在这里进行 http request.网络请求相关操作
				HttpURLConnection conn = null; //连接对象  
				InputStream is = null;  	
				String resultData = ""; 
				// 先储存请求地址的URL
				String request = requestAction;
				// 初始化计数器为0
				int count = 0;
				if (!param.isEmpty()) {
					// 通过map和url结构化请求地址
					request += "?";
					Iterator iterator = param.entrySet().iterator();
					while (iterator.hasNext()) {
						if (count != 0) {
							request += "&";
						}
						Map.Entry entry = (Map.Entry) iterator.next();
						String key = entry.getKey().toString();
						String val = entry.getValue().toString();
						request += key + "=" + val;
						count++;
					}
					System.out.println(request);
				}
				try {
					URL url = new URL(request); //URL对象  
		            conn = (HttpURLConnection)url.openConnection(); //使用URL打开一个链接  
		            conn.setDoInput(true); //允许输入流，即允许下载  
		            conn.setDoOutput(true); //允许输出流，即允许上传  
		            conn.setUseCaches(false); //不使用缓冲  
		            conn.setRequestMethod("GET"); //使用get请求  
		            is = conn.getInputStream();   //获取输入流，此时才真正建立链接  
		            InputStreamReader isr = new InputStreamReader(is);  
		            BufferedReader bufferReader = new BufferedReader(isr);  
		            String inputLine  = "";  
		            while((inputLine = bufferReader.readLine()) != null){  
		                resultData += inputLine + "\n"; 
		                }
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{  
		            if(is != null){  
		                try {  
		                    is.close();  
		                } catch (IOException e) {  
		                    e.printStackTrace();  
		                }  
		            }  
		            if(conn != null){  
		                conn.disconnect();  
		            }  
		        }  
				Message msg = new Message();
				msg.what = 0;
				Bundle data = new Bundle();
				data.putString("value", resultData);
				msg.setData(data);
				handler.sendMessage(msg);
			}
		};
		new Thread(networkTask).start();
	}
	/**
	 * 文件不存在
	 */
	public static final int UPLOAD_FILE_NOT_EXISTS_CODE = 2;
	/**
	 * 服务器出错
	 */
	public static final int UPLOAD_SERVER_ERROR_CODE = 3;
	protected static final int WHAT_TO_UPLOAD = 1;
	protected static final int WHAT_UPLOAD_DONE = 2;
	/**
	 * android上传文件到服务器
	 * 
	 * @param file
	 *            需要上传的文件
	 * @param fileKey
	 *            在网页上<input type=file name=xxx/> xxx就是这里的fileKey
	 * @param RequestURL
	 *            请求的URL
	 */
	public static void uploadFile(final File file, final String fileKey, final String RequestURL, final Map<String, String> param){
		Log.i("exchange", "请求的URL=" + RequestURL);
		Log.i("exchange", "请求的fileName=" + file.getName());
		Log.i("exchange", "请求的fileKey=" + fileKey);
		new Thread(new Runnable() {  //开启线程上传文件
			@Override
			public void run() {
				toUploadFile(file, fileKey, RequestURL, param);
			}

			private void toUploadFile(File file, String fileKey,
					String requestURL, Map<String, String> param) {
				
			}
		}).start();
	}
	
}
